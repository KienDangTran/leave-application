package com.giong.service.implement.mt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giong.dao.interfaces.IGenericDAO;
import com.giong.dao.interfaces.mt.IRoleDAO;
import com.giong.model.mt.MtRole;
import com.giong.service.implement.GenericServiceImpl;
import com.giong.service.interfaces.mt.IRoleService;

@Service("roleService")
@Transactional(readOnly = true)
public class RoleServiceImpl extends GenericServiceImpl<MtRole, String> implements IRoleService {
	
	private static final long serialVersionUID = 1L;
	
	private IRoleDAO roleDAO;
	
	
	/*
	 ***************************************	GETTER & SETTER	***************************************	
	 */
	public IRoleDAO getRoleDAO() {
		return this.roleDAO;
	}
	
	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
	
	/*
	 ***************************************	CONTRUCTORS	  ***************************************	
	 */
	public RoleServiceImpl() {
		super();
	}
	
	@Autowired
	public RoleServiceImpl(@Qualifier("roleDAO") IGenericDAO<MtRole, String> genericDAO) {
		super(genericDAO);
		this.roleDAO = (IRoleDAO) genericDAO;
	}
	
}
