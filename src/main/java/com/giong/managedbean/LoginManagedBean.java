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

import com.giong.util.FacesUtil;

@ManagedBean(name = "loginManagedBean")
@RequestScoped
public class LoginManagedBean extends AbtractManagedBean implements PhaseListener {
	
	private static final long serialVersionUID = 1L;
	
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
			FacesUtil.sendFatalMessageToUser(FacesUtil.getResource("error.user_pass_is_invalid"), "");
		}
		else if (e instanceof LockedException || e instanceof DisabledException) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			FacesUtil.sendErrorMessageToUser(FacesUtil.getResource("error.user_acc_has_been_suspended"), "");
		}
	}
	
	
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
	
	public void doLogin() throws ServletException, IOException {
		final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		final RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/login");
		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	
	/*
	 ***************************************	GETTER & SETTER	***************************************	
	 */
	
}
