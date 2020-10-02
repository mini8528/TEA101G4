package com.blog_mes.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Blog_MesVO implements Serializable {
	private String blogMesno;
	private String blogno;
	private String memberId;
	private String text;
	private Timestamp postDate;
	private Timestamp updateTime;
	private String status;

	
	public Blog_MesVO() {
	}
	public Blog_MesVO(String blogno) {
		this.blogno = blogno;
	}
	public String getBlogMesno() {
		return blogMesno;
	}
	public void setBlogMesno(String blogMesno) {
		this.blogMesno = blogMesno;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getPostDate() {
		return postDate;
	}
	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Blog_MesVO [blogMesno=" + blogMesno + ", blogno=" + blogno + ", memberId=" + memberId + ", text=" + text
				+ ", postDate=" + postDate + ", updateTime=" + updateTime + ", status=" + status + "]";
	}
	
	

}
