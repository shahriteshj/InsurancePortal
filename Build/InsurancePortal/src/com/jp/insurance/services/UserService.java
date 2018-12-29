package com.jp.insurance.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jp.insurance.daos.IUserDao;
import com.jp.insurance.daos.UserDao;
import com.jp.insurance.entities.User;
import com.jp.insurance.exceptions.InsuranceException;

@Service("userService")
public class UserService implements Serializable, IUserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1579546557142523147L;

	private IUserDao userDao;;

	public UserService() throws InsuranceException {

	}

	@Autowired
	public UserService(@Qualifier("userDao") UserDao userDao) throws InsuranceException {
		this.userDao = userDao;
	}
	
	@Override
	@Transactional
	public User changePassword(User user) throws InsuranceException {
		return userDao.updateUser(user);
	}
	
	@Override
	@Transactional
	public User unLockAccount(User user) throws InsuranceException {
		return userDao.updateUser(user);
	}

	@Override
	public User getUserById(Long userId) throws InsuranceException {
		return userDao.getUserById(userId);
	}

	@Override
	public User getUserByUserName(String username) throws InsuranceException {
		return userDao.getUserByUserName(username);
	}

	@Override
	public User addUser(User user) throws InsuranceException {
		return userDao.addUser(user);
	}

	@Override
	public User updateUser(User user) throws InsuranceException {
		return userDao.updateUser(user);
	}
	
	
}
