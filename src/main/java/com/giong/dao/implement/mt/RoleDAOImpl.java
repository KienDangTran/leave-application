package com.giong.dao.implement.mt;

import org.springframework.stereotype.Repository;

import com.giong.dao.implement.GenericDaoHibernateImpl;
import com.giong.dao.interfaces.mt.IRoleDAO;
import com.giong.model.mt.MtRole;

@Repository("roleDAO")
public class RoleDAOImpl extends GenericDaoHibernateImpl<MtRole, String> implements IRoleDAO {

}
