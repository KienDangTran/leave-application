package com.giong.managedbean.mt;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.giong.dao.interfaces.mt.IUserDAO;
import com.giong.managedbean.AbtractManagedBean;
import com.giong.model.mt.MtEmployee;
import com.giong.model.mt.MtUser;

@ManagedBean(name = "userManagedBean")
@SessionScoped
public class UserManagedBean extends AbtractManagedBean {
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{userDAO}")
	IUserDAO userDAO;
	
	private MtUser currentUser;
	private MtEmployee currentEmployee;
	
	@PostConstruct
	public void init() {
		final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user != null) {
			this.currentUser = this.userDAO.loadUserByUsername(user.getUsername());
		}
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
	
	@SuppressWarnings("unchecked")
	public String getCurrentUserRoles() {
		final List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		final StringBuilder sb = new StringBuilder();
		for (final SimpleGrantedAuthority simpleGrantedAuthority : authorities) {
			sb.append(simpleGrantedAuthority.getAuthority());
		}
		return sb.toString();
	}
}
