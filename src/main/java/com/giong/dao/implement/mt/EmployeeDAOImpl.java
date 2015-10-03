package com.giong.dao.implement.mt;

import org.springframework.stereotype.Repository;

import com.giong.dao.implement.GenericDaoHibernateImpl;
import com.giong.dao.interfaces.mt.IEmployeeDAO;
import com.giong.model.mt.Mt_Employee;

@Repository("employeeDAO")
public class EmployeeDAOImpl extends GenericDaoHibernateImpl<Mt_Employee>implements IEmployeeDAO {
	
	public EmployeeDAOImpl(Class<Mt_Employee> entityClass) {
		super(entityClass);
	}
	
}
