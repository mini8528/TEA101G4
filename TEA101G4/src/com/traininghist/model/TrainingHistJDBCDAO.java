package com.traininghist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.trainingclsdetail.model.TrainingClsDetailVO;
import com.trainingsche.model.TrainingScheJDBCDAO;
import com.trainingsche.model.TrainingScheVO;

public class TrainingHistJDBCDAO implements TrainingHistDAOIntf {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO traininghist (traininghistid,memberid,trainingScheid,actionid,trainingtime,trainingSet,trainingRep,trainingWt) VALUES (('HIST' || lpad(HIST_SEQ.NEXTVAL, 3, '0')), ?, ?, ?, ?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT traininghistid,memberid,trainingScheid,actionid,trainingtime,trainingSet,trainingRep,trainingWt FROM traininghist order by traininghistid";
	private static final String GET_ONE_STMT = "SELECT traininghistid,memberid,trainingScheid,actionid,trainingtime,trainingSet,trainingRep,trainingWt FROM traininghist where traininghistid = ?";
	private static final String DELETE = "DELETE FROM traininghist where traininghistid= ?";
	private static final String UPDATE = "UPDATE traininghist set memberid=?, trainingScheid=?, actionid=?, trainingtime=?, trainingSet=?,trainingRep=?, trainingWt=? where traininghistid = ?";
	private static final String SELECT_BY_MEMBERID = "SELECT traininghistid,memberid,trainingScheid,actionid,trainingtime,trainingSet,trainingRep,trainingWt FROM traininghist where memberid = ?";
	private static final String UPDATEHIST = "UPDATE traininghist set trainingSet=?,trainingRep=?, trainingWt=? where traininghistid = ?";

	public String insert(TrainingHistVO trainingHistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			// 新增運動歷程
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String[] col = { "traininghistid" };
			pstmt = con.prepareStatement(INSERT_STMT, col);

			pstmt.setString(1, trainingHistVO.getMemberid());
			pstmt.setString(2, trainingHistVO.getTrainingscheid());
			pstmt.setString(3, trainingHistVO.getActionid());
			pstmt.setInt(4, trainingHistVO.getTrainingtime());
			pstmt.setInt(5, trainingHistVO.getTrainingset());
			pstmt.setInt(6, trainingHistVO.getTrainingrep());
			pstmt.setInt(7, trainingHistVO.getTrainingwt());

			pstmt.executeUpdate();

			// --取得對應的自增主鍵值
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
//				trainingHistVO.setTraininghistid(rs.getString(1));
				return rs.getString(1);
			} else {
				System.out.println("未取得自增主鍵值");
			}
			// Handle any SQL errors
		} catch (ClassNotFoundException exception) {
			throw new RuntimeException("A database error occured. " + exception.getMessage());
			// Clean p JDBC resources
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
		return "";
	}

	public void insert2(TrainingHistVO traininghistVO, Connection con) {

		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, traininghistVO.getMemberid());
			System.out.println(traininghistVO.getMemberid());
			pstmt.setString(2, traininghistVO.getTrainingscheid());
			System.out.println(traininghistVO.getTrainingscheid());

			pstmt.setString(3, traininghistVO.getActionid());
			System.out.println(traininghistVO.getActionid());
			pstmt.setInt(4, traininghistVO.getTrainingtime());
			System.out.println(traininghistVO.getTrainingtime());

			pstmt.setInt(5, traininghistVO.getTrainingset());
			System.out.println(traininghistVO.getTrainingset());

			pstmt.setInt(6, traininghistVO.getTrainingrep());
			System.out.println(traininghistVO.getTrainingrep());

			pstmt.setInt(7, traininghistVO.getTrainingwt());
			System.out.println(traininghistVO.getTrainingwt());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3���]�w���exception�o�ͮɤ�catch�϶���
					System.err.print("Transaction is being ");
					System.err.println("rolled back-��-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
		}

	}

