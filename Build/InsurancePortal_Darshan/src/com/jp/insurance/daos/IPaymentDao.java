package com.jp.insurance.daos;

import java.util.List;

import com.jp.insurance.entities.Payment;
import com.jp.insurance.exceptions.InsuranceException;

public interface IPaymentDao {

	public List<Payment> getPaymentList() throws InsuranceException;

	public Payment addPayment(Payment payment) throws InsuranceException;

}
