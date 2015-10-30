package com.giong.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {
	
	public static void sendInfoMessage(String summary, String detail) {
		final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static void sendWarningMessage(String summary, String detail) {
		final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static void sendErrorMessage(String summary, String detail) {
		final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static void sendFatalMessage(String summary, String detail) {
		final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
}
