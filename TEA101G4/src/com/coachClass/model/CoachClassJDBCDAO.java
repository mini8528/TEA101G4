package com.coachClass.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.classDetail.model.ClassDetailVO;

public class CoachClassJDBCDAO implements CoachClassDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G4";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO CoachClass (coachClassID, memberID, className, classContext, photo, startTime, price, quantity, address, addDate, editDate) "
			+ "												   VALUES ('COC' || lpad(COACHCLASS_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT coachClassID, memberID, className, classContext, photo, startTime, price, quantity, address, addDate, editDate FROM CoachClass order by coachClassID";
	private static final String GET_ONE_STMT = "SELECT coachClassID, memberID, className, classContext, photo, startTime, price, quantity, address, addDate, editDate FROM CoachClass where coachClassID = ?";
	private static final String GET_ClassDetail_ByCoachClass_STMT = "SELECT classDetailID, classOrderID, coachClassID, quantity,classOrderID FROM ClassDetail where coachClassID = ? order by classDetailID";
	
	private static final String DELETE_ClassDetail = "DELETE FROM ClassDetail where coachClassID = ?";
	private static final String DELETE_CoachClass = "DELETE FROM CoachClass where coachClassID = ?";	
	
	private static final String UPDATE = "UPDATE CoachClass set memberID=?, className=?, classContext=?, photo=?, startTime=?, price=?, quantity=?, address=?, addDate=?, editDate=?   where coachClassID = ?";
	private static final String GET_CLASS_BY_NAME = "SELECT * FROM CoachClass WHERE className LIKE ? ORDER BY coachClassID ASC";
	
	@Override
	public void insert(CoachClassVO coachClassVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, coachClassVO.getMemberID());
			pstmt.setString(2, coachClassVO.getClassName());
			pstmt.setString(3, coachClassVO.getClassContext());
			pstmt.setBytes(4, coachClassVO.getPhoto());
			pstmt.setTimestamp(5, coachClassVO.getStartTime());
			pstmt.setInt(6, coachClassVO.getPrice());
			pstmt.setInt(7, coachClassVO.getQuantity());
			pstmt.setString(8, coachClassVO.getAddress());
			pstmt.setTimestamp(9, coachClassVO.getAddDate());
			pstmt.setTimestamp(10, coachClassVO.getEditDate());

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
	public void update(CoachClassVO coachClassVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, coachClassVO.getMemberID());
			pstmt.setString(2, coachClassVO.getClassName());
			pstmt.setString(3, coachClassVO.getClassContext());
			pstmt.setBytes(4, coachClassVO.getPhoto());
			pstmt.setTimestamp(5, coachClassVO.getStartTime());
			pstmt.setInt  (6, coachClassVO.getPrice());
			pstmt.setInt(7, coachClassVO.getQuantity());
			pstmt.setString  (8, coachClassVO.getAddress());
			pstmt.setTimestamp  (9, coachClassVO.getAddDate());
			pstmt.setTimestamp  (10, coachClassVO.getEditDate());
			pstmt.setString  (11, coachClassVO.getCoachClassID());
			
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
	public void delete(String coachClassID) {

		int updateCount_CoachClass = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除員工
			pstmt = con.prepareStatement(DELETE_ClassDetail);
			pstmt.setString(1, "CD00002");
			updateCount_CoachClass = pstmt.executeUpdate();
			// 再刪除部門
			pstmt = con.prepareStatement(DELETE_CoachClass);
			pstmt.setString(1, coachClassID);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除訂單編號" + coachClassID + "時,共有明細 " + updateCount_CoachClass
					+ " 筆同時被刪除");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public CoachClassVO findByPrimaryKey(String coachClassID) {

		CoachClassVO coachClassVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, coachClassID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// coachClassVO 也稱為 Domain objects
				coachClassVO = new CoachClassVO();
				coachClassVO.setCoachClassID(rs.getString("coachClassID"));
				coachClassVO.setMemberID(rs.getString("memberID"));
				coachClassVO.setClassName(rs.getString("className"));
				coachClassVO.setClassContext(rs.getString("classContext"));
				coachClassVO.setPhoto(rs.getBytes("photo"));
				coachClassVO.setStartTime(rs.getTimestamp("startTime"));
				coachClassVO.setPrice(rs.getInt("price"));
				coachClassVO.setQuantity(rs.getInt("quantity"));
				coachClassVO.setAddress(rs.getString("address"));
				coachClassVO.setAddDate(rs.getTimestamp("addDate"));
				coachClassVO.setEditDate(rs.getTimestamp("addDate"));
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
		return coachClassVO;
	}

	@Override
	public List<CoachClassVO> getAll() {

		List<CoachClassVO> list = new ArrayList<CoachClassVO>();
		CoachClassVO coachClassVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				coachClassVO = new CoachClassVO();
				coachClassVO.setCoachClassID(rs.getString("coachClassID"));
				coachClassVO.setMemberID(rs.getString("memberID"));
				coachClassVO.setClassName(rs.getString("className"));
				coachClassVO.setClassContext(rs.getString("classContext"));
				coachClassVO.setPhoto(rs.getBytes("photo"));
				coachClassVO.setStartTime(rs.getTimestamp("startTime"));
				coachClassVO.setPrice(rs.getInt("price"));
				coachClassVO.setQuantity(rs.getInt("quantity"));
				coachClassVO.setAddress(rs.getString("address"));
				coachClassVO.setAddDate(rs.getTimestamp("addDate"));
				coachClassVO.setEditDate(rs.getTimestamp("addDate"));
				list.add(coachClassVO);
				// Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<CoachClassVO> getCoachClassByName(String className) {

		List<CoachClassVO> list = new ArrayList<CoachClassVO>();
		CoachClassVO coachClassVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(GET_CLASS_BY_NAME);
			pstmt.setString(1, "%"+className+"%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				coachClassVO = new CoachClassVO();
				coachClassVO.setCoachClassID(rs.getString("coachClassID"));
				coachClassVO.setMemberID(rs.getString("memberID"));
				coachClassVO.setClassName(rs.getString("className"));
				coachClassVO.setClassContext(rs.getString("classContext"));
				coachClassVO.setPhoto(rs.getBytes("photo"));
				coachClassVO.setStartTime(rs.getTimestamp("startTime"));
				coachClassVO.setPrice(rs.getInt("price"));
				coachClassVO.setQuantity(rs.getInt("quantity"));
				coachClassVO.setAddress(rs.getString("address"));
				coachClassVO.setAddDate(rs.getTimestamp("addDate"));
				coachClassVO.setEditDate(rs.getTimestamp("addDate"));
				list.add(coachClassVO);
				// Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	
		public static void main(String[] args) {
//
		CoachClassJDBCDAO dao = new CoachClassJDBCDAO();
//
//		// 新增
//		CoachClassVO coachClassVO1 = new CoachClassVO();
//		coachClassVO1.setMemberID("M006");
//		coachClassVO1.setClassName("className");
//		coachClassVO1.setClassContext("Y");
//		coachClassVO1.setPhoto(null);
//		coachClassVO1.setStartTime(new Timestamp(System.currentTimeMillis()));
//		coachClassVO1.setPrice(new Integer(6));
//		coachClassVO1.setQuantity(new Integer(6));
//		coachClassVO1.setAddress("Address");
//		coachClassVO1.setAddDate(new Date(System.currentTimeMillis()));
//		coachClassVO1.setEditDate(new Date(System.currentTimeMillis()));
//		dao.insert(coachClassVO1);
//
//		// 修改
//		CoachClassVO coachClassVO2 = new CoachClassVO();
//		coachClassVO2.setCoachClassID("COC00001");
//		coachClassVO2.setMemberID("M003");
//		coachClassVO2.setClassName("className");
//		coachClassVO2.setClassContext("Y");
//		coachClassVO2.setPhoto(null);
//		coachClassVO2.setStartTime(new Timestamp(System.currentTimeMillis()));
//		coachClassVO2.setPrice(new Integer(6));
//		coachClassVO2.setQuantity(new Integer(6));
//		coachClassVO2.setAddress("Address");
//		coachClassVO2.setAddDate(new Date(System.currentTimeMillis()));
//		coachClassVO2.setEditDate(new Date(System.currentTimeMillis()));
//		
//		dao.update(coachClassVO2);
//
//		// 刪除
//		dao.delete("COC00006");
//
//		// 查詢
		CoachClassVO coachClassVO3 = dao.findByPrimaryKey("COC00001");
		System.out.print(coachClassVO3.getCoachClassID() + ",");
		System.out.print(coachClassVO3.getMemberID() + ",");
		System.out.print(coachClassVO3.getClassName()+ ",");
		System.out.print(coachClassVO3.getClassContext()+ ",");
		System.out.print(coachClassVO3.getPhoto()+ ",");
		System.out.print(coachClassVO3.getStartTime()+ ",");
		System.out.print(coachClassVO3.getPrice()+ ",");
		System.out.print(coachClassVO3.getQuantity()+ ",");
		System.out.print(coachClassVO3.getAddress()+ ",");
		System.out.print(coachClassVO3.getAddDate()+ ",");
		System.out.print(coachClassVO3.getEditDate());
		System.out.println();
		System.out.println("---------------------");
//
//		// 查詢部門
//		List<CoachClassVO> list = dao.getAll();
//		for (CoachClassVO aCoachClass : list) {
//			System.out.print(aCoachClass.getCoachClassID() + ",");
//			System.out.print(aCoachClass.getMemberID() + ",");
//			System.out.print(aCoachClass.getClassName()+ ",");
//			System.out.print(aCoachClass.getClassContext()+ ",");
//			System.out.print(aCoachClass.getPhoto()+ ",");
//			System.out.print(aCoachClass.getStartTime()+ ",");
//			System.out.print(aCoachClass.getPrice()+ ",");
//			System.out.print(aCoachClass.getQuantity()+ ",");
//			System.out.print(aCoachClass.getAddress()+ ",");
//			System.out.print(aCoachClass.getAddDate()+ ",");
//			System.out.print(aCoachClass.getEditDate());
//			System.out.println();
//		}
//		
//		// 查詢某部門的員工
//		Set<ClassDetailVO> set = dao.getClassDetailByCoachClassID("COC00001");
//		for (ClassDetailVO aClassDetail : set) {
//			System.out.print(aClassDetail.getClassDetailID() + ",");
//			System.out.print(aClassDetail.getClassOrderID() + ",");
//			System.out.print(aClassDetail.getCoachClassID() + ",");
//			System.out.print(aClassDetail.getQuantity() );
//			System.out.println();
//		}
	}

	

}
