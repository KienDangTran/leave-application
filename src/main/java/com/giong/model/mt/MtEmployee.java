package com.giong.model.mt;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.giong.model.BaseEntity;


/**
 * The persistent class for the mt_employee database table.
 * 
 */
@Entity
@Table(name = "mt_employee")
@NamedQuery(name = "MtEmployee.findAll", query = "SELECT m FROM MtEmployee m")
public class MtEmployee extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "EMPLOYEE_CODE")
	private String employeeCode;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "EMPLOYEE_NAME")
	private String employeeName;
	
	@Column(name = "PHONE_NO")
	private String phoneNo;
	
	@Column(name = "STATUS")
	private String status;
	
	//bi-directional one-to-one association to MtUser
	@OneToOne(mappedBy = "mtEmployee")
	private MtUser mtUser;
	
	
	/*
	 *  CONTRUCTOS
	 */
	public MtEmployee() {
	}
	
	
	/*
	 *  GETTERS & SETTERS
	 */
	public String getEmployeeCode() {
		return this.employeeCode;
	}
	
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmployeeName() {
		return this.employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public String getPhoneNo() {
		return this.phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public MtUser getMtUser() {
		return this.mtUser;
	}
	
	public void setMtUser(MtUser mtUser) {
		this.mtUser = mtUser;
	}
	
	
	/*
	 *  OTHER METHODS
	 */
	@Override
	public Object getId() {
		return this.getEmployeeCode();
	}
	
}