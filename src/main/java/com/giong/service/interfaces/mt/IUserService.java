package com.giong.service.interfaces.mt;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.giong.model.mt.MtUser;
import com.giong.service.interfaces.IGenericService;

public interface IUserService extends IGenericService<MtUser, Integer>, UserDetailsService {
	
	MtUser getUserByUsername(String username);
	
}
