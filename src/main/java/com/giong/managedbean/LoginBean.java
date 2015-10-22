package com.giong.managedbean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.giong.service.interfaces.mt.IUserService;
import com.giong.util.JSFMessageUtil;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean extends AbtractManagedBean {
	
	private static final long serialVersionUID = 1L;
	
	public static final String HOME_URL = "/faces/home.xhtml";
	public static final String LOGIN_URL = "/login.xhtml";
	
	private String username = null;
	private String password = null;
	private String rememberMe = null;
	
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
	
	public String getRememberMe() {
		return this.rememberMe;
	}
	
	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	/*
	 ***************************************	ACTIONS		***************************************	
	 */
	public String login() throws IOException {
		try {
			final Authentication request = new UsernamePasswordAuthenticationToken(this.getUsername(), this.getPassword());
			final Authentication result = this.authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			return LoginBean.HOME_URL;
		}
		catch (final LockedException e) {
			e.printStackTrace();
			JSFMessageUtil.sendErrorMessageToUser(JSFMessageUtil.getResource("error.user_acc_has_been_suspended", this.getUsername()), "");
			return LoginBean.LOGIN_URL;
		}
		catch (final AuthenticationException e) {
			e.printStackTrace();
			JSFMessageUtil.sendErrorMessageToUser(JSFMessageUtil.getResource("error.user_pass_is_invalid"), "");
			return LoginBean.LOGIN_URL;
		}
	}
	
	public String logout() {
		JSFMessageUtil.sendInfoMessageToUser("Logout successfully.", "");
		SecurityContextHolder.clearContext();
		return LoginBean.LOGIN_URL;
	}
	
}
