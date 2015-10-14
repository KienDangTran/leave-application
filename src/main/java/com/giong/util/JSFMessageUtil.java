package com.giong.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.util.StringUtils;

public class JSFMessageUtil {
	
	
	private static final String RESOURCE_BUNDLE_BASE_NAME = "i18n.i18n";
	
	public static void sendInfoMessageToUser(String summary, String detail) {
		final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static void sendWarningMessageToUser(String summary, String detail) {
		final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static void sendErrorMessageToUser(String summary, String detail) {
		final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static void sendFatalMessageToUser(String summary, String detail) {
		final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static String getResource(String key, Object... params) {
		if (StringUtils.isEmpty(key)) return "";
		final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		final ResourceBundle bundle = ResourceBundle.getBundle(JSFMessageUtil.RESOURCE_BUNDLE_BASE_NAME, locale);
		String value;
		try {
			value = bundle.getString(key);
		}
		catch (final MissingResourceException e) {
			value = "?? Resource key '" + key + "' not found ??";
		}
		
		if (params != null && params.length > 0) {
			final MessageFormat mf = new MessageFormat(value, locale);
			value = mf.format(params, new StringBuffer(), null).toString();
		}
		return StringUtils.isEmpty(value) ? key : value;
	}
}
