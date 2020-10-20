package com.member.controller;

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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.bodyRecord.model.BodyRecordService;
import com.bodyRecord.model.BodyRecordVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.mysql.fabric.xmlrpc.base.Member;

@WebServlet("/member/MemberServlet")
@MultipartConfig
public class MemberServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				String memberid = null;
				try {
					memberid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編碼格是不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService mService = new MemberService();
				MemberVO memberVO = mService.getOneMember(memberid);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display1".equals(action)) {

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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				String memberid = null;
				try {
					memberid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編碼格是不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService mService = new MemberService();
				MemberVO memberVO = mService.getOneMember(memberid);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/member/listCoachMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
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
				String memberid = new String(req.getParameter("memberid"));

				/*************************** 2.開始查詢資料 ****************************************/
				MemberService mService = new MemberService();
				MemberVO memberVO = mService.getOneMember(memberid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("userVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update1".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String memberid = req.getParameter("memberid");

				/*************************** 2.開始查詢資料 ****************************************/
				MemberService mService = new MemberService();
				MemberVO memberVO = mService.getOneMember(memberid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("userVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/member/update_member_input1.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update2".equals(action)) { // 來自back-end/listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String memberid = req.getParameter("memberid");

				/*************************** 2.開始查詢資料 ****************************************/
				MemberService mService = new MemberService();
				MemberVO memberVO = mService.getOneMember(memberid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update3".equals(action)) { // 來自back-end/listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String memberid = req.getParameter("memberid");

				/*************************** 2.開始查詢資料 ****************************************/
				MemberService mService = new MemberService();
				MemberVO memberVO = mService.getOneMember(memberid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				HttpSession session = req.getSession();
				session.setAttribute("userVO", memberVO); // 資料庫取出的empVO物件,存入req
				 // 資料庫取出的empVO物件,存入req
				String url = "/front-end/member/update_member_input2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/listAllMember.jsp");
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
					errorMsgs.add("名字請勿空白");
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
					errorMsgs.add("性別請勿空白");
				}

				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}

				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入生日日期");
				}

				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("email請勿空白");
				}

				byte[] photo = null;
				Part photo_a = req.getPart("photo");
				try {
					if (photo_a == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_a.getInputStream();
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

				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				String authority = req.getParameter("authority").trim();
				if (authority == null || authority.trim().length() == 0) {
					errorMsgs.add("權限請勿空白，請輸入Y或N");
				}

				String qualifications = req.getParameter("qualifications").trim();

				String expertise = req.getParameter("expertise").trim();

				String introduction = req.getParameter("introduction").trim();

				byte[] photo1 = null;
				Part photo_b = req.getPart("photo1");
				try {
					if (photo_b == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_b.getInputStream();
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

				byte[] photo2 = null;
				Part photo_c = req.getPart("photo2");
				try {
					if (photo_c == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_c.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = fis.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}
						baos.close();
						fis.close();
						photo2 = baos.toByteArray();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				byte[] photo3 = null;
				Part photo_d = req.getPart("photo3");
				try {
					if (photo_d == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_d.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = fis.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}
						baos.close();
						fis.close();
						photo3 = baos.toByteArray();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				Date adddate = Date.valueOf(req.getParameter("adddate"));

				System.out.println("匯入VO ");
				MemberVO memberVO = new MemberVO();
				memberVO.setMemberid(memberid);
				memberVO.setName(name);
				memberVO.setAccount(account);
				memberVO.setPassword(password);
				memberVO.setGender(gender);
				memberVO.setPhone(phone);
				memberVO.setBirthday(birthday);
				memberVO.setEmail(email);
				memberVO.setPhoto(photo);
				memberVO.setAddress(address);
				memberVO.setAuthority(authority);
				memberVO.setQualifications(qualifications);
				memberVO.setExpertise(expertise);
				memberVO.setIntroduction(introduction);
				memberVO.setPhoto1(photo1);
				memberVO.setPhoto2(photo2);
				memberVO.setPhoto3(photo3);
				memberVO.setAdddate(adddate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/

				System.out.println("開始修改資料 ");
				MemberService mService = new MemberService();
				memberVO = mService.updateMember(memberid, name, account, password, gender, phone, birthday, email,
						photo, address, authority, qualifications, expertise, introduction, photo1, photo2, photo3, adddate);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("userVO", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
				HttpSession session = req.getSession();
				session.setAttribute("userVO", memberVO);
				String url = "/front-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/update_member_input.jsp");
				failureView.forward(req, res);
				System.out.println("update 其他錯誤");
				e.printStackTrace();
			}
		}
		if ("update1".equals(action)) { // 來自update_blog_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memberid = new String(req.getParameter("memberid").trim());

				String name = req.getParameter("name").trim();
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("名字請勿空白");
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
					errorMsgs.add("性別請勿空白");
				}

				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}

				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入生日日期");
				}

				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("email請勿空白");
				}

				byte[] photo = null;
				Part photo_a = req.getPart("photo");
				try {
					if (photo_a == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_a.getInputStream();
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

				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				String authority = req.getParameter("authority").trim();
				if (authority == null || authority.trim().length() == 0) {
					errorMsgs.add("權限請勿空白，請輸入Y或N");
				}

				String qualifications = req.getParameter("qualifications").trim();

				String expertise = req.getParameter("expertise").trim();

				String introduction = req.getParameter("introduction").trim();

				byte[] photo1 = null;
				Part photo_b = req.getPart("photo1");
				try {
					if (photo_b == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_b.getInputStream();
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

				byte[] photo2 = null;
				Part photo_c = req.getPart("photo2");
				try {
					if (photo_c == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_c.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = fis.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}
						baos.close();
						fis.close();
						photo2 = baos.toByteArray();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				byte[] photo3 = null;
				Part photo_d = req.getPart("photo3");
				try {
					if (photo_d == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_d.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = fis.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}
						baos.close();
						fis.close();
						photo3 = baos.toByteArray();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				Date adddate = Date.valueOf(req.getParameter("adddate"));

				System.out.println("匯入VO ");
				MemberVO memberVO = new MemberVO();
				memberVO.setMemberid(memberid);
				memberVO.setName(name);
				memberVO.setAccount(account);
				memberVO.setPassword(password);
				memberVO.setGender(gender);
				memberVO.setPhone(phone);
				memberVO.setBirthday(birthday);
				memberVO.setEmail(email);
				memberVO.setPhoto(photo);
				memberVO.setAddress(address);
				memberVO.setAuthority(authority);
				memberVO.setQualifications(qualifications);
				memberVO.setExpertise(expertise);
				memberVO.setIntroduction(introduction);
				memberVO.setPhoto1(photo1);
				memberVO.setPhoto2(photo2);
				memberVO.setPhoto3(photo3);
				memberVO.setAdddate(adddate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_member_input1.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/

				System.out.println("開始修改資料 ");
				MemberService mService = new MemberService();
				memberVO = mService.updateMember(memberid, name, account, password, gender, phone, birthday, email,
						photo, address, authority, qualifications, expertise, introduction, photo1, photo2, photo3, adddate);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("userVO", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/update_member_input1.jsp");
				failureView.forward(req, res);
				System.out.println("update 其他錯誤");
				e.printStackTrace();
			}
		}
		if ("update2".equals(action)) { // 來自update_blog_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memberid = new String(req.getParameter("memberid").trim());

				String name = req.getParameter("name").trim();
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("名字請勿空白");
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
					errorMsgs.add("性別請勿空白");
				}

				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}

				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入生日日期");
				}

				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("email請勿空白");
				}

				byte[] photo = null;
				Part photo_a = req.getPart("photo");
				try {
					if (photo_a == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_a.getInputStream();
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

				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				String authority = req.getParameter("authority").trim();
				if (authority == null || authority.trim().length() == 0) {
					errorMsgs.add("權限請勿空白，請輸入Y或N");
				}

				String qualifications = req.getParameter("qualifications").trim();

				String expertise = req.getParameter("expertise").trim();

				String introduction = req.getParameter("introduction").trim();

				byte[] photo1 = null;
				Part photo_b = req.getPart("photo1");
				try {
					if (photo_b == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_b.getInputStream();
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

				byte[] photo2 = null;
				Part photo_c = req.getPart("photo2");
				try {
					if (photo_c == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_c.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = fis.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}
						baos.close();
						fis.close();
						photo2 = baos.toByteArray();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				byte[] photo3 = null;
				Part photo_d = req.getPart("photo3");
				try {
					if (photo_d == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_d.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = fis.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}
						baos.close();
						fis.close();
						photo3 = baos.toByteArray();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				Date adddate = Date.valueOf(req.getParameter("adddate"));

				System.out.println("匯入VO ");
				MemberVO memberVO = new MemberVO();
				memberVO.setMemberid(memberid);
				memberVO.setName(name);
				memberVO.setAccount(account);
				memberVO.setPassword(password);
				memberVO.setGender(gender);
				memberVO.setPhone(phone);
				memberVO.setBirthday(birthday);
				memberVO.setEmail(email);
				memberVO.setPhoto(photo);
				memberVO.setAddress(address);
				memberVO.setAuthority(authority);
				memberVO.setQualifications(qualifications);
				memberVO.setExpertise(expertise);
				memberVO.setIntroduction(introduction);
				memberVO.setPhoto1(photo1);
				memberVO.setPhoto2(photo2);
				memberVO.setPhoto3(photo3);
				memberVO.setAdddate(adddate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/

				System.out.println("開始修改資料 ");
				MemberService mService = new MemberService();
				memberVO = mService.updateMember(memberid, name, account, password, gender, phone, birthday, email,
						photo, address, authority, qualifications, expertise, introduction, photo1, photo2, photo3,
						adddate);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				failureView.forward(req, res);
				System.out.println("update 其他錯誤");
				e.printStackTrace();
			}
		}
		if ("update3".equals(action)) { // 來自update_blog_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memberid = new String(req.getParameter("memberid").trim());

				String name = req.getParameter("name").trim();
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("名字請勿空白");
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
					errorMsgs.add("性別請勿空白");
				}

				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}

				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入生日日期");
				}

				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("email請勿空白");
				}

				byte[] photo = null;
				Part photo_a = req.getPart("photo");
				try {
					if (photo_a == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_a.getInputStream();
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

				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				String authority = req.getParameter("authority").trim();
				if (authority == null || authority.trim().length() == 0) {
					errorMsgs.add("權限請勿空白，請輸入Y或N");
				}

				String qualifications = req.getParameter("qualifications").trim();

				String expertise = req.getParameter("expertise").trim();

				String introduction = req.getParameter("introduction").trim();

				byte[] photo1 = null;
				Part photo_b = req.getPart("photo1");
				try {
					if (photo_b == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_b.getInputStream();
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

				byte[] photo2 = null;
				Part photo_c = req.getPart("photo2");
				try {
					if (photo_c == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_c.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = fis.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}
						baos.close();
						fis.close();
						photo2 = baos.toByteArray();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				byte[] photo3 = null;
				Part photo_d = req.getPart("photo3");
				try {
					if (photo_d == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_d.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = fis.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}
						baos.close();
						fis.close();
						photo3 = baos.toByteArray();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				Date adddate = Date.valueOf(req.getParameter("adddate"));

				System.out.println("匯入VO ");
				MemberVO memberVO = new MemberVO();
				memberVO.setMemberid(memberid);
				memberVO.setName(name);
				memberVO.setAccount(account);
				memberVO.setPassword(password);
				memberVO.setGender(gender);
				memberVO.setPhone(phone);
				memberVO.setBirthday(birthday);
				memberVO.setEmail(email);
				memberVO.setPhoto(photo);
				memberVO.setAddress(address);
				memberVO.setAuthority(authority);
				memberVO.setQualifications(qualifications);
				memberVO.setExpertise(expertise);
				memberVO.setIntroduction(introduction);
				memberVO.setPhoto1(photo1);
				memberVO.setPhoto2(photo2);
				memberVO.setPhoto3(photo3);
				memberVO.setAdddate(adddate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_member_input2.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/

				System.out.println("開始修改資料 ");
				MemberService mService = new MemberService();
				memberVO = mService.updateMember(memberid, name, account, password, gender, phone, birthday, email,
						photo, address, authority, qualifications, expertise, introduction, photo1, photo2, photo3, adddate);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("userVO", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
				HttpSession session = req.getSession();
				session.setAttribute("userVO", memberVO);
				String url = "/front-end/member/listCoachMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/update_member_input2.jsp");
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

				String name = req.getParameter("name").trim();
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("名字請勿空白");
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
					errorMsgs.add("性別請勿空白");
				}

				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}

				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入生日日期");
				}

				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("email請勿空白");
				}

				byte[] photo = null;
				Part photo_a = req.getPart("photo");
				try {
					if (photo_a == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_a.getInputStream();
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

				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

//				String authority = req.getParameter("authority").trim();
//				if (authority == null || authority.trim().length() == 0) {
//					errorMsgs.add("權限請勿空白，請輸入Y或N");
//				}

				String qualifications = req.getParameter("qualifications").trim();

				String expertise = req.getParameter("expertise").trim();

				String introduction = req.getParameter("introduction").trim();

				byte[] photo1 = null;
				Part photo_b = req.getPart("photo1");
				try {
					if (photo_b == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_b.getInputStream();
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

				byte[] photo2 = null;
				Part photo_c = req.getPart("photo2");
				try {
					if (photo_c == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_c.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = fis.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}
						baos.close();
						fis.close();
						photo2 = baos.toByteArray();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				byte[] photo3 = null;
				Part photo_d = req.getPart("photo3");
				try {
					if (photo_d == null) {
						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photo_d.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = fis.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}
						baos.close();
						fis.close();
						photo3 = baos.toByteArray();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				Date adddate = new java.sql.Date(System.currentTimeMillis());

				System.out.println("匯入VO ");
				MemberVO memberVO = new MemberVO();

				memberVO.setName(name);
				memberVO.setAccount(account);
				memberVO.setPassword(password);
				memberVO.setGender(gender);
				memberVO.setPhone(phone);
				memberVO.setBirthday(birthday);
				memberVO.setEmail(email);
				memberVO.setPhoto(photo);
				memberVO.setAddress(address);
//				memberVO.setAuthority(authority);
				memberVO.setQualifications(qualifications);
				memberVO.setExpertise(expertise);
				memberVO.setIntroduction(introduction);
				memberVO.setPhoto1(photo1);
				memberVO.setPhoto2(photo2);
				memberVO.setPhoto3(photo3);
				memberVO.setAdddate(adddate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemberService mService = new MemberService();
				memberVO = mService.addMember(name, account, password, gender, phone, birthday, email, photo, address,
						"N", qualifications, expertise, introduction, photo1, photo2, photo3, adddate);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllClassDetail.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/addMember.jsp");
				failureView.forward(req, res);
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
				MemberService mService = new MemberService();
				mService.deleteMember(memberid);
				System.out.println("123");
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
