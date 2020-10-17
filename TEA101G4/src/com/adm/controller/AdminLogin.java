package com.adm.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adm.model.AdminnoService;
import com.adm.model.AdminnoVO;



@WebServlet("/AdminiLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");

		HttpSession session = req.getSession();
		AdminnoService memSer = new AdminnoService();
		List<String> errorMsgs = new LinkedList<String>();
		RequestDispatcher error = req.getRequestDispatcher("/back-end/back-login.jsp");
System.out.print("1");
		String memberuser = req.getParameter("memberuser");
		if (memberuser == null || (memberuser.trim()).length() == 0) {
			errorMsgs.add("請輸入帳號");
			req.setAttribute("errorMsgs", errorMsgs);
			error.forward(req, res);
			return;
		}
		System.out.print("1");
		String passwd = req.getParameter("passwd");
		if (passwd == null || (passwd.trim()).length() == 0) {
			errorMsgs.add("請輸入密碼");
			req.setAttribute("errorMsgs", errorMsgs);
			error.forward(req, res);
			return;
		}
		System.out.print("1");
		req.setAttribute("memberuser", memberuser);
		req.setAttribute("passwd", passwd);

		System.out.println(memberuser + passwd);

		AdminnoVO userVO = null;
		System.out.print("1");
		if (errorMsgs.isEmpty()) {
			try {
				//帳號比對
				userVO = memSer.getAlluser(memberuser);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("查無此帳號");
//				System.out.println("查無此帳號");
				req.setAttribute("errorMsgs", errorMsgs);
				error.forward(req, res);
				return;
			}
			System.out.print("1");
			if(userVO == null) {
				errorMsgs.add("查無此帳號");
//				System.out.println("查無此帳號2");
				req.setAttribute("errorMsgs", errorMsgs);
				error.forward(req, res);
				return;
			}
			
			//密碼正確，存userVO
			if (userVO.getPasswd().equals(passwd)) {
				
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

		res.sendRedirect(req.getContextPath() + "/back-end/index.jsp"); // *工作3:
																					// (-->如無來源網頁:則重導至login_success.jsp)
	}

}