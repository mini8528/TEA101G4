package com.orderdetail.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderdetail.model.OrderdetailService;
import com.orderdetail.model.OrderdetailVO;
import com.ordermaster.model.OrdermasterService;
import com.ordermaster.model.OrdermasterVO;
@WebServlet("/back-end/orderdetail/orderdetail.do")
public class OrderdetailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//		---------------------------------------------------------------------------------------------------------------------------
//		首頁/查詢部分(依訂單ID)
		if ("getSomeList_ordermasterid".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
//				1.接收請求參數
				String ordermasterid = new String(req.getParameter("ordermasterid"));
System.out.println("getSomeList_ordermasterid:action---2");
//				2.開始查詢資料
				OrderdetailService orderdetailSvc = new OrderdetailService();
				List<OrderdetailVO> list = orderdetailSvc.getDetailByMaster(ordermasterid);
				System.out.println("符合的Orderdetail共：" + list.size());
				req.getSession().setAttribute("list", list);
System.out.println("getSomeList_ordermasterid:action---3");
//				3.查詢完成,準備轉交
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/orderdetail/listSomeOrderdetail.jsp");
				successView.forward(req, res);
System.out.println("getSomeList_ordermasterid:action---4");
//				其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orderdetail/select_page.jsp");
				failureView.forward(req, res);
			}
			System.out.println("end------getSomeList_ordermasterid");
		}
		
//		---------------------------------------------------------------------------------------------------------------------------
//		成立訂單
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String ordermasterid = new String(req.getParameter("ordermasterid")).trim();
				System.out.println("ordermasterid="+ordermasterid);
				String specid = null;
				specid = new String(req.getParameter("specid").trim());
				System.out.println("specid="+specid);
				String qq = req.getParameter("quantity").trim();
//			輸入錯誤的處理（沒輸入或空白｜不符合正規表示｜轉跳頁面）
				String qqReg = "^\\d{1,60}$";
				if (qq == null || qq.trim().length() == 0) {
					errorMsgs.add("庫存：請勿空白。");
				} else if (!qq.trim().matches(qqReg)) {
					errorMsgs.add("庫存：只能是數字");
				}
				Integer quantity = new Integer(qq);
				System.out.println("quantity="+quantity);
//--------------
				OrderdetailVO orderdetailVO = new OrderdetailVO();
				orderdetailVO.setOrdermasterid(ordermasterid);
				orderdetailVO.setSpecid(specid);
				orderdetailVO.setQuantity(quantity);
				System.out.println(orderdetailVO.toString());
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderdetailVO", orderdetailVO);
					RequestDispatcher fv = req.getRequestDispatcher("/back-end/orderdetail/addOrderDetail.jsp");
					fv.forward(req, res);
					return;
				}
//			開始新增資料
				OrderdetailService orderdetailSvc = new OrderdetailService();
				System.out.println("ordermasterid:"+ordermasterid);
				System.out.println("specid:"+specid);
				System.out.println("quantity:"+quantity);
//				orderdetailVO = orderdetailSvc.addOrderdetail("OR00002", "SD0002", 2);
				orderdetailVO = orderdetailSvc.addOrderdetail(ordermasterid, specid, quantity);//????
				RequestDispatcher successViw = req.getRequestDispatcher("/back-end/orderdetail/listAllorderdetail.jsp");
				successViw.forward(req, res);
			} catch (Exception e) {
				System.out.println("add:---Exception---");
				errorMsgs.add("add:Exception");
				e.printStackTrace();
			}
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		list-編輯單筆	
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// 接收請求參數
				String orderdetailid = new String(req.getParameter("orderdetailid"));
				System.out.println("orderdetailid="+orderdetailid);
				// 開始查詢該筆資料
				OrderdetailService orderdetailSvc = new OrderdetailService();
				OrderdetailVO orderdetailVO = orderdetailSvc.getOneOrderdetail(orderdetailid);
				// 查詢完成，準備轉交
				System.out.println("查詢完成，準備轉交");
				req.setAttribute("orderdetailVO", orderdetailVO);
				System.out.println("orderdetailVO");
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/orderdetail/update_orderdetail.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				System.out.println("list-編輯單筆=Exception");
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/orderdetail/listAllorderdetail.jsp");
				failureView.forward(req, res);
			}
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		進入編輯單筆明細頁面
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String orderdetailid = new String(req.getParameter("orderdetailid"));
				String ordermasterid = new String(req.getParameter("ordermasterid")).trim();
				System.out.println("ordermasterid="+ordermasterid);
				String specid = null;
				specid = new String(req.getParameter("specid"));
				System.out.println("specid="+specid);
				String qq = req.getParameter("quantity").trim();
//			輸入錯誤的處理（沒輸入或空白｜不符合正規表示｜轉跳頁面）
				String qqReg = "^\\d{1,60}$";
				if (qq == null || qq.trim().length() == 0) {
					errorMsgs.add("庫存：請勿空白。");
				} else if (!qq.trim().matches(qqReg)) {
					errorMsgs.add("庫存：只能是數字");
				}
				Integer quantity = new Integer(qq);
				System.out.println("quantity="+quantity);
//--------------
				OrderdetailVO orderdetailVO = new OrderdetailVO();
				orderdetailVO.setOrderdetailid(orderdetailid);
				orderdetailVO.setOrdermasterid(ordermasterid);
				orderdetailVO.setSpecid(specid);
				orderdetailVO.setQuantity(quantity);
				System.out.println(orderdetailVO.toString());
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderdetailVO", orderdetailVO);
					RequestDispatcher fv = req.getRequestDispatcher("/back-end/orderdetail/update_orderdetail.jsp");
					fv.forward(req, res);
					return;
				}
//			開始修改資料
				OrderdetailService orderdetailSvc = new OrderdetailService();
				orderdetailVO = orderdetailSvc.updateOrderdetail(orderdetailid, ordermasterid, specid, quantity);
				RequestDispatcher successViw = req.getRequestDispatcher("/back-end/ordermaster/listAllordermaster.jsp");
				successViw.forward(req, res);
			} catch (Exception e) {
				System.out.println("add:Exception");
				errorMsgs.add("修改失敗：" + e.getMessage());
				RequestDispatcher failure = req.getRequestDispatcher("/back-end/orderdetail/update_orderdetail.jsp");
				failure.forward(req, res);
			}
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		delete
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String orderdetailid = new String(req.getParameter("orderdetailid"));
//			2.開始刪除資料
				OrderdetailService orderdetailSvc = new OrderdetailService();
				orderdetailSvc.deleteOrderdetail(orderdetailid);
//			3.刪除完成,準備轉交(Send the Success view)
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/orderdetail/listAllorderdetail.jsp");
				successView.forward(req, res);
//				其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orderdetail/listAllorderdetail.jsp");
				failureView.forward(req, res);
			}
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		(會員)瀏覽訂單明細(依訂單ID)
		if ("getOneList_ordermasterid".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
//				1.接收請求參數
				String ordermasterid = new String(req.getParameter("ordermasterid"));
//				2.開始查詢資料
				OrderdetailService orderdetailSvc = new OrderdetailService();
				List<OrderdetailVO> list = orderdetailSvc.getDetailByMaster(ordermasterid);
				req.getSession().setAttribute("list", list);
//				3.查詢完成,準備轉交
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/orderdetail/one_orderdetail.jsp");
				successView.forward(req, res);
//				其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ordermaster/list_ordermaster.jsp");
				failureView.forward(req, res);
			}
		}
		
//		---------------------------------------------------------------------------------------------------------------------------

	}
}