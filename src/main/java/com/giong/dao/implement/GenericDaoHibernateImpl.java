package com.giong.dao.implement;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.giong.dao.interfaces.IGenericDAO;

@Repository
public abstract class GenericDaoHibernateImpl<T, K extends Serializable> implements IGenericDAO<T, K> {
	
	private final Class<? extends T> entityClass;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	/*
	 ***************************************	CONTRUCTORS	  ***************************************	
	 */
	@SuppressWarnings("unchecked")
	public GenericDaoHibernateImpl() {
		final Type t = this.getClass().getGenericSuperclass();
		final ParameterizedType pt = (ParameterizedType) t;
		this.entityClass = (Class<? extends T>) pt.getActualTypeArguments()[0];
	}
	
	
	/*
	 ***************************************	GETTERS & SETTERS	***************************************	
	 */
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	/*
	 ***************************************	IMPLEMENTATIONS		***************************************	
	 */
	@Override
	public void save(T entity) {
		this.currentSession().save(entity);
	}
	
	@Override
	public void update(T entity) {
		this.currentSession().update(entity);
	}
	
	@Override
	public void delete(T entity) {
		this.currentSession().delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T find(Serializable entityID) {
		return (T) this.currentSession().get(this.entityClass, entityID);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return this.currentSession().createCriteria(this.entityClass).list();
	}
	
	
	/*
	 ***************************************	OTHER METHODS	***************************************	
	 */
	
	protected Session currentSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
