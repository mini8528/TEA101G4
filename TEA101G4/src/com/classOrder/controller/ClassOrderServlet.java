package com.classOrder.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.classDetail.model.ClassDetailVO;
import com.classOrder.model.ClassOrderService;
import com.classOrder.model.ClassOrderVO;

@WebServlet("/back-end/classOrder/classOrder.do")
public class ClassOrderServlet extends HttpServlet{
	
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("==================================================");
		System.out.println("======      Class Order Servlet   ================");
		System.out.println("==================================================");
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("customer_insert".equals(action)) {
			System.out.println("======      customer_insert    ===  start  ===");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {

				String memberID = req.getParameter("memberid").trim();

				String payment = req.getParameter("payment").trim();
				String paymentStatus = req.getParameter("paymentStatus").trim();
				String payCode = req.getParameter("payCode").trim();

				Timestamp orderDate = new java.sql.Timestamp(System.currentTimeMillis());// 下單日

				Date dd = new java.sql.Date(System.currentTimeMillis());
				Date payExpire = new Date(dd.getTime() + 7 * 24 * 60 * 60 * 1000);// 3天內付款

				
				
				
				ClassDetailVO classDetailVO = new ClassDetailVO();

				//蒐集所有coachClassID
				//從網頁抓值(coachClassID)下來, 擺入站存aa
				String[] aa = new String[(req.getParameterValues("coachClassID")).length];

				aa = req.getParameterValues("coachClassID");

				System.out.println("coachClassID-length=>"+aa.length);

				//將aa暫存的資料匯進 規格idAll 的list
				List<String> coachClassIDAll = new ArrayList<String>();
				for(String test:aa) {
					System.out.println("test_id="+test);
					coachClassIDAll.add(test);
				}

//				規格idAll 的List 資料匯進各個 明細 VO
				System.out.println("coachClassIDAll.toString()=>"+coachClassIDAll.toString());
				for(String coachClassID2:coachClassIDAll) {
					classDetailVO.setCoachClassID(coachClassID2);
				}
//				----------------------------------------------------

				
				//收集所有quantity
				String[] bb = new String[(req.getParameterValues("quantity")).length];
				bb = req.getParameterValues("quantity");
				System.out.println("quantity-length=>"+bb.length);
				List<Integer> quantityAll = new ArrayList<Integer>();
				for(String test2:bb) {
					System.out.println("test_qt="+test2);
					Integer ii = Integer.parseInt(test2);
					System.out.println("ii="+ii);
					quantityAll.add(ii);
				}
				System.out.println(quantityAll.toString());
				for(Integer quantity2:quantityAll) {
					classDetailVO.setQuantity(quantity2);
				}
//--------------
				
				ClassOrderVO classOrderVO = new ClassOrderVO();
				classOrderVO.setMemberID(memberID);
				classOrderVO.setPayment(payment);
				
				classOrderVO.setPaymentStatus(paymentStatus);
				classOrderVO.setPayCode(payCode);
				
				classOrderVO.setOrderDate(orderDate);
				classOrderVO.setPayExpire(payExpire);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("classOrderVO", classOrderVO);
//					RequestDispatcher fv = req.getRequestDispatcher("/front-end/classOrderVO/addOrderMaster.jsp");
					RequestDispatcher fv = req.getRequestDispatcher("/back-end/cartClass/CheckoutClass.jsp");
					fv.forward(req, res);
					return;
				}
				
				System.out.println("開始新增資料");
				System.out.println("classOrderVO="+classOrderVO.toString());
				ClassOrderService coService = new ClassOrderService();
				classOrderVO = coService.auto_addClassOrderAndDetail(  memberID,  payment,  paymentStatus,  payExpire,  payCode,  orderDate,  coachClassIDAll,  quantityAll);
				System.out.println("_______  auto_addClassOrderAndDetail __ 完成");
				
				HttpSession session = req.getSession();
				session.removeAttribute("shoppingCartClass");
				
				RequestDispatcher successViw = req.getRequestDispatcher("/front-end/classOrder/listAllClassOrder.jsp");
//				RequestDispatcher successViw = req.getRequestDispatcher("/front-end/orderdetail/one_orderdetail.jsp");
				System.out.println("_______  Class Order Servlet  __  end  ____  轉交  listAllClassOrder.jsp_____");
				successViw.forward(req, res); 
			} catch (Exception e) {
				System.out.println("add:Exception");
				errorMsgs.add("add:Exception");
				e.printStackTrace();
			}
		}
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("classOrderID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入classOrderID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/classOrder/select_page.jsp");
					failureView.forward(req, res);
