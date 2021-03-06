package com.cus.controller;

import java.io.*;

import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cus.model.*;

@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024, maxRequestSize = 5*5*1024*1024)
@WebServlet("/back-end/cus/cus.do")
public class CusServlet  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
/*------------------------------------查詢一般會員memberid---------------------------------------------------------------------*/		

		if ("getOne_For_memberid".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String memberid = req.getParameter("memberid");
				if (memberid == null || (memberid.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}

				/***************************2.開始查詢資料*****************************************/
				CusService cusSvc = new CusService();
				List<CusVO> list =cusSvc.getCusByMid(memberid);

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.getSession().setAttribute("list", list);
				String url = "/back-end/cus/listSomeCus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/cus/select_page.jsp");
				failureView.forward(req, res);

			}
		}
		
/*------------------------------------------------------------------------------------------------------------------*/		

		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("customerserviceid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入課服編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/cus/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String customerserviceid = null;
				try {
					customerserviceid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("課服編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/cus/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CusService cusSvc = new CusService();
				CusVO cusVO = cusSvc.getOneCus(customerserviceid);
				if (cusVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/cus/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("cusVO", cusVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/cus/listOneCus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/cus/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
/*------------------------------------------------------------------------------------------------------------------*/		

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String customerserviceid = new String(req.getParameter("customerserviceid"));
				/***************************2.開始查詢資料****************************************/
				CusService cusSvc = new CusService();
				CusVO cusVO = cusSvc.getOneCus(customerserviceid);					
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("cusVO", cusVO);         // 資料庫取出的admVO物件,存入req	
				String url = "/back-end/cus/update_cus_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/cus/listAllCus.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String customerserviceid = new String(req.getParameter("customerserviceid").trim());

				/***************************1a.會員編號**********************/
				String memberReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,300}$";

				/***************************1b.管理員編號**********************/
				String adminid = req.getParameter("adminid").trim();

	
				/***************************1c.電子信箱 **********************/
				String email = req.getParameter("email");

				/***************************1d.回覆內容**********************/
				String replytext = req.getParameter("replytext");

				if (replytext == null || replytext.trim().length() == 0) {
					errorMsgs.add("回覆內容請勿空白");
				} 

				/***************************1e.回覆日期**********************/
				java.sql.Date replydate = null;
				replydate=new java.sql.Date(System.currentTimeMillis());

/*------------------------------------------------------------------------------------------------------------------*/		

				
				CusVO cusVO = new CusVO();
			
				cusVO.setCustomerserviceid(customerserviceid);
				cusVO.setAdminid(adminid);
				cusVO.setReplytext(replytext);
				cusVO.setReplydate(replydate);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cusVO", cusVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/cus/update_cus_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/***************************2.開始修改資料*****************************************/
				CusService cusSvc = new CusService();
				cusVO = cusSvc.updateCus(customerserviceid, adminid, replytext, replydate);
				/***************************2.開始傳送EMAIL*****************************************/
			    MailService mailService = new MailService();
			    String subject = "GYMPAYZ通知";
			    mailService.sendMail(email, subject, replytext);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("cusVO", cusVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/cus/listOneCus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/cus/update_cus_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1a.會員編號**********************/
				String memberid = req.getParameter("memberid");
				String memberReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,300}$";


				/***************************1b.主旨**********************/
				String subject = req.getParameter("subject").trim();
				
				if (subject == null || subject.trim().length() == 0) {
					errorMsgs.add("主旨空白");
				}else if(!subject.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("主旨只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }	
				/***************************1c.電子信箱 **********************/
				String email = req.getParameter("email").trim();
				
				if (email == null || email.trim().length() == 0 ) {
					errorMsgs.add("電子信箱請勿空白");
				}

				/***************************1d.問題描述**********************/
				String problemtext = req.getParameter("problemtext").trim();
				
				if (problemtext == null || problemtext.trim().length() == 0 ) {
					errorMsgs.add("問題描述請勿空白");
				}else if(!problemtext.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("問題描述只能是中、英文字母、數字和_ , 且長度必需在1到300s之間");
	            }
				/***************************1d.客訴日期**********************/
				java.sql.Date complaintdate = null;
				complaintdate=new java.sql.Date(System.currentTimeMillis());
				
				

/*------------------------------------------------------------------------------------------------------------------*/		

				CusVO cusVO = new CusVO();
				
				cusVO.setMemberid(memberid);
				cusVO.setSubject(subject);
				cusVO.setEmail(email);
				cusVO.setProblemtext(problemtext);
				cusVO.setComplaintdate(complaintdate);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cusVO", cusVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/cus/addCus.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				CusService cusSvc = new CusService();
				cusVO = cusSvc.addCus(memberid, subject, email, problemtext, complaintdate);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/cus/addCus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/cus/addCus.jsp");
				failureView.forward(req, res);
			}
		}
/*------------------------------------------------------------------------------------------------------------------*/		

		if ("delete".equals(action)) { // 來自listAllEmp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String customerserviceid = new String(req.getParameter("customerserviceid"));
				
				/***************************2.開始刪除資料***************************************/
				CusService cisSvc = new CusService();
				cisSvc.deleteAdm(customerserviceid);;
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/cus/listAllCus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/cus/listAllCus.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
}
