package com.cart.model;

import java.io.Serializable;

public class CartVO implements Serializable{
	private String memberid;
	private String specid;
	private String productid;//
	private byte[] photo1;//
	private String brandname;
	private String productname;
	private Integer quantity;
	private Integer price;
	private String specific;
	
	
	public CartVO() {
		memberid = "";
		specid = "";
		specific = "";
		brandname = "";
		productname = "";
		quantity = 0;
		price = 0;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public byte[] getPhoto1() {
		return photo1;
	}
	public void setPhoto1(byte[] photo1) {
		this.photo1 = photo1;
	}
	public String getSpecific() {
		return specific;
	}
	public void setSpecific(String specific) {
		this.specific = specific;
	}
	public String getSpecid() {
		return specid;
	}
	public void setSpecid(String specid) {
		this.specid = specid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	@Override
	public String toString() {
		return "CartVO [memberid=" + memberid + ", specid=" + specid + ", productid=" + productid + ", brandname="
				+ brandname + ", productname=" + productname + ", quantity=" + quantity + ", price=" + price
				+ ", specific=" + specific + "]";
	}
	
}
