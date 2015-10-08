package com.giong.managedbean.mt;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.giong.managedbean.AbtractManagedBean;
import com.giong.model.mt.MtEmployee;
import com.giong.service.interfaces.mt.IEmployeeService;

@ManagedBean(name = "employeeManagedBean")
@ViewScoped
public class EmployeeManagedBean extends AbtractManagedBean {
	
	private static final long serialVersionUID = 1L;
	
	private MtEmployee currentEmployee;
	private final List<MtEmployee> allEmployees = this.employeeService.findAll();
	
	@ManagedProperty(value = "#{employeeService}")
	IEmployeeService employeeService;
	
	/*
	 ***************************************	GETTER & SETTER		***************************************	
	 */
	
	public MtEmployee getCurrentEmployee() {
		return this.currentEmployee;
	}
	
	public void setCurrentEmployee(MtEmployee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}
	
	public IEmployeeService getEmployeeService() {
		return this.employeeService;
	}
	
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public List<MtEmployee> getAllEmployees() {
		return this.allEmployees;
	}
	
	
	/*
	 ***************************************	ACTION METHOD	***************************************	
	 */
	public void addEmployee() {
		this.getEmployeeService().save(this.currentEmployee);
	}
	
	public void reset() {
		this.currentEmployee = new MtEmployee();
	}
}
