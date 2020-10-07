package com.bodyRecord.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BodyRecordJDBCDAO implements BodyRecord_interface {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "TEA101G4";
	private static final String passwd = "123456";
	
	private static final String INSERT_STMT =  
			"INSERT INTO BodyRecord (bodyRecordID, memberID, uploadDate, photo1, description, describeStatus, updateTime) VALUES ('BR'|| lpad(BODYRECORD_SEQ.NEXTVAL, 5,'0'), ?, ?, ?, ?, ?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT bodyRecordID, memberID, uploadDate, photo1, description, describeStatus, updateTime FROM bodyRecord order by bodyRecordID";
		private static final String GET_ONE_STMT = 
			"SELECT bodyRecordID, memberID, uploadDate, photo1, description, describeStatus, updateTime FROM BodyRecord where bodyRecordID = ?";
		private static final String DELETE = 
			"DELETE FROM BodyRecord where bodyRecordID = ?";
		private static final String UPDATE = 
			"UPDATE BodyRecord set  memberID=?, uploadDate=?, photo1=?, description=?, describeStatus=?, updateTime=? where bodyRecordID = ?";

		
	@Override
	public void insert(BodyRecordVO bodyRecordVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, bodyRecordVO.getMemberID());
			pstmt.setTimestamp(2, bodyRecordVO.getUploadDate());
			pstmt.setBytes(3, bodyRecordVO.getPhoto1());
			pstmt.setString(4, bodyRecordVO.getDescription());
			pstmt.setString(5, bodyRecordVO.getDescribeStatus());
			pstmt.setTimestamp(6, bodyRecordVO.getUpdateTime());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	public void update(BodyRecordVO bodyRecordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, bodyRecordVO.getMemberID());
			pstmt.setTimestamp(2, bodyRecordVO.getUploadDate());
			pstmt.setBytes(3, bodyRecordVO.getPhoto1());
			pstmt.setString(4, bodyRecordVO.getDescription());
			pstmt.setString(5, bodyRecordVO.getDescribeStatus());
			pstmt.setTimestamp  (6, bodyRecordVO.getUpdateTime());
			pstmt.setString(7, bodyRecordVO.getBodyRecordID());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	public void delete(String bodyRecordID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bodyRecordID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	public BodyRecordVO findByPrimaryKey(String bodyRecordID) {
		
		BodyRecordVO bodyRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bodyRecordID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// classDetailVo 也稱為 Domain objects
				bodyRecordVO = new BodyRecordVO();
				bodyRecordVO.setBodyRecordID(rs.getString("bodyRecordID"));
				bodyRecordVO.setMemberID(rs.getString("memberID"));
				bodyRecordVO.setUploadDate(rs.getTimestamp("uploadDate"));
				bodyRecordVO.setPhoto1(rs.getBytes("photo1"));
				bodyRecordVO.setDescription(rs.getString("description"));
				bodyRecordVO.setDescribeStatus(rs.getString("describeStatus"));
				bodyRecordVO.setUpdateTime(rs.getTimestamp("updateTime"));
			}

			// Handle any driver errors
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
		return bodyRecordVO;
	}

	@Override
	public List<BodyRecordVO> getAll() {
		
		List<BodyRecordVO> list = new ArrayList<BodyRecordVO>();
		BodyRecordVO bodyRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// classDetailVO 也稱為 Domain objects
				bodyRecordVO = new BodyRecordVO();
				bodyRecordVO.setBodyRecordID(rs.getString("bodyRecordID"));
				bodyRecordVO.setMemberID(rs.getString("memberID"));
				bodyRecordVO.setUploadDate(rs.getTimestamp("uploadDate"));
				bodyRecordVO.setPhoto1(rs.getBytes("photo1"));
				bodyRecordVO.setDescription(rs.getString("description"));
				bodyRecordVO.setDescribeStatus(rs.getString("describeStatus"));
				bodyRecordVO.setUpdateTime(rs.getTimestamp("updateTime"));
				list.add(bodyRecordVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	
//	public static void main(String[] args) {
//
//		BodyRecordJDBCDAO dao = new BodyRecordJDBCDAO();

		// 新增
//		BodyRecordVO WishListVO1 = new BodyRecordVO();
//		WishListVO1.setMemberID("M002");
//		WishListVO1.setUploadDate(new Timestamp(System.currentTimeMillis()));
//		WishListVO1.setDescription("yo");
//		WishListVO1.setDescribeStatus("N");
//		WishListVO1.setUpdateTime(new Timestamp(System.currentTimeMillis()));
//		WishListVO1.setPhoto1(null);
//		dao.insert(WishListVO1);

		// 修改
//		BodyRecordVO WishListVO1 = new BodyRecordVO();
//		WishListVO1.setBodyRecordID("BR00007");
//		WishListVO1.setMemberID("M001");
//		WishListVO1.setUploadDate(new Timestamp(System.currentTimeMillis()));
//		WishListVO1.setDescription("yo11");
//		WishListVO1.setDescribeStatus("N");
//		WishListVO1.setUpdateTime(new Timestamp(System.currentTimeMillis()));
//		WishListVO1.setPhoto1(null);
//		dao.update(WishListVO1);

		// 刪除
//		dao.delete("BR00007");

		// 查詢
//		BodyRecordVO WishListVO3 = dao.findByPrimaryKey("BR00001");
//		System.out.print(WishListVO3.getBodyRecordID() + ",");
//		System.out.print(WishListVO3.getMemberID() + ",");
//		System.out.print(WishListVO3.getUploadDate() + ",");
//		System.out.print(WishListVO3.getPhoto1() + ",");
//		System.out.print(WishListVO3.getDescription() + ",");
//		System.out.print(WishListVO3.getDescribeStatus() + ",");
//		System.out.println(WishListVO3.getUpdateTime() );
//		System.out.println("---------------------");

		// 查詢
//		List<BodyRecordVO> list = dao.getAll();
//		for (BodyRecordVO aBodyRecord : list) {
//			System.out.print(aBodyRecord.getBodyRecordID() + ",");
//			System.out.print(aBodyRecord.getMemberID() + ",");
//			System.out.print(aBodyRecord.getUploadDate() + ",");
//			System.out.print(aBodyRecord.getPhoto1() + ",");
//			System.out.print(aBodyRecord.getDescription() + ",");
//			System.out.print(aBodyRecord.getDescribeStatus() + ",");
//			System.out.print(aBodyRecord.getUpdateTime() );
//			System.out.println();
//		}
//	}
	

}
