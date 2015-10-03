package com.giong.service.implement.mt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giong.dao.implement.GenericDaoHibernateImpl;
import com.giong.dao.implement.mt.UserDAOImpl;
import com.giong.model.mt.Mt_User;
import com.giong.service.implement.GenericServiceImpl;
import com.giong.service.interfaces.mt.IUserService;

@Transactional(readOnly = true)
@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<Mt_User>implements IUserService {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("userDAO")
	private UserDAOImpl dao;
	
	@Override
	public GenericDaoHibernateImpl<Mt_User> getDao() {
		return this.dao;
	}
	
	public void setDao(UserDAOImpl dao) {
		this.dao = dao;
	}
	
}
