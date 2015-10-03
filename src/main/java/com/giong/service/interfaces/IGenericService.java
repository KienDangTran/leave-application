package com.giong.service.interfaces;

import java.util.List;

public interface IGenericService<T, K> {
	
	public void save(T entity);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public T find(K entityID);
	
	public List<T> findAll();
}
