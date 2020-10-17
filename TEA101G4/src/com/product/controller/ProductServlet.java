package com.product.controller;

import java.util.List;
import java.util.Optional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.jsp.PageContext;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.spec.model.SpecVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 500 * 500 * 1024
		* 1024)
@WebServlet("/back-end/product/product.do")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = -4024538454158252173L;
	int aa = 1;

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
			System.out.println("result=" + ("getOne_For_Display".equals(action)));
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String str = req.getParameter("productid");
//			輸入錯誤的處理（沒輸入或空白｜轉跳頁面）
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
//				傳入字串格式有問題，拋出例外，顯示錯誤訊息｜轉跳頁面
				String productid = null;
				try {
					productid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
//			2.開始查詢
				ProductService productSvc = new ProductService();
				ProductVO productVO = (ProductVO) productSvc.getOneProduct(productid);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
//			3.查詢完成，準備轉交
				req.setAttribute("productVO", productVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listOneProduct.jsp");
				successView.forward(req, res);
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
				failureView.forward(req, res);
			}
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		list-編輯單筆		
		if ("getOne_For_Update".equals(action)) {
			System.out.println("---getOne_For_Update---");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// 接收請求參數
				String productid = new String(req.getParameter("productid"));
				// 開始查詢該筆資料
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(productid);
				// 查詢完成，準備轉交
				System.out.println("查詢完成，準備轉交");
				req.setAttribute("productVO", productVO);
				System.out.println("productVO");
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/update_product.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				System.out.println("list-編輯單筆=Exception");
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllproduct.jsp");
				failureView.forward(req, res);
			}
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		進入編輯單筆商品頁面
		if ("update".equals(action)) {
			System.out.println("--update---");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("----==1==-----");
//			1.接收請求參數
			try {
				String productid = new String(req.getParameter("productid"));
				System.out.println("----==2==-----");
				String adminid = new String(req.getParameter("adminid"));
				System.out.println("00" + adminid);
				System.out.println("11");
				Timestamp adddate = Timestamp.valueOf(req.getParameter("adddate"));
				System.out.println("adddate;" + adddate);
//			輸入錯誤的處理（沒輸入或空白｜不符合正規表示｜轉跳頁面）
				String name = req.getParameter("name").trim();
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9._)[-][\\s][\"]]{1,60}$";//(:blank:)(:punct:)
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("商品名稱：請勿空白。");
				} else if (!name.trim().matches(nameReg)) {
					System.out.println("update??");
					errorMsgs.add("商品名稱：只能是中、英文字母、數字和_ , 且長度必需在1到60之間");
				}
//			
				String brandid = new String(req.getParameter("brandid"));
				if (brandid == null || brandid.trim().length() == 0) {
					errorMsgs.add("需選擇品牌");
				}

				Timestamp editdate = new java.sql.Timestamp(System.currentTimeMillis());

				String category = null;
				category = new String(req.getParameter("category").trim());
				if (category == null || category.trim().length() == 0) {
					errorMsgs.add("需選擇種類");
				}

				Integer price = new Integer(req.getParameter("price").trim());
				if (price == null || price == 0) {
					errorMsgs.add("需輸入金額");
				}
				String adminid2 = null;
				adminid2 = new String(req.getParameter("adminid2").trim());
				if (adminid2 == null || adminid2.trim().length() == 0) {
					errorMsgs.add("需選擇編輯者");
				}

				String status = null;
				status = new String(req.getParameter("status").trim());
				if (status == null || status.trim().length() == 0) {
					errorMsgs.add("需選擇上下架狀態");
				}

				String intro = null;
				intro = new String(req.getParameter("intro").trim());
				if (intro == null || intro.trim().length() == 0) {
					errorMsgs.add("需輸入商品描述");
				}

				byte[] photo1 = null;
				byte[] photo2 = null;
				byte[] photo3 = null;
				Part photo = req.getPart("photo1");
				System.out.println("photo1:"+photo1);
				try {
					if (photo.getSize() == 0) {
//						errorMsgs.add("請上傳照片");
						ProductService productSvc = new ProductService();
						List<ProductVO> list = productSvc.getAll();
						Optional<ProductVO> pp = list.stream()
						.filter(p -> p.getProductid().equals(productid))
						.findFirst();
						photo1 = pp.get().getPhoto1();
					} else {
						InputStream fis = photo.getInputStream();
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
					System.out.println("photo_Exception");
					e.printStackTrace();
				}
				Part photoP2 = req.getPart("photo2");
				try {
					if (photoP2.getSize() == 0) {
						ProductService productSvc = new ProductService();
						List<ProductVO> list = productSvc.getAll();
						Optional<ProductVO> pp = list.stream()
						.filter(p -> p.getProductid().equals(productid))
						.findFirst();
						photo2 = pp.get().getPhoto2();
					} else {
						InputStream fis = photoP2.getInputStream();
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
					System.out.println("photo_Exception");
					e.printStackTrace();
				}
				Part photoP3 = req.getPart("photo3");
				try {
					if (photoP3.getSize() == 0) {
						ProductService productSvc = new ProductService();
						List<ProductVO> list = productSvc.getAll();
						Optional<ProductVO> pp = list.stream()
						.filter(p -> p.getProductid().equals(productid))
						.findFirst();
						photo3 = pp.get().getPhoto3();
					} else {
						InputStream fis = photoP3.getInputStream();
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
					System.out.println("photo_Exception");
					e.printStackTrace();
				}
//-------------------
				ProductVO productVO = new ProductVO();
				productVO.setProductid(productid);
				productVO.setName(name);
				productVO.setAdminid(adminid);
				productVO.setAdddate(adddate);
				productVO.setAdminid2(adminid2);
				productVO.setBrandid(brandid);
				productVO.setCategory(category);
				productVO.setPrice(price);
				productVO.setEditdate(editdate);
				productVO.setStatus(status);
				productVO.setPhoto1(photo1);
				productVO.setPhoto2(photo2);
				productVO.setPhoto3(photo3);
				productVO.setIntro(intro);

				System.out.println("productVO:" + productVO.toString());

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher fv = req.getRequestDispatcher("/back-end/product/update_product.jsp");
					fv.forward(req, res);
					return;
				}
//			開始修改資料
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProduct(productid, adminid, adminid2, brandid, name, category, price,
						adddate, status, editdate, photo1, photo2, photo3, intro);
				req.setAttribute("productVO", productVO);// ?
				RequestDispatcher successViw = req.getRequestDispatcher("/back-end/product/listOneProduct.jsp");
				successViw.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改失敗：" + e.getMessage());
				RequestDispatcher failure = req.getRequestDispatcher("/back-end/product/update_product.jsp");
				failure.forward(req, res);
			}
		}
//		---------------------------------------------------------------------------------------------------------------------------
//		新增商品
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
//			輸入錯誤的處理（沒輸入或空白｜不符合正規表示｜轉跳頁面）
				String name = req.getParameter("name").trim();
				System.out.println("name=>" + name);
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9._)[-][\\s][\"]]{1,60}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("商品名稱：請勿空白。");
				} else if (!name.trim().matches(nameReg)) {
					errorMsgs.add("商品名稱：只能是中、英文字母、數字和_ , 且長度必需在1到60之間");
				}

				String brandid = req.getParameter("brandid").trim();
				if (brandid == null || brandid.trim().length() == 0) {
					errorMsgs.add("需選擇品牌");
				}

				Timestamp adddate = new java.sql.Timestamp(System.currentTimeMillis());

				String category = null;
				category = new String(req.getParameter("category").trim());
				if (category == null || category.trim().length() == 0) {
					errorMsgs.add("需選擇種類");
				}

				Integer price = new Integer(req.getParameter("price").trim());
				if (price == null || price == 0) {
					errorMsgs.add("需輸入金額");
				}
				String adminid = null;
				adminid = new String(req.getParameter("adminid").trim());
				if (adminid == null || adminid.trim().length() == 0) {
					errorMsgs.add("需選擇編輯者");
				}

				String status = null;
				status = new String(req.getParameter("status").trim());
				if (status == null || status.trim().length() == 0) {
					errorMsgs.add("需選擇上下架狀態");
				}

				String intro = null;
				intro = new String(req.getParameter("intro").trim());
				if (intro == null || intro.trim().length() == 0) {
					errorMsgs.add("需輸入商品描述");
				}
//--------------
				String specific = null;
				specific = req.getParameter("specific").trim();
				System.out.println("specific=>" + specific);
//			輸入錯誤的處理（沒輸入或空白｜不符合正規表示｜轉跳頁面）
				String specificReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9._)[-][\\s][\"]]{1,60}$";//(\u4e00-\u9fa5)(a-zA-Z0-9._)[-][\\s][\"]
				if (specific == null || specific.trim().length() == 0) {
					errorMsgs.add("規格：請勿空白。");
				} else if (!specific.trim().matches(specificReg)) {
					errorMsgs.add("規格：只能是中、英文字母、數字和_ , 且長度必需在1到60之間");
				}
				Integer stock = new Integer(req.getParameter("stock").trim());
				System.out.println("stock=>" + stock);
				if (stock == null || stock == 0) {
					errorMsgs.add("請輸入庫存數量");
				}
//--------------
				byte[] photo1 = null;
				byte[] photo2 = null;
				byte[] photo3 = null;
				Part photo = req.getPart("photo1");
				try {
					if (photo.getSize() == 0) {
						errorMsgs.add("至少上傳一張照片");
					} else {
						InputStream fis = photo.getInputStream();
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
				Part photoP2 = req.getPart("photo2");
				try {
					if (photoP2.getSize() == 0) {
						photoP2 = null;
//						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photoP2.getInputStream();
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
				Part photoP3 = req.getPart("photo3");
				try {
					if (photoP3.getSize() == 0) {
						photoP3 = null;
//						errorMsgs.add("請上傳照片");
					} else {
						InputStream fis = photoP3.getInputStream();
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
//??
//				Part photo = req.getPart("photo2");
//				Part photo = req.getPart("photo3");
//				List<Part> parts = (List<Part>) req.getParts();
//				parts.add(req.getPart("photo2"));//這兩行不可留，getParts已經裝好了
//				parts.add(req.getPart("photo3"));//
//				Boolean bb = true;
//				for (Part part : parts) {
//					try {
//						InputStream fis = part.getInputStream();
//						ByteArrayOutputStream baos = new ByteArrayOutputStream();
//						byte[] buffer = new byte[8192];
//						int i;
//						if (bb == true) {
//							while ((i = fis.read(buffer)) != -1) {
//								baos.write(buffer, 0, i);
//							}
//							baos.close();
//							fis.close();
//							photo2 = baos.toByteArray();
//							System.out.println("bb=" + bb);
//							bb = false;
//						} else {
//							while ((i = fis.read(buffer)) != -1) {
//								baos.write(buffer, 0, i);
//							}
//							baos.close();
//							fis.close();
//							photo3 = baos.toByteArray();
//							System.out.println("bb=" + bb);
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//-------------------
				ProductVO productVO = new ProductVO();
				productVO.setName(name);
				productVO.setAdminid(adminid);
				productVO.setBrandid(brandid);
				productVO.setCategory(category);
				productVO.setPrice(price);
				productVO.setAdddate(adddate);
				productVO.setStatus(status);
				productVO.setPhoto1(photo1);
				productVO.setPhoto2(photo2);
				productVO.setPhoto3(photo3);
				productVO.setIntro(intro);

				System.out.println(productVO.toString());

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher fv = req.getRequestDispatcher("/back-end/product/addProduct.jsp");
					fv.forward(req, res);
					return;
				}

				SpecVO specVO = new SpecVO();
				specVO.setSpecific(specific);
				specVO.setStock(stock);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("specVO", specVO);
					RequestDispatcher fv = req.getRequestDispatcher("/back-end/product/addProduct.jsp");
					fv.forward(req, res);
					return;
				}
//				開始新增資料
				ProductService productSvc = new ProductService();
				productVO = productSvc.auto_addProductAndSpec(adminid, brandid, name, category, price, adddate, status,
						photo1, photo2, photo3, intro, specific, stock);
				RequestDispatcher successViw = req.getRequestDispatcher("/back-end/product/listAllproduct.jsp");
				successViw.forward(req, res);
			} catch (Exception e) {
				System.out.println("add_Exception");
				errorMsgs.add("add_Exception");
				e.printStackTrace();
			}
		}
//delete-------------
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			1.接收請求參數
			try {
				String productid = new String(req.getParameter("productid"));
//			2.開始刪除資料
				ProductService productSvc = new ProductService();
				productSvc.deleteProduct(productid);
//			3.刪除完成,準備轉交(Send the Success view)
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listAllproduct.jsp");
				successView.forward(req, res);
//				其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllproduct.jsp");
				failureView.forward(req, res);
			}
		}
//管理員/查詢商品KEYWORD----------------
		if ("getSomeList".equals(action)) {
			System.out.println("getSome_For_Display:action---1");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
//				1.接收請求參數
				String name = new String(req.getParameter("name"));
				System.out.println("getSomeList:action---2");
//				2.開始查詢資料
				ProductService productSvc = new ProductService();
				List<ProductVO> list = productSvc.getProducts(name);
				System.out.println("符合的Product共：" + list.size());
				req.getSession().setAttribute("list", list);
				System.out.println("getSomeList:action---3");
//				3.查詢完成,準備轉交
//				req.setAttribute("productVO", productVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listSomeProduct.jsp");
				successView.forward(req, res);
				System.out.println("getSomeList:action---4");
//				其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
				failureView.forward(req, res);
			}
			System.out.println("end------getSomeList");
		}

//		-----------------------------------------------------------------
//		(客戶端)	商品頁-進入單筆商品頁面
		if ("getOne_For_Display_Front".equals(action)) {
			System.out.println("result=" + ("getOne_For_Display".equals(action)));
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			1.接收請求參數
			try {
				String str = req.getParameter("productid");
//			輸入錯誤的處理（沒輸入或空白｜轉跳頁面）
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
//				傳入字串格式有問題，拋出例外，顯示錯誤訊息｜轉跳頁面
				String productid = null;
				try {
					productid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
					failureView.forward(req, res);
					return;
				}
//			2.開始查詢
				ProductService productSvc = new ProductService();
				ProductVO productVO = (ProductVO) productSvc.getOneProduct(productid);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
					failureView.forward(req, res);
					return;
				}
//			3.查詢完成，準備轉交
				req.setAttribute("productVO", productVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/one_product.jsp");
				successView.forward(req, res);
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
				failureView.forward(req, res);
			}
		}
		// （管理員）查詢上下架商品----------------
		if ("getSomeListByStatus".equals(action)) {
			System.out.println("getSomeListByStatus:action---1");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
//						1.接收請求參數
				String status = new String(req.getParameter("status"));
				System.out.println("status:" + status);
//						2.開始查詢資料
				ProductService productSvc = new ProductService();
				List<ProductVO> list = productSvc.getAll_byStatus(status);
				System.out.println("符合的Product共：" + list.size());
				req.getSession().setAttribute("list", list);
				System.out.println("getSomeListByStatus:action---3");
//						3.查詢完成,準備轉交
//						req.setAttribute("productVO", productVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listSomeProduct.jsp");
				successView.forward(req, res);
				System.out.println("getSomeListByStatus:action---4");
//						其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
				failureView.forward(req, res);
			}
			System.out.println("end------getSomeList999");
		}
