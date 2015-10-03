package com.giong.service.implement;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.giong.dao.interfaces.IGenericDAO;
import com.giong.service.interfaces.IGenericService;

@Service
public abstract class GenericServiceImpl<T, K extends Serializable> implements IGenericService<T, K>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private IGenericDAO<T, K> genericDAO;
	
	
	/*
	 ***************************************	CONTRUCTORS	  ***************************************	
	 */
	public GenericServiceImpl(IGenericDAO<T, K> genericDAO) {
		this.genericDAO = genericDAO;
	}
	
	public GenericServiceImpl() {
	}
	
	
	/*
	 ***************************************	IMPLEMENTATIONS		***************************************	
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(T entity) {
		this.genericDAO.save(entity);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T entity) {
		this.genericDAO.update(entity);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(T entity) {
		this.genericDAO.delete(entity);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public T find(K entityID) {
		return this.genericDAO.find(entityID);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findAll() {
		return this.genericDAO.findAll();
	}
	
}
