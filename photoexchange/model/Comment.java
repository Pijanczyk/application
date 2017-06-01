package com.photoexchange.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "comment")
public class Comment {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss"); 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String text;
	
	private Date date;
	
	@ManyToOne()
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne()
	@JoinColumn(name="photo_id")
	private Photo photo;
	
	public Comment() {}
	
	public Comment(long id) { 
	    this.id = id;
	  }

	public Comment(Photo photo) {
		this.photo = photo;
	}
	
	public Comment(String text, User user, Photo photo) {
		this.text = text;
		this.user = user;
		this.photo = photo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return dateFormat.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	
}
