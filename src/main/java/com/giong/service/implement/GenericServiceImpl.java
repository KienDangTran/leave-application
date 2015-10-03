package com.giong.service.implement;

import java.io.Serializable;
import java.util.List;

import com.giong.dao.implement.GenericDAOImpl;
import com.giong.service.interfaces.IGenericService;

public abstract class GenericServiceImpl<T> implements IGenericService<T> {
	
	private GenericDAOImpl<T> dao;
	
	
	public abstract GenericDAOImpl<T> getDao();
	
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
