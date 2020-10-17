package com.cus.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class CusJDBCDAO implements Cus_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "TEA101G4";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = "INSERT INTO CUSTOMERSERVICE (customerserviceid,memberid, subject,email, problemtext,complaintdate)"
																	+"VALUES ('LD'|| lpad(CUSTOMERSERVICE_SEQ.NEXTVAL, 4, '0'),?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE CUSTOMERSERVICE set adminid = ?, replytext = ?, replydate = ? WHERE customerserviceid = ?";
	private static final String DELETE_STMT = "DELETE FROM CUSTOMERSERVICE WHERE customerserviceid = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CUSTOMERSERVICE WHERE customerserviceid = ?";
	private static final String GET_ALL = "SELECT * FROM CUSTOMERSERVICE";
	private static final String GET_STMT_BY_FK = "SELECT CUSTOMERSERVICEID,memberid, adminid, subject,email, problemtext,complaintdate, replytext,replydate FROM CUSTOMERSERVICE WHERE MEMBERID LIKE ?";
	@Override
	public void insert(CusVO cusVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			 
//			pstmt.setString(1, cusVO.getCustomerserviceid());
			pstmt.setString(1, cusVO.getMemberid());
//			pstmt.setString(2, cusVO.getAdminid());
			pstmt.setString(2, cusVO.getSubject());
			pstmt.setString(3, cusVO.getEmail());
			pstmt.setString(4, cusVO.getProblemtext());
			pstmt.setDate(5, cusVO.getComplaintdate());
//			pstmt.setString(7, cusVO.getReplytext());
//			pstmt.setDate(8, cusVO.getReplydate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(CusVO cusVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
//			pstmt.setString(1, cusVO.getMemberid());
			pstmt.setString(1, cusVO.getAdminid());
//			pstmt.setString(2, cusVO.getSubject());
//			pstmt.setString(3, cusVO.getEmail());
//			pstmt.setString(2, cusVO.getProblemtext());
//			pstmt.setDate(2, cusVO.getComplaintdate());
			pstmt.setString(2, cusVO.getReplytext());
			pstmt.setDate(3, cusVO.getReplydate());
			pstmt.setString(4, cusVO.getCustomerserviceid());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String latestnewsid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, latestnewsid);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public CusVO findByPrimaryKey(String customerserviceid) {
		CusVO cus = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, customerserviceid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cus = new CusVO();
				cus.setCustomerserviceid(rs.getString("customerserviceid"));
				cus.setMemberid(rs.getString("memberid"));
				cus.setAdminid(rs.getString("adminid"));
				cus.setSubject(rs.getString("subject"));
				cus.setEmail(rs.getString("email"));
				cus.setProblemtext(rs.getString("problemtext"));
				cus.setComplaintdate(rs.getDate("complaintdate"));
				cus.setReplytext(rs.getString("replytext"));
				cus.setReplydate(rs.getDate("replydate"));
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return cus;
	}

	@Override
	public List<CusVO> getAll() {
		List<CusVO> cusList = new ArrayList<>();
		CusVO cus = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cus = new CusVO();
				cus.setCustomerserviceid(rs.getString("customerserviceid"));
				cus.setMemberid(rs.getString("memberid"));
				cus.setAdminid(rs.getString("adminid"));
				cus.setSubject(rs.getString("subject"));
				cus.setEmail(rs.getString("email"));
				cus.setProblemtext(rs.getString("problemtext"));
				cus.setComplaintdate(rs.getDate("complaintdate"));
				cus.setReplytext(rs.getString("replytext"));
				cus.setReplydate(rs.getDate("replydate"));
				cusList.add(cus);
				
				
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return cusList;
	}

	@Override
	public List<CusVO> getCusByMid(String memberid) {
		List<CusVO> list = new ArrayList<CusVO>();
		CusVO cusVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_STMT_BY_FK);
			pstmt.setString(1, "%"+memberid+"%");
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				cusVO= new CusVO();
				cusVO.setCustomerserviceid(rs.getString("customerserviceid"));
				cusVO.setMemberid(rs.getString("memberid"));
				cusVO.setAdminid(rs.getString("adminid"));
				cusVO.setSubject(rs.getString("subject"));
				cusVO.setEmail(rs.getString("email"));
				cusVO.setProblemtext(rs.getString("problemtext"));
				cusVO.setComplaintdate(rs.getDate("complaintdate"));
				cusVO.setReplytext(rs.getString("replytext"));
				cusVO.setReplydate(rs.getDate("replydate"));
		
				list.add(cusVO);
			}
//			else {
//				System.out.println("�w�w");
//			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
