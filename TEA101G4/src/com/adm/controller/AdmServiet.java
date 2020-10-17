package com.adm.controller;

import java.io.*;

import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.adm.model.*;

@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024, maxRequestSize = 5*5*1024*1024)
@WebServlet("/back-end/adm/adm.do")
public class AdmServiet  extends HttpServlet{
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
				String str = req.getParameter("adminid");
//				輸入錯誤的處理（沒輸入或空白｜轉跳頁面）
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adm/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String adminid = null;
				try {
					adminid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adm/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AdminnoService admSvc = new AdminnoService();
				AdminnoVO admVO = (AdminnoVO)admSvc.getOneAdm(adminid);
				if (admVO == null) {
					errorMsgs.add("查無資料");
					System.out.print("aaaaaaa2");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adm/select_page.jsp");
					failureView.forward(req, res);
					System.out.print("aaaaa666");
					return;//程式中斷
				}
				System.out.print("bbb");
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("admVO", admVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/adm/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				System.out.print("aaaaaaaaaaaaaaaaaaaaaaaaa1");
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/adm/select_page.jsp");
				failureView.forward(req, res);
				System.out.print("aa7777");

			}
		}
		
/*------------------------------------------------------------------------------------------------------------------*/		

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String adminid = new String(req.getParameter("adminid"));
				System.out.print("aaa1");
				/***************************2.開始查詢資料****************************************/
				AdminnoService admSvc = new AdminnoService();
				AdminnoVO admVO = admSvc.getOneAdm(adminid);
						
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("admVO", admVO);         // 資料庫取出的admVO物件,存入req	
				String url = "/back-end/adm/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				System.out.print("aaaa2");
				successView.forward(req, res);
                System.out.print("aaaaaaaaa3");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/adm/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			System.out.println("yaaa");
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String adminid = new String(req.getParameter("adminid").trim());
				/***************************1a.管理員姓名**********************/
				System.out.println("yaaa2");
				String membername = req.getParameter("membername");
				String memberReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (membername == null || membername.trim().length() == 0) {
					errorMsgs.add("管理員姓名: 請勿空白");
				} else if(!membername.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				/***************************1b.管理員帳號**********************/
				System.out.println("yaaa3");
				String memberuser = req.getParameter("memberuser").trim();
				
				if (memberuser == null || memberuser.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}else if(!memberuser.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("帳號只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }	
				/***************************1b.管理員密碼**********************/
				System.out.println("yaaa4");
				String passwd = req.getParameter("passwd").trim();
				
				if (passwd == null || passwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}else if(!passwd.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }	
				System.out.println("yaaa5");
				/***************************1c.管理員性別**********************/
				String gender = req.getParameter("gender").trim();
				
//				if (gender == null || passwd.trim().length() == 0 ) {
//					errorMsgs.add("密碼請勿空白");
//				}else if(!gender.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("性別只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				/***************************1d.管理員電話**********************/
				System.out.println("yaaa6");

				String phone = req.getParameter("phone").trim();	
//				if (gender == null || passwd.trim().length() == 0 ) {
//					errorMsgs.add("電話請勿空白");
//				}else if(!gender.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("電話只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				/***************************1d.管理員入職時間**********************/
				System.out.println("yaaa7");
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					birthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				System.out.println("yaaa77");
				/***************************1d.管理員信箱**********************/
				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0 ) {
					errorMsgs.add("信箱請勿空白");
				}
				
				/***************************1e.管理員圖片**********************/
				System.out.println("yaaa8");
				byte[] photo =null;
				Part photo1 = req.getPart("photo");
				try {
				InputStream fis = photo1.getInputStream();
				ByteArrayOutputStream boas = new ByteArrayOutputStream();
				byte[] buffer = new byte[8192] ;
				int i ;
				while ((i=fis.read(buffer)) !=-1) {
					boas.write(buffer, 0, i);
				}
				boas.close();
				fis.close();
				photo = boas.toByteArray();
				}catch(Exception e){
					e.printStackTrace();
				}
			
				/***************************1e.管理員地址**********************/
				System.out.println("yaaa9");
				String address = req.getParameter("address");
		
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				} else if(!address.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地址只能是中、大寫英文字母、數字");
	            }
		
/*------------------------------------------------------------------------------------------------------------------*/		

				//Integer deptno = new Integer(req.getParameter("deptno").trim());
				System.out.println("yaaa10");
				
				AdminnoVO admVO = new AdminnoVO();
				admVO.setAdminid(adminid);
				admVO.setMembername(membername);
				admVO.setMemberuser(memberuser);
				admVO.setPasswd(passwd);
				admVO.setGender(gender);
				admVO.setPhone(phone);
				admVO.setBirthday(birthday);
				admVO.setEmail(email);
				admVO.setPhoto(photo);
				admVO.setAddress(address);

				System.out.println("yaaa11");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adm/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				System.out.println("yaaa12");
				/***************************2.開始修改資料*****************************************/
				AdminnoService admSvc = new AdminnoService();
				admVO = admSvc.updateAdm(adminid, membername, memberuser, passwd, gender, phone, birthday, email, photo, address);
				System.out.println("yaaa13");
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("admVO", admVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/adm/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				System.out.println("yaaa14");
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/adm/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}
/*------------------------------------------------------------------------------------------------------------------*/		

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			System.out.println("AAA1");
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			
				/***************************1a.管理員姓名**********************/
			System.out.println("AAA2");
				String membername = req.getParameter("membername");
				String memberReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				String memberNum = "^[0-9]*$";
				
				if (membername == null || membername.trim().length() == 0) {
					errorMsgs.add("管理員姓名: 請勿空白");
				} else if(!membername.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				/***************************1b.管理員帳號**********************/
				System.out.println("YYYY");
				String memberuser = req.getParameter("memberuser").trim();
				
				if (memberuser == null || memberuser.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}else if(!memberuser.trim().matches(memberReg)) { 
					errorMsgs.add("帳號只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }	
				/***************************1b.管理員密碼**********************/
				System.out.println("YYYY2");
				String passwd = req.getParameter("passwd").trim();
				
				if (passwd == null || passwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}else if(!passwd.trim().matches(memberReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }	
				
				/***************************1c.管理員性別**********************/
				System.out.println("YYYY3");
				String gender = req.getParameter("gender").trim();
				
				/***************************1d.管理員電話**********************/
				System.out.println("YYYY4");
				String phone = req.getParameter("phone").trim();	
				if (phone == null || phone.trim().length() == 0 ) {
					errorMsgs.add("電話請勿空白");
				}else if(!phone.trim().matches(memberNum)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電話只能是數字");
	            }
				/***************************1d.管理員入職時間**********************/
				System.out.println("YYYY5");
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					birthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				/***************************1d.管理員信箱**********************/
				System.out.println("YYYY6");
				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0 ) {
					errorMsgs.add("信箱請勿空白");
				}
				/***************************1e.管理員圖片**********************/
				System.out.println("YYYY7");
				byte[] photo = null;
				Part photo1 = req.getPart("photo");
//				System.out.println(photo1);
				try {
					InputStream fis = photo1.getInputStream();
//					photo= new byte[fis.available()];
//					fis.read(photo);
					ByteArrayOutputStream boas = new ByteArrayOutputStream();
					byte[] buffer = new byte[8192];
					int i;
					while ((i = fis.read(buffer)) != -1) {
						boas.write(buffer, 0, i);
					}
					boas.close();
					fis.close();
					photo = boas.toByteArray();
//					System.out.println("Photo"+photo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				/***************************1e.管理員地址**********************/
				System.out.println("YYYY8");
				String address = req.getParameter("address");
				String addressReg = "^[(\u4e00-\u9fa5)(A-Z0-9)]{2,30}$";
				
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				} else if(!address.trim().matches(addressReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("地址只能是中、大寫英文字母、數字");
	            }
				System.out.println("AAA3");

				AdminnoVO admVO = new AdminnoVO();
				admVO.setMembername(membername);
				admVO.setMemberuser(memberuser);
				admVO.setPasswd(passwd);
				admVO.setGender(gender);
				admVO.setPhone(phone);
				admVO.setBirthday(birthday);
				admVO.setEmail(email);
				admVO.setPhoto(photo);
				admVO.setAddress(address);
				System.out.println("AAA4");

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adm/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				System.out.println("AAA5");
				AdminnoService admSvc = new AdminnoService();
				admVO = admSvc.addAdm(membername, memberuser, passwd, gender, phone, birthday, email, photo, address);
				System.out.println("AAA6");
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/adm/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				System.out.println("AAA7");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/adm/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
/*------------------------------------------------------------------------------------------------------------------*/		

		if ("delete".equals(action)) { // 來自listAllEmp.jsp
System.out.println("yaaa");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String adminid = new String(req.getParameter("adminid"));
				
				/***************************2.開始刪除資料***************************************/
				AdminnoService admSvc = new AdminnoService();
				admSvc.deleteAdm(adminid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/adm/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/adm/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
}
