package com.blog_mes.model;

import java.util.List;

public interface Blog_MesDAO_interface {
	 public void insert(Blog_MesVO blogMesVO);
     public void update(Blog_MesVO blogMesVO);
     public void delete(String blogMesno);
     public Blog_MesVO findByPrimaryKey(String blogMesno);
     public List<Blog_MesVO> getAll();
    
}


