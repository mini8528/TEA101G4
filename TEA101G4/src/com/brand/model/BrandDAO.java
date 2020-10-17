package com.brand.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BrandDAO implements BrandDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO BRAND (BRANDID,NAME) VALUES ('BD' || LPAD(BRAND_SEQ.NEXTVAL, 2, '0'),?)";
	private static final String GET_ALL_STMT = "SELECT BRANDID, NAME FROM BRAND ORDER BY BRANDID";
	private static final String GET_ONE_STMT = "SELECT BRANDID, NAME FROM BRAND WHERE BRANDID = ?";
	private static final String DELETE = "DELETE FROM BRAND WHERE BRANDID = ?";
	private static final String UPDATE = "UPDATE BRAND SET NAME=? WHERE BRANDID = ?";
	private static final String GET_STMT_BY_FK = "SELECT BRANDID, NAME FROM BRAND WHERE NAME LIKE ?";

	@Override
	public void insert(BrandVO brandVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, brandVO.getName());
			
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
	public void update(BrandVO brandVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, brandVO.getName());
			pstmt.setString(2, brandVO.getBrandid());
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
	public void delete(String brandid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, brandid);
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
	public BrandVO findByPrimaryKey(String brandid) {
		BrandVO brandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
	
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, brandid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
//				System.out.println("findPK");

				brandVO = new BrandVO();
				brandVO.setBrandid(rs.getString("brandid"));
//				System.out.println("測試brand-----PK");
				brandVO.setName(rs.getString("name"));
			}else {
				System.out.println("BRANDJBDCDAO_findByPrimaryKey_查無資料。");
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
		return brandVO;
	}

	@Override
	public List<BrandVO> getAll() {
		List<BrandVO> list = new ArrayList<BrandVO>();
		BrandVO brandVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
	
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
//			System.out.println("測試brand-----查全部");
			while (rs.next()) {
				// VO = Domain objects
				brandVO = new BrandVO();
				brandVO.setBrandid(rs.getString("brandid"));
//				System.out.println("測試brand-----查brandid:" + brandVO.getBrandid());
				brandVO.setName(rs.getString("name"));
//				System.out.println("測試brand-----查品牌名:" + brandVO.getName());
				list.add(brandVO);
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
		return list;
	}

	@Override
	public List<BrandVO> getBrandsByName(String name) {
		List<BrandVO> list = new ArrayList<BrandVO>();
		BrandVO brandVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STMT_BY_FK);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			System.out.println("BRANDJBDCDAO_KW_");
			if (rs.next()) {
				// VO = Domain objects
				brandVO = new BrandVO();
				brandVO.setBrandid(rs.getString("brandid"));
				System.out.println("BRANDJBDCDAO_KW_BrandId:" + brandVO.getBrandid());
				brandVO.setName(rs.getString("name"));
				System.out.println("BRANDJBDCDAO_KW_BrandId:" + brandVO.getName());
				list.add(brandVO);
			}else {
				System.out.println("BRANDJBDCDAO_getBrandsByName_查無資料。");
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
		return list;
	}
}