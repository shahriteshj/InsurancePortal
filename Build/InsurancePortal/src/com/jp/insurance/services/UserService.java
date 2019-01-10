package com.jp.insurance.services;

import java.io.Serializable;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jp.insurance.daos.CustomerDao;
import com.jp.insurance.daos.RoleDao;
import com.jp.insurance.daos.UserDao;
import com.jp.insurance.daos.interfaces.ICustomerDao;
import com.jp.insurance.daos.interfaces.IRoleDao;
import com.jp.insurance.daos.interfaces.IUserDao;
import com.jp.insurance.entities.Customer;
import com.jp.insurance.entities.Role;
import com.jp.insurance.entities.User;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IUserService;

@Service("userService")
public class UserService implements Serializable, IUserService {

	private static final long serialVersionUID = 1579546557142523147L;

	private IUserDao userDao;
	private IRoleDao roleDao;
	private ICustomerDao customerDao;

	public UserService() throws InsuranceException {

	}

	@Autowired
	public UserService(@Qualifier("userDao") UserDao userDao, @Qualifier("roleDao") RoleDao roleDao,
			@Qualifier("customerDao") CustomerDao customerDao) throws InsuranceException {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.customerDao = customerDao;
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
	@Transactional
	public User addUser(User user, Customer customer) throws InsuranceException {
		User newUser = userDao.addUser(user);
		customerDao.addCustomer(customer);
		return newUser;
	}

	@Override
	public User updateUser(User user) throws InsuranceException {
		return userDao.updateUser(user);
	}

	@Override
	@Transactional
	public User authenticateUser(String username, String password) throws InsuranceException {
		User user = userDao.getUserByUserName(username);
		if (user != null) {
			if (password.equals(user.getPassword())) { // check password
				if ("Y".equals(user.getAccountLocked())) { // check if account
															// locked
					// Account Locked
					user.setResponseText("Account Locked");
				} else {
					// successful Login
					user.setLastSuccessfulLoginDate(Calendar.getInstance().getTime());
					user.setFailedLoginAttempt(0);
					user.setResponseText("SUCCESS");
					userDao.updateUser(user);
					return user;
				}
			} else {
				// password invalid
				user.setFailedLoginAttempt(user.getFailedLoginAttempt() + 1);
				user.setResponseText("INVALID PASSWORD");
				if (user.getFailedLoginAttempt() >= 5) {
					user.setAccountLocked('Y');
				}
				userDao.updateUser(user);
				return user;
			}
		}
		return null;
	}

	@Override
	public String getRoleById(Integer roleId) throws InsuranceException {
		return roleDao.getRoleNameById(roleId);
	}

	@Override
	public Customer getCustomerByEmailId(String emailId) throws InsuranceException {
		return customerDao.getCustomerByEmailId(emailId);
	}

	@Override
	public Role getRoleByName(String roleName) throws InsuranceException {
		return roleDao.getRoleIdByName(roleName);
	}

	@Override
	public User checkUserExists(String username) throws InsuranceException {
		
		return userDao.getUserByUserName(username);
	}

	


	
	

}
