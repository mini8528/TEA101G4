package com.bodyrecord.controller;

import java.io.IOException;
import java.io.InputStream;
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

import com.bodyrecord.model.BodyrecordService;
import com.bodyrecord.model.BodyrecordVO;
import com.member.model.MemberService;



@WebServlet("/BodyrecordServlet")
@MultipartConfig
public class BodyrecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				doGet(req, res);
				
				req.setCharacterEncoding("UTF-8");
				String action = req.getParameter("action");

				if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					
					try {
						/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						String str = req.getParameter("bodyrecordid");
						if (str == null || (str.trim()).length() == 0) {
							errorMsgs.add("請輸入體態紀錄編號");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/bodyrecord/select_page.jsp");
							failureView.forward(req, res);
							return;// 程式中斷
						}

						String bodyrecordid = null;
						try {
							bodyrecordid = new String(str);
						} catch (Exception e) {
							errorMsgs.add("體態紀錄編號格式不正確");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/bodyrecord/select_page.jsp");
							failureView.forward(req, res);
							return;// 程式中斷
						}
						/*************************** 2.開始查詢資料 *****************************************/
						BodyrecordService bodyrecordSvc = new BodyrecordService();
						BodyrecordVO bodyrecordVO = bodyrecordSvc.getOneBodyrecord(bodyrecordid);
						if (bodyrecordVO == null) {
							errorMsgs.add("查無資料");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/bodyrecord/select_page.jsp");
							failureView.forward(req, res);
							return;// 程式中斷
						}
						
						/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
						req.setAttribute("bodyrecordVO", bodyrecordVO); // 資料庫取出的empVO物件,存入req
						String url = "/bodyrecord/listOneBodyrecord.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 *************************************/
		       }catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
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
						String bodyrecordid = req.getParameter("bodyrecordid");
						
						/***************************2.開始查詢資料****************************************/
						BodyrecordService bodyrecordSvc = new BodyrecordService();
						BodyrecordVO bodyrecordVO = bodyrecordSvc.getOneBodyrecord(bodyrecordid);//查Ation table 的動作id?
					
						/***************************3.查詢完成,準備轉交(Send the Success view)************/
						req.setAttribute("bodyrecordVO", bodyrecordVO);         // 資料庫取出的empVO物件,存入req
						String url = "/bodyrecord/update_bodyrecord_input.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_action_input.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						e.printStackTrace();
						errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/bodyrecord/listAllBodyrecord.jsp");
						failureView.forward(req, res);
					}
				}
				
				if ("update".equals(action)) { // 來自addaction.jsp的請求  
					
					 List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);
						try {
							/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/

							String bodyrecordid = req.getParameter("bodyrecordid").trim();
							if (bodyrecordid == null || bodyrecordid.trim().length() == 0) {
								errorMsgs.add("收藏編號請勿空白");
							}
							String memberId = req.getParameter("memberId").trim();
							if (memberId == null || memberId.trim().length() == 0) {
								errorMsgs.add("會員編號請勿空白");
							}
							java.sql.Date uploaddate = null;
							try {
								uploaddate = java.sql.Date.valueOf(req.getParameter("uploaddate").trim());
							} catch (IllegalArgumentException e) {
								uploaddate=new java.sql.Date(System.currentTimeMillis());
								errorMsgs.add("請輸入上傳日期!");
							}
							BodyrecordService bodyrecordSvc = new BodyrecordService();
							Part photo1 = req.getPart("photo1");
							InputStream in = photo1.getInputStream();
							byte[] buf = null;
							if (in.available() != 0) {
								buf = new byte[in.available()];
								in.read(buf);
								in.close();
							} else {
								buf = bodyrecordSvc.getOneBodyrecord(bodyrecordid).getphoto1();
							}
							String description = req.getParameter("description").trim();
							if (description == null || description.trim().length() == 0) {
								errorMsgs.add("內容描述請勿空白");
							}
							String describestatus = req.getParameter("describestatus").trim();
							if (describestatus == null || describestatus.trim().length() == 0) {
								errorMsgs.add("內文請勿空白，請輸入Y或N");
							}
							java.sql.Date updatetime = null;
							try {
								updatetime = java.sql.Date.valueOf(req.getParameter("updatetime").trim());
							} catch (IllegalArgumentException e) {
								updatetime=new java.sql.Date(System.currentTimeMillis());
								errorMsgs.add("請輸入更新日期!");
							}
							
							BodyrecordVO bodyrecordVO = new BodyrecordVO();
							bodyrecordVO.setbodyrecordid(bodyrecordid);
							bodyrecordVO.setmemberId(memberId);
							bodyrecordVO.setuploaddate(uploaddate);
							bodyrecordVO.setphoto1(buf);
							bodyrecordVO.setdescription(description);
							bodyrecordVO.setdescribestatus(describestatus);
							bodyrecordVO.setupdatetime(updatetime);
							
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								req.setAttribute("bodyrecordVO", bodyrecordVO); // 含有輸入格式錯誤的empVO物件,也存入req
								RequestDispatcher failureView = req.getRequestDispatcher("/bodyrecord/update_bodyrecord_input.jsp");
								failureView.forward(req, res);
								return; // 程式中斷
							}
							/*************************** 2.開始修改資料 *****************************************/
							
							
							bodyrecordVO = bodyrecordSvc.updateBodyrecord(bodyrecordid, memberId, uploaddate, buf, description, describestatus,updatetime);
									

							/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
							req.setAttribute("bodyrecordVO", bodyrecordVO); // 資料庫update成功後,正確的的empVO物件,存入req
							String url = "/bodyrecord/listOneBodyrecord.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
							successView.forward(req, res);

							/*************************** 其他可能的錯誤處理 *************************************/
						}catch (Exception e) {
							errorMsgs.add("修改資料失敗:" + e.getMessage());
							RequestDispatcher failureView = req.getRequestDispatcher("/bodyrecord/update_bodyrecord_input.jsp");
							failureView.forward(req, res);
							System.out.println("update 其他錯誤");
							e.printStackTrace();
						}
				}
				if ("insert".equals(action)) { // 來自addEmp.jsp的請求
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					
					try {
						/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
						String bodyrecordid = new String(req.getParameter("bodyrecordid").trim());
						
						String memberId = req.getParameter("memberId").trim();
						if (memberId == null || memberId.trim().length() == 0) {
							errorMsgs.add("會員編號請勿空白");
						}
						java.sql.Date uploaddate = null;
						try {
							uploaddate = java.sql.Date.valueOf(req.getParameter("uploaddate").trim());
						} catch (IllegalArgumentException e) {
							uploaddate=new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入上傳日期!");
						}
						BodyrecordService bodyrecordSvc = new BodyrecordService();
						Part photo1 = req.getPart("photo1");
						InputStream in = photo1.getInputStream();
						byte[] buf = null;
						if (in.available() != 0) {
							buf = new byte[in.available()];
							in.read(buf);
							in.close();
						} else {
							buf = bodyrecordSvc.getOneBodyrecord(bodyrecordid).getphoto1();
						}
						String description = req.getParameter("description").trim();
						if (description == null || description.trim().length() == 0) {
							errorMsgs.add("內容描述請勿空白");
						}
						String describestatus = req.getParameter("describestatus").trim();
						if (describestatus == null || describestatus.trim().length() == 0) {
							errorMsgs.add("內文請勿空白，請輸入Y或N");
						}
						java.sql.Date updatetime = null;
						try {
							updatetime = java.sql.Date.valueOf(req.getParameter("updatetime").trim());
						} catch (IllegalArgumentException e) {
							updatetime=new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入更新日期!");
						}
						
						BodyrecordVO bodyrecordVO = new BodyrecordVO();
						
						bodyrecordVO.setmemberId(memberId);
						bodyrecordVO.setuploaddate(uploaddate);
						bodyrecordVO.setphoto1(buf);
						bodyrecordVO.setdescription(description);
						bodyrecordVO.setdescribestatus(describestatus);
						bodyrecordVO.setupdatetime(updatetime);
						
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("bodyrecordVO", bodyrecordVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = req.getRequestDispatcher("/bodyrecord/update_bodyrecord_input.jsp");
							failureView.forward(req, res);
							return; // 程式中斷
						}
						/*************************** 2.開始新增資料 ***************************************/
//						BodyrecordService bodyrecordSvc = new BodyrecordService();
						bodyrecordVO = bodyrecordSvc.addBodyrecord(memberId, uploaddate, buf, description, describestatus,updatetime);
						
						/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
						String url = "/bodyrecord/listAllBodyrecord.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 **********************************/

					}catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/bodyrecord/addBodyrecord.jsp");
						failureView.forward(req, res);
						e.printStackTrace();
						System.out.println("其他錯誤處理");
					}
				}
				
				if ("delete".equals(action)) { // 來自listAllEmp.jsp

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/*************************** 1.接收請求參數 ***************************************/
						String bodyrecordid = new String(req.getParameter("bodyrecordid"));

						/*************************** 2.開始刪除資料 ***************************************/
						BodyrecordService bodyrecordSvc = new BodyrecordService();
						bodyrecordSvc.deleteBodyrecord(bodyrecordid);;

						/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
						String url = "/bodyrecord/listAllBodyrecord.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception e) {
						errorMsgs.add("刪除資料失敗:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/bodyrecord/listAllBodyrecord.jsp");
						failureView.forward(req, res);
				}
			 }		 	
	 }
}
