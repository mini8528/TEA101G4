package com.ordermaster.controller;

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

import com.orderdetail.model.OrderdetailService;
import com.orderdetail.model.OrderdetailVO;
import com.ordermaster.model.OrdermasterService;
import com.ordermaster.model.OrdermasterVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.spec.model.SpecService;
import com.spec.model.SpecVO;

@WebServlet("/back-end/ordermaster/ordermaster.do")
public class OrdermasterServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action=" + action);
//		-----------------------------------------
//		首頁-搜單筆
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String str = req.getParameter("ordermasterid");
//			錯誤處理
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ordermaster/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
//			傳入字串格式有問題，拋出例外，顯示錯誤訊息｜轉跳頁面
				String ordermasterid = null;
				try {
					ordermasterid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ordermaster/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
//			2.開始查詢
				OrdermasterService orderSvc = new OrdermasterService();
				OrdermasterVO ordermasterVO = (OrdermasterVO) orderSvc.getOneOrdermaster(ordermasterid);
				if (ordermasterVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ordermaster/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
//			3.查詢完成，準備轉交
				req.setAttribute("ordermasterVO", ordermasterVO);
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/ordermaster/listOneOrdermaster.jsp");
				successView.forward(req, res);
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ordermaster/select_page.jsp");
				failureView.forward(req, res);
			}
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		首頁/查詢部分(依會員ID)
		if ("getSomeList_memberid".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
//				1.接收請求參數
				String memberid = new String(req.getParameter("memberid"));
				System.out.println("getSomeList:action---2");
//				2.開始查詢資料
				OrdermasterService ordermasterSvc = new OrdermasterService();
				List<OrdermasterVO> list = ordermasterSvc.getOrderByMemberid(memberid);
				System.out.println("符合的Ordermaster共：" + list.size());
				req.getSession().setAttribute("list", list);
				System.out.println("getSomeList:action---3");
//				3.查詢完成,準備轉交
//				req.setAttribute("productVO", productVO);
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/ordermaster/listSomeOrdermaster.jsp");
				successView.forward(req, res);
				System.out.println("getSomeList:action---4");
//				其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ordermaster/select_page.jsp");
				failureView.forward(req, res);
			}
			System.out.println("end------getSomeList");
		}

//		---------------------------------------------------------------------------------------------------------------------------
//		管理員/查詢部分(依出貨狀態)
		if ("getSomeList_orderstatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
//				1.接收請求參數
				String orderstatus = new String(req.getParameter("orderstatus"));
				System.out.println("getSomeList:action---2");
//				2.開始查詢資料
				OrdermasterService ordermasterSvc = new OrdermasterService();
				List<OrdermasterVO> list = ordermasterSvc.getOrderByOrderstatus(orderstatus);
				System.out.println("符合的Ordermaster共：" + list.size());
				req.getSession().setAttribute("list", list);
				System.out.println("getSomeList:action---3");
//				3.查詢完成,準備轉交
//				req.setAttribute("productVO", productVO);
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/ordermaster/listSomeOrdermaster.jsp");
				successView.forward(req, res);
				System.out.println("getSomeList:action---4");
//				其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ordermaster/select_page.jsp");
				failureView.forward(req, res);
			}
			System.out.println("end------getSomeList");
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		成立訂單
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String receiver = req.getParameter("receiver").trim();
//			輸入錯誤的處理（沒輸入或空白｜不符合正規表示｜轉跳頁面）
				String receiverReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,60}$";
				if (receiver == null || receiver.trim().length() == 0) {
					errorMsgs.add("收件人：請勿空白。");
				} else if (!receiver.trim().matches(receiverReg)) {
					errorMsgs.add("收件人：只能是中、英文字母,且長度必需在2到60之間");
				}
				String tel = req.getParameter("tel").trim();
				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.add("請填入電話");
				}
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("請填入地址");
				}
				String memberid = req.getParameter("memberid");
				System.out.println("memberid" + memberid);
				if (memberid == null || memberid.trim().length() == 0) {
					errorMsgs.add("會員編號不可空白");
				}
				String payment = req.getParameter("payment");
				String paystatus = req.getParameter("paystatus");

				Timestamp orderdate = new java.sql.Timestamp(System.currentTimeMillis());// 下單日
				Date dd = new java.sql.Date(System.currentTimeMillis());
				Date payexpire = new Date(dd.getTime() + 3 * 24 * 60 * 60 * 1000);// 3天內付款
				String paycode = null;

				paycode = req.getParameter("paycode");
				String orderstatus = "未出貨";
