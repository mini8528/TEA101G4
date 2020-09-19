package com.blog_likes.model;

import java.util.List;

public interface Blog_LikesDAO_interface {
	 public void insert(Blog_LikesVO blogLikesVO);
     public void update(Blog_LikesVO blogLikesVO);
     public void delete(String blogLikesno);
     public Blog_LikesVO findByPrimaryKey(String blogLikesno);
     public List<Blog_LikesVO> getAll();
    
}


