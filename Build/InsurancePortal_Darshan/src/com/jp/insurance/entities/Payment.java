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
@Table(name = "PolicyPayment")
public class Payment {

	private Long paymentId;
	private Long policyId;
	private String nameOnCard;
	private String cardNo;
	private String cardExpirtDate;
	private Float policyAmount;
	private Date paymentDate;

	@Id
	// @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name = "PAYMENTID_GEN", sequenceName = "paymentId_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENTID_GEN")
	@Column(name = "PaymentId")
	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	@Column(name = "PolicyId")
	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	@Column(name = "NameOnCard")
	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	@Column(name = "CardNo")
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(name = "CardExpiryDate")
	public String getCardExpirtDate() {
		return cardExpirtDate;
	}

	public void setCardExpirtDate(String cardExpirtDate) {
		this.cardExpirtDate = cardExpirtDate;
	}

	@Column(name = "PolicyAmount")
	public Float getPolicyAmount() {
		return policyAmount;
	}

	public void setPolicyAmount(Float policyAmount) {
		this.policyAmount = policyAmount;
	}

	@Column(name = "PaymentDate")
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", policyId=" + policyId + ", nameOnCard=" + nameOnCard + ", cardNo="
				+ cardNo + ", cardExpirtDate=" + cardExpirtDate + ", policyAmount=" + policyAmount + ", paymentDate="
				+ paymentDate + "]";
	}

}
