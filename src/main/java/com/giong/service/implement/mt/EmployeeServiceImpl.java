package com.giong.service.implement.mt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.giong.dao.implement.GenericDAOImpl;
import com.giong.dao.implement.mt.EmployeeDAOImpl;
import com.giong.model.mt.Mt_Employee;
import com.giong.service.implement.GenericServiceImpl;
import com.giong.service.interfaces.mt.IEmployeeService;

@Transactional(readOnly = true)
public class EmployeeServiceImpl extends GenericServiceImpl<Mt_Employee>implements IEmployeeService {
	
	@Autowired
	private EmployeeDAOImpl dao;
	
	@Override
	public GenericDAOImpl<Mt_Employee> getDao() {
		return this.dao;
	}
	
	public void setDao(EmployeeDAOImpl dao) {
		this.dao = dao;
	}
	
}
