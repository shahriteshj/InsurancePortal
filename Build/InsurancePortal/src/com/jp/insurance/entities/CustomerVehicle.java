package com.jp.insurance.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CustomerVehicle")
public class CustomerVehicle {

	private Long vehicleId;
	private Long customerId;
	private String vehicleRegNo;
	private String make;
	private String model;
	private String submodel;
	private String engineNo;
	private String chasisNo;
	private String cc;
	private String fuelType;
	private Integer manufacturingYear;
	private Date registrationDate;
	private String vehicleRegCity;

	@Id
	@SequenceGenerator(name = "VEHICLEID_GEN", sequenceName = "vehicleId_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEHICLEID_GEN")
	// @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "VehicleId")
	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Column(name = "CustomerId")
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "VehicleRegNo")
	public String getVehicleRegNo() {
		return vehicleRegNo;
	}

	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}

	@Column(name = "Make")
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	@Column(name = "Model")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "Submodel")
	public String getSubmodel() {
		return submodel;
	}

	public void setSubmodel(String submodel) {
		this.submodel = submodel;
	}

	@Column(name = "EngineNo")
	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	@Column(name = "ChasisNo")
	public String getChasisNo() {
		return chasisNo;
	}

	public void setChasisNo(String chasisNo) {
		this.chasisNo = chasisNo;
	}

	@Column(name = "CC")
	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	@Column(name = "FuelType")
	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Column(name = "ManufacturingYear")
	public Integer getManufacturingYear() {
		return manufacturingYear;
	}

	public void setManufacturingYear(Integer manufacturingYear) {
		this.manufacturingYear = manufacturingYear;
	}

	@Column(name = "RegistrationDate")
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Column(name = "VehicleRegCity")
	public String getVehicleRegCity() {
		return vehicleRegCity;
	}

	public void setVehicleRegCity(String vehicleRegCity) {
		this.vehicleRegCity = vehicleRegCity;
	}

	@Override
	public String toString() {
		return "CustomerVehicle [vehicleId=" + vehicleId + ", customerId=" + customerId + ", VehicleRegNo="
				+ vehicleRegNo + ", make=" + make + ", model=" + model + ", submodel=" + submodel + ", engineNo="
				+ engineNo + ", chasisNo=" + chasisNo + ", cc=" + cc + ", fuelType=" + fuelType + ", manufacturingYear="
				+ manufacturingYear + ", registrationDate=" + registrationDate + ", vehicleRegCity=" + vehicleRegCity
				+ "]";
	}

}
