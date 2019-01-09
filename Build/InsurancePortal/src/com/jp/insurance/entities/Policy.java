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
@Table(name = "Policy")
public class Policy {

	private Long policyId;
	private Long customerId;
	private Long vehicleId;

	// @Convert(converter =LocalDateAttributeConverter.class)
	private Date policyStartDate;

	// @Convert(converter =LocalDateAttributeConverter.class)
	private Date policyEndDate;

	private String status;
	
	@Id
	@SequenceGenerator(name = "POLICYID_GEN", sequenceName = "policyId_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLICYID_GEN")
	@Column(name = "PolicyId")
	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	@Column(name = "CustomerId")
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "VehicleId")
	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Column(name = "PolicyStartDate")
	public Date getPolicyStartDate() {
		return policyStartDate;
	}

	public void setPolicyStartDate(Date policyStartDate) {
		this.policyStartDate = policyStartDate;
	}

	@Column(name = "PolicyEndDate")
	public Date getPolicyEndDate() {
		return policyEndDate;
	}

	public void setPolicyEndDate(Date policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", customerId=" + customerId + ", vehicleId=" + vehicleId
				+ ", policyStartDate=" + policyStartDate + ", policyEndDate=" + policyEndDate + ", status=" + status
				+ "]";
	}

	

}
