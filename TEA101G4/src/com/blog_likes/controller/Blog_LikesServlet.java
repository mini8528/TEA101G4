package com.blog_likes.controller;

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

import com.blog_likes.model.Blog_LikesService;
import com.blog_likes.model.Blog_LikesVO;
import com.blog_save.model.Blog_SaveVO;



@WebServlet("/blog_Likes/Blog_LikesServlet")
@MultipartConfig
public class Blog_LikesServlet extends HttpServlet {
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
				String str = req.getParameter("bloglikesno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入部落格按讚編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog_likes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String blogLikesno = null;
				try {
					blogLikesno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("部落格按讚編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog_likes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Blog_LikesService blogLikesSvc = new Blog_LikesService();
				Blog_LikesVO blogLikesVO = blogLikesSvc.getOneBlogLikes(blogLikesno);
				if (blogLikesVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog_likes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("blog_LikesVO", blogLikesVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/blog_likes/listOneBlog_Likes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog_likes/select_page.jsp");
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
				String blogLikesno = req.getParameter("blogLikesno");

				/*************************** 2.開始查詢資料 ****************************************/
				Blog_LikesService blogLikesSvc = new Blog_LikesService();
				Blog_LikesVO blogLikesVO = blogLikesSvc.getOneBlogLikes(blogLikesno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("blog_LikesVO", blogLikesVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/blog_likes/update_blog_Likes_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog_likes/listAllBlog_Likes.jsp");
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
				String blogLikesno = new String(req.getParameter("blogLikesno").trim());
				
				String blogno = req.getParameter("blogno").trim();
				if (blogno == null || blogno.trim().length() == 0) {
					errorMsgs.add("文章編號請勿空白");
				}
				
				String memberid = req.getParameter("memberid");
				String memberidReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberid == null || memberid.trim().length() == 0) {
					errorMsgs.add("一般會員編號: 請勿空白");
				} else if (!memberid.trim().matches(memberidReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("一般會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String status = req.getParameter("status").trim();
				if (status == null || status.trim().length() == 0) {
					errorMsgs.add("內文請勿空白，請輸入Y或N");
				}
				
				Timestamp likesdate = new Timestamp(System.currentTimeMillis());
				
				Timestamp updatetime = new Timestamp(System.currentTimeMillis());

				Blog_LikesVO blogLikesVO = new Blog_LikesVO();
				
				blogLikesVO.setBlogLikesno(blogLikesno);
				blogLikesVO.setBlogno(blogno);
				blogLikesVO.setMemberId(memberid);
				blogLikesVO.setStatus(status);
				blogLikesVO.setLikesDate(likesdate);
				blogLikesVO.setUpdateTime(updatetime);
		

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blog_LikesVO", blogLikesVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog_likes/update_blog_Likes_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				
				Blog_LikesService blogLikesSvc = new Blog_LikesService();
				blogLikesVO = blogLikesSvc.updateBlogLikes(blogLikesno, blogno, memberid, status, likesdate, updatetime);
						

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("blog_LikesVO", blogLikesVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/blog_likes/listOneBlog_Likes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog_mes/update_Blog_Mes_input.jsp");
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
				
				String blogno = req.getParameter("blogno").trim();
				if (blogno == null || blogno.trim().length() == 0) {
					errorMsgs.add("文章編號請勿空白");
				}
				
				String memberid = req.getParameter("memberid");
				String memberidReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberid == null || memberid.trim().length() == 0) {
					errorMsgs.add("一般會員編號: 請勿空白");
				} else if (!memberid.trim().matches(memberidReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("一般會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String status = req.getParameter("status").trim();
				if (status == null || status.trim().length() == 0) {
					errorMsgs.add("內文請勿空白，請輸入Y或N");
				}
				
				Timestamp likesdate = new Timestamp(System.currentTimeMillis());
				
				Timestamp updatetime = new Timestamp(System.currentTimeMillis());

				Blog_LikesVO blogLikesVO = new Blog_LikesVO();
				
				blogLikesVO.setBlogno(blogno);
				blogLikesVO.setMemberId(memberid);
				blogLikesVO.setStatus(status);
				blogLikesVO.setLikesDate(likesdate);
				blogLikesVO.setUpdateTime(updatetime);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blog_LikesVO", blogLikesVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog_likes/addBlog_Likes.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Blog_LikesService blogLikesSvc = new Blog_LikesService();
				blogLikesVO = blogLikesSvc.addBlogLikes(blogno, memberid, status, likesdate, updatetime);
						
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/blog_likes/listAllBlog_Likes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog_likes/addBlog_Likes.jsp");
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
				String blogLikesno = new String(req.getParameter("blogLikesno"));

				/*************************** 2.開始刪除資料 ***************************************/
				Blog_LikesService blogLikesSvc = new Blog_LikesService();
				blogLikesSvc.deleteBlogLikes(blogLikesno);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/blog_likes/listAllBlog_Likes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog_likes/listAllBlog_Likes.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
