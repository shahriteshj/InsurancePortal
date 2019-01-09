package com.jp.insurance.daos.interfaces;

import java.util.List;

import com.jp.insurance.entities.Role;
import com.jp.insurance.exceptions.InsuranceException;

public interface IRoleDao {

	public List<Role> getRoleList() throws InsuranceException;

	public List<String> getRoleNameList() throws InsuranceException;

	public Role getRoleIdByName(String name) throws InsuranceException;

	public String getRoleNameById(Integer roleId) throws InsuranceException;
}
