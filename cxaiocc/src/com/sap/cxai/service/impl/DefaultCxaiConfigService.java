/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.cxai.service.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.site.BaseSiteService;

import java.util.Optional;

import org.apache.log4j.Logger;

import com.sap.cxai.CxaiConfigData;
import com.sap.cxai.model.CxaiConfigModel;
import com.sap.cxai.service.CxaiConfigService;


/**
 *
 */
public class DefaultCxaiConfigService implements CxaiConfigService
{
	private static final Logger LOGGER = Logger.getLogger(DefaultCxaiConfigService.class);

	private final FlexibleSearchService flexibleSearchService;
	private final BaseSiteService baseSiteService;

	private final Converter<CxaiConfigModel, CxaiConfigData> cxaiConfigConverter;

	public DefaultCxaiConfigService(final FlexibleSearchService flexibleSearchService, final BaseSiteService baseSiteService,
			final Converter<CxaiConfigModel, CxaiConfigData> cxaiConfigConverter)
	{
		super();
		this.flexibleSearchService = flexibleSearchService;
		this.baseSiteService = baseSiteService;
		this.cxaiConfigConverter = cxaiConfigConverter;
	}

	@Override
	public Optional<CxaiConfigData> getConfigForCurrentSite()
	{
		final CxaiConfigModel example = new CxaiConfigModel();
		example.setActive(true);
		fillExtraConfigFilters(example);

		final BaseSiteModel baseSite = baseSiteService.getCurrentBaseSite();

		final Optional<CxaiConfigModel> config = flexibleSearchService.getModelsByExample(example).stream() //
				.filter(c -> c.getBaseSites().contains(baseSite)) //
				.findFirst();

		if (config.isPresent())
		{
			return Optional.of(cxaiConfigConverter.convert(config.get()));
		}

		return Optional.empty();
	}

	protected void fillExtraConfigFilters(final CxaiConfigModel example)
	{
		// hook to allow setting extra filters for config search (e.g. apiVersion etc.)
	}

	@Override
	public CxaiConfigData getConfigForCode(final String code) throws ModelNotFoundException
	{
		final CxaiConfigModel example = new CxaiConfigModel();
		example.setActive(true);
		example.setCode(code);

		final CxaiConfigModel config = flexibleSearchService.getModelByExample(example);
		return cxaiConfigConverter.convert(config);
	}

}
