package com.jp.insurance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jp.insurance.daos.IQueryDao;
import com.jp.insurance.entities.Query;
import com.jp.insurance.exceptions.InsuranceException;

@Service("queryService")
public class QueryService implements IQueryService {
	
	private IQueryDao queryDao;
	
		
	public QueryService() {
		
	}

	
	@Autowired
	public QueryService(@Qualifier("queryDao") IQueryDao queryDao) {
		System.out.println("In side Query side constructor");
		this.queryDao = queryDao;
	}

	@Override
	public List<Query> getQueryList() throws InsuranceException {		
		return queryDao.getQueryList();
	}


	@Override
	public List<Query> getQueryByEmailId(String emailId) throws InsuranceException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Query getQueryById(Long queryId) throws InsuranceException {
		System.out.println("In ServiceImpl : getQueryById()");
		return queryDao.getQueryById(queryId);
	}


	@Override
	public List<Query> getQueryByRole(String assignedTo) throws InsuranceException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Query> getQueryByStatus(String status) throws InsuranceException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@Transactional
	public boolean addNewQuery(Query queryObj) throws InsuranceException {
		return queryDao.addNewQuery(queryObj);	
		 
	}
}
