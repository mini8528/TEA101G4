package com.wishList.model;

import java.util.*;


public interface WishListDAO_interface {

	public void insert(WishListVO wishListVO);
	public void update(WishListVO wishListVO);
	public void delete(String wishListId);
	public WishListVO findByPrimaryKey(String wishListId);
	public List<WishListVO> getAll();
}
