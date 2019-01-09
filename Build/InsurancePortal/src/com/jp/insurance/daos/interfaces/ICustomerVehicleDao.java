package com.jp.insurance.daos.interfaces;

import java.util.List;

import com.jp.insurance.entities.CustomerVehicle;
import com.jp.insurance.exceptions.InsuranceException;

public interface ICustomerVehicleDao {

	public List<CustomerVehicle> getCustomerVehicleList() throws InsuranceException;

	public CustomerVehicle addCustomerVehicle(CustomerVehicle customerVehicle) throws InsuranceException;

	public CustomerVehicle getCustomerVehicleDetails(Long customerId) throws InsuranceException;
	
	public CustomerVehicle getCustomerVehicleDetailsByVehicleId(Long vehicleId) throws InsuranceException;

}
