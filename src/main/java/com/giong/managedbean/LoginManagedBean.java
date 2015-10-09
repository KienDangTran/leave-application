package com.giong.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean(name = "loginManagedBean")
@RequestScoped
public class LoginManagedBean {
	
	public static final String LOGIN_SUCCESS = "success";
	public static final String LOGIN_FAIL = "fail";
	public static final String LOGGEDOUT = "loggedout";
	
	private String username = null;
	private String password = null;
	
	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager = null;
	
	
	/*
	 ***************************************	GETTER & SETTER	***************************************	
	 */
	public AuthenticationManager getAuthenticationManager() {
		return this.authenticationManager;
	}
	
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
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
		}
		catch (final AuthenticationException e) {
			e.printStackTrace();
			return LoginManagedBean.LOGIN_FAIL;
		}
		return LoginManagedBean.LOGIN_SUCCESS;
	}
	
	public String cancel() {
		return null;
	}
	
	public String logout() {
		SecurityContextHolder.clearContext();
		return LOGGEDOUT;
	}
	
}
