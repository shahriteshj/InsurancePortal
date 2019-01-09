package com.jp.insurance.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jp.insurance.daos.VehicleMasterDao;
import com.jp.insurance.daos.interfaces.IVehicleMasterDao;
import com.jp.insurance.entities.VehicleMaster;
import com.jp.insurance.exceptions.InsuranceException;
import com.jp.insurance.services.interfaces.IVehicleMasterService;

@Service("vehicleMasterService")
public class VehicleMasterService implements Serializable, IVehicleMasterService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -320999877190312009L;

	private IVehicleMasterDao vehicleMasterDao;

	public VehicleMasterService() throws InsuranceException {

	}

	@Autowired
	public VehicleMasterService(@Qualifier("vehicleMasterDao") VehicleMasterDao vehicleMasterDao)
			throws InsuranceException {
		this.vehicleMasterDao = vehicleMasterDao;
	}

	@Override
	public List<VehicleMaster> getVehiclesList() throws InsuranceException {
		return vehicleMasterDao.getVehiclesList();
	}

	@Override
	public List<String> getVehiclesMake() throws InsuranceException {
		return vehicleMasterDao.getVehiclesMake();
	}

	@Override
	public List<VehicleMaster> getVehiclesById(Long vehicleId) throws InsuranceException {
		return vehicleMasterDao.getVehiclesById(vehicleId);
	}

	@Override
	public List<VehicleMaster> getVehiclesByMake(String make) throws InsuranceException {
		return vehicleMasterDao.getVehiclesByMake(make);
	}

	@Override
	public List<VehicleMaster> getVehiclesByMakeAndModel(String make, String model) throws InsuranceException {
		return vehicleMasterDao.getVehiclesByMakeAndModel(make, model);
	}

	@Override
	public List<VehicleMaster> getVehiclesByMakeModelSubmodel(String make, String model, String submodel)
			throws InsuranceException {
		return vehicleMasterDao.getVehiclesByMakeModelSubmodel(make, model, submodel);
	}

	@Override
	public List<String> getVehicleModelByMake(String make) throws InsuranceException {
		return vehicleMasterDao.getVehicleModelByMake(make);
	}

	@Override
	public List<String> getVehicleSubmodelByMakeAndModel(String make, String model) throws InsuranceException {
		return vehicleMasterDao.getVehicleSubmodelByMakeAndModel(make, model);
	}

	@Override
	public String getVehicleCCByMakeModelSubmodel(String make, String model, String submodel)
			throws InsuranceException {
		return vehicleMasterDao.getVehicleCCByMakeModelSubmodel(make, model, submodel);
	}

	@Override
	public Float getVehiclePriceByMakeModelSubmodel(String make, String model, String submodel)
			throws InsuranceException {
		return vehicleMasterDao.getVehiclePriceByMakeModelSubmodel(make, model, submodel);
	}

}
