package com.jp.insurance.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "Query_Record")
@Table(name = "QUERY")
public class Query {

	private Long queryId;
	private String name;
	private String emailId;
	private String queryType;
	private String queryDescription;
	private String queryResponse;
	private Date creationDate;
	private String assignedTo;
	private String status;

	@Id
	@SequenceGenerator(name = "QUERYID_GEN", sequenceName = "queryId_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUERYID_GEN")
	@Column(name = "QUERYID")
	public Long getQueryId() {
		return queryId;
	}

	public void setQueryId(Long queryId) {
		this.queryId = queryId;
	}

	/*
	 * @Column(name="NAME") public String getName() { return name; } public void
	 * setName(String name) { this.name = name; }
	 */

	@NotNull
	/*
	 * @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
	 * +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
	 * +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
	 * ,message="{invalid.email}")
	 * 
	 * @Column(name="EMAILID")
	 */
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@NotNull
	@Column(name = "QUERYTYPE")
	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	@Column(name = "QUERYDESCRIPTION")
	public String getQueryDescription() {
		return queryDescription;
	}

	public void setQueryDescription(String queryDescription) {
		this.queryDescription = queryDescription;
	}

	@Column(name = "QUERYRESPONSE")
	public String getQueryResponse() {
		return queryResponse;
	}

	public void setQueryResponse(String queryResponse) {
		this.queryResponse = queryResponse;
	}

	@Column(name = "CREATIONDATE")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "ASSIGNEDTO")
	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Query [queryId=" + queryId + ",  emailId=" + emailId + ", queryType=" + queryType
				+ ", queryDescription=" + queryDescription + ", queryResponse=" + queryResponse + ", creationDate="
				+ creationDate + ", assignedTo=" + assignedTo + ", status=" + status + "]";
	}

}
