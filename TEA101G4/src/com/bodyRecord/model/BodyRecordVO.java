package com.bodyRecord.model;

import java.sql.Timestamp;

public class BodyRecordVO {

//	BodyRecord
	
	private String bodyRecordID;
	
	private String memberID;
	private Timestamp uploadDate;
	private byte[] photo1;
	private String description;
	private String describeStatus;
	private Timestamp updateTime;
	
	
	public String getBodyRecordID() {
		return bodyRecordID;
	}
	public void setBodyRecordID(String bodyRecordID) {
		this.bodyRecordID = bodyRecordID;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public Timestamp getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}
	public byte[] getPhoto1() {
		return photo1;
	}
	public void setPhoto1(byte[] photo1) {
		this.photo1 = photo1;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescribeStatus() {
		return describeStatus;
	}
	public void setDescribeStatus(String describeStatus) {
		this.describeStatus = describeStatus;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
	
}
