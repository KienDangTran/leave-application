package com.giong.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.RememberMeServices;

import com.giong.config.security.SecurityConfig;
import com.giong.service.interfaces.mt.IUserDetailsService;
import com.giong.util.JSFMessageUtil;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean extends AbtractManagedBean {
	
	private static final long serialVersionUID = 1L;
	
	
	public static final String LOGIN_FAILURE = "loginFailure";
	public static final String LOGIN_SUCCESS = "loginSuccess";
	public static final String LOGGED_OUT = "loggedOUT";
	
	private String username;
	private String password;
	private boolean rememberMe;
	
	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager = null;
	
	@ManagedProperty(value = "#{rememberMeServices}")
	private RememberMeServices rememberMeServices;
	
	@ManagedProperty(value = "#{userDetailsService}")
	private IUserDetailsService userDetailsService;
	
	/*
	 ***************************************	ACTIONS		***************************************	
	 */
	public String doLogin() {
		try {
			Authentication authenticationResult = null;
			if (this.rememberMe) {
				final UserDetails userDetails = this.userDetailsService.loadUserByUsername(this.getUsername());
				final RememberMeAuthenticationToken rememberMeAuthenticationToken = new RememberMeAuthenticationToken(SecurityConfig.APPLICATION_SECURITY_KEY, userDetails,
						userDetails.getAuthorities());
				final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				final HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
				this.rememberMeServices.loginSuccess(request, response, rememberMeAuthenticationToken);
				this.rememberMeServices.autoLogin(request, response);
				authenticationResult = rememberMeAuthenticationToken;
			}
			else {
				final Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(this.getUsername(), this.getPassword());
				authenticationResult = this.authenticationManager.authenticate(authenticationRequest);
			}
			
			SecurityContextHolder.getContext().setAuthentication(authenticationResult);
			
			return LoginBean.LOGIN_SUCCESS;
			
		}
		catch (LockedException | DisabledException e) {
			e.printStackTrace();
			JSFMessageUtil.sendErrorMessageToUser(JSFMessageUtil.getResource("error.user_acc_has_been_suspended", this.getUsername()), "");
		}
		catch (final BadCredentialsException e) {
			e.printStackTrace();
			JSFMessageUtil.sendErrorMessageToUser("the credentials are invalid", "");
		}
		catch (final AuthenticationException e) {
			e.printStackTrace();
			JSFMessageUtil.sendFatalMessageToUser(JSFMessageUtil.getResource("error.user_pass_is_invalid"), "");
		}
		
		return LoginBean.LOGIN_FAILURE;
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
	
	public boolean isRememberMe() {
		return this.rememberMe;
	}
	
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
}
