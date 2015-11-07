package com.giong.model.mt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.giong.model.BaseEntity;


/**
 * The persistent class for the mt_user database table.
 * 
 */
@Entity
@Table(name = "MT_USER")
@NamedQuery(name = "MtUser.findAll", query = "SELECT m FROM MtUser m")
public class MtUser extends BaseEntity implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "THEME")
	private String theme;
	
	@Column(name = "ACCOUNT_NON_EXPIRED")
	private boolean accountNonExpired = true;
	
	@Column(name = "ACCOUNT_NON_LOCKED")
	private boolean accountNonLocked = true;
	
	@Column(name = "CREDENTIALS_NON_EXPIRED")
	private boolean credentialsNonExpired = true;
	
	@Column(name = "ENABLED")
	private boolean enabled = true;
	
	
	//bi-directional one-to-one association to MtEmployee
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMPLOYEE_CODE", insertable = false, updatable = false)
	private MtEmployee mtEmployee;
	
	//bi-directional many-to-one association to MtUserRole
	@OneToMany(mappedBy = "mtUser", fetch = FetchType.EAGER)
	private List<MtUserRole> mtUserRoles = new ArrayList<MtUserRole>();
	
	
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
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getTheme() {
		return this.theme;
	}
	
	@Override
	public String getUsername() {
		return this.username;
	}
	
	public void setTheme(String theme) {
		this.theme = theme;
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
	
	@Override
	public Object getId() {
		return this.getUserId();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		if (this.mtUserRoles.isEmpty()) return Collections.emptyList();
		
		final Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		String roleCode;
		GrantedAuthority authority;
		for (final MtUserRole userRole : this.mtUserRoles) {
			roleCode = userRole.getId().getRoleCode();
			
			if (StringUtils.isEmpty(roleCode)) {
				continue;
			}
			
			authority = new SimpleGrantedAuthority(roleCode);
			if (!authorities.contains(authority)) {
				authorities.add(authority);
			}
		}
		
		return authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}
	
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}
	
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}