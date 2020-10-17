package com.traininghist.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trainingcls.model.TrainingClsService;
import com.trainingcls.model.TrainingClsVO;
import com.trainingclsdetail.model.TrainingClsDetailService;
import com.trainingclsdetail.model.TrainingClsDetailVO;
import com.traininghist.model.TrainingHistService;
import com.traininghist.model.TrainingHistVO;
import com.trainingsche.model.TrainingScheVO;

@WebServlet("/traininghist/TrainingHistServlet")
public class TrainingHistServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("traininghistid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訓練歷程編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/traininghist/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String traininghistid = null;
				try {
					traininghistid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("訓練歷程編號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/traininghist/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				TrainingHistService thSvc = new TrainingHistService();
				TrainingHistVO thVO = thSvc.getOneTrainingHist(traininghistid);
				if (thVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/traininghist/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("thVO", thVO); 
				String url = "/traininghist/listOneTrainingHist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/traininghist/select_page.jsp");
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
				String traininghistid = new String(req.getParameter("traininghistid"));
				
				/***************************2.開始查詢資料****************************************/
				TrainingHistService thSvc = new TrainingHistService();
				TrainingHistVO thVO = thSvc.getOneTrainingHist(traininghistid);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("thVO", thVO);       
				String url = "/traininghist/update_traininghist_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_action_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/traininghist/listAllTrainingHist.jsp");
				failureView.forward(req, res);
			}
		}
		
//------修改資料traininghist--------		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			TrainingHistVO thVO = new TrainingHistVO();
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String trainingscheid = req.getParameter("trainingscheid").trim();
				if (trainingscheid == null || trainingscheid.trim().length() == 0) {
					errorMsgs.add("訓練行程請勿空白");
				}
				
				
				Integer trainingset = new Integer(req.getParameter("trainingset").trim());
	           
	            Integer trainingrep = new Integer(req.getParameter("trainingrep").trim());
	           
	            Integer trainingwt = new Integer(req.getParameter("trainingwt").trim());
	           
				
				thVO.setTrainingscheid(trainingscheid);
				thVO.setTrainingset(trainingset);
				thVO.setTrainingrep(trainingrep);
				thVO.setTrainingwt(trainingwt);

			
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("thVO", thVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trainingindex.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TrainingHistService thSvc = new TrainingHistService();
				thVO = thSvc.updatehistTrainingHist(trainingset ,trainingrep ,trainingwt, trainingscheid);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("thVO", thVO); 
				String url = "/traininghist/listOneTrainingHist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				req.setAttribute("thVO", thVO);
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trainingindex.jsp");
				failureView.forward(req, res);
			}
		}
		
        //------新增資料--------		
		if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String memberid = req.getParameter("memberid").trim();
				if (memberid == null || memberid.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
					System.out.println("1");
				}	
				
				String trainingscheid = req.getParameter("trainingscheid").trim();
				if (trainingscheid == null || trainingscheid.trim().length() == 0) {
					errorMsgs.add("訓練行程編號請勿空白");
					System.out.println("2");
				}
				
				
				String actionid = req.getParameter("actionid").trim();
				if (actionid == null || actionid.trim().length() == 0) {
					errorMsgs.add("更新的管理員請勿空白");
					
				}
				Timestamp starttime = new java.sql.Timestamp(System.currentTimeMillis());
				
				Timestamp endtime = new java.sql.Timestamp(System.currentTimeMillis());


				Integer trainingtime = new Integer(req.getParameter("trainingtime").trim());
			
				Integer trainingset = new Integer(req.getParameter("trainingset").trim());
		
				Integer trainingrep = new Integer(req.getParameter("trainingrep").trim());
				
				Integer trainingwt = new Integer(req.getParameter("trainingwt").trim());

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("error");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/traininghist/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				TrainingScheVO tsVO = new TrainingScheVO();
				tsVO.setStarttime(starttime);
				tsVO.setEndtime(endtime);
				
				TrainingHistVO thVO = new TrainingHistVO();
				thVO.setMemberid(memberid);
				thVO.setTrainingscheid(trainingscheid);
				thVO.setActionid(actionid);
				thVO.setTrainingtime(trainingtime);
				thVO.setTrainingset(trainingset);
				thVO.setTrainingrep(trainingrep);
				thVO.setTrainingwt(trainingwt);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("thVO", thVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/traininghist/addTrainingHist.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				TrainingHistService thSvc = new TrainingHistService();
				thVO = thSvc.addTrainingHist(memberid, trainingscheid, actionid ,trainingtime ,trainingset ,trainingrep ,trainingwt);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/traininghist/listAllTrainingHist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/traininghist/addTrainingHist.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllAction.jsp

			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String traininghistid = req.getParameter("traininghistid").trim();
				/***************************2.開始刪除資料***************************************/
				TrainingHistService thSvc = new TrainingHistService();
				thSvc.deleteTrainingHist(traininghistid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/traininghist/listAllTrainingHist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				System.out.println("1");
				successView.forward(req, res);
				System.out.println("2ggg");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/traininghist/listAllTrainingHist.jsp");
				System.out.println("3");
				failureView.forward(req, res);
				System.out.println("4");
			}
		}
		
	}
	
}
