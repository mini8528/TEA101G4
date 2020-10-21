package com.coachClass.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import com.coachClass.model.*;


@MultipartConfig
@WebServlet("/back-end/coachClass/coachClass.do")
public class CoachClassServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 靘select_page.jsp�����
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				/*************************** 1.��隢�� - 頛詨�撘�隤方��� **********************/
				String str = req.getParameter("coachClassID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("隢撓�coachClassID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/select_page.jsp");
					failureView.forward(req, res);
					return;// 蝔�葉�
				}

				String coachClassID = null;
				try {
					coachClassID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("coachClassID�撘�迤蝣�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/select_page.jsp");
					failureView.forward(req, res);
					return;// 蝔�葉�
				}
				
				/*************************** 2.���閰Ｚ��� *****************************************/
				CoachClassService cocService = new CoachClassService();
				CoachClassVO coachClassVO = cocService.getOneCoachClass(coachClassID);
				if (coachClassVO == null) {
					errorMsgs.add("��鞈��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/select_page.jsp");
					failureView.forward(req, res);
					return;// 蝔�葉�
				}

				/*************************** 3.�閰Ｗ���,皞��漱(Send the Success view) *************/
				req.setAttribute("coachClassVO", coachClassVO); // 鞈�澈����oachClassVO�隞�,摮req
				String url = "/back-end/coachClass/listOneCoachClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ����漱 listOneEmp.jsp
				

				successView.forward(req, res);

				/*************************** �隞���隤方��� *************************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜�����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Display_front".equals(action)) { // 靘select_page.jsp�����


			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				/*************************** 1.��隢�� - 頛詨�撘�隤方��� **********************/
				String str = req.getParameter("coachClassID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("隢撓�coachClassID");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coachClass/select_page.jsp");
					failureView.forward(req, res);
					return;// 蝔�葉�
				}

				String coachClassID = null;
				try {
					coachClassID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("coachClassID�撘�迤蝣�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coachClass/select_page.jsp");
					failureView.forward(req, res);
					return;// 蝔�葉�
				}
				
				/*************************** 2.���閰Ｚ��� *****************************************/
				CoachClassService cocService = new CoachClassService();
				CoachClassVO coachClassVO = cocService.getOneCoachClass(coachClassID);
				if (coachClassVO == null) {
					errorMsgs.add("��鞈��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coachClass/select_page.jsp");
					failureView.forward(req, res);
					return;// 蝔�葉�
				}

				/*************************** 3.�閰Ｗ���,皞��漱(Send the Success view) *************/
				HttpSession session = req.getSession();
				session.setAttribute("coachClassVO", coachClassVO);
				
