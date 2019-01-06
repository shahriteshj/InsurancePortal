package com.jp.insurance.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jp.insurance.daos.interfaces.IPolicyDao;
import com.jp.insurance.entities.Policy;
import com.jp.insurance.exceptions.InsuranceException;

@Repository("policyDao")
public class PolicyDao implements Serializable, IPolicyDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1594141369248758365L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Policy> getPolicyList() throws InsuranceException {
		String sql = "SELECT p FROM Policy p";
		TypedQuery<Policy> qry = entityManager.createQuery(sql, Policy.class);
		List<Policy> policyList = qry.getResultList();
		return policyList;
	}

	@Override
	public List<Policy> getPolicyList(Long customerId) throws InsuranceException {
		String sql = "SELECT p FROM Policy p where customerId = " + customerId;
		TypedQuery<Policy> qry = entityManager.createQuery(sql, Policy.class);
		List<Policy> policyList = qry.getResultList();
		return policyList;
	}

	
	@Override
	public Policy getPolicyDetails(Long policyId) throws InsuranceException {
		String sql = "SELECT p FROM Policy p where policyId = " + policyId;
		TypedQuery<Policy> qry = entityManager.createQuery(sql, Policy.class);
		List<Policy> policyList = qry.getResultList();
		if (policyList.isEmpty()) {
			return null;
		} else {
			return policyList.get(0);
		}
	}

	
	@Override
	public Policy addPolicy(Policy policy) throws InsuranceException {
		Policy newPolicy = null;
		System.out.println(policy);
		entityManager.persist(policy);
		System.out.println(policy);
		// entityManager.flush();
		if (policy.getPolicyId() > 0) {
			newPolicy = policy;
		}
		return newPolicy;
	}

}
