package com.wishList.model;

import java.sql.Date;

public class WishListVO {

	private String wishListId;
	
	private String memberId;
	private String productId;
	private String likeStatus;
	private Date addDate;
	private Date editDate;
	
	public String getWishListId() {
		return wishListId;
	}
	public void setWishListId(String wishListId) {
		this.wishListId = wishListId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getLikeStatus() {
		return likeStatus;
	}
	public void setLikeStatus(String likeStatus) {
		this.likeStatus = likeStatus;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	
}
