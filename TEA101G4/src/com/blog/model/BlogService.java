package com.blog.model;

import java.sql.Timestamp;
import java.util.List;

public class BlogService {
	private BlogDAO_interface dao;

	public BlogService() {
		dao = new BlogJDBCDAO();
	}

	public BlogVO addBlog(String memberId, String blogClass, Timestamp postDate, String title, String text,
			byte[] photo, byte[] video, String status, Timestamp updateTime) {
		BlogVO blogVO = new BlogVO();
		
		blogVO.setMemberId(memberId);
		blogVO.setBlogClass(blogClass);
		blogVO.setPostDate(postDate);
		blogVO.setTitle(title);
		blogVO.setText(text);
		blogVO.setPhoto(photo);
		blogVO.setVideo(video);
		blogVO.setStatus(status);
		blogVO.setUpdateTime(updateTime);
		
		dao.insert(blogVO);
		
		return blogVO;

	}
	
	public BlogVO updateBlog(String blogno, String memberId, String blogClass, Timestamp postDate, String title, String text,
			byte[] photo, byte[] video, String status, Timestamp updateTime) {
		BlogVO blogVO = new BlogVO();
		
		blogVO.setBlogno(blogno);
		blogVO.setMemberId(memberId);
		blogVO.setBlogClass(blogClass);
		blogVO.setPostDate(postDate);
		blogVO.setTitle(title);
		blogVO.setText(text);
		blogVO.setPhoto(photo);
		blogVO.setVideo(video);
		blogVO.setStatus(status);
		blogVO.setUpdateTime(updateTime);
		
		dao.update(blogVO);
		
		return blogVO;

	}
	
	public void deleteBlog(String blogno) {
		dao.delete(blogno);
	}
	
	public BlogVO getOneBlog(String blogno) {
		return dao.findByPrimaryKey(blogno);
	}
	
	public List<BlogVO> getAll() {
		return dao.getAll();
	}
	
	public List<BlogVO> getTitle(String title){
		return dao.searchTitle("%" + title + "%");
	}
	
	public List<BlogVO> getMemberBlog(String memberId){
		return dao.findByMemberid(memberId);
	}
}
