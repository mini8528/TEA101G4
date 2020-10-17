package com.lat.controller;

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

@WebServlet("/back-end/lat/latshow.do")
public class Latestnewsimage extends HttpServlet{
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "TEA101G4";
	private static final String PASSWORD = "123456";
	
	private static final String SHOW_PHOTO = "SELECT IMAGE FROM LATESTNEWS WHERE LATESTNEWSID = ?";
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("image/jpg");
		ServletOutputStream outpho = res.getOutputStream();
		try {
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			String latestnewsid = new String(req.getParameter("latestnewsid").trim());
//System.out.println("productid:"+productid);
//			PreparedStatement pstmt = con.prepareStatement("SELECT PHOTO1 FROM PRODUCT WHERE PRODUCTID = " +productid);
			PreparedStatement pstmt = con.prepareStatement(SHOW_PHOTO);
			pstmt.setString(1, latestnewsid);
			ResultSet rs = pstmt.executeQuery();
//System.out.println(rs.next());
//System.out.println("getRow:"+rs.getRow());
			try {
				if (rs.next()) {
//System.out.println("--rs.next()--");
//System.out.println(req.getParameter("photo1")); 
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("IMAGE"));
//System.out.println(req.getParameter("photo1").length());
					byte[] buf = new byte[4 * 1024];
					int len;
//System.out.println("--while--");
					while ((len = in.read(buf)) != -1) {
						outpho.write(buf, 0, len);
					}
					in.close();
				} else {
//					System.out.println("run here:--rs.next()_else--");
//					InputStream in = getServletContext().getResourceAsStream("/back-end/product/images/Pisuke.png");
//					byte[] b = new byte[in.available()];
//					in.read(b);
//					outpho.write(b);
//					in.close();
				}
				rs.close();
				pstmt.close();
				con.close();
			} catch (IOException e) {
//				e.printStackTrace();
//				System.out.println("run here:--IOException--");
				InputStream in = getServletContext().getResourceAsStream("/back-end/lat/images/non_default.png");
				byte[] b = new byte[in.available()];
				in.read(b);
				outpho.write(b);
				in.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
//			System.out.println("run here:--ClassNotFoundException--");
		} catch (SQLException e) {
//			System.out.println("run here:--SQLException--");
			InputStream in = getServletContext().getResourceAsStream("/back-end/lat/images/default_bear.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			outpho.write(b);
			in.close();
		}
	}
}
