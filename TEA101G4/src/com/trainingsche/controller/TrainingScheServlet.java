package com.trainingsche.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import javax.swing.ActionMap;

import org.apache.naming.java.javaURLContextFactory;

import com.action.model.ActionService;
import com.action.model.ActionVO;
import com.member.model.MemberVO;
import com.trainingcls.model.TrainingClsService;
import com.trainingcls.model.TrainingClsVO;
import com.trainingclsdetail.model.TrainingClsDetailVO;
import com.traininghist.model.TrainingHistService;
import com.traininghist.model.TrainingHistVO;
import com.trainingsche.model.TrainingScheJDBCDAO;
import com.trainingsche.model.TrainingScheService;
import com.trainingsche.model.TrainingScheVO;

@WebServlet("/trainingsche/TrainingScheServlet")
public class TrainingScheServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		TrainingHistService HistService = new TrainingHistService();
		String action = req.getParameter("action");

//首頁/查詢--------------------取單筆資料
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("trainingscheid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訓練行程編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/trainingsche/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String trainingscheid = null;
				try {
					trainingscheid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("訓練歷程編號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/trainingsche/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				TrainingScheService tsSvc = new TrainingScheService();
				TrainingScheVO tsVO = tsSvc.getOneTrainingSche(trainingscheid);
				if (tsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/trainingsche/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				System.out.println("2");
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("tsVO", tsVO);
				String url = "/trainingsche/listOneTrainingSche.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				System.out.println("3");
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/trainingsche/select_page.jsp");
				failureView.forward(req, res);
				System.out.println("4");
			}
		}
//-----------新增一筆資料		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String trainingscheid = new String(req.getParameter("trainingscheid"));
				/*************************** 2.開始查詢資料 ****************************************/
				TrainingScheService tsSvc = new TrainingScheService();
				TrainingScheVO tsVO = tsSvc.getOneTrainingSche(trainingscheid);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("tsVO", tsVO);
				String url = "/trainingsche/update_trainingsche_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_action_input.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/trainingsche/listAllTrainingSche.jsp");
				failureView.forward(req, res);
			}
		}

// ------修改traininghist資料--------
		if ("update".equals(action)) {

//			List<TrainingHistVO> thList = (List<TrainingHistVO>) session.getAttribute("thList");
			TrainingHistService thSvc = new TrainingHistService();
			String trainingscheid = req.getParameter("trainingscheid");
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("開始UPDATE");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//取用多筆,使用getParameterValues
				String[] traininghistid = req.getParameterValues("traininghistid");
				String[] trainingset = req.getParameterValues("trainingset");
				String[] trainingrep = req.getParameterValues("trainingrep");
				String[] trainingwt = req.getParameterValues("trainingwt");
//用traininghistid來判斷迴圈要跑幾次(選幾個健身動作跑幾次)				
				for (int i = 0; i < traininghistid.length; i++){				
					thSvc.updatehistTrainingHist(Integer.parseInt(trainingset[i]), Integer.parseInt(trainingrep[i]), Integer.parseInt(trainingwt[i]), traininghistid[i]);
				}

				java.sql.Timestamp endtime = new java.sql.Timestamp(System.currentTimeMillis());
				System.out.println("印出運動結束時間");
				System.out.println(endtime);

				TrainingScheVO tsVO = new TrainingScheVO();
				System.out.println("new schevo");
				tsVO.setEndtime(endtime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tsVO", tsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trainingindex.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				TrainingScheService tsSvc = new TrainingScheService();
				tsVO = tsSvc.updateenddtimeTrainingSche(endtime, trainingscheid);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("tsVO", tsVO);
				String url = "/front-end/traininghist/Finishedworkout.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trainingindex.jsp");
				failureView.forward(req, res);
			}
		}

