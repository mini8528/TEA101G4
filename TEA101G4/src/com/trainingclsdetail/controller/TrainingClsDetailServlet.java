package com.trainingclsdetail.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.model.ActionService;
import com.action.model.ActionVO;
import com.trainingcls.model.TrainingClsService;
import com.trainingcls.model.TrainingClsVO;
import com.trainingclsdetail.model.TrainingClsDetailJDBCDAO;
import com.trainingclsdetail.model.TrainingClsDetailService;
import com.trainingclsdetail.model.TrainingClsDetailVO;

import sun.awt.RepaintArea;

@WebServlet("/trainingclsdetail/TrainingClsDetailServlet")
public class TrainingClsDetailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
//		list<TrainingClsDetailVO> listaction = (TrainingClsDetailVO<VO>) session.getAttribute("tcdVO");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("trainingclsdetailid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入trainingclsdetailid");
				}
//				String trainingclsid = req.getParameter("trainingclsid");
//				if (trainingclsid == null || (trainingclsid.trim()).length() == 0) {
//					errorMsgs.add("請輸入trainingclsid");
//				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/trainingclsdetail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String trainingclsdetailid = null;
				try {
					trainingclsdetailid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/trainingclsdetail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				TrainingClsDetailService tcdSvc = new TrainingClsDetailService();
				TrainingClsDetailVO tcdVO = tcdSvc.getOneTrainingClsDetail(trainingclsdetailid);
				if (tcdVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/trainingclsdetail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("tcdVO", tcdVO);
				String url = "/trainingclsdetail/listOneTrainingClsDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
//--用select by trainingclsid	
		if ("getOne_For_showactionid".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("trainingclsid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訓練課程編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trainingclsdetail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				System.out.println(str + "課程編號");
				String trainingclsid = null;
				try {
					trainingclsid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trainingclsdetail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				System.out.println("輸入錯誤,跳轉回首頁");
				/*************************** 2.開始查詢資料 *****************************************/
				TrainingClsDetailService tcdSvc = new TrainingClsDetailService();
				TrainingClsDetailVO tcdVO = tcdSvc.getOneTrainingClsDetail(trainingclsid);
				if (tcdVO == null) {
					errorMsgs.add("查無資料");
				}
				System.out.println("查無資料");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/trainingclsdetail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("tcdVO", tcdVO);
				String url = "/front-end/trainingclsdetail/listOneTrainingClsDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/select_page.jsp");
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
				String trainingclsdetailid = req.getParameter("trainingclsdetailid");

				/*************************** 2.開始查詢資料 ****************************************/
				TrainingClsDetailService tcdSvc = new TrainingClsDetailService();
				TrainingClsDetailVO tcdVO = tcdSvc.getOneTrainingClsDetail(trainingclsdetailid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("tcdVO", tcdVO); // 資料庫取出的empVO物件,存入req
				String url = "/trainingclsdetail/update_TrainingClsDetail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/trainingclsdetail/listAllTrainingClsDetail.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String trainingclsdetailid = req.getParameter("trainingclsdetailid");
				if (trainingclsdetailid == null || trainingclsdetailid.trim().length() == 0) {
					errorMsgs.add("訓練課表內容編號請勿空白");
				}

				String trainingclsid = req.getParameter("trainingclsid").trim();
				if (trainingclsid == null || trainingclsid.trim().length() == 0) {
					errorMsgs.add("訓練課表編號請勿空白");
				}

				String actionid = req.getParameter("actionid").trim();
				if (actionid == null || actionid.trim().length() == 0) {
					errorMsgs.add("訓練動作編號請勿空白");
				}

				Integer seqid = new Integer(req.getParameter("seqid").trim());
				if (actionid == null || actionid.trim().length() == 0) {
					errorMsgs.add("訓練動作順序請勿空白");
				}

//			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/trainingclsdetail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				TrainingClsDetailVO tcdVO = new TrainingClsDetailVO();
				tcdVO.setTrainingclsdetailid(trainingclsdetailid);
				tcdVO.setTrainingclsid(trainingclsid);
				tcdVO.setActionid(actionid);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tcdVO", tcdVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trainingclsdetail/update_trainingclsdetail_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				TrainingClsDetailService tcdSvc = new TrainingClsDetailService();
				tcdVO = tcdSvc.updateTrainingClsDetail(trainingclsdetailid, trainingclsid, actionid);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("tcdVO", tcdVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/trainingclsdetail/listOneTrainingClsDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/trainingclsdetail/update_trainingclsdetail_input.jsp");
				failureView.forward(req, res);
			}
		}

// ------listAllAction新增訓練動作資料--------
		if ("insertaction".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String trainingclsid = req.getParameter("trainingclsid").trim();
			if (trainingclsid == null || trainingclsid.trim().length() == 0) {
				errorMsgs.add("訓練課表編號請勿空白");
			}

			String[] actionIdList = req.getParameterValues("actionId");

			ActionService actionService = new ActionService();
			Map<String, ActionVO> actionMap = actionService.getActionMap();
			
			List<TrainingClsDetailVO> listActions3 = (List<TrainingClsDetailVO>) session.getAttribute("listActions3");
			if (null == listActions3) {
				listActions3 = new ArrayList<>();
			} else {
				Map<String, TrainingClsDetailVO> listActionMap3 = listActions3.parallelStream()
						.collect(Collectors.toMap(item -> item.getActionid(), Function.identity()));
				TrainingClsDetailVO detailVO = null;
//取得使用者所選的健身動作名稱(get actionId)比對actionIdList 一樣的便取出
				for (String actionId : actionIdList) {
					if (null == listActionMap3.get(actionId)) {
						detailVO = new TrainingClsDetailVO();
						ActionVO actionVO = actionMap.get(actionId);
						detailVO.setActionid(actionVO.getActionid());
						detailVO.setActionidnm(actionVO.getActionnm());
						listActions3.add(detailVO);
					}
				}
			}

			/*************************** 2.開始新增資料 ***************************************/
			TrainingClsService tcSvc = new TrainingClsService();
			TrainingClsVO tcVO = tcSvc.getOneTrainingCls(trainingclsid);
			req.setAttribute("tcVO", tcVO);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/trainingcls/listAllAction.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			/*************************** 其他可能的錯誤處理 **********************************/
		}
// ------新增資料--------
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String trainingclsid = req.getParameter("trainingclsid").trim();
			if (trainingclsid == null || trainingclsid.trim().length() == 0) {
				errorMsgs.add("訓練課表編號請勿空白");
			}

			String actionid = req.getParameter("actionid").trim();
			if (actionid == null || actionid.trim().length() == 0) {
				errorMsgs.add("訓練課表編號請勿空白");
			}

			String str = req.getParameter("seqid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入訓練動作順續");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/trainingclsdetail/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			TrainingClsDetailVO tcdVO = new TrainingClsDetailVO();
			tcdVO.setTrainingclsid(trainingclsid);
			tcdVO.setActionid(actionid);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("tcdVO", tcdVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/trainingclsdetail/addTrainingClsDetail.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			TrainingClsDetailService tcdSvc = new TrainingClsDetailService();
			tcdVO = tcdSvc.addTrainingClsDetail(trainingclsid, actionid);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/trainingclsdetail/listAllTrainingClsDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/trainingclsdetail/addTrainingClsDetail.jsp");
			failureView.forward(req, res);
		}
		}
		if ("delete".equals(action)) { // 來自listAllAction.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String trainingclsdetailid = req.getParameter("trainingclsdetailid").trim();

				/*************************** 2.開始刪除資料 ***************************************/
				TrainingClsDetailService tcdSvc = new TrainingClsDetailService();
				TrainingClsDetailVO tcdVO = tcdSvc.getOneTrainingClsDetail(trainingclsdetailid);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/trainingclsdetail/listAllTrainingClsDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/action/listAllAction.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
