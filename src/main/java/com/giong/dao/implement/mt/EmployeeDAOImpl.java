package com.giong.dao.implement.mt;

import org.springframework.stereotype.Repository;

import com.giong.dao.implement.GenericDaoHibernateImpl;
import com.giong.dao.interfaces.mt.IEmployeeDAO;
import com.giong.model.mt.MtEmployee;

@Repository("employeeDAO")
public class EmployeeDAOImpl extends GenericDaoHibernateImpl<MtEmployee, String> implements IEmployeeDAO {

}
