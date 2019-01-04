package com.jp.insurance.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jp.insurance.daos.interfaces.IVehicleMasterDao;
import com.jp.insurance.entities.VehicleMaster;
import com.jp.insurance.exceptions.InsuranceException;


@Repository("vehicleMasterDao")
public class VehicleMasterDao implements Serializable, IVehicleMasterDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6162835877237532393L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<VehicleMaster> getVehiclesList() throws InsuranceException {
		String sql = "SELECT v FROM VehicleMaster v";
		TypedQuery<VehicleMaster> qry = entityManager.createQuery(sql, VehicleMaster.class);
		List<VehicleMaster> vehicleMasterList = qry.getResultList();
		return vehicleMasterList;
	}

	@Override
	public List<VehicleMaster> getVehiclesById(Long vehicleId) throws InsuranceException {
		String sql = "SELECT v FROM VehicleMaster v where vehicleId=:vehicleId";
		TypedQuery<VehicleMaster> qry = entityManager.createQuery(sql, VehicleMaster.class);
		qry.setParameter("vehicleId", vehicleId);
		List<VehicleMaster> vehicleMasterList = qry.getResultList();
		return vehicleMasterList;
	}

	@Override
	public List<VehicleMaster> getVehiclesByMake(String make) throws InsuranceException {
		String sql = "SELECT v FROM VehicleMaster v where make=:make";
		TypedQuery<VehicleMaster> qry = entityManager.createQuery(sql, VehicleMaster.class);
		qry.setParameter("make", make);
		List<VehicleMaster> vehicleMasterList = qry.getResultList();
		return vehicleMasterList;
	}

	@Override
	public List<String> getVehiclesMake() throws InsuranceException{
		String sql = "SELECT distinct v.make FROM VehicleMaster v";
		TypedQuery<String> qry = entityManager.createQuery(sql, String.class);
		List<String> vehicleMakeList = qry.getResultList();
		return vehicleMakeList;
	}
	
	@Override
	public List<VehicleMaster> getVehiclesByMakeAndModel(String make, String model) throws InsuranceException {
		String sql = "SELECT v FROM VehicleMaster v where make=:make and model=:model";
		TypedQuery<VehicleMaster> qry = entityManager.createQuery(sql, VehicleMaster.class);
		qry.setParameter("make", make);
		qry.setParameter("model", model);
		List<VehicleMaster> vehicleMasterList = qry.getResultList();
		return vehicleMasterList;
	}

	@Override
	public List<String> getVehicleModelByMake(String make) throws InsuranceException {
		String sql = "SELECT v.model FROM VehicleMaster v where make=:make";
		TypedQuery<String> qry = entityManager.createQuery(sql, String.class);
		qry.setParameter("make", make);
		List<String> model = qry.getResultList();
		return model;
	}

	@Override
	public List<String> getVehicleSubmodelByMakeAndModel(String make, String model) throws InsuranceException {
		String sql = "SELECT v.submodel FROM VehicleMaster v where make=:make and model=:model";
		TypedQuery<String> qry = entityManager.createQuery(sql, String.class);
		qry.setParameter("make", make);
		qry.setParameter("model", model);
		List<String> submodel = qry.getResultList();
		return submodel;
	}

	@Override
	public List<VehicleMaster> getVehiclesByMakeModelSubmodel(String make, String model, String submodel)
			throws InsuranceException {
		String sql = "SELECT v FROM VehicleMaster v where make=:make and model=:model and submodel=:submodel";
		TypedQuery<VehicleMaster> qry = entityManager.createQuery(sql, VehicleMaster.class);
		qry.setParameter("make", make);
		qry.setParameter("model", model);
		qry.setParameter("submodel", submodel);
		List<VehicleMaster> vehicleMasterList = qry.getResultList();
		return vehicleMasterList;
	}

	@Override
	public String getVehicleCCByMakeModelSubmodel(String make, String model, String submodel)
			throws InsuranceException {
		String sql = "SELECT v.cc FROM VehicleMaster v where make=:make and model=:model and submodel=:submodel";
		TypedQuery<String> qry = entityManager.createQuery(sql, String.class);
		qry.setParameter("make", make);
		qry.setParameter("model", model);
		qry.setParameter("submodel", submodel);
		String cc = qry.getSingleResult();
		return cc;
	}

	@Override
	public Float getVehiclePriceByMakeModelSubmodel(String make, String model, String submodel)
			throws InsuranceException {
		System.out.println("Make: "+make + " Model: "+ model + " subModel:  " + submodel );
		String sql = "SELECT v.price FROM VehicleMaster v where make=:make and model=:model and submodel=:submodel";
		TypedQuery<Float> qry = entityManager.createQuery(sql, Float.class);
		qry.setParameter("make", make);
		qry.setParameter("model", model);
		qry.setParameter("submodel", submodel);
		Float price = qry.getSingleResult();
		return price;
	}
	
	@Override
	public List<String> getVehicleMake() throws InsuranceException {
		String sql = "SELECT v.make FROM VehicleMaster v";
		TypedQuery<String> qry = entityManager.createQuery(sql, String.class);
		List<String> make = qry.getResultList();
		return make;
	}

	@Override
	public List<String> getVehicleModel() throws InsuranceException {
		String sql = "SELECT v.model FROM VehicleMaster v";
		TypedQuery<String> qry = entityManager.createQuery(sql, String.class);
		List<String> model = qry.getResultList();
		return model;
	}

	@Override
	public List<String> getVehicleSubmodel() throws InsuranceException {
		String sql = "SELECT v.submodel FROM VehicleMaster v";
		TypedQuery<String> qry = entityManager.createQuery(sql, String.class);
		List<String> submodel = qry.getResultList();
		return submodel;
	}

}
