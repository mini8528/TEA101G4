package com.pro.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ProDAO implements Pro_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//----新增評論----
	private static final String INSERT_STMT = "INSERT INTO PRODCOMM (prodcommid,productid, memberid, commtext,commstar, adddate, status)VALUES ('PROD'|| lpad(PRODCOMM_SEQ.NEXTVAL, 4, '0'),?,?,?,?,?,?)";
	//----修改評論狀態----
	private static final String UPDATE_TEXT = "UPDATE PRODCOMM set status = ? WHERE prodcommid = ?";
	//----修改評論內容----
	private static final String UPDATE_STMT = "UPDATE PRODCOMM set  productid = ?, memberid = ?, commtext = ?,commstar = ?, adddate = ?,editdate = ?, status = ? WHERE prodcommid = ?";	
	private static final String DELETE_STMT = "DELETE FROM PRODCOMM WHERE prodcommid = ?";
	//----查詢商品評論----
	private static final String FIND_BY_PK = "SELECT * FROM PRODCOMM WHERE prodcommid = ?";
	//----查詢上架多筆評論----
//		private static final String GET_ALL_STMT_customer = "SELECT * FROM PRODCOMM WHERE STATUS = 'Y' ORDER BY PRICE";
	//----查詢全部評論----
	private static final String GET_ALL = "SELECT * FROM PRODCOMM";
	//----新增評論----
	@Override
	public void insert(ProVO proVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, proVO.getProductid());
			pstmt.setString(2, proVO.getMemberid());
			pstmt.setString(3, proVO.getCommtext());
			pstmt.setInt(4, proVO.getCommstar());
			pstmt.setDate(5, proVO.getAdddate());
			pstmt.setString(6, proVO.getStatus());
		

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
	//----修改評論內容----
	@Override
	public void update(ProVO proVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			
			pstmt.setString(1, proVO.getProductid());
			pstmt.setString(2, proVO.getMemberid());
			pstmt.setString(3, proVO.getCommtext());
			pstmt.setInt(4, proVO.getCommstar());
			pstmt.setDate(5, proVO.getAdddate());
			pstmt.setDate(6, proVO.getEditdate());
			pstmt.setString(7, proVO.getStatus());
			pstmt.setString(8, proVO.getProdcommid());
			pstmt.executeUpdate();

			// Handle any driver errors
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
	//----修改評論狀態----
	@Override
	public void updatetext(ProVO proVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_TEXT);
			
			
//			pstmt.setString(1, proVO.getProductid());
//			pstmt.setString(2, proVO.getMemberid());
//			pstmt.setString(3, proVO.getCommtext());
//			pstmt.setInt(4, proVO.getCommstar());
//			pstmt.setDate(5, proVO.getAdddate());
//			pstmt.setDate(6, proVO.getEditdate());
			pstmt.setString(1, proVO.getStatus());
			pstmt.setString(2, proVO.getProdcommid());
			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
	public void delete(String prodcommid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, prodcommid);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
	
	//----查詢單筆商品評論----
	@Override
	public ProVO findByPrimaryKey(String prodcommid) {
		ProVO pro = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, prodcommid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pro = new ProVO();
				pro.setProdcommid(rs.getString("prodcommid"));
				pro.setProductid(rs.getString("productid"));
				pro.setMemberid(rs.getString("memberid"));
				pro.setCommtext(rs.getString("commtext"));
				pro.setCommstar(rs.getInt("commstar"));
				pro.setAdddate(rs.getDate("adddate"));
				pro.setEditdate(rs.getDate("editdate"));
				pro.setStatus(rs.getString("status"));
			}

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
		return pro;
	}
	//----查詢全部評論----
	@Override
	public List<ProVO> getAll() {
		List<ProVO> proList = new ArrayList<>();
		ProVO pro = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				
				pro = new ProVO();
				pro.setProdcommid(rs.getString("prodcommid"));
				pro.setProductid(rs.getString("productid"));
				pro.setMemberid(rs.getString("memberid"));
				pro.setCommtext(rs.getString("commtext"));
				pro.setCommstar(rs.getInt("commstar"));
				pro.setAdddate(rs.getDate("adddate"));
				pro.setEditdate(rs.getDate("editdate"));
				pro.setStatus(rs.getString("status"));
				
				proList.add(pro);
				
				
			}

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
		return proList;
	}

	//----查詢為狀態為"Y"的評論----
//		@Override
//		public List<ProVO> getproductid(String status) {
//			List<ProVO> proList = new ArrayList<>();
//			ProVO pro = null;
//			Connection con = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//
//			try {
//
//				Class.forName(DRIVER);
//				con = DriverManager.getConnection(URL, USER, PASSWORD);
//				pstmt = con.prepareStatement(GET_ALL_STMT_customer);
//				rs = pstmt.executeQuery();
//
//				while (rs.next()) {
//					
//					
//					pro = new ProVO();
//					pro.setProdcommid(rs.getString("prodcommid"));
//					pro.setProductid(rs.getString("productid"));
//					pro.setMemberid(rs.getString("memberid"));
//					pro.setCommtext(rs.getString("commtext"));
//					pro.setCommstar(rs.getInt("commstar"));
//					pro.setAdddate(rs.getDate("adddate"));
//					pro.setEditdate(rs.getDate("editdate"));
//					pro.setStatus(rs.getString("status"));
//					
//					proList.add(pro);
//					
//					
//				}
//
//			} catch (ClassNotFoundException ce) {
//				throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
//				// Handle any SQL errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. " + se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//			return proList;
//		}

}
