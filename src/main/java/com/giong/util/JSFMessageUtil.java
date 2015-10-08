package com.giong.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFMessageUtil {
	
	public static final String INFO = "Information";
	public static final String WARN = "Warning";
	public static final String ERROR = "Error";
	public static final String FATAL = "Fatal Error";
	
	public static void sendInfoMessageToUser(String message) {
		final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, JSFMessageUtil.INFO, message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static void sendWarningMessageToUser(String message) {
		final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, JSFMessageUtil.WARN, message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static void sendErrorMessageToUser(String message) {
		final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JSFMessageUtil.ERROR, message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static void sendFatalMessageToUser(String message) {
		final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, JSFMessageUtil.FATAL, message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
