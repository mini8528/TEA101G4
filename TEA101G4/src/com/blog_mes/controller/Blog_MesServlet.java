package com.blog_mes.controller;

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
import com.blog_mes.model.Blog_MesService;
import com.blog_mes.model.Blog_MesVO;


@WebServlet("/blog_Mes/Blog_MesServlet")
@MultipartConfig
public class Blog_MesServlet extends HttpServlet {
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
				String str = req.getParameter("blogmesno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入部落格留言編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String blogMesno = null;
				try {
					blogMesno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("部落格留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Blog_MesService blogMesSvc = new Blog_MesService();
				Blog_MesVO blogMesVO = blogMesSvc.getOneBlogMes(blogMesno);
				if (blogMesVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("blog_MesVO", blogMesVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/blog_mes/listOneBlog_Mes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/select_page.jsp");
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
				String blogMesno = req.getParameter("blogMesno");

				/*************************** 2.開始查詢資料 ****************************************/
				Blog_MesService blogMesSvc = new Blog_MesService();
				Blog_MesVO blogMesVO = blogMesSvc.getOneBlogMes(blogMesno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("blog_MesVO", blogMesVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/blog_mes/update_blog_Mes_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/listAllBlog_Mes.jsp");
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
				String blogMesno = new String(req.getParameter("blogMesno").trim());
				
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

				String text = req.getParameter("text").trim();
				if (text == null || text.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}
				

				Timestamp postdate = Timestamp.valueOf(req.getParameter("postdate").trim());
				
				Timestamp updatetime =  new Timestamp(System.currentTimeMillis());

				Blog_MesVO blogMesVO = new Blog_MesVO();
				blogMesVO.setBlogMesno(blogMesno);
				blogMesVO.setBlogno(blogno);
				blogMesVO.setMemberId(memberid);
				blogMesVO.setText(text);
				blogMesVO.setPostDate(postdate);
				blogMesVO.setUpdateTime(updatetime);
		

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blog_MesVO", blogMesVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/update_blog_Mes_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				
				Blog_MesService blogMesSvc = new Blog_MesService();
				blogMesVO = blogMesSvc.updateBlogMes(blogMesno, blogno, memberid, text, postdate, updatetime, "N");
						

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("blog_MesVO", blogMesVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/blog_mes/listOneBlog_Mes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/update_Blog_Mes_input.jsp");
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
				String blogno = req.getParameter("blogno").trim();
				if (blogno == null ||blogno.trim().length() == 0) {
					errorMsgs.add("文章編號請勿空白");
				}
				
				String memberid = req.getParameter("memberid");
				String memberidReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memberid == null || memberid.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if (!memberid.trim().matches(memberidReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String text = req.getParameter("text").trim();
				if (text == null || text.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}
				
				Timestamp postdate =  new Timestamp(System.currentTimeMillis());

				Blog_MesVO blogMesVO = new Blog_MesVO();
				blogMesVO.setBlogno(blogno);
				blogMesVO.setMemberId(memberid);
				blogMesVO.setText(text);
				blogMesVO.setPostDate(postdate);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blog_MesVO", blogMesVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/addBlog_Mes.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Blog_MesService blogMesSvc = new Blog_MesService();
				blogMesVO = blogMesSvc.addBlogMes(blogno, memberid, text, postdate, null, "N");
				
				BlogService blogser = new BlogService();
				BlogVO blogVO = blogser.getOneBlog(blogno);
				req.setAttribute("blogVO", blogVO);
				List<Blog_MesVO> list = blogMesSvc.getOneBlognoMes(blogno);
				req.setAttribute("list", list); 
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/blog/listOneBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/addBlog_Mes.jsp");
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
				String blogMesno = new String(req.getParameter("blogMesno"));

				/*************************** 2.開始刪除資料 ***************************************/
				Blog_MesService blogMesSvc = new Blog_MesService();
				blogMesSvc.deleteBlogMes(blogMesno);;

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/blog_mes/listAllBlog_Mes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/listAllBlog_Mes.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getAll_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("blogno");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入部落格留言編號");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String blogno = null;
				try {
					blogno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("部落格編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Blog_MesService blogMesSvc = new Blog_MesService();
				List<Blog_MesVO> blogMesList = blogMesSvc.getOneBlognoMes(blogno);
				if (blogMesList == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("blogMesList", blogMesList); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/blog_mes/listAllBlogno_Mes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog_mes/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("admin_Update_Mes".equals(action)) {
			try {
				String blogmesno = req.getParameter("blogMesno");
				String status = req.getParameter("status");
				Timestamp updatetime = new Timestamp(System.currentTimeMillis());
				System.out.println(blogmesno);
				System.out.println(status);

				Blog_MesService blogMesSer = new Blog_MesService();
				blogMesSer.adminChangeMesStatus(blogmesno, status, updatetime);
				System.out.println("success");
				String url = "/back-end/blog_mes/listAllBlog_Mes_admin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog_mes/listAllBlog_Mes_admin.jsp");
				failureView.forward(req, res);
		
			}
		}
		
		
	}

}
