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
 * The persistent class for the mt_role_permission_granted database table.
 * 
 */
@Entity
@Table(name = "mt_role_permission_granted")
@NamedQuery(name = "MtRolePermissionGranted.findAll", query = "SELECT m FROM MtRolePermissionGranted m")
public class MtRolePermissionGranted extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private MtRolePermissionGrantedPK id;
	
	private String status;
	
	//bi-directional many-to-one association to MtPermission
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERMISSION_CODE", insertable = false, updatable = false)
	private MtPermission mtPermission;
	
	//bi-directional many-to-one association to MtRole
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_CODE", insertable = false, updatable = false)
	private MtRole mtRole;
	
	
	/*
	 *  CONTRUCTOS
	 */
	public MtRolePermissionGranted() {
	}
	
	public MtRolePermissionGranted(MtRolePermissionGrantedPK id) {
		super();
		this.id = id;
	}
	
	
	/*
	 *  GETTERS & SETTERS
	 */
	@Override
	public MtRolePermissionGrantedPK getId() {
		return this.id;
	}
	
	public void setId(MtRolePermissionGrantedPK id) {
		this.id = id;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public MtPermission getMtPermission() {
		return this.mtPermission;
	}
	
	public void setMtPermission(MtPermission mtPermission) {
		this.mtPermission = mtPermission;
		this.getId().setPermissionCode(mtPermission == null ? null : mtPermission.getPermissionCode());
	}
	
	public MtRole getMtRole() {
		return this.mtRole;
	}
	
	public void setMtRole(MtRole mtRole) {
		this.mtRole = mtRole;
		this.getId().setRoleCode(mtRole == null ? null : mtRole.getRoleCode());
	}
	
	
	/*
	 *  OTHER METHODS
	 */
	
	
}