package com.bodyrecord.model;

import java.sql.Date;
import java.util.Arrays;

public class BodyrecordVO implements java.io.Serializable{
	private String bodyrecordid;
	private String memberId;
	private Date uploaddate;
	private byte[] photo1;
	private String description;
	private String describestatus;
	private Date updatetime;
	
	public String getbodyrecordid() {
		return bodyrecordid;
	}
	public void setbodyrecordid(String bodyrecordid) {
		this.bodyrecordid = bodyrecordid;
	}
	public String getmemberId() {
		return memberId;
	}
	public void setmemberId(String memberId) {
		this.memberId = memberId;
	}
	public Date getuploaddate() {
		return uploaddate;
	}
	public void setuploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}
	public byte[] getphoto1() {
		return photo1;
	}
	public void setphoto1(byte[] photo1) {
		this.photo1 = photo1;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	public String getdescribestatus() {
		return describestatus;
	}
	public void setdescribestatus(String describestatus) {
		this.describestatus = describestatus;
	}
	public Date getupdatetime() {
		return updatetime;
	}
	public void setupdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	@Override
	public String toString() {
		return "BodyrecordVO [bodyrecordid=" + bodyrecordid+ ",memberId=" + memberId+",uploaddate=" + uploaddate + ",photo1="+Arrays.toString(photo1) + 
				",description=" + description + ",describestatus" + describestatus +",updatetime=" + updatetime + "]";
	}
}
