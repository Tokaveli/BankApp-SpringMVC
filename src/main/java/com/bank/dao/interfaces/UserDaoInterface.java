package com.bank.dao.interfaces;

import java.util.List;

import com.bank.models.User;

public interface UserDaoInterface {

	public List<User> getUsers();
	
	public User getUser(int id);
	
	public void saveOrUpdateUser(User user);
	
	public void deleteUser(User user);
}