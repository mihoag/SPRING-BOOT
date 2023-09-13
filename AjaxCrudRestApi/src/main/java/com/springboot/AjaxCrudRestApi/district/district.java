package com.springboot.AjaxCrudRestApi.district;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.springboot.AjaxCrudRestApi.address.address;
import com.springboot.AjaxCrudRestApi.user.user;

@Entity
@Table(name = "district")
public class district {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "idProvince")
	int province;

	public district() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "district [id=" + id + ", name=" + name + ", province=" + province + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public district(Integer id, String name, int province) {
		super();
		this.id = id;
		this.name = name;
		this.province = province;
	}
}
