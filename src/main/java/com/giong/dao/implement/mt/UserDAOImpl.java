package com.giong.dao.implement.mt;

import com.giong.dao.implement.GenericDAOImpl;
import com.giong.dao.interfaces.mt.IUserDAO;
import com.giong.model.mt.Mt_User;

public class UserDAOImpl extends GenericDAOImpl<Mt_User>implements IUserDAO {
	
	private static final long serialVersionUID = 1L;
	
	public UserDAOImpl(Class<Mt_User> entityClass) {
		super(entityClass);
	}
	
}
