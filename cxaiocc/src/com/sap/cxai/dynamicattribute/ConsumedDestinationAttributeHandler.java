/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.cxai.dynamicattribute;

import de.hybris.platform.apiregistryservices.model.ConsumedDestinationModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import org.apache.commons.lang3.StringUtils;

import com.sap.cxai.model.CxaiConfigModel;


/**
 *
 */
public class ConsumedDestinationAttributeHandler implements DynamicAttributeHandler<ConsumedDestinationModel, CxaiConfigModel>
{

	private final FlexibleSearchService flexibleSearchService;

	public ConsumedDestinationAttributeHandler(final FlexibleSearchService flexibleSearchService)
	{
		super();
		this.flexibleSearchService = flexibleSearchService;
	}

	@Override
	public ConsumedDestinationModel get(final CxaiConfigModel model)
	{
		final String id = model.getConsumedDestinationId();
		if (StringUtils.isEmpty(id))
		{
			return null;
		}

		final ConsumedDestinationModel ex = new ConsumedDestinationModel();
		ex.setId(id);

		final var result = flexibleSearchService.getModelsByExample(ex);

		if (result.size() == 1)
		{
			return result.get(0);
		}
		else if (result.size() > 1)
		{
			throw new IllegalStateException("Too many results for " + id);
		}

		return null;
	}

	@Override
	public void set(final CxaiConfigModel model, final ConsumedDestinationModel consumedDestinationModel)
	{
		if (consumedDestinationModel != null)
		{
			model.setConsumedDestinationId(consumedDestinationModel.getId());
		}
		else
		{
			model.setConsumedDestinationId(null);
		}
	}

}
