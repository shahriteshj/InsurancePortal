package com.jp.insurance.daos.interfaces;

import java.util.List;

import com.jp.insurance.entities.SecurityQuestion;
import com.jp.insurance.exceptions.InsuranceException;

public interface ISecQuesMstDao {

	public List<SecurityQuestion> getQuestionList() throws InsuranceException;

}
