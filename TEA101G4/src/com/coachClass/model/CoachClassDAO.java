//package com.coachClass.model;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import com.classDetail.model.ClassDetailVO;
//import com.classOrder.model.ClassOrderVO;
//
//public class CoachClassDAO implements CoachClassDAO_interface {
//
//	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
//			private static DataSource ds = null;
//			static {
//				try {
//					Context ctx = new InitialContext();
//					ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//				} catch (NamingException e) {
//					e.printStackTrace();
//				}
//			}
//	
//			private static final String INSERT_STMT = "INSERT INTO CoachClass (coachClassID, memberID, className, classContext, photo, startTime, price, quantity, address, addDate, editDate) "
//					+ "												   VALUES ('COC' || lpad(COACHCLASS_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//			private static final String GET_ALL_STMT = "SELECT coachClassID, memberID, className, classContext, photo, startTime, price, quantity, address, addDate, editDate FROM CoachClass";
//			private static final String GET_ONE_STMT = "SELECT coachClassID, memberID, className, classContext, photo, startTime, price, quantity, address, addDate, editDate FROM CoachClass where coachClassID = ?";
//			private static final String GET_ClassDetail_ByCoachClass_STMT = "SELECT classDetailID, classOrderID, coachClassID, quantity,classOrderID FROM ClassDetail where coachClassID = ? order by classDetailID";
//			
//			private static final String DELETE_ClassDetail = "DELETE FROM ClassDetail where coachClassID = ?";
//			private static final String DELETE_CoachClass = "DELETE FROM CoachClass where coachClassID = ?";	
//			
//			private static final String UPDATE = "UPDATE CoachClass set memberID=?, className=?, classContext=?, photo=?, startTime=?, price=?, quantity=?, address=?, addDate=?, editDate=?   where coachClassID = ?";
//			
//			
//	@Override
//	public void insert(CoachClassVO coachClassVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, coachClassVO.getMemberID());
//			pstmt.setString(2, coachClassVO.getClassName());
//			pstmt.setString(3, coachClassVO.getClassContext());
//			pstmt.setBytes(4, coachClassVO.getPhoto());
//			pstmt.setDate(5, coachClassVO.getStartTime());
//			pstmt.setInt(6, coachClassVO.getPrice());
//			pstmt.setInt(7, coachClassVO.getQuantity());
//			pstmt.setString(8, coachClassVO.getAddress());
//			pstmt.setDate(9, coachClassVO.getAddDate());
//			pstmt.setDate(10, coachClassVO.getEditDate());
//
//			pstmt.executeUpdate();
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
//
//
//	}
//
//	@Override
//	public void update(CoachClassVO coachClassVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, coachClassVO.getMemberID());
//			pstmt.setString(2, coachClassVO.getClassName());
//			pstmt.setString(3, coachClassVO.getClassContext());
//			pstmt.setBytes  (4, coachClassVO.getPhoto());
//			pstmt.setDate(5, coachClassVO.getStartTime());
//			pstmt.setInt  (6, coachClassVO.getPrice());
//			pstmt.setInt(7, coachClassVO.getQuantity());
//			pstmt.setString  (8, coachClassVO.getAddress());
//			pstmt.setDate  (9, coachClassVO.getAddDate());
//			pstmt.setDate  (10, coachClassVO.getEditDate());
//			pstmt.setString  (11, coachClassVO.getCoachClassID());
//
//			pstmt.executeUpdate();
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
//
//	}
//
//	@Override
//	public void delete(String coachClassID) {
//		int updateCount_CoachClass = 0;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//
//			// 1●設定於 pstm.executeUpdate()之前
//			con.setAutoCommit(false);
//
//			// 先刪除員工
//			pstmt = con.prepareStatement(DELETE_ClassDetail);
//			pstmt.setString(1, "CD00002");
//			updateCount_CoachClass = pstmt.executeUpdate();
//			// 再刪除部門
//			pstmt = con.prepareStatement(DELETE_CoachClass);
//			pstmt.setString(1, coachClassID);
//			pstmt.executeUpdate();
//
//			// 2●設定於 pstm.executeUpdate()之後
//			con.commit();
//			con.setAutoCommit(true);
//			System.out.println("刪除訂單編號" + coachClassID + "時,共有明細 " + updateCount_CoachClass
//					+ " 筆同時被刪除");
//			
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					// 3●設定於當有exception發生時之catch區塊內
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. "
//							+ excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
//
//
//	}
//
//	@Override
//	public CoachClassVO findByPrimaryKey(String coachClassID) {
//
//		CoachClassVO coachClassVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, coachClassID);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// classOrderVO 也稱為 Domain objects
//				coachClassVO = new CoachClassVO();
//				coachClassVO.setCoachClassID(rs.getString("coachClassID"));
//				coachClassVO.setMemberID(rs.getString("memberID"));
//				coachClassVO.setClassName(rs.getString("className"));
//				coachClassVO.setClassContext(rs.getString("classContext"));
//				coachClassVO.setPhoto(rs.getBytes("photo"));
//				coachClassVO.setStartTime(rs.getDate("startTime"));
//				coachClassVO.setPrice(rs.getInt("price"));
//				coachClassVO.setQuantity(rs.getInt("quantity"));
//				coachClassVO.setAddress(rs.getString("address"));
//				coachClassVO.setAddDate(rs.getDate("addDate"));
//				coachClassVO.setEditDate(rs.getDate("addDate"));
//			}
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
//		return coachClassVO;
//	}
//
//	@Override
//	public List<CoachClassVO> getAll() {
//
//		List<CoachClassVO> list = new ArrayList<CoachClassVO>();
//		CoachClassVO coachClassVO = null;
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
//				coachClassVO = new CoachClassVO();
//				coachClassVO.setCoachClassID(rs.getString("coachClassID"));
//				coachClassVO.setMemberID(rs.getString("memberID"));
//				coachClassVO.setClassName(rs.getString("className"));
//				coachClassVO.setClassContext(rs.getString("classContext"));
//				coachClassVO.setPhoto(rs.getBytes("photo"));
//				coachClassVO.setStartTime(rs.getDate("startTime"));
//				coachClassVO.setPrice(rs.getInt("price"));
//				coachClassVO.setQuantity(rs.getInt("quantity"));
//				coachClassVO.setAddress(rs.getString("address"));
//				coachClassVO.setAddDate(rs.getDate("addDate"));
//				coachClassVO.setEditDate(rs.getDate("addDate"));
//				list.add(coachClassVO);// Store the row in the list
//			}
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
//	public Set<ClassDetailVO> getClassDetailByCoachClassID(String coachClassID) {
//
//		Set<ClassDetailVO> set = new LinkedHashSet<ClassDetailVO>();
//		ClassDetailVO classDetailVO = null;
//	
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//	
//		try {
//	
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ClassDetail_ByCoachClass_STMT);
//			pstmt.setString(1, coachClassID);
//			rs = pstmt.executeQuery();
//	
//			while (rs.next()) {
//				classDetailVO = new ClassDetailVO();
//				classDetailVO.setClassDetailID(rs.getString("classDetailID"));
//				classDetailVO.setClassOrderID(rs.getString("classOrderID"));
//				classDetailVO.setCoachClassID(rs.getString("coachClassID"));
//				classDetailVO.setQuantity(rs.getInt("quantity"));
//				set.add(classDetailVO); // Store the row in the vector
//			}
//		
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
//		return set;
//	}
//
//}
