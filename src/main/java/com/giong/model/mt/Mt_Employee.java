package com.giong.model.mt;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MT_EMPLOYEE")
public class Mt_Employee {
	
	@Id
	@Column(name = "EMPLOYEE_CODE", unique = true, nullable = false)
	private String employee_code;
	
	public String getEmployee_code() {
		return this.employee_code;
	}
	
	public void setEmployee_code(String employee_code) {
		this.employee_code = employee_code;
	}
	
	@Column(name = "EMPLOYEE_NAME", nullable = false)
	private String employee_name;
	
	public String getEmployee_name() {
		return this.employee_name;
	}
	
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH")
	private Date date_of_birth;
	
	public Date getDate_of_birth() {
		return this.date_of_birth;
	}
	
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	
	@Column(name = "EMAIL")
	private String email;
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "PHONE_NO")
	private String phone_no;
	
	public String getPhone_no() {
		return this.phone_no;
	}
	
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	
	@OneToOne(mappedBy = "mt_employee", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Mt_User mt_User;
	
	public Mt_User getMt_User() {
		return this.mt_User;
	}
	
	public void setMt_User(Mt_User mt_User) {
		this.mt_User = mt_User;
	}
	
}
