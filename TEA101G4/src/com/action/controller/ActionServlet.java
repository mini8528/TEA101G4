package com.action.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.action.model.*;
import com.trainingcls.model.TrainingClsService;
import com.trainingcls.model.TrainingClsVO;





@WebServlet("/action/ActionServlet")
@MultipartConfig
public class ActionServlet extends HttpServlet {
	
	public byte[] toByteArray(InputStream in) throws IOException {

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int len;

		// read bytes from the input stream and store them in buffer
		while ((len = in.read(buffer)) != -1) {
			// write bytes from the buffer into output stream
			os.write(buffer, 0, len);
		}

		return os.toByteArray();
	}

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
				
				String str = req.getParameter("actionid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訓練動作名稱");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/action/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String actionid = null;
				try {
					actionid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("訓練動作不存在");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/action/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActionService actionSvc = new ActionService();
				ActionVO actionVO = actionSvc.getOneAction(actionid);/////////????
				if (actionVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/action/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actionVO", actionVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/action/listOneAction.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp

				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		 if ("getOne_For_Update".equals(action)) { 

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
	System.out.println("getOne_For_Update1");			
				try {
					/***************************1.接收請求參數****************************************/
					String actionid = req.getParameter("actionid");
	System.out.println("getOne_For_Update2");						
					/***************************2.開始查詢資料****************************************/
					ActionService actionSvc = new ActionService();
					ActionVO actionVO = actionSvc.getOneAction(actionid);
	System.out.println("getOne_For_Update3");				
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("actionVO", actionVO);         // 資料庫取出的empVO物件,存入req
					String url = "/back-end/action/update_action_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
	System.out.println("getOne_For_Update4");	
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					e.printStackTrace();
	System.out.println("5");	
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/action/listAllAction.jsp");
					failureView.forward(req, res);
				}
			}
		
		 if ("insert".equals(action)) { // 來自addaction.jsp的請求  
				
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				System.out.println("insert1");
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String actionnm = req.getParameter("actionnm").trim();
					if (actionnm == null || actionnm.trim().length() == 0) {
						errorMsgs.put("actionnm","動作名稱請勿空白");
					}
	System.out.println(actionnm);

					String part = req.getParameter("part").trim();
					if (part == null || part.trim().length() == 0) {
						errorMsgs.put("part","運動部位請勿空白");
					}

					byte[] myFileArray = null;
					 InputStream in = req.getPart("video").getInputStream();
					 myFileArray = new byte[in.available()];
					 in.read(myFileArray);
						System.out.println(req.getPart("video").getSize());

				
					java.sql.Date posttime = null;
					try {
						posttime = java.sql.Date.valueOf(req.getParameter("posttime").trim());
					} catch (IllegalArgumentException e) {
						errorMsgs.put("posttime","請輸入上傳日期");
					}

			
					java.sql.Date updatetime = null;
					try {
						updatetime = java.sql.Date.valueOf(req.getParameter("updatetime").trim());
					} catch (IllegalArgumentException e) {
						errorMsgs.put("updatetime","請輸入更新日期");
					}

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/action/addAction.jsp");
						failureView.forward(req, res);
						return;
					}
					
					ActionVO actVO = new ActionVO();
			
					actVO.setPart(part);
					actVO.setVideo(myFileArray);
					actVO.setPosttime(posttime);
					actVO.setUpdatetime(updatetime);
					
					/***************************2.開始新增資料***************************************/
					ActionService actionSvc = new ActionService();
					actionSvc.addAction(actionnm, part, myFileArray ,posttime ,updatetime);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/back-end/action/listAllAction.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.put("Exception",e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/action/addAction.jsp");
					failureView.forward(req, res);
				}
			}
		 
		
		 if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("update1");
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String actionid = req.getParameter("actionid").trim();
					if (actionid == null || actionid.trim().length() == 0) {
						errorMsgs.add("管理員請勿空白");
					}	
					
					System.out.println("update2");
					String actionnm = req.getParameter("actionnm");//輸入訓練動作名稱
					String actionnmReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
					if (actionnm == null || actionnm.trim().length() == 0) {
						errorMsgs.add("訓練動作名稱: 請勿空白");
					} else if(!actionnm.trim().matches(actionnmReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("訓練動作名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
		            }

					
					String part = req.getParameter("part").trim();
					if (part == null || part.trim().length() == 0) {
						errorMsgs.add("訓練部位名稱請勿空白");
					}
					
					byte[] myFileArray = this.toByteArray(req.getPart("video").getInputStream());
					
					if (myFileArray == null || myFileArray.length == 0) {
						errorMsgs.add("訓練影片請勿空白");
					}
					
					java.sql.Date posttime = null;
					try {
						posttime = java.sql.Date.valueOf(req.getParameter("posttime").trim());
					} catch (IllegalArgumentException e) {
						posttime=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入上傳日期!");
					}

					java.sql.Date updatetime = null;
					try {
						updatetime = java.sql.Date.valueOf(req.getParameter("updatetime").trim());
					} catch (IllegalArgumentException e) {
						updatetime=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入更新日期!");
					}


					ActionVO actVO = new ActionVO();
					actVO.setActionid(actionid);
					actVO.setPart(part);
					actVO.setVideo(myFileArray);
					actVO.setPosttime(posttime);
					actVO.setUpdatetime(updatetime);
					

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("actionVO", actVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/action/update_action_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料*****************************************/
					ActionService actionSvc = new ActionService();
					actVO = actionSvc.updateAction(actionid ,actionnm, part, myFileArray, posttime,updatetime);
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("actionVO", actVO); // 資料庫update成功後,正確的的empVO物件,存入req
					String url = "/back-end/action/listOneAction.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					System.out.println("1");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/action/update_action_input.jsp");
					System.out.println("2");
					failureView.forward(req, res);
					System.out.println("3");
				}
			}
		 
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
System.out.println("1");
//			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String actionnm = req.getParameter("actionnm");
				System.out.println("2");			
				String actionnmReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (actionnm == null || actionnm.trim().length() == 0) {
					errorMsgs.add("訓練動作名稱: 請勿空白");
				} else if (!actionnm.trim().matches(actionnmReg)) {
					errorMsgs.add("訓練動作名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}
System.out.println("3");
				String part = req.getParameter("part").trim();
				if (part == null || part.trim().length() == 0) {
					errorMsgs.add("運動部位名稱請勿空白");
				}
System.out.println("4");				
				byte[] myFileArray = this.toByteArray(req.getPart("video").getInputStream());
				
				if (myFileArray == null || myFileArray.length == 0) {
					errorMsgs.add("訓練影片請勿空白");
				}
System.out.println("5");
				java.sql.Date posttime = null;
				try {
					posttime = java.sql.Date.valueOf(req.getParameter("posttime").trim());
				} catch (IllegalArgumentException e) {
					posttime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上傳日期!");
				}

				java.sql.Date updatetime = null;
				try {
					updatetime = java.sql.Date.valueOf(req.getParameter("updatetime").trim());
				} catch (IllegalArgumentException e) {
					updatetime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入更新日期!");
				}

				String actionid = req.getParameter("actionid");

				ActionVO actVO = new ActionVO();
				actVO.setActionnm(actionnm);
				actVO.setPart(part);
				actVO.setVideo(myFileArray);
				actVO.setPosttime(posttime);
				actVO.setUpdatetime(updatetime);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("back-end/action/addAction.jsp");
					failureView.forward(req, res);
					return;
				}
	
				/*************************** 2.開始新增資料 ***************************************/
				ActionService actionSvc = new ActionService();
				actVO = actionSvc.addAction(actionnm, part, myFileArray, posttime, updatetime);
			
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/action/listAllAction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				System.out.println("3");	
				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/action/addAction.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		 if ("delete".equals(action)) { // 來自listAllAction.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					String actionid = req.getParameter("actionid").trim();
					
					/***************************2.開始刪除資料***************************************/
					ActionService actionSvc = new ActionService();
					actionSvc.deleteAction(actionid);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
					String url = "/back-end/action/listAllAction.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/action/listAllAction.jsp");
					failureView.forward(req, res);
				}
			}
	}
	
}
 