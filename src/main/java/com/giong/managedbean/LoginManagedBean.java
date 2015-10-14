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
	public String login() throws IOException {
		try {
			final Authentication request = new UsernamePasswordAuthenticationToken(this.getUsername(), this.getPassword());
			final Authentication result = this.authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			return LoginManagedBean.LOGIN_SUCCESS;
		}
		catch (final LockedException e) {
			e.printStackTrace();
			JSFMessageUtil.sendErrorMessageToUser(JSFMessageUtil.getResource("error.user_acc_has_been_suspended", this.getUsername()), "");
			return LoginManagedBean.LOGIN_FAIL;
		}
		catch (final AuthenticationException e) {
			e.printStackTrace();
			JSFMessageUtil.sendErrorMessageToUser(JSFMessageUtil.getResource("error.user_pass_is_invalid"), "");
			return LoginManagedBean.LOGIN_FAIL;
		}
	}
	
	public String logout() {
		SecurityContextHolder.clearContext();
		return LoginManagedBean.LOGGEDOUT;
	}
	
}
