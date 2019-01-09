package com.jp.insurance.services.interfaces;

import java.util.List;

import com.jp.insurance.entities.Customer;
import com.jp.insurance.entities.CustomerVehicle;
import com.jp.insurance.entities.Payment;
import com.jp.insurance.entities.Policy;
import com.jp.insurance.exceptions.InsuranceException;

public interface IPolicyService {

	public List<Policy> getPolicyList(String enailId) throws InsuranceException;

	public List<Policy> getPolicyList() throws InsuranceException;

	public Policy addNewPolicy(String custEmail, Policy policy, CustomerVehicle customerVehicle, Payment payment)
			throws InsuranceException;

	public Policy addNewPolicy(Policy policy) throws InsuranceException;

	public Policy getPolicyDetails(Long policyId) throws InsuranceException;

	public CustomerVehicle getCustomerVehicleDetails(Long customerId) throws InsuranceException;

	public Payment getPolicyPaymentDetails(Long policyId) throws InsuranceException;

	public Customer getCustomerDetails(Long customerId) throws InsuranceException;

}
