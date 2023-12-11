/**
 *
 */
package com.sap.cxai.populator;

import de.hybris.platform.apiregistryservices.model.AbstractCredentialModel;
import de.hybris.platform.apiregistryservices.model.ConsumedDestinationModel;
import de.hybris.platform.apiregistryservices.model.ConsumedOAuthCredentialModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

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

	/**
	 * @param flexibleSearchService
	 */
	public CxaiConfigPopulator(final FlexibleSearchService flexibleSearchService)
	{
		super();
		this.flexibleSearchService = flexibleSearchService;
	}


	@Override
	public void populate(final CxaiConfigModel source, final CxaiConfigData target) throws ConversionException
	{
		target.setActive(source.isActive());
		target.setCode(source.getCode());
		target.setCustomCatalogId(source.getCustomCatalogId());

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
		else
		{
			LOGGER.warn("Invalid CXAI config - can't find consumed destination " + source.getConsumedDestinationId());
		}
	}

}
