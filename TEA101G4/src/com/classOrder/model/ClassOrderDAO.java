package com.classOrder.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.classDetail.model.ClassDetailDAO;

import com.classDetail.model.ClassDetailVO;

public class ClassOrderDAO implements ClassOrderDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ClassOrder (classOrderID, memberID, payment, paymentStatus, payExpire, payCode, orderDate) "
			+ "												   VALUES ('CO' || lpad(CLASSORDER_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT classOrderID, memberID, payment, paymentStatus, payExpire, payCode, orderDate FROM ClassOrder order by classOrderID";
	private static final String GET_ONE_STMT = "SELECT classOrderID, memberID, payment, paymentStatus, payExpire, payCode, orderDate FROM ClassOrder where classOrderID = ?";
	private static final String GET_ClassDetail_ByClassOrder_STMT = "SELECT classDetailID, classOrderID, coachClassID, quantity,classOrderID FROM ClassDetail where classOrderID = ? order by classDetailID";

	private static final String DELETE_ClassDetail = "DELETE FROM ClassDetail where classOrderID = ?";
	private static final String DELETE_ClassOrder = "DELETE FROM ClassOrder where classOrderID = ?";

	private static final String UPDATE = "UPDATE ClassOrder set memberID=?, payment=?, paymentStatus=?, payExpire=?, payCode=?, orderDate=?  where classOrderID = ?";
	private static final String UPDATE_PAYSTATUS = "UPDATE ClassOrder SET paymentStatus=? WHERE classOrderID = ?";

	private static final String GET_LIST_BY_PAYMENTSTATUS = "SELECT * FROM ClassOrder WHERE paymentStatus = ? ORDER BY orderDate ASC";
	private static final String GET_LIST_BY_MEMBERID = "SELECT * FROM ClassOrder WHERE memberID = ? ORDER BY orderDate ASC";
	private static final String GET__BY_MEMBERID = "SELECT * FROM ClassOrder WHERE memberID = ? ";

	private static final String INSERT_STMT2 = "INSERT INTO ClassOrder (classOrderID, memberID, payment, paymentStatus, payExpire, payCode, orderDate) "
			+ "												   VALUES ('CO' || lpad(CLASSORDER_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?)";

	@Override
	public void insert(ClassOrderVO classOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, classOrderVO.getMemberID());
			pstmt.setString(2, classOrderVO.getPayment());
			pstmt.setString(3, classOrderVO.getPaymentStatus());
			pstmt.setDate(4, classOrderVO.getPayExpire());
			pstmt.setString(5, classOrderVO.getPayCode());
			pstmt.setTimestamp(6, classOrderVO.getOrderDate());
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void update(ClassOrderVO classOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, classOrderVO.getMemberID());
			pstmt.setString(2, classOrderVO.getPayment());
			pstmt.setString(3, classOrderVO.getPaymentStatus());
			pstmt.setDate(4, classOrderVO.getPayExpire());
			pstmt.setString(5, classOrderVO.getPayCode());
			pstmt.setTimestamp(6, classOrderVO.getOrderDate());
			pstmt.setString(7, classOrderVO.getClassOrderID());
			pstmt.executeUpdate();
			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void delete(String classOrderID) {

		int updateCount_ClassDetail = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除員工
			pstmt = con.prepareStatement(DELETE_ClassDetail);
			pstmt.setString(1, "CO00002");
			updateCount_ClassDetail = pstmt.executeUpdate();
			// 再刪除部門
			pstmt = con.prepareStatement(DELETE_ClassOrder);
			pstmt.setString(1, classOrderID);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);

			// Handle any driver errors
		}  catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public ClassOrderVO findByPrimaryKey(String classOrderID) {

		ClassOrderVO classOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, classOrderID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// classOrderVO 也稱為 Domain objects
				classOrderVO = new ClassOrderVO();
				classOrderVO.setClassOrderID(rs.getString("classOrderID"));
				classOrderVO.setMemberID(rs.getString("memberID"));
				classOrderVO.setPayment(rs.getString("payment"));
				classOrderVO.setPaymentStatus(rs.getString("paymentStatus"));
				classOrderVO.setPayExpire(rs.getDate("payExpire"));
				classOrderVO.setPayCode(rs.getString("payCode"));
				classOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
		return classOrderVO;
	}

	@Override
	public List<ClassOrderVO> getAll() {

		List<ClassOrderVO> list = new ArrayList<ClassOrderVO>();
		ClassOrderVO classOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				classOrderVO = new ClassOrderVO();
				classOrderVO.setClassOrderID(rs.getString("classOrderID"));
				classOrderVO.setMemberID(rs.getString("memberID"));
				classOrderVO.setPayment(rs.getString("payment"));
				classOrderVO.setPaymentStatus(rs.getString("paymentStatus"));
				classOrderVO.setPayExpire(rs.getDate("payExpire"));
				classOrderVO.setPayCode(rs.getString("payCode"));
				classOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
				list.add(classOrderVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update_paymentStatus(ClassOrderVO classOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE_PAYSTATUS);

			pstmt.setString(1, classOrderVO.getPaymentStatus());
			pstmt.setTimestamp(2, classOrderVO.getOrderDate());

			pstmt.executeUpdate();
		}  catch (SQLException se) {
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
	public List<ClassOrderVO> getSomeByMemberId(String memberID) {
		List<ClassOrderVO> list = new ArrayList<ClassOrderVO>();
		ClassOrderVO classOrderVO = new ClassOrderVO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LIST_BY_MEMBERID);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				classOrderVO = new ClassOrderVO();
				classOrderVO.setClassOrderID(rs.getString("classOrderID"));
				classOrderVO.setMemberID(rs.getString("memberID"));
				classOrderVO.setPayment(rs.getString("payment"));
				classOrderVO.setPaymentStatus(rs.getString("paymentStatus"));
				classOrderVO.setPayExpire(rs.getDate("payExpire"));
				classOrderVO.setPayCode(rs.getString("payCode"));
				classOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
				list.add(classOrderVO);
			}
		}  catch (SQLException e) {
			throw new RuntimeException("A DataBase Error Occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	public List<ClassOrderVO> getSomeByPaymentStatus(String paymentStatus) {
		List<ClassOrderVO> list = new ArrayList<ClassOrderVO>();
		ClassOrderVO classOrderVO = new ClassOrderVO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LIST_BY_PAYMENTSTATUS);
			pstmt.setString(1, paymentStatus);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				classOrderVO = new ClassOrderVO();
				classOrderVO.setClassOrderID(rs.getString("classOrderID"));
				classOrderVO.setMemberID(rs.getString("memberID"));
				classOrderVO.setPayment(rs.getString("payment"));
				classOrderVO.setPaymentStatus(rs.getString("paymentStatus"));
				classOrderVO.setPayExpire(rs.getDate("payExpire"));
				classOrderVO.setPayCode(rs.getString("payCode"));
				classOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
				list.add(classOrderVO);
			}
		}  catch (SQLException e) {
			throw new RuntimeException("A DataBase Error Occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	public void insertWithClassOrder(ClassOrderVO classOrderVO, List<ClassDetailVO> testList) {

		System.out.println("DAO _insertWithClassOrder __ in");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			// 先新增商品
			String cols[] = { "ClassOrderID" };
			System.out.println("DAO _cols[] ="+cols);
			
			pstmt = con.prepareStatement(INSERT_STMT2, cols);

			pstmt.setString(1, classOrderVO.getMemberID());
			pstmt.setString(2, classOrderVO.getPayment());
			pstmt.setString(3, classOrderVO.getPaymentStatus());
			pstmt.setDate(4, classOrderVO.getPayExpire());
			pstmt.setString(5, classOrderVO.getPayCode());
			pstmt.setTimestamp(6, classOrderVO.getOrderDate());
			
			System.out.println("DAO _classOrderVO.getClassOrderID() ="+classOrderVO.getClassOrderID());
			System.out.println("DAO _classOrderVO.getMemberID() ="+classOrderVO.getMemberID());
			System.out.println("DAO _classOrderVO.getPayment() ="+classOrderVO.getPayment());
			System.out.println("DAO _classOrderVO.getPaymentStatus() ="+classOrderVO.getPaymentStatus());
			System.out.println("DAO _classOrderVO.getPayExpire() ="+classOrderVO.getPayExpire());
			System.out.println("DAO _classOrderVO.getPayCode() ="+classOrderVO.getPayCode());
			System.out.println("DAO _classOrderVO.getOrderDate() ="+classOrderVO.getOrderDate());
			try {
			pstmt.executeUpdate();
			System.out.println("DAO _executeUpdate __ end");
			}catch(Exception e) {
				System.out.println("000088888");
			}
			// 掘取對應的自增主鍵值
			String next_classOrderID = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_classOrderID = rs.getString(1);
				System.out.println("自增主鍵值 = "+next_classOrderID);
			} else {
			}
			rs.close();

			// 再同時新增明細
			ClassDetailDAO dao = new ClassDetailDAO();
			for (ClassDetailVO aClassDetail : testList) {
				aClassDetail.setClassOrderID(new String(next_classOrderID));
				dao.insert2(aClassDetail, con);
			}

		}  catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-orderMaster");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
	public ClassOrderVO getOrderVOByMemberId(String memberId) {

		ClassOrderVO classOrderVO = new ClassOrderVO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET__BY_MEMBERID);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				classOrderVO = new ClassOrderVO();
				classOrderVO.setClassOrderID(rs.getString("classOrderID"));
				classOrderVO.setMemberID(rs.getString("memberID"));
				classOrderVO.setPayment(rs.getString("payment"));
				classOrderVO.setPaymentStatus(rs.getString("paymentStatus"));
				classOrderVO.setPayExpire(rs.getDate("payExpire"));
				classOrderVO.setPayCode(rs.getString("payCode"));
				classOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
			}
		}  catch (SQLException e) {
			throw new RuntimeException("A DataBase Error Occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
		return classOrderVO;
	
	}


}
