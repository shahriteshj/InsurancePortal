package com.jp.insurance.daos.interfaces;

import java.util.List;

import com.jp.insurance.entities.VehicleMaster;
import com.jp.insurance.exceptions.InsuranceException;

public interface IVehicleMasterDao {
	
	public List<VehicleMaster> getVehiclesList() throws InsuranceException;
	
	public List<VehicleMaster> getVehiclesById(Long vehicleId) throws InsuranceException;
	
	public List<VehicleMaster> getVehiclesByMake(String make) throws InsuranceException;

	public List<VehicleMaster> getVehiclesByMakeAndModel(String make, String model) throws InsuranceException;

	public List<VehicleMaster> getVehiclesByMakeModelSubmodel(String make, String model, String submodel) throws InsuranceException;
	
	public List<String> getVehicleModelByMake(String make) throws InsuranceException;
	
	public List<String> getVehicleSubmodelByMakeAndModel(String make, String model) throws InsuranceException;
	
	public String getVehicleCCByMakeModelSubmodel(String make, String model, String submodel) throws InsuranceException;
	
	public Float getVehiclePriceByMakeModelSubmodel(String make, String model, String submodel) throws InsuranceException;
}
