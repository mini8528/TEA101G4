package com.coachClass.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;

import com.coachClass.model.*;


@MultipartConfig
@WebServlet("/back-end/coachClass/coachClass.do")
public class CoachClassServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("==================================================");
		System.out.println("======      Coach Class Servlet   ================");
		System.out.println("==================================================");
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			
			System.out.println("=== Coach Class Servlet ___ getOne_For_Display ===");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("coachClassID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入coachClassID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String coachClassID = null;
				try {
					coachClassID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("coachClassID格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/*************************** 2.開始查詢資料 *****************************************/
				CoachClassService cocService = new CoachClassService();
				CoachClassVO coachClassVO = cocService.getOneCoachClass(coachClassID);
				if (coachClassVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("coachClassVO", coachClassVO); // 資料庫取出的coachClassVO物件,存入req
				String url = "/back-end/coachClass/listOneCoachClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				

				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Display_front".equals(action)) { // 來自select_page.jsp的請求

			System.out.println("顯示單一課程畫面   方法 : getOne_For_Display_front");
			System.out.println("課程ID coachClassID = "+ req.getParameter("coachClassID"));

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("coachClassID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入coachClassID");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coachClass/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String coachClassID = null;
				try {
					coachClassID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("coachClassID格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coachClass/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/*************************** 2.開始查詢資料 *****************************************/
				CoachClassService cocService = new CoachClassService();
				CoachClassVO coachClassVO = cocService.getOneCoachClass(coachClassID);
				if (coachClassVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coachClass/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("coachClassVO", coachClassVO); // 資料庫取出的coachClassVO物件,存入req
				String url = "/front-end/coachClass/listOneCoachClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				System.out.println("顯示單一課程畫面 == end");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coachClass/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("update".equals(action)) { // 來自update_coachClass_input.jsp的請求
			
			System.out.println("=== Coach Class Servlet ___ update ===");

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String coachClassID = new String(req.getParameter("coachClassID").trim());
				String memberID = req.getParameter("memberID");
				String memberIDReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberID == null || memberID.trim().length() == 0) {
					errorMsgs.add("memberID: 請勿空白");
				} else if (!memberID.trim().matches(memberIDReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("memberID: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				String className = req.getParameter("className").trim();
				if (className == null || className.trim().length() == 0) {
					errorMsgs.add("className請勿空白");
				}
				String classContext = req.getParameter("classContext").trim();
				if (classContext == null || classContext.trim().length() == 0) {
					errorMsgs.add("classContext請勿空白");
				}

				java.sql.Timestamp startTime = null;
				try {
					startTime = java.sql.Timestamp.valueOf(req.getParameter("startTime").trim());
				} catch (IllegalArgumentException e) {
					startTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入startTime!");
				}
				Integer price = new Integer(req.getParameter("price").trim());

				Integer quantity = new Integer(req.getParameter("quantity").trim());
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("address請勿空白");
				}
				
				byte[] photo = null;
				Part photo1 = req.getPart("photo");
				try {
					if (photo1 == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo1.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = fis.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}
						baos.close();
						fis.close();
						photo = baos.toByteArray();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Timestamp editDate = new java.sql.Timestamp(System.currentTimeMillis());

				Timestamp addDate = Timestamp.valueOf(req.getParameter("addDate"));
				
				
				
				CoachClassVO coachClassVO = new CoachClassVO();
				coachClassVO.setCoachClassID(coachClassID);
				coachClassVO.setMemberID(memberID);
				coachClassVO.setClassName(className);
				coachClassVO.setClassContext(classContext);
				coachClassVO.setPhoto(photo);
				coachClassVO.setStartTime(startTime);
				coachClassVO.setPrice(price);
				coachClassVO.setQuantity(quantity);
				coachClassVO.setAddress(address);
				coachClassVO.setAddDate(addDate);
				coachClassVO.setEditDate(editDate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("coachClassVO", coachClassVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/update_coachClass_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				CoachClassService cocService = new CoachClassService();
				coachClassVO = cocService.updateCoachClass(coachClassID, memberID, className, classContext, photo,
						startTime, price, quantity, address, addDate, editDate);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/

				req.setAttribute("coachClassVO", coachClassVO); // 資料庫update成功後,正確的的CoachClassVO物件,存入req
				String url = "/back-end/coachClass/listOneCoachClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCoachClass.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/update_coachClass_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addCoachClass.jsp的請求
			
			System.out.println("=== Coach Class Servlet ___ insert ===");

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String memberID = req.getParameter("memberID");
				
				String memberIDReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberID == null || memberID.trim().length() == 0) {
					errorMsgs.add("memberID: 請勿空白");
				} else if (!memberID.trim().matches(memberIDReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("memberID: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String className = req.getParameter("className").trim();
				if (className == null || className.trim().length() == 0) {
					errorMsgs.add("className請勿空白");
				}
				
				Integer price = new Integer(req.getParameter("price").trim());

				
				java.sql.Timestamp startTime = null;
				try {
					startTime = java.sql.Timestamp.valueOf(req.getParameter("startTime").trim());
				} catch (IllegalArgumentException e) {
					startTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入startTime!");
				}
				
				Integer quantity = new Integer(req.getParameter("quantity").trim());
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("address請勿空白");
				}
				
				Timestamp addDate = new java.sql.Timestamp(System.currentTimeMillis());
				
				Timestamp editDate = new java.sql.Timestamp(System.currentTimeMillis());
				
				String classContext = req.getParameter("classContext").trim();
				if (classContext == null || classContext.trim().length() == 0) {
					errorMsgs.add("classContext請勿空白");
				}

				byte[] photo = null;
				System.out.println("req.getPart(\"photo\") = "+req.getPart("photo"));
				Part photo1 = req.getPart("photo");
				try {
					if (photo1 == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo1.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = fis.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}
						baos.close();
						fis.close();
						photo = baos.toByteArray();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				CoachClassVO coachClassVO = new CoachClassVO();
				coachClassVO.setMemberID(memberID);
				coachClassVO.setClassName(className);
				coachClassVO.setClassContext(classContext);
				coachClassVO.setPhoto(photo);
				coachClassVO.setStartTime(startTime);
				coachClassVO.setPrice(price);
				coachClassVO.setQuantity(quantity);
				coachClassVO.setAddress(address);
				coachClassVO.setAddDate(addDate);
				coachClassVO.setEditDate(editDate);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("coachClassVO", coachClassVO); // 含有輸入格式錯誤的ClassDetailVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coachClass/addCoachClass.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				CoachClassService cocService = new CoachClassService();
				coachClassVO = cocService.addCoachClass(memberID, className, classContext, photo, startTime, price,
						quantity, address, addDate, editDate);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/coachClass/listAllCoachClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllClassOrder.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coachClass/addCoachClass.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllCoachClass.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String coachClassID = new String(req.getParameter("coachClassID"));

				/*************************** 2.開始刪除資料 ***************************************/
				CoachClassService cocService = new CoachClassService();
				cocService.deleteCoachClass(coachClassID);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/coachClass/listAllCoachClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/listAllCoachClass.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllCoachClass.jsp的請求

			System.out.println("=== Coach Class Servlet ___ getOne_For_Update ===");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				
				String coachClassID = new String(req.getParameter("coachClassID"));
				System.out.println("目標 修改課程ID = "+ coachClassID);
//				1.接收請求參數
				try {
					String str = req.getParameter("coachClassID");
//				錯誤處理
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入訂單編號");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/select_page.jsp");
						failureView.forward(req, res);
						return;
					}
//				傳入字串格式有問題，拋出例外，顯示錯誤訊息｜轉跳頁面
					coachClassID = null;
					try {
						coachClassID = new String(str);
					} catch (Exception e) {
						errorMsgs.add("訂單編號格式不正確");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/select_page.jsp");
						failureView.forward(req, res);
						return;
					}
					/*************************** 2.開始查詢資料 ****************************************/
					CoachClassService cocService = new CoachClassService();
					CoachClassVO coachClassVO = cocService.getOneCoachClass(coachClassID);
					System.out.println("取得課程ID = "+ coachClassID);

					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("coachClassVO", coachClassVO); // 資料庫取出的CoachClassVO物件,存入req
					String url = "/back-end/coachClass/update_coachClass_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_coachClass_input.jsp
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/listAllCoachClass.jsp");
					failureView.forward(req, res);
				}

			} catch (Exception e) {
				errorMsgs.add("servlet getOne_For_Update請求參數失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/listAllCoachClass.jsp");
				failureView.forward(req, res);
			}
		}
	}

}