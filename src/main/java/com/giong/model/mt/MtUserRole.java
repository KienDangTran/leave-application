package com.giong.model.mt;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.giong.model.BaseEntity;


/**
 * The persistent class for the mt_user_role database table.
 * 
 */
@Entity
@Table(name = "mt_user_role")
@NamedQuery(name = "MtUserRole.findAll", query = "SELECT m FROM MtUserRole m")
public class MtUserRole extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private MtUserRolePK id;
	
	private String status;
	
	//bi-directional many-to-one association to MtRole
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_CODE", insertable = false, updatable = false)
	private MtRole mtRole;
	
	//bi-directional many-to-one association to MtUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", insertable = false, updatable = false)
	private MtUser mtUser;
	
	
	/*
	 *  Contructors
	 */
	public MtUserRole() {
		super();
	}
	
	public MtUserRole(MtUserRolePK id) {
		super();
		this.id = id;
	}
	
	
	/*
	 *  Getters & Setters
	 */
	@Override
	public MtUserRolePK getId() {
		return this.id;
	}
	
	public void setId(MtUserRolePK id) {
		this.id = id;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public MtRole getMtRole() {
		return this.mtRole;
	}
	
	public void setMtRole(MtRole mtRole) {
		this.mtRole = mtRole;
		this.getId().setRoleCode(mtRole == null ? null : mtRole.getRoleCode());
	}
	
	public MtUser getMtUser() {
		return this.mtUser;
	}
	
	public void setMtUser(MtUser mtUser) {
		this.mtUser = mtUser;
		this.getId().setUserId(this.mtRole == null ? null : mtUser.getUserId());
	}
	
	
	/*
	 *  OTHER METHODS
	 */
	
}