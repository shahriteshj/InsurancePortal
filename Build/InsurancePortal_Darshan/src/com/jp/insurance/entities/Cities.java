package com.jp.insurance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cities")
public class Cities {

	private Integer id;
	private String name;
	private States state;
	
	@Id
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToOne()
	@JoinColumn(name="STATE_ID")
	public States getState() {
		return state;
	}
	public void setState(States state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "Cities [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
