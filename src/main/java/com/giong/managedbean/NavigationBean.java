package com.giong.managedbean;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "navigationBean")
public class NavigationBean extends AbtractManagedBean {
	
	private static final long serialVersionUID = 1L;
	private String page = "/pages/dashboard.xhtml";
	
	
	
	/*
	 ***************************************	GETTER & SETTER	***************************************	
	 */
	public String getPage() {
		return this.page;
	}
	
	public void setPage(String page) {
		this.page = page;
	}
	
}
