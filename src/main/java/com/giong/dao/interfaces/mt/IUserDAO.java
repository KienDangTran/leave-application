package com.giong.dao.interfaces.mt;

import com.giong.dao.interfaces.IGenericDAO;
import com.giong.model.mt.MtUser;

public interface IUserDAO extends IGenericDAO<MtUser, Integer> {
	
	public MtUser loadUserByUsername(String username);
	
}
