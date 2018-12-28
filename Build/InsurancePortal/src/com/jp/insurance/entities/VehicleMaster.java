package com.jp.insurance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VehicleMaster")
public class VehicleMaster {

	private Long vehicleId;
	private String make;
	private String model;
	private String submodel;
	private String cc;
	private Float price;
	
	@Id
	@Column(name="VEHICLEID")
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	@Column(name="MAKE")
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	
	@Column(name="MODEL")
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	@Column(name="SUBMODEL")
	public String getSubmodel() {
		return submodel;
	}
	public void setSubmodel(String submodel) {
		this.submodel = submodel;
	}
	
	@Column(name="CC")
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	
	@Column(name="PRICE")
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "VehicleMaster [vehicleId=" + vehicleId + ", make=" + make + ", model=" + model + ", submodel="
				+ submodel + ", cc=" + cc + ", price=" + price + "]";
	}
	
	
	
}
