package com.giong.managedbean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.web.WebAttributes;

import com.giong.util.JSFMessageUtil;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean extends AbtractManagedBean implements PhaseListener {
	
	private static final long serialVersionUID = 1L;
	
	
	//	private String username;
	//	private String password;
	//	private boolean rememberMe;
	//	
	
	/*
	 ***************************************	ACTIONS		***************************************	
	 */
	
	@Override
	public void afterPhase(PhaseEvent event) {
	
	}
	
	@Override
	public void beforePhase(PhaseEvent event) {
		final Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);
		
		if (e instanceof BadCredentialsException) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			JSFMessageUtil.sendFatalMessageToUser(JSFMessageUtil.getResource("error.user_pass_is_invalid"), "");
		}
		else if (e instanceof LockedException || e instanceof DisabledException) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			JSFMessageUtil.sendErrorMessageToUser(JSFMessageUtil.getResource("error.user_acc_has_been_suspended"), "");
		}
	}
	
	
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
	
	/**
	 *
	 * Redirects the login request directly to spring security check. Leave this method as it is to properly support spring security.
	 * 
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doLogin() {
		try {
			
			final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			
			final RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/login");
			
			dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
			
			
			FacesContext.getCurrentInstance().responseComplete();
			
		}
		catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 ***************************************	GETTER & SETTER	***************************************	
	 */
	//	public String getUsername() {
	//		return this.username;
	//	}
	//	
	//	public void setUsername(String username) {
	//		this.username = username;
	//	}
	//	
	//	public String getPassword() {
	//		return this.password;
	//	}
	//	
	//	public void setPassword(String password) {
	//		this.password = password;
	//	}
	//	
	//	public boolean isRememberMe() {
	//		return this.rememberMe;
	//	}
	//	
	//	public void setRememberMe(boolean rememberMe) {
	//		this.rememberMe = rememberMe;
	//	}
	
}
