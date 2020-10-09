package com.classDetail.model;

public class ClassDetailVO implements java.io.Serializable {

	private String classDetailID;
	
	private String classOrderID;
	private String coachClassID;
	private Integer quantity;
	
	public String getClassDetailID() {
		return classDetailID;
	}
	public void setClassDetailID(String classDetailID) {
		this.classDetailID = classDetailID;
	}
	public String getClassOrderID() {
		return classOrderID;
	}
	public void setClassOrderID(String classOrderID) {
		this.classOrderID = classOrderID;
	}
	public String getCoachClassID() {
		return coachClassID;
	}
	public void setCoachClassID(String coachClassID) {
		this.coachClassID = coachClassID;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

	
}
