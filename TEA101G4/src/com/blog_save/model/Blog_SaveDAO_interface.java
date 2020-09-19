package com.blog_save.model;

import java.util.List;

public interface Blog_SaveDAO_interface {
	 public void insert(Blog_SaveVO blogSaveVO);
     public void update(Blog_SaveVO blogSaveVO);
     public void delete(String blogSaveno);
     public Blog_SaveVO findByPrimaryKey(String blogSaveno);
     public List<Blog_SaveVO> getAll();
    
}


