package com.lat.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.adm.model.AdminnoVO;

public class LatJDBCDAO implements Lat_interface{
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "TEA101G4";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = "INSERT INTO LATESTNEWS (latestnewsid,adminid,admin2id, text,image, adddate, updatetime,uploaddate)"
			+ "VALUES ('LA' || lpad(LATESTNEWS_SEQ.NEXTVAL, 4, '0'),?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE LATESTNEWS set  adminid = ?, admin2id = ?, text = ?,image = ?, adddate = ?,updatetime = ?, uploaddate = ?  WHERE latestnewsid = ?";
	private static final String DELETE_STMT = "DELETE FROM LATESTNEWS WHERE latestnewsid = ?";
	private static final String FIND_BY_PK = "SELECT * FROM LATESTNEWS WHERE latestnewsid = ?";
	private static final String GET_ALL = "SELECT * FROM LATESTNEWS";
	
	@Override
	public void insert(LatVO latestnewsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			//latestnewsid,adminid, text,image, adddate, uploaddate			
			pstmt.setString(1, latestnewsVO.getAdminid());
			pstmt.setString(2, latestnewsVO.getAdmin2id());
			pstmt.setString(3, latestnewsVO.getText());
			pstmt.setBytes(4, latestnewsVO.getImage());
			pstmt.setDate(5, latestnewsVO.getAdddate());
			pstmt.setDate(6, latestnewsVO.getUpdatetime());
			pstmt.setDate(7, latestnewsVO.getUploaddate());



			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
	public void update(LatVO latestnewsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, latestnewsVO.getAdminid());
			pstmt.setString(2, latestnewsVO.getAdmin2id());
			pstmt.setString(3, latestnewsVO.getText());
			pstmt.setBytes(4, latestnewsVO.getImage());
			pstmt.setDate(5, latestnewsVO.getAdddate());
			pstmt.setDate(6, latestnewsVO.getUpdatetime());
			pstmt.setDate(7, latestnewsVO.getUploaddate());
			pstmt.setString(8, latestnewsVO.getLatestnewsid());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
	public void delete(String latestnewsid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, latestnewsid);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
	public LatVO findByPrimaryKey(String latestnewsid) {
		LatVO lat = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, latestnewsid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lat = new LatVO();
				lat.setLatestnewsid(rs.getString("latestnewsid"));
				lat.setAdminid(rs.getString("adminid"));
				lat.setAdmin2id(rs.getString("admin2id"));
				lat.setText(rs.getString("text"));
				lat.setImage(rs.getBytes("image"));
				lat.setAdddate(rs.getDate("adddate"));
				lat.setUpdatetime(rs.getDate("updatetime"));
				lat.setUploaddate(rs.getDate("uploaddate"));

			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
		return lat;
	}

	@Override
	public List<LatVO> getAll() {
		List<LatVO> latList = new ArrayList<>();
		LatVO lat = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lat = new LatVO();
				lat.setLatestnewsid(rs.getString("latestnewsid"));
				lat.setAdminid(rs.getString("adminid"));
				lat.setAdmin2id(rs.getString("admin2id"));
				lat.setText(rs.getString("text"));
				lat.setImage(rs.getBytes("image"));
				lat.setAdddate(rs.getDate("adddate"));
				lat.setUpdatetime(rs.getDate("updatetime"));
				lat.setUploaddate(rs.getDate("uploaddate"));
				latList.add(lat);
				
				
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
		return latList;
	}



}
