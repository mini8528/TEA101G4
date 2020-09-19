package com.blog_save.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Blog_SaveVO implements Serializable {
	private String blogSaveno;
	private String memberId;
	private String blogno;
	private String status;
	private Timestamp saveDate;
	private Timestamp updateTime;
	
	
	public String getBlogSaveno() {
		return blogSaveno;
	}
	public void setBlogSaveno(String blogSaveno) {
		this.blogSaveno = blogSaveno;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBlogno() {
		return blogno;
	}
	public void setBlogno(String blogno) {
		this.blogno = blogno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getSaveDate() {
		return saveDate;
	}
	public void setSaveDate(Timestamp saveDate) {
		this.saveDate = saveDate;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Blog_SaveVO [blogSaveno=" + blogSaveno + ", memberId=" + memberId + ", blogno=" + blogno + ", status="
				+ status + ", saveDate=" + saveDate + ", updateTime=" + updateTime + "]";
	}
	
	
	
}
