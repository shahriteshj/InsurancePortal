package com.jp.insurance.daos.interfaces;

import java.util.List;

import com.jp.insurance.entities.Customer;
import com.jp.insurance.exceptions.InsuranceException;

public interface ICustomerDao {

	public List<Customer> getCustomerList() throws InsuranceException;

	public Customer getCustomerByEmailId(String emailId) throws InsuranceException;

	public Customer getCustomerById(Long customerId) throws InsuranceException;

	public Customer addCustomer(Customer customer) throws InsuranceException;
}
