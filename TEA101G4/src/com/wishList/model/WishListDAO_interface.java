package com.wishList.model;

import java.util.*;

import com.member.model.MemberVO;


public interface WishListDAO_interface {

	public void insert(WishListVO wishListVO);
	public void update(WishListVO wishListVO);
	public void delete(String wishListId);
	public WishListVO findByPrimaryKey(String wishListId);
	public List<WishListVO> getAll();
//	public List<WishListVO> findByMemberid(String memberId);
	public WishListVO findByAccount(String account);

	public List<WishListVO> getWishListByMemberId(String memberId);
}
