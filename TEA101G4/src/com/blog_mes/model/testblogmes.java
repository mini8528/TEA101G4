package com.blog_mes.model;

import java.util.List;

public class testblogmes {

	public static void main(String[] args) {
		Blog_MesDAO dao = new Blog_MesDAO();
//		List <Blog_MesVO> list = dao.findByBlogno("B00001");
//		for(Blog_MesVO blog : list) {
//			System.out.println(blog.getText());
//		}
		
		List<Blog_MesVO> list = dao.searchText("胖");
		System.out.println(list);
		

	}

}