//				-----------------------------前台商品呈現------------------------------------
		if ("getAll_select".equals(action)) {
			ProductService productSvc = new ProductService();
			List<ProductVO> list = productSvc.getAll_byStatus("Y");
			System.out.println("list---:" + list.toString());
			System.out.println("lambda_test_start");
			String type = new String(req.getParameter("type").trim());
			System.out.println("type=" + type);
			
			if ("".equals(type)) {
				req.getSession().setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
				successView.forward(req, res);
				System.out.println("list=>>>" + list);
				return;
			}
			if("searchByPdname".equals(type)) {
				String name = new String(req.getParameter("name").trim());
				System.out.println("name="+name);
				if ("".equals(name)) {
					req.getSession().setAttribute("list", list);
					RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
					successView.forward(req, res);
					System.out.println("list=>>>" + list);
					return;
				}
				List<ProductVO> list2 = list.stream()
						.filter(p -> p.getName().contains(name))
						.collect(Collectors.toList());
				list2.forEach(System.out::println);
				System.out.println("======" + list2.toString());
				list = list2;
				req.getSession().setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
				successView.forward(req, res);
				return;
			}
			if ("searchByBrandname".equals(type)) {
				String brandid = new String(req.getParameter("brandid")).trim();
				System.out.println("brandid=" + brandid);
				if ("".equals(brandid)) {
					req.getSession().setAttribute("list", list);
					RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
					successView.forward(req, res);
					System.out.println("list=>>>" + list);
					return;
				}
				List<ProductVO> list2 = list.stream().filter(p -> p.getBrandid().equals(brandid))
						.collect(Collectors.toList());

				list2.forEach(System.out::println);
				System.out.println("======" + list2.toString());
				list = list2;

				req.getSession().setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
				successView.forward(req, res);
				return;
			}
			if ("searchByCategory".equals(type)) {
				String category = new String(req.getParameter("category")).trim();
				System.out.println("category=" + category);
				if ("".equals(category)) {
					req.getSession().setAttribute("list", list);
					RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
					successView.forward(req, res);
					System.out.println("list=>>>" + list);
					return;
				}
				List<ProductVO> list2 = list.stream().filter(p -> p.getCategory().equals(category))
						.collect(Collectors.toList());

				list2.forEach(System.out::println);
				System.out.println("======" + list2.toString());
				list = list2;
//				req.setAttribute("list", list);

				req.getSession().setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
				successView.forward(req, res);
				return;
			}
			if ("searchByMuti".equals(type)) {
				Enumeration<String> ee = req.getParameterNames();
				System.out.println(ee.toString());
				List<String> ll = new ArrayList<String>();
				if(ee.hasMoreElements()) {
					String n = (String) ee.nextElement();
					System.out.println("n:"+n);
					ll.add(n);
				}else {
					req.getSession().setAttribute("list", list);
					RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
					successView.forward(req, res);
					System.out.println("list=>>>" + list);
					return;
				}
				System.out.println("ll==>"+ll.toString());
				System.out.println("ll.size==>"+ll.size());
				if(ll.size()==2) {
					if ((ll.get(0)).equals("category")&&(ll.get(1)).equals("brandid")) {
						String category = req.getParameter("category");
						String brandid = req.getParameter("brandid");
						System.out.println("1category="+category+";"+"brandid"+brandid);
						List<ProductVO> list2 = list.stream()
								.filter(p -> p.getCategory().equals(category))
								.filter(p -> p.getBrandid().equals(brandid))
								.collect(Collectors.toList());
						list2.forEach(System.out::println);
						System.out.println("======" + list2.toString());
						list = list2;
						req.getSession().setAttribute("list", list);
						RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
						successView.forward(req, res);
						return;
					}
					else if((ll.get(1)).equals("category")&&(ll.get(0)).equals("brandid")) {
						String category = req.getParameter("category");
						String brandid = req.getParameter("brandid");
						System.out.println("2category="+category+";"+"brandid"+brandid);
						List<ProductVO> list2 = list.stream()
								.filter(p -> p.getCategory().equals(category))
								.filter(p -> p.getBrandid().equals(brandid))
								.collect(Collectors.toList());
						list2.forEach(System.out::println);
						System.out.println("======" + list2.toString());
						list = list2;
						req.getSession().setAttribute("list", list);
						RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
						successView.forward(req, res);
						return;
					}
					
				}else if(ll.size()==1) {
					System.out.println("ll.get(0)="+ll.get(0));
					if ((ll.get(0)).equals("category")) {
						String category = req.getParameter("category");
						List<ProductVO> list2 = list.stream()
								.filter(p -> p.getCategory().equals(category))
								.collect(Collectors.toList());
						list2.forEach(System.out::println);
						System.out.println("======" + list2.toString());
						list = list2;
						req.getSession().setAttribute("list", list);
						RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
						successView.forward(req, res);
						return;
					}else if((ll.get(0)).equals("brandid")) {
						String brandid = req.getParameter("brandid");
						System.out.println("(ll.get(0)).equals(\"brandid\")"+(ll.get(0)).equals("brandid"));
						System.out.println("brandid---"+brandid);
						List<ProductVO> list2 = list.stream()
								.filter(p -> p.getBrandid().equals(brandid))
								.collect(Collectors.toList());
						list2.forEach(System.out::println);
						System.out.println("======" + list2.toString());
						list = list2;
						req.getSession().setAttribute("list", list);
						RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
						successView.forward(req, res);
						return;
					}
				}
				req.getSession().setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
				successView.forward(req, res);
				return;
			}
			System.out.println("lambda_test_end");
			
		}
