package com.giong.managed.bean.mt;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.giong.model.mt.Mt_Employee;
import com.giong.service.interfaces.mt.IEmployeeService;

@ManagedBean(name = "employeeManagedBean")
public class EmployeeManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	
	@ManagedProperty(value = "#{employeeService}")
	IEmployeeService employeeService;
	private String employee_code;
	private String employee_name;
	private Date date_of_birth;
	private String email;
	private String phone_no;
	private List<Mt_Employee> employeeList;
	
	public IEmployeeService getEmployeeService() {
		return this.employeeService;
	}
	
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public String getEmployee_code() {
		return this.employee_code;
	}
	
	public void setEmployee_code(String employee_code) {
		this.employee_code = employee_code;
	}
	
	public String getEmployee_name() {
		return this.employee_name;
	}
	
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	
	public Date getDate_of_birth() {
		return this.date_of_birth;
	}
	
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getPhone_no() {
		return this.phone_no;
	}
	
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	
	public List<Mt_Employee> getEmployeeList() {
		return this.employeeList;
	}
	
	public void setEmployeeList(List<Mt_Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	public String addEmployee() {
		try {
			final Mt_Employee employee = new Mt_Employee();
			employee.setEmployee_code(this.getEmployee_code());
			employee.setEmployee_name(this.getEmployee_name());
			employee.setDate_of_birth(this.getDate_of_birth());
			employee.setEmail(this.getEmail());
			employee.setPhone_no(this.getPhone_no());
			this.getEmployeeService().save(employee);
			return EmployeeManagedBean.SUCCESS;
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
		return EmployeeManagedBean.ERROR;
	}
	
	public void reset() {
		this.setEmployee_code(null);
		this.setEmployee_name(null);
		this.setDate_of_birth(null);
		this.setEmail(null);
		this.setPhone_no(null);
	}
}
