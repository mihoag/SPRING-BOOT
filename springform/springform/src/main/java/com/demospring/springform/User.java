package com.demospring.springform;

import java.sql.Date;

public class User {

private String name;
private String email;
private String gender;
private String password;
private String profession;
private String note;
private Date birthday;
private boolean married;
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public User(String name, String email, String gender, String password, String profession, String note, Date birthday,
		boolean married) {
	super();
	this.name = name;
	this.email = email;
	this.gender = gender;
	this.password = password;
	this.profession = profession;
	this.note = note;
	this.birthday = birthday;
	this.married = married;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getProfession() {
	return profession;
}
public void setProfession(String profession) {
	this.profession = profession;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
public boolean isMarried() {
	return married;
}
public void setMarried(boolean married) {
	this.married = married;
}


}
