package com.giong.managedbean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.ServletException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.giong.util.FacesUtil;

@ManagedBean(name = "loginManagedBean")
@RequestScoped
public class LoginManagedBean extends AbtractManagedBean {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager;
	
	public String doLogin() throws ServletException, IOException {
		
		try {
			final Authentication request = new UsernamePasswordAuthenticationToken(this.getUsername(), this.getPassword());
			final Authentication result = this.authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			return "success";
		}
		catch (final LockedException | DisabledException e) {
			e.printStackTrace();
			FacesUtil.sendErrorMessage(this.getLocalizedMessage("error.user_acc_has_been_suspended", this.getUsername()), "");
		}
		catch (final BadCredentialsException e) {
			e.printStackTrace();
			FacesUtil.sendErrorMessage(this.getLocalizedMessage("error.user_pass_is_invalid"), "");
		}
		return "failure";
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
	
}
