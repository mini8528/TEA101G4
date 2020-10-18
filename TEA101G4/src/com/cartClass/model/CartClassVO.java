package com.cartClass.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CartClassVO implements Serializable{
	
	private String memberid;
	private String coachClassID;
	private String className;//
	private byte[] photo;//
	private String memberid_Coach;//??™ç·´ 
	
	private Timestamp startTime;
	private Integer price;
	private String address;
	private Integer quantity;
	
	
	
	public CartClassVO() {
		memberid = "";
		coachClassID = "";
		className = "";
		memberid_Coach = "";
		price = 0;
		address = "";
		quantity = 0;
	}



	public String getMemberid() {
		return memberid;
	}



	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}



	public String getCoachClassID() {
		return coachClassID;
	}



	public void setCoachClassID(String coachClassID) {
		this.coachClassID = coachClassID;
	}



	public String getClassName() {
		return className;
	}



	public void setClassName(String className) {
		this.className = className;
	}



	public byte[] getPhoto() {
		return photo;
	}



	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}



	public String getMemberid_Coach() {
		return memberid_Coach;
	}



	public void setMemberid_Coach(String memberid_Coach) {
		this.memberid_Coach = memberid_Coach;
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



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Integer getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
	
	
	
}
