package com.spec.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecJDBCDAO implements SpecDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G4";
	String passwd = "123456";
//	String url = "jdbc:oracle:thin:@database-2.c25eusi5ih7r.ap-northeast-1.rds.amazonaws.com:1520:orcl";
//	String userid = "admin";
//	String passwd = "admin1234";
//----新增商品----
	private static final String INSERT_STMT = "INSERT INTO spec (specid,productid,specific,stock) "
			+ "VALUES ('SD' || lpad(SPEC_SEQ.NEXTVAL, 4, '0'),?,?,?)";
//----查詢所有商品----
	private static final String GET_ALL_STMT = "SELECT specid, productid,specific,stock " + "FROM spec order by specid";
//----查詢單筆商品----
	private static final String GET_ONE_STMT = "SELECT specid, productid,specific,stock "
			+ "FROM spec where specid = ?";
//----刪除商品----
	private static final String DELETE = "DELETE FROM spec where specid = ?";
//----編輯商品----
	private static final String UPDATE = "UPDATE spec set productid=?,specific=?,stock=? where specid = ?";
//--查某商品的多樣規格，用(productid)。
	private static final String GET_BY_PRODUCTID = "SELECT * FROM SPEC WHERE PRODUCTID = ? ORDER BY STOCK ASC";

	@Override
	public void insert(SpecVO specVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, specVO.getProductid());
			pstmt.setString(2, specVO.getSpecific());
			pstmt.setInt(3, specVO.getStock());

			pstmt.executeUpdate();
			// Handle any SQL errors
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
	public void update(SpecVO specVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, specVO.getProductid());
			pstmt.setString(2, specVO.getSpecific());
			pstmt.setInt(3, specVO.getStock());
			pstmt.setString(4, specVO.getSpecid());

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
	public void delete(String specid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, specid);
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
	public SpecVO findByPrimaryKey(String specid) {
		SpecVO specVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, specid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// specVO 也稱為 Domain objects
				specVO = new SpecVO();
				specVO.setSpecid(rs.getString("specid"));
//				System.out.println("測試spec-----PK");
				specVO.setProductid(rs.getString("productid"));
				specVO.setSpecific(rs.getString("specific"));
				specVO.setStock(rs.getInt("stock"));
			}
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
		return specVO;
	}

	@Override
	public List<SpecVO> getAll() {
		List<SpecVO> list = new ArrayList<SpecVO>();
		SpecVO specVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
//			System.out.println("測試spec-----查全部");
			while (rs.next()) {
				// VO = Domain objects
				specVO = new SpecVO();
				specVO.setSpecid(rs.getString("specid"));
				specVO.setProductid(rs.getString("productid"));
//				System.out.println("測試spec-----查specID:" + specVO.getSpecid());
				specVO.setSpecific(rs.getString("specific"));
//				System.out.println("測試spec-----查規格為:" + specVO.getSpecific());
				specVO.setStock(rs.getInt("stock"));
//				System.out.println("測試spec-----查該規格庫存:" + specVO.getStock());
				list.add(specVO);
			}
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

//----查某商品的多樣規格，用(productid)。
	@Override
	public List<SpecVO> getSpecByProductid(String productid) {
		List<SpecVO> list = new ArrayList<SpecVO>();
		SpecVO specVO = new SpecVO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(GET_BY_PRODUCTID);
			pstmt.setString(1, productid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				specVO = new SpecVO();
				specVO.setProductid(rs.getString("productid"));
				specVO.setSpecid(rs.getString("specid"));
				specVO.setSpecific(rs.getString("specific"));
				specVO.setStock(rs.getInt("stock"));
				list.add(specVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't Load DataBase Driver. " + e.getMessage());
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
	public void insert2(SpecVO specVO, Connection con) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, specVO.getProductid());
			pstmt.setString(2, specVO.getSpecific());
			pstmt.setInt(3, specVO.getStock());
			pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-Spec");
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