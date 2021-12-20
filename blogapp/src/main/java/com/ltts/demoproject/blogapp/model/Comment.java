package com.ltts.demoproject.blogapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue
	private int commentId;
	private String blogId;
	private String userId;
	private String content;
	private String datetime;
	private int likes;
	private int dislikes;
	
	public Comment(){}

	public Comment(String blogId, String userId, String content, String datetime) {
		super();
		this.blogId = blogId;
		this.userId = userId;
		this.content = content;
		this.datetime = datetime;
		this.likes = 0;
		this.dislikes = 0;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", blogId=" + blogId + ", userId=" + userId + ", content=" + content
				+ ", datetime=" + datetime + "]";
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
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
	
	
}
