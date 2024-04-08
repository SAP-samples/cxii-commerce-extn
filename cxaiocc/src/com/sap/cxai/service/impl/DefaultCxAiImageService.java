/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.cxai.service.impl;

import de.hybris.platform.commercewebservicescommons.dto.product.ImageWsDTO;
import de.hybris.platform.core.PK;
import de.hybris.platform.servicelayer.cluster.ClusterService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import com.sap.cxai.event.ImageUploadedEvent;
import com.sap.cxai.model.CxAiMediaModel;
import com.sap.cxai.service.CxAiImageService;


/**
 *
 */
public class DefaultCxAiImageService implements CxAiImageService
{
	private static final Logger LOGGER = Logger.getLogger(DefaultCxAiImageService.class);

	private final Map<String, Pair<SseEmitter, ScheduledFuture<?>>> emitterMap = new ConcurrentHashMap<>();
	private final long emitterTimeout = 10 * 60 * 1000L;
	private final long emitterKeepAliveInterval = 30;
	private final FlexibleSearchService flexibleSearchService;
	private final MediaService mediaService;

	private final ModelService modelService;
	private final ConfigurationService configurationService;
	private final EventService eventService;
	private final ClusterService clusterService;
	private final ScheduledExecutorService sseKeepalivePool = Executors.newScheduledThreadPool(2);

	protected static final String IMAGE_RESIZE_PROPERTY = "stylebuddy.image.maxStorageSize";

	public DefaultCxAiImageService(final FlexibleSearchService flexibleSearchService, final MediaService mediaService,
			final ModelService modelService, final EventService eventService, final ClusterService clusterService,
			final ConfigurationService configurationService)
	{
		super();
		this.flexibleSearchService = flexibleSearchService;
		this.mediaService = mediaService;
		this.modelService = modelService;
		this.configurationService = configurationService;
		this.eventService = eventService;
		this.clusterService = clusterService;

		this.eventService.registerEventListener(new ImageUploadedListener());
	}

	protected long getImageSizeLimit()
	{
		return configurationService.getConfiguration().getLong(IMAGE_RESIZE_PROPERTY, Long.MAX_VALUE);
	}

	private class ImageUploadedListener implements ApplicationListener<ImageUploadedEvent>
	{
		@Override
		public void onApplicationEvent(final ImageUploadedEvent imageUploadedEvent)
		{
			LOGGER.info("Received ImageUploadedEvent " + imageUploadedEvent.getSid() + " in " + clusterService.getClusterId() + ", "
					+ clusterService.getClusterGroups());
			final String sid = imageUploadedEvent.getSid();
			final var media = imageUploadedEvent.getMedia();

			final ImageWsDTO img = new ImageWsDTO();
			if (media != null)
			{
				img.setUrl(media.getURL());
			}

			final SseEventBuilder event = SseEmitter.event() //
					.data(img) //
					.name("message");

			try
			{
				final var sseEmitterEntry = emitterMap.get(sid);
				if (sseEmitterEntry != null)
				{
					LOGGER.info("Sending image update for " + sid);
					sseEmitterEntry.getKey().send(event);
				}
				else
				{
					LOGGER.debug("Emitter " + sid + " not found in " + emitterMap);
				}
			}
			catch (final IOException ex)
			{
				LOGGER.error("Error sending sse event " + ex.getMessage());
				LOGGER.debug("Stack trace", ex);
			}
		}
	}

	private static class SseKeepaliveRunnable implements Runnable
	{
		int counter = 0;
		private final SseEmitter emitter;
		private final String sid;

		public SseKeepaliveRunnable(final SseEmitter emitter, final String sid)
		{
			super();
			this.emitter = emitter;
			this.sid = sid;
		}

		@Override
		public void run()
		{
			try
			{
				final SseEventBuilder event = SseEmitter.event() //
						.data(counter++) //
						.name("ping");

				emitter.send(event);
			}
			catch (final IOException e)
			{
				LOGGER.warn("Error sending keep-alive message for " + sid + ": " + e.getClass().getSimpleName());
				LOGGER.debug("Stacktrace", e);
			}
		}
	}

