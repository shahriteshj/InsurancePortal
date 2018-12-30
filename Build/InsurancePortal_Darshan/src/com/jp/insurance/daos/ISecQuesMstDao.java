package com.jp.insurance.daos;

import java.util.List;

import com.jp.insurance.entities.SecurityQuestion;
import com.jp.insurance.exceptions.InsuranceException;

public interface ISecQuesMstDao {

	public List<SecurityQuestion> getQuestionList() throws InsuranceException; 
	
}
