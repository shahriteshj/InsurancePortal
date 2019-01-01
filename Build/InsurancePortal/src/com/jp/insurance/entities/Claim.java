package com.jp.insurance.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Claim")
public class Claim {
	
	private Long claimId;
	private Long policyId;
	private String status;
	private Date createdOn;
	private Date updatedOn;
	private String assignedTo;
	private String rejectedReason;
	private AccidentDetails accidentDetails;
	
	@Id
	@Column(name="claimId")
	@SequenceGenerator(name = "CLAIMID_GEN", sequenceName = "claimId_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLAIMID_GEN")
	public Long getClaimId() {
		return claimId;
	}
	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}
	
	@Column(name="polciyId")
	public Long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="createdOn")
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Column(name="updatedOn")
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	@Column(name="assignedTo")
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	
	@Column(name="rejectedReason")
	public String getRejectedReason() {
		return rejectedReason;
	}
	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}
	
	@OneToOne(mappedBy="employee", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "ACCIDENTID") // Name of column to be created in CLAIM
	public AccidentDetails getAccidentDetails() {
		return accidentDetails;
	}
	public void setAccidentDetails(AccidentDetails accidentDetails) {
		this.accidentDetails = accidentDetails;
	}
	@Override
	public String toString() {
		return "Claim [claimId=" + claimId + ", policyId=" + policyId + ", status=" + status + ", createdOn="
				+ createdOn + ", updatedOn=" + updatedOn + ", assignedTo=" + assignedTo + ", rejectedReason="
				+ rejectedReason + ", accidentDetails=" + accidentDetails + "]";
	}
	
	
	
	
}
