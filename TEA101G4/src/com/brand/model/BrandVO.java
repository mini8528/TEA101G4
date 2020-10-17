package com.brand.model;

import java.io.Serializable;

public class BrandVO implements Serializable{

	private String brandid;
	private String name;
	
	public String getBrandid() {
		return brandid;
	}
	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "BrandVO [brandid=" + brandid + ", name=" + name + "]";
	}
}
