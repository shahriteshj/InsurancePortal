package com.jp.insurance.services;

import java.util.List;

import com.jp.insurance.entities.CustomerVehicle;
import com.jp.insurance.entities.Payment;
import com.jp.insurance.entities.Policy;
import com.jp.insurance.exceptions.InsuranceException;

public interface IPolicyService {

	public List<Policy> getPolicyList(String enailId) throws InsuranceException;

	public Policy addNewPolicyToExistingCustomer(Policy policy, CustomerVehicle customerVehicle, Payment payment)
			throws InsuranceException;

	public Policy addNewPolicy(Policy policy) throws InsuranceException;

}
