package com.orderdetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ordermaster.model.OrdermasterJDBCDAO;
import com.ordermaster.model.OrdermasterVO;

public class OrderdetailJDBCDAO implements OrderdetailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G4";
	String passwd = "123456";
//	String url = "jdbc:oracle:thin:@database-2.c25eusi5ih7r.ap-northeast-1.rds.amazonaws.com:1520:orcl";
//	String userid = "admin";
//	String passwd = "admin1234";

	private static final String INSERT_STMT = "INSERT INTO ORDERDETAIL (ORDERDETAILID,ORDERMASTERID,SPECID,"
			+ "QUANTITY) VALUES ('TL' || lpad(ORDERDETAIL_SEQ.NEXTVAL, 5, '0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT ORDERDETAILID,ORDERMASTERID,SPECID ,QUANTITY FROM ORDERDETAIL ORDER BY ORDERDETAILID";
	private static final String GET_ONE_STMT = "SELECT ORDERDETAILID,ORDERMASTERID,SPECID ,QUANTITY FROM ORDERDETAIL WHERE ORDERDETAILID = ?";
	private static final String GET_STMT_BY_FK = "SELECT ORDERDETAILID,ORDERMASTERID,SPECID ,QUANTITY FROM ORDERDETAIL WHERE ORDERMASTERID = ?";
	private static final String DELETE = "DELETE FROM ORDERDETAIL WHERE ORDERDETAILID = ?";
	private static final String UPDATE = "UPDATE ORDERDETAIL SET ORDERMASTERID=?,SPECID=?,"
			+ "QUANTITY=?  WHERE ORDERDETAILID = ?";
//------------------
	private static final String INSERT_STMT2 = "INSERT INTO ORDERDETAIL (ORDERDETAILID,ORDERMASTERID,SPECID,"
			+ "QUANTITY) VALUES ('TL' || lpad(ORDERDETAIL_SEQ.NEXTVAL, 5, '0'),?,?,?)";
	
	@Override
	public void insert(OrderdetailVO orderdetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, orderdetailVO.getOrdermasterid());
			pstmt.setString(2, orderdetailVO.getSpecid());
			pstmt.setInt(3, orderdetailVO.getQuantity());
			System.out.println(orderdetailVO.getOrdermasterid());
			System.out.println(orderdetailVO.getSpecid());
			System.out.println(orderdetailVO.getQuantity());
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
	public void update(OrderdetailVO orderdetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, orderdetailVO.getOrdermasterid());
			pstmt.setString(2, orderdetailVO.getSpecid());
			pstmt.setInt(3, orderdetailVO.getQuantity());
			pstmt.setString(4, orderdetailVO.getOrderdetailid());
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
	public void delete(String orderdetailid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, orderdetailid);
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
	public OrderdetailVO findbyPrimaryKey(String orderdetailid) {
		OrderdetailVO orderdetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, orderdetailid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderdetailVO = new OrderdetailVO();
				orderdetailVO.setOrderdetailid(rs.getString("orderdetailid"));
				orderdetailVO.setOrdermasterid(rs.getString("ordermasterid"));
				orderdetailVO.setSpecid(rs.getString("specid"));
				orderdetailVO.setQuantity(rs.getInt("quantity"));
			}
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
		return orderdetailVO;

	}

	@Override
	public List<OrderdetailVO> getAll() {
		List<OrderdetailVO> list = new ArrayList<OrderdetailVO>();
		OrderdetailVO orderdetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			System.out.println("OrderDETAIL_JDBDDAO_getAll_test");
			while (rs.next()) {
				orderdetailVO = new OrderdetailVO();
				orderdetailVO.setOrderdetailid(rs.getString("orderdetailid"));
				orderdetailVO.setOrdermasterid(rs.getString("ordermasterid"));
				orderdetailVO.setSpecid(rs.getString("specid"));
				orderdetailVO.setQuantity(rs.getInt("quantity"));
				list.add(orderdetailVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public List<OrderdetailVO> findbyFK(String ordermasterid) {
		List<OrderdetailVO> list = new ArrayList<OrderdetailVO>();
		OrderdetailVO orderdetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STMT_BY_FK);
			pstmt.setString(1, ordermasterid);
			rs = pstmt.executeQuery();
			System.out.println("OrderDETAIL_JDBDDAO_get_test_BY_FK");
			while (rs.next()) {
				orderdetailVO = new OrderdetailVO();
				orderdetailVO.setOrderdetailid(rs.getString("orderdetailid"));
				orderdetailVO.setOrdermasterid(rs.getString("ordermasterid"));
				orderdetailVO.setSpecid(rs.getString("specid"));
				orderdetailVO.setQuantity(rs.getInt("quantity"));
				list.add(orderdetailVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public void insert2(OrderdetailVO orderdetailVO, Connection con) {
		PreparedStatement pstmt = null;
		try {
     		pstmt = con.prepareStatement(INSERT_STMT2);
     		pstmt.setString(1, orderdetailVO.getOrdermasterid());
			pstmt.setString(2, orderdetailVO.getSpecid());
			pstmt.setInt(3, orderdetailVO.getQuantity());
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-OrderDeatil");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

}