package com.pro.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.model.*;



@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024, maxRequestSize = 5*5*1024*1024)
@WebServlet("/back-end/pro/pro.do")
public class ProServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		/*-----------------------------------------insert最新消息-------------------------------------------------------------------------*/
		if ("getSome_For_insert".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("productid");
			
				String productid = null;

				productid = new String(str);
			
				/***************************2.開始查詢資料*****************************************/
				ProService proSvc = new ProService();
				List <ProVO> proVO = proSvc.getAllStatusYById(productid);
				
		
				if (proVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/pro/addPro.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("proVO", proVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/pro/addPro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/pro/addPro.jsp");
				failureView.forward(req, res);
			}
		}
		
		
/*------------------------------------------------------------------------------------------------------------------*/
		if ("getSome_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("productid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/pro/listAllPro.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String productid = null;
				try {
					productid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/pro/listAllPro.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProService proSvc = new ProService();
				List <ProVO> proVO = proSvc.getAllStatusYById(productid);
				
				
				
				if (proVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/pro/listAllPro.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("proVO", proVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/pro/listsomePro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/pro/listAllPro.jsp");
				failureView.forward(req, res);
			}
		}
		
/*------------------------------------------------------------------------------------------------------------------*/
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();	
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("prodcommid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品評價編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pro/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String prodcommid = null;
				try {
					prodcommid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品評價編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pro/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProService proSvc = new ProService();
				ProVO proVO = proSvc.getOnePro(prodcommid);
				if (proVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pro/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("proVO", proVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/pro/listOnePro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/pro/select_page.jsp");
				failureView.forward(req, res);

			}
		}
		
/*------------------------------------------------------------------------------------------------------------------*/		

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String prodcommid = new String(req.getParameter("prodcommid"));
				/***************************2.開始查詢資料****************************************/
				ProService proSvc = new ProService();
				ProVO proVO = proSvc.getOnePro(prodcommid);
						
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("proVO", proVO);         // 資料庫取出的admVO物件,存入req	
				String url = "/back-end/pro/update_pro_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/pro/listAllPro.jsp");
				failureView.forward(req, res);
			}
		}

/*--------------------會員修改內容----------------------------------------------------------------------------------------------*/		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();			
			req.setAttribute("errorMsgs", errorMsgs);		
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String prodcommid = new String(req.getParameter("prodcommid").trim());
				/***************************2.商品編號**********************/
				String productid = req.getParameter("productid");
				String memberReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";

				/***************************3.一般會員編號**********************/
				String memberid = req.getParameter("memberid").trim();

				/***************************4.評論內容**********************/
				String commtext = req.getParameter("commtext").trim();			
				if (commtext == null || commtext.trim().length() == 0) {
					errorMsgs.add("評論內容: 請勿空白");
				}else if(!commtext.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("評論內容只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				/***************************5.星級數**********************/
				Integer commstar = null;

					commstar = Integer.parseInt(req.getParameter("commstar").trim());

				/***************************5.新增日期 **********************/								
				java.sql.Date adddate = null;
					adddate=new java.sql.Date(System.currentTimeMillis());

				/***************************6.修改日期**********************/

				java.sql.Date editdate = null;
					editdate=new java.sql.Date(System.currentTimeMillis());
		
/*------------------------------------------------------------------------------------------------------------------*/		

				ProVO proVO = new ProVO();
			
				proVO.setProdcommid(prodcommid);
				proVO.setProductid(productid);
				proVO.setMemberid(memberid);
				proVO.setCommtext(commtext);
				proVO.setCommstar(commstar);
				proVO.setAdddate(adddate);
				proVO.setEditdate(editdate);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("proVO", proVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/pro/update_pro_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/***************************2.開始修改資料*****************************************/
				ProService proSvc = new ProService();
				String status = "Y";
				proVO = proSvc.updatePro(prodcommid, productid, memberid, commtext, commstar, adddate, editdate, status);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("proVO", proVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/pro/listOnePro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/pro/update_pro_input.jsp");
				failureView.forward(req, res);
			}
		}
/*------------------------------------------------------------------------------------------------------------------*/		

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************2.商品編號**********************/
				String productid = req.getParameter("productid");
				String memberReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,200}$";

				/***************************3.一般會員編號**********************/
				String memberid = req.getParameter("memberid").trim();

				/***************************4.評論內容**********************/
				String commtext = req.getParameter("commtext").trim();
				
				if (commtext == null || commtext.trim().length() == 0) {
					errorMsgs.add("評論內容: 請勿空白");
				}else if(!commtext.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("評論內容只能是中、英文字母、數字和_ , 且長度必需在1到200之間");
	            }
				/***************************5.星級數**********************/
				Integer commstar = null;
				
				try {
					commstar = Integer.parseInt(req.getParameter("commstar").trim());
			
				} catch (Exception e) {
					errorMsgs.add("星級數不正確");
				}
				/***************************5.新增日期 **********************/				
				
				java.sql.Date adddate = null;

					adddate=new java.sql.Date(System.currentTimeMillis());

/*------------------------------------------------------------------------------------------------------------------*/		

				ProVO proVO = new ProVO();
				proVO.setProductid(productid);
				proVO.setMemberid(memberid);
				proVO.setCommtext(commtext);
				proVO.setCommstar(commstar);
				proVO.setAdddate(adddate);

				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("proVO", proVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ordermaster/list_ordermaster.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ProService proSvc = new ProService();
				String status = "Y";
				proVO = proSvc.addPro(productid, memberid, commtext, commstar, adddate, status);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/ordermaster/list_ordermaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ordermaster/list_ordermaster.jsp");
				failureView.forward(req, res);
			}
		}
/*------------------------------------------------------------------------------------------------------------------*/		

		if ("delete".equals(action)) { // 來自listAllEmp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String prodcommid = new String(req.getParameter("prodcommid"));
				
				/***************************2.開始刪除資料***************************************/
				ProService cisSvc = new ProService();
				cisSvc.deletePro(prodcommid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/pro/listAllPro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/pro/listAllPro.jsp");
				failureView.forward(req, res);
			}
		}
	
	
/*-------------------修改狀態-----------------------------------------------------------------------------------------------*/		
if ("updatetext".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String prodcommid = new String(req.getParameter("prodcommid").trim());

				/***************************7.評論狀態**********************/
				String status = req.getParameter("status").trim();
		
/*------------------------------------------------------------------------------------------------------------------*/		
			
				ProVO proVO = new ProVO();
				proVO.setProdcommid(prodcommid);
				proVO.setStatus(status);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("proVO", proVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/pro/update_pro_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/***************************2.開始修改資料*****************************************/
				ProService proSvc = new ProService();
				proVO = proSvc.updatestatus(prodcommid, status);
				/***************修改完直接查詢*************/
				ProVO Inquire = new ProVO();
				Inquire = proSvc.getOnePro(prodcommid);
				req.setAttribute("proVO", Inquire);
				String url = "/back-end/pro/listOnePro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/pro/update_pro_input.jsp");
				failureView.forward(req, res);
			}
		}
/*------------------------------------------------------------------------------------------------------------------*/		

	}
}
