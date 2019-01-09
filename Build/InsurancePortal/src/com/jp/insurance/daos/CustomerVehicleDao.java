package com.jp.insurance.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jp.insurance.daos.interfaces.ICustomerVehicleDao;
import com.jp.insurance.entities.CustomerVehicle;
import com.jp.insurance.exceptions.InsuranceException;

@Repository("customerVehicleDao")
public class CustomerVehicleDao implements Serializable, ICustomerVehicleDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2251599886731911576L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CustomerVehicle> getCustomerVehicleList() throws InsuranceException {
		String sql = "SELECT cv FROM CustomerVehicle cv";
		TypedQuery<CustomerVehicle> qry = entityManager.createQuery(sql, CustomerVehicle.class);
		List<CustomerVehicle> custVehicleList = qry.getResultList();
		return custVehicleList;
	}

	@Override
	public CustomerVehicle addCustomerVehicle(CustomerVehicle customerVehicle) throws InsuranceException {
		CustomerVehicle newCustomerVehicle = null;

		entityManager.persist(customerVehicle);
		entityManager.flush();
		if (customerVehicle.getVehicleId() > 0) {
			newCustomerVehicle = customerVehicle;
		}
		return newCustomerVehicle;
	}

	@Override
	public CustomerVehicle getCustomerVehicleDetails(Long customerId) throws InsuranceException {
		String sql = "SELECT cv FROM CustomerVehicle cv where customerId=" + customerId;
		TypedQuery<CustomerVehicle> qry = entityManager.createQuery(sql, CustomerVehicle.class);
		List<CustomerVehicle> custVehicleList = qry.getResultList();
		if (custVehicleList.isEmpty()) {
			return null;
		} else {
			CustomerVehicle customerVehicle = (CustomerVehicle) custVehicleList.get(0);
			return customerVehicle;
		}
	}
	
	
	@Override
	public CustomerVehicle getCustomerVehicleDetailsByVehicleId(Long vehicleId) throws InsuranceException {
		String sql = "SELECT cv FROM CustomerVehicle cv where vehicleId=" + vehicleId;
		TypedQuery<CustomerVehicle> qry = entityManager.createQuery(sql, CustomerVehicle.class);
		List<CustomerVehicle> custVehicleList = qry.getResultList();
		if (custVehicleList.isEmpty()) {
			return null;
		} else {
			CustomerVehicle customerVehicle = (CustomerVehicle) custVehicleList.get(0);
			return customerVehicle;
		}
	}
}