	@Override
	public SseEmitter getSseEmitter(final String sid)
	{
		var emitterEntry = emitterMap.get(sid);
		if (emitterEntry != null)
		{
			return emitterEntry.getKey();
		}

		synchronized (this)
		{
			emitterEntry = emitterMap.get(sid);
			if (emitterEntry != null)
			{
				return emitterEntry.getKey();
			}

			final var emitter = new SseEmitter(this.emitterTimeout);
			emitter.onCompletion(() -> {
				final var entry = emitterMap.remove(sid);
				LOGGER.info("Remove emitter " + sid + ": " + entry);
				entry.getValue().cancel(true);
			});
			emitter.onTimeout(emitter::complete);
			emitter.onError((ex) -> {
				if (ex instanceof IOException)
				{
					LOGGER.info("SSE emitter IOException in " + sid);
					LOGGER.debug("Stacktrace", ex);
				}
				else
				{
					LOGGER.error("SSE emitter error in " + sid, ex);
				}

				final var entry = emitterMap.remove(sid);
				entry.getValue().cancel(true);
			});

			final ScheduledFuture<?> future = sseKeepalivePool.scheduleAtFixedRate(new SseKeepaliveRunnable(emitter, sid), 5,
					emitterKeepAliveInterval, TimeUnit.SECONDS);
			emitterMap.put(sid, Pair.of(emitter, future));

			return emitter;
		}
	}

	@Override
	public void notifyImage(final String sid, final CxAiMediaModel media)
	{
		eventService.publishEvent(new ImageUploadedEvent(sid, media));
	}

	@Override
	public CxAiMediaModel getMedia(final String mediaId)
	{
		final CxAiMediaModel example = new CxAiMediaModel();
		example.setCode(mediaId);
		final var models = flexibleSearchService.getModelsByExample(example);
		if (models.isEmpty())
		{
			return null;
		}

		final var media = models.get(0);
		if (!mediaService.hasData(media))
		{
			modelService.remove(media);
			return null;
		}

		return media;
	}

	@Override
	public CxAiMediaModel uploadMedia(final String sid, final MultipartFile file) throws IOException
	{
		var currentMedia = getMedia(sid);
		if (currentMedia == null)
		{
			currentMedia = modelService.create(CxAiMediaModel.class);
			currentMedia.setCode(sid);
		}
		currentMedia.setExternalId(null);
		currentMedia.setExternalURL(null);
		currentMedia.setExternalURLExpiration(null);

		final long maxFileSize = getImageSizeLimit();
		if (LOGGER.isDebugEnabled())
		{
			LOGGER.debug("Size limit after which image is downscaled: " + maxFileSize + "(" + IMAGE_RESIZE_PROPERTY + ")");
		}

		if (file.getSize() < maxFileSize)
		{
			currentMedia.setRealFileName(file.getOriginalFilename());
			currentMedia.setMime(file.getContentType());

			modelService.save(currentMedia);
			try (var is = file.getInputStream())
			{
				mediaService.setStreamForMedia(currentMedia, is);
			}
		}
		else
		{
			currentMedia.setRealFileName(file.getOriginalFilename() + "_resized.jpg");
			currentMedia.setMime(MediaType.IMAGE_JPEG_VALUE);

			modelService.save(currentMedia);
			try (var is = getResizedImage(file, maxFileSize))
			{
				mediaService.setStreamForMedia(currentMedia, is);
			}
		}

		this.notifyImage(sid, currentMedia);
		return currentMedia;
	}

	private InputStream getResizedImage(final MultipartFile file, final long sizeLimit) throws IOException
	{
		final BufferedImage originalImage = ImageIO.read(file.getInputStream());

		// Calculate scaling ratio
		final double ratio = Math.sqrt((double) sizeLimit / (double) file.getSize());

		// Calculate new image size
		final int newWidth = (int) Math.floor(originalImage.getWidth() * ratio);
		final int newHeight = (int) Math.floor(originalImage.getHeight() * ratio);
		LOGGER.info("Image is too large, resize ratio " + ratio + ", new size " + newWidth + "x" + newHeight);

		final Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		final BufferedImage outputImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

		Graphics graphics = null;
		try
		{
			graphics = outputImage.getGraphics();
			graphics.drawImage(scaledImage, 0, 0, null);
		}
		finally
		{
			if (graphics != null)
			{
				graphics.dispose();
			}
		}

		final ByteArrayOutputStream tempOutputStream = new ByteArrayOutputStream();
		ImageIO.write(outputImage, "jpg", tempOutputStream);
		final byte[] outBytes = tempOutputStream.toByteArray();
		LOGGER.info("Resized image from " + file.getSize() + " to " + outBytes.length);
		return new ByteArrayInputStream(outBytes);
	}

	@Override
	public PK deleteMedia(final String sid)
	{
		final CxAiMediaModel media = getMedia(sid);
		if (media != null)
		{
			modelService.remove(media.getPk());
			this.notifyImage(sid, null);
			return media.getPk();
		}

		return null;
	}

}
