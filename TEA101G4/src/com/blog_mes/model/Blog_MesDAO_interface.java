package com.blog_mes.model;

import java.sql.Timestamp;
import java.util.List;

public interface Blog_MesDAO_interface {
	 public void insert(Blog_MesVO blogMesVO);
     public void update(Blog_MesVO blogMesVO);
     public void delete(String blogMesno);
     public Blog_MesVO findByPrimaryKey(String blogMesno);
     public List<Blog_MesVO> getAll();
     public List<Blog_MesVO> findByBlogno(String blogno);
	 public void changeStatus(String blogmesno, String status, Timestamp updatetime);
	 public List<Blog_MesVO> searchText(String text);
    
}


