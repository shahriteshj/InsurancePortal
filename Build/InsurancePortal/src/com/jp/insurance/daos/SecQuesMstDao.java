package com.jp.insurance.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jp.insurance.entities.Cities;
import com.jp.insurance.entities.SecurityQuestion;
import com.jp.insurance.exceptions.InsuranceException;

@Repository("secQuesMstDao")
public class SecQuesMstDao implements Serializable, ISecQuesMstDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -962690696477093043L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SecurityQuestion> getQuestionList() throws InsuranceException {
		String sql = "SELECT s FROM SecurityQuestion s";
		TypedQuery<SecurityQuestion> qry = entityManager.createQuery(sql, SecurityQuestion.class);
		List<SecurityQuestion> questionList = qry.getResultList();
		return questionList;
	}

}
