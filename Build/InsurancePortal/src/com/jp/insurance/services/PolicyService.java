package com.jp.insurance.services;

import java.util.Calendar;
import java.util.Date;
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
	public List<Policy> getPolicyList() throws InsuranceException {
		List<Policy> policyList = null;
		policyList = policyDao.getPolicyList();
		return policyList;
	}

	@Override
	@Transactional(rollbackFor = InsuranceException.class)
	public Policy addNewPolicy(String custEmail, Policy policy, CustomerVehicle customerVehicle, Payment payment)
			throws InsuranceException {

		if (custEmail == null || policy == null || customerVehicle == null || payment == null) {
			throw new InsuranceException("Error in saving Policy");
		}

		Customer customer = customerDao.getCustomerByEmailId(custEmail.toUpperCase());

		if (customer == null) {
			throw new InsuranceException("Error in fetching Customer Details");
		}

		customerVehicle.setCustomerId(customer.getCustomerId());
		policy.setCustomerId(customer.getCustomerId());
		CustomerVehicle newCustomerVehicle = customerVehicleDao.addCustomerVehicle(customerVehicle);
		if (newCustomerVehicle == null) {
			throw new InsuranceException("Error in saving Customer Vehicle Details");
		} else {
			policy.setVehicleId(newCustomerVehicle.getVehicleId());
		}

		Policy newPolicy = policyDao.addPolicy(policy);
		if (newPolicy == null) {
			throw new InsuranceException("Error in saving Policy Details");
		} else {
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

	@Override
	public CustomerVehicle getCustomerVehicleDetails(Long customerId) throws InsuranceException {

		return customerVehicleDao.getCustomerVehicleDetails(customerId);

	}
	
	@Override
	public CustomerVehicle getCustomerVehicleDetailsByVehicleId(Long vehicleId) throws InsuranceException {
		return customerVehicleDao.getCustomerVehicleDetailsByVehicleId(vehicleId);
	}

	@Override
	public Payment getPolicyPaymentDetails(Long policyId) throws InsuranceException {

		return paymentDao.getPaymentDetails(policyId);

	}

	@Override
	public Policy getPolicyDetails(Long policyId) throws InsuranceException {
		return policyDao.getPolicyDetails(policyId);
	}

	@Override
	public Customer getCustomerDetails(Long customerId) throws InsuranceException {
		return customerDao.getCustomerById(customerId);
	}

	@Override
	@Transactional(rollbackFor = InsuranceException.class)
	public Policy renewPolicy(String custEmail, Long currentPolicyId,Policy policy, Payment payment) throws InsuranceException {
		
		if (custEmail == null || currentPolicyId == null || payment == null) {
			throw new InsuranceException("Error in saving Policy");
		}
		
		Policy currentPolicy = policyDao.getPolicyDetails(currentPolicyId);
		
		if(currentPolicy== null){
			throw new InsuranceException("Error in getting current Policy");
		}
		
		CustomerVehicle customerVehicle = customerVehicleDao.getCustomerVehicleDetailsByVehicleId(currentPolicy.getVehicleId());
		
		if(customerVehicle== null){
			throw new InsuranceException("Error in getting vehicle Details for PolicyId: " + currentPolicy.getPolicyId());
		}
		
		policy.setCustomerId(currentPolicy.getCustomerId());
		policy.setVehicleId(currentPolicy.getVehicleId());
		
		Date currentPolEndDate = currentPolicy.getPolicyEndDate();
		Calendar c = Calendar.getInstance();
		c.setTime(currentPolEndDate);
		c.add(Calendar.DAY_OF_MONTH, +1);
		policy.setPolicyStartDate(c.getTime());
		
		c.add(Calendar.YEAR, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		policy.setPolicyEndDate(c.getTime());
		
		currentPolicy.setStatus("RENEWED");
		
		policyDao.updatePolicy(currentPolicy);
				
		policyDao.addPolicy(policy);
		
		if (policy.getPolicyId() >0) {
			payment.setPolicyId(policy.getPolicyId());
		} else {
			throw new InsuranceException("Error in saving new Policy");
		}
		
		paymentDao.addPayment(payment);
		
		if (payment.getPaymentId()<=0) {
			throw new InsuranceException("Error in saving Payment Details");
		}
		
		
		return policy;
	}

}