//------新增資料--------		
		if ("insert".equals(action)) {
			List<TrainingClsDetailVO> listActions3 = (List<TrainingClsDetailVO>) session.getAttribute("listActions3");
			List<TrainingHistVO> thList = new ArrayList<TrainingHistVO>();

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				MemberVO userVO = (MemberVO) session.getAttribute("userVO");
				String trainingclsid = (String) req.getParameter("trainingclsid");
				TrainingScheService trainingScheService = new TrainingScheService();
				TrainingScheVO tsVO = trainingScheService.addTrainingSche(trainingclsid, userVO.getMemberid(),
						new java.sql.Timestamp(System.currentTimeMillis()), null);

				TrainingHistService thSvc = new TrainingHistService();
				System.out.println("開始新增traininghist");
				for (TrainingClsDetailVO tcdVO : listActions3) {
					TrainingHistVO thVO = thSvc.addTrainingHist(userVO.getMemberid(),
							tsVO.getTrainingscheid(), tcdVO.getActionid(), 0, 0, 0, 0);

					thList.add(thVO);
				}

				if (tsVO.getTrainingscheid() == null
						|| tsVO.getTrainingscheid().trim().length() == 0) {
					errorMsgs.add("訓練課程編號請勿空白");
					System.out.println("1");
				}

				String memberid = req.getParameter("memberid").trim();
				if (memberid == null || memberid.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
					System.out.println("2");
				}

				// 出現錯誤訊息導入trainingindex
				if (!errorMsgs.isEmpty()) {
					System.out.println("error");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trainingindex.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("tsVO", tsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trainingindex.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ActionService actionSvc = new ActionService();
				Map<String, ActionVO> actionMap = actionSvc.getActionMap();

				req.setAttribute("actionMap", actionMap);
//				req.setAttribute("thList", thList);
				session.setAttribute("thList", thList);
				req.setAttribute("tsVO", tsVO);
				

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/traininghist/MyTrainingHist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trainingindex.jsp");
				failureView.forward(req, res);
			}
		}
//開始新增我的運動hist		
		if ("mytraininghist".equals(action)) {

			
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("開始記錄traininghist");
//			try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String trainingclsid = req.getParameter("trainingclsid").trim();
			if (trainingclsid == null || trainingclsid.trim().length() == 0) {
				errorMsgs.add("訓練trainingcls編號請勿空白");
				System.out.println("1");
			}

			System.out.println("接收trainingclsid");

			if (!errorMsgs.isEmpty()) {
				System.out.println("error");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trainingindex.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			if (!errorMsgs.isEmpty()) {

				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trainingindex.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			TrainingClsService tcSvc = new TrainingClsService();
			TrainingClsVO tcVO = tcSvc.getOneTrainingCls(trainingclsid);
			req.setAttribute("tcVO", tcVO);
			System.out.println("開始新增trainingclsid");
			List<TrainingClsDetailVO> list = (List<TrainingClsDetailVO>) session.getAttribute("listActions3");
			MemberVO memberVO = (MemberVO) session.getAttribute("userVO");
			TrainingScheVO trainSchVO = new TrainingScheService().addTrainingSche(trainingclsid, memberVO.getMemberid(),
					new java.sql.Timestamp(new java.util.Date().getTime()), null);
			System.out.println("開始新增trainingclsdetail list");
			ActionService actionSvc = new ActionService();
			Map<String, ActionVO> actionMap = actionSvc.getActionMap();
			req.setAttribute("actionMap", actionMap);
			System.out.println("開始新增actionmap");
			TrainingHistService thSvc = new TrainingHistService();
			List<TrainingHistVO> thList = new ArrayList<TrainingHistVO>();
			System.out.println("開始新增traininghist");
			for (TrainingClsDetailVO tcdVO : list) {
				TrainingHistVO thVO = thSvc.addTrainingHist(memberVO.getMemberid(), trainSchVO.getTrainingscheid(),
						tcdVO.getActionid(), 0, 0, 0, 0);
				thList.add(thVO);
			}

			for (TrainingHistVO vo : thList) {
				System.out.println(vo.toString());
			}
			req.setAttribute("thList", thList);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/traininghist/MyTrainingHist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/trainingindex.jsp");
//				failureView.forward(req, res);
//				System.out.println("進入錯誤處理");
//			}
		}

		if ("delete".equals(action)) { // 來自listAllAction.jsp

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String trainingscheid = req.getParameter("trainingscheid").trim();
				/*************************** 2.開始刪除資料 ***************************************/
				TrainingScheService tsSvc = new TrainingScheService();
				tsSvc.deleteTrainingSche(trainingscheid);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/trainingsche/listAllTrainingSche.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				System.out.println("1");
				successView.forward(req, res);
				System.out.println("2ggg");
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/trainingsche/listAllTrainingSche.jsp");
				System.out.println("3");
				failureView.forward(req, res);
				System.out.println("4");
			}
		}
	}
}
