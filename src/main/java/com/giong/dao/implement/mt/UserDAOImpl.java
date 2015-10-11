package com.giong.dao.implement.mt;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.giong.dao.implement.GenericDaoHibernateImpl;
import com.giong.dao.interfaces.mt.IUserDAO;
import com.giong.model.mt.MtUser;

@Repository("userDAO")
public class UserDAOImpl extends GenericDaoHibernateImpl<MtUser, Integer> implements IUserDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public MtUser loadUserByUsername(String username) {
		final List<MtUser> users = this.currentSession().createQuery("SELECT m FROM MtUser m WHERE m.username = :username").setParameter("username", username).list();
		return users == null || users.size() == 0 ? null : users.get(0);
	}
	
}
