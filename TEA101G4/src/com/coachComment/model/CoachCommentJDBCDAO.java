package com.coachComment.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

import com.coachComment.model.CoachCommentVO;

public class CoachCommentJDBCDAO implements CoachCommentDAO_interface {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "TEA101G4";
	private static final String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO CoachComment (coachCommentID,memberID,memberID2,commText,commStar,addDate,editDate,status) VALUES ('CC' || lpad(COACHCOMMENT_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?, ? )";
	private static final String GET_ALL_STMT = "SELECT coachCommentID,memberID,memberID2,commText,commStar,addDate,editDate,status FROM CoachComment order by coachCommentID";
	private static final String GET_ONE_STMT = "SELECT coachCommentID,memberID,memberID2,commText,commStar,addDate,editDate,status FROM CoachComment where coachCommentID = ?";
	private static final String GET_ONE_STMT_MEMBER = "SELECT coachCommentID,memberID,memberID2,commText,commStar,addDate,editDate,status FROM CoachComment where memberID = ?";
	
	private static final String DELETE = "DELETE FROM CoachComment where coachCommentID = ?";
	private static final String UPDATE = "UPDATE COACHCOMMENT set memberID=? ,memberID2=?, commText=?, commStar=? ,addDate=?, editDate=? ,status=?  where coachCommentID = ? ";

	private static final String UPDATE_SATAUS = "UPDATE CoachComment SET status=?,editDate=? WHERE coachCommentID = ?";
//	 memberID2=?

	@Override
	public void insert(CoachCommentVO coachCommentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, coachCommentVO.getMemberID());
			pstmt.setString(2, coachCommentVO.getMemberID2());
			pstmt.setString(3, coachCommentVO.getCommText());
			pstmt.setInt(4, coachCommentVO.getCommStar());
			pstmt.setTimestamp(5, coachCommentVO.getAddDate());
			pstmt.setTimestamp(6, coachCommentVO.getEditDate());
			pstmt.setString(7, coachCommentVO.getStatus());

			pstmt.executeUpdate();

			System.out.println("------------insert 成功--------");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(CoachCommentVO coachCommentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			System.out.println("=== JDBC_update start ===");

			pstmt.setString(1, coachCommentVO.getMemberID());
			pstmt.setString(2, coachCommentVO.getMemberID2());
			pstmt.setString(3, coachCommentVO.getCommText());
			pstmt.setInt(4, coachCommentVO.getCommStar());
			pstmt.setTimestamp(5, coachCommentVO.getAddDate());
			pstmt.setTimestamp(6, coachCommentVO.getEditDate());
			pstmt.setString(7, coachCommentVO.getStatus());
			pstmt.setString(8, coachCommentVO.getCoachCommentID());

			pstmt.executeUpdate();

			System.out.println("=== JDBC_update success ===");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(String coachCommentID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, coachCommentID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public CoachCommentVO findByPrimaryKey(String coachCommentID) {

		CoachCommentVO coachCommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, coachCommentID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				coachCommentVO = new CoachCommentVO();
				coachCommentVO.setCoachCommentID(rs.getString("coachCommentID"));
				coachCommentVO.setMemberID(rs.getString("memberID"));
				coachCommentVO.setMemberID2(rs.getString("memberID2"));
				coachCommentVO.setCommText(rs.getString("commText"));
				coachCommentVO.setCommStar(rs.getInt("commStar"));
				coachCommentVO.setAddDate(rs.getTimestamp("addDate"));
				coachCommentVO.setEditDate(rs.getTimestamp("editDate"));
				coachCommentVO.setStatus(rs.getString("status"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return coachCommentVO;
	}

	@Override
	public List<CoachCommentVO> getAll() {
		List<CoachCommentVO> list = new ArrayList<CoachCommentVO>();
		CoachCommentVO coachCommentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				coachCommentVO = new CoachCommentVO();
				coachCommentVO.setCoachCommentID(rs.getString("coachCommentID"));
				coachCommentVO.setMemberID(rs.getString("memberID"));
				coachCommentVO.setMemberID2(rs.getString("memberID2"));
				coachCommentVO.setCommText(rs.getString("commText"));
				coachCommentVO.setCommStar(rs.getInt("commStar"));
				coachCommentVO.setAddDate(rs.getTimestamp("addDate"));
				coachCommentVO.setEditDate(rs.getTimestamp("editDate"));
				coachCommentVO.setStatus(rs.getString("status"));
				list.add(coachCommentVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return list;
	}

	@Override
	public void update_status(CoachCommentVO coachCommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(UPDATE_SATAUS);

			pstmt.setString(1, coachCommentVO.getStatus());
			pstmt.setTimestamp(2, coachCommentVO.getEditDate());
			pstmt.setString(3, coachCommentVO.getCoachCommentID());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public CoachCommentVO findByMemberID(String memberID) {
		
		CoachCommentVO coachCommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_MEMBER);

			pstmt.setString(1, memberID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				coachCommentVO = new CoachCommentVO();
				coachCommentVO.setCoachCommentID(rs.getString("coachCommentID"));
				coachCommentVO.setMemberID(rs.getString("memberID"));
				coachCommentVO.setMemberID2(rs.getString("memberID2"));
				coachCommentVO.setCommText(rs.getString("commText"));
				coachCommentVO.setCommStar(rs.getInt("commStar"));
				coachCommentVO.setAddDate(rs.getTimestamp("addDate"));
				coachCommentVO.setEditDate(rs.getTimestamp("editDate"));
				coachCommentVO.setStatus(rs.getString("status"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return coachCommentVO;
	}
	
//	public static void main(String[] args) {
//
//		CoachCommentJDBCDAO dao = new CoachCommentJDBCDAO();

		// 新增
//		CoachCommentVO coachCommentVO1 = new CoachCommentVO();
//		coachCommentVO1.setMemberID("M001");
//		coachCommentVO1.setMemberID2("M002");
//		coachCommentVO1.setCommText("CommText");
//		coachCommentVO1.setCommStar(new Integer(5));
//		coachCommentVO1.setAddDate(new Timestamp(System.currentTimeMillis()));
//		coachCommentVO1.setEditDate(new Timestamp(System.currentTimeMillis()));
//		coachCommentVO1.setStatus("Y");
//		dao.insert(coachCommentVO1);

		// 修改
//		CoachCommentVO coachCommentVO2 = new CoachCommentVO();
//
//		coachCommentVO2.setCoachCommentID("CC00003");
//		coachCommentVO2.setMemberID("M001");
//		coachCommentVO2.setMemberID2("M002");
//		coachCommentVO2.setCommText("CommText999");
//		coachCommentVO2.setCommStar(new Integer(5));
//		coachCommentVO2.setAddDate(new Timestamp(System.currentTimeMillis()));
//		coachCommentVO2.setEditDate(new Timestamp(System.currentTimeMillis()));
//		coachCommentVO2.setStatus("Y");
//
//		dao.update(coachCommentVO2);

//		// 刪除
//		dao.delete("CC00002");
//		System.out.println("----刪除成功-----");

		// 查詢
//		CoachCommentVO coachCommentVO3 = dao.findByPrimaryKey("CC00003");
//		System.out.println("----------查詢-----------");
//		System.out.print(coachCommentVO3.getCoachCommentID() + ",");
//		System.out.print(coachCommentVO3.getMemberID() + ",");
//		System.out.print(coachCommentVO3.getMemberID2() + ",");
//		System.out.print(coachCommentVO3.getCommText() + ",");
//		System.out.print(coachCommentVO3.getCommStar() + ",");
//		System.out.print(coachCommentVO3.getAddDate() + ",");
//		System.out.print(coachCommentVO3.getEditDate() + ",");
//		System.out.println(coachCommentVO3.getStatus());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<CoachCommentVO> list = dao.getAll();
//		for (CoachCommentVO aEmp : list) {
//			System.out.print(aEmp.getCoachCommentID() + ",");
//			System.out.print(aEmp.getMemberID() + ",");
//			System.out.print(aEmp.getMemberID2() + ",");
//			System.out.print(aEmp.getCommText() + ",");
//			System.out.print(aEmp.getCommStar() + ",");
//			System.out.print(aEmp.getAddDate() + ",");
//			System.out.print(aEmp.getEditDate() + ",");
//			System.out.print(aEmp.getStatus() + ",");
//			System.out.println();
//		}
//	}

	
}
