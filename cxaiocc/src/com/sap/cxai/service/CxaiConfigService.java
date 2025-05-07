/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.cxai.service;

import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;

import java.util.Optional;

import com.sap.cxai.CxaiConfigData;


/**
 *
 */
public interface CxaiConfigService
{
	/**
	 * Return active config for current site
	 */
	Optional<CxaiConfigData> getConfigForCurrentSite();

	/**
	 * Return config with specific code, regardless of active flag
	 */
	CxaiConfigData getConfigForCode(final String code) throws ModelNotFoundException;
}
