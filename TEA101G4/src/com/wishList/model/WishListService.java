package com.wishList.model;

import java.sql.Date;
import java.util.List;


public class WishListService {

	private WishListDAO_interface dao;

	public WishListService() {
		dao = new WishListJDBCDAO();
	}

	public WishListVO addWishList( String memberId,
			String productId,String likeStatus,	Date addDate,Date editDate) {

		WishListVO wishListVO = new WishListVO();
		
		wishListVO.setMemberId(memberId);
		wishListVO.setProductId(productId);
		wishListVO.setLikeStatus(likeStatus);
		wishListVO.setAddDate(addDate);
		wishListVO.setEditDate(editDate);
		dao.insert(wishListVO);
		
		return wishListVO;
	}

	public WishListVO updateWishList(String wishListId, String memberId,
			String productId,String likeStatus,	Date addDate,Date editDate) {

		WishListVO wishListVO = new WishListVO();
		
		wishListVO.setWishListId(wishListId);
		wishListVO.setMemberId(memberId);
		wishListVO.setProductId(productId);
		wishListVO.setLikeStatus(likeStatus);
		wishListVO.setAddDate(addDate);
		wishListVO.setEditDate(editDate);
		dao.update(wishListVO);
		
		return wishListVO;
	}
	
	public void deleteWishList(String wishListId) {
		dao.delete(wishListId);
	}

	public WishListVO getOneWishList(String wishListId) {
		return dao.findByPrimaryKey(wishListId);
	}

	public List<WishListVO> getAll() {
		return dao.getAll();
	}
	public List<WishListVO> getWishList(String memberId){
		return dao.getWishListByMemberId(memberId);
	}
//	public List<WishListVO> getMemberBlog(String memberId){
//		return dao.findByMemberid(memberId);
//	}
}
