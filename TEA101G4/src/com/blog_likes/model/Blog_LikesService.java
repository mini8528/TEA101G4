package com.blog_likes.model;

import java.sql.Timestamp;
import java.util.List;

public class Blog_LikesService {
	private Blog_LikesDAO_interface dao;

	public Blog_LikesService() {
		dao = new Blog_LikesDAO();
	}

	public Blog_LikesVO addBlogLikes(String blogno, String memberId, String status, Timestamp likesDate,
			Timestamp updateTime) {
		Blog_LikesVO bloglikesVO = new Blog_LikesVO();

		bloglikesVO.setBlogno(blogno);
		bloglikesVO.setMemberId(memberId);
		bloglikesVO.setStatus(status);
		bloglikesVO.setLikesDate(likesDate);
		bloglikesVO.setUpdateTime(updateTime);

		dao.insert(bloglikesVO);

		return bloglikesVO;

	}

	public Blog_LikesVO updateBlogLikes(String bloglikesno,String blogno, String memberId, String status, Timestamp likesDate,
			Timestamp updateTime) {

		Blog_LikesVO bloglikesVO = new Blog_LikesVO();

		bloglikesVO.setBlogLikesno(bloglikesno);
		bloglikesVO.setBlogno(blogno);
		bloglikesVO.setMemberId(memberId);
		bloglikesVO.setStatus(status);
		bloglikesVO.setLikesDate(likesDate);
		bloglikesVO.setUpdateTime(updateTime);
		
		dao.update(bloglikesVO);

		return bloglikesVO;

	}

	public void deleteBlogLikes(String bloglikesno) {
		dao.delete(bloglikesno);
	}

	public Blog_LikesVO getOneBlogLikes(String bloglikesno) {
		return dao.findByPrimaryKey(bloglikesno);
	}

	public List<Blog_LikesVO> getAll() {
		return dao.getAll();
	}
	//
	public Blog_LikesVO getRecordStatus(String blogno, String memberId) {
		return dao.findRecordStatus(blogno, memberId);
	}

	public List<Blog_LikesVO> getBlogLikes(String blogno) {
		return dao.findByBlogno(blogno);
	}
}
