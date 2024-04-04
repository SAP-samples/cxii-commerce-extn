/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 03-Mar-2024, 10:12:46 am                    ---
 * ----------------------------------------------------------------
 */
package com.sap.cxai.jalo;

import de.hybris.platform.catalog.jalo.CatalogUnawareMedia;
import de.hybris.platform.directpersistence.annotation.SLDSafe;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type CxAiMedia.
 */
@SLDSafe
@SuppressWarnings({"unused","cast"})
public class CxAiMedia extends CatalogUnawareMedia
{
	/** Qualifier of the <code>CxAiMedia.externalId</code> attribute **/
	public static final String EXTERNALID = "externalId";
	/** Qualifier of the <code>CxAiMedia.externalURL</code> attribute **/
	public static final String EXTERNALURL = "externalURL";
	/** Qualifier of the <code>CxAiMedia.externalURLExpiration</code> attribute **/
	public static final String EXTERNALURLEXPIRATION = "externalURLExpiration";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CatalogUnawareMedia.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(EXTERNALID, AttributeMode.INITIAL);
		tmp.put(EXTERNALURL, AttributeMode.INITIAL);
		tmp.put(EXTERNALURLEXPIRATION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxAiMedia.externalId</code> attribute.
	 * @return the externalId - If uploaded to external system, this is external system id
	 */
	public String getExternalId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "externalId".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxAiMedia.externalId</code> attribute.
	 * @return the externalId - If uploaded to external system, this is external system id
	 */
	public String getExternalId()
	{
		return getExternalId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxAiMedia.externalId</code> attribute. 
	 * @param value the externalId - If uploaded to external system, this is external system id
	 */
	public void setExternalId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "externalId".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxAiMedia.externalId</code> attribute. 
	 * @param value the externalId - If uploaded to external system, this is external system id
	 */
	public void setExternalId(final String value)
	{
		setExternalId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxAiMedia.externalURL</code> attribute.
	 * @return the externalURL
	 */
	public String getExternalURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "externalURL".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxAiMedia.externalURL</code> attribute.
	 * @return the externalURL
	 */
	public String getExternalURL()
	{
		return getExternalURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxAiMedia.externalURL</code> attribute. 
	 * @param value the externalURL
	 */
	public void setExternalURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "externalURL".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxAiMedia.externalURL</code> attribute. 
	 * @param value the externalURL
	 */
	public void setExternalURL(final String value)
	{
		setExternalURL( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxAiMedia.externalURLExpiration</code> attribute.
	 * @return the externalURLExpiration
	 */
	public Date getExternalURLExpiration(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, "externalURLExpiration".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxAiMedia.externalURLExpiration</code> attribute.
	 * @return the externalURLExpiration
	 */
	public Date getExternalURLExpiration()
	{
		return getExternalURLExpiration( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxAiMedia.externalURLExpiration</code> attribute. 
	 * @param value the externalURLExpiration
	 */
	public void setExternalURLExpiration(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, "externalURLExpiration".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxAiMedia.externalURLExpiration</code> attribute. 
	 * @param value the externalURLExpiration
	 */
	public void setExternalURLExpiration(final Date value)
	{
		setExternalURLExpiration( getSession().getSessionContext(), value );
	}
	
}