//				req.setAttribute("coachClassVO", coachClassVO); // 鞈�澈����oachClassVO�隞�,摮req
				String url = "/front-end/coachClass/listOneCoachClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ����漱 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** �隞���隤方��� *************************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜�����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coachClass/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("update".equals(action)) { // 靘update_coachClass_input.jsp�����
			

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				
				/*************************** 1.��隢�� - 頛詨�撘�隤方��� **********************/
				String coachClassID = new String(req.getParameter("coachClassID").trim());
				String memberID = req.getParameter("memberID");
				String memberIDReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberID == null || memberID.trim().length() == 0) {
					errorMsgs.add("memberID: 隢蝛箇");
				} else if (!memberID.trim().matches(memberIDReg)) { // 隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("memberID: ���銝准������摮� , 銝摨血���2�10銋��");
				}
				String className = req.getParameter("className").trim();
				if (className == null || className.trim().length() == 0) {
					errorMsgs.add("className隢蝛箇");
				}
				String classContext = req.getParameter("classContext").trim();
				if (classContext == null || classContext.trim().length() == 0) {
					errorMsgs.add("classContext隢蝛箇");
				}

				java.sql.Timestamp startTime = null;
				try {
					startTime = java.sql.Timestamp.valueOf(req.getParameter("startTime").trim());
				} catch (IllegalArgumentException e) {
					startTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("隢撓�startTime!");
				}
				Integer price = new Integer(req.getParameter("price").trim());

				Integer quantity = new Integer(req.getParameter("quantity").trim());
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("address隢蝛箇");
				}
				
				byte[] photo = null;
				Part photo1 = req.getPart("photo");
				try {
					if (photo1 == null) {
						errorMsgs.add("隢�����");
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
					req.setAttribute("coachClassVO", coachClassVO); // ���撓��撘隤斤�mpVO�隞�,銋�req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/update_coachClass_input.jsp");
					failureView.forward(req, res);
					return; // 蝔�葉�
				}

				/*************************** 2.���耨�鞈�� *****************************************/
				CoachClassService cocService = new CoachClassService();
				coachClassVO = cocService.updateCoachClass(coachClassID, memberID, className, classContext, photo,
						startTime, price, quantity, address, addDate, editDate);

				/*************************** 3.靽格摰��,皞��漱(Send the Success view) *************/

				req.setAttribute("coachClassVO", coachClassVO); // 鞈�澈update�����,甇�蝣箇��oachClassVO�隞�,摮req
				String url = "/back-end/coachClass/listOneCoachClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 靽格�����,頧漱listOneCoachClass.jsp
				successView.forward(req, res);

				/*************************** �隞���隤方��� *************************************/
			} catch (Exception e) {
				errorMsgs.add("靽格鞈�仃���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/update_coachClass_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 靘addCoachClass.jsp�����
			

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.��隢�� - 頛詨�撘�隤方��� *************************/

				String memberID = req.getParameter("memberID");
				
				String memberIDReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberID == null || memberID.trim().length() == 0) {
					errorMsgs.add("memberID: 隢蝛箇");
				} else if (!memberID.trim().matches(memberIDReg)) { // 隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("memberID: ���銝准������摮� , 銝摨血���2�10銋��");
				}

				String className = req.getParameter("className").trim();
				if (className == null || className.trim().length() == 0) {
					errorMsgs.add("className隢蝛箇");
				}
				
				Integer price = new Integer(req.getParameter("price").trim());

				
				java.sql.Timestamp startTime = null;
				try {
					startTime = java.sql.Timestamp.valueOf(req.getParameter("startTime").trim());
				} catch (IllegalArgumentException e) {
					startTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("隢撓�startTime!");
				}
				
				Integer quantity = new Integer(req.getParameter("quantity").trim());
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("address隢蝛箇");
				}
				
				Timestamp addDate = new java.sql.Timestamp(System.currentTimeMillis());
				
				Timestamp editDate = new java.sql.Timestamp(System.currentTimeMillis());
				
				String classContext = req.getParameter("classContext").trim();
				if (classContext == null || classContext.trim().length() == 0) {
					errorMsgs.add("classContext隢蝛箇");
				}

				byte[] photo = null;
				Part photo1 = req.getPart("photo");
				try {
					if (photo1 == null) {
						errorMsgs.add("隢�����");
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
					req.setAttribute("coachClassVO", coachClassVO); // ���撓��撘隤斤�lassDetailVO�隞�,銋�req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coachClass/addCoachClass.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.���憓��� ***************************************/
				CoachClassService cocService = new CoachClassService();
				coachClassVO = cocService.addCoachClass(memberID, className, classContext, photo, startTime, price,
						quantity, address, addDate, editDate);

				/*************************** 3.�憓���,皞��漱(Send the Success view) ***********/
				String url = "/front-end/coachClass/listAllCoachClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllClassOrder.jsp
				successView.forward(req, res);

				/*************************** �隞���隤方��� **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coachClass/addCoachClass.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 靘listAllCoachClass.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.��隢�� ***************************************/
				String coachClassID = new String(req.getParameter("coachClassID"));

				/*************************** 2.����鞈�� ***************************************/
				CoachClassService cocService = new CoachClassService();
				cocService.deleteCoachClass(coachClassID);

				/*************************** 3.��摰��,皞��漱(Send the Success view) ***********/
				String url = "/back-end/coachClass/listAllCoachClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �������,頧漱���������雯���
				successView.forward(req, res);

				/*************************** �隞���隤方��� **********************************/
			} catch (Exception e) {
				errorMsgs.add("��鞈�仃���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/listAllCoachClass.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 靘listAllCoachClass.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.��隢�� ****************************************/
				
				String coachClassID = new String(req.getParameter("coachClassID"));
//				1.��隢��
				try {
					String str = req.getParameter("coachClassID");
//				�隤方���
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("隢撓�閮蝺刻��");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/select_page.jsp");
						failureView.forward(req, res);
						return;
					}
//				��摮葡�撘�����靘��＊蝷粹隤方�嚚�歲��
					coachClassID = null;
					try {
						coachClassID = new String(str);
					} catch (Exception e) {
						errorMsgs.add("閮蝺刻�撘�迤蝣�");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/select_page.jsp");
						failureView.forward(req, res);
						return;
					}
					/*************************** 2.���閰Ｚ��� ****************************************/
					CoachClassService cocService = new CoachClassService();
					CoachClassVO coachClassVO = cocService.getOneCoachClass(coachClassID);

					/*************************** 3.�閰Ｗ���,皞��漱(Send the Success view) ************/
					req.setAttribute("coachClassVO", coachClassVO); // 鞈�澈����oachClassVO�隞�,摮req
					String url = "/back-end/coachClass/update_coachClass_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// ����漱 update_coachClass_input.jsp
					successView.forward(req, res);

					/*************************** �隞���隤方��� **********************************/
				} catch (Exception e) {
					errorMsgs.add("�瘜���耨������:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/listAllCoachClass.jsp");
					failureView.forward(req, res);
				}

			} catch (Exception e) {
				errorMsgs.add("servlet getOne_For_Update隢��憭望��:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coachClass/listAllCoachClass.jsp");
				failureView.forward(req, res);
			}
		}
	}

}