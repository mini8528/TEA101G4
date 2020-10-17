package com.product.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ProductVO implements Serializable{
	private String productid;
	private String adminid;
	private String adminid2;
	private String brandid;
	private String name;
	private String category;
	private Integer price;
	private Timestamp adddate;
	private String status;
	private Timestamp editdate;
	private byte[] photo1;
	private byte[] photo2;
	private byte[] photo3;
	private String intro;
	
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getAdminid2() {
		return adminid2;
	}
	public void setAdminid2(String adminid2) {
		this.adminid2 = adminid2;
	}
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Timestamp getAdddate() {
		return adddate;
	}
	public void setAdddate(Timestamp adddate) {
		this.adddate = adddate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getEditdate() {
		return editdate;
	}
	public void setEditdate(Timestamp editdate) {
		this.editdate = editdate;
	}
	public byte[] getPhoto1() {
		return photo1;
	}
	public void setPhoto1(byte[] photo1) {
		this.photo1 = photo1;
	}
	public byte[] getPhoto2() {
		return photo2;
	}
	public void setPhoto2(byte[] photo2) {
		this.photo2 = photo2;
	}
	public byte[] getPhoto3() {
		return photo3;
	}
	public void setPhoto3(byte[] photo3) {
		this.photo3 = photo3;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	@Override
	public String toString() {
		return "ProductVO [productid=" + productid + ", adminid=" + adminid + ", adminid2=" + adminid2 + ", brandid="
				+ brandid + ", name=" + name + ", category=" + category + ", price=" + price + ", adddate=" + adddate
				+ ", status=" + status + ", editdate=" + editdate + ", intro=" + intro + "]";
	}
	
}
