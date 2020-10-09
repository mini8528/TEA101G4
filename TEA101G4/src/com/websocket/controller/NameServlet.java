package com.websocket.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classDetail.model.ClassDetailService;

public class NameServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String memberid = req.getParameter("memberid"); //可以用memberid
		
//		ClassDetailService ClassDetailSer = new ClassDetailService();
//		List<String> list = ClassDetailSer.studentChat(memberid);
//		System.out.println(list);
		
		req.setAttribute("memberid", memberid);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/front-end/chat/chat.jsp");
		dispatcher.forward(req, res);
	}
}
