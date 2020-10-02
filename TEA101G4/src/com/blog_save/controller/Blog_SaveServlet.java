package com.blog_save.controller;

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

import com.blog_save.model.Blog_SaveService;
import com.blog_save.model.Blog_SaveVO;



@WebServlet("/blog_Save/Blog_SaveServlet")
@MultipartConfig
public class Blog_SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action "+action);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action "+action);

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("blogsaveno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入部落格收藏編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_save/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String blogSaveno = null;
				try {
					blogSaveno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("部落格收藏編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_save/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Blog_SaveService blogSaveSvc = new Blog_SaveService();
				Blog_SaveVO blogSaveVO = blogSaveSvc.getOneBlogSave(blogSaveno);
				if (blogSaveVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_save/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				分頁
				String whichPage = req.getParameter("whichPage");
				req.setAttribute("whichPage", whichPage);
				
				req.setAttribute("blog_SaveVO", blogSaveVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/blog_save/listOneBlog_Save.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_save/select_page.jsp");
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
				String blogSaveno = req.getParameter("blogSaveno");

				/*************************** 2.開始查詢資料 ****************************************/
				Blog_SaveService blogSaveSvc = new Blog_SaveService();
				Blog_SaveVO blogSaveVO = blogSaveSvc.getOneBlogSave(blogSaveno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("blog_SaveVO", blogSaveVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/blog_save/update_blog_Save_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_save/listAllBlog_Save.jsp");
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
				String blogSaveno = new String(req.getParameter("blogSaveno").trim());
				
				String memberid = req.getParameter("memberid");
				String memberidReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberid == null || memberid.trim().length() == 0) {
					errorMsgs.add("一般會員編號: 請勿空白");
				} else if (!memberid.trim().matches(memberidReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("一般會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String blogno = req.getParameter("blogno").trim();
				if (blogno == null || blogno.trim().length() == 0) {
					errorMsgs.add("文章編號請勿空白");
				}

				String status = req.getParameter("status").trim();
				if (status == null || status.trim().length() == 0) {
					errorMsgs.add("內文請勿空白，請輸入Y或N");
				}
				Timestamp savedate = new Timestamp(System.currentTimeMillis());
				
				Timestamp updatetime = new Timestamp(System.currentTimeMillis());

				Blog_SaveVO blogSaveVO = new Blog_SaveVO();
				
				blogSaveVO.setBlogSaveno(blogSaveno);
				blogSaveVO.setMemberId(memberid);
				blogSaveVO.setBlogno(blogno);
				blogSaveVO.setStatus(status);
				blogSaveVO.setSaveDate(savedate);
				blogSaveVO.setUpdateTime(updatetime);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blog_SaveVO", blogSaveVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_save/update_blog_Save_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				
				Blog_SaveService blogSaveSvc = new Blog_SaveService();
				blogSaveVO = blogSaveSvc.updateBlogSave(blogSaveno, memberid, blogno, status, savedate, updatetime);
						

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("blog_SaveVO", blogSaveVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/blog_save/listOneBlog_Save.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_save/update_Blog_Save_input.jsp");
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
					errorMsgs.add("一般會員編號: 請勿空白");
				} else if (!memberid.trim().matches(memberidReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("一般會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String blogno = req.getParameter("blogno").trim();
				if (blogno == null || blogno.trim().length() == 0) {
					errorMsgs.add("文章編號請勿空白");
				}

				String status = req.getParameter("status").trim();
				if (status == null || status.trim().length() == 0) {
					errorMsgs.add("內文請勿空白，請輸入Y或N");
				}
				Timestamp savedate = new Timestamp(System.currentTimeMillis());
				
				Timestamp updatetime = new Timestamp(System.currentTimeMillis());

				Blog_SaveVO blogSaveVO = new Blog_SaveVO();
				
			
				blogSaveVO.setMemberId(memberid);
				blogSaveVO.setBlogno(blogno);
				blogSaveVO.setStatus(status);
				blogSaveVO.setSaveDate(savedate);
				blogSaveVO.setUpdateTime(updatetime);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blog_SaveVO", blogSaveVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_save/addBlog_Save.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Blog_SaveService blogSaveSvc = new Blog_SaveService();
				blogSaveVO = blogSaveSvc.addBlogSave(memberid, blogno, status, savedate, updatetime);
						
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/blog_save/listAllBlog_Save.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_save/addBlog_Save.jsp");
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
				String blogSaveno = new String(req.getParameter("blogSaveno"));

				/*************************** 2.開始刪除資料 ***************************************/
				Blog_SaveService blogSaveSvc = new Blog_SaveService();
				blogSaveSvc.deleteBlogSave(blogSaveno);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/blog_save/listAllBlog_Save.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_save/listAllBlog_Save.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getUserSaveBlog".equals(action)) { // 來自select_page.jsp的請求
			System.out.println("i am here");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memberId = req.getParameter("memberId");
				System.out.println(memberId);
				if (memberId == null || memberId.trim().length() == 0) {
					errorMsgs.add("請登入會員");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Blog_SaveService blogSaveSvc = new Blog_SaveService();
				List<Blog_SaveVO> userSaveList = blogSaveSvc.getMemberSaveBlog(memberId);
				

				if (userSaveList == null) {
					errorMsgs.add("查無資料");
				}
				
				if (memberId == null) {
					errorMsgs.add("查無資料");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_save/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				String whichPage = req.getParameter("whichPage");
				System.out.println("whichPage "+whichPage);
				req.setAttribute("whichPage", whichPage);
			
				
				System.out.println(userSaveList);
				req.setAttribute("userSaveList", userSaveList); 
				String url = "/front-end/blog_save/listAllBlog_Save.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_save/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
