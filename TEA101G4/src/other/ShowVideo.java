package other;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
@WebServlet("/blog/ShowVideo")
public class ShowVideo extends HttpServlet {
	
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "TEA101G4";
	private static final String passwd = "123456";
	private static final String GET_VIDEO = "SELECT VIDEO FROM BLOG WHERE BLOGNO = ?";


	Connection con = null;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("video/mp4");
		ServletOutputStream out = res.getOutputStream();

		try {
			PreparedStatement pstmt = con.prepareStatement(GET_VIDEO);
			String blogno = req.getParameter("blogno").trim();
			pstmt.setString(1, blogno);
			ResultSet rs = pstmt.executeQuery();
			

			if (rs.next()) {
//				InputStream in = rs.getBinaryStream("PIC");
//				InputStream in = new BufferedInputStream(rs.getBinaryStream("PIC"));
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("VIDEO"));
				byte[] buf = new byte[8 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			pstmt.close();
//			con.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(req.getParameter("blogno").trim());
		}
	}

	public void init() throws ServletException {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
//		try {
//			Context ctx = new javax.naming.InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//			con = ds.getConnection();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