//					return;//程式中斷
				}
				String classOrderID = null;
				try {
					classOrderID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("classOrderID格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/classOrder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				ClassOrderService coService = new ClassOrderService();
				ClassOrderVO classOrderVO = coService.getOneClassOrder(classOrderID);
				if (classOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/classOrder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("classOrderVO", classOrderVO); // 資料庫取出的classOrderVO物件,存入req
				String url = "/back-end/classOrder/listOneClassOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料 123:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/classOrder/select_page.jsp");
				
				failureView.forward(req, res);
			}
		}
		

		
		if ("getOne_For_Update".equals(action)) { // 來自listAllClassOrder.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String classOrderID = new String(req.getParameter("classOrderID"));
				
				/***************************2.開始查詢資料****************************************/
				ClassOrderService coService = new ClassOrderService();
				ClassOrderVO classOrderVO = coService.getOneClassOrder(classOrderID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("classOrderVO", classOrderVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/classOrder/update_classOrder_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/classOrder/listAllClassOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_classOrder_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String classOrderID = new String(req.getParameter("classOrderID").trim());
				System.out.println("1_1 classOrderID =" + classOrderID);
				
				String memberID = req.getParameter("memberID");
				System.out.println("memberID = " + memberID);
				if (memberID == null || memberID.trim().length() == 0) {
					errorMsgs.add("會員編號不可空白");
				}
				
				System.out.println("2");
				String payment = null;
				payment = req.getParameter("payment").trim();
				if (payment == null || payment.trim().length() == 0) {
					errorMsgs.add("payment請勿空白");
				}
				System.out.println("3");
				
				String paymentStatus = null;
				paymentStatus = req.getParameter("paymentStatus").trim();
				if (paymentStatus == null || paymentStatus.trim().length() == 0) {
					errorMsgs.add("paymentStatus請勿空白");
				}
				
				System.out.println("4");
				String payCode = null;
				payCode = req.getParameter("payCode").trim();
				if (payCode == null || payCode.trim().length() == 0) {
					errorMsgs.add("payCode請勿空白");
				}
				
				System.out.println("5");
				Timestamp orderDate = Timestamp.valueOf(req.getParameter("orderDate"));
				System.out.println("6");
				Date payExpire = Date.valueOf(req.getParameter("payExpire"));
				System.out.println("7");
				
				ClassOrderVO classOrderVO = new ClassOrderVO();
				classOrderVO.setClassOrderID(classOrderID);
				classOrderVO.setMemberID(memberID);
				classOrderVO.setPayment(payment);
				classOrderVO.setPaymentStatus(paymentStatus);
				classOrderVO.setPayExpire(payExpire);
				classOrderVO.setPayCode(payCode);
				classOrderVO.setOrderDate(orderDate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("classOrderVO", classOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/classOrder/update_classOrder_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ClassOrderService coService = new ClassOrderService();
				classOrderVO = coService.updateClassOrder
						(classOrderID, memberID, payment, paymentStatus, payExpire, payCode, orderDate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				
				req.setAttribute("classOrderVO", classOrderVO); // 資料庫update成功後,正確的的ClassOrderVO物件,存入req
				String url = "/back-end/classOrder/listOneClassOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneClassOrder.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/classOrder/update_classOrder_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addClassOrder.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String memberID = req.getParameter("memberID");
				String memberIDReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberID == null || memberID.trim().length() == 0) {
					errorMsgs.add("memberID: 請勿空白");
				} else if(!memberID.trim().matches(memberIDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("memberID: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String payment = req.getParameter("payment");
				
				String paymentStatus = req.getParameter("paymentStatus");
				
				String payCode = null;
				payCode = req.getParameter("payCode");

				Timestamp orderDate = new java.sql.Timestamp(System.currentTimeMillis());
				
				
				Date dd = new java.sql.Date(System.currentTimeMillis());
				Date payExpire = new Date(dd.getTime() + 7 * 24 * 60 * 60 * 1000);
				
				ClassOrderVO classOrderVO = new ClassOrderVO();
				classOrderVO.setMemberID(memberID);
				classOrderVO.setPayment(payment);
				classOrderVO.setPaymentStatus(paymentStatus);
				classOrderVO.setPayExpire(payExpire);
				classOrderVO.setPayCode(payCode);
				classOrderVO.setOrderDate(orderDate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("classOrderVO", classOrderVO); // 含有輸入格式錯誤的ClassDetailVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/classOrder/addClassOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				System.out.println("=== sevlet_insert 開始新增資料 ===");
				
				ClassOrderService coService = new ClassOrderService();
				classOrderVO = coService.addClassOrder
						(memberID, payment, paymentStatus,payExpire, payCode, orderDate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				System.out.println("=== sevlet_insert 新增完成,準備轉交 ===");
				
				String url = "/back-end/classOrder/listAllClassOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllClassOrder.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/classOrder/addClassOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllClassOrder.jsp
				System.out.println("=== servlet_delete start ===");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				System.out.println("=== servlet_delete 接收請求參數 ===");
				
				String classOrderID = new String(req.getParameter("classOrderID"));
				
				/***************************2.開始刪除資料***************************************/
				System.out.println("=== servlet_delete 開始刪除資料 ===");
				
				ClassOrderService coService = new ClassOrderService();
				coService.deleteClassOrder(classOrderID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				System.out.println("=== servlet_delete 刪除完成,準備轉交 ===");
				
				String url = "/back-end/classOrder/listAllClassOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/classOrder/listAllClassOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
	
	}

}
