package com.giong.service.implement.mt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giong.dao.interfaces.IGenericDAO;
import com.giong.dao.interfaces.mt.IUserDAO;
import com.giong.model.mt.Mt_User;
import com.giong.service.implement.GenericServiceImpl;
import com.giong.service.interfaces.mt.IUserService;

@Transactional(readOnly = true)
@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<Mt_User, Integer>implements IUserService {
	
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
	public UserServiceImpl(@Qualifier("userDAO") IGenericDAO<Mt_User, Integer> genericDAO) {
		super(genericDAO);
		this.userDAO = (IUserDAO) genericDAO;
	}
	
}
