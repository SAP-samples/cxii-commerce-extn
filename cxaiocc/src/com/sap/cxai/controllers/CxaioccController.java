/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.cxai.controllers;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.webservicescommons.mapping.DataMapper;
import de.hybris.platform.webservicescommons.mapping.FieldSetLevelHelper;
import de.hybris.platform.webservicescommons.swagger.ApiFieldsParam;

import java.util.Optional;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sap.cxai.CxaiConfigData;
import com.sap.cxai.CxaiConfigWsDTO;
import com.sap.cxai.service.CxaiConfigService;


@Controller
@RequestMapping("/{baseSiteId}/cxai")
public class CxaioccController
{
	private static final Logger LOGGER = Logger.getLogger(CxaioccController.class);
	protected static final String DEFAULT_FIELD_SET = FieldSetLevelHelper.DEFAULT_LEVEL;

	@Resource(name = "cxaiConfigService")
	private CxaiConfigService cxaiConfigService;
	@Resource(name = "configurationService")
	private ConfigurationService configurationService;
	@Resource(name = "dataMapper")
	private DataMapper dataMapper;

	@GetMapping(
	{ "/config" })
	public ResponseEntity<CxaiConfigWsDTO> getConfig(@ApiFieldsParam
	@RequestParam(defaultValue = DEFAULT_FIELD_SET)
	final String fields)
	{
		final Optional<CxaiConfigData> config = cxaiConfigService.getConfigForCurrentSite();

		if (config.isPresent())
		{
			final var occConfig = dataMapper.map(config.get(), CxaiConfigWsDTO.class, fields);
			return ResponseEntity.ok(cleanCredentials(occConfig));
		}

		LOGGER.warn("No CXAI configuration found");
		return ResponseEntity.notFound().build();
	}

	@GetMapping(
	{ "/config/{code}" })
	public ResponseEntity<CxaiConfigWsDTO> getConfigForCode(@PathVariable
	final String code, @ApiFieldsParam
	@RequestParam(defaultValue = DEFAULT_FIELD_SET)
	final String fields)
	{
		try
		{
			final var config = cxaiConfigService.getConfigForCode(code);
			if (!config.isActive())
			{
				throw new ModelNotFoundException(code);
			}

			final var occConfig = dataMapper.map(config, CxaiConfigWsDTO.class, fields);

			return ResponseEntity.ok(cleanCredentials(occConfig));
		}
		catch (final ModelNotFoundException ex)
		{
			return ResponseEntity.notFound().build();
		}

	}

	protected CxaiConfigWsDTO cleanCredentials(final CxaiConfigWsDTO config)
	{
		final boolean disableCred = configurationService.getConfiguration().getBoolean("cxai.config.disableReturnCredentials",
				true);
		if (disableCred)
		{
			config.setClientSecret(null);
		}

		return config;
	}
}
