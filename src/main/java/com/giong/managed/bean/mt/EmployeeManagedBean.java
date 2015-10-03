package com.giong.managed.bean.mt;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.giong.model.mt.Mt_Employee;
import com.giong.service.interfaces.mt.IEmployeeService;

@ManagedBean(name = "employeeManagedBean")
@ViewScoped
public class EmployeeManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Mt_Employee currentEmployee = new Mt_Employee();
	
	@ManagedProperty(value = "#{employeeService}")
	IEmployeeService employeeService;
	
	/*
	 ***************************************	GETTER & SETTER	***************************************	
	 */
	
	public Mt_Employee getCurrentEmployee() {
		return this.currentEmployee;
	}
	
	public void setCurrentEmployee(Mt_Employee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}
	
	public IEmployeeService getEmployeeService() {
		return this.employeeService;
	}
	
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	/*
	 ***************************************	GETTER & SETTER		***************************************	
	 */
	
	public void addEmployee() {
		this.getEmployeeService().save(this.currentEmployee);
	}
	
	public void reset() {
		this.currentEmployee = new Mt_Employee();
	}
}
