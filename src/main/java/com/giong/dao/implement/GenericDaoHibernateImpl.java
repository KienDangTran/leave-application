package com.giong.dao.implement;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.giong.dao.interfaces.IGenericDAO;

public abstract class GenericDaoHibernateImpl<T> implements IGenericDAO<T> {
	
	private final Class<T> entityClass;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public GenericDaoHibernateImpl(Class<T> entityClass) {
		super();
		this.entityClass = entityClass;
	}
	
	@Override
	public void save(T entity) {
		this.getSessionFactory().getCurrentSession().save(entity);
	}
	
	@Override
	public void update(T entity) {
		this.getSessionFactory().getCurrentSession().update(entity);
	}
	
	@Override
	public void delete(T entity) {
		this.getSessionFactory().getCurrentSession().delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T find(Serializable entityID) {
		return (T) this.getSessionFactory().getCurrentSession().get(this.entityClass, entityID);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return this.getSessionFactory().getCurrentSession().createCriteria(this.entityClass).list();
	}
}