//--------------
				OrdermasterVO ordermasterVO = new OrdermasterVO();
				ordermasterVO.setMemberid(memberid);
				ordermasterVO.setPayment(payment);
				ordermasterVO.setPaystatus(paystatus);
				ordermasterVO.setOrderdate(orderdate);
				ordermasterVO.setPayexpire(payexpire);
				ordermasterVO.setPaycode(paycode);
				ordermasterVO.setReceiver(receiver);
				ordermasterVO.setTel(tel);
				ordermasterVO.setAddress(address);
				ordermasterVO.setOrderstatus(orderstatus);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordermasterVO", ordermasterVO);
					RequestDispatcher fv = req.getRequestDispatcher("/back-end/ordermaster/addOrderMaster.jsp");
					fv.forward(req, res);
					return;
				}
//			開始新增資料
				OrdermasterService ordermasterSvc = new OrdermasterService();
				ordermasterVO = ordermasterSvc.addOrdermaster(memberid, payment, paystatus, orderdate, payexpire,
						paycode, receiver, tel, address, orderstatus);
				RequestDispatcher successViw = req.getRequestDispatcher("/back-end/ordermaster/listAllordermaster.jsp");
				successViw.forward(req, res);
			} catch (Exception e) {
				System.out.println("add:Exception");
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
				String ordermasterid = new String(req.getParameter("ordermasterid"));
				// 開始查詢該筆資料
				OrdermasterService ordermasterSvc = new OrdermasterService();
				OrdermasterVO ordermasterVO = ordermasterSvc.getOneOrdermaster(ordermasterid);
				// 查詢完成，準備轉交
				System.out.println("查詢完成，準備轉交");
				req.setAttribute("ordermasterVO", ordermasterVO);
				System.out.println("ordermasterVO");
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/ordermaster/update_ordermaster.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				System.out.println("list-編輯單筆=Exception");
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/ordermaster/listAllordermaster.jsp");
				failureView.forward(req, res);
			}
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		進入編輯單筆商品頁面
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String ordermasterid = new String(req.getParameter("ordermasterid"));
				String receiver = req.getParameter("receiver");
//			輸入錯誤的處理（沒輸入或空白｜不符合正規表示｜轉跳頁面）
				String receiverReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,60}$";
				if (receiver == null || receiver.trim().length() == 0) {
					errorMsgs.add("收件人：請勿空白。");
				} else if (!receiver.trim().matches(receiverReg)) {
					errorMsgs.add("收件人：只能是中、英文字母,且長度必需在2到60之間");
				}
				String tel = req.getParameter("tel").trim();
				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.add("請填入電話");
				}
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("請填入地址");
				}
				String memberid = req.getParameter("memberid");
				System.out.println("memberid" + memberid);
				if (memberid == null || memberid.trim().length() == 0) {
					errorMsgs.add("會員編號不可空白");
				}
				String payment = req.getParameter("payment");
				String paystatus = req.getParameter("paystatus");
				Timestamp orderdate = Timestamp.valueOf(req.getParameter("orderdate"));
				Date payexpire = Date.valueOf(req.getParameter("payexpire"));
				String paycode = null;
				paycode = req.getParameter("paycode");
				String orderstatus = req.getParameter("orderstatus");
