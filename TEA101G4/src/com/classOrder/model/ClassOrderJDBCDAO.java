//package com.classOrder.model;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Set;
//
//import com.classDetail.model.ClassDetailJDBCDAO;
//import com.classDetail.model.ClassDetailVO;
//
//public class ClassOrderJDBCDAO implements ClassOrderDAO_interface {
//
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "TEA101G4";
//	String passwd = "123456";
//
//	private static final String INSERT_STMT = "INSERT INTO ClassOrder (classOrderID, memberID, payment, paymentStatus, payExpire, payCode, orderDate) "
//			+ "												   VALUES ('CO' || lpad(CLASSORDER_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = "SELECT classOrderID, memberID, payment, paymentStatus, payExpire, payCode, orderDate FROM ClassOrder order by classOrderID";
//	private static final String GET_ONE_STMT = "SELECT classOrderID, memberID, payment, paymentStatus, payExpire, payCode, orderDate FROM ClassOrder where classOrderID = ?";
//	private static final String GET_ClassDetail_ByClassOrder_STMT = "SELECT classDetailID, classOrderID, coachClassID, quantity,classOrderID FROM ClassDetail where classOrderID = ? order by classDetailID";
//
//	private static final String DELETE_ClassDetail = "DELETE FROM ClassDetail where classOrderID = ?";
//	private static final String DELETE_ClassOrder = "DELETE FROM ClassOrder where classOrderID = ?";
//
//	private static final String UPDATE = "UPDATE ClassOrder set memberID=?, payment=?, paymentStatus=?, payExpire=?, payCode=?, orderDate=?  where classOrderID = ?";
//	private static final String UPDATE_PAYSTATUS = "UPDATE ClassOrder SET paymentStatus=? WHERE classOrderID = ?";
//
//	private static final String GET_LIST_BY_PAYMENTSTATUS = "SELECT * FROM ClassOrder WHERE paymentStatus = ? ORDER BY orderDate ASC";
//	private static final String GET_LIST_BY_MEMBERID = "SELECT * FROM ClassOrder WHERE memberID = ? ORDER BY orderDate ASC";
//
//	private static final String INSERT_STMT2 = "INSERT INTO ClassOrder (classOrderID, memberID, payment, paymentStatus, payExpire, payCode, orderDate) "
//			+ "												   VALUES ('CO' || lpad(CLASSORDER_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?)";
//
//	@Override
//	public void insert(ClassOrderVO classOrderVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, classOrderVO.getMemberID());
//			pstmt.setString(2, classOrderVO.getPayment());
//			pstmt.setString(3, classOrderVO.getPaymentStatus());
//			pstmt.setDate(4, classOrderVO.getPayExpire());
//			pstmt.setString(5, classOrderVO.getPayCode());
//			pstmt.setTimestamp(6, classOrderVO.getOrderDate());
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
//	public void update(ClassOrderVO classOrderVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//			pstmt.setString(1, classOrderVO.getMemberID());
//			pstmt.setString(2, classOrderVO.getPayment());
//			pstmt.setString(3, classOrderVO.getPaymentStatus());
//			pstmt.setDate(4, classOrderVO.getPayExpire());
//			pstmt.setString(5, classOrderVO.getPayCode());
//			pstmt.setTimestamp(6, classOrderVO.getOrderDate());
//			pstmt.setString(7, classOrderVO.getClassOrderID());
//			pstmt.executeUpdate();
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//
//			// 1��身摰 pstm.executeUpdate()銋��
//			con.setAutoCommit(false);
//
//			// ����撌�
//			pstmt = con.prepareStatement(DELETE_ClassDetail);
//			pstmt.setString(1, "CO00002");
//			updateCount_ClassDetail = pstmt.executeUpdate();
//			// ������
//			pstmt = con.prepareStatement(DELETE_ClassOrder);
//			pstmt.setString(1, classOrderID);
//			pstmt.executeUpdate();
//
//			con.commit();
//			con.setAutoCommit(true);
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. " + excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//	public ClassOrderVO findByPrimaryKey(String classOrderID) {
//
//		ClassOrderVO classOrderVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, classOrderID);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// classOrderVO 銋迂� Domain objects
//				classOrderVO = new ClassOrderVO();
//				classOrderVO.setClassOrderID(rs.getString("classOrderID"));
//				classOrderVO.setMemberID(rs.getString("memberID"));
//				classOrderVO.setPayment(rs.getString("payment"));
//				classOrderVO.setPaymentStatus(rs.getString("paymentStatus"));
//				classOrderVO.setPayExpire(rs.getDate("payExpire"));
//				classOrderVO.setPayCode(rs.getString("payCode"));
//				classOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
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
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				classOrderVO = new ClassOrderVO();
//				classOrderVO.setClassOrderID(rs.getString("classOrderID"));
//				classOrderVO.setMemberID(rs.getString("memberID"));
//				classOrderVO.setPayment(rs.getString("payment"));
//				classOrderVO.setPaymentStatus(rs.getString("paymentStatus"));
//				classOrderVO.setPayExpire(rs.getDate("payExpire"));
//				classOrderVO.setPayCode(rs.getString("payCode"));
//				classOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
//				list.add(classOrderVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//	public void update_paymentStatus(ClassOrderVO classOrderVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//
//			pstmt = con.prepareStatement(UPDATE_PAYSTATUS);
//
//			pstmt.setString(1, classOrderVO.getPaymentStatus());
//			pstmt.setTimestamp(2, classOrderVO.getOrderDate());
//
//			pstmt.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
//	public List<ClassOrderVO> getSomeByMemberId(String memberID) {
//		List<ClassOrderVO> list = new ArrayList<ClassOrderVO>();
//		ClassOrderVO classOrderVO = new ClassOrderVO();
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_LIST_BY_MEMBERID);
//			pstmt.setString(1, memberID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				classOrderVO = new ClassOrderVO();
//				classOrderVO.setClassOrderID(rs.getString("classOrderID"));
//				classOrderVO.setMemberID(rs.getString("memberID"));
//				classOrderVO.setPayment(rs.getString("payment"));
//				classOrderVO.setPaymentStatus(rs.getString("paymentStatus"));
//				classOrderVO.setPayExpire(rs.getDate("payExpire"));
//				classOrderVO.setPayCode(rs.getString("payCode"));
//				classOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
//				list.add(classOrderVO);
//			}
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't Load DataBase Driver. " + e.getMessage());
//		} catch (SQLException e) {
//			throw new RuntimeException("A DataBase Error Occured. " + e.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
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
//	public List<ClassOrderVO> getSomeByPaymentStatus(String paymentStatus) {
//		List<ClassOrderVO> list = new ArrayList<ClassOrderVO>();
//		ClassOrderVO classOrderVO = new ClassOrderVO();
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_LIST_BY_PAYMENTSTATUS);
//			pstmt.setString(1, paymentStatus);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				classOrderVO = new ClassOrderVO();
//				classOrderVO.setClassOrderID(rs.getString("classOrderID"));
//				classOrderVO.setMemberID(rs.getString("memberID"));
//				classOrderVO.setPayment(rs.getString("payment"));
//				classOrderVO.setPaymentStatus(rs.getString("paymentStatus"));
//				classOrderVO.setPayExpire(rs.getDate("payExpire"));
//				classOrderVO.setPayCode(rs.getString("payCode"));
//				classOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
//				list.add(classOrderVO);
//			}
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't Load DataBase Driver. " + e.getMessage());
//		} catch (SQLException e) {
//			throw new RuntimeException("A DataBase Error Occured. " + e.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
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
//	public void insertWithClassOrder(ClassOrderVO classOrderVO, List<ClassDetailVO> testList) {
//
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			con.setAutoCommit(false);
//			String cols[] = { "ClassOrderID" };
//			System.out.println("cols[]  ="+ cols);
//			pstmt = con.prepareStatement(INSERT_STMT2, cols);
//
//			pstmt.setString(1, classOrderVO.getMemberID());
//			pstmt.setString(2, classOrderVO.getPayment());
//			pstmt.setString(3, classOrderVO.getPaymentStatus());
//			pstmt.setDate(4, classOrderVO.getPayExpire());
//			pstmt.setString(5, classOrderVO.getPayCode());
//			pstmt.setTimestamp(6, classOrderVO.getOrderDate());
//			
//			System.out.println("JDBC _getMemberID ="+classOrderVO.getMemberID());
//			System.out.println("JDBC _getPayment ="+classOrderVO.getPayment());
//			System.out.println("JDBC _getPaymentStatus ="+classOrderVO.getPaymentStatus());
//			System.out.println("JDBC _getPayExpire ="+classOrderVO.getPayExpire());
//			System.out.println("JDBC _getPayCode ="+classOrderVO.getPayCode());
//			System.out.println("JDBC _getOrderDate ="+classOrderVO.getOrderDate());
//			
//			
//			pstmt.executeUpdate();
//			System.out.println("JDBC _執行成功");
//			
//			
//			String next_classOrderID = null;
//			ResultSet rs = pstmt.getGeneratedKeys();
//			if (rs.next()) {
//				next_classOrderID = rs.getString(1);
//			} else {
//			}
//			rs.close();
//
//			ClassDetailJDBCDAO dao = new ClassDetailJDBCDAO();
//			for (ClassDetailVO aClassDetail : testList) {
//				aClassDetail.setClassOrderID(new String(next_classOrderID));
//				dao.insert2(aClassDetail, con);
//			}
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					System.err.print("Transaction is being ");
//					System.err.println("rolled back-�-orderMaster");
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. " + excep.getMessage());
//				}
//			}
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
//	
//	
//	public static void main(String[] args) {
//
//		ClassOrderJDBCDAO dao = new ClassOrderJDBCDAO();
//		
//		ClassOrderVO classOrderVO = new ClassOrderVO();
//		classOrderVO.setMemberID("M005");
//		classOrderVO.setPayment("信用卡");
//		classOrderVO.setPaymentStatus("Y");
//		classOrderVO.setPayExpire(new Date(System.currentTimeMillis()));
//		classOrderVO.setPayCode("DDDT");
//		classOrderVO.setOrderDate(new Timestamp(System.currentTimeMillis()));
//		dao.insertWithClassOrder(classOrderVO , testList);
//		
//		// 新增
////		ClassOrderVO classOrderVO1 = new ClassOrderVO();
////		classOrderVO1.setMemberID("M005");
////		classOrderVO1.setPayment("信用卡");
////		classOrderVO1.setPaymentStatus("Y");
////		classOrderVO1.setPayExpire(new Date(System.currentTimeMillis()));
////		classOrderVO1.setPayCode("DDDT");
////		classOrderVO1.setOrderDate(new Timestamp(System.currentTimeMillis()));
////		dao.insert(classOrderVO1);
//
//		// 修改
////		ClassOrderVO classOrderVO2 = new ClassOrderVO();
////		classOrderVO2.setClassOrderID("CO00006");
////		classOrderVO2.setMemberID("M003");
////		classOrderVO2.setPayment("credit");
////		classOrderVO2.setPaymentStatus("N");
////		classOrderVO2.setPayExpire(new Date(System.currentTimeMillis()));
////		classOrderVO2.setPayCode("DTT");
////		classOrderVO2.setOrderDate(new Date(System.currentTimeMillis()));
////		dao.update(classOrderVO2);
//
//		// 刪除
////		dao.delete("CO00006");
//
//		// 查詢
////		ClassOrderVO classOrderVO3 = dao.findByPrimaryKey("CO00052");
////		System.out.print(classOrderVO3.getClassOrderID() + ",");
////		System.out.print(classOrderVO3.getMemberID() + ",");
////		System.out.print(classOrderVO3.getPayment() + ",");
////		System.out.print(classOrderVO3.getPaymentStatus() + ",");
////		System.out.print(classOrderVO3.getPayExpire() + ",");
////		System.out.print(classOrderVO3.getPayCode() + ",");
////		System.out.print(classOrderVO3.getOrderDate());
////		System.out.println();
////		System.out.println("---------------------");
//
//		// 查詢部門
////		List<ClassOrderVO> list = dao.getAll();
////		for (ClassOrderVO aClassOrder : list) {
////			System.out.print(aClassOrder.getClassOrderID() + ",");
////			System.out.print(aClassOrder.getMemberID() + ",");
////			System.out.print(aClassOrder.getPayment() + ",");
////			System.out.print(aClassOrder.getPaymentStatus() + ",");
////			System.out.print(aClassOrder.getPayExpire() + ",");
////			System.out.print(aClassOrder.getPayCode() + ",");
////			System.out.print(aClassOrder.getOrderDate());
////			System.out.println();
////		}
//
//		// 查詢某部門的員工
////		Set<ClassDetailVO> set = dao.getClassDetailByClassOrderID("CO00001");
////		for (ClassDetailVO aClassDetail : set) {
////			System.out.print(aClassDetail.getClassDetailID() + ",");
////			System.out.print(aClassDetail.getClassOrderID() + ",");
////			System.out.print(aClassDetail.getCoachClassID() + ",");
////			System.out.print(aClassDetail.getQuantity() );
////			System.out.println();
////		}
//	}
//
//}
