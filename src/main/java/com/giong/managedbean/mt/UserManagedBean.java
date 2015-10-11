package com.giong.managedbean.mt;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.giong.managedbean.AbtractManagedBean;
import com.giong.model.mt.MtEmployee;
import com.giong.model.mt.MtUser;

@ManagedBean(name = "userManagedBean")
@SessionScoped
public class UserManagedBean extends AbtractManagedBean {
	
	private static final long serialVersionUID = 1L;
	
	private MtUser currentUser;
	private MtEmployee currentEmployee;
	
	
	public MtUser getCurrentUser() {
		this.currentUser = (MtUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		return this.currentUser;
	}
	
	public MtEmployee getCurrentEmployee() {
		this.currentEmployee = this.currentUser == null ? null : this.currentUser.getMtEmployee();
		return this.currentEmployee;
	}
	
}
