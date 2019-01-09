package com.jp.insurance.daos.interfaces;

import java.util.List;

import com.jp.insurance.entities.User;
import com.jp.insurance.exceptions.InsuranceException;

public interface IUserDao {

	public List<User> getUserList() throws InsuranceException;

	public User getUserByUserName(String username) throws InsuranceException;

	public User getUserById(Long userId) throws InsuranceException;

	public User addUser(User user) throws InsuranceException;

	public User updateUser(User user) throws InsuranceException;

	// public User authenticateUser(String username, String password) throws
	// InsuranceException;

}
