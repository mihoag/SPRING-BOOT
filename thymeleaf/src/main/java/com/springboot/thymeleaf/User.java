package com.springboot.thymeleaf;

import java.sql.Date;

public class User {
	private String name;
    private String email;
    private String password;
    private String gender;
    private String note;
    private boolean married;
    private Date birthday;
    private String profession;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String name, String email, String password, String gender, String note, boolean married, Date birthday,
			String profession) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.note = note;
		this.married = married;
		this.birthday = birthday;
		this.profession = profession;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public boolean isMarried() {
		return married;
	}
	public void setMarried(boolean married) {
		this.married = married;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
}
