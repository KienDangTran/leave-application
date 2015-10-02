package com.giong.service.implement.mt;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.giong.dao.interfaces.mt.IEmployeeDAO;
import com.giong.model.mt.Mt_Employee;
import com.giong.service.interfaces.mt.IEmployeeService;

@Transactional(readOnly = true)
public class EmployeeService implements IEmployeeService {

	private IEmployeeDAO employeeDAO;

	public IEmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(IEmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	public void addEmployee(Mt_Employee employee) {
		this.getEmployeeDAO().addEmployee(employee);
	}

	@Override
	public void updateEmployee(Mt_Employee employee) {
		this.getEmployeeDAO().updateEmployee(employee);
	}

	@Override
	public void deleteEmployee(Mt_Employee employee) {
		this.getEmployeeDAO().deleteEmployee(employee);
	}

	@Override
	public Mt_Employee getEmployeeByCode(String employeeCode) {
		return this.getEmployeeDAO().getEmployeeByCode(employeeCode);
	}

	@Override
	public List<Mt_Employee> getEmployeeList() {
		return this.getEmployeeDAO().getEmployeeList();
	}

}
