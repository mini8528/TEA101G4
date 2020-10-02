package com.blog_mes.model;

import java.util.List;

public class testblogmes {

	public static void main(String[] args) {
		Blog_MesJDBCDAO dao = new Blog_MesJDBCDAO();
		List <Blog_MesVO> list = dao.findByBlogno("B00001");
		for(Blog_MesVO blog : list) {
			System.out.println(blog.getText());
		}
		

	}

}
