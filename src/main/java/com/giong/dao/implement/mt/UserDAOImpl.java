package com.giong.dao.implement.mt;

import org.springframework.stereotype.Repository;

import com.giong.dao.implement.GenericDaoHibernateImpl;
import com.giong.dao.interfaces.mt.IUserDAO;
import com.giong.model.mt.Mt_User;

@Repository("userDAO")
public class UserDAOImpl extends GenericDaoHibernateImpl<Mt_User, Integer>implements IUserDAO {

}
