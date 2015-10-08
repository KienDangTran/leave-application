package com.giong.service.implement.mt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giong.dao.interfaces.IGenericDAO;
import com.giong.dao.interfaces.mt.IEmployeeDAO;
import com.giong.model.mt.MtEmployee;
import com.giong.service.implement.GenericServiceImpl;
import com.giong.service.interfaces.mt.IEmployeeService;

@Service("employeeService")
@Transactional(readOnly = true)
public class EmployeeServiceImpl extends GenericServiceImpl<MtEmployee, String> implements IEmployeeService {
	
	private static final long serialVersionUID = 1L;
	
	private IEmployeeDAO employeeDAO;
	
	
	/*
	 ***************************************	GETTER & SETTER	***************************************	
	 */
	public IEmployeeDAO getEmployeeDAO() {
		return this.employeeDAO;
	}
	
	public void setEmployeeDAO(IEmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	/*
	 ***************************************	CONTRUCTORS	  ***************************************	
	 */
	public EmployeeServiceImpl() {
	}
	
	@Autowired
	public EmployeeServiceImpl(@Qualifier("employeeDAO") IGenericDAO<MtEmployee, String> genericDAO) {
		super(genericDAO);
		this.employeeDAO = (IEmployeeDAO) genericDAO;
	}
	
}
