package com.ordermaster.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orderdetail.model.OrderdetailJDBCDAO;
import com.orderdetail.model.OrderdetailVO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdermasterDAO implements OrdermasterDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ORDERMASTER (ORDERMASTERID,MEMBERID,PAYMENT,PAYSTATUS,"
			+ "ORDERDATE,PAYEXPIRE,PAYCODE,RECEIVER,TEL,ADDRESS,ORDERSTATUS) "
			+ "VALUES ('OR' || lpad(ORDERMASTER_SEQ.NEXTVAL, 5, '0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT ORDERMASTERID,MEMBERID,PAYMENT,PAYSTATUS,"
			+ "ORDERDATE,PAYEXPIRE,PAYCODE,RECEIVER,TEL,ADDRESS,ORDERSTATUS "
			+ "FROM ORDERMASTER ORDER BY ORDERDATE DESC";
	private static final String GET_ONE_STMT = "SELECT ORDERMASTERID,MEMBERID,PAYMENT,PAYSTATUS,"
			+ "ORDERDATE,PAYEXPIRE,PAYCODE,RECEIVER,TEL,ADDRESS,ORDERSTATUS "
			+ "FROM ORDERMASTER WHERE ORDERMASTERID = ?";
	private static final String DELETE = "DELETE FROM ORDERMASTER WHERE ORDERMASTERID = ?";
	private static final String UPDATE = "UPDATE ORDERMASTER SET MEMBERID=?,PAYMENT=?,PAYSTATUS=?,"
			+ "ORDERDATE=?,PAYEXPIRE=?,PAYCODE=?,RECEIVER=?,TEL=?,ADDRESS=?,ORDERSTATUS=? " + "WHERE ORDERMASTERID = ?";

	private static final String UPDATE_ORDERSTATUS = "UPDATE ORDERMASTER SET ORDERSTATUS=? WHERE ORDERMASTERID = ?";

//	--（管理員）查多訂單，用出貨狀態。
	private static final String GET_LIST_BY_ORDERSTATUS = "SELECT * FROM ORDERMASTER WHERE ORDERSTATUS = ? ORDER BY ORDERDATE DESC";
//	--（管理員）查多訂單，用付款、出貨狀態。
	private static final String GET_LIST_BY_PAY_ORDERSTATUS = "SELECT * FROM ORDERMASTER WHERE PAYSTATUS = ? AND ORDERSTATUS = ? ORDER BY ORDERDATE DESC";
//	--（管理員）查多訂單，用會員編號。
	private static final String GET_LIST_BY_MEMBERID = "SELECT * FROM ORDERMASTER WHERE MEMBERID = ? ORDER BY ORDERDATE DESC";
//	--（管理員）查多訂單，用付款狀態與截止期限。
	private static final String GET_LIST_BY_PAYSTATUS_EXPIRE = "SELECT * FROM ORDERMASTER WHERE PAYSTATUS = ? AND PAYEXPIRE < ? ORDER BY ORDERDATE DESC";
// ---------同步新增訂單｜明細
	private static final String INSERT_STMT2 = "INSERT INTO ORDERMASTER (ORDERMASTERID,MEMBERID,PAYMENT,PAYSTATUS,"
			+ "ORDERDATE,PAYEXPIRE,PAYCODE,RECEIVER,TEL,ADDRESS,ORDERSTATUS) "
			+ "VALUES ('OR' || lpad(ORDERMASTER_SEQ.NEXTVAL, 5, '0'),?,?,?,?,?,?,?,?,?,?)";
	@Override
	public void insert(OrdermasterVO ordermasterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, ordermasterVO.getMemberid());
			pstmt.setString(2, ordermasterVO.getPayment());
			pstmt.setString(3, ordermasterVO.getPaystatus());
			pstmt.setTimestamp(4, ordermasterVO.getOrderdate());
			pstmt.setDate(5, ordermasterVO.getPayexpire());
			pstmt.setString(6, ordermasterVO.getPaycode());
			pstmt.setString(7, ordermasterVO.getReceiver());
			pstmt.setString(8, ordermasterVO.getTel());
			pstmt.setString(9, ordermasterVO.getAddress());
			pstmt.setString(10, ordermasterVO.getOrderstatus());
			pstmt.executeUpdate();

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
	public void update(OrdermasterVO ordermasterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, ordermasterVO.getMemberid());
			pstmt.setString(2, ordermasterVO.getPayment());
			pstmt.setString(3, ordermasterVO.getPaystatus());
			pstmt.setTimestamp(4, ordermasterVO.getOrderdate());
			pstmt.setDate(5, ordermasterVO.getPayexpire());
			pstmt.setString(6, ordermasterVO.getPaycode());
			pstmt.setString(7, ordermasterVO.getReceiver());
			pstmt.setString(8, ordermasterVO.getTel());
			pstmt.setString(9, ordermasterVO.getAddress());
			pstmt.setString(10, ordermasterVO.getOrderstatus());
			pstmt.setString(11, ordermasterVO.getOrdermasterid());

			pstmt.executeUpdate();

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
	public void delete(String ordermasterid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ordermasterid);
			pstmt.executeUpdate();

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
	public OrdermasterVO findbyPrimaryKey(String ordermasterid) {
		OrdermasterVO ordermasterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ordermasterid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ordermasterVO = new OrdermasterVO();
				ordermasterVO.setOrdermasterid(rs.getString("ordermasterid"));
				ordermasterVO.setMemberid(rs.getString("memberid"));
				ordermasterVO.setPayment(rs.getString("payment"));
				ordermasterVO.setPaystatus(rs.getString("paystatus"));
				ordermasterVO.setOrderdate(rs.getTimestamp("orderdate"));
				ordermasterVO.setPayexpire(rs.getDate("payexpire"));
				ordermasterVO.setPaycode(rs.getString("paycode"));
				ordermasterVO.setReceiver(rs.getString("receiver"));
				ordermasterVO.setTel(rs.getString("tel"));
				ordermasterVO.setAddress(rs.getString("address"));
				ordermasterVO.setOrderstatus(rs.getString("orderstatus"));
			}
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
		return ordermasterVO;
	}

	@Override
	public List<OrdermasterVO> getAll() {
		List<OrdermasterVO> list = new ArrayList<OrdermasterVO>();
		OrdermasterVO ordermasterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			System.out.println("OrderMasterJDBDDAO_getAll_test");
			while (rs.next()) {
				ordermasterVO = new OrdermasterVO();
				ordermasterVO.setOrdermasterid(rs.getString("ordermasterid"));
				ordermasterVO.setMemberid(rs.getString("memberid"));
				ordermasterVO.setPayment(rs.getString("payment"));
				ordermasterVO.setPaystatus(rs.getString("paystatus"));
				ordermasterVO.setOrderdate(rs.getTimestamp("orderdate"));
				ordermasterVO.setPayexpire(rs.getDate("payexpire"));
				ordermasterVO.setPaycode(rs.getString("paycode"));
				ordermasterVO.setReceiver(rs.getString("receiver"));
				ordermasterVO.setTel(rs.getString("tel"));
				ordermasterVO.setAddress(rs.getString("address"));
				ordermasterVO.setOrderstatus(rs.getString("orderstatus"));
				list.add(ordermasterVO);
			}
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
	public void update_orderstatus(OrdermasterVO ordermasterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ORDERSTATUS);

			pstmt.setString(1, ordermasterVO.getOrderstatus());
			pstmt.setString(2, ordermasterVO.getOrdermasterid());

			pstmt.executeUpdate();

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
	public List<OrdermasterVO> getSomeByMemberid(String memberid) {
		List<OrdermasterVO> list = new ArrayList<OrdermasterVO>();
		OrdermasterVO ordermasterVO = new OrdermasterVO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LIST_BY_MEMBERID);
			pstmt.setString(1, memberid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ordermasterVO = new OrdermasterVO();
				ordermasterVO.setOrdermasterid(rs.getString("ordermasterid"));
				ordermasterVO.setMemberid(rs.getString("memberid"));
				ordermasterVO.setPayment(rs.getString("payment"));
				ordermasterVO.setPaystatus(rs.getString("paystatus"));
				ordermasterVO.setOrderdate(rs.getTimestamp("orderdate"));
				ordermasterVO.setPayexpire(rs.getDate("payexpire"));
				ordermasterVO.setPaycode(rs.getString("paycode"));
				ordermasterVO.setReceiver(rs.getString("receiver"));
				ordermasterVO.setTel(rs.getString("tel"));
				ordermasterVO.setAddress(rs.getString("address"));
				ordermasterVO.setOrderstatus(rs.getString("orderstatus"));
				list.add(ordermasterVO);
			}
		} catch (SQLException e) {
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
	public List<OrdermasterVO> getSomeByOrderstatus(String orderstatus) {
		List<OrdermasterVO> list = new ArrayList<OrdermasterVO>();
		OrdermasterVO ordermasterVO = new OrdermasterVO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LIST_BY_ORDERSTATUS);
			pstmt.setString(1, orderstatus);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ordermasterVO = new OrdermasterVO();
				ordermasterVO.setOrdermasterid(rs.getString("ordermasterid"));
				ordermasterVO.setMemberid(rs.getString("memberid"));
				ordermasterVO.setPayment(rs.getString("payment"));
				ordermasterVO.setPaystatus(rs.getString("paystatus"));
				ordermasterVO.setOrderdate(rs.getTimestamp("orderdate"));
				ordermasterVO.setPayexpire(rs.getDate("payexpire"));
				ordermasterVO.setPaycode(rs.getString("paycode"));
				ordermasterVO.setReceiver(rs.getString("receiver"));
				ordermasterVO.setTel(rs.getString("tel"));
				ordermasterVO.setAddress(rs.getString("address"));
				ordermasterVO.setOrderstatus(rs.getString("orderstatus"));
				list.add(ordermasterVO);
			}
		} catch (SQLException e) {
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
	public void insertWithOderMaster(OrdermasterVO ordermasterVO, List<OrderdetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
    		// 先新增商品
			String cols[] = {"ORDERMASTERID"};
			pstmt = con.prepareStatement(INSERT_STMT2, cols);			
			pstmt.setString(1, ordermasterVO.getMemberid());
			pstmt.setString(2, ordermasterVO.getPayment());
			pstmt.setString(3, ordermasterVO.getPaystatus());
			pstmt.setTimestamp(4, ordermasterVO.getOrderdate());
			pstmt.setDate(5, ordermasterVO.getPayexpire());
			pstmt.setString(6, ordermasterVO.getPaycode());
			pstmt.setString(7, ordermasterVO.getReceiver());
			pstmt.setString(8, ordermasterVO.getTel());
			pstmt.setString(9, ordermasterVO.getAddress());
			pstmt.setString(10, ordermasterVO.getOrderstatus());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_ordermasterid = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_ordermasterid = rs.getString(1);
				System.out.println("自增主鍵值= " + next_ordermasterid +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增明細
			OrderdetailJDBCDAO dao = new OrderdetailJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (OrderdetailVO aOrderdetail : list) {
				aOrderdetail.setOrdermasterid(new String(next_ordermasterid));
				dao.insert2(aOrderdetail,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_ordermasterid + "時,共有明細" + list.size()
					+ "筆同時被新增");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-orderMaster");
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
	public List<OrdermasterVO> getSomeByPayAndExpire(String paystatus,Date pickdate) {
		List<OrdermasterVO> list = new ArrayList<OrdermasterVO>();
		OrdermasterVO ordermasterVO = new OrdermasterVO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LIST_BY_PAYSTATUS_EXPIRE);
			pstmt.setString(1, paystatus);
			pstmt.setDate(2, pickdate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ordermasterVO = new OrdermasterVO();
				ordermasterVO.setOrdermasterid(rs.getString("ordermasterid"));
				ordermasterVO.setMemberid(rs.getString("memberid"));
				ordermasterVO.setPayment(rs.getString("payment"));
				ordermasterVO.setPaystatus(rs.getString("paystatus"));
				ordermasterVO.setOrderdate(rs.getTimestamp("orderdate"));
				ordermasterVO.setPayexpire(rs.getDate("payexpire"));
				ordermasterVO.setPaycode(rs.getString("paycode"));
				ordermasterVO.setReceiver(rs.getString("receiver"));
				ordermasterVO.setTel(rs.getString("tel"));
				ordermasterVO.setAddress(rs.getString("address"));
				ordermasterVO.setOrderstatus(rs.getString("orderstatus"));
				list.add(ordermasterVO);
			}
		} catch (SQLException e) {
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
	public void update_orderstatus2(List<OrdermasterVO> list,String orderstatus) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ORDERSTATUS);
//			System.out.println("list==>"+list);
			for(OrdermasterVO ordermasterVO:list) {
				pstmt.setString(1, orderstatus);
				System.out.println(orderstatus);
				pstmt.setString(2, ordermasterVO.getOrdermasterid());
				System.out.println(ordermasterVO.getOrdermasterid());
				pstmt.executeUpdate();
				System.out.println("for each");
			}
System.out.println("test");
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
	public List<OrdermasterVO> getSomeByPayAndOrderstatus(String paystatus, String orderstatus) {
		List<OrdermasterVO> list = new ArrayList<OrdermasterVO>();
		OrdermasterVO ordermasterVO = new OrdermasterVO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LIST_BY_PAY_ORDERSTATUS);
			pstmt.setString(1, paystatus);
			pstmt.setString(2, orderstatus);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ordermasterVO = new OrdermasterVO();
				ordermasterVO.setOrdermasterid(rs.getString("ordermasterid"));
				ordermasterVO.setMemberid(rs.getString("memberid"));
				ordermasterVO.setPayment(rs.getString("payment"));
				ordermasterVO.setPaystatus(rs.getString("paystatus"));
				ordermasterVO.setOrderdate(rs.getTimestamp("orderdate"));
				ordermasterVO.setPayexpire(rs.getDate("payexpire"));
				ordermasterVO.setPaycode(rs.getString("paycode"));
				ordermasterVO.setReceiver(rs.getString("receiver"));
				ordermasterVO.setTel(rs.getString("tel"));
				ordermasterVO.setAddress(rs.getString("address"));
				ordermasterVO.setOrderstatus(rs.getString("orderstatus"));
				list.add(ordermasterVO);
			}
		} catch (SQLException e) {
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

}