//		------------------------------------------------------------------------------------------------
//		===========前台商品價格================
		if("searchByPrice".equals(action)) {
			ProductService productSvc = new ProductService();
			List<ProductVO> list = (List<ProductVO>) req.getSession().getAttribute("list");
			Integer priceUB = Integer.parseInt(req.getParameter("priceUpperBound"));
			System.out.println("priceUB:"+priceUB);
			Integer priceLB = Integer.parseInt(req.getParameter("priceLowerBound"));
			System.out.println("priceLB:"+priceLB);
			if(list == null) {
				list = productSvc.getAll();
				System.out.println("list.size:" + list.size());
				List<ProductVO> list2 = list.stream()
						.filter(p -> p.getPrice() < priceUB)
						.filter(p -> p.getPrice() > priceLB)
						.collect(Collectors.toList());
				list2.forEach(System.out::println);
				System.out.println("======" + list2.toString());
				list = list2;
				req.getSession().setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
				successView.forward(req, res);
				return;
			}
			System.out.println("list.size:" + list.size());
			List<ProductVO> list2 = list.stream()
					.filter(p -> p.getPrice() < priceUB)
					.filter(p -> p.getPrice() > priceLB)
					.collect(Collectors.toList());
			list2.forEach(System.out::println);
			System.out.println("======" + list2.toString());
			list = list2;
			req.getSession().setAttribute("list", list);
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
			successView.forward(req, res);
			return;
		}

