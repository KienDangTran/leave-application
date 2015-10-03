package com.giong.service.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<T> {
	
	public void save(T entity);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public T find(Serializable entityID);
	
	public List<T> findAll();
}
