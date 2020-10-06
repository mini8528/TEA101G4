package com.blog_save.model;

import java.util.List;

import com.blog_mes.model.Blog_MesVO;

public interface Blog_SaveDAO_interface {
	 public void insert(Blog_SaveVO blogSaveVO);
     public void update(Blog_SaveVO blogSaveVO);
     public void delete(String blogSaveno);
     public Blog_SaveVO findByPrimaryKey(String blogSaveno);
     public List<Blog_SaveVO> getAll();
	 public List<Blog_SaveVO> findByMemberId(String memberId);
	 public Blog_SaveVO findBlogSaveStatus(String blogno, String memberId);
}


