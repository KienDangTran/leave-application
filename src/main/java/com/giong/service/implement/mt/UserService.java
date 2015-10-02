package com.giong.service.implement.mt;

import org.springframework.transaction.annotation.Transactional;

import com.giong.dao.interfaces.mt.IUserDAO;
import com.giong.model.mt.Mt_User;
import com.giong.service.interfaces.mt.IUserService;

@Transactional(readOnly = true)
public class UserService implements IUserService {

	private IUserDAO userDAO;

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public void addUser(Mt_User user) {
		this.getUserDAO().addUser(user);
	}

	@Override
	public void updateUser(Mt_User user) {
		this.getUserDAO().updateUser(user);
	}

	@Override
	public void deleteUser(Mt_User user) {
		this.getUserDAO().deleteUser(user);
	}

	@Override
	public Mt_User getUserByID(int userID) {
		return this.getUserDAO().getUserByID(userID);
	}

	@Override
	public void getUserList() {
		this.getUserDAO().getUserList();
	}

}
