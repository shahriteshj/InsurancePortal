package com.jp.insurance.entities;

import java.util.Date;

import javax.persistence.Entity;
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
}
