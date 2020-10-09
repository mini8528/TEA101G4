package com.classDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coachComment.model.CoachCommentVO;

public class ClassDetailJDBCDAO implements ClassDetailDAO_interface {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "TEA101G4";
	private static final String passwd = "123456";
	
	private static final String INSERT_STMT =  
			"INSERT INTO ClassDetail (classDetailID, classOrderID, coachClassID, quantity) VALUES ('CD' || lpad(CLASSDETAIL_SEQ.NEXTVAL, 5, '0'), ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT classDetailID, classOrderID, coachClassID, quantity FROM ClassDetail order by classDetailID";
		private static final String GET_ONE_STMT = 
			"SELECT classDetailID, classOrderID, coachClassID, quantity FROM ClassDetail where classDetailID = ?";
		private static final String DELETE = 
			"DELETE FROM ClassDetail where classDetailID = ?";
		private static final String UPDATE = 
			"UPDATE ClassDetail set classOrderID=?, coachClassID=?, quantity=? where classDetailID = ?";
		private static final String MEMBER_FIND_COACH = "SELECT DISTINCT CC.MEMBERID FROM classdetail cd LEFT JOIN coachclass cc ON cd.coachclassid = cc.coachclassid RIGHT JOIN classorder CO ON cd.classorderid = CO.classorderid WHERE CO.MEMBERID = ?";
	
	@Override
	public void insert(ClassDetailVO classDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, classDetailVO.getClassOrderID());
			pstmt.setString(2, classDetailVO.getCoachClassID());
			pstmt.setInt(3, classDetailVO.getQuantity());
			
			pstmt.executeUpdate();
			System.out.println("------------insert 成功--------");

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
	public void update(ClassDetailVO classDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			System.out.println("------------update start--------");

			pstmt.setString(1, classDetailVO.getClassOrderID());
			pstmt.setString(2, classDetailVO.getCoachClassID());
			pstmt.setInt(3, classDetailVO.getQuantity());
			pstmt.setString(4, classDetailVO.getClassDetailID());

			pstmt.executeUpdate();

			System.out.println("------------update success--------");

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
	public void delete(String classDetailID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, classDetailID);

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
	public ClassDetailVO findByPrimaryKey(String classDetailID) {
		
		ClassDetailVO classDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, classDetailID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// classDetailVo 也稱為 Domain objects
				classDetailVO = new ClassDetailVO();
				classDetailVO.setClassDetailID(rs.getString("classDetailID"));
				classDetailVO.setClassOrderID(rs.getString("classOrderID"));
				classDetailVO.setCoachClassID(rs.getString("coachClassID"));
				classDetailVO.setQuantity(rs.getInt("quantity"));
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
		return classDetailVO;
	}

	@Override
	public List<ClassDetailVO> getAll() {
		List<ClassDetailVO> list = new ArrayList<ClassDetailVO>();
		ClassDetailVO classDetailVO = null;

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
				classDetailVO = new ClassDetailVO();
				classDetailVO.setClassDetailID(rs.getString("classDetailID"));
				classDetailVO.setClassOrderID(rs.getString("classOrderID"));
				classDetailVO.setCoachClassID(rs.getString("coachClassID"));
				classDetailVO.setQuantity(rs.getInt("quantity"));
				list.add(classDetailVO); // Store the row in the list
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
	
	
	@Override
	public List<String> studentFindCoach(String memberid) {
		List<String> list = new ArrayList<String>();
		

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(MEMBER_FIND_COACH);
			pstmt.setString(1, memberid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				list.add(rs.getString("memberid")); 
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
	
	
	
	public static void main(String[] args) {

		ClassDetailJDBCDAO dao = new ClassDetailJDBCDAO();

		// 新增
//		ClassDetailVO classDetailVO1 = new ClassDetailVO();
//		classDetailVO1.setClassOrderID("CO00002");
//		classDetailVO1.setCoachClassID("COC00001");
//		classDetailVO1.setQuantity(new Integer(6));
//		dao.insert(classDetailVO1);

		// 修改
//		ClassDetailVO classDetailVO2 = new ClassDetailVO();
//		classDetailVO2.setClassDetailID("CD00009");
//		classDetailVO2.setClassOrderID("CO00003");
//		classDetailVO2.setCoachClassID("COC00001");
//		classDetailVO2.setQuantity(new Integer(6));
//		dao.update(classDetailVO2);

		// 刪除
//		dao.delete("CD00009");

		// 查詢
//		ClassDetailVO classDetailVO3 = dao.findByPrimaryKey("CD00001");
//		System.out.print(classDetailVO3.getClassDetailID() + ",");
//		System.out.print(classDetailVO3.getClassOrderID() + ",");
//		System.out.print(classDetailVO3.getCoachClassID() + ",");
//		System.out.print(classDetailVO3.getQuantity() + ",");
//		System.out.println("---------------------");

		// 查詢
//		List<ClassDetailVO> list = dao.getAll();
//		for (ClassDetailVO aClassDetail : list) {
//			System.out.print(aClassDetail.getClassDetailID() + ",");
//			System.out.print(aClassDetail.getClassOrderID() + ",");
//			System.out.print(aClassDetail.getCoachClassID() + ",");
//			System.out.print(aClassDetail.getQuantity() + ",");
//			System.out.println();
//		}
		
		
		List<String> list = dao.studentFindCoach("M001");
		System.out.println(list);
		
	}

}