//		=====================================
//		===========後台多筆商品上下架================
		if("change_status_pds".equals(action)) {
			ProductService productSvc = new ProductService();
			List<ProductVO> list = productSvc.getAll();
			System.out.println("list.size->:" + list.size());
			
//			HttpSession session = req.getSession();
//			List<ProductVO> list_test = (List<ProductVO>) session.getAttribute("list");
			
			ProductVO productVO = new ProductVO();
			String[] aa = req.getParameterValues("productid");//
			List<String> productidAll = new ArrayList<String>();
			for(String test : aa) {
				System.out.println("挑選的商品id:"+test);
				productidAll.add(test);
			}
			for(String productid2 : productidAll) {
				productVO.setProductid(productid2);
			}
			String status = null;
			Optional<ProductVO> status2 = null;
			status2 = list.stream()
			.filter(p -> p.getProductid().equals(aa[0]))
			.findFirst();
			status = status2.get().getStatus();
			System.out.println("status:"+status);
			if("Y".equals(status)) {
				status = "N";
			}else if("N".equals(status)) {
				status = "Y";
			}
			Timestamp editdate = new java.sql.Timestamp(System.currentTimeMillis());
			
			productVO = productSvc.updateProducts_status2(productidAll, status, editdate);
			
			req.getSession().setAttribute("list", list);
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listAllproduct.jsp");
			successView.forward(req, res);
		}
//		=====================================
	}
//	public void method1(List<ProductVO> list,HttpServletRequest req,HttpServletResponse res) {
//		req.getSession().setAttribute("list", list);
//		RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/list_product.jsp");
//		System.out.println("list=>>>" + list);
//		try {
//			successView.forward(req, res);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}