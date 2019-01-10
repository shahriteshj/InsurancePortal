package com.jp.insurance.view.entities;

import java.util.Date;

import javax.persistence.Column;


public class ViewPolicy {

	private Long policyId;
	private Long customerId;
	private Long vehicleId;
	private Date policyStartDate;
	private Date policyEndDate;
	private String status;
	private String name;
	private String emailId;
	
	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "ViewPolicy [policyId=" + policyId + ", customerId=" + customerId + ", vehicleId=" + vehicleId
				+ ", policyStartDate=" + policyStartDate + ", policyEndDate=" + policyEndDate + ", status=" + status
				+ ", name=" + name + ", emailId=" + emailId + "]";
	}

	

}
