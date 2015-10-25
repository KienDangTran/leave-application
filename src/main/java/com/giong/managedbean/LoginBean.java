package com.giong.managedbean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;

import com.giong.service.interfaces.mt.IUserDetailsService;
import com.giong.util.JSFMessageUtil;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean extends AbtractManagedBean {
	
	private static final long serialVersionUID = 1L;
	
	
	public static final String LOGIN_FAILURE = "loginFailure";
	public static final String LOGIN_SUCCESS = "loginSuccess";
	public static final String LOGGED_OUT = "loggedOUT";
	
	private String username = null;
	private String password = null;
	private String rememberMe = null;
	
	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager = null;
	
	@ManagedProperty(value = "#{rememberMeServices}")
	private RememberMeServices rememberMeServices;
	
	@ManagedProperty(value = "#{userDetailsService}")
	private IUserDetailsService userDetailsService;
	
	/*
	 ***************************************	ACTIONS		***************************************	
	 */
	public String doLogin() throws IOException, ServletException {
		try {
			final Authentication authenticationDetails = new UsernamePasswordAuthenticationToken(this.getUsername(), this.getPassword());
			final Authentication authenticationResult = this.authenticationManager.authenticate(authenticationDetails);
			SecurityContextHolder.getContext().setAuthentication(authenticationResult);
			
			final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			final HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			final HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request);
			this.rememberMeServices.loginSuccess(wrapper, response, authenticationResult);
			
			return LoginBean.LOGIN_SUCCESS;
		}
		catch (LockedException | DisabledException e) {
			e.printStackTrace();
			JSFMessageUtil.sendErrorMessageToUser(JSFMessageUtil.getResource("error.user_acc_has_been_suspended", this.getUsername()), "");
			return LoginBean.LOGIN_FAILURE;
		}
		catch (final BadCredentialsException e) {
			e.printStackTrace();
			JSFMessageUtil.sendErrorMessageToUser(JSFMessageUtil.getResource("error.user_pass_is_invalid"), "");
			return LoginBean.LOGIN_FAILURE;
		}
		catch (final AuthenticationException e) {
			e.printStackTrace();
			JSFMessageUtil.sendFatalMessageToUser("Authentication fails", "");
			return LoginBean.LOGIN_FAILURE;
		}
	}
	
	
	/*
	 ***************************************	GETTER & SETTER	***************************************	
	 */
	public AuthenticationManager getAuthenticationManager() {
		return this.authenticationManager;
	}
	
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	public RememberMeServices getRememberMeServices() {
		return this.rememberMeServices;
	}
	
	public void setRememberMeServices(RememberMeServices rememberMeServices) {
		this.rememberMeServices = rememberMeServices;
	}
	
	public IUserDetailsService getUserDetailsService() {
		return this.userDetailsService;
	}
	
	public void setUserDetailsService(IUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
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
	
	
}
