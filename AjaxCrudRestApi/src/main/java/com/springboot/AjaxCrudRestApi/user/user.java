package com.springboot.AjaxCrudRestApi.user;

import java.security.KeyStore.PrivateKeyEntry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.springboot.AjaxCrudRestApi.address.address;
import com.springboot.AjaxCrudRestApi.district.district;

@Entity
@Table(name = "user")
public class user {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	
	@Column(name = "name")
	private String name;
	

    @Column(name = "province")
    private int add;
    
    @Column(name = "district")
    private int dis;
    
	public user() {
		super();
		// TODO Auto-generated constructor stub
	}

	public user(Integer id, String name, int add, int dis) {
		super();
		this.id = id;
		this.name = name;
		this.add = add;
		this.dis = dis;
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

	public int getAdd() {
		return add;
	}

	public void setAdd(int add) {
		this.add = add;
	}

	public int getDis() {
		return dis;
	}

	public void setDis(int dis) {
		this.dis = dis;
	}
}
