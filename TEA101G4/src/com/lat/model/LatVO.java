package com.lat.model;

import java.sql.Blob;
import java.sql.Date;



public class LatVO implements java.io.Serializable{
	private String latestnewsid;
	 private String adminid;	
	 private String admin2id;	
	 private String text;	
	 private byte[] image;	
	 private Date adddate;
	 private Date updatetime;
	 private Date uploaddate;
	 
	 
	 
	 public LatVO() {
		}
		
		public LatVO(String latestnewsid, String adminid, String admin2id,String text, byte[] image,Date adddate,Date updatetime, Date uploaddate) {
			this.latestnewsid = latestnewsid;
			this.adminid = adminid;
			this.admin2id = admin2id;
			this.text = text;
			this.image = image;
			this.adddate = adddate;
			this.updatetime = updatetime;
			this.uploaddate = uploaddate;
			
		}
	 
	 

	 
	
	 public String getLatestnewsid() {
		return latestnewsid;
	}
	public void setLatestnewsid(String latestnewsid) {
		this.latestnewsid = latestnewsid;
	}
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getAdmin2id() {
		return admin2id;
	}
	public void setAdmin2id(String admin2id) {
		this.admin2id = admin2id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Date getAdddate() {
		return adddate;
	}
	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Date getUploaddate() {
		return uploaddate;
	}
	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}
	
}

