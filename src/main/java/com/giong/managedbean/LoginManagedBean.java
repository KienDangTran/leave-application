package com.giong.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.giong.model.mt.MtUser;
import com.giong.service.interfaces.mt.IUserService;

@ManagedBean(name = "loginManagedBean")
@RequestScoped
public class LoginManagedBean extends AbtractManagedBean {
	
	private static final long serialVersionUID = 1L;
	
	public static final String LOGIN_SUCCESS = "success";
	public static final String LOGIN_FAIL = "fail";
	public static final String LOGGEDOUT = "loggedout";
	
	private String username = null;
	private String password = null;
	
	@ManagedProperty(value = "#{authenticationManager}")
	AuthenticationManager authenticationManager = null;
	
	@ManagedProperty(value = "#{userService}")
	IUserService userService;
	
	
	/*
	 ***************************************	GETTER & SETTER	***************************************	
	 */
	public AuthenticationManager getAuthenticationManager() {
		return this.authenticationManager;
	}
	
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	public IUserService getUserService() {
		return this.userService;
	}
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/*
	 ***************************************	ACTIONS		***************************************	
	 */
	public String login() {
		try {
			final Authentication request = new UsernamePasswordAuthenticationToken(this.getUsername(), this.getPassword());
			final Authentication result = this.authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			final MtUser user = this.userService.getUserByUsername(this.username);
			if (user != null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
			}
			return LoginManagedBean.LOGIN_SUCCESS;
		}
		catch (final AuthenticationException e) {
			e.printStackTrace();
			this.displayErrorMessageToUser("Check your email/password");
			return LoginManagedBean.LOGIN_FAIL;
		}
	}
	
	public String logout() {
		SecurityContextHolder.clearContext();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		return LoginManagedBean.LOGGEDOUT;
	}
	
}
