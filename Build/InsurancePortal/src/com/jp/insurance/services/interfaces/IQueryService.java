package com.jp.insurance.services.interfaces;

import java.util.List;

import com.jp.insurance.entities.Query;
import com.jp.insurance.exceptions.InsuranceException;

public interface IQueryService {

	public List<Query> getQueryList() throws InsuranceException;

	public List<Query> getQueryByEmailId(String emailId) throws InsuranceException;

	public Query getQueryById(Long queryId) throws InsuranceException;

	public List<Query> getQueryByRole(String assignedTo) throws InsuranceException;

	public List<Query> getQueryByStatus(String status) throws InsuranceException;

	public Query addNewQuery(Query queryObj) throws InsuranceException;

	public List<String> getRoleNameList() throws InsuranceException;

	public Query updateExistingQuery(Query queryObj) throws InsuranceException;

}
