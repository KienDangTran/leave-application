package com.giong.model.mt;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MT_USER")
public class Mt_User {
	
	@Id
	@Column(name = "USER_ID", unique = true, nullable = false)
	private int user_id;
	
	public int getUser_id() {
		return this.user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "EMPLOYEE_CODE", unique = true, nullable = false)
	private String employee_code;
	
	public String getEmployee_code() {
		return this.employee_code;
	}
	
	public void setEmployee_code(String employee_code) {
		this.employee_code = employee_code;
	}
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_code")
	private Mt_Employee mt_employee;
	
	public Mt_Employee getMt_employee() {
		return this.mt_employee;
	}
	
	public void setMt_employee(Mt_Employee mt_employee) {
		this.mt_employee = mt_employee;
		this.employee_code = mt_employee == null ? null : mt_employee.getEmployee_code();
	}
	
}
