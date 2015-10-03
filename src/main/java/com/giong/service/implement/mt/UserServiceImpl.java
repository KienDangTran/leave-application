package com.giong.service.implement.mt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.giong.dao.implement.GenericDAOImpl;
import com.giong.dao.implement.mt.UserDAOImpl;
import com.giong.model.mt.Mt_User;
import com.giong.service.implement.GenericServiceImpl;
import com.giong.service.interfaces.mt.IUserService;

@Transactional(readOnly = true)
public class UserServiceImpl extends GenericServiceImpl<Mt_User>implements IUserService {
	
	@Autowired
	private UserDAOImpl dao;
	
	@Override
	public GenericDAOImpl<Mt_User> getDao() {
		return this.dao;
	}
	
	public void setDao(UserDAOImpl dao) {
		this.dao = dao;
	}
	
}
