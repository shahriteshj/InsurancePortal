package com.jp.insurance.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jp.insurance.daos.interfaces.IStateCityDao;
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
	public List<String> getCityList() throws InsuranceException {
		String sql = "SELECT c.name FROM Cities c";
		TypedQuery<String> qry = entityManager.createQuery(sql, String.class);
		List<String> cityList = qry.getResultList();
		return cityList;

	}

	@Override
	public List<String> getStateList() throws InsuranceException {
		String sql = "SELECT s.name FROM States s";
		TypedQuery<String> qry = entityManager.createQuery(sql, String.class);
		List<String> stateList = qry.getResultList();
		return stateList;
	}

	@Override
	public List<String> getCityListbyStateName(String stateName) throws InsuranceException {
		String sql = "SELECT c.name FROM Cities c, States s where s.name='" + stateName + "' and c.stateId=s.id";
		TypedQuery<String> qry = entityManager.createQuery(sql, String.class);
		List<String> cityList = qry.getResultList();
		return cityList;

	}

	public List<Cities> getCityListByStateId(Integer stateId) throws InsuranceException {
		String sql = "SELECT c FROM Cities c WHERE c.state.id = :stateId";
		TypedQuery<Cities> qry = entityManager.createQuery(sql, Cities.class);
		qry.setParameter("stateId", stateId);
		List<Cities> cityList = qry.getResultList();
		return cityList;
	}

	@Override
	public States getStateId(String stateName) throws InsuranceException {
		String sql = "SELECT s FROM States s WHERE s.name=:stateName";
		TypedQuery<States> qry = entityManager.createQuery(sql, States.class);
		qry.setParameter("stateName", stateName);
		States state = qry.getSingleResult();
		return state;
	}

}