//更新運動歷程	
	public void update(TrainingHistVO trainingHistVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, trainingHistVO.getMemberid());
			pstmt.setString(2, trainingHistVO.getTrainingscheid());
			pstmt.setString(3, trainingHistVO.getActionid());
			pstmt.setInt(4, trainingHistVO.getTrainingtime());
			pstmt.setInt(5, trainingHistVO.getTrainingset());
			pstmt.setInt(6, trainingHistVO.getTrainingrep());
			pstmt.setInt(7, trainingHistVO.getTrainingwt());
			pstmt.setString(8, trainingHistVO.getTraininghistid());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException se) {
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

//更新運動歷程	traininghist
	public void updatehist(TrainingHistVO trainingHistVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEHIST);
			pstmt.setInt(1, trainingHistVO.getTrainingset());
			pstmt.setInt(2, trainingHistVO.getTrainingrep());
			pstmt.setInt(3, trainingHistVO.getTrainingwt());
			pstmt.setString(4, trainingHistVO.getTraininghistid());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException se) {
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

	public void delete(String trainingHistid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, trainingHistid);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException qq) {
			throw new RuntimeException("A database error occured. " + qq.getMessage());
			// Clean up JDBC resources
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

	public TrainingHistVO findByPrimaryKey(String trainingHistid) {

		TrainingHistVO trainingHist = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, trainingHistid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// TrainingHistVO 也稱為 Domain objects
				trainingHist = new TrainingHistVO();
				trainingHist.setTraininghistid(rs.getString("traininghistid"));
				trainingHist.setMemberid(rs.getString("memberid"));
				trainingHist.setTrainingscheid(rs.getString("trainingscheid"));
				trainingHist.setActionid(rs.getString("actionid"));
				trainingHist.setTrainingtime(rs.getInt("trainingtime"));
				trainingHist.setTrainingset(rs.getInt("trainingset"));
				trainingHist.setTrainingrep(rs.getInt("trainingrep"));
				trainingHist.setTrainingwt(rs.getInt("trainingwt"));

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException se) {
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
		return trainingHist;
	}

	public List<TrainingHistVO> getAll() {
		List<TrainingHistVO> list = new ArrayList<TrainingHistVO>();
		TrainingHistVO trainingHistVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				trainingHistVO = new TrainingHistVO();
				trainingHistVO.setTraininghistid(rs.getString("traininghistid"));
				trainingHistVO.setMemberid(rs.getString("memberid"));
				trainingHistVO.setTrainingscheid(rs.getString("trainingscheid"));
				trainingHistVO.setActionid(rs.getString("actionid"));
				trainingHistVO.setTrainingtime(rs.getInt("trainingtime"));
				trainingHistVO.setTrainingset(rs.getInt("trainingset"));
				trainingHistVO.setTrainingrep(rs.getInt("trainingrep"));
				trainingHistVO.setTrainingwt(rs.getInt("trainingwt"));

				list.add(trainingHistVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public List<TrainingHistVO> SELECT_BY_MEMBERID(String memberid) {

		return null;
	}

//Select:用課程Memberid搜尋所有的訓練歷程

	public List<TrainingHistVO> select(String memberid) {
		List<TrainingHistVO> list = new ArrayList<TrainingHistVO>();
		TrainingHistVO trainingHistVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_BY_MEMBERID);

			pstmt.setString(1, memberid);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				trainingHistVO = new TrainingHistVO();
				trainingHistVO.setTraininghistid(rs.getString("traininghistid"));
				trainingHistVO.setMemberid(rs.getString("memberid"));
				trainingHistVO.setTrainingscheid(rs.getString("trainingscheid"));
				trainingHistVO.setActionid(rs.getString("actionid"));
				trainingHistVO.setTrainingtime(rs.getInt("trainingtime"));
				trainingHistVO.setTrainingset(rs.getInt("trainingset"));
				trainingHistVO.setTrainingrep(rs.getInt("trainingrep"));
				trainingHistVO.setTrainingwt(rs.getInt("trainingwt"));

				list.add(trainingHistVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

	public static void main(String[] args) {

		TrainingHistJDBCDAO dao = new TrainingHistJDBCDAO();

		// 新增
//		TrainingHistVO trainingHistVO1 = new TrainingHistVO();
//		trainingHistVO1.setMemberid("M001");
//		trainingHistVO1.setTrainingscheid("SCH002");
//		trainingHistVO1.setActionid("AC008");
//		trainingHistVO1.setTrainingtime(38);
//		trainingHistVO1.setTrainingtime(50);
//		
//		trainingHistVO1.setTrainingset(12);
//		trainingHistVO1.setTrainingrep(12);
//		trainingHistVO1.setTrainingwt(12);
//	    dao.insert(trainingHistVO1);
//
//	// 修改
//	    TrainingHistVO trainingHistVO2 = new TrainingHistVO();
//	    trainingHistVO2.setMemberid("M003");
//	    trainingHistVO2.setTrainingscheid("SCH005");
//	    trainingHistVO2.setActionid("AC008");
//	    trainingHistVO2.setTrainingtime(java.sql.Date.valueOf("00:58:00"));
//	    trainingHistVO2.setTrainingset(12);
//	    trainingHistVO2.setTrainingrep(4);
//	    trainingHistVO2.setTrainingwt(25);
//	dao.update(trainingHistVO2);
//	
		TrainingHistVO trainingHistVO2 = new TrainingHistVO();
		trainingHistVO2.setTraininghistid("HIST001");
		trainingHistVO2.setTrainingset(30);
		trainingHistVO2.setTrainingrep(30);
		trainingHistVO2.setTrainingwt(30);
		dao.updatehist(trainingHistVO2);
		System.out.println("更新成功");

//
//	刪除
//dao.delete(7014);
//
//	// 查詢
//		TrainingHistVO trainingHistVO3 = dao.findByPrimaryKey("CLS001");
//		System.out.print(trainingHistVO3.getMemberid() + ",");
//		System.out.println("---------------------");

		// 查詢
//	List<TrainingHistVO> list = dao.getAll();
//	for (TrainingHistVO xx : list) {
//		System.out.print(xx.getTraininghistid() + ",");
//	
//		System.out.println();
//	}

//測試SELECT方法		
//				List<TrainingHistVO> traininghistVOs = dao.select("M001");
//				for (TrainingHistVO vo : traininghistVOs) {
//					System.out.println("vo.getTrainingclsdetailid = " + vo.getTraininghistid());
//					System.out.println("vo.getTrainingclsid = " + vo.getTrainingset());
//					System.out.println("vo.getActionid = " + vo.getTrainingwt());
//				}
	}

}
