package com.member.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.sun.swing.internal.plaf.metal.resources.metal;

import javax.servlet.annotation.WebServlet;

@WebServlet("/loginhandler")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");

		HttpSession session = req.getSession();
		MemberService memSer = new MemberService();
		List<String> errorMsgs = new LinkedList<String>();
		RequestDispatcher error = req.getRequestDispatcher("/front-end/login.jsp");

		String account = req.getParameter("account");
		if (account == null || (account.trim()).length() == 0) {
			errorMsgs.add("請輸入帳號");
			req.setAttribute("errorMsgs", errorMsgs);
			error.forward(req, res);
			return;
		}

		String password = req.getParameter("password");
		if (password == null || (password.trim()).length() == 0) {
			errorMsgs.add("請輸入密碼");
			req.setAttribute("errorMsgs", errorMsgs);
			error.forward(req, res);
			return;
		}
		
		req.setAttribute("account", account);
		req.setAttribute("password", password);

		System.out.println(account + password);

		MemberVO userVO = null;

		if (errorMsgs.isEmpty()) {
			try {
				//帳號比對
				userVO = memSer.getOneAccount(account);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("查無此帳號");
//				System.out.println("查無此帳號");
				req.setAttribute("errorMsgs", errorMsgs);
				error.forward(req, res);
				return;
			}
			
			if(userVO == null) {
				errorMsgs.add("查無此帳號");
//				System.out.println("查無此帳號2");
				req.setAttribute("errorMsgs", errorMsgs);
				error.forward(req, res);
				return;
			}
			
			//密碼正確，存userVO
			if (userVO.getPassword().equals(password)) {
				
				session.setAttribute("userVO", userVO);
			
			} else {
				errorMsgs.add("輸入密碼錯誤");
				req.setAttribute("errorMsgs", errorMsgs);
				error.forward(req, res);
				return;
			}
		} else {
			req.setAttribute("errorMsgs", errorMsgs);
			error.forward(req, res);
			return;

		}

			try {
				String location = (String) session.getAttribute("location");
				System.out.println("login" + location);
				if (location != null) {
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		res.sendRedirect(req.getContextPath() + "/front-end/blog/listAllBlog.jsp"); // *工作3:
																					// (-->如無來源網頁:則重導至login_success.jsp)
	}

}