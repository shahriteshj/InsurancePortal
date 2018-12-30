package com.jp.insurance.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.jp.insurance.entities.Query;
import com.jp.insurance.exceptions.InsuranceException;

@Repository("queryDao")
public class QueryDao implements IQueryDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Query> getQueryList() throws InsuranceException {
		String sql = "SELECT q FROM Query_Record q"; 
		TypedQuery<Query> qry = entityManager.createQuery(sql, Query.class);	
		List<Query> queryList = qry.getResultList();
		System.out.println("In getQueryList() dao :" + queryList);
		return queryList;
		
	}
	
	@Override
	public List<Query> getQueryByEmailId(String emailId) throws InsuranceException {
		String sql = "SELECT q FROM Query_Record q where EMAILID = '" + emailId +"'";
		TypedQuery<Query> qry = entityManager.createQuery(sql, Query.class);
		List<Query> queryList = qry.getResultList();		
		return queryList;
	}

	@Override
	public Query getQueryById(Long queryId) throws InsuranceException {
		System.out.println("In DAOImpl : getQueryById()");
		return entityManager.find(Query.class, queryId);
	}

	@Override
	public List<Query> getQueryByRole(String assignedTo) throws InsuranceException {
		String sql = "SELECT q FROM Query_Record q where ASSIGNEDTO = '" + assignedTo + "'";
		TypedQuery<Query> qry = entityManager.createQuery(sql, Query.class);
		List<Query> queryList = qry.getResultList();
		return queryList;
		
	}

	@Override
	public List<Query> getQueryByStatus(String status) throws InsuranceException {
		String sql = "SELECT q FROM Query_Record q where STATUS = '" + status + "'";
		TypedQuery<Query> qry = entityManager.createQuery(sql, Query.class);
		List<Query> queryList = qry.getResultList();
		return queryList;
	}

	@Override
	public boolean addNewQuery(Query queryObj) throws InsuranceException {
		entityManager.persist(queryObj);
		
		Query queryNewObj = entityManager.find(Query.class, queryObj.getQueryId());
		System.out.println(queryNewObj);
		if(queryNewObj != null) {
			return true;
		} else {
		
		return false;
	}

}

}
