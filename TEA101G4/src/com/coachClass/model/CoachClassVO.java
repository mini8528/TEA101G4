package com.coachClass.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

import oracle.sql.BLOB;

public class CoachClassVO implements java.io.Serializable {
	
/*1*/	private String coachClassID;
	
/*2*/	private String memberID;
/*3*/	private String className;
/*4*/	private String classContext;
/*5*/	private byte[] photo;
/*6*/	private Timestamp startTime;
/*7*/	private Integer price;
/*8*/	private Integer quantity;
/*9*/	private String address;
/*10*/	private Timestamp addDate;
/*11*/	private Timestamp editDate;
public String getCoachClassID() {
	return coachClassID;
}
public void setCoachClassID(String coachClassID) {
	this.coachClassID = coachClassID;
}
public String getMemberID() {
	return memberID;
}
public void setMemberID(String memberID) {
	this.memberID = memberID;
}
public String getClassName() {
	return className;
}
public void setClassName(String className) {
	this.className = className;
}
public String getClassContext() {
	return classContext;
}
public void setClassContext(String classContext) {
	this.classContext = classContext;
}
public byte[] getPhoto() {
	return photo;
}
public void setPhoto(byte[] photo) {
	this.photo = photo;
}
public Timestamp getStartTime() {
	return startTime;
}
public void setStartTime(Timestamp startTime) {
	this.startTime = startTime;
}
public Integer getPrice() {
	return price;
}
public void setPrice(Integer price) {
	this.price = price;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Timestamp getAddDate() {
	return addDate;
}
public void setAddDate(Timestamp addDate) {
	this.addDate = addDate;
}
public Timestamp getEditDate() {
	return editDate;
}
public void setEditDate(Timestamp editDate) {
	this.editDate = editDate;
}



	

	
}
