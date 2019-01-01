package com.jp.insurance.entities;

import java.util.Date;

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
@Table(name="ACCIDENTDETAILS")
public class AccidentDetails {
	
	private Long accidentId;
	private Date accidentDate;
	private String location;
	private String city;
	private String state;
	private Integer noOfPersonsTravelling;
	private String accidentDescription;
	private char thirdPartyResponsible;
	private String firDetails;
	private String driverName;
	private String driverRelaton;
	private String driverContact;
	private String driverLicenseNo;
	private Claim claim;
	
	@Id
	@Column(name="accidentId")
	@SequenceGenerator(name = "ACCIDENTID_GEN", sequenceName = "accidentId_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCIDENTID_GEN")
	public Long getAccidentId() {
		return accidentId;
	}
	public void setAccidentId(Long accidentId) {
		this.accidentId = accidentId;
	}
	
	@Column(name="accidentDate")
	public Date getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}
	
	@Column(name="location")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(name="city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="noOfPersonsTravelling")
	public Integer getNoOfPersonsTravelling() {
		return noOfPersonsTravelling;
	}
	public void setNoOfPersonsTravelling(Integer noOfPersonsTravelling) {
		this.noOfPersonsTravelling = noOfPersonsTravelling;
	}
	
	@Column(name="accidentDescription")
	public String getAccidentDescription() {
		return accidentDescription;
	}
	public void setAccidentDescription(String accidentDescription) {
		this.accidentDescription = accidentDescription;
	}
	
	@Column(name="thirdPartyResponsible")
	public char getThirdPartyResponsible() {
		return thirdPartyResponsible;
	}
	public void setThirdPartyResponsible(char thirdPartyResponsible) {
		this.thirdPartyResponsible = thirdPartyResponsible;
	}
	
	@Column(name="firDetails")
	public String getFirDetails() {
		return firDetails;
	}
	public void setFirDetails(String firDetails) {
		this.firDetails = firDetails;
	}
	
	@Column(name="driverName")
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	@Column(name="driverRelation")
	public String getDriverRelaton() {
		return driverRelaton;
	}
	public void setDriverRelaton(String driverRelaton) {
		this.driverRelaton = driverRelaton;
	}
	
	@Column(name="driverContact")
	public String getDriverContact() {
		return driverContact;
	}
	public void setDriverContact(String driverContact) {
		this.driverContact = driverContact;
	}
	
	@Column(name="driverLicenseNo")
	public String getDriverLicenseNo() {
		return driverLicenseNo;
	}
	public void setDriverLicenseNo(String driverLicenseNo) {
		this.driverLicenseNo = driverLicenseNo;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="claimId")
	public Claim getClaim() {
		return claim;
	}
	public void setClaim(Claim claim) {
		this.claim = claim;
	}
	
	@Override
	public String toString() {
		return "AccidentDetails [accidentId=" + accidentId + ", accidentDate=" + accidentDate + ", location=" + location
				+ ", city=" + city + ", state=" + state + ", noOfPersonsTravelling=" + noOfPersonsTravelling
				+ ", accidentDescription=" + accidentDescription + ", thirdPartyResponsible=" + thirdPartyResponsible
				+ ", firDetails=" + firDetails + ", driverName=" + driverName + ", driverRelaton=" + driverRelaton
				+ ", driverContact=" + driverContact + ", driverLicenseNo=" + driverLicenseNo + "]";
	}

	
	
	

}
