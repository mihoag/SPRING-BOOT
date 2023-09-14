package com.springboot.UploadDownloadwithdatabase;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "documents")
public class documents {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(name = "name")
   private String name;
   
   @Column(name = "size")
   private long size;
   
   @Column(name = "uploadTime")
   private Date uploadTime;

public documents() {
	super();
	// TODO Auto-generated constructor stub
}

public documents(Long id, String name, long size, Date uploadTime) {
	super();
	this.id = id;
	this.name = name;
	this.size = size;
	this.uploadTime = uploadTime;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public long getSize() {
	return size;
}

public void setSize(long size) {
	this.size = size;
}

public Date getUploadTime() {
	return uploadTime;
}

public void setUploadTime(Date uploadTime) {
	this.uploadTime = uploadTime;
}
   
}
