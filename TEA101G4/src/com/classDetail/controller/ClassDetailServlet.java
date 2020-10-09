package com.classDetail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classDetail.model.ClassDetailService;
import com.classDetail.model.ClassDetailVO;

@WebServlet("/back-end/classDetail/classDetail.do")
public class ClassDetailServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("classDetailID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入classDetailID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/classDetail/select_page.jsp");
					failureView.forward(req, res);
//					return;//程式中斷
				}
				
				String classDetailID = null;
				try {
					classDetailID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("classDetailID格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/classDetail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ClassDetailService cdService = new ClassDetailService();
				ClassDetailVO classDetailVO = cdService.getOneClassDetail(classDetailID);
				if (classDetailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/classDetail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("classDetailVO", classDetailVO); // 資料庫取出的classDetailVO物件,存入req
				String url = "/back-end/classDetail/listOneClassDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/classDetail/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		

		
		if ("getOne_For_Update".equals(action)) { // 來自listAllClassDetail.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String classDetailID = new String(req.getParameter("classDetailID"));
				
				/***************************2.開始查詢資料****************************************/
				ClassDetailService cdService = new ClassDetailService();
				ClassDetailVO classDetailVO = cdService.getOneClassDetail(classDetailID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("classDetailVO", classDetailVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/classDetail/update_classDetail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/classDetail/listAllClassDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_classDetail_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String classDetailID = new String(req.getParameter("classDetailID").trim());
				String classOrderID = req.getParameter("classOrderID");
				String classOrderIDReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (classOrderID == null || classOrderID.trim().length() == 0) {
					errorMsgs.add("classOrderID: 請勿空白");
				} else if(!classOrderID.trim().matches(classOrderIDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("classOrderID: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				String coachClassID = req.getParameter("coachClassID");
				String coachClassIDReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (coachClassID == null || coachClassID.trim().length() == 0) {
					errorMsgs.add("coachClassID: 請勿空白");
				} else if(!coachClassID.trim().matches(coachClassIDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("coachClassID: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }	
				
				
				Integer quantity=0;
				quantity = new Integer(req.getParameter("quantity").trim());
				System.out.println("req.getParameter(\"quantity\").trim() = "+req.getParameter("quantity").trim());
				
//				if( quantity.toString()==null || quantity.toString().trim().length() == 0) {
//					errorMsgs.add("quantity: 請勿空白");
//				}
				
				
				if (quantity < 0) {
					errorMsgs.add("數量: 請勿小於 0 ");
				} 
				
				
				
				
				
				
				ClassDetailVO classDetailVO = new ClassDetailVO();
				classDetailVO.setClassDetailID(classDetailID);
				classDetailVO.setClassOrderID(classOrderID);
				classDetailVO.setCoachClassID(coachClassID);
				classDetailVO.setQuantity(quantity);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("classDetailVO", classDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/classDetail/update_classDetail_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ClassDetailService cdService = new ClassDetailService();
				classDetailVO = cdService.updateClassDetail
						(classDetailID, classOrderID, coachClassID, quantity);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				
				req.setAttribute("classDetailVO", classDetailVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/classDetail/listOneClassDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/classDetail/update_classDetail_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addClassDetail.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String classOrderID = req.getParameter("classOrderID");
				String classOrderIDReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (classOrderID == null || classOrderID.trim().length() == 0) {
					errorMsgs.add("classOrderID: 請勿空白");
				} else if(!classOrderID.trim().matches(classOrderIDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("classOrderID: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String coachClassID = req.getParameter("coachClassID");
				String coachClassIDReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (coachClassID == null || coachClassID.trim().length() == 0) {
					errorMsgs.add("coachClassID: 請勿空白");
				} else if(!coachClassID.trim().matches(coachClassIDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("coachClassID: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }	
				
				Integer quantity = new Integer(req.getParameter("quantity").trim());

				ClassDetailVO classDetailVO = new ClassDetailVO();
				classDetailVO.setClassOrderID(classOrderID);
				classDetailVO.setCoachClassID(coachClassID);
				classDetailVO.setQuantity(quantity);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("classDetailVO", classDetailVO); // 含有輸入格式錯誤的ClassDetailVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/classDetail/addClassDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ClassDetailService cdService = new ClassDetailService();
				classDetailVO = cdService.addClassDetail
						(classOrderID, coachClassID, quantity);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/classDetail/listAllClassDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllClassDetail.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/classDetail/addClassDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllClassDetail.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String classDetailID = new String(req.getParameter("classDetailID"));
				
				/***************************2.開始刪除資料***************************************/
				ClassDetailService ccService = new ClassDetailService();
				ccService.deleteClassDetail(classDetailID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/classDetail/listAllClassDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/classDetail/listAllClassDetail.jsp");
				failureView.forward(req, res);
			}
		}
	
	}
}
