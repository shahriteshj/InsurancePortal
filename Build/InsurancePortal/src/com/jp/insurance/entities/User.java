package com.jp.insurance.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {

	private Integer userId;
	private String username;
	private String password;
	private Integer roleId;
	
	
}
