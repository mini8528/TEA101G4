package com.coachClass.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/back-end/coachClass/coachClassShow.do")
public class ShowPhoto_CoachClassServlet extends HttpServlet {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String SHOW_PHOTO = "SELECT PHOTO FROM coachClass WHERE coachClassID = ?";
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("==  顯示課程照片    ShowPhoto_CoachClassServlet =");

		Connection con = null;
		PreparedStatement pstmt = null;
		
		res.setContentType("image/gif");
		ServletOutputStream outpho = res.getOutputStream();
		try {
			
			con = ds.getConnection();
			String coachClassID = new String(req.getParameter("coachClassID").trim());
			pstmt = con.prepareStatement(SHOW_PHOTO);
			pstmt.setString(1, coachClassID);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("資料庫查詢 :" + coachClassID );
			try {
				if (rs.next()) {
					BufferedInputStream in    = new BufferedInputStream(rs.getBinaryStream("PHOTO"));
					byte[] buf = new byte[4 * 1024];
					int len;
					while ((len = in.read(buf)) != -1) {
						outpho.write(buf, 0, len);
					}
					in.close();
				} else {
				}
				rs.close();
				pstmt.close();
				con.close();
				System.out.println("資料庫查詢 :" + coachClassID + "照片, 成功" );
			} catch (IOException e) {
				System.out.println("資料庫查詢 :" + coachClassID + "照片, 失敗, 使用預設照片" );
				InputStream in = getServletContext().getResourceAsStream("/back-end/coachClass/images/tomcat.png");
				byte[] b = new byte[in.available()];
				in.read(b);
				outpho.write(b);
				in.close();
			}
		}  catch (SQLException e) {
			System.out.println("資料庫查詢照片, 失敗, 使用預設照片" );
			InputStream in = getServletContext().getResourceAsStream("/back-end/coachClass/images/tomcat.png");
			byte[] b = new byte[in.available()];
			in.read(b);
			outpho.write(b);
			in.close();
		}
	}
}