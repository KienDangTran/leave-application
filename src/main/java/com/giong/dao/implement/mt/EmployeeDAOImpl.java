package com.giong.dao.implement.mt;

import com.giong.dao.implement.GenericDAOImpl;
import com.giong.dao.interfaces.mt.IEmployeeDAO;
import com.giong.model.mt.Mt_Employee;

public class EmployeeDAOImpl extends GenericDAOImpl<Mt_Employee>implements IEmployeeDAO {
	
	private static final long serialVersionUID = 1L;
	
	public EmployeeDAOImpl(Class<Mt_Employee> entityClass) {
		super(entityClass);
	}
	
}
