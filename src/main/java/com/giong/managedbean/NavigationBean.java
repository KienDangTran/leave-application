package com.giong.managedbean;

import javax.faces.bean.ManagedBean;

import org.springframework.util.StringUtils;

@ManagedBean(name = "navigationBean")
public class NavigationBean extends AbtractManagedBean {
	
	private static final long serialVersionUID = 1L;
	private String pathToPage = "/pages/dashboard.xhtml";
	private String pageTitle;
	
	
	/*
	 ***************************************	GETTER & SETTER	***************************************	
	 */
	
	public String getPageTitle() {
		if (StringUtils.isEmpty(this.pathToPage)) return "";
		this.pageTitle = StringUtils.getFilename(this.pathToPage);
		this.pageTitle = StringUtils.stripFilenameExtension(this.pageTitle);
		this.pageTitle = StringUtils.replace(this.pageTitle, "_", " ");
		this.pageTitle = StringUtils.capitalize(this.pageTitle);
		this.pageTitle = StringUtils.trimWhitespace(this.pageTitle);
		return this.pageTitle;
	}
	
	public String getPathToPage() {
		return this.pathToPage;
	}
	
	public void setPathToPage(String pathToPage) {
		this.pathToPage = pathToPage;
	}
	
}
