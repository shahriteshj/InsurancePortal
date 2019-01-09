package com.jp.insurance.services.interfaces;

import com.jp.insurance.entities.Customer;
import com.jp.insurance.entities.Role;
import com.jp.insurance.entities.User;
import com.jp.insurance.exceptions.InsuranceException;

public interface IUserService {

	public User changePassword(User user) throws InsuranceException;

	public User unLockAccount(User user) throws InsuranceException;

	public User getUserById(Long userId) throws InsuranceException;

	public User getUserByUserName(String username) throws InsuranceException;

	public User addUser(User user, Customer customer) throws InsuranceException;

	public User updateUser(User user) throws InsuranceException;

	public String getRoleById(Integer roleId) throws InsuranceException;

	public Role getRoleByName(String roleName) throws InsuranceException;

	public User authenticateUser(String username, String password) throws InsuranceException;

	public Customer getCustomerByEmailId(String emailId) throws InsuranceException;
	
	public User checkUserExists(String username) throws InsuranceException;
}
