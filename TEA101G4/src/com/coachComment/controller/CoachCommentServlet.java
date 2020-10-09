package com.coachComment.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.coachComment.model.*;

@WebServlet("/back-end/coachComment/coachComment.do")
public class CoachCommentServlet extends HttpServlet{

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
				String str = req.getParameter("coachCommentID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入coachCommentID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/coachComment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String coachCommentID = null;
				try {
					coachCommentID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("coachCommentID格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/coachComment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CoachCommentService ccService = new CoachCommentService();
				CoachCommentVO coachCommentVO = ccService.getOneCoachComment(coachCommentID);
				if (coachCommentVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/coachComment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("coachCommentVO", coachCommentVO); // 資料庫取出的coachCommentVO物件,存入req
				String url = "/back-end/coachComment/listOneCoachComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/coachComment/select_page.jsp");
				failureView.forward(req, res);
			}
			
			
		}
		

		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String coachCommentID = new String(req.getParameter("coachCommentID"));
				
				/***************************2.開始查詢資料****************************************/
				CoachCommentService ccService = new CoachCommentService();
				CoachCommentVO coachCommentVO = ccService.getOneCoachComment(coachCommentID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("coachCommentVO", coachCommentVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/coachComment/update_coachComment_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/coachComment/listAllCoachComment.jsp");
				failureView.forward(req, res);
			}
		}

//		試修改可以一選多
		
//		if ("getOne_For_Update2".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				String commStar = new String(req.getParameter("commStar"));
//				
//				/***************************2.開始查詢資料****************************************/
//				CoachCommentService ccService = new CoachCommentService();
//				CoachCommentVO coachCommentVO = ccService.getOneCoachComment(commStar);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("coachCommentVO", coachCommentVO);         // 資料庫取出的empVO物件,存入req
//				String url = "/coachComment/update_coachComment_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/coachComment/listAllCoachComment.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			System.out.println("update 開始 接收請求參數 ");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String coachCommentID = new String(req.getParameter("coachCommentID").trim());
				
				
				String memberID = req.getParameter("memberID");
				String memberIDReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";

//				System.out.println("1 memberID = " + req.getParameter("member"));
//				System.out.println("1 errorMsgs = "+errorMsgs);
				
//				CoachCommentService ccService4 = new CoachCommentService();
//				CoachCommentVO coachCommentVO4 = ccService4.getOnememberID(memberID);
//				if (coachCommentVO4 == null) {
//					errorMsgs.add("查無資料");
//				}
				
//				System.out.println("2 memberID = " + req.getParameter("member"));
//				System.out.println("2 errorMsgs = "+errorMsgs);
				
				
				if (memberID == null || memberID.trim().length() == 0) {
					errorMsgs.add("memberID: 請勿空白");
				} else if(!memberID.trim().matches(memberIDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("memberID: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            } 
//				System.out.println("3 memberID = " + req.getMemberID());
//				System.out.println("3 errorMsgs = "+errorMsgs);
				
				
				String memberID2 = req.getParameter("memberID2");
				String memberID2Reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberID2 == null || memberID2.trim().length() == 0) {
					errorMsgs.add("memberID2: 請勿空白");
				} else if(!memberID2.trim().matches(memberID2Reg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("memberID2: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }	
				
				String commText = null;
				commText = new String(req.getParameter("commText").trim());
				if (commText == null || commText.trim().length() == 0) {
					errorMsgs.add("commText請勿空白");
				}
				
				Timestamp addDate = Timestamp.valueOf(req.getParameter("addDate"));

				Timestamp editDate = new java.sql.Timestamp(System.currentTimeMillis());
				
				Integer commStar = new Integer(req.getParameter("commStar").trim());
				
				String status = null;
				status = req.getParameter("status").trim();
				if (status == null || status.trim().length() == 0) {
					errorMsgs.add("status請勿空白");
				}
				

				CoachCommentVO coachCommentVO = new CoachCommentVO();
				coachCommentVO.setCoachCommentID(coachCommentID);
				coachCommentVO.setMemberID(memberID);
				coachCommentVO.setMemberID2(memberID2);
				coachCommentVO.setCommText(commText);
				coachCommentVO.setCommStar(commStar);
				coachCommentVO.setAddDate(addDate);
				coachCommentVO.setEditDate(editDate);
				coachCommentVO.setStatus(status);
				
//				System.out.println("4 memberID = " + coachCommentVO.getMemberID());
//				System.out.println("4 errorMsgs = "+errorMsgs);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("coachCommentVO", coachCommentVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/coachComment/update_coachComment_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/***************************2.開始修改資料*****************************************/
				System.out.println("=== servlet_update start 修改資料 ===");
				
				CoachCommentService ccService = new CoachCommentService();
				coachCommentVO = ccService.updateCoachComment
						(coachCommentID, memberID, memberID2, commText, commStar, addDate, editDate, status);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("coachCommentVO", coachCommentVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/coachComment/listOneCoachComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
				System.out.println("=== servlet_update 錯誤處理 ===");
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/coachComment/update_coachComment_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
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
				
				String memberID2 = req.getParameter("memberID2");
				String memberID2Reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberID2 == null || memberID2.trim().length() == 0) {
					errorMsgs.add("memberID2: 請勿空白");
				} else if(!memberID2.trim().matches(memberID2Reg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("memberID2: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }	
				
				String commText = req.getParameter("commText").trim();
				if (commText == null || commText.trim().length() == 0) {
					errorMsgs.add("commText請勿空白");
				}	
				
				Integer commStar = new Integer(req.getParameter("commStar").trim());
				
				Timestamp addDate = new java.sql.Timestamp(System.currentTimeMillis());
				
				Timestamp editDate = new java.sql.Timestamp(System.currentTimeMillis());


				String status = req.getParameter("status").trim();
				if (status == null || status.trim().length() == 0) {
					errorMsgs.add("status請勿空白");
				}

				CoachCommentVO coachCommentVO = new CoachCommentVO();
				coachCommentVO.setMemberID(memberID);
				coachCommentVO.setMemberID2(memberID2);
				coachCommentVO.setCommText(commText);
				coachCommentVO.setCommStar(commStar);
				coachCommentVO.setAddDate(addDate);
				coachCommentVO.setEditDate(editDate);
				coachCommentVO.setStatus(status);
				System.out.println("req.getParameter(\"addDate\") = "+coachCommentVO.getAddDate());
				System.out.println("req.getParameter(\"addDate\") = "+req.getParameter("addDate"));
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("coachCommentVO", coachCommentVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/coachComment/addCoachComment.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				CoachCommentService ccService = new CoachCommentService();
				coachCommentVO = ccService.addCoachComment
						(memberID, memberID2, commText, commStar, addDate, editDate, status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/coachComment/listAllCoachComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCoachComment.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/coachComment/addCoachComment.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllCoachComment.jsp
			System.out.println("進入 servlet 刪除");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				System.out.println("接收 刪除 請求參數");
				/***************************1.接收請求參數***************************************/
				String coachCommentID = new String(req.getParameter("coachCommentID"));
				
				/***************************2.開始刪除資料***************************************/
				System.out.println("開始刪除資料");
				CoachCommentService ccService = new CoachCommentService();
				ccService.deleteCoachComment(coachCommentID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				System.out.println("刪除完成");
				String url = "/back-end/coachComment/listAllCoachComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/coachComment/listAllCoachComment.jsp");
				failureView.forward(req, res);
			}
		}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
