package com.orderdetail.model;

import java.io.Serializable;

public class OrderdetailVO implements Serializable{
	private String orderdetailid;
	private String ordermasterid;
	private String specid;
	private Integer quantity;
	
	public String getOrderdetailid() {
		return orderdetailid;
	}
	public void setOrderdetailid(String orderdetailid) {
		this.orderdetailid = orderdetailid;
	}
	public String getOrdermasterid() {
		return ordermasterid;
	}
	public void setOrdermasterid(String ordermasterid) {
		this.ordermasterid = ordermasterid;
	}
	public String getSpecid() {
		return specid;
	}
	public void setSpecid(String specid) {
		this.specid = specid;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderdetailVO [orderdetailid=" + orderdetailid + ", ordermasterid=" + ordermasterid + ", specid="
				+ specid + ", quantity=" + quantity + "]";
	}
	
}
