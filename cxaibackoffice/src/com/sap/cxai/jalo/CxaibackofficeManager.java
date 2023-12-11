package com.sap.cxai.jalo;

import com.sap.cxai.constants.CxaibackofficeConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

public class CxaibackofficeManager extends GeneratedCxaibackofficeManager
{
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger( CxaibackofficeManager.class.getName() );
	
	public static final CxaibackofficeManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (CxaibackofficeManager) em.getExtension(CxaibackofficeConstants.EXTENSIONNAME);
	}
	
}
