package com.trainingcls.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.model.ActionService;
import com.action.model.ActionVO;
import com.member.model.MemberVO;
import com.trainingcls.model.TrainingClsService;
import com.trainingcls.model.TrainingClsVO;
import com.trainingclsdetail.model.TrainingClsDetailService;
import com.trainingclsdetail.model.TrainingClsDetailVO;
import com.traininghist.model.TrainingHistService;
import com.traininghist.model.TrainingHistVO;

@WebServlet("/trainingcls/TrainingClsServlet")
public class TrainingClsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("trainingclsid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入課程名稱");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {

					RequestDispatcher failureView = req.getRequestDispatcher("/trainingcls/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String trainingclsid = null;
				try {

					trainingclsid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("課程不存在");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/trainingcls/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				TrainingClsService tcSvc = new TrainingClsService();
				TrainingClsVO tcVO = tcSvc.getOneTrainingCls(trainingclsid);///////// ????
				if (tcVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/trainingcls/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("tcVO", tcVO); // 資料庫取出的empVO物件,存入req
				String url = "/trainingcls/listOneTrainingCls.jsp";
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
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String trainingclsid = new String(req.getParameter("trainingclsid"));

				/*************************** 2.開始查詢資料 ****************************************/
				TrainingClsService tcSvc = new TrainingClsService();
				TrainingClsVO tcVO = tcSvc.getOneTrainingCls(trainingclsid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("tcVO", tcVO);
				String url = "/trainingcls/update_trainingcls_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_action_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/trainingcls/listAllTrainingCls.jsp");
				failureView.forward(req, res);
			}
		}

//----------使用者選取自己想要的訓練動作----------		 
		if ("getOne_For_myaction".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("1");
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String str = req.getParameter("actionid");

				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訓練動作名稱");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trainingcls/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String trainingclsid = req.getParameter("trainingclsid");

				if (trainingclsid == null || (trainingclsid.trim()).length() == 0) {
					errorMsgs.add("請輸入訓練動作名稱");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trainingcls/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trainingcls/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 ****************************************/
				TrainingClsService tcSvc = new TrainingClsService();
				TrainingClsVO tcVO = tcSvc.getOneTrainingCls(trainingclsid);
				TrainingClsDetailService detailSvc = new TrainingClsDetailService();
				List<TrainingClsDetailVO> list = detailSvc.select(trainingclsid);
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/traininghist/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				System.out.println("3");
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//					req.setAttribute("actionVO", actionVO);  
//					System.out.println(actionVO);
				req.setAttribute("tcVO", tcVO);
				System.out.println(tcVO);
				req.setAttribute("list", list);
				String url = "/front-end/trainingcls/MyAction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("4");
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trainingcls/listAllAction.jsp");
				failureView.forward(req, res);
			}
		}
		// ------修改資料--------
		if ("update".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String trainingclsid = req.getParameter("trainingclsid").trim();
				if (trainingclsid == null || trainingclsid.trim().length() == 0) {
					errorMsgs.add("訓練課程編號請勿空白");
				}

				String memberid = ((MemberVO)session.getAttribute("userId")).getMemberid();
				if (memberid == null || memberid.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}

				String trainingclsnm = req.getParameter("trainingclsnm").trim();
				if (trainingclsnm == null || trainingclsnm.trim().length() == 0) {
					errorMsgs.add("訓練課程名稱請勿空白");
				}

				String trainingclsdetailid = req.getParameter("trainingclsdetailid").trim();
				if (trainingclsdetailid == null || trainingclsdetailid.trim().length() == 0) {
					errorMsgs.add("訓練課程內容編號請勿空白");
				}

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
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/trainingcls/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				TrainingClsVO tcVO = new TrainingClsVO();
				tcVO.setTrainingclsid(trainingclsid);
				tcVO.setMemberid(memberid);
				tcVO.setTrainingclsnm(trainingclsnm);
				tcVO.setPosttime(posttime);
				tcVO.setUpdatetime(updatetime);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tcVO", tcVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trainingcls/update_trainingcls_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				TrainingClsService tcSvc = new TrainingClsService();
				tcVO = tcSvc.updateTrainingCls(trainingclsid, memberid, trainingclsnm, posttime, updatetime);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("tcVO", tcVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/trainingcls/listOneTrainingCls.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/trainingcls/update_trainingcls_input.jsp");
				failureView.forward(req, res);
			}
		}
// ------新增訓練課程名稱資料--------
		if ("insert".equals(action)) {
			
			List<TrainingClsDetailVO> listActions3 = (List<TrainingClsDetailVO>) session.getAttribute("listActions3");
			

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("開始insert");
			try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String memberid = req.getParameter("memberid").trim();
			System.out.println("memberid=" + memberid);
			if (memberid == null || memberid.trim().length() == 0) {
				errorMsgs.add("會員編號請勿空白");
			}

			String trainingclsnm = req.getParameter("trainingclsnm").trim();
			if (trainingclsnm == null || trainingclsnm.trim().length() == 0) {
				errorMsgs.add("訓練課程名稱請勿空白");
			}

			java.sql.Date posttime = new java.sql.Date(System.currentTimeMillis());

			java.sql.Date updatetime = new java.sql.Date(System.currentTimeMillis());

			TrainingClsVO tcVO = new TrainingClsVO();
			tcVO.setMemberid(memberid);
			tcVO.setTrainingclsnm(trainingclsnm);
			tcVO.setPosttime(posttime);
			tcVO.setUpdatetime(updatetime);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("errorMsgs", errorMsgs);
				System.out.println("error");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trainingcls/addMyCls.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			TrainingClsService tcSvc = new TrainingClsService();
			System.out.println("開始insert資料");
			tcVO = tcSvc.addTrainingCls(memberid, trainingclsnm, posttime, updatetime);
			System.out.println("呼叫tcSVC addto trainingcls");
			session.setAttribute("tcVO", tcVO);
			
			ActionService actionSvc = new ActionService();
			List<String> partList = actionSvc.getActionParts();
			
			session.setAttribute("partList", partList);
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/trainingcls/listAllAction.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/trainingcls/addTrainingCls.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String trainingclsid = req.getParameter("trainingclsid").trim();
				System.out.println("123");
				/*************************** 2.開始刪除資料 ***************************************/
				TrainingClsService tcSvc = new TrainingClsService();
				tcSvc.deleteTrainingCls(trainingclsid);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/trainingcls/listAllTrainingCls.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/trainingcls/listAllTrainingCls.jsp");
				failureView.forward(req, res);
			}
		}
//使用者新增完畢自己想要的健身動作後儲存
		if ("save".equals(action)) {
			TrainingClsDetailService tcdSvc = new TrainingClsDetailService();
			String trainingclsid = req.getParameter("trainingclsid");
			List<TrainingClsDetailVO> listActions3 = (List<TrainingClsDetailVO>) session.getAttribute("listActions3");
			System.out.println(listActions3);
			for (TrainingClsDetailVO vo : listActions3) {
				vo = tcdSvc.addTrainingClsDetail(trainingclsid, vo.getActionid());
				System.out.println(vo);
			}
			
//			List<String> actionIdList=(List<String>) session.getAttribute("tcdList");
//			TrainingClsDetailService tcdSvc = new TrainingClsDetailService();
//			List<TrainingClsDetailVO> tcdList = new ArrayList<>();
//			for (String actionId : actionIdList) {
//				TrainingClsDetailVO tcdVO = tcdSvc.addTrainingClsDetail(trainingclsid, actionId);
//				tcdList.add(tcdVO);
//			}
//			
//			session.removeAttribute("tcdList");
//			session.setAttribute("tcdList", tcdList);
			
			String url = "/front-end/trainingcls/MyAction.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

}
