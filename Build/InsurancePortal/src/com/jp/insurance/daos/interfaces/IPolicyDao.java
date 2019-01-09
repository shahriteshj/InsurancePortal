package com.jp.insurance.daos.interfaces;

import java.util.List;

import com.jp.insurance.entities.Policy;
import com.jp.insurance.exceptions.InsuranceException;

public interface IPolicyDao {

	public List<Policy> getPolicyList() throws InsuranceException;

	public List<Policy> getPolicyList(Long customerId) throws InsuranceException;

	public Policy addPolicy(Policy policy) throws InsuranceException;

	public Policy getPolicyDetails(Long policyId) throws InsuranceException;

}
