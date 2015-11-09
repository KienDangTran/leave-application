package com.giong.managedbean.mt;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.context.SecurityContextHolder;

import com.giong.dao.interfaces.mt.IUserDAO;
import com.giong.managedbean.AbtractManagedBean;
import com.giong.model.mt.MtEmployee;
import com.giong.model.mt.MtUser;

@ManagedBean(name = "userSessionBean")
@SessionScoped
public class UserSessionBean extends AbtractManagedBean {
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{userDAO}")
	IUserDAO userDAO;
	
	private MtUser currentUser;
	private MtEmployee currentEmployee;
	
	@PostConstruct
	public void init() {
		this.currentUser = (MtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public void changeTheme(String themeName) {
		this.currentUser.setTheme(themeName);
		this.getUserDAO().update(this.getCurrentUser());
	}
	
	/*
	 ***************************************	GETTER & SETTER	***************************************	
	 */
	
	public MtUser getCurrentUser() {
		return this.currentUser;
	}
	
	public IUserDAO getUserDAO() {
		return this.userDAO;
	}
	
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public MtEmployee getCurrentEmployee() {
		this.currentEmployee = this.currentUser == null ? null : this.currentUser.getMtEmployee();
		return this.currentEmployee;
	}
}
