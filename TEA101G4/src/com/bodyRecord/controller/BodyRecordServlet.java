package com.bodyRecord.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.http.Part;

import com.bodyRecord.model.BodyRecordService;
import com.bodyRecord.model.BodyRecordVO;

@MultipartConfig
@WebServlet("/back-end/bodyRecord/bodyRecord.do")
public class BodyRecordServlet extends HttpServlet{

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
				String str = req.getParameter("bodyRecordID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入bodyRecordID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/bodyRecord/select_page.jsp");
					failureView.forward(req, res);
//					return;//程式中斷
				}
				
				String bodyRecordID = null;
				try {
					bodyRecordID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("bodyRecordID格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/bodyRecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BodyRecordService brService = new BodyRecordService();
				BodyRecordVO bodyRecordVO = brService.getOneBodyRecord(bodyRecordID);
				if (bodyRecordVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/bodyRecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("bodyRecordVO", bodyRecordVO); // 資料庫取出的classDetailVO物件,存入req
				String url = "/back-end/bodyRecord/listOneBodyRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/bodyRecord/select_page.jsp");
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
				String bodyRecordID = new String(req.getParameter("bodyRecordID"));
				
				/***************************2.開始查詢資料****************************************/
				System.out.println("開始查詢資料");
				BodyRecordService brService = new BodyRecordService();
				BodyRecordVO bodyRecordVO = brService.getOneBodyRecord(bodyRecordID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				System.out.println("查詢完成,準備轉交");
				req.setAttribute("bodyRecordVO", bodyRecordVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/bodyRecord/update_bodyRecord_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				
				successView.forward(req, res);
				System.out.println("out --- 更新完成 ---- out ");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/bodyRecord/listAllBodyRecord.jsp");
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
				String bodyRecordID = new String(req.getParameter("bodyRecordID").trim());
				
				String memberID = req.getParameter("memberID");
				String memberIDReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberID == null || memberID.trim().length() == 0) {
					errorMsgs.add("memberID: 請勿空白");
				} else if(!memberID.trim().matches(memberIDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("memberID: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				Timestamp uploadDate = Timestamp.valueOf(req.getParameter("uploadDate"));
				
				byte[] photo1 = null;
				Part photo2 = req.getPart("photo1");
				try {
					if (photo2 == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo2.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = fis.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}
						baos.close();
						fis.close();
						photo1 = baos.toByteArray();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				String description = null;
				description = req.getParameter("description").trim();
				if (description == null || description.trim().length() == 0) {
					errorMsgs.add("description請勿空白");
				}
				
				String describeStatus = null;
				describeStatus = req.getParameter("describeStatus").trim();
				if (describeStatus == null || describeStatus.trim().length() == 0) {
					errorMsgs.add("describeStatus請勿空白");
				}
				
				
				Timestamp updateTime = new java.sql.Timestamp(System.currentTimeMillis());
				
				
				System.out.println("匯入VO ");
				BodyRecordVO bodyRecordVO = new BodyRecordVO();
				bodyRecordVO.setBodyRecordID(bodyRecordID);
				bodyRecordVO.setMemberID(memberID);
				bodyRecordVO.setUploadDate(uploadDate);
				bodyRecordVO.setPhoto1(photo1);
				bodyRecordVO.setDescription(description);
				bodyRecordVO.setDescribeStatus(describeStatus);
				bodyRecordVO.setUpdateTime(updateTime);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bodyRecordVO", bodyRecordVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/bodyRecord/update_bodyRecord_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				System.out.println("開始修改資料 ");
				BodyRecordService brService = new BodyRecordService();
				bodyRecordVO = brService.updateBodyRecord
						(  bodyRecordID,  memberID, uploadDate , photo1 , description , describeStatus,  updateTime);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				System.out.println("修改完成,準備轉交 ");
				req.setAttribute("bodyRecordVO", bodyRecordVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/bodyRecord/listOneBodyRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				System.out.println("out --- 更新完成 ---- out  ");
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/bodyRecord/update_bodyRecord_input.jsp");
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
				String memberID = req.getParameter("memberID");
				String memberIDReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberID == null || memberID.trim().length() == 0) {
					errorMsgs.add("memberID: 請勿空白");
				} else if(!memberID.trim().matches(memberIDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("memberID: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				Timestamp uploadDate = new java.sql.Timestamp(System.currentTimeMillis());
				
				
				byte[] photo1 = null;
				Part photo2 = req.getPart("photo1");
				try {
					if (photo2 == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo2.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = fis.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}
						baos.close();
						fis.close();
						photo1 = baos.toByteArray();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				String description = null;
				description = req.getParameter("description").trim();
				if (description == null || description.trim().length() == 0) {
					errorMsgs.add("description請勿空白");
				}
				

				String describeStatus = null;
				describeStatus = req.getParameter("describeStatus").trim();
				if (describeStatus == null || describeStatus.trim().length() == 0) {
					errorMsgs.add("describeStatus請勿空白");
				}
				
				
				Timestamp updateTime = new java.sql.Timestamp(System.currentTimeMillis());
				
				
				
				
				BodyRecordVO bodyRecordVO = new BodyRecordVO();
				bodyRecordVO.setMemberID(memberID);
				bodyRecordVO.setUploadDate(uploadDate);
				bodyRecordVO.setPhoto1(photo1);
				bodyRecordVO.setDescription(description);
				bodyRecordVO.setDescribeStatus(describeStatus);
				bodyRecordVO.setUpdateTime(updateTime);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bodyRecordVO", bodyRecordVO); // 含有輸入格式錯誤的ClassDetailVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/bodyRecord/addBodyRecord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				BodyRecordService brService = new BodyRecordService();
				bodyRecordVO = brService.addBodyRecord
						( memberID,	 uploadDate , photo1 , description , describeStatus,  updateTime);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/bodyRecord/listAllBodyRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllClassDetail.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/bodyRecord/addBodyRecord.jsp");
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
				String bodyRecordID = new String(req.getParameter("bodyRecordID"));
				
				/***************************2.開始刪除資料***************************************/
				BodyRecordService brService = new BodyRecordService();
				brService.deleteBodyRecord(bodyRecordID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/bodyRecord/listAllBodyRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/bodyRecord/listAllBodyRecord.jsp");
				failureView.forward(req, res);
			}
		}
	
	}
}
