package com.giong.service.implement;

import java.io.Serializable;
import java.util.List;

import com.giong.dao.interfaces.IGenericDAO;
import com.giong.service.interfaces.IGenericService;

public abstract class GenericServiceImpl<T> implements IGenericService<T>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private IGenericDAO<T> dao;
	
	public abstract IGenericDAO<T> getDao();
	
	@Override
	public void save(T entity) {
		this.dao.save(entity);
	}
	
	@Override
	public void update(T entity) {
		this.dao.update(entity);
	}
	
	@Override
	public void delete(T entity) {
		this.dao.delete(entity);
	}
	
	@Override
	public T find(Serializable entityID) {
		return this.dao.find(entityID);
	}
	
	@Override
	public List<T> findAll() {
		return this.dao.findAll();
	}
	
}
