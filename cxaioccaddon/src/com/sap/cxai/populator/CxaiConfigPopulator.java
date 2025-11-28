/**
 *
 */
package com.sap.cxai.populator;

import de.hybris.platform.apiregistryservices.model.AbstractCredentialModel;
import de.hybris.platform.apiregistryservices.model.ConsumedDestinationModel;
import de.hybris.platform.apiregistryservices.model.ConsumedOAuthCredentialModel;
import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.site.BaseSiteService;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.sap.cxai.CxaiConfigData;
import com.sap.cxai.model.CxaiConfigModel;


/**
 *
 */
public class CxaiConfigPopulator implements Populator<CxaiConfigModel, CxaiConfigData>
{
	static final Logger LOGGER = Logger.getLogger(CxaiConfigPopulator.class);

	private final FlexibleSearchService flexibleSearchService;
	private final BaseSiteService baseSiteService;
	private final CatalogService catalogService;

	/**
	 * @param flexibleSearchService
	 */
	public CxaiConfigPopulator(final FlexibleSearchService flexibleSearchService, final CatalogService catalogService,
			final BaseSiteService baseSiteService)
	{
		super();
		this.flexibleSearchService = flexibleSearchService;
		this.baseSiteService = baseSiteService;
		this.catalogService = catalogService;
	}


	@Override
	public void populate(final CxaiConfigModel source, final CxaiConfigData target) throws ConversionException
	{
		target.setActive(source.isActive());
		target.setCode(source.getCode());
		this.populateCatalogData(source, target);

		final ConsumedDestinationModel consumedDestination = source.getConsumedDestination();
		if (consumedDestination != null)
		{
			final String tenantUrl = consumedDestination.getUrl();
			target.setTenantUrl(tenantUrl);

			final AbstractCredentialModel abstractCredential = consumedDestination.getCredential();

			if (abstractCredential instanceof ConsumedOAuthCredentialModel)
			{
				final ConsumedOAuthCredentialModel credential = (ConsumedOAuthCredentialModel) abstractCredential;

				final String authUrl = credential.getOAuthUrl();
				final String clientId = credential.getClientId();
				final String clientSecret = credential.getClientSecret();

				target.setAuthUrl(authUrl);
				target.setClientId(clientId);
				target.setClientSecret(clientSecret);
			}
			else
			{
				LOGGER.warn(
						"Invalid CXAI config - invalid credential type, should be ConsumedOAuthCredentialModel: " + abstractCredential);
			}
		}
		else if (source.getConsumedDestinationId() != null)
		{
			LOGGER.warn("Invalid CXAI config - can't find consumed destination " + source.getConsumedDestinationId());
		}
	}

	protected void populateCatalogData(final CxaiConfigModel source, final CxaiConfigData target)
	{
		CatalogModel catalog = null;

		if (StringUtils.isNotBlank(source.getCustomCatalogId()))
		{
			target.setCatalogId(source.getCustomCatalogId());
		}
		else
		{
			catalog = this.getDefaultCatalog();
			target.setCatalogId(catalog == null ? null : catalog.getId());
		}

		if (StringUtils.isNotBlank(source.getCustomCatalogVersion()))
		{
			target.setCatalogVersion(source.getCustomCatalogVersion());
		}
		else
		{
			target.setCatalogVersion(getActiveCatalogVersion(catalog, target.getCatalogId()));
		}
	}

	/**
	 * @param catalogId
	 * @return
	 */
	private String getActiveCatalogVersion(CatalogModel catalog, final String catalogId)
	{
		if (catalog == null && catalogId == null)
		{
			return null;
		}

		try
		{
			if (catalog == null)
			{
				catalog = catalogService.getCatalogForId(catalogId);
			}

			if (catalog.getActiveCatalogVersion() != null)
			{
				return catalog.getActiveCatalogVersion().getVersion();
			}

			LOGGER.warn("Catalog " + catalogId + " has no active version, falling back to first catalog version");
			if (catalog.getCatalogVersions().size() > 0)
			{
				return IterableUtils.get(catalog.getCatalogVersions(), 0).getVersion();
			}
			LOGGER.warn("Catalog " + catalogId + " doesn't have any versions, cant determine catalog version for configuration");
		}
		catch (final UnknownIdentifierException ex)
		{
			LOGGER.warn("Catalog " + catalogId + " doesn't exist in the system, cant determine catalog version for configuration",
					ex);
		}

		return null;
	}


	protected CatalogModel getDefaultCatalog()
	{
		try
		{
			final var site = baseSiteService.getCurrentBaseSite();
			if (site != null)
			{
				final var defaultProductCatalog = site instanceof CMSSiteModel ? ((CMSSiteModel) site).getDefaultCatalog() : null;
				return defaultProductCatalog != null ? defaultProductCatalog : site.getStores().get(0).getCatalogs().get(0);
			}
			else
			{
				LOGGER.debug("No current basesite - can't determine default catalog");
				return null;
			}
		}
		catch (final ArrayIndexOutOfBoundsException ex)
		{
			LOGGER.warn("Can't determine default catalog for site " + baseSiteService.getCurrentBaseSite().getUid()
					+ " - fix site config or set customCatalogId in configuration", ex);
			return null;
		}
	}
}
