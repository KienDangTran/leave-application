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

import com.giong.dao.interfaces.IGenericDAO;
import com.giong.dao.interfaces.mt.IUserDAO;
import com.giong.model.mt.MtUser;
import com.giong.model.mt.MtUserRole;
import com.giong.service.implement.GenericServiceImpl;
import com.giong.service.interfaces.mt.IUserService;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl extends GenericServiceImpl<MtUser, Integer> implements IUserService, UserDetailsService {
	
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
	
	public UserServiceImpl() {
	}
	
	
	@Autowired
	public UserServiceImpl(@Qualifier("userDAO") IGenericDAO<MtUser, Integer> genericDAO) {
		super(genericDAO);
		this.userDAO = (IUserDAO) genericDAO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final MtUser domainUser = this.userDAO.loadUserByUsername(username);
		final boolean enabled = true;
		final boolean accountNonExpired = true;
		final boolean credentialsNonExpired = true;
		final boolean accountNonLocked = true;
		final Collection<? extends GrantedAuthority> authorities = this.getAuthorities(domainUser.getMtUserRoles());
		final User user = new User(domainUser.getUsername(), domainUser.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return user;
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(List<MtUserRole> rolesOfUser) {
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (final MtUserRole userRole : rolesOfUser) {
			authorities.add(UserServiceImpl.getGrantedAuthority(userRole.getId().getRoleCode()));
		}
		return authorities;
	}
	
	public static GrantedAuthority getGrantedAuthority(String roleCode) {
		final GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleCode);
		
		return grantedAuthority;
	}
	
}
