package com.springboot.fileupload;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "customer")
public class customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	public void setPhotosImagePath(String photosImagePath) {
		PhotosImagePath = photosImagePath;
	}

	@Column(name = "photo", nullable = true, length = 64)
	private String photo;
	
	String PhotosImagePath;
	
	String desciption;

	public customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public customer(Integer id, String name, String photo) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	@Transient
	public String getPhotosImagePath()
	{
		return PhotosImagePath;
	}
	

}
