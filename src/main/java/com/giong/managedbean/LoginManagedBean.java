package com.giong.managedbean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
public class LoginManagedBean extends AbtractManagedBean {
	
	private static final long serialVersionUID = 1L;
	
	
	public void doLogin() throws ServletException, IOException {
		
		final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		final RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/login");
		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
		
		final Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);
		if (e instanceof BadCredentialsException) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			FacesUtil.sendFatalMessage(this.getLocalizedMessage("error.user_pass_is_invalid"), "");
			//			this.err = this.getLocalizedMessage("error.user_pass_is_invalid");
		}
		else if (e instanceof LockedException || e instanceof DisabledException) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			FacesUtil.sendErrorMessage(this.getLocalizedMessage("error.user_acc_has_been_suspended"), "");
			//			this.err = this.getLocalizedMessage("error.user_acc_has_been_suspended");
		}
		
	}
	
	//	private String err;
	//	
	//	public String getErr() {
	//		return this.err;
	//	}
	//	
	//	public void setErr(String err) {
	//		this.err = err;
	//	}
	
}
