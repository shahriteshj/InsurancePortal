package com.jp.insurance.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jp.insurance.daos.interfaces.IPaymentDao;
import com.jp.insurance.entities.Payment;
import com.jp.insurance.exceptions.InsuranceException;

@Repository("paymentDao")
public class PaymentDao implements Serializable, IPaymentDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1594141369248758365L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Payment> getPaymentList() throws InsuranceException {
		String sql = "SELECT c FROM Payment c";
		TypedQuery<Payment> qry = entityManager.createQuery(sql, Payment.class);
		List<Payment> paymentList = qry.getResultList();
		return paymentList;
	}

	@Override
	public Payment addPayment(Payment payment) throws InsuranceException {
		Payment newPayment = null;

		entityManager.persist(payment);
		entityManager.flush();
		if (payment.getPaymentId() > 0) {
			newPayment = payment;
		}
		return newPayment;
	}
	
	@Override
	public Payment getPaymentDetails(Long policyId) throws InsuranceException {
		String sql = "SELECT c FROM Payment c where policyId="+policyId;
		TypedQuery<Payment> qry = entityManager.createQuery(sql, Payment.class);
		List<Payment> paymentList = qry.getResultList();
		if(paymentList.isEmpty()){
			return null;
		}else{
			return paymentList.get(0);
		}
	}

}
