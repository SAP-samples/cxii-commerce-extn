/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.cxai.service;

import de.hybris.platform.core.PK;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.sap.cxai.model.CxAiMediaModel;


/**
 * Manage uploaded image and events
 */
public interface CxAiImageService
{
	void notifyImage(final String sid, final CxAiMediaModel media);

	SseEmitter getSseEmitter(final String sid);

	CxAiMediaModel getMedia(final String mediaId);

	PK deleteMedia(final String sid);

	CxAiMediaModel uploadMedia(final String sid, final MultipartFile file) throws IOException;

}
