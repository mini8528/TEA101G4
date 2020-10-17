package com.spec.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.spec.model.SpecService;
import com.spec.model.SpecVO;

@WebServlet("/back-end/spec/spec.do")
public class SpecServlet extends HttpServlet {
	int aa = 1;
	private static final long serialVersionUID = 3003728674238878202L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		-----------------------------------------
		System.out.println("ProductServlet_doPost " + aa++);
		System.out.println("action=" + action);
//		-----------------------------------------
//		首頁-搜單筆
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String str = req.getParameter("specid");
//			錯誤處理
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入規格編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spec/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
//			傳入字串格式有問題，拋出例外，顯示錯誤訊息｜轉跳頁面
				String specid = null;
				try {
					specid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("規格編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spec/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
//			2.開始查詢
				SpecService specSvc = new SpecService();
				SpecVO specVO = (SpecVO) specSvc.getOneSpec(specid);
				if (specVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spec/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
//			3.查詢完成，準備轉交
				req.setAttribute("specVO", specVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/spec/listOneSpec.jsp");
				successView.forward(req, res);
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spec/select_page.jsp");
				failureView.forward(req, res);
			}
		}
//------首頁/查詢部分-----------------------------------
		if ("getSomeList".equals(action)) {
			System.out.println("getSome_For_Display:action---1");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String productid = new String(req.getParameter("productid"));
//			2.開始查詢資料
				SpecService specSvc = new SpecService();
				List<SpecVO> list = specSvc.getSome(productid);
				req.getSession().setAttribute("list", list);
//			3.查詢完成,準備轉交
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/spec/listSomeSpec.jsp");
				successView.forward(req, res);
//				其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spec/select_page.jsp");
				failureView.forward(req, res);
			}
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		新增商品
		if ("insert".equals(action)) {
			System.out.println("000");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String specific = req.getParameter("specific").trim();
//			輸入錯誤的處理（沒輸入或空白｜不符合正規表示｜轉跳頁面）
				String specificReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9._)[-][\\s][\"]]{1,60}$";//[\\s][\"]
				if (specific == null || specific.trim().length() == 0) {
					errorMsgs.add("規格：請勿空白。");
				} else if (!specific.trim().matches(specificReg)) {
					errorMsgs.add("規格：只能是中、英文字母、數字和_ , 且長度必需在1到60之間");
				}
//--------------
				String productid = req.getParameter("productid").trim();
				if (productid == null || productid.trim().length() == 0) {
					errorMsgs.add("需選擇品牌ID");
				}
//				String specific = null;
//				specific = new String(req.getParameter("specific").trim());
//				if (specific == null || (specific.trim()).length() == 0) {
//					errorMsgs.add("需輸入規格");
//				}
				Integer stock = new Integer(req.getParameter("stock").trim());
				if (stock == null || stock == 0) {
					errorMsgs.add("請輸入庫存數量");
				}
//-------------------
				SpecVO specVO = new SpecVO();
				specVO.setProductid(productid);
				specVO.setSpecific(specific);
				specVO.setStock(stock);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("specVO", specVO);
					RequestDispatcher fv = req.getRequestDispatcher("/back-end/spec/addspec.jsp");
					fv.forward(req, res);
					return;
				}
//			開始新增資料
				SpecService specSvc = new SpecService();
				specVO = specSvc.addSpec(productid, specific, stock);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/spec/listAllspec.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("addSpec:Exception");
				e.printStackTrace();
			}
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		list-編輯單筆	
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// 接收請求參數
				String specid = new String(req.getParameter("specid"));
				// 開始查詢該筆資料
				SpecService specSvc = new SpecService();
				SpecVO specVO = specSvc.getOneSpec(specid);
				// 查詢完成，準備轉交
				System.out.println("查詢完成，準備轉交");
				req.setAttribute("specVO", specVO);
				System.out.println("specVO");
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/spec/update_spec.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				System.out.println("list-編輯單筆=Exception");
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spec/listAllspec.jsp");
				failureView.forward(req, res);
			}
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		進入編輯單筆商品頁面
		if ("update".equals(action)) {
			SpecService specSvc = new SpecService();
			List<SpecVO> list = specSvc.getAll();
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String specid = new String(req.getParameter("specid"));
				String productid = new String(req.getParameter("productid"));
				String specific = req.getParameter("specific").trim();
//			輸入錯誤的處理（沒輸入或空白｜不符合正規表示｜轉跳頁面）
				String specificReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9._)[-][\\s][\"]]{1,60}$";//[\\s][\"]
				if (specific == null || specific.trim().length() == 0) {
					errorMsgs.add("規格：請勿空白。");
				} else if (!specific.trim().matches(specificReg)) {
					errorMsgs.add("規格：只能是中、英文字母、數字和_ , 且長度必需在1到60之間");
				}
				Integer stock = new Integer(req.getParameter("stock"));
				if (stock == null || stock == 0) {
					errorMsgs.add("需輸入庫存");
				}
				SpecVO specVO = new SpecVO();
				System.out.println("specid:"+specid);
				System.out.println("productid:"+productid);
				System.out.println("specific:"+specific);
				System.out.println("stock:"+stock);
				
				
				System.out.println("specVO:"+specVO.toString());
//-------------------
				
				specVO.setSpecid(specid);
				specVO.setProductid(productid);
				specVO.setSpecific(specific);
				specVO.setStock(stock);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("specVO", specVO);
					RequestDispatcher fv = req.getRequestDispatcher("/back-end/spec/update_spec.jsp");
					fv.forward(req, res);
					return;
				}
//		開始修改資料
				specVO = specSvc.updateSpec(specid, productid, specific, stock);
				req.setAttribute("specVO", specVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/spec/listAllspec.jsp");
				successView.forward(req, res);
			} catch (IOException e) {
				errorMsgs.add("修改失敗：" + e.getMessage());
				RequestDispatcher failure = req.getRequestDispatcher("/back-end/spec/update_spec.jsp");
				failure.forward(req, res);
			}
		}

//delete-------------
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String specid = new String(req.getParameter("specid"));
//			2.開始刪除資料
				SpecService specSvc = new SpecService();
				specSvc.deleteSpec(specid);
//			3.刪除完成,準備轉交(Send the Success view)
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/spec/listAllspec.jsp");
				successView.forward(req, res);
//			其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/spec/listAllspec.jsp");
				failureView.forward(req, res);
			}
		}
	}
}