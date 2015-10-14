package com.giong.model.mt;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.giong.constant.MasterDataStatus;
import com.giong.model.BaseEntity;


/**
 * The persistent class for the mt_user database table.
 * 
 */
@Entity
@Table(name = "mt_user")
@NamedQuery(name = "MtUser.findAll", query = "SELECT m FROM MtUser m")
public class MtUser extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "USERNAME")
	private String username;
	
	//bi-directional one-to-one association to MtEmployee
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMPLOYEE_CODE", insertable = false, updatable = false)
	private MtEmployee mtEmployee;
	
	//bi-directional many-to-one association to MtUserRole
	@OneToMany(mappedBy = "mtUser")
	private List<MtUserRole> mtUserRoles;
	
	
	/*
	 *  CONTRUCTOS
	 */
	public MtUser() {
	}
	
	
	/*
	 *  GETTERS & SETTERS
	 */
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public MtEmployee getMtEmployee() {
		return this.mtEmployee;
	}
	
	public void setMtEmployee(MtEmployee mtEmployee) {
		this.mtEmployee = mtEmployee;
	}
	
	public List<MtUserRole> getMtUserRoles() {
		return this.mtUserRoles;
	}
	
	public void setMtUserRoles(List<MtUserRole> mtUserRoles) {
		this.mtUserRoles = mtUserRoles;
	}
	
	public MtUserRole addMtUserRole(MtUserRole mtUserRole) {
		this.getMtUserRoles().add(mtUserRole);
		mtUserRole.setMtUser(this);
		
		return mtUserRole;
	}
	
	public MtUserRole removeMtUserRole(MtUserRole mtUserRole) {
		this.getMtUserRoles().remove(mtUserRole);
		mtUserRole.setMtUser(null);
		
		return mtUserRole;
	}
	
	
	/*
	 *  OTHER METHODS
	 */
	@Override
	public Object getId() {
		return this.getUserId();
	}
	
	public boolean isSuspened() {
		return MasterDataStatus.SUSPENDED.equalsIgnoreCase(this.getStatus());
	}
	
}