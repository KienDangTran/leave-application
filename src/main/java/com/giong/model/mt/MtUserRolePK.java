package com.giong.model.mt;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.giong.model.BasePK;

/**
 * The primary key class for the mt_user_role database table.
 * 
 */
@Embeddable
public class MtUserRolePK extends BasePK {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column(name = "USER_ID", insertable = false, updatable = false)
	private String userId;
	
	@Column(name = "ROLE_CODE", insertable = false, updatable = false)
	private String roleCode;
	
	
	/*
	 *  CONTRUCTOS
	 */
	public MtUserRolePK() {
	}
	
	public MtUserRolePK(String userId, String roleCode) {
		super();
		this.userId = userId;
		this.roleCode = roleCode;
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
	
	public String getRoleCode() {
		return this.roleCode;
	}
	
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	
	/*
	 *  OTHER METHODS
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof MtUserRolePK)) return false;
		final MtUserRolePK castOther = (MtUserRolePK) other;
		return this.userId.equals(castOther.userId) && this.roleCode.equals(castOther.roleCode);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + (this.userId == null ? 0 : this.userId.hashCode());
		hash = hash * prime + (this.roleCode == null ? 0 : this.roleCode.hashCode());
		
		return hash;
	}
}
