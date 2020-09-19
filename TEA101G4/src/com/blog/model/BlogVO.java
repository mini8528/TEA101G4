package com.blog.model;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class BlogVO implements Serializable {
	private String blogno;
	private String memberId;
	private String blogClass;
	private Timestamp postDate;
	private String title;
	private String text;
	private byte[] photo;
	private byte[] video;
	private String status;
	private Timestamp updateTime;


	
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

	public String getBlogClass() {
		return blogClass;
	}

	public void setBlogClass(String blogClass) {
		this.blogClass = blogClass;
	}

	public Timestamp getPostDate() {
		return postDate;
	}

	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public byte[] getVideo() {
		return video;
	}

	public void setVideo(byte[] video) {
		this.video = video;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	


	@Override
	public String toString() {
		return "BlogVO [blogno=" + blogno + ", memberId=" + memberId + ", blogClass=" + blogClass + ", postDate="
				+ postDate + ", title=" + title + ", text=" + text + ", photo=" + Arrays.toString(photo) + ", video="
				+ Arrays.toString(video) + ", status=" + status + ", updateTime=" + updateTime + "]";
	}
	

}