//--------------
				OrdermasterVO ordermasterVO = new OrdermasterVO();
				ordermasterVO.setOrdermasterid(ordermasterid);
				ordermasterVO.setMemberid(memberid);
				ordermasterVO.setPayment(payment);
				ordermasterVO.setPaystatus(paystatus);
				ordermasterVO.setOrderdate(orderdate);
				ordermasterVO.setPayexpire(payexpire);
				ordermasterVO.setPaycode(paycode);
				ordermasterVO.setReceiver(receiver);
				ordermasterVO.setTel(tel);
				ordermasterVO.setAddress(address);
				ordermasterVO.setOrderstatus(orderstatus);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordermasterVO", ordermasterVO);
					RequestDispatcher fv = req.getRequestDispatcher("/back-end/ordermaster/update_ordermaster.jsp");
					fv.forward(req, res);
					return;
				}
//			開始修改資料
				OrdermasterService ordermasterSvc = new OrdermasterService();
				ordermasterVO = ordermasterSvc.updateOrdermaster(ordermasterid, memberid, payment, paystatus, orderdate,
						payexpire, paycode, receiver, tel, address, orderstatus);
				req.setAttribute("ordermasterVO", ordermasterVO);
				RequestDispatcher successViw = req.getRequestDispatcher("/back-end/ordermaster/listOneOrdermaster.jsp");
				successViw.forward(req, res);
			} catch (Exception e) {
				System.out.println("add:Exception");
				errorMsgs.add("修改失敗：" + e.getMessage());
				RequestDispatcher failure = req.getRequestDispatcher("/back-end/ordermaster/update_ordermaster.jsp");
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
				String ordermasterid = new String(req.getParameter("ordermasterid"));
//			2.開始刪除資料
				OrdermasterService ordermasterSvc = new OrdermasterService();
				ordermasterSvc.deleteOrdermaster(ordermasterid);
//			3.刪除完成,準備轉交(Send the Success view)
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/ordermaster/listAllordermaster.jsp");
				successView.forward(req, res);
//				其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/ordermaster/listAllordermaster.jsp");
				failureView.forward(req, res);
			}
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		(會員)成立訂單
		if ("customer_insert".equals(action)) {
			System.out.println("customer_insert");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String receiver = req.getParameter("receiver").trim();
//			輸入錯誤的處理（沒輸入或空白｜不符合正規表示｜轉跳頁面）
				String receiverReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,60}$";
				if (receiver == null || receiver.trim().length() == 0) {
					errorMsgs.add("收件人：請勿空白。");
				} else if (!receiver.trim().matches(receiverReg)) {
					errorMsgs.add("收件人：只能是中、英文字母,且長度必需在2到60之間");
				}
				String tel = req.getParameter("tel").trim();
				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.add("請填入電話");
				}
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("請填入地址");
				}
				String memberid = req.getParameter("memberid");
				System.out.println("!!memberid=>" + memberid);
				if (memberid == null || memberid.trim().length() == 0) {
					errorMsgs.add("會員編號不可空白");
				}
				String payment = req.getParameter("payment");
				String paystatus = req.getParameter("paystatus");

				Timestamp orderdate = new java.sql.Timestamp(System.currentTimeMillis());// 下單日
				Date dd = new java.sql.Date(System.currentTimeMillis());
				Date payexpire = new Date(dd.getTime() + 3 * 24 * 60 * 60 * 1000);// 3天內付款
				String paycode = null;

				paycode = req.getParameter("paycode");
				String orderstatus = "未出貨";
