package com.ltts.demoproject.blogapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Blog {
	@Id
	@GeneratedValue
	private int blogId; 
	private String blogTitle;
	private String blogContent;
	private String language;
	private String dateTime;
	private int likes;
	private int dislikes;
	private int comments;
	
	public Blog() {};
	public Blog(String blogTitle, String blogContent, String language, String dateTime, int likes,
			int dislikes, int comments) {
		super();
		this.blogTitle = blogTitle;
		this.blogContent = blogContent;
		this.language = language;
		this.dateTime = dateTime;
		this.likes = likes;
		this.dislikes = dislikes;
		this.comments = comments;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public void addComment() {
		this.comments += 1;
	}
	public void addLike() {
		this.likes += 1;
	}
	public void subLike() {
		this.likes -= 1;
	}
	public void addDislike() {
		this.dislikes += 1;
	}
	public void subDislike() {
		this.dislikes -= 1;
	}
	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", blogTitle=" + blogTitle + ", blogContent=" + blogContent + ", language="
				+ language + ", dateTime=" + dateTime + ", likes=" + likes + ", dislikes=" + dislikes + "]";
	}
	
	
}
