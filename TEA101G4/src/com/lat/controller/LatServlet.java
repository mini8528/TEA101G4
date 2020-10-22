package com.lat.controller;

import java.io.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.lat.model.*;

@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024, maxRequestSize = 5*5*1024*1024)
@WebServlet("/back-end/lat/lat.do")
public class LatServlet extends HttpServlet{
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
				
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("latestnewsid");
//				System.out.print(str);


				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入最新活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/lat/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String latestnewsid = null;
				try {
					latestnewsid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/lat/select_page.jsp");
					failureView.forward(req, res);
//					System.out.print("111");

					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				LatService latSvc = new LatService();
				LatVO latVO = (LatVO)latSvc.getOneLat(latestnewsid);
				if (latVO == null) {
					errorMsgs.add("查無資料");
//					System.out.print("sss2");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/lat/select_page.jsp");
					failureView.forward(req, res);
//					System.out.print("sss3");
					return;//程式中斷
				}
				System.out.print("sss4");
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("latVO", latVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/lat/listOneLat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				System.out.print("sss5");
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/lat/select_page.jsp");
				failureView.forward(req, res);

			}
		}
		
/*------------------------------------------------------------------------------------------------------------------*/		

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			System.out.print("aaa1");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.print("aaa2");
			try {
				/***************************1.接收請求參數****************************************/
				String latestnewsid = new String(req.getParameter("latestnewsid"));
				System.out.print("aaa3");
				/***************************2.開始查詢資料****************************************/
				LatService admSvc = new LatService();
				LatVO latVO = admSvc.getOneLat(latestnewsid);
				System.out.print("aaa4");	
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("latVO", latVO);         // 資料庫取出的admVO物件,存入req	
				String url = "/back-end/lat/update_lat_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				System.out.print("aaa5");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/lat/listAllLat.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("update".equals(action)) { // 來自update_lat_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			System.out.println("yaaa");
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String latestnewsid = new String(req.getParameter("latestnewsid").trim());
				/***************************1a.管理員姓名**********************/
		System.out.println("yaaa2");
				String adminid = req.getParameter("adminid");
				String memberReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,400}$";
				if (adminid == null || adminid.trim().length() == 0) {
					errorMsgs.add("管理員姓名: 請勿空白");
				} else if(!adminid.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				/***************************1b.管理員姓名2**********************/
		System.out.println("yaaa3");
				String admin2id = req.getParameter("admin2id");
				if (admin2id == null || admin2id.trim().length() == 0) {
					errorMsgs.add("管理員姓名: 請勿空白");
				} else if(!admin2id.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }	
				/***************************1b.文章**********************/
		System.out.println("yaaa4");
				
				String text = req.getParameter("text").trim();
				if (text == null || text.trim().length() == 0) {
					errorMsgs.add("文章請勿空白");
				}
//				else if(!text.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("文章只能是中、英文字母、數字和_ , 且長度必需在1到300之間");
//	            }
				
				/***************************1e.活動圖片**********************/
		System.out.println("yaaa5");
				byte[] image =null;
				Part image1 = req.getPart("image");
				try {
				InputStream fis = image1.getInputStream();
				ByteArrayOutputStream boas = new ByteArrayOutputStream();
				byte[] buffer = new byte[8192] ;
				int i ;
				while ((i=fis.read(buffer)) !=-1) {
					boas.write(buffer, 0, i);
				}
				boas.close();
				fis.close();
				image = boas.toByteArray();
				}catch(Exception e){
					e.printStackTrace();
				}
				
				/***************************1d.消息新增時間**********************/
		System.out.println("yaaa6");
				java.sql.Date adddate = null;
				try {
					adddate = java.sql.Date.valueOf(req.getParameter("adddate").trim());
				} catch (IllegalArgumentException e) {
					adddate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期1!");
				}
				
				/***************************1d.消息更新時間**********************/
				System.out.println("yaaa7");
				java.sql.Date updatetime = null;
//				try {
					updatetime=new java.sql.Date(System.currentTimeMillis());
//				} catch (IllegalArgumentException e) {
//					updatetime=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				/***************************1d.消息上傳時間**********************/
				System.out.println("yaaa8");
				java.sql.Date uploaddate = null;
				try {
					uploaddate=new java.sql.Date(System.currentTimeMillis());
				} catch (IllegalArgumentException e) {
					uploaddate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期2!");
				}

		
				System.out.println("yaaa9");
				
				LatVO latVO = new LatVO();
				latVO.setLatestnewsid(latestnewsid);
				latVO.setAdminid(adminid);
				latVO.setAdmin2id(admin2id);
				latVO.setText(text);
				latVO.setImage(image);
				latVO.setAdddate(adddate);
				latVO.setUpdatetime(updatetime);
				latVO.setUploaddate(uploaddate);

				System.out.println("yaaa10");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("latVO", latVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/lat/update_lat_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				System.out.println("yaaa11");
				/***************************2.開始修改資料*****************************************/
				LatService ladSvc = new LatService();
				latVO = ladSvc.updateLat(latestnewsid,adminid, admin2id, text, image, adddate, updatetime, uploaddate);
				System.out.println("yaaa12");
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("latVO", latVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/lat/listOneLat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				System.out.println("yaaa13");
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/lat/update_lat_input.jsp");
				failureView.forward(req, res);
			}
		}
/*------------------------------------------------------------------------------------------------------------------*/		

        if ("insert".equals(action)) { // 來自addLat.jsp的請求  
			System.out.println("AAA1");
			List<String> errorMsgs = new LinkedList<String>();
			
			
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("yaaa1");

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				/***************************1a.管理員姓名**********************/
				System.out.println("yaaa2");
				String adminid = req.getParameter("adminid");
				String memberReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (adminid == null || adminid.trim().length() == 0) {
					errorMsgs.add("管理員姓名: 請勿空白");
				} else if(!adminid.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				/***************************1b.管理員姓名2**********************/
				System.out.println("yaaa3");
//				String adminNull = "adminNull";
//				String admin2id = req.getParameter(adminNull);
//				String admin2id = req.getParameter("admin2id");
//				if (admin2id == null || admin2id.trim().length() == 0) {
//					errorMsgs.add("管理員姓名: 請勿空白");
//				} else if(!admin2id.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }	
				/***************************1b.文章**********************/
				System.out.println("yaaa4");
				
				String text = req.getParameter("text").trim();
				if (text == null || text.trim().length() == 0) {
					errorMsgs.add("文章請勿空白");
				}
//				else if(!text.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("文章只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				
				/***************************1e.活動圖片**********************/
				System.out.println("yaaa5");
				
				byte[] image = null;
				Part image1 = req.getPart("image");
				try {
					InputStream fis = image1.getInputStream();
//					image= new byte[fis.available()];
//					fis.read(image);
					ByteArrayOutputStream boas = new ByteArrayOutputStream();
					byte[] buffer = new byte[8192];
					int i;
					while ((i = fis.read(buffer)) != -1) {
						boas.write(buffer, 0, i);
					}
					boas.close();
					fis.close();
					image = boas.toByteArray();
				} catch (Exception e) {
					e.printStackTrace();
				}
				/***************************1d.消息新增時間**********************/
				System.out.println("yaaa6");
				java.sql.Date adddate = null;
//				try {
					adddate=new java.sql.Date(System.currentTimeMillis());
//				} catch (IllegalArgumentException e) {
//					adddate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				
				/***************************1d.消息更新時間**********************/
//				System.out.println("yaaa7");
//				java.sql.Date updatetime = null;
//				try {
//					updatetime = java.sql.Date.valueOf(req.getParameter("updatetime").trim());
//				} catch (IllegalArgumentException e) {
//					updatetime=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				/***************************1d.消息上傳時間**********************/
				System.out.println("yaaa8");
				java.sql.Date uploaddate = null;
				uploaddate=new java.sql.Date(System.currentTimeMillis());
//				try {
//					uploaddate = java.sql.Date.valueOf(req.getParameter("uploaddate").trim());
//				} catch (IllegalArgumentException e) {
//					uploaddate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}


				System.out.println("yaaa9");
				
				LatVO latVO = new LatVO();
				latVO.setAdminid(adminid);
//				latVO.setAdmin2id(admin2id);
				latVO.setText(text);
				latVO.setImage(image);
				latVO.setAdddate(adddate);
//				latVO.setUpdatetime(updatetime);
				latVO.setUploaddate(uploaddate);

				System.out.println("yaaa10");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("latVO", latVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/lat/addLat.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				System.out.println("yaaa11");
				/***************************2.開始新增資料*****************************************/
				LatService ladSvc = new LatService();
				latVO = ladSvc.addLat(adminid, null, text, image, adddate, null,uploaddate);
				System.out.println("yaaa12");
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("latVO", latVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/lat/listAllLat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				System.out.println("yaaa13");
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/lat/addLat.jsp");
				failureView.forward(req, res);
			}
		}
/*------------------------------------------------------------------------------------------------------------------*/		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp
	System.out.println("d");
			List<String> errorMsgs = new LinkedList<String>();
			
			
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數***************************************/
				String latestnewsid = new String(req.getParameter("latestnewsid"));
				System.out.println("latestnewsid="+latestnewsid);
				/***************************2.開始刪除資料***************************************/
				LatService ladSvc = new LatService();
				ladSvc.deleteLat(latestnewsid);;
				System.out.println("d3");
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/lat/listAllLat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				System.out.println("d4");
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/lat/listAllLat.jsp");
//				failureView.forward(req, res);
//				System.out.println("d5");
//			}
		}
	}
	
}