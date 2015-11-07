package com.giong.service.implement.mt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giong.dao.interfaces.IGenericDAO;
import com.giong.dao.interfaces.mt.IUserDAO;
import com.giong.model.mt.MtUser;
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
		final MtUser user = this.userDAO.loadUserByUsername(username);
		if (user == null) throw new UsernameNotFoundException("Username Is Not Found");
		return user;
	}
}
