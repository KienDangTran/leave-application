package com.giong.dao.implement;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.giong.dao.interfaces.IGenericDAO;

public abstract class GenericDAOImpl<T> implements Serializable, IGenericDAO<T> {
	
	private static final long serialVersionUID = 1L;
	
	private final Class<T> entityClass;
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public GenericDAOImpl(Class<T> entityClass) {
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
