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
	Optional<CxaiConfigData> getConfigForCurrentSite();

	CxaiConfigData getConfigForCode(final String code) throws ModelNotFoundException;
}
