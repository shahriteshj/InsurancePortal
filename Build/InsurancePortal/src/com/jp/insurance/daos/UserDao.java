package com.jp.insurance.daos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jp.insurance.entities.User;
import com.jp.insurance.exceptions.InsuranceException;

@Repository("userDao")
public class UserDao implements Serializable, IUserDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6945600290811655543L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> getUserList() throws InsuranceException {
		String sql = "SELECT u FROM User u";
		TypedQuery<User> qry = entityManager.createQuery(sql, User.class);
		List<User> userList = qry.getResultList();
		return userList;
	}

	@Override
	public User getUserByUserName(String username) throws InsuranceException {
		String sql = "SELECT u FROM User u where username=:username";
		TypedQuery<User> qry = entityManager.createQuery(sql, User.class);
		qry.setParameter("username", username);
		User user = qry.getSingleResult();
		return user;
	}

	@Override
	public User getUserById(Long userId) throws InsuranceException {
		String sql = "SELECT u FROM User u where userId=:userId";
		TypedQuery<User> qry = entityManager.createQuery(sql, User.class);
		qry.setParameter("userId", userId);
		User user = qry.getSingleResult();
		return user;
	}

	@Override
	public User addUser(User user) throws InsuranceException {
		User newUser = null;

		entityManager.persist(user);
		if (user.getUserId() > 0) {
			newUser = user;
		}
		return newUser;
	}

	@Override
	public User updateUser(User user) throws InsuranceException {
		User updateUser= entityManager.find(User.class,user.getUserId());
		updateUser.setAccountLocked(user.getAccountLocked());
		updateUser.setCreationDate(user.getCreationDate());
		updateUser.setFailedLoginAttempt(user.getFailedLoginAttempt());
		updateUser.setLastSuccessfulLoginDate(user.getLastSuccessfulLoginDate());
		updateUser.setPassword(user.getPassword());
		updateUser.setQuestionId(user.getQuestionId());
		updateUser.setRoleId(user.getRoleId());
		updateUser.setSecurityAnswer(user.getSecurityAnswer());
		updateUser.setLastModifiedDate(new Date());
		entityManager.flush();
		return updateUser;
	}


}
