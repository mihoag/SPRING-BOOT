package com.springboot.AjaxCrudRestApi.address;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.springboot.AjaxCrudRestApi.district.district;
import com.springboot.AjaxCrudRestApi.user.user;

@Entity
@Table(name = "address")
public class address {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "provice_name")
	private String provincename;

	public address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public address(Integer id, String provincename) {
		super();
		this.id = id;
		this.provincename = provincename;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}
}
