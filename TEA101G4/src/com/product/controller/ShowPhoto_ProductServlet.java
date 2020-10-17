package com.product.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/back-end/product/productshow.do")
public class ShowPhoto_ProductServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 696609752947444363L;
	private static final String SHOW_PHOTO = "SELECT PHOTO1 FROM PRODUCT WHERE PRODUCTID = ?";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream outpho = res.getOutputStream();
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");

			if (ds != null) {

				Connection con = ds.getConnection();
				String productid = new String(req.getParameter("productid").trim());
				PreparedStatement pstmt = con.prepareStatement(SHOW_PHOTO);
				pstmt.setString(1, productid);
				ResultSet rs = pstmt.executeQuery();
				try {
					if (rs.next()) {
						if (rs.getBlob("PHOTO1") == null) {
							InputStream in = getServletContext()
									.getResourceAsStream("/back-end/product/images/non_default.png");
							byte[] b = new byte[in.available()];
							in.read(b);
							outpho.write(b);
							in.close();
						} else {
							BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("PHOTO1"));
							byte[] buf = new byte[4 * 1024];
							int len;
							while ((len = in.read(buf)) != -1) {
								outpho.write(buf, 0, len);
							}
							in.close();
						}
					} else {
						System.out.println("1run here:--rs.next()_else--");
						res.sendError(HttpServletResponse.SC_NOT_FOUND);
					}
					rs.close();
					pstmt.close();
					if (con != null)
						con.close();
				} catch (Exception e) {
//				e.printStackTrace();
					System.out.println("1.1run here:--IOException--");
					InputStream in = getServletContext()
							.getResourceAsStream("/back-end/product/images/non_default.png");
					byte[] b = new byte[in.available()];
					in.read(b);
					outpho.write(b);
					in.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}