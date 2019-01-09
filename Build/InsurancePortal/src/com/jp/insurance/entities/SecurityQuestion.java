package com.jp.insurance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SECURITYQUESTIONMASTER")
public class SecurityQuestion {

	private Integer questionId;
	private String question;

	@Id
	@Column(name = "questionId")
	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Column(name = "question")
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "SecurityQuestion [questionId=" + questionId + ", question=" + question + "]";
	}

}
