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
import javax.servlet.http.Part;


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
				/*************************** 1.��隢�� - 頛詨�撘�隤方��� **********************/
				String str = req.getParameter("memberid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("隢撓���蝺刻��");
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
					errorMsgs.add("��蝺函Ⅳ��銝迤蝣�");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 蝔�葉�
				}

				/*************************** 2.���閰Ｚ��� *****************************************/
				MemberService mService = new MemberService();
				MemberVO memberVO = mService.getOneMember(memberid);
				if (memberVO == null) {
					errorMsgs.add("��鞈��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 蝔�葉�
				}
				/*************************** 3.�閰Ｗ���,皞��漱(Send the Success view) *************/
				req.setAttribute("memberVO", memberVO); // 鞈�澈����mpVO�隞�,摮req
				String url = "/front-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ����漱 listOneEmp.jsp
				successView.forward(req, res);
				/*************************** �隞���隤方��� *************************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜�����:" + e.getMessage());
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
				/*************************** 1.��隢�� - 頛詨�撘�隤方��� **********************/
				String str = req.getParameter("memberid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("隢撓���蝺刻��");
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
					errorMsgs.add("��蝺函Ⅳ��銝迤蝣�");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 蝔�葉�
				}

				/*************************** 2.���閰Ｚ��� *****************************************/
				MemberService mService = new MemberService();
				MemberVO memberVO = mService.getOneMember(memberid);
				if (memberVO == null) {
					errorMsgs.add("��鞈��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 蝔�葉�
				}
				/*************************** 3.�閰Ｗ���,皞��漱(Send the Success view) *************/
				req.setAttribute("memberVO", memberVO); // 鞈�澈����mpVO�隞�,摮req
				String url = "/front-end/member/listCoachMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ����漱 listOneEmp.jsp
				successView.forward(req, res);
				/*************************** �隞���隤方��� *************************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜�����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 靘listAllEmp.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.��隢�� ****************************************/
				String memberid = new String(req.getParameter("memberid"));

				/*************************** 2.���閰Ｚ��� ****************************************/
				MemberService mService = new MemberService();
				MemberVO memberVO = mService.getOneMember(memberid);

				/*************************** 3.�閰Ｗ���,皞��漱(Send the Success view) ************/
				req.setAttribute("memberVO", memberVO); // 鞈�澈����mpVO�隞�,摮req
				String url = "/front-end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ����漱 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** �隞���隤方��� **********************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜���耨������:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update1".equals(action)) { // 靘listAllEmp.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.��隢�� ****************************************/
				String memberid = req.getParameter("memberid");

				/*************************** 2.���閰Ｚ��� ****************************************/
				MemberService mService = new MemberService();
				MemberVO memberVO = mService.getOneMember(memberid);

				/*************************** 3.�閰Ｗ���,皞��漱(Send the Success view) ************/
				req.setAttribute("memberVO", memberVO); // 鞈�澈����mpVO�隞�,摮req
				String url = "/front-end/member/update_member_input1.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ����漱 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** �隞���隤方��� **********************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜���耨������:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update2".equals(action)) { // 靘back-end/listAllEmp.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.��隢�� ****************************************/
				String memberid = req.getParameter("memberid");

				/*************************** 2.���閰Ｚ��� ****************************************/
				MemberService mService = new MemberService();
				MemberVO memberVO = mService.getOneMember(memberid);

				/*************************** 3.�閰Ｗ���,皞��漱(Send the Success view) ************/
				req.setAttribute("memberVO", memberVO); // 鞈�澈����mpVO�隞�,摮req
				String url = "/back-end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ����漱 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** �隞���隤方��� **********************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜���耨������:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 靘update_blog_input.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.��隢�� - 頛詨�撘�隤方��� **********************/
				String memberid = new String(req.getParameter("memberid").trim());

				String name = req.getParameter("name").trim();
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("����蝛箇");
				}

				String account = req.getParameter("account");
				String accountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("銝����撣唾��: 隢蝛箇");
				} else if (!account.trim().matches(accountReg)) { // 隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("銝����撣唾��: ���銝准������摮� , 銝摨血���2�10銋��");
				}

				String password = req.getParameter("password");
				String passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("銝����撖Ⅳ: 隢蝛箇");
				} else if (!password.trim().matches(passwordReg)) { // 隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("銝����撖Ⅳ: ���銝准������摮� , 銝摨血���2�10銋��");
				}

				String gender = req.getParameter("gender").trim();
				if (gender == null || gender.trim().length() == 0) {
					errorMsgs.add("�批隢蝛箇");
				}

				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("�閰梯�蝛箇");
				}

				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("隢撓�������");
				}

				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("email隢蝛箇");
				}

				byte[] photo = null;
				Part photo_a = req.getPart("photo");
				try {
					if (photo_a == null) {
						errorMsgs.add("隢�����");
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
					errorMsgs.add("���隢蝛箇");
				}

//				String authority = req.getParameter("authority").trim();
//				if (authority == null || authority.trim().length() == 0) {
//					errorMsgs.add("甈��蝛箇嚗�撓�Y��");
//				}

				String qualifications = req.getParameter("qualifications").trim();

				String expertise = req.getParameter("expertise").trim();

				String introduction = req.getParameter("introduction").trim();

				byte[] photo1 = null;
				Part photo_b = req.getPart("photo1");
				try {
					if (photo_b == null) {
						errorMsgs.add("隢�����");
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
						errorMsgs.add("隢�����");
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
						errorMsgs.add("隢�����");
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

				System.out.println("��VO ");
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
					req.setAttribute("memberVO", memberVO); // ���撓��撘隤斤�mpVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; // 蝔�葉�
				}

				/*************************** 2.���耨�鞈�� *****************************************/

				System.out.println("���耨�鞈�� ");
				MemberService mService = new MemberService();
				memberVO = mService.updateMember(memberid, name, account, password, gender, phone, birthday, email,
						photo, address, "N", qualifications, expertise, introduction, photo1, photo2, photo3, adddate);

				/*************************** 3.靽格摰��,皞��漱(Send the Success view) *************/
				req.setAttribute("memberVO", memberVO); // 鞈�澈update�����,甇�蝣箇��mpVO�隞�,摮req
				String url = "/front-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 靽格�����,頧漱listOneEmp.jsp
				successView.forward(req, res);

				/*************************** �隞���隤方��� *************************************/
			} catch (Exception e) {
				errorMsgs.add("靽格鞈�仃���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/update_member_input.jsp");
				failureView.forward(req, res);
				System.out.println("update �隞隤�");
				e.printStackTrace();
			}
		}
		if ("update1".equals(action)) { // 靘update_blog_input.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.��隢�� - 頛詨�撘�隤方��� **********************/
				String memberid = new String(req.getParameter("memberid").trim());

				String name = req.getParameter("name").trim();
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("����蝛箇");
				}

				String account = req.getParameter("account");
				String accountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("銝����撣唾��: 隢蝛箇");
				} else if (!account.trim().matches(accountReg)) { // 隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("銝����撣唾��: ���銝准������摮� , 銝摨血���2�10銋��");
				}

				String password = req.getParameter("password");
				String passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("銝����撖Ⅳ: 隢蝛箇");
				} else if (!password.trim().matches(passwordReg)) { // 隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("銝����撖Ⅳ: ���銝准������摮� , 銝摨血���2�10銋��");
				}

				String gender = req.getParameter("gender").trim();
				if (gender == null || gender.trim().length() == 0) {
					errorMsgs.add("�批隢蝛箇");
				}

				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("�閰梯�蝛箇");
				}

				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("隢撓�������");
				}

				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("email隢蝛箇");
				}

				byte[] photo = null;
				Part photo_a = req.getPart("photo");
				try {
					if (photo_a == null) {
						errorMsgs.add("隢�����");
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
					errorMsgs.add("���隢蝛箇");
				}

//				String authority = req.getParameter("authority").trim();
//				if (authority == null || authority.trim().length() == 0) {
//					errorMsgs.add("甈��蝛箇嚗�撓�Y��");
//				}

				String qualifications = req.getParameter("qualifications").trim();

				String expertise = req.getParameter("expertise").trim();

				String introduction = req.getParameter("introduction").trim();

				byte[] photo1 = null;
				Part photo_b = req.getPart("photo1");
				try {
					if (photo_b == null) {
						errorMsgs.add("隢�����");
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
						errorMsgs.add("隢�����");
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
						errorMsgs.add("隢�����");
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

				System.out.println("��VO ");
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
					req.setAttribute("memberVO", memberVO); // ���撓��撘隤斤�mpVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_member_input1.jsp");
					failureView.forward(req, res);
					return; // 蝔�葉�
				}

				/*************************** 2.���耨�鞈�� *****************************************/

				System.out.println("���耨�鞈�� ");
				MemberService mService = new MemberService();
				memberVO = mService.updateMember(memberid, name, account, password, gender, phone, birthday, email,
						photo, address, "N", qualifications, expertise, introduction, photo1, photo2, photo3, adddate);

				/*************************** 3.靽格摰��,皞��漱(Send the Success view) *************/
				req.setAttribute("memberVO", memberVO); // 鞈�澈update�����,甇�蝣箇��mpVO�隞�,摮req
				String url = "/front-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 靽格�����,頧漱listOneEmp.jsp
				successView.forward(req, res);

				/*************************** �隞���隤方��� *************************************/
			} catch (Exception e) {
				errorMsgs.add("靽格鞈�仃���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/update_member_input1.jsp");
				failureView.forward(req, res);
				System.out.println("update �隞隤�");
				e.printStackTrace();
			}
		}
		if ("update2".equals(action)) { // 靘update_blog_input.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.��隢�� - 頛詨�撘�隤方��� **********************/
				String memberid = new String(req.getParameter("memberid").trim());

				String name = req.getParameter("name").trim();
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("����蝛箇");
				}

				String account = req.getParameter("account");
				String accountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("銝����撣唾��: 隢蝛箇");
				} else if (!account.trim().matches(accountReg)) { // 隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("銝����撣唾��: ���銝准������摮� , 銝摨血���2�10銋��");
				}

				String password = req.getParameter("password");
				String passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("銝����撖Ⅳ: 隢蝛箇");
				} else if (!password.trim().matches(passwordReg)) { // 隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("銝����撖Ⅳ: ���銝准������摮� , 銝摨血���2�10銋��");
				}

				String gender = req.getParameter("gender").trim();
				if (gender == null || gender.trim().length() == 0) {
					errorMsgs.add("�批隢蝛箇");
				}

				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("�閰梯�蝛箇");
				}

				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("隢撓�������");
				}

				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("email隢蝛箇");
				}

				byte[] photo = null;
				Part photo_a = req.getPart("photo");
				try {
					if (photo_a == null) {
						errorMsgs.add("隢�����");
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
					errorMsgs.add("���隢蝛箇");
				}

				String authority = req.getParameter("authority").trim();
				if (authority == null || authority.trim().length() == 0) {
					errorMsgs.add("甈��蝛箇嚗�撓�Y��");
				}

				String qualifications = req.getParameter("qualifications").trim();

				String expertise = req.getParameter("expertise").trim();

				String introduction = req.getParameter("introduction").trim();

				byte[] photo1 = null;
				Part photo_b = req.getPart("photo1");
				try {
					if (photo_b == null) {
						errorMsgs.add("隢�����");
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
						errorMsgs.add("隢�����");
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
						errorMsgs.add("隢�����");
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

				System.out.println("��VO ");
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
					req.setAttribute("memberVO", memberVO); // ���撓��撘隤斤�mpVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; // 蝔�葉�
				}

				/*************************** 2.���耨�鞈�� *****************************************/

				System.out.println("���耨�鞈�� ");
				MemberService mService = new MemberService();
				memberVO = mService.updateMember(memberid, name, account, password, gender, phone, birthday, email,
						photo, address, authority, qualifications, expertise, introduction, photo1, photo2, photo3,
						adddate);

				/*************************** 3.靽格摰��,皞��漱(Send the Success view) *************/
				req.setAttribute("memberVO", memberVO); // 鞈�澈update�����,甇�蝣箇��mpVO�隞�,摮req
				String url = "/back-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 靽格�����,頧漱listOneEmp.jsp
				successView.forward(req, res);

				/*************************** �隞���隤方��� *************************************/
			} catch (Exception e) {
				errorMsgs.add("靽格鞈�仃���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				failureView.forward(req, res);
				System.out.println("update �隞隤�");
				e.printStackTrace();
			}
		}

		if ("insert".equals(action)) { // 靘addEmp.jsp�����
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.��隢�� - 頛詨�撘�隤方��� *************************/

				String name = req.getParameter("name").trim();
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("����蝛箇");
				}

				String account = req.getParameter("account");
				String accountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("銝����撣唾��: 隢蝛箇");
				} else if (!account.trim().matches(accountReg)) { // 隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("銝����撣唾��: ���銝准������摮� , 銝摨血���2�10銋��");
				}

				String password = req.getParameter("password");
				String passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("銝����撖Ⅳ: 隢蝛箇");
				} else if (!password.trim().matches(passwordReg)) { // 隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("銝����撖Ⅳ: ���銝准������摮� , 銝摨血���2�10銋��");
				}

				String gender = req.getParameter("gender").trim();
				if (gender == null || gender.trim().length() == 0) {
					errorMsgs.add("�批隢蝛箇");
				}

				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("�閰梯�蝛箇");
				}

				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("隢撓�������");
				}

				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("email隢蝛箇");
				}

				byte[] photo = null;
				Part photo_a = req.getPart("photo");
				try {
					if (photo_a == null) {
						errorMsgs.add("隢�����");
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
					errorMsgs.add("���隢蝛箇");
				}

//				String authority = req.getParameter("authority").trim();
//				if (authority == null || authority.trim().length() == 0) {
//					errorMsgs.add("甈��蝛箇嚗�撓�Y��");
//				}

				String qualifications = req.getParameter("qualifications").trim();

				String expertise = req.getParameter("expertise").trim();

				String introduction = req.getParameter("introduction").trim();

				byte[] photo1 = null;
				Part photo_b = req.getPart("photo1");
				try {
					if (photo_b == null) {
						errorMsgs.add("隢�����");
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
						errorMsgs.add("隢�����");
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
						errorMsgs.add("隢�����");
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

				System.out.println("��VO ");
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
					req.setAttribute("memberVO", memberVO); // ���撓��撘隤斤�mpVO�隞�,銋�req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.���憓��� ***************************************/
				MemberService mService = new MemberService();
				memberVO = mService.addMember(name, account, password, gender, phone, birthday, email, photo, address,
						"N", qualifications, expertise, introduction, photo1, photo2, photo3, adddate);

				/*************************** 3.�憓���,皞��漱(Send the Success view) ***********/
				String url = "/front-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllClassDetail.jsp
				successView.forward(req, res);

				/*************************** �隞���隤方��� **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/addMember.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 靘listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.��隢�� ***************************************/
				String memberid = new String(req.getParameter("memberid"));

				/*************************** 2.����鞈�� ***************************************/
				MemberService mService = new MemberService();
				mService.deleteMember(memberid);
				System.out.println("123");
				/*************************** 3.��摰��,皞��漱(Send the Success view) ***********/
				String url = "/front-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �������,頧漱���������雯���
				successView.forward(req, res);

				/*************************** �隞���隤方��� **********************************/
			} catch (Exception e) {
				errorMsgs.add("��鞈�仃���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
