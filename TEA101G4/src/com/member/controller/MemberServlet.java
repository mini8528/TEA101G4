package com.member.controller;

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

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.mysql.fabric.xmlrpc.base.Member;


@WebServlet("/member/MemberServlet")
@MultipartConfig
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("memberid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
			}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				String memberid = null;
				try {
					memberid = new String(str);
				} catch(Exception e) {
					errorMsgs.add("會員編碼格是不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(memberid);
				if(memberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String memberid = req.getParameter("memberid");

				/*************************** 2.開始查詢資料 ****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(memberid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_blog_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memberid = new String(req.getParameter("memberid").trim());
				
				String name = req.getParameter("name").trim();
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("文章編號請勿空白");
				}
				
				String account = req.getParameter("account");
				String accountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("一般會員帳號: 請勿空白");
				} else if (!account.trim().matches(accountReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("一般會員帳號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String password = req.getParameter("password");
				String passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("一般會員密碼: 請勿空白");
				} else if (!password.trim().matches(passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("一般會員密碼: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				String gender = req.getParameter("gender").trim();
				if (gender == null || gender.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				
//				Timestamp birthday = new Timestamp(System.currentTimeMillis());
				
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				}catch (IllegalArgumentException e) {
					birthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				
				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				
//				memberid = new String(req.getParameter("memberid").trim());

				MemberService memberSvc = new MemberService();
				Part photo = req.getPart("photo");
				InputStream in = photo.getInputStream();
				byte[] buf = null;
				if (in.available() != 0) {
					buf = new byte[in.available()];
					in.read(buf);
					in.close();
				} else {
					buf = memberSvc.getOneMember(memberid).getPhoto();
				}
				
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String authority = req.getParameter("authority").trim();
				if (authority == null || authority.trim().length() == 0) {
					errorMsgs.add("內文請勿空白，請輸入Y或N");
				}
				String qualifications = req.getParameter("qualifications").trim();
				if (qualifications == null || qualifications.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String expertise = req.getParameter("expertise").trim();
				if (expertise == null || expertise.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String introduction = req.getParameter("introduction").trim();
				if (introduction == null || introduction.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				
				Part photo1 = req.getPart("photo1");
				InputStream in1 = photo1.getInputStream();
				byte[] buf1 = null;
				if (in1.available() != 0) {
					buf1 = new byte[in1.available()];
					in1.read(buf1);
					in1.close();
				} else {
					buf1 = memberSvc.getOneMember(memberid).getPhoto1();
				}
				
				Part photo2 = req.getPart("photo2");
				InputStream in2 = photo2.getInputStream();
				byte[] buf2 = null;
				if (in2.available() != 0) {
					buf2 = new byte[in2.available()];
					in2.read(buf2);
					in2.close();
				} else {
					buf2 = memberSvc.getOneMember(memberid).getPhoto2();
				}
				
				Part photo3 = req.getPart("photo3");
				InputStream in3 = photo3.getInputStream();
				byte[] buf3 = null;
				if (in3.available() != 0) {
					buf3 = new byte[in3.available()];
					in3.read(buf3);
					in3.close();
				} else {
					buf3 = memberSvc.getOneMember(memberid).getPhoto3();
				}
				Date adddate = new Date(System.currentTimeMillis());

				MemberVO memberVO = new MemberVO();
				
				memberVO.setMemberid(memberid);
				memberVO.setName(name);
				memberVO.setAccount(account);
				memberVO.setPassword(password);
				memberVO.setGender(gender);
				memberVO.setPhone(phone);
				memberVO.setBirthday(birthday);
				memberVO.setEmail(email);
				memberVO.setPhoto(buf);
				memberVO.setAddress(address);
				memberVO.setAuthority(authority);
				memberVO.setQualifications(qualifications);
				memberVO.setExpertise(expertise);
				memberVO.setIntroduction(introduction);
				memberVO.setPhoto1(buf1);
				memberVO.setPhoto2(buf2);
				memberVO.setPhoto3(buf3);
				memberVO.setAdddate(adddate);
		

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/update_member_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				
				
				memberVO = memberSvc.updateMember(memberid,name,account,password,gender,phone,birthday,email,buf,address,authority,qualifications,expertise,introduction,buf1,buf2,buf3,adddate);
						

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/update_Member_input.jsp");
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
				//===========================
				String name = req.getParameter("name").trim();
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("使用者名稱請勿空白");
				}
				
				String account = req.getParameter("account");
				String accountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("一般會員帳號: 請勿空白");
				} else if (!account.trim().matches(accountReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("一般會員帳號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String password = req.getParameter("password");
				String passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("一般會員密碼: 請勿空白");
				} else if (!password.trim().matches(passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("一般會員密碼: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String gender = req.getParameter("gender").trim();
				if (gender == null || gender.trim().length() == 0) {
					errorMsgs.add("性別請勿空白，請輸入M或F");
				}
				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("電話號碼請勿空白");
				}
				
//				Timestamp birthday = new Timestamp(System.currentTimeMillis());
				
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				}catch (IllegalArgumentException e) {
					birthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				
				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("電子郵件請勿空白");
				}
				InputStream in = null;
				byte[] buf = null;
				Part photo = req.getPart("photo");
				in = photo.getInputStream();
				buf = new byte[in.available()];
				in.read(buf);
				in.close();
				
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				String authority = req.getParameter("authority").trim();
				if (authority == null || authority.trim().length() == 0) {
					errorMsgs.add("權限請勿空白，請輸入Y或N");
				}
				String qualifications = req.getParameter("qualifications").trim();
				if (qualifications == null || qualifications.trim().length() == 0) {
					errorMsgs.add("資歷請勿空白");
				}
				String expertise = req.getParameter("expertise").trim();
				if (expertise == null || expertise.trim().length() == 0) {
					errorMsgs.add("專長請勿空白");
				}
				String introduction = req.getParameter("introduction").trim();
				if (introduction == null || introduction.trim().length() == 0) {
					errorMsgs.add("自我介紹請勿空白");
				}
				
				InputStream in1 = null;
				byte[] buf1 = null;
				Part photo1 = req.getPart("photo1");
				in1 = photo1.getInputStream();
				buf1 = new byte[in1.available()];
				in1.read(buf1);
				in1.close();
				
				InputStream in2 = null;
				byte[] buf2 = null;
				Part photo2 = req.getPart("photo2");
				in2 = photo2.getInputStream();
				buf2 = new byte[in2.available()];
				in2.read(buf2);
				in2.close();
				
				InputStream in3 = null;
				byte[] buf3 = null;
				Part photo3 = req.getPart("photo3");
				in3 = photo3.getInputStream();
				buf3 = new byte[in3.available()];
				in3.read(buf3);
				in3.close();
				
				Date adddate = new Date(System.currentTimeMillis());

				MemberVO memberVO = new MemberVO();
				
				memberVO.setName(name);
				memberVO.setAccount(account);
				memberVO.setPassword(password);
				memberVO.setGender(gender);
				memberVO.setPhone(phone);
				memberVO.setBirthday(birthday);
				memberVO.setEmail(email);
				memberVO.setPhoto(buf);
				memberVO.setAddress(address);
				memberVO.setAuthority(authority);
				memberVO.setQualifications(qualifications);
				memberVO.setExpertise(expertise);
				memberVO.setIntroduction(introduction);
				memberVO.setPhoto1(buf1);
				memberVO.setPhoto2(buf2);
				memberVO.setPhoto3(buf3);
				memberVO.setAdddate(adddate);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/addMember.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(name,account,password,gender,phone,birthday,email,buf,address,authority,qualifications,expertise,introduction,buf1,buf2,buf3,adddate);

						
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/addMember.jsp");
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
				String memberid = new String(req.getParameter("memberid"));

				/*************************** 2.開始刪除資料 ***************************************/
				MemberService memberSvc = new MemberService();
				memberSvc.deleteMember(memberid);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
