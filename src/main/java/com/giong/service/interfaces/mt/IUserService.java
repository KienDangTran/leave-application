package com.giong.service.interfaces.mt;

import com.giong.model.mt.Mt_User;

public interface IUserService {

	public void addUser(Mt_User user);

	public void updateUser(Mt_User user);

	public void deleteUser(Mt_User user);

	public Mt_User getUserByID(int userID);

	public void getUserList();
}
