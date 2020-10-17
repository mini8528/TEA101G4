package com.trainingcls.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
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

import com.trainingcls.model.TrainingClsVO;
import com.trainingclsdetail.model.TrainingClsDetailVO;

public class TrainingClsDAO implements TrainingClsDAOIntf {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO trainingcls (trainingclsid,memberid,trainingclsnm,posttime,updatetime) VALUES (('CLS' || lpad(CLSNM_SEQ.NEXTVAL, 3, '0') ), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT trainingclsid,memberid,trainingclsnm,posttime,updatetime FROM trainingcls order by trainingclsid";
	private static final String GET_ONE_STMT = "SELECT trainingclsid,memberid,trainingclsnm,posttime,updatetime FROM trainingcls where trainingclsid = ?";
	private static final String GET_BY_CLASS = "SELECT trainingclsid,memberid,trainingclsnm,posttime,updatetime FROM trainingcls order by trainingclsnm";
	private static final String DELETE = "DELETE FROM trainingcls where trainingclsid = ?";
	private static final String UPDATE = "UPDATE trainingcls set memberid=?, trainingclsnm=?, posttime=? , updatetime = ? where trainingclsid = ?";
	private static final String SELECT = "SELECT trainingclsid,memberid,trainingclsnm,posttime,updatetime FROM trainingcls where MEMBERID = ?";

	//--查詢會員所有的課程
	public String insert(TrainingClsVO trainingclsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			String[] col = { "trainingclsid" };
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, col);

			pstmt.setString(1, trainingclsVO.getMemberid());
			pstmt.setString(2, trainingclsVO.getTrainingclsnm());
			pstmt.setDate(3, trainingclsVO.getPosttime());
			pstmt.setDate(4, trainingclsVO.getUpdatetime());

			pstmt.executeUpdate();
// --取得對應的自增主鍵值
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getString(1);
			} else {
				System.out.println("未取得自增主鍵值");
			}
			

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
		return "";
	}

	@Override
	public void update(TrainingClsVO trainingclsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, trainingclsVO.getMemberid());
			pstmt.setString(2, trainingclsVO.getTrainingclsnm());
			pstmt.setDate(4, trainingclsVO.getPosttime());
			pstmt.setDate(5, trainingclsVO.getUpdatetime());
			pstmt.setString(6, trainingclsVO.getTrainingclsid());

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

//--會員編號搜尋所有的課程內容
	public List<TrainingClsVO> select(String memberid) {
		List<TrainingClsVO> list = new ArrayList<TrainingClsVO>();
		TrainingClsVO trainingclsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT);
			
			pstmt.setString(1, memberid);
			rs = pstmt.executeQuery();

		
			while (rs.next()) {

				trainingclsVO = new TrainingClsVO();
				trainingclsVO.setTrainingclsid(rs.getString("trainingclsid"));
				trainingclsVO.setMemberid(rs.getString("memberid"));
				trainingclsVO.setTrainingclsnm(rs.getString("trainingclsnm"));
				trainingclsVO.setPosttime(rs.getDate("posttime"));
				trainingclsVO.setUpdatetime(rs.getDate("updatetime"));
				list.add(trainingclsVO); 
			}

			// Handle any driver errors
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
		return list;
	}

	@Override
	public void delete(String trainingclsid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, trainingclsid);

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
	public TrainingClsVO findByPrimaryKey(String trainingclsid) {

		TrainingClsVO trainingClsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, trainingclsid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// trainingclsVO 也稱為 Domain objects
				trainingClsVO = new TrainingClsVO();
				trainingClsVO.setTrainingclsid(rs.getString("trainingclsid"));
				trainingClsVO.setMemberid(rs.getString("memberid"));
				trainingClsVO.setTrainingclsnm(rs.getString("trainingclsnm"));
				trainingClsVO.setPosttime(rs.getDate("posttime"));
				trainingClsVO.setUpdatetime(rs.getDate("updatetime"));
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
		return trainingClsVO;
	}

	@Override
	public List<TrainingClsVO> getAll() {
		List<TrainingClsVO> list = new ArrayList<TrainingClsVO>();
		TrainingClsVO trainingClsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				trainingClsVO = new TrainingClsVO();
				trainingClsVO.setTrainingclsid(rs.getString("trainingclsid"));
				trainingClsVO.setMemberid(rs.getString("memberid"));
				trainingClsVO.setTrainingclsnm(rs.getString("trainingclsnm"));
				trainingClsVO.setPosttime(rs.getDate("posttime"));
				trainingClsVO.setUpdatetime(rs.getDate("updatetime"));
				list.add(trainingClsVO); // Store the row in the list
			}

			// Handle any driver errors
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
		return list;
	}

//測試	    
	public static void main(String[] args) {

		TrainingClsDAO dao = new TrainingClsDAO();

		// 新增
//		TrainingClsVO trainingclsVO1 = new TrainingClsVO();
//		trainingclsVO1.setMemberid("M003");
//		trainingclsVO1.setTrainingclsnm("xxx");
//		trainingclsVO1.setPosttime(java.sql.Date.valueOf("2020-09-10"));
//		trainingclsVO1.setUpdatetime(java.sql.Date.valueOf("2020-09-10"));
//		dao.insert(trainingclsVO1);
//		System.out.println("ok");
		
//		TrainingClsService tcs = new TrainingClsService();
//		tcs.addTrainingCls("M002", "手臂運動", java.sql.Date.valueOf("2020-09-29"), java.sql.Date.valueOf("2020-09-29"));
//		System.out.println();

		// 修改
//			TrainingClsVO trainingclsVO2 = new TrainingClsVO();
//			trainingclsVO2.setTrainingclsid("tr001");
//			trainingclsVO2.setMemberid("M001");
//			trainingclsVO2.setAdminid("ad001");
//			trainingclsVO2.setTrainingclsnm("ooooo");
//			trainingclsVO2.setPosttime(java.sql.Date.valueOf("2020-09-20"));
//			trainingclsVO2.setUpdatetime(java.sql.Date.valueOf("2020-09-20"));
//			dao.update(trainingclsVO2);

		// 刪除
//			dao.delete(7014);

		// 查詢
//		TrainingClsVO trainingclsVO3 = dao.findByPrimaryKey("CLS001");
//		System.out.print(trainingclsVO3.getTrainingclsid() + ",");
//		System.out.print(trainingclsVO3.getMemberid() + ",");
//		System.out.print(trainingclsVO3.getTrainingclsnm() + ",");
//		System.out.println(trainingclsVO3.getPosttime());
//		System.out.println(trainingclsVO3.getUpdatetime());
//		System.out.println("---------------------");
//測試Select方法
		List<TrainingClsVO> trainingClsVOs = dao.select("M002");
		for (TrainingClsVO vo : trainingClsVOs) {
			System.out.println("vo.getMemberid = " + vo.getMemberid());
			System.out.println("vo.getTrainingclsid = " + vo.getTrainingclsid());
			System.out.println("vo.getTrainingclsnm = " + vo.getTrainingclsnm());
			
		}
		// 查詢
//			List<TrainingClsVO> list = dao.getAll();
//			for (TrainingClsVO xx : list) {
//				System.out.print(xx.getTrainingclsid() + ",");
//				System.out.print(xx.getAdminid());
//				System.out.println();
//			}
	}

	



}