//--------------
				OrderdetailVO orderdetailVO = new OrderdetailVO();
				// 蒐集所有specid
				String[] aa = new String[(req.getParameterValues("specid")).length];
				aa = req.getParameterValues("specid");
				System.out.println("specid-length=>" + aa.length);
				List<String> specidAll = new ArrayList<String>();
				for (String test : aa) {
					System.out.println("test_id=" + test);
					specidAll.add(test);
				}
				System.out.println("specidAll.toString()=>" + specidAll.toString());
				for (String specid2 : specidAll) {
					orderdetailVO.setSpecid(specid2);
				}
				// 收集所有quantity
				String[] bb = new String[(req.getParameterValues("quantity")).length];
				bb = req.getParameterValues("quantity");
				System.out.println("quantity-length=>" + bb.length);
				List<Integer> quantityAll = new ArrayList<Integer>();
				for (String test2 : bb) {
					System.out.println("test_qt=" + test2);
					Integer ii = Integer.parseInt(test2);
					System.out.println("ii=" + ii);
					quantityAll.add(ii);
				}
				System.out.println(quantityAll.toString());
				for (Integer quantity2 : quantityAll) {
					orderdetailVO.setQuantity(quantity2);
				}
//--------------
				OrdermasterVO ordermasterVO = new OrdermasterVO();
				ordermasterVO.setMemberid(memberid);
				ordermasterVO.setPayment(payment);
				ordermasterVO.setPaystatus(paystatus);
				ordermasterVO.setOrderdate(orderdate);
				ordermasterVO.setPayexpire(payexpire);
				ordermasterVO.setPaycode(paycode);
				ordermasterVO.setReceiver(receiver);
				ordermasterVO.setTel(tel);
				ordermasterVO.setAddress(address);
				ordermasterVO.setOrderstatus(orderstatus);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordermasterVO", ordermasterVO);
//					RequestDispatcher fv = req.getRequestDispatcher("/front-end/ordermaster/addOrderMaster.jsp");
					RequestDispatcher fv = req.getRequestDispatcher("/back-end/cart/Checkout.jsp");
					fv.forward(req, res);
					return;
				}
//			開始新增資料
				System.out.println("ordermasterVO=" + ordermasterVO.toString());
				OrdermasterService ordermasterSvc = new OrdermasterService();
				ordermasterVO = ordermasterSvc.auto_addOrderMasterAndDetail(memberid, payment, paystatus, orderdate,
						payexpire, paycode, receiver, tel, address, orderstatus, specidAll, quantityAll);
				HttpSession session = req.getSession();
				session.removeAttribute("shoppingcart");

				RequestDispatcher successViw = req.getRequestDispatcher("/front-end/ordermaster/list_ordermaster.jsp");
//				RequestDispatcher successViw = req.getRequestDispatcher("/front-end/orderdetail/one_orderdetail.jsp");
				successViw.forward(req, res);
			} catch (Exception e) {
				System.out.println("add:Exception");
				errorMsgs.add("add:Exception");
				e.printStackTrace();
			}
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		(會員)查詢訂單列表(依會員ID)
		if ("getOrdermasterList_memberid".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
//				1.接收請求參數
				String memberid = new String(req.getParameter("memberid"));
				System.out.println("memberid=" + memberid);
//				2.開始查詢資料
				OrdermasterService ordermasterSvc = new OrdermasterService();
				List<OrdermasterVO> list = ordermasterSvc.getOrderByMemberid(memberid);
				req.getSession().setAttribute("list", list);
//				3.查詢完成,準備轉交
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/ordermaster/list_ordermaster.jsp");
				successView.forward(req, res);
//				其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ordermaster/list_ordermaster.jsp");
				failureView.forward(req, res);
			}
			System.out.println("end------getSomeList");
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		---------------------------------------------------------------------------------------------------------------------------
//		(管理員)*編輯多筆商品出貨狀態
		if ("update_some_orderstatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String ordermasterid = new String(req.getParameter("ordermasterid").trim());
//			輸入錯誤的處理（沒輸入或空白｜不符合正規表示｜轉跳頁面）
				String orderstatus = "出貨";
//				String orderstatus = req.getParameter("orderstatus").trim();
				if (ordermasterid == null || ordermasterid.length() == 0) {
					errorMsgs.add("找無ordermasterid");
				}
				if (orderstatus == null || orderstatus.length() == 0) {
					errorMsgs.add("找無orderstatus");
				}
//--------------
				OrdermasterVO ordermasterVO = new OrdermasterVO();
				String[] aa = new String[(req.getParameterValues("ordermasterid")).length];
				aa = req.getParameterValues("ordermasterid");
				List<String> list = new ArrayList<String>();
				for (String test : aa) {
					list.add(test);
				}
				ordermasterVO.setOrderstatus(orderstatus);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordermasterVO", ordermasterVO);
					RequestDispatcher fv = req.getRequestDispatcher("/back-end/ordermaster/listSomeOrdermaster.jsp");
					fv.forward(req, res);
					return;
				}
