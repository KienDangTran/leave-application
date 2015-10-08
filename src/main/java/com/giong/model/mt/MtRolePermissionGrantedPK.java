package com.giong.model.mt;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.giong.model.BasePK;

/**
 * The primary key class for the mt_role_permission_granted database table.
 * 
 */
@Embeddable
public class MtRolePermissionGrantedPK extends BasePK {
	
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ROLE_CODE", insertable = false, updatable = false)
	private String roleCode;
	
	@Column(name = "PERMISSION_CODE", insertable = false, updatable = false)
	private String permissionCode;
	
	
	/*
	 *  CONTRUCTOS
	 */
	public MtRolePermissionGrantedPK() {
	}
	
	public MtRolePermissionGrantedPK(String roleCode, String permissionCode) {
		super();
		this.roleCode = roleCode;
		this.permissionCode = permissionCode;
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
	
	public String getPermissionCode() {
		return this.permissionCode;
	}
	
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	
	
	
	/*
	 *  OTHER METHODS
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof MtRolePermissionGrantedPK)) return false;
		final MtRolePermissionGrantedPK castOther = (MtRolePermissionGrantedPK) other;
		return this.roleCode.equals(castOther.roleCode) && this.permissionCode.equals(castOther.permissionCode);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + (this.roleCode == null ? 0 : this.roleCode.hashCode());
		hash = hash * prime + (this.permissionCode == null ? 0 : this.permissionCode.hashCode());
		
		return hash;
	}
}