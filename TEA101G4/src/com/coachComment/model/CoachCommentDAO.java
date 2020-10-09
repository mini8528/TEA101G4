//package com.coachComment.model;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//public class CoachCommentDAO implements CoachCommentDAO_interface {
//
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static final String INSERT_STMT = "INSERT INTO CoachComment (coachCommentID,memberID,memberID2,commText,commStar,addDate,editDate,status) VALUES ('CC' || lpad(COACHCOMMENT_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?, ? )";
//	private static final String GET_ALL_STMT = "SELECT coachCommentID,memberID,memberID2,commText,commStar,addDate,editDate,status FROM CoachComment order by coachCommentID";
//	private static final String GET_ONE_STMT = "SELECT coachCommentID,memberID,memberID2,commText,commStar,addDate,editDate,status FROM CoachComment where coachCommentID = ?";
//	private static final String DELETE = "DELETE FROM CoachComment where coachCommentID = ?";
//	private static final String UPDATE = "UPDATE COACHCOMMENT set memberID=? ,memberID2=?, commText=?, commStar=? , addDate=? ,editDate=? ,status=?  where coachCommentID = ? ";
//	private static final String UPDATE_SATAUS = "UPDATE CoachComment SET status=?,editDate=? WHERE coachCommentID = ?";
//
//	@Override
//	public void insert(CoachCommentVO coachCommentVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, coachCommentVO.getMemberID());
//			pstmt.setString(2, coachCommentVO.getMemberID2());
//			pstmt.setString(3, coachCommentVO.getCommText());
//			pstmt.setInt(4, coachCommentVO.getCommStar());
//			pstmt.setTimestamp(5, coachCommentVO.getAddDate());
//			pstmt.setTimestamp(6, coachCommentVO.getEditDate());
//			pstmt.setString(7, coachCommentVO.getStatus());
//
//			pstmt.executeUpdate();
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	@Override
//	public void update(CoachCommentVO coachCommentVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, coachCommentVO.getMemberID());
//			pstmt.setString(2, coachCommentVO.getMemberID2());
//			pstmt.setString(3, coachCommentVO.getCommText());
//			pstmt.setInt(4, coachCommentVO.getCommStar());
//			pstmt.setTimestamp(5, coachCommentVO.getAddDate());
//			pstmt.setTimestamp(6, coachCommentVO.getEditDate());
//			pstmt.setString(7, coachCommentVO.getStatus());
//			pstmt.setString(8, coachCommentVO.getCoachCommentID());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	@Override
//	public void delete(String coachCommentID) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, coachCommentID);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	@Override
//	public CoachCommentVO findByPrimaryKey(String coachCommentID) {
//		CoachCommentVO coachCommentVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, coachCommentID);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// coachCommentVO 也稱為 Domain objects
//				coachCommentVO = new CoachCommentVO();
//				coachCommentVO.setCoachCommentID(rs.getString("coachCommentID"));
//				coachCommentVO.setMemberID(rs.getString("memberID"));
//				coachCommentVO.setMemberID2(rs.getString("memberID2"));
//				coachCommentVO.setCommText(rs.getString("commText"));
//				coachCommentVO.setCommStar(rs.getInt("commStar"));
//				coachCommentVO.setAddDate(rs.getTimestamp("addDate"));
//				coachCommentVO.setEditDate(rs.getTimestamp("editDate"));
//				coachCommentVO.setStatus(rs.getString("status"));
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return coachCommentVO;
//	}
//
//	@Override
//	public List<CoachCommentVO> getAll() {
//		List<CoachCommentVO> list = new ArrayList<CoachCommentVO>();
//		CoachCommentVO coachCommentVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// coachCommentVO 也稱為 Domain objects
//				coachCommentVO = new CoachCommentVO();
//				coachCommentVO.setCoachCommentID(rs.getString("coachCommentID"));
//				coachCommentVO.setMemberID(rs.getString("memberID"));
//				coachCommentVO.setMemberID2(rs.getString("memberID2"));
//				coachCommentVO.setCommText(rs.getString("commText"));
//				coachCommentVO.setCommStar(rs.getInt("commStar"));
//				coachCommentVO.setAddDate(rs.getTimestamp("addDate"));
//				coachCommentVO.setEditDate(rs.getTimestamp("editDate"));
//				coachCommentVO.setStatus(rs.getString("status"));
//				list.add(coachCommentVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
//
//	@Override
//	public void update_status(CoachCommentVO coachCommentVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE_SATAUS);
//
//			pstmt.setString(1, coachCommentVO.getStatus());
//			pstmt.setTimestamp(2, coachCommentVO.getEditDate());
//			pstmt.setString(3, coachCommentVO.getCoachCommentID());
//
//			pstmt.executeUpdate();
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//}
