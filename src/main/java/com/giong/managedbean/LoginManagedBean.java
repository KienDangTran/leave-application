package com.giong.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;

import com.giong.util.FacesUtil;

@ManagedBean(name = "loginManagedBean")
@RequestScoped
public class LoginManagedBean extends AbtractManagedBean {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager;
	
	@ManagedProperty(value = "#{rememberMeServices}")
	private RememberMeServices rememberMeServices;
	
	public String doLogin() {
		
		try {
			final Authentication request = new UsernamePasswordAuthenticationToken(this.getUsername(), this.getPassword());
			final Authentication result = this.authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			
			final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			final HttpServletRequest httpServletRequest = (HttpServletRequest) context.getRequest();
			final HttpServletResponse httpServletResponse = (HttpServletResponse) context.getResponse();
			final HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpServletRequest);
			this.rememberMeServices.loginSuccess(wrapper, httpServletResponse, result);
		}
		catch (final LockedException | DisabledException e) {
			e.printStackTrace();
			FacesUtil.sendErrorMessage(this.getLocalizedMessage("error.user_acc_has_been_suspended", this.getUsername()), "");
			return "failure";
		}
		catch (final BadCredentialsException e) {
			e.printStackTrace();
			FacesUtil.sendErrorMessage(this.getLocalizedMessage("error.user_pass_is_invalid"), "");
			return "failure";
		}
		
		return "success";
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
	
}
