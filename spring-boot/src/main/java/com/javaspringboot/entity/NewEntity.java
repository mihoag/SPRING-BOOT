package com.javaspringboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "new")
@Table
public class NewEntity extends baseEntity{

	@Column(name = "title")
	private String title;
	
	@Column(name = "thumbnail")
	private String thumbnail;
	
	@Column(name = "shortdescription")
	private String shortDescription;
	
	@Column(name = "content")
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
		
}
