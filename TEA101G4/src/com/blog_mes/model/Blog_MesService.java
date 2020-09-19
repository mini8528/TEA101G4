package com.blog_mes.model;

import java.sql.Timestamp;
import java.util.List;


public class Blog_MesService {
	private Blog_MesDAO_interface dao;

	public Blog_MesService() {
		dao = new Blog_MesJDBCDAO();
	}
	
	public Blog_MesVO addBlogMes(String blogno, String memberId, String text, Timestamp postDate, Timestamp updateTime, 
			String status) {
		Blog_MesVO blogmesVO = new Blog_MesVO();

		blogmesVO.setBlogno(blogno);
		blogmesVO.setMemberId(memberId);
		blogmesVO.setText(text);
		blogmesVO.setPostDate(postDate);
		blogmesVO.setUpdateTime(updateTime);
		blogmesVO.setStatus(status);

		dao.insert(blogmesVO);

		return blogmesVO;

	}

	public Blog_MesVO updateBlogMes(String blogmesno, String blogno, String memberId, String text, Timestamp postDate, Timestamp updateTime, 
			String status) {

		Blog_MesVO blogmesVO = new Blog_MesVO();
		
		blogmesVO.setBlogMesno(blogmesno);
		blogmesVO.setBlogno(blogno);
		blogmesVO.setMemberId(memberId);
		blogmesVO.setText(text);
		blogmesVO.setPostDate(postDate);
		blogmesVO.setUpdateTime(updateTime);
		blogmesVO.setStatus(status);
		
		dao.update(blogmesVO);

		return blogmesVO;
	}

	public void deleteBlogMes(String blogmesno) {
		dao.delete(blogmesno);
	}

	public Blog_MesVO getOneBlogMes(String blogmesno) {
		return dao.findByPrimaryKey(blogmesno);
	}

	public List<Blog_MesVO> getAll() {
		return dao.getAll();
	}

}
