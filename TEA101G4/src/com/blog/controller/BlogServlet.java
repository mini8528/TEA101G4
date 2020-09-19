package com.blog.controller;

import java.io.IOException;
import java.io.InputStream;
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

import com.blog.model.BlogService;
import com.blog.model.BlogVO;

@WebServlet("/blog/BlogServlet")
@MultipartConfig
public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("blogno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String blogno = null;
				try {
					blogno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.getOneBlog(blogno);
				if (blogVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("blogVO", blogVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/blog/listOneBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/select_page.jsp");
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
				String blogno = req.getParameter("blogno");

				/*************************** 2.開始查詢資料 ****************************************/
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.getOneBlog(blogno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("blogVO", blogVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/blog/update_blog_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
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
				String blogno = new String(req.getParameter("blogno").trim());

				String memberid = req.getParameter("memberid");
				String memberidReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberid == null || memberid.trim().length() == 0) {
					errorMsgs.add("一般會員編號: 請勿空白");
				} else if (!memberid.trim().matches(memberidReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("一般會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String blogclass = req.getParameter("blogclass").trim();
				if (blogclass == null || blogclass.trim().length() == 0) {
					errorMsgs.add("文章分類請勿空白");
				}

				
				Timestamp postdate = Timestamp.valueOf(req.getParameter("postdate").trim());

				String title = req.getParameter("title").trim();
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}

				String text = req.getParameter("text").trim();
				if (text == null || text.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}

//				blogno = new String(req.getParameter("blogno").trim());
				BlogService blogSvc = new BlogService();
				Part photo = req.getPart("photo");
				InputStream in = photo.getInputStream();
				byte[] buf = null;
				if (in.available() != 0) {
					buf = new byte[in.available()];
					in.read(buf);
					in.close();
				} else {
					buf = blogSvc.getOneBlog(blogno).getPhoto();
				}
				BlogVO blogVO = new BlogVO();
				blogVO.setBlogno(blogno);
				blogVO.setMemberId(memberid);
				blogVO.setBlogClass(blogclass);
				blogVO.setPostDate(postdate);
				blogVO.setTitle(title);
				blogVO.setText(text);
				blogVO.setPhoto(buf);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogVO", blogVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/update_blog_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/

				blogVO = blogSvc.updateBlog(blogno, memberid, blogclass, postdate,
						title, text, buf, null, "N", new Timestamp(System.currentTimeMillis()));

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("blogVO", blogVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/blog/listOneBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/update_Blog_input.jsp");
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
				String memberid = req.getParameter("memberid");
				String memberidReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberid == null || memberid.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if (!memberid.trim().matches(memberidReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String blogclass = req.getParameter("blogclass").trim();
				if (blogclass == null || blogclass.trim().length() == 0) {
					errorMsgs.add("文章分類請勿空白");
				}

//				java.sql.Timestamp postdate = null;
//				try {
//					postdate = java.sql.Timestamp.valueOf(req.getParameter("postdate").trim());
//				} catch (IllegalArgumentException e) {
//					postdate = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}

				String title = req.getParameter("title").trim();
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}

				String text = req.getParameter("text").trim();
				if (text == null || text.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}

//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}
//
//				Double comm = null;
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}

				InputStream in = null;
				byte[] buf = null;
				Part photo = req.getPart("photo");
				in = photo.getInputStream();
				buf = new byte[in.available()];
				in.read(buf);
				in.close();
				

				BlogVO blogVO = new BlogVO();
				blogVO.setMemberId(memberid);
				;
				blogVO.setBlogClass(blogclass);
//				blogVO.setPostDate(postdate);
				blogVO.setTitle(title);
				blogVO.setText(text);
				blogVO.setPhoto(buf);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogVO", blogVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/addBlog.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				BlogService blogSvc = new BlogService();
				blogVO = blogSvc.addBlog(memberid, blogclass, new Timestamp(System.currentTimeMillis()), title, text,
						buf, null, "N", null);
//				blogVO = blogSvc.addBlog(memberid, blogclass, new Timestamp(System.currentTimeMillis()), title, text, null, null, "N", null);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/blog/listAllBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/addBlog.jsp");
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
				String blogno = new String(req.getParameter("blogno"));

				/*************************** 2.開始刪除資料 ***************************************/
				BlogService blogSvc = new BlogService();
				blogSvc.deleteBlog(blogno);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/blog/listAllBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
