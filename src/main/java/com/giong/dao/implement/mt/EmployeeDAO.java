package com.giong.dao.implement.mt;

import java.util.List;

import org.hibernate.SessionFactory;

import com.giong.dao.interfaces.mt.IEmployeeDAO;
import com.giong.model.mt.Mt_Employee;

public class EmployeeDAO implements IEmployeeDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addEmployee(Mt_Employee employee) {
		this.getSessionFactory().getCurrentSession().save(employee);
	}

	@Override
	public void updateEmployee(Mt_Employee employee) {
		this.getSessionFactory().getCurrentSession().update(employee);
	}

	@Override
	public void deleteEmployee(Mt_Employee employee) {
		this.getSessionFactory().getCurrentSession().delete(employee);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Mt_Employee getEmployeeByCode(String employeeCode) {
		List<Mt_Employee> emps = this.getSessionFactory().getCurrentSession().createQuery("FROM Mt_Employee WHERE employee_code = ? ").setParameter(0, employeeCode).list();
		return emps.size() == 0 ? null : emps.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mt_Employee> getEmployeeList() {
		List<Mt_Employee> emps = this.getSessionFactory().getCurrentSession().createQuery("FROM Mt_Employee ").list();
		return emps;
	}

}
