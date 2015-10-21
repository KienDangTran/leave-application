package com.giong.managedbean.mt;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.giong.managedbean.AbtractManagedBean;
import com.giong.model.mt.MtEmployee;
import com.giong.model.mt.MtUser;
import com.giong.service.interfaces.mt.IUserService;

@ManagedBean(name = "userManagedBean")
@SessionScoped
public class UserManagedBean extends AbtractManagedBean {
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{userService}")
	IUserService userService;
	
	private MtUser currentUser;
	private MtEmployee currentEmployee;
	
	
	@PostConstruct
	public void init() {
		final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user != null) {
			this.currentUser = this.userService.getUserByUsername(user.getUsername());
		}
	}
	
	public IUserService getUserService() {
		return this.userService;
	}
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	public MtUser getCurrentUser() {
		return this.currentUser;
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
	
	public void editUser() {
	
	}
	
}
