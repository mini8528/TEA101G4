package com.classOrder.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ClassOrderVO implements Serializable {
	
	private String classOrderID;
	
	private String memberID;
	private String payment;
	private String paymentStatus;
	private Date payExpire;
	private String payCode;
	private Timestamp orderDate;
	public String getClassOrderID() {
		return classOrderID;
	}
	public void setClassOrderID(String classOrderID) {
		this.classOrderID = classOrderID;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Date getPayExpire() {
		return payExpire;
	}
	public void setPayExpire(Date payExpire) {
		this.payExpire = payExpire;
	}
	public String getPayCode() {
		return payCode;
	}
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
	
}
