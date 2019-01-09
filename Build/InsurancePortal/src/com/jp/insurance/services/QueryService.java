package com.jp.insurance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jp.insurance.daos.interfaces.IQueryDao;
import com.jp.insurance.daos.interfaces.IRoleDao;
import com.jp.insurance.entities.Query;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IQueryService;

@Service("queryService")
public class QueryService implements IQueryService {

	private IQueryDao queryDao;
	private IRoleDao roleDao;

	public QueryService() {

	}

	@Autowired
	public QueryService(@Qualifier("queryDao") IQueryDao queryDao, @Qualifier("roleDao") IRoleDao roleDao) {
		System.out.println("In side Query side constructor");
		this.queryDao = queryDao;
		this.roleDao = roleDao;
	}

	@Override
	public List<Query> getQueryList() throws InsuranceException {
		return queryDao.getQueryList();
	}

	@Override
	public List<Query> getQueryByEmailId(String emailId) throws InsuranceException {
		System.out.println("In QueryService metho");
		return queryDao.getQueryByEmailId(emailId);
	}

	@Override
	public Query getQueryById(Long queryId) throws InsuranceException {
		System.out.println("In ServiceImpl : getQueryById()");
		return queryDao.getQueryById(queryId);
	}

	@Override
	public List<Query> getQueryByRole(String assignedTo) throws InsuranceException {
		return queryDao.getQueryByRole(assignedTo);
	}

	@Override
	public List<Query> getQueryByStatus(String status) throws InsuranceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Query addNewQuery(Query queryObj) throws InsuranceException {
		return queryDao.addNewQuery(queryObj);
	}

	@Override
	public List<String> getRoleNameList() throws InsuranceException {
		return roleDao.getRoleNameList();

	}

	@Override
	@Transactional
	public Query updateExistingQuery(Query queryObj) throws InsuranceException {
		Query queryNew = getQueryById(queryObj.getQueryId());
		queryNew.setAssignedTo(queryObj.getAssignedTo());
		queryNew.setQueryResponse(queryObj.getQueryResponse());
		queryNew.setStatus(queryObj.getStatus());
		return queryDao.updateExistingQuery(queryNew);
	}
}
