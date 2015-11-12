package com.giong.model.mt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
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
	
	@Transient
	private Set<GrantedAuthority> authorities;
	
	
	//bi-directional one-to-one association to MtEmployee
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMPLOYEE_CODE", insertable = false, updatable = false)
	private MtEmployee mtEmployee;
	
	//bi-directional many-to-one association to MtUserRole
	@OneToMany(mappedBy = "mtUser", fetch = FetchType.EAGER)
	private List<MtUserRole> mtUserRoles = new ArrayList<MtUserRole>();
	
	
	/*
	 *-----------------------------------------------		CONTRUCTOS				-----------------------------------------------
	 */
	public MtUser() {
	}
	
	
	/*
	 *  -----------------------------------------------		GETTERS & SETTERS		-----------------------------------------------
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
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		if (this.mtUserRoles == null || this.mtUserRoles.isEmpty()) return Collections.emptySet();
		//		final Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		this.authorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());
		String roleCode;
		GrantedAuthority authority;
		for (final MtUserRole userRole : this.mtUserRoles) {
			roleCode = userRole.getId().getRoleCode();
			
			if (StringUtils.isEmpty(roleCode)) {
				continue;
			}
			
			authority = new SimpleGrantedAuthority(roleCode);
			if (!this.authorities.contains(authority)) {
				this.authorities.add(authority);
			}
		}
		
		return Collections.unmodifiableSet(MtUser.sortAuthorities(this.authorities));
	}
	
	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		// Ensure array iteration order is predictable (as per
		// UserDetails.getAuthorities() contract and SEC-717)
		final SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());
		
		for (final GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}
		
		return sortedAuthorities;
	}
	
	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
		
		@Override
		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			// Neither should ever be null as each entry is checked before adding it to
			// the set.
			// If the authority is null, it is a custom authority and should precede
			// others.
			if (g2.getAuthority() == null) return -1;
			
			if (g1.getAuthority() == null) return 1;
			
			return g1.getAuthority().compareTo(g2.getAuthority());
		}
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		sb.append("Username: ").append(this.username).append("; ");
		sb.append("Password: [PROTECTED]; ");
		sb.append("Enabled: ").append(this.enabled).append("; ");
		sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
		sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
		sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");
		
		if (!this.authorities.isEmpty()) {
			sb.append("Granted Authorities: ");
			
			boolean first = true;
			for (final GrantedAuthority auth : this.authorities) {
				if (!first) {
					sb.append(",");
				}
				first = false;
				
				sb.append(auth);
			}
		}
		else {
			sb.append("Not granted any authorities");
		}
		
		return sb.toString();
	}
	
	/**
	 * Returns {@code true} if the supplied object is a {@code MtUser} instance with the same {@code username} value.
	 * <p>
	 * In other words, the objects are equal if they have the same username, representing the same principal.
	 */
	@Override
	public boolean equals(Object rhs) {
		if (rhs instanceof MtUser) return this.username.equals(((MtUser) rhs).username);
		return false;
	}
	
	/**
	 * Returns the hashcode of the {@code username}.
	 */
	@Override
	public int hashCode() {
		return this.username.hashCode();
	}
	
}