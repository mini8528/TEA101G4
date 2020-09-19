package com.blog_likes.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Blog_LikesVO implements Serializable {
	private String blogLikesno;
	private String blogno;
	private String memberId;
	private String status;
	private Timestamp likesDate;
	private Timestamp updateTime;
	
	
	public String getBlogLikesno() {
		return blogLikesno;
	}
	public void setBlogLikesno(String blogLikesno) {
		this.blogLikesno = blogLikesno;
	}
	public String getBlogno() {
		return blogno;
	}
	public void setBlogno(String blogno) {
		this.blogno = blogno;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getLikesDate() {
		return likesDate;
	}
	public void setLikesDate(Timestamp likesDate) {
		this.likesDate = likesDate;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Blog_LikesVO [blogLikesno=" + blogLikesno + ", blogno=" + blogno + ", memberId=" + memberId
				+ ", status=" + status + ", likesDate=" + likesDate + ", updateTime=" + updateTime + "]";
	}
	
	
}
