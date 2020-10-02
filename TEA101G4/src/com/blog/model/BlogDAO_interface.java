package com.blog.model;

import java.util.List;

public interface BlogDAO_interface {
	 public void insert(BlogVO blogVO);
     public void update(BlogVO blogVO);
     public void delete(String blogno);
     public BlogVO findByPrimaryKey(String blogno);
     public List<BlogVO> getAll();
	 public List<BlogVO> searchTitle(String title);
    
}


