package com.jp.insurance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jp.insurance.daos.interfaces.ICustomerDao;
import com.jp.insurance.daos.interfaces.ICustomerVehicleDao;
import com.jp.insurance.daos.interfaces.IPaymentDao;
import com.jp.insurance.daos.interfaces.IPolicyDao;
import com.jp.insurance.entities.Customer;
import com.jp.insurance.entities.CustomerVehicle;
import com.jp.insurance.entities.Payment;
import com.jp.insurance.entities.Policy;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IPolicyService;

@Service("policyService")
public class PolicyService implements IPolicyService {

	private IPolicyDao policyDao;
	private ICustomerDao customerDao;
	private ICustomerVehicleDao customerVehicleDao;
	private IPaymentDao paymentDao;

	public PolicyService() throws InsuranceException {
		System.out.println("In Policy Service Constructor");
	}

	@Autowired
	public PolicyService(@Qualifier("policyDao") IPolicyDao policyDao,
			@Qualifier("customerDao") ICustomerDao customerDao,
			@Qualifier("customerVehicleDao") ICustomerVehicleDao customerVehicleDao,
			@Qualifier("paymentDao") IPaymentDao paymentDao) throws InsuranceException {
		this.policyDao = policyDao;
		this.customerDao = customerDao;
		this.customerVehicleDao = customerVehicleDao;
		this.paymentDao = paymentDao;
	}

	@Override
	public List<Policy> getPolicyList(String emailId) throws InsuranceException {
		List<Policy> policyList = null;
		Customer customer = customerDao.getCustomerByEmailId(emailId);
		if (customer != null) {
			policyList = policyDao.getPolicyList(customer.getCustomerId());
		}
		return policyList;
	}

	@Override
	@Transactional
	public Policy addNewPolicy(String custEmail,Policy policy, CustomerVehicle customerVehicle, Payment payment)
			throws InsuranceException {

		if (custEmail==null || policy == null || customerVehicle == null || payment == null) {
			throw new InsuranceException("Error in saving Policy");
		}
		
		Customer customer = customerDao.getCustomerByEmailId(custEmail);
		
		if(customer==null){
			throw new InsuranceException("Error in fetching Customer Details");
		}
		
		customerVehicle.setCustomerId(customer.getCustomerId());

		CustomerVehicle newCustomerVehicle = customerVehicleDao.addCustomerVehicle(customerVehicle);
		if (newCustomerVehicle == null) {
			throw new InsuranceException("Error in saving Customer Vehicle Details");
		}else{
			policy.setVehicleId(newCustomerVehicle.getVehicleId());
		}

		Policy newPolicy = policyDao.addPolicy(policy);
		if (newPolicy == null) {
			throw new InsuranceException("Error in saving Policy Details");
		}else {
			payment.setPolicyId(newPolicy.getPolicyId());
		}

		Payment newPayment = paymentDao.addPayment(payment);
		if (newPayment == null) {
			throw new InsuranceException("Error in saving Payment Details");
		}

		return newPolicy;

	}

	@Override
	@Transactional
	public Policy addNewPolicy(Policy policy) throws InsuranceException {

		if (policy == null) {
			throw new InsuranceException("Error in saving Policy");
		}

		Policy newPolicy = policyDao.addPolicy(policy);
		if (newPolicy == null) {
			throw new InsuranceException("Error in saving Policy Details");
		}

		return newPolicy;

	}

}
