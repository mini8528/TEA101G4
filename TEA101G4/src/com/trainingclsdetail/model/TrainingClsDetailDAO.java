package com.trainingclsdetail.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.action.model.ActionVO;
import com.trainingcls.model.TrainingClsJDBCDAO;
import com.trainingcls.model.TrainingClsVO;
import com.traininghist.model.TrainingHistVO;

public class TrainingClsDetailDAO implements TrainingClsDetailDAOIntf {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO trainingclsdetail (trainingclsdetailid,trainingclsid,actionid) VALUES (('TD' || lpad(DETL_SEQ.NEXTVAL, 3, '0') ),?, ?)";
	private static final String GET_ALL_STMT = "SELECT trainingclsdetailid,trainingclsid,actionid FROM trainingclsdetail order by trainingclsdetailid";
	private static final String GET_ONE_STMT = "SELECT trainingclsdetailid,trainingclsid,actionid FROM trainingclsdetail where trainingclsdetailid = ?";
	private static final String DELETE = "DELETE FROM trainingclsdetail where trainingclsid = ?";
	private static final String UPDATE = "UPDATE trainingclsdetail set trainingclsid=?, actionid=?where trainingclsdetailid = ?";
	private static final String SELECT = "SELECT trainingclsdetailid,trainingclsid,actionid FROM trainingclsdetail WHERE TRAININGCLSID = ?";
	private static final String SELECT_MEMBERID = "SELECT trainingclsdetailid,trainingclsid,actionid FROM trainingclsdetail WHERE MEMBERID = ?";

//--查詢Trainingcls的所有動作	
	public void insert(TrainingClsDetailVO trainingclsdetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, trainingclsdetailVO.getTrainingclsid());
			pstmt.setString(2, trainingclsdetailVO.getActionid());

			pstmt.executeUpdate();

			// Handle any SQL errors
		}  catch (SQLException se) {
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

	public void update(TrainingClsDetailVO trainingclsdetailVO) {
//新增個人訓練課程內容
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, trainingclsdetailVO.getTrainingclsid());
			pstmt.setString(2, trainingclsdetailVO.getActionid());
			pstmt.setString(3, trainingclsdetailVO.getTrainingclsdetailid());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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

	public void delete(String trainingclsdetailid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, trainingclsdetailid);

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

	@Override
	public List<TrainingClsDetailVO> getAll() {
		List<TrainingClsDetailVO> list = new ArrayList<TrainingClsDetailVO>();
		TrainingClsDetailVO trainingclsdetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				trainingclsdetailVO = new TrainingClsDetailVO();
				trainingclsdetailVO.setTrainingclsdetailid(rs.getString("trainingclsdetailid"));
				trainingclsdetailVO.setTrainingclsid(rs.getString("trainingclsid"));
				trainingclsdetailVO.setActionid(rs.getString("actionid"));
				list.add(trainingclsdetailVO); // Store the row in the list
			}

			// Handle any driver errors
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

//	搜尋主鍵  
	public TrainingClsDetailVO findByPrimaryKey(String trainingclsdetailid) {

		TrainingClsDetailVO trainingclsdetail = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, trainingclsdetailid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// trainingclsdetailVo 也稱為 Domain objects
				trainingclsdetail = new TrainingClsDetailVO();
				trainingclsdetail.setTrainingclsdetailid(rs.getString("trainingclsdetailid"));
				trainingclsdetail.setTrainingclsid(rs.getString("trainingclsid"));
				trainingclsdetail.setActionid(rs.getString("actionid"));

			}

			// Handle any driver errors
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
		return trainingclsdetail;
	}

