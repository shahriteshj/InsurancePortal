package com.jp.insurance.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jp.insurance.daos.interfaces.IRoleDao;
import com.jp.insurance.entities.Role;
import com.jp.insurance.exceptions.InsuranceException;

@Repository("roleDao")
public class RoleDao implements Serializable, IRoleDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1395374604844166104L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Role> getRoleList() throws InsuranceException {
		String sql = "SELECT r FROM Role r";
		TypedQuery<Role> qry = entityManager.createQuery(sql, Role.class);
		List<Role> roleList = qry.getResultList();
		return roleList;
		
	}
	
	@Override
	public List<String> getRoleNameList() throws InsuranceException {
		String sql = "SELECT r.name FROM Role r";
		TypedQuery<String> qry = entityManager.createQuery(sql, String.class);
		List<String> roleNameList = qry.getResultList();
		return roleNameList;
		
	}

	@Override
	public Role getRoleIdByName(String name) throws InsuranceException {
		String sql = "SELECT r FROM Role r where name=:name";
		TypedQuery<Role> qry = entityManager.createQuery(sql, Role.class);
		qry.setParameter("name", name);
		Role role = qry.getSingleResult();
		return role;
	}

	@Override
	public Role getRoleNameById(Integer roleId) throws InsuranceException {
		String sql = "SELECT r FROM Role r where roleId=:roleId";
		TypedQuery<Role> qry = entityManager.createQuery(sql, Role.class);
		qry.setParameter("roleId", roleId);
		Role role = qry.getSingleResult();
		return role;
	}

}