//			開始修改資料
				OrdermasterService ordermasterSvc = new OrdermasterService();
				ordermasterVO = ordermasterSvc.updateOrderStatus2(list, orderstatus);
				req.getSession().setAttribute("ordermasterVO", ordermasterVO);
//				RequestDispatcher successViw = req.getRequestDispatcher("/back-end/ordermaster/listSomeOrdermaster.jsp");
//				RequestDispatcher successViw = req.getRequestDispatcher("/back-end/ordermaster/listOneOrdermaster.jsp");
				RequestDispatcher successViw = req.getRequestDispatcher("/back-end/ordermaster/listAllordermaster.jsp");
				successViw.forward(req, res);
			} catch (Exception e) {
				System.out.println("add:Exception");
				errorMsgs.add("修改失敗：" + e.getMessage());
				RequestDispatcher failure = req.getRequestDispatcher("/back-end/ordermaster/listSomeOrdermaster.jsp");
				failure.forward(req, res);
			}
		}
		// ***（管理員）依照付款狀態(XX日期前未付款)
		if ("list_ByOrderstatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
//				1.接收請求參數
				String paystatus = new String(req.getParameter("paystatus").trim());
//				String paystatus = "N";
				if (paystatus == null || paystatus.length() == 0) {
//					errorMsgs.add("找無paystatus");
					System.out.println("找無paystatus");
				}
				Date dd = new java.sql.Date(System.currentTimeMillis());
				Date pickdate = dd;

//				2.開始查詢資料
				OrdermasterService ordermasterSvc = new OrdermasterService();
				List<OrdermasterVO> list = ordermasterSvc.getOrderByPayAndExpire(paystatus, pickdate);
				System.out.println("符合的Ordermaster共：" + list.size());
				req.getSession().setAttribute("list", list);
//				3.查詢完成,準備轉交
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/ordermaster/listSomeOrdermaster.jsp");
				successView.forward(req, res);
//				其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ordermaster/select_page.jsp");
				failureView.forward(req, res);
			}
			System.out.println("end------list_ByOrderstatus");
		}
		// ***（管理員）依照付款狀態(出貨狀態)
		if ("list_ByPay_Osta".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
//						1.接收請求參數
				String paystatus = new String(req.getParameter("paystatus").trim());
				if (paystatus == null || paystatus.length() == 0) {
					errorMsgs.add("找無paystatus");
				}
				String orderstatus = new String(req.getParameter("orderstatus").trim());
				if (orderstatus == null || orderstatus.length() == 0) {
					errorMsgs.add("找無orderstatus");
				}

//						2.開始查詢資料
				OrdermasterService ordermasterSvc = new OrdermasterService();
				List<OrdermasterVO> list = ordermasterSvc.getOrderByPayAndOrderstatus(paystatus, orderstatus);
				System.out.println("符合的Ordermaster共：" + list.size());
				req.getSession().setAttribute("list", list);
//						3.查詢完成,準備轉交
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/ordermaster/listSomeOrdermaster.jsp");
				successView.forward(req, res);
//						其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ordermaster/select_page.jsp");
				failureView.forward(req, res);
			}
			System.out.println("end------list_ByPay_Osta");
		}
	}
}