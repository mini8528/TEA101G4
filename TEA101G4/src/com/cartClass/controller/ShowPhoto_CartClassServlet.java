package com.cartClass.controller;

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

@WebServlet("/front-end/cartClass/cartClassShow.do")
public class ShowPhoto_CartClassServlet extends HttpServlet {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "TEA101G4";
	private static final String passwd = "123456";
	
	private static final String SHOW_PHOTO = "SELECT PHOTO FROM coachClass WHERE coachClassID = ?";
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream outpho = res.getOutputStream();
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, userid, passwd);
			String coachClassID = new String(req.getParameter("coachClassID").trim());
			PreparedStatement pstmt = con.prepareStatement(SHOW_PHOTO);
			pstmt.setString(1, coachClassID);
			ResultSet rs = pstmt.executeQuery();
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
			} catch (IOException e) {
				InputStream in = getServletContext().getResourceAsStream("/back-end/coachClass/images/tomcat.png");
				byte[] b = new byte[in.available()];
				in.read(b);
				outpho.write(b);
				in.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			InputStream in = getServletContext().getResourceAsStream("/back-end/coachClass/images/tomcat.png");
			byte[] b = new byte[in.available()];
			in.read(b);
			outpho.write(b);
			in.close();
		}
	}
}