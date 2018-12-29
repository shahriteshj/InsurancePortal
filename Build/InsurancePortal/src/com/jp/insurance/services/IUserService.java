package com.jp.insurance.services;

import com.jp.insurance.entities.User;
import com.jp.insurance.exceptions.InsuranceException;

public interface IUserService {

	public User changePassword(User user) throws InsuranceException;

	public User unLockAccount(User user) throws InsuranceException;

	public User getUserById(Long userId) throws InsuranceException;

	public User getUserByUserName(String username) throws InsuranceException;

	public User addUser(User user) throws InsuranceException;

	public User updateUser(User user) throws InsuranceException;
}
