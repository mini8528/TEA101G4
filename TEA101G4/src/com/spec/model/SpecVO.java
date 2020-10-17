package com.spec.model;

import java.io.Serializable;

public class SpecVO implements Serializable{
	private String specid;
	private String productid;
	private String specific;
	private Integer stock;
	
	public String getSpecid() {
		return specid;
	}
	public void setSpecid(String specid) {
		this.specid = specid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getSpecific() {
		return specific;
	}
	public void setSpecific(String specific) {
		this.specific = specific;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "SpecVO [specid=" + specid + ", productid=" + productid + ", specific=" + specific + ", stock=" + stock
				+ "]";
	}
	
}
