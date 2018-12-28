package com.jp.insurance.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="states")
public class States {

	private Integer id;
	private String name;
	List<Cities> cityList;
	
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
	
	@OneToMany(mappedBy="state",fetch=FetchType.EAGER)
	public List<Cities> getCityList() {
		return cityList;
	}
	public void setCityList(List<Cities> cityList) {
		this.cityList = cityList;
	}
	@Override
	public String toString() {
		return "States [id=" + id + ", name=" + name + ", cityList=" + cityList + "]";
	}

	
	
	
	
	
}
