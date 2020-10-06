package com.bodyRecord.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/back-end/bodyRecord/bodyRecordShow.do")
public class ShowPhoto_BodyRecordServlet extends HttpServlet {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "TEA101G4";
	private static final String passwd = "123456";
	
	private static final String SHOW_PHOTO = "SELECT PHOTO1 FROM bodyRecord WHERE bodyRecordID = ?";
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("進入 ShowPhoto_BodyRecordServlet");

		res.setContentType("image/gif");
		ServletOutputStream outpho = res.getOutputStream();
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, userid, passwd);
			String bodyRecordID = new String(req.getParameter("bodyRecordID").trim());
			PreparedStatement pstmt = con.prepareStatement(SHOW_PHOTO);
			pstmt.setString(1, bodyRecordID);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("進入 ShowPhoto_BodyRecordServlet 連結資料庫並查詢 :" + bodyRecordID );
			try {
				if (rs.next()) {
					BufferedInputStream in    = new BufferedInputStream(rs.getBinaryStream("PHOTO1"));
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
				System.out.println("進入 ShowPhoto_BodyRecordServlet 連結資料庫並查詢 :" + bodyRecordID + "照片, 查詢成功關閉連線" );
			} catch (IOException e) {
				System.out.println("進入 ShowPhoto_BodyRecordServlet 連結資料庫並查詢 :" + bodyRecordID + "照片, 寫入照片失敗, 使用預設照片" );
				InputStream in = getServletContext().getResourceAsStream("/back-end/bodyRecord/images/tomcat.png");
				byte[] b = new byte[in.available()];
				in.read(b);
				outpho.write(b);
				in.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("進入 ShowPhoto_BodyRecordServlet 連結資料庫並查詢照片, 查詢失敗, 使用預設照片" );
			InputStream in = getServletContext().getResourceAsStream("/back-end/bodyRecord/images/tomcat.png");
			byte[] b = new byte[in.available()];
			in.read(b);
			outpho.write(b);
			in.close();
		}
	}
}