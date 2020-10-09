//package com.classOrder.model;
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
//
//public class ClassOrderDAO implements ClassOrderDAO_interface {
//
//	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
//		private static DataSource ds = null;
//		static {
//			try {
//				Context ctx = new InitialContext();
//				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//		}
//
//		private static final String INSERT_STMT = "INSERT INTO ClassOrder (classOrderID, memberID, payment, paymentStatus, payExpire, payCode, orderDate) "
//				+ "												   VALUES ('CO' || lpad(CLASSORDER_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?)";
//		private static final String GET_ALL_STMT = "SELECT classOrderID, memberID, payment, paymentStatus, payExpire, payCode, orderDate FROM ClassOrder";
//		private static final String GET_ONE_STMT = "SELECT classOrderID, memberID, payment, paymentStatus, payExpire, payCode, orderDate FROM ClassOrder where classOrderID = ?";
//		private static final String GET_ClassDetail_ByClassOrder_STMT = "SELECT classDetailID, classOrderID, coachClassID, quantity,classOrderID FROM ClassDetail where classOrderID = ? order by classDetailID";
//		
//		private static final String DELETE_ClassDetail = "DELETE FROM ClassDetail where classOrderID = ?";
//		private static final String DELETE_ClassOrder = "DELETE FROM ClassOrder where classOrderID = ?";	
//		
//		private static final String UPDATE = "UPDATE ClassOrder set memberID=?, payment=?, paymentStatus=?, payExpire=?, payCode=?, orderDate=?  where classOrderID = ?";
//		
//
//	@Override
//	public void insert(ClassOrderVO classOrderVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, classOrderVO.getMemberID());
//			pstmt.setString(2, classOrderVO.getPayment());
//			pstmt.setString(3, classOrderVO.getPaymentStatus());
//			pstmt.setDate(4, classOrderVO.getPayExpire());
//			pstmt.setString(5, classOrderVO.getPayCode());
//			pstmt.setDate(6, classOrderVO.getOrderDate());
//
//
//			pstmt.executeUpdate();
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured.(DAO_insert) "
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
//	public void update(ClassOrderVO classOrderVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, classOrderVO.getMemberID());
//			pstmt.setString(2, classOrderVO.getPayment());
//			pstmt.setString(3, classOrderVO.getPaymentStatus());
//			pstmt.setDate  (4, classOrderVO.getPayExpire());
//			pstmt.setString(5, classOrderVO.getPayCode());
//			pstmt.setDate  (6, classOrderVO.getOrderDate());
//			pstmt.setString(7, classOrderVO.getClassOrderID());
//
//			pstmt.executeUpdate();
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. (DAO_update)"
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
//	public void delete(String classOrderID) {
//		
//		int updateCount_ClassDetail = 0;
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
//			pstmt.setString(1, "CO00002");
//			updateCount_ClassDetail = pstmt.executeUpdate();
//			// 再刪除部門
//			pstmt = con.prepareStatement(DELETE_ClassOrder);
//			pstmt.setString(1, classOrderID);
//			pstmt.executeUpdate();
//
//			// 2●設定於 pstm.executeUpdate()之後
//			con.commit();
//			con.setAutoCommit(true);
//			System.out.println("刪除訂單編號" + classOrderID + "時,共有明細 " + updateCount_ClassDetail
//					+ " 筆同時被刪除");
//			
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					// 3●設定於當有exception發生時之catch區塊內
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. (DAO_delete)"
//							+ excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured. (DAO_delete)"
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
//	public ClassOrderVO findByPrimaryKey(String classOrderID) {
//		
//		ClassOrderVO classOrderVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//			
//			pstmt.setString(1, classOrderID);
//System.out.println("b_3");
//			rs = pstmt.executeQuery();
//System.out.println("c");
//			while (rs.next()) {
//				System.out.println("d");
//				// classOrderVO 也稱為 Domain objects
//				classOrderVO = new ClassOrderVO();
//				
//				classOrderVO.setClassOrderID(rs.getString("classOrderID"));
//				classOrderVO.setMemberID(rs.getString("memberID"));
//				classOrderVO.setPayment(rs.getString("payment"));
//				classOrderVO.setPaymentStatus(rs.getString("paymentStatus"));
//				classOrderVO.setPayExpire(rs.getDate("payExpire"));
//				classOrderVO.setPayCode(rs.getString("payCode"));
//				classOrderVO.setOrderDate(rs.getDate("orderDate"));
//			}
//			System.out.println("e");
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. (DAO_findByPrimaryKey)"
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
//		return classOrderVO;
//	}
//
//	@Override
//	public List<ClassOrderVO> getAll() {
//		
//		List<ClassOrderVO> list = new ArrayList<ClassOrderVO>();
//		ClassOrderVO classOrderVO = null;
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
//				
//				classOrderVO = new ClassOrderVO();
//				
//				classOrderVO.setClassOrderID(rs.getString("classOrderID"));
//				classOrderVO.setMemberID(rs.getString("memberID"));
//				classOrderVO.setPayment(rs.getString("payment"));
//				classOrderVO.setPaymentStatus(rs.getString("paymentStatus"));
//				classOrderVO.setPayExpire(rs.getDate("payExpire"));
//				classOrderVO.setPayCode(rs.getString("payCode"));
//				classOrderVO.setOrderDate(rs.getDate("orderDate"));
//				
//				list.add(classOrderVO); // Store the row in the list
//			}
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. (DAO_getAll)"
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
//	public Set<ClassDetailVO> getClassDetailByClassOrderID(String classOrderID) {
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
//			pstmt = con.prepareStatement(GET_ClassDetail_ByClassOrder_STMT);
//			pstmt.setString(1, classOrderID);
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
//			throw new RuntimeException("A database error occured. (DAO_getClassDetailByClassOrderID)"
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
//}
