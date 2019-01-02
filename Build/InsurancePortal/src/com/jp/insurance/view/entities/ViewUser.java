package com.jp.insurance.view.entities;

import java.util.Date;

import javax.persistence.Column;


public class ViewUser {

	private Long userId;
	private String name;
	private String username;
	private String password;
	private String roleName;
	private Integer questionId;
	private String securityAnswer;
	private Date lastSuccessfulLoginDate;
	private String responseText;

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
	
	@Column(name="lastSuccessfulLoginDate")
	public Date getLastSuccessfulLoginDate() {
		return lastSuccessfulLoginDate;
	}
	public void setLastSuccessfulLoginDate(Date lastSuccessfulLoginDate) {
		this.lastSuccessfulLoginDate = lastSuccessfulLoginDate;
	}
	
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", roleName=" + roleName
				+ ", questionId=" + questionId + ", securityAnswer=" + securityAnswer + ", lastSuccessfulLoginDate="
				+ lastSuccessfulLoginDate + ", responseText=" + responseText + "]";
	}
	

	
	
}
