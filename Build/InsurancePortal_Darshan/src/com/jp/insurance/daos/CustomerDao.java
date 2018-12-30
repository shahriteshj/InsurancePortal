package com.jp.insurance.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jp.insurance.entities.Customer;
import com.jp.insurance.exceptions.InsuranceException;

@Repository("customerDao")
public class CustomerDao implements Serializable, ICustomerDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1594141369248758365L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Customer> getCustomerList() throws InsuranceException {
		String sql = "SELECT c FROM Customer c";
		TypedQuery<Customer> qry = entityManager.createQuery(sql, Customer.class);
		List<Customer> custList = qry.getResultList();
		return custList;
	}

	public Customer getCustomerByEmailId(String emailId) throws InsuranceException {
		String sql = "SELECT c FROM Customer c where emailId = '" + emailId + "'";
		TypedQuery<Customer> qry = entityManager.createQuery(sql, Customer.class);
		Customer customer = (Customer) qry.getResultList();
		return customer;
	}

	@Override
	public Customer addCustomer(Customer customer) throws InsuranceException {
		Customer newCustomer = null;

		entityManager.persist(customer);
		entityManager.flush();
		if (customer.getCustomerId() > 0) {
			newCustomer = customer;
		}
		return newCustomer;
	}

	@Override
	public Customer getCustomerById(Long customerId) throws InsuranceException {
		String sql = "SELECT c FROM Customer c where customerId = '" + customerId + "'";
		TypedQuery<Customer> qry = entityManager.createQuery(sql, Customer.class);
		Customer customer = (Customer) qry.getResultList();
		return customer;	}
}
