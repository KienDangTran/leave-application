package com.giong.dao.implement.mt;

import org.springframework.stereotype.Repository;

import com.giong.dao.implement.GenericDaoHibernateImpl;
import com.giong.dao.interfaces.mt.IPermissonDAO;
import com.giong.model.mt.MtPermission;

@Repository("permissonDAO")
public class PermissonDAOImpl extends GenericDaoHibernateImpl<MtPermission, String> implements IPermissonDAO {

}
