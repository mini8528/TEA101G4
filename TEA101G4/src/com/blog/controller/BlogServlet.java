package com.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
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

import com.blog.model.BlogService;
import com.blog.model.BlogVO;
import com.blog_mes.model.Blog_MesService;
import com.blog_mes.model.Blog_MesVO;
import com.blog_save.model.Blog_SaveService;
import com.blog_save.model.Blog_SaveVO;
import com.member.model.MemberVO;

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
				String blogClass = blogVO.getBlogClass();
//				System.out.println(blogClass);
				if (blogVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Blog_MesService blogmesSvc = new Blog_MesService();
				List<Blog_MesVO> list = blogmesSvc.getOneBlognoMes(blogno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("blogVO", blogVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("list", list);
				if (("健身影片").equals(blogClass)) {
					req.setAttribute("video", "Y");
				}

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

				Part video = req.getPart("video");
				InputStream in2 = video.getInputStream();
				byte[] buf2 = null;
				if (in2.available() != 0) {
					buf2 = new byte[in2.available()];
					in2.read(buf2);
					in2.close();
				} else {
					buf2 = blogSvc.getOneBlog(blogno).getVideo();
				}

				Timestamp updatetime = new Timestamp(System.currentTimeMillis());

				BlogVO blogVO = new BlogVO();
				blogVO.setBlogno(blogno);
				blogVO.setMemberId(memberid);
				blogVO.setBlogClass(blogclass);
				blogVO.setPostDate(postdate);
				blogVO.setTitle(title);
				blogVO.setText(text);
				blogVO.setPhoto(buf);
				blogVO.setVideo(buf2);
				blogVO.setUpdateTime(updatetime);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogVO", blogVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/update_blog_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/

				blogVO = blogSvc.updateBlog(blogno, memberid, blogclass, postdate, title, text, buf, buf2, "N",
						updatetime);

				Blog_MesService blogmesSvc = new Blog_MesService();
				List<Blog_MesVO> list = blogmesSvc.getOneBlognoMes(blogno);
				String blogClass = blogVO.getBlogClass();

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("blogVO", blogVO); // 資料庫update成功後,正確的的empVO物件,存入req
				req.setAttribute("list", list); // 資料庫update成功後,正確的的empVO物件,存入req
				req.setAttribute("flag", "Y");
				if (("健身影片").equals(blogClass)) {
					req.setAttribute("video", "Y");
				}

				String url = "/front-end/blog/listOneBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/update_Blog_input.jsp");
				failureView.forward(req, res);
//				System.out.println("update 其他錯誤");
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

				Timestamp postdate = new Timestamp(System.currentTimeMillis());

				String title = req.getParameter("title").trim();
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}

				String text = req.getParameter("text").trim();
				if (text == null || text.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}
				
				Timestamp updatetime = new Timestamp(System.currentTimeMillis());

				InputStream in1 = null;
				byte[] buf = null;
				Part photo = req.getPart("photo");
				in1 = photo.getInputStream();
				buf = new byte[in1.available()];
				in1.read(buf);
				in1.close();

				InputStream in2 = null;
				byte[] buf2 = null;
				Part video = req.getPart("video");
				in2 = video.getInputStream();
				buf2 = new byte[in2.available()];
				in2.read(buf2);
				in2.close();

				BlogVO blogVO = new BlogVO();
				blogVO.setMemberId(memberid);
				blogVO.setBlogClass(blogclass);
				blogVO.setPostDate(postdate);
				blogVO.setTitle(title);
				blogVO.setText(text);
				blogVO.setPhoto(buf);
				blogVO.setVideo(buf2);
				blogVO.setUpdateTime(updatetime);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogVO", blogVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/addBlog.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				BlogService blogSvc = new BlogService();
				blogVO = blogSvc.addBlog(memberid, blogclass, postdate, title, text, buf, buf2, "N", updatetime);

//				blogVO = blogSvc.addBlog(memberid, blogclass, new Timestamp(System.currentTimeMillis()), title, text, null, null, "N", null);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("flag", null);
				if (("健身影片").equals(blogclass)) {
					req.setAttribute("video", "Y");
				}
				String url = "/front-end/blog/listAllBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/addBlog.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
//				System.out.println("其他錯誤處理");
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

		if ("getAllExperience".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String blogClass = req.getParameter("blogClass");
				if (blogClass == null || blogClass.trim().length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/

				BlogService blogSvc = new BlogService();
				List<BlogVO> bloglist = blogSvc.getAll();

				List<BlogVO> list = new ArrayList<>();

				// Collect CO Executives
				list = bloglist.stream()
						.filter(e -> e.getBlogClass().equals(blogClass))
						.collect(Collectors.toList());

				if (blogClass == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				System.out.println(list);
				req.setAttribute("list", list);
				req.setAttribute("flag", "Y");
				if (("健身影片").equals(blogClass)) {
					req.setAttribute("video", "Y");
				}
				String url = "/front-end/blog/listAllBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				System.out.println("error");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getUserBlog".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			System.out.println("i am here");
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memberId = req.getParameter("memberId");
//				System.out.println(memberId);
				if (memberId == null || memberId.trim().length() == 0) {
					errorMsgs.add("請登入會員");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/

				BlogService blogSvc = new BlogService();
				List<BlogVO> bloglist = blogSvc.getAll();

				List<BlogVO> list = new ArrayList<>();

				// Collect CO Executives
				list = bloglist.stream().filter(e -> e.getMemberId().equals(memberId)).collect(Collectors.toList());

				if (memberId == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				System.out.println(list);
				req.setAttribute("list", list);
				req.setAttribute("flag", "Y");

				String url = "/front-end/blog/listAllBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				System.out.println("error");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getUserSaveBlog".equals(action)) { // 來自select_page.jsp的請求
//			System.out.println("i am here");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memberId = req.getParameter("memberId");
//				HttpSession session = req.getSession();
//				// 【從 session 判斷此user是否登入過】
//				MemberVO userVO = (MemberVO) session.getAttribute("userVO");
//				if(userVO == null) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/login.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//				String memberId = userVO.getMemberid();
//				System.out.println(memberId);
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				System.out.println(userSaveList);
				req.setAttribute("userSaveList", userSaveList); 
				String url = "/front-end/blog/listAllBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("searchBlog".equals(action)) { // 來自select_page.jsp的請求
//			System.out.println("i am here");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String searchWord = req.getParameter("searchWord");
//				System.out.println(searchWord);
				if (searchWord == null || (searchWord.trim()).length() == 0) {
					errorMsgs.add("請輸入要查詢文字");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				BlogService blogSvc = new BlogService();
				List<BlogVO> list = blogSvc.getTitle(searchWord);
//				System.out.println("list " + list);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

//				Blog_MesService blogmesSvc = new Blog_MesService();
//				List<Blog_MesVO> list = blogmesSvc.getOneBlognoMes(blogno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				req.setAttribute("flag", "Y");


				String url = "/front-end/blog/listAllBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("blog_Hide".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String blogno = req.getParameter("blogno");
				

				/*************************** 2.開始隱藏資料 ****************************************/
				BlogService blogSvc = new BlogService();
				blogSvc.hideBlog(blogno);
				
				

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/front-end/blog/userArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("admin_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String blogno = req.getParameter("blogno");
				String status = req.getParameter("status");
//				System.out.println(blogno);
//				System.out.println(status);

				/*************************** 2.開始隱藏資料 ****************************************/
				BlogService blogSvc = new BlogService();
				blogSvc.adminChangeStatus(blogno, status);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/blog/listAllBlog_Admin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/listAllBlog_Admin.jsp");
				failureView.forward(req, res);
		
			}
		}

	}

}
