package com.giong.dao.implement.mt;

import java.util.List;

import org.hibernate.SessionFactory;

import com.giong.dao.interfaces.mt.IUserDAO;
import com.giong.model.mt.Mt_User;

public class UserDAO implements IUserDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUser(Mt_User user) {
		this.getSessionFactory().getCurrentSession().save(user);
	}

	@Override
	public void updateUser(Mt_User user) {
		this.getSessionFactory().getCurrentSession().update(user);
	}

	@Override
	public void deleteUser(Mt_User user) {
		this.getSessionFactory().getCurrentSession().delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Mt_User getUserByID(int userID) {
		List<Mt_User> users = this.getSessionFactory().getCurrentSession().createQuery("FROM Mt_User WHERE user_id = ? ").setParameter(0, userID).list();
		return users.size() == 0 ? null : users.get(0);
	}

	@Override
	public List<Mt_User> getUserList() {
		@SuppressWarnings("unchecked")
		List<Mt_User> users = this.getSessionFactory().getCurrentSession().createQuery("FROM Mt_User ").list();
		return users;
	}

}
