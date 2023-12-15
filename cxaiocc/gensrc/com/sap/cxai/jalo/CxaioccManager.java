/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 14-Dec-2023, 8:39:50 pm                     ---
 * ----------------------------------------------------------------
 */
package com.sap.cxai.jalo;

import com.sap.cxai.constants.CxaioccConstants;
import com.sap.cxai.jalo.CxaiConfig;
import de.hybris.platform.basecommerce.jalo.site.BaseSite;
import de.hybris.platform.directpersistence.annotation.SLDSafe;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>CxaioccManager</code>.
 */
@SuppressWarnings({"unused","cast"})
@SLDSafe
public class CxaioccManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("cxaiConfig", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.basecommerce.jalo.site.BaseSite", Collections.unmodifiableMap(tmp));
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public CxaiConfig createCxaiConfig(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType("CxaiConfig");
			return (CxaiConfig)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating CxaiConfig : "+e.getMessage(), 0 );
		}
	}
	
	public CxaiConfig createCxaiConfig(final Map attributeValues)
	{
		return createCxaiConfig( getSession().getSessionContext(), attributeValues );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.cxaiConfig</code> attribute.
	 * @return the cxaiConfig
	 */
	public CxaiConfig getCxaiConfig(final SessionContext ctx, final BaseSite item)
	{
		return (CxaiConfig)item.getProperty( ctx, CxaioccConstants.Attributes.BaseSite.CXAICONFIG);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.cxaiConfig</code> attribute.
	 * @return the cxaiConfig
	 */
	public CxaiConfig getCxaiConfig(final BaseSite item)
	{
		return getCxaiConfig( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseSite.cxaiConfig</code> attribute. 
	 * @param value the cxaiConfig
	 */
	public void setCxaiConfig(final SessionContext ctx, final BaseSite item, final CxaiConfig value)
	{
		item.setProperty(ctx, CxaioccConstants.Attributes.BaseSite.CXAICONFIG,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseSite.cxaiConfig</code> attribute. 
	 * @param value the cxaiConfig
	 */
	public void setCxaiConfig(final BaseSite item, final CxaiConfig value)
	{
		setCxaiConfig( getSession().getSessionContext(), item, value );
	}
	
	public static final CxaioccManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (CxaioccManager) em.getExtension(CxaioccConstants.EXTENSIONNAME);
	}
	
	@Override
	public String getName()
	{
		return CxaioccConstants.EXTENSIONNAME;
	}
	
}
