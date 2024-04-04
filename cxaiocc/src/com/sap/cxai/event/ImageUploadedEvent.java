/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.cxai.event;

import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.PublishEventContext;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

import org.apache.log4j.Logger;

/**
 *
 */
public class ImageUploadedEvent extends AbstractEvent implements ClusterAwareEvent {
    private static final Logger LOGGER = Logger.getLogger(ImageUploadedEvent.class);

    private final CatalogUnawareMediaModel media;
    private final String sid;

    public ImageUploadedEvent(final String sid, final CatalogUnawareMediaModel media) {
	super();
	this.sid = sid;
	this.media = media;
    }

    @Override
    public boolean canPublish(final PublishEventContext publishEventContext) {
	if (LOGGER.isTraceEnabled()) {
	    LOGGER.trace("Can publish " + publishEventContext.getSourceNodeId() + " -> "
		    + publishEventContext.getTargetNodeId() + " targetNodeGroups: "
		    + publishEventContext.getTargetNodeGroups());
	}
	return true;
    }

    public CatalogUnawareMediaModel getMedia() {
	return media;
    }

    public String getSid() {
	return sid;
    }

}
