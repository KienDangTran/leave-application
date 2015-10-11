package com.giong.service.interfaces.mt;

import com.giong.model.mt.MtUser;
import com.giong.service.interfaces.IGenericService;

public interface IUserService extends IGenericService<MtUser, Integer> {
	
	MtUser getUserByUsername(String username);
	
}
