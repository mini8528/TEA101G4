package com.brand.controller;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;

import com.brand.model.BrandService;
import com.brand.model.BrandVO;

@WebServlet("/back-end/brand/brand.do")
public class BrandServlet extends HttpServlet {
	int aa = 1;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("BrandServlet_doPost" + aa++);
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//		Collection<Part> parts = req.getParts();
//		Iterator<Part> it = parts.iterator();
//		Part part = it.next();
//		part.
//		System.out.println("map="+req.getParameterMap());

System.out.println("action=" + action);
System.out.println("result=" + ("getOne_For_Display".equals(action)));

//首頁/查詢-------// 來自select_page.jsp的請求
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("brandid");
System.out.println("1.接收請求參數");
				if (str == null || (str.trim()).length() == 0) {
					System.out.println("str == null ");
					errorMsgs.add("請輸入要查詢的品牌編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/brand/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				String brandid = null;
				try {
					brandid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("品牌編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/brand/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				System.out.println("getOne_For_Display----");
				/*************************** 2.開始查詢資料 *****************************************/
				BrandService brandSvc = new BrandService();
				BrandVO brandVO = brandSvc.getOneBrand(brandid);
				if (brandVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/brand/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				System.out.println("getOne_For_Display------");
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("brandVO", brandVO); // 資料庫取出的brandVO物件,存入req
				String url = "/back-end/brand/listOneBrand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBrand.jsp
				successView.forward(req, res);
				System.out.println("getOne_For_Display--------");
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/brand/select_page.jsp");
				failureView.forward(req, res);
			}
		}
//查詢所有，修改----------------------來自listAllbrand.jsp的請求
		if ("getOne_For_Update".equals(action)) { // 來自listAllbrand.jsp的請求
			System.out.println("getOne_For_Update-");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String brandid = new String(req.getParameter("brandid"));

				/*************************** 2.開始查詢資料 ****************************************/
				BrandService brandSvc = new BrandService();
				BrandVO brandVO = brandSvc.getOneBrand(brandid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("brandVO", brandVO); // 資料庫取出的brandVO物件,存入req
				String url = "/back-end/brand/update_brand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_brand.jsp
				successView.forward(req, res);
System.out.println("getOne_For_Update--");
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/brand/listAllbrand.jsp");
				failureView.forward(req, res);
			}
		}
//update-------------------------from update_brand.jsp request------------------
		if ("update".equals(action)) {
System.out.println("--update1---");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
System.out.println("--update2---");
			try {
				String brandid = new String(req.getParameter("brandid"));
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,25}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("品牌名稱：請勿空白。");
				} else if (!name.trim().matches(nameReg)) {
					errorMsgs.add("品牌名稱：只能是中、英文字母、數字和_ , 且長度必需在1到25之間");
				}

				BrandVO brandVO = new BrandVO();
				brandVO.setBrandid(brandid);
				brandVO.setName(name);
				System.out.println("--update---");
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("brandVO", brandVO);
					RequestDispatcher fv = req.getRequestDispatcher("/back-end/brand/update_brand.jsp");
System.out.println("測試修改1");
					fv.forward(req, res);
					return;
				}
				BrandService brandSvc = new BrandService();
				brandVO = brandSvc.updateBrand(brandid, name);
System.out.println("測試修改2");
				req.setAttribute("brandVO", brandVO);
				RequestDispatcher sv = req.getRequestDispatcher("/back-end/brand/listOneBrand.jsp");
				sv.forward(req, res);
System.out.println("測試修改3");
			} catch (Exception e) {
				errorMsgs.add("編輯失敗：" + e.getMessage());
				RequestDispatcher fv = req.getRequestDispatcher("/back-end/brand/update_brand.jsp");
				fv.forward(req, res);
			}
		}
//add------------------------form addBrand.jsp request--------------------
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// -------------1.接收請求參數 - 輸入格式的錯誤處理-------
			try {
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,25}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("品牌名稱：請勿空白。");
				} else if (!name.trim().matches(nameReg)) {
					errorMsgs.add("品牌名稱：只能是中、英文字母、數字和_ , 且長度必需在1到25之間");
				}

				BrandVO brandVO = new BrandVO();
				brandVO.setName(name);
				System.out.println("--++--++--++");
				System.out.println(brandVO.toString());

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("brandVO", brandVO);
					RequestDispatcher fv = req.getRequestDispatcher("/back-end/brand/addBrand.jsp");
					fv.forward(req, res);
					return;
				}
				// 2.開始新增資料
				BrandService brandSvc = new BrandService();
				brandVO = brandSvc.addBrand(name);

				// 3.新增完成,準備轉交(Send the Success view)
				RequestDispatcher sv = req.getRequestDispatcher("/back-end/brand/listAllbrand.jsp");
				sv.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("新增失敗：" + e.getMessage());
				RequestDispatcher fv = req.getRequestDispatcher("/back-end/brand/addBrand.jsp");
				fv.forward(req, res);
			}
		}
//delete-----------------------from listAllbrand.jsp------------------------
		if ("delete".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1. get 請求參數
				String brandid = new String(req.getParameter("brandid"));
				// 2. delete data
				BrandService brandSvc = new BrandService();
				brandSvc.deleteBrand(brandid);
				// 3. delete完成，開始轉交view
				RequestDispatcher sv = req.getRequestDispatcher("/back-end/brand/listAllbrand.jsp");
				sv.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除失敗：" + e.getMessage());
				RequestDispatcher fv = req.getRequestDispatcher("/back-end/brand/listAllbrand.jsp");
				fv.forward(req, res);
			}
		}
		
		// 首頁/查詢品牌KEYWORD-------// 來自select_page.jsp的請求
		if ("getSomeList".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				/*************************** 1.接收請求參數 ****************************************/
				String name = new String(req.getParameter("name"));
				if (name == null || (name.trim()).length() == 0) {
					System.out.println("name == null ");
					errorMsgs.add("請輸入要查詢的品牌keyword");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/brand/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 ****************************************/
				BrandService brandSvc = new BrandService();
				List<BrandVO> list = brandSvc.getBrandsByName(name);
				System.out.println("符合的Brand共:"+list.size());
				req.getSession().setAttribute("list", list);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				req.setAttribute("brandVO", brandVO); // 資料庫取出的brandVO物件,存入req
String url = "/back-end/brand/listSomebrand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_brand.jsp
				successView.forward(req, res);
System.out.println("getSome_For_Display--");
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/brand/select_page.jsp");
				failureView.forward(req, res);
			}
		}

// 首頁/查詢品牌KEYWORD-------// 來自select_page.jsp的請求
//		if ("getSome_For_Display".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//				/*************************** 1.接收請求參數 ****************************************/
//				String name = new String(req.getParameter("name"));
//
//				/*************************** 2.開始查詢資料 ****************************************/
//				BrandService brandSvc = new BrandService();
//				BrandVO brandVO = (BrandVO) brandSvc.getOneBrand(name);
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				req.setAttribute("brandVO", brandVO); // 資料庫取出的brandVO物件,存入req
//String url = "/back-end/brand/listSomebrand.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_brand.jsp
//				successView.forward(req, res);
//System.out.println("getSome_For_Display--");
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/brand/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
		System.out.println("end");
	}

}