//Select:用課程Trainingclsid搜尋所有的Action

	public List<TrainingClsDetailVO> select(String trainingclsid) {
		List<TrainingClsDetailVO> list = new ArrayList<TrainingClsDetailVO>();
		TrainingClsDetailVO trainingclsdetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT);

			pstmt.setString(1, trainingclsid);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				trainingclsdetailVO = new TrainingClsDetailVO();
				trainingclsdetailVO.setTrainingclsdetailid(rs.getString("trainingclsdetailid"));
				trainingclsdetailVO.setTrainingclsid(rs.getString("trainingclsid"));
				trainingclsdetailVO.setActionid(rs.getString("actionid"));
				list.add(trainingclsdetailVO);
			}

			// Handle any driver errors
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
	
	//--會員編號搜尋所有的課程內容
	public List<TrainingClsDetailVO> select_memberid(String memberid) {
		List<TrainingClsDetailVO> list = new ArrayList<TrainingClsDetailVO>();
		TrainingClsDetailVO trainingclsdetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT);

			pstmt.setString(1, memberid);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				trainingclsdetailVO = new TrainingClsDetailVO();
				trainingclsdetailVO.setTrainingclsdetailid(rs.getString("trainingclsdetailid"));
				trainingclsdetailVO.setTrainingclsid(rs.getString("trainingclsid"));
				trainingclsdetailVO.setActionid(rs.getString("actionid"));
				list.add(trainingclsdetailVO);
			}

			// Handle any driver errors
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
		
		TrainingClsDetailDAO dao = new TrainingClsDetailDAO();
		List<TrainingClsDetailVO> list = dao.select_memberid("M001");
		for (TrainingClsDetailVO vo : list) {
			System.out.println(vo.getTrainingclsdetailid());
			System.out.println(vo.getTrainingclsid());
			System.out.println(vo.getActionid());
			System.out.println("======================");

		}
		
//用課程編號查詢所有的ACTion
//		TrainingClsDetailJDBCDAO dao = new TrainingClsDetailJDBCDAO();
//		List<TrainingClsDetailVO> list = dao.select("CLS009");
//		for (TrainingClsDetailVO vo : list) {
//			System.out.println(vo.getTrainingclsdetailid());
//			System.out.println(vo.getTrainingclsid());
//			System.out.println(vo.getActionid());
//			System.out.println("======================");
//
//		}

		// 新增
//		TrainingClsDetailVO trainingclsdetailVO1 = new TrainingClsDetailVO();
//		trainingclsdetailVO1.setTrainingclsid("CLS020");
//		trainingclsdetailVO1.setActionid("AC008");
//		dao.insert(trainingclsdetailVO1);
//		System.out.println("ok");

		// 修改
//		TrainingClsDetailVO trainingclsdetailVO2 = new TrainingClsDetailVO();
//		trainingclsdetailVO2.setTrainingclsdetailid("TD003");
//		trainingclsdetailVO2.setTrainingclsid("CLS002");
//		trainingclsdetailVO2.setActionid("AC001");
//		trainingclsdetailVO2.setSeqid(3);
//		dao.update(trainingclsdetailVO2);

		// 刪除
//		dao.delete(7014);

//		
//		TrainingClsDetailVO trainingclsdetailVO2 = new TrainingClsDetailVO();
//		trainingclsdetailVO2.setTrainingclsid("CLS002");
//		dao.select(trainingclsdetailVO2);

//		// 查詢
		TrainingClsDetailVO trainingclsdetailVO3 = dao.findByPrimaryKey("TD001");
		System.out.print(trainingclsdetailVO3.getTrainingclsid() + ",");

//測試SELECT方法		
		List<TrainingClsDetailVO> trainingClsDetailVOs = dao.select("CLS020");
		for (TrainingClsDetailVO vo : trainingClsDetailVOs) {
			System.out.println("vo.getTrainingclsdetailid = " + vo.getTrainingclsdetailid());
			System.out.println("vo.getTrainingclsid = " + vo.getTrainingclsid());
			System.out.println("vo.getActionid = " + vo.getActionid());
		}
		
//
//		System.out.print(trainingclsdetailVO3.getTrainingclsnm() + ",");
//		System.out.print(trainingclsdetailVO3.getTrainingclsdetail() + ",");
//		System.out.println(trainingclsdetailVO3.getPosttime());
//		System.out.println(trainingclsdetailVO3.getUpdatetime());
//		System.out.println("---------------------");

		// 查詢
//		List<TrainingClsDetailVO> list = dao.getAll();
//		for (TrainingClsDetailVO xxx : list) {
//			System.out.println(xxx.getActionid());
//		}
	}

	@Override
	public List<TrainingClsDetailVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
}
