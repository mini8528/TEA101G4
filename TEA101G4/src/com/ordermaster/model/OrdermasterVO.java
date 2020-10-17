package com.ordermaster.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class OrdermasterVO implements Serializable{
	private String ordermasterid;
	private String memberid;
	private String payment;
	private String paystatus;
	private Timestamp orderdate;
	private Date payexpire;
	private String paycode;
	private String receiver;
	private String tel;
	private String address;
	private String orderstatus;
	
	public Date getPayexpire() {
		return payexpire;
	}
	public void setPayexpire(Date payexpire) {
		this.payexpire = payexpire;
	}

	
	public String getOrdermasterid() {
		return ordermasterid;
	}
	public void setOrdermasterid(String ordermasterid) {
		this.ordermasterid = ordermasterid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPaystatus() {
		return paystatus;
	}
	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}
	public Timestamp getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}
	public String getPaycode() {
		return paycode;
	}
	public void setPaycode(String paycode) {
		this.paycode = paycode;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	@Override
	public String toString() {
		return "OrdermasterVO [ordermasterid=" + ordermasterid + ", memberid=" + memberid + ", payment=" + payment
				+ ", paystatus=" + paystatus + ", orderdate=" + orderdate + ", payexpire=" + payexpire + ", paycode="
				+ paycode + ", receiver=" + receiver + ", tel=" + tel + ", address=" + address + ", orderstatus="
				+ orderstatus + "]";
	}

	
}
