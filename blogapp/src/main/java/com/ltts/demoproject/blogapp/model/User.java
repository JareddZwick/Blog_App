package com.ltts.demoproject.blogapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.annotation.Transient;

@Entity
public class User {
	@Id
	private String emailId;
	private String mobile;
	private String gender;
	private String location;
	private int numComments;
	private boolean admin;
	private String photos;
	private String imgsrc;
	private String likes;
	private String dislikes;
	private String cLikes;
	private String cDislikes;
	
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public String getDislikes() {
		return dislikes;
	}
	public void setDislikes(String dislikes) {
		this.dislikes = dislikes;
	}
	public User() {}
	public User(String emailId, String mobile, String gender, String location) {
		super();
		this.emailId = emailId;
		this.mobile = mobile;
		this.gender = gender;
		this.location = location;
		this.likes = "";
		this.dislikes = "";
		this.cLikes = "";
		this.cDislikes ="";
	}
	
	@Transient
    public String getPhotosImagePath() {
        if (photos == null || mobile == null) return null;
         
        return "/user-photos/" + mobile + "/" + photos;
    }
	
	@Override
	public String toString() {
		return "User:  [emailId=" + emailId + ", mobile=" + mobile + ", gender=" + gender + ", location=" + location
				+ "]";
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getNumComments() {
		return numComments;
	}
	public void setNumComments(int numComments) {
		this.numComments = numComments;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getcDislikes() {
		return cDislikes;
	}
	public void setcDislikes(String cDislikes) {
		this.cDislikes = cDislikes;
	}
	public String getcLikes() {
		return cLikes;
	}
	public void setcLikes(String cLikes) {
		this.cLikes = cLikes;
	}
	
}
