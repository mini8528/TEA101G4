package com.wishList.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wishList.model.WishListService;
import com.wishList.model.WishListVO;

@MultipartConfig
@WebServlet("/wishList/wishList.do")
public class WishListServlet extends HttpServlet{

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
				String str = req.getParameter("wishListId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入wishListId");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/wishList/select_page.jsp");
					failureView.forward(req, res);
//					return;//程式中斷
				}
				
				String wishListId = null;
				try {
					wishListId = new String(str);
				} catch (Exception e) {
					errorMsgs.add("wishListId格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/wishList/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				WishListService wlService = new WishListService();
				WishListVO wishListVO = wlService.getOneWishList(wishListId);
				if (wishListVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/wishList/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("wishListVO", wishListVO); // 資料庫取出的classDetailVO物件,存入req
				String url = "/back-end/wishList/listOneWishList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/wishList/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		

		
		if ("getOne_For_Update".equals(action)) { // 來自listAllClassDetail.jsp的請求
			System.out.println("in --- 進入 Servlet getOne_For_Update --- in ");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				System.out.println("接收請求參數");
				/***************************1.接收請求參數****************************************/
				String wishListId = new String(req.getParameter("wishListId"));
				
				/***************************2.開始查詢資料****************************************/
				System.out.println("開始查詢資料");
				WishListService wlService = new WishListService();
				WishListVO wishListVO = wlService.getOneWishList(wishListId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				System.out.println("查詢完成,準備轉交");
				req.setAttribute("wishListVO", wishListVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/wishList/update_wishList_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				
				successView.forward(req, res);
				System.out.println("out --- 更新完成 ---- out ");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/wishList/listAllWishList.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_classDetail_input.jsp的請求
			System.out.println("in --- 進入 Servlet update --- in ");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				System.out.println("接收請求參數 ");
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String wishListId = new String(req.getParameter("wishListId").trim());
				String memberId = req.getParameter("memberId");
				String memberIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberId == null || memberId.trim().length() == 0) {
					errorMsgs.add("classOrderID: 請勿空白");
				} else if(!memberId.trim().matches(memberIdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("memberId: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				String productId = req.getParameter("productId");
				String productIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (productId == null || productId.trim().length() == 0) {
					errorMsgs.add("productId: 請勿空白");
				} else if(!productId.trim().matches(productIdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("coachClassID: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }	
				String likeStatus = null;
				likeStatus = req.getParameter("likeStatus").trim();
				if (likeStatus == null || likeStatus.trim().length() == 0) {
					errorMsgs.add("likeStatus請勿空白");
				}
				Date editDate = new java.sql.Date(System.currentTimeMillis());
				
				Date addDate = Date.valueOf(req.getParameter("addDate"));
				
				System.out.println("匯入VO ");
				WishListVO wishListVO = new WishListVO();
				wishListVO.setWishListId(wishListId);
				wishListVO.setMemberId(memberId);
				wishListVO.setProductId(productId);
				wishListVO.setLikeStatus(likeStatus);
				wishListVO.setAddDate(addDate);
				wishListVO.setEditDate(editDate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wishListVO", wishListVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/wishList/update_wishList_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				System.out.println("開始修改資料 ");
				WishListService wlService = new WishListService();
				wishListVO = wlService.updateWishList
						( wishListId,  memberId, productId, likeStatus,	 addDate, editDate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				System.out.println("修改完成,準備轉交 ");
				req.setAttribute("wishListVO", wishListVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/wishList/listOneWishList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				System.out.println("out --- 更新完成 ---- out  ");
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/wishList/update_wishList_input.jsp");
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
				String memberId = req.getParameter("memberId");
				String memberIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberId == null || memberId.trim().length() == 0) {
					errorMsgs.add("memberId: 請勿空白");
				} else if(!memberId.trim().matches(memberIdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("classOrderID: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String productId = req.getParameter("productId");
				String productIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (productId == null || productId.trim().length() == 0) {
					errorMsgs.add("productId: 請勿空白");
				} else if(!productId.trim().matches(productIdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("coachClassID: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }	
				
				String likeStatus = null;
				likeStatus = req.getParameter("likeStatus").trim();
				if (likeStatus == null || likeStatus.trim().length() == 0) {
					errorMsgs.add("likeStatus請勿空白");
				}
				
				Date addDate = new java.sql.Date(System.currentTimeMillis());
				
				Date editDate = new java.sql.Date(System.currentTimeMillis());
				
				
				
				WishListVO wishListVO = new WishListVO();
				wishListVO.setMemberId(memberId);
				wishListVO.setProductId(productId);
				wishListVO.setLikeStatus(likeStatus);
				wishListVO.setAddDate(addDate);
				wishListVO.setEditDate(editDate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wishListVO", wishListVO); // 含有輸入格式錯誤的ClassDetailVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/wishList/addWishList.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				WishListService wlService = new WishListService();
				wishListVO = wlService.addWishList
						(  memberId, productId, likeStatus,	addDate, editDate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/wishList/listAllWishList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllClassDetail.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/wishList/addWishList.jsp");
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
				String wishListId = new String(req.getParameter("wishListId"));
				
				/***************************2.開始刪除資料***************************************/
				WishListService wlService = new WishListService();
				wlService.deleteWishList(wishListId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/wishList/listAllWishList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/wishList/listAllWishList.jsp");
				failureView.forward(req, res);
			}
		}
	
	}
	
}
