package com.giong.managedbean;

import java.io.Serializable;

import org.primefaces.context.RequestContext;

public abstract class AbtractManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";
	
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
}
