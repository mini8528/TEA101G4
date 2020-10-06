package com.blog_save.model;

import java.sql.Timestamp;
import java.util.List;



public class Blog_SaveService {
	private Blog_SaveDAO_interface dao;

	public Blog_SaveService() {
		dao = new Blog_SaveJDBCDAO();
	}

	public Blog_SaveVO addBlogSave(String memberId, String blogno, String status, Timestamp saveDate,
			Timestamp updateTime) {
		Blog_SaveVO blogsaveVO = new Blog_SaveVO();

		blogsaveVO.setMemberId(memberId);
		blogsaveVO.setBlogno(blogno);
		blogsaveVO.setStatus(status);
		blogsaveVO.setSaveDate(saveDate);
		blogsaveVO.setUpdateTime(updateTime);

		dao.insert(blogsaveVO);

		return blogsaveVO;

	}

	public Blog_SaveVO updateBlogSave(String blogsaveno, String memberId, String blogno, String status, Timestamp saveDate,
			Timestamp updateTime) {

		Blog_SaveVO blogsaveVO = new Blog_SaveVO();

		blogsaveVO.setBlogSaveno(blogsaveno);
		blogsaveVO.setMemberId(memberId);
		blogsaveVO.setBlogno(blogno);
		blogsaveVO.setStatus(status);
		blogsaveVO.setSaveDate(saveDate);
		blogsaveVO.setUpdateTime(updateTime);
		
		dao.update(blogsaveVO);

		return blogsaveVO;

	}

	public void deleteBlogSave(String blogsaveno) {
		dao.delete(blogsaveno);
	}

	public Blog_SaveVO getOneBlogSave(String blogsaveno) {
		return dao.findByPrimaryKey(blogsaveno);
	}

	public List<Blog_SaveVO> getAll() {
		return dao.getAll();
	}
	
	public List<Blog_SaveVO> getMemberSaveBlog(String memberId){
		return dao.findByMemberId(memberId);
	}
	
	public Blog_SaveVO getBlogSaveStatus(String blogno, String memberId) {
		return dao.findBlogSaveStatus(blogno, memberId);
	}
	
}
