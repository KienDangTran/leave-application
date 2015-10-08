package com.giong.managedbean;

import java.io.Serializable;

import org.primefaces.context.RequestContext;

import com.giong.util.JSFMessageUtil;

public abstract class AbtractManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";
	
	public AbtractManagedBean() {
		super();
	}
	
	protected void displayFatalMessageToUser(String message) {
		JSFMessageUtil.sendFatalMessageToUser(message);
	}
	
	protected void displayErrorMessageToUser(String message) {
		JSFMessageUtil.sendErrorMessageToUser(message);
	}
	
	protected void displayWarningMessageToUser(String message) {
		JSFMessageUtil.sendWarningMessageToUser(message);
	}
	
	protected void displayInfoMessageToUser(String message) {
		JSFMessageUtil.sendInfoMessageToUser(message);
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
}
