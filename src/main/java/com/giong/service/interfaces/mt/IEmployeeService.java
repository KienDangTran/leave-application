package com.giong.service.interfaces.mt;

import java.util.List;

import com.giong.model.mt.Mt_Employee;

public interface IEmployeeService {

	public void addEmployee(Mt_Employee employee);

	public void updateEmployee(Mt_Employee employee);

	public void deleteEmployee(Mt_Employee employee);

	public Mt_Employee getEmployeeByCode(String employeeCode);

	public List<Mt_Employee> getEmployeeList();
}
