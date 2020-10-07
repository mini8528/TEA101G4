package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MemberLogout")
public class MemberLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberLogout() {
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		session.invalidate();
//		session.removeAttribute("userVO");
		String url = req.getContextPath() + "/front-end/blog/listAllBlog.jsp"; 
		res.sendRedirect(url);
		

//		res.sendRedirect(req.getContextPath() + "/front-end/login.jsp");

	}

}
