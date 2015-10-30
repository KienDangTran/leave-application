package com.giong.managedbean;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.util.StringUtils;

public abstract class AbtractManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";
	private static final String RESOURCE_BUNDLE_BASE_NAME = "i18n.i18n";
	
	public AbtractManagedBean() {
		super();
	}
	
	protected void closeDialog() {
		this.getRequestContext().addCallbackParam(AbtractManagedBean.KEEP_DIALOG_OPENED, false);
	}
	
	protected void keepDialogOpen() {
		this.getRequestContext().addCallbackParam(AbtractManagedBean.KEEP_DIALOG_OPENED, true);
	}
	
	protected RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}
	
	public String getLocalizedMessage(String key, Object... params) {
		if (StringUtils.isEmpty(key)) return "";
		final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		final ResourceBundle bundle = ResourceBundle.getBundle(AbtractManagedBean.RESOURCE_BUNDLE_BASE_NAME, locale);
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
