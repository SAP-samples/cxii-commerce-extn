/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 03-Mar-2024, 10:12:46 am                    ---
 * ----------------------------------------------------------------
 */
package com.sap.cxai.jalo;

import de.hybris.platform.basecommerce.constants.BasecommerceConstants;
import de.hybris.platform.basecommerce.jalo.site.BaseSite;
import de.hybris.platform.directpersistence.annotation.SLDSafe;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Generated class for type CxaiConfig.
 */
@SLDSafe
@SuppressWarnings({"unused","cast"})
public class CxaiConfig extends GenericItem
{
	/** Qualifier of the <code>CxaiConfig.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>CxaiConfig.consumedDestinationId</code> attribute **/
	public static final String CONSUMEDDESTINATIONID = "consumedDestinationId";
	/** Qualifier of the <code>CxaiConfig.active</code> attribute **/
	public static final String ACTIVE = "active";
	/** Qualifier of the <code>CxaiConfig.customCatalogId</code> attribute **/
	public static final String CUSTOMCATALOGID = "customCatalogId";
	/** Qualifier of the <code>CxaiConfig.customCatalogVersion</code> attribute **/
	public static final String CUSTOMCATALOGVERSION = "customCatalogVersion";
	/** Qualifier of the <code>CxaiConfig.baseSites</code> attribute **/
	public static final String BASESITES = "baseSites";
	/**
	* {@link OneToManyHandler} for handling 1:n BASESITES's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<BaseSite> BASESITESHANDLER = new OneToManyHandler<BaseSite>(
	BasecommerceConstants.TC.BASESITE,
	false,
	"cxaiConfig",
	null,
	false,
	true,
	CollectionType.SET
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(CONSUMEDDESTINATIONID, AttributeMode.INITIAL);
		tmp.put(ACTIVE, AttributeMode.INITIAL);
		tmp.put(CUSTOMCATALOGID, AttributeMode.INITIAL);
		tmp.put(CUSTOMCATALOGVERSION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxaiConfig.active</code> attribute.
	 * @return the active
	 */
	public Boolean isActive(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, "active".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxaiConfig.active</code> attribute.
	 * @return the active
	 */
	public Boolean isActive()
	{
		return isActive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxaiConfig.active</code> attribute. 
	 * @return the active
	 */
	public boolean isActiveAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isActive( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxaiConfig.active</code> attribute. 
	 * @return the active
	 */
	public boolean isActiveAsPrimitive()
	{
		return isActiveAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxaiConfig.active</code> attribute. 
	 * @param value the active
	 */
	public void setActive(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, "active".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxaiConfig.active</code> attribute. 
	 * @param value the active
	 */
	public void setActive(final Boolean value)
	{
		setActive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxaiConfig.active</code> attribute. 
	 * @param value the active
	 */
	public void setActive(final SessionContext ctx, final boolean value)
	{
		setActive( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxaiConfig.active</code> attribute. 
	 * @param value the active
	 */
	public void setActive(final boolean value)
	{
		setActive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxaiConfig.baseSites</code> attribute.
	 * @return the baseSites
	 */
	public Set<BaseSite> getBaseSites(final SessionContext ctx)
	{
		return (Set<BaseSite>)BASESITESHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxaiConfig.baseSites</code> attribute.
	 * @return the baseSites
	 */
	public Set<BaseSite> getBaseSites()
	{
		return getBaseSites( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxaiConfig.baseSites</code> attribute. 
	 * @param value the baseSites
	 */
	public void setBaseSites(final SessionContext ctx, final Set<BaseSite> value)
	{
		BASESITESHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxaiConfig.baseSites</code> attribute. 
	 * @param value the baseSites
	 */
	public void setBaseSites(final Set<BaseSite> value)
	{
		setBaseSites( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to baseSites. 
	 * @param value the item to add to baseSites
	 */
	public void addToBaseSites(final SessionContext ctx, final BaseSite value)
	{
		BASESITESHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to baseSites. 
	 * @param value the item to add to baseSites
	 */
	public void addToBaseSites(final BaseSite value)
	{
		addToBaseSites( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from baseSites. 
	 * @param value the item to remove from baseSites
	 */
	public void removeFromBaseSites(final SessionContext ctx, final BaseSite value)
	{
		BASESITESHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from baseSites. 
	 * @param value the item to remove from baseSites
	 */
	public void removeFromBaseSites(final BaseSite value)
	{
		removeFromBaseSites( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxaiConfig.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "code".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxaiConfig.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxaiConfig.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "code".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxaiConfig.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxaiConfig.consumedDestinationId</code> attribute.
	 * @return the consumedDestinationId
	 */
	public String getConsumedDestinationId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "consumedDestinationId".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxaiConfig.consumedDestinationId</code> attribute.
	 * @return the consumedDestinationId
	 */
	public String getConsumedDestinationId()
	{
		return getConsumedDestinationId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxaiConfig.consumedDestinationId</code> attribute. 
	 * @param value the consumedDestinationId
	 */
	public void setConsumedDestinationId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "consumedDestinationId".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxaiConfig.consumedDestinationId</code> attribute. 
	 * @param value the consumedDestinationId
	 */
	public void setConsumedDestinationId(final String value)
	{
		setConsumedDestinationId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxaiConfig.customCatalogId</code> attribute.
	 * @return the customCatalogId - Custom catalogId, if not using default (first) product catalog connected to BaseSite
	 */
	public String getCustomCatalogId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "customCatalogId".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxaiConfig.customCatalogId</code> attribute.
	 * @return the customCatalogId - Custom catalogId, if not using default (first) product catalog connected to BaseSite
	 */
	public String getCustomCatalogId()
	{
		return getCustomCatalogId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxaiConfig.customCatalogId</code> attribute. 
	 * @param value the customCatalogId - Custom catalogId, if not using default (first) product catalog connected to BaseSite
	 */
	public void setCustomCatalogId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "customCatalogId".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxaiConfig.customCatalogId</code> attribute. 
	 * @param value the customCatalogId - Custom catalogId, if not using default (first) product catalog connected to BaseSite
	 */
	public void setCustomCatalogId(final String value)
	{
		setCustomCatalogId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxaiConfig.customCatalogVersion</code> attribute.
	 * @return the customCatalogVersion - Custom catalogVersion, if not using active catalog for catalogId
	 */
	public String getCustomCatalogVersion(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "customCatalogVersion".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxaiConfig.customCatalogVersion</code> attribute.
	 * @return the customCatalogVersion - Custom catalogVersion, if not using active catalog for catalogId
	 */
	public String getCustomCatalogVersion()
	{
		return getCustomCatalogVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxaiConfig.customCatalogVersion</code> attribute. 
	 * @param value the customCatalogVersion - Custom catalogVersion, if not using active catalog for catalogId
	 */
	public void setCustomCatalogVersion(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "customCatalogVersion".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxaiConfig.customCatalogVersion</code> attribute. 
	 * @param value the customCatalogVersion - Custom catalogVersion, if not using active catalog for catalogId
	 */
	public void setCustomCatalogVersion(final String value)
	{
		setCustomCatalogVersion( getSession().getSessionContext(), value );
	}
	
}
