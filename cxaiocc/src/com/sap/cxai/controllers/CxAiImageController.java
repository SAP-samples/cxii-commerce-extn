package com.sap.cxai.controllers;

import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.commercewebservicescommons.dto.product.ImageWsDTO;

import java.util.Collections;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sap.cxai.service.CxAiImageService;


@Controller
@RequestMapping("/{baseSiteId}/cxai/image")
public class CxAiImageController
{
	private static final Logger LOGGER = Logger.getLogger(CxAiImageController.class);

	@Resource(name = "cxaiImageService")
	private CxAiImageService cxaiImageService;


	@PostMapping("/upload/{sid}")
	public ResponseEntity<Map<?, ?>> uploadImage(@RequestParam
	final MultipartFile file, @PathVariable
	final String sid) throws InterruptedException
	{

		Thread.sleep(5000); //simulate slow upload (for demo purposes)
		try
		{
			final CatalogUnawareMediaModel media = cxaiImageService.uploadMedia(sid, file);
			return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("PK", media.getPk().toString()));
		}
		catch (final Exception ex)
		{
			LOGGER.error("Error uploading file", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonMap("result", ex.getClass().getSimpleName()));
		}
	}

	@GetMapping("/{sid}")
	public ResponseEntity<ImageWsDTO> getImage(@PathVariable
	final String sid)
	{

		final CatalogUnawareMediaModel media = cxaiImageService.getMedia(sid);
		if (media == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ImageWsDTO());
		}

		final ImageWsDTO img = new ImageWsDTO();
		img.setUrl(media.getURL());

		//we store updatedTime in altText for convenience, this can be used by polling
		final long updatedTime = media.getModifiedtime() == null ? media.getCreationtime().getTime()
				: media.getModifiedtime().getTime();
		img.setAltText("" + updatedTime);
		return ResponseEntity.ok().body(img);
	}

	@DeleteMapping("/{sid}")
	public ResponseEntity<Map<String, String>> deleteImage(@PathVariable
	final String sid)
	{
		final var pk = cxaiImageService.deleteMedia(sid);
		return ResponseEntity.ok().body(Collections.singletonMap("PK", pk == null ? null : pk.toString()));
	}
}
