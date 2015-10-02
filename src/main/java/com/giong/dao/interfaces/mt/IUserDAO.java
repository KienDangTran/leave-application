package com.giong.dao.interfaces.mt;

import java.util.List;

import com.giong.model.mt.Mt_User;

public interface IUserDAO {

	public void addUser(Mt_User user);

	public void updateUser(Mt_User user);

	public void deleteUser(Mt_User user);

	public Mt_User getUserByID(int userID);

	public List<Mt_User> getUserList();
}
