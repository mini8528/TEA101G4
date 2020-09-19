package com.chat.model;

import java.util.List;

public interface ChatDAO_interface {
	 public void insert(ChatVO chatVO);
     public void update(ChatVO chatVO);
     public void delete(String chatno);
     public ChatVO findByPrimaryKey(String chatno);
     public List<ChatVO> getAll();
    
}


