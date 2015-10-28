package com.giong.service.implement.mt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.giong.dao.interfaces.IGenericDAO;
import com.giong.dao.interfaces.mt.IUserDAO;
import com.giong.model.mt.MtUser;
import com.giong.model.mt.MtUserRole;
import com.giong.service.implement.GenericServiceImpl;

@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl extends GenericServiceImpl<MtUser, Integer> implements UserDetailsService {
	
	private static final long serialVersionUID = 1L;
	
	private IUserDAO userDAO;
	
	
	/*
	 ***************************************	GETTER & SETTER	***************************************	
	 */
	public IUserDAO getUserDAO() {
		return this.userDAO;
	}
	
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
	/*
	 ***************************************	CONTRUCTORS	  ***************************************	
	 */
	
	public UserDetailsServiceImpl() {
	}
	
	
	@Autowired
	public UserDetailsServiceImpl(@Qualifier("userDAO") IGenericDAO<MtUser, Integer> genericDAO) {
		super(genericDAO);
		this.userDAO = (IUserDAO) genericDAO;
	}
	
	
	/*
	 ***************************************	OTHER METHODS	  ***************************************	
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final MtUser domainUser = this.userDAO.loadUserByUsername(username);
		if (domainUser == null) throw new UsernameNotFoundException("Username Is Not Found");
		final boolean enabled = true;
		final boolean accountNonExpired = true;
		final boolean credentialsNonExpired = true;
		final boolean accountNonLocked = !domainUser.isSuspened();
		final Collection<? extends GrantedAuthority> authorities = this.getAuthorities(domainUser.getMtUserRoles());
		final User user = new User(domainUser.getUsername(), domainUser.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return user;
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(List<MtUserRole> rolesOfUser) {
		final List<GrantedAuthority> authorities = UserDetailsServiceImpl.getGrantedAuthorities(this.getRoles(rolesOfUser));
		return authorities;
	}
	
	private List<String> getRoles(List<MtUserRole> rolesOfUser) {
		
		final List<String> roles = new ArrayList<String>();
		String roleCode;
		for (final MtUserRole userRole : rolesOfUser) {
			roleCode = userRole.getId().getRoleCode();
			if (!StringUtils.isEmpty(roleCode) && !roles.contains(roleCode)) {
				roles.add(roleCode);
			}
		}
		return roles;
	}
	
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roleCode) {
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (final String role : roleCode) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
	
}
