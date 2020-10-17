package com.product.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class PicServlet
 */
@WebServlet("/back-end/product/show.do")
public class show_ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");

			if (ds != null) {
				Connection con = ds.getConnection();

				Statement stmt = con.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT PHOTO1 FROM PRODUCT WHERE PRODUCTID = " + req.getParameter("PHOTO1"));

				if (rs.next()) {

					if (rs.getBlob("PHOTO1") == null) {
						InputStream in = getServletContext()
								.getResourceAsStream("/back-end/product/images/non_default.png");
						byte[] b = new byte[in.available()];
						in.read(b);
						out.write(b);
						in.close();
					} else {

						BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("PHOTO1"));
						byte[] buf = new byte[4 * 1024]; // 4K buffer
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
				stmt.close();
				if (con != null)
					con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {

	}

	public void destroy() {
	}

}
