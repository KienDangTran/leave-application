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
 * The persistent class for the mt_permission database table.
 * 
 */
@Entity
@Table(name = "mt_permission")
@NamedQuery(name = "MtPermission.findAll", query = "SELECT m FROM MtPermission m")
public class MtPermission extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PERMISSION_CODE")
	private String permissionCode;
	
	@Lob
	@Column(name = "PERMISSION_DESC")
	private String permissionDesc;
	
	@Column(name = "STATUS")
	private String status;
	
	//bi-directional many-to-one association to MtRolePermissionGranted
	@OneToMany(mappedBy = "mtPermission")
	private List<MtRolePermissionGranted> mtRolePermissionGranteds;
	
	
	/*
	 *  CONTRUCTOS
	 */
	public MtPermission() {
	}
	
	
	/*
	 *  GETTERS & SETTERS
	 */
	public String getPermissionCode() {
		return this.permissionCode;
	}
	
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	
	public String getPermissionDesc() {
		return this.permissionDesc;
	}
	
	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
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
		mtRolePermissionGranted.setMtPermission(this);
		
		return mtRolePermissionGranted;
	}
	
	public MtRolePermissionGranted removeMtRolePermissionGranted(MtRolePermissionGranted mtRolePermissionGranted) {
		this.getMtRolePermissionGranteds().remove(mtRolePermissionGranted);
		mtRolePermissionGranted.setMtPermission(null);
		
		return mtRolePermissionGranted;
	}
	
	
	/*
	 *  OTHER METHODS
	 */
	@Override
	public Object getId() {
		return this.getPermissionCode();
	}
}