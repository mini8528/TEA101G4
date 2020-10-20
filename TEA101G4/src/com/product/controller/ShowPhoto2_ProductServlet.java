package com.product.controller;

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

@WebServlet("/back-end/product/productshow2.do")
public class ShowPhoto2_ProductServlet extends HttpServlet {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "TEA101G4";
	private static final String passwd = "123456";
	private static final String SHOW_PHOTO = "SELECT PHOTO2 FROM PRODUCT WHERE PRODUCTID = ?";

	Connection con = null;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			PreparedStatement pstmt = con.prepareStatement(SHOW_PHOTO);
			String productid = new String(req.getParameter("productid").trim());
			pstmt.setString(1, productid);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getBlob("PHOTO2") == null) {
//					System.out.println("PHOTO2=null");
					InputStream in = getServletContext().getResourceAsStream("/images/non_default.png");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}else {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("PHOTO2"));
					byte[] buf = new byte[8 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
					
				}
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			pstmt.close();
//			con.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(req.getParameter("productid").trim());
			InputStream in = getServletContext().getResourceAsStream("/images/non_default.png");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}