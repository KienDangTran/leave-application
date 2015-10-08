package com.giong.model.mt;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.giong.model.BaseEntity;


/**
 * The persistent class for the mt_role database table.
 * 
 */
@Entity
@Table(name = "mt_role")
@NamedQuery(name = "MtRole.findAll", query = "SELECT m FROM MtRole m")
public class MtRole extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ROLE_CODE")
	private String roleCode;
	
	@Lob
	@Column(name = "ROLE_DESC")
	private String roleDesc;
	
	private String status;
	
	//bi-directional many-to-one association to MtRolePermissionGranted
	@OneToMany(mappedBy = "mtRole")
	private List<MtRolePermissionGranted> mtRolePermissionGranteds;
	
	//bi-directional many-to-one association to MtUserRole
	@OneToMany(mappedBy = "mtRole")
	private List<MtUserRole> mtUserRoles;
	
	/*
	 *  CONTRUCTOS
	 */
	public MtRole() {
	}
	
	
	/*
	 *  GETTERS & SETTERS
	 */
	public String getRoleCode() {
		return this.roleCode;
	}
	
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public String getRoleDesc() {
		return this.roleDesc;
	}
	
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<MtRolePermissionGranted> getMtRolePermissionGranteds() {
		return this.mtRolePermissionGranteds;
	}
	
	public void setMtRolePermissionGranteds(List<MtRolePermissionGranted> mtRolePermissionGranteds) {
		this.mtRolePermissionGranteds = mtRolePermissionGranteds;
	}
	
	public MtRolePermissionGranted addMtRolePermissionGranted(MtRolePermissionGranted mtRolePermissionGranted) {
		this.getMtRolePermissionGranteds().add(mtRolePermissionGranted);
		mtRolePermissionGranted.setMtRole(this);
		
		return mtRolePermissionGranted;
	}
	
	public MtRolePermissionGranted removeMtRolePermissionGranted(MtRolePermissionGranted mtRolePermissionGranted) {
		this.getMtRolePermissionGranteds().remove(mtRolePermissionGranted);
		mtRolePermissionGranted.setMtRole(null);
		
		return mtRolePermissionGranted;
	}
	
	public List<MtUserRole> getMtUserRoles() {
		return this.mtUserRoles;
	}
	
	public void setMtUserRoles(List<MtUserRole> mtUserRoles) {
		this.mtUserRoles = mtUserRoles;
	}
	
	public MtUserRole addMtUserRole(MtUserRole mtUserRole) {
		this.getMtUserRoles().add(mtUserRole);
		mtUserRole.setMtRole(this);
		
		return mtUserRole;
	}
	
	public MtUserRole removeMtUserRole(MtUserRole mtUserRole) {
		this.getMtUserRoles().remove(mtUserRole);
		mtUserRole.setMtRole(null);
		
		return mtUserRole;
	}
	
	
	/*
	 *  OTHER METHODS
	 */
	@Override
	public Object getId() {
		return this.getRoleCode();
	}
	
}