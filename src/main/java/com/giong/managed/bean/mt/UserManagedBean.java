package com.giong.managed.bean.mt;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.giong.model.mt.Mt_User;
import com.giong.service.interfaces.mt.IUserService;

@ManagedBean(name = "userManagedBean")
public class UserManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	
	@ManagedProperty(value = "#{UserService}")
	IUserService userService;
	private int user_id;
	private String username;
	private String password;
	private List<Mt_User> userList;
	
	public IUserService getUserService() {
		return this.userService;
	}
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	public int getUser_id() {
		return this.user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	
	public List<Mt_User> getUserList() {
		return this.userList;
	}
	
	public void setUserList(List<Mt_User> userList) {
		this.userList = userList;
	}
	
	public String addUser() {
		try {
			final Mt_User user = new Mt_User();
			user.setUser_id(this.getUser_id());
			user.setUsername(this.getUsername());
			user.setPassword(this.getPassword());
			this.getUserService().save(user);
			return UserManagedBean.SUCCESS;
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
		return UserManagedBean.ERROR;
	}
	
	public void reset() {
		this.setUser_id(0);
		this.setUsername(null);
		this.setPassword(null);
	}
}
