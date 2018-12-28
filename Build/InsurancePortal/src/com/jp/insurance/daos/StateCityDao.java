package com.jp.insurance.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jp.insurance.entities.Cities;
import com.jp.insurance.entities.States;
import com.jp.insurance.exceptions.InsuranceException;

@Repository("stateCityDao")
public class StateCityDao implements Serializable, IStateCityDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4258796837580920309L;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Cities> getCityList() throws InsuranceException {
		String sql = "SELECT c FROM Cities c";
		TypedQuery<Cities> qry = entityManager.createQuery(sql, Cities.class);
		List<Cities> cityList = qry.getResultList();
		return cityList;
		
	}
	
	@Override
	public List<States> getStateList() throws InsuranceException {
		String sql = "SELECT s FROM States s";
		TypedQuery<States> qry = entityManager.createQuery(sql, States.class);
		List<States> stateList = qry.getResultList();
		return stateList;
	}

	public List<Cities> getCityListByStateId(Integer stateId) throws InsuranceException {
		String sql = "SELECT c FROM Cities c WHERE c.state.id = :stateId";
		TypedQuery<Cities> qry =entityManager.createQuery(sql, Cities.class);
		qry.setParameter("stateId", stateId);
		List<Cities> cityList = qry.getResultList();
		return cityList;
	}

	@Override
	public States getStateId(String stateName) throws InsuranceException {
		String sql = "SELECT s FROM States s WHERE s.name=:stateName";
		TypedQuery<States> qry =entityManager.createQuery(sql, States.class);
		qry.setParameter("stateName", stateName);
		States state = qry.getSingleResult();
		return state;
	}
}
