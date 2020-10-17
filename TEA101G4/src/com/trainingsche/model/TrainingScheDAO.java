package com.trainingsche.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Timestamp;
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

import com.traininghist.model.TrainingHistJDBCDAO;
import com.traininghist.model.TrainingHistVO;

public class TrainingScheDAO implements TrainingScheDAOIntf{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

	private static final String INSERT_STMT = "INSERT INTO trainingsche (trainingscheid,trainingclsid,memberid,starttime,endtime) VALUES (('SCH' || lpad(SCHE_SEQ.NEXTVAL, 3, '0')), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT trainingscheid,trainingclsid,memberid,starttime,endtime FROM trainingsche order by trainingscheid";
	private static final String GET_ONE_STMT = "SELECT trainingscheid,trainingclsid,memberid,starttime,endtime FROM trainingsche where trainingscheid = ?";
	private static final String DELETE = "DELETE FROM trainingsche where trainingscheid = ?";
	private static final String UPDATE = "UPDATE trainingsche set trainingclsid=?, memberid=?, starttime=?, endtime=? where trainingscheid =?";
	private static final String SELECT = "SELECT trainingscheid,trainingclsid,memberid,starttime,endtime FROM trainingsche where memberid = ?";
	private static final String UPDATEENDTIME = "UPDATE trainingsche set endtime=? where trainingscheid =?";
	
	//	update trainingsche set endtime = TIMESTAMP '2020-10-13 14:05:59.10' where trainingscheid='SCH008';    
	
	@Override
	public  String insert(TrainingScheVO trainingscheVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
	    try {
// trainingSche 和 traininghist 自增主鍵值的綁定 
	    	
	    	con = ds.getConnection();
			String[] col = { "trainingscheid" };
			pstmt = con.prepareStatement(INSERT_STMT, col);

			pstmt.setString(1, trainingscheVO.getTrainingclsid());
			pstmt.setString(2, trainingscheVO.getMemberid());
			pstmt.setTimestamp(3, trainingscheVO.getStarttime());
			pstmt.setTimestamp(4, trainingscheVO.getEndtime());
			
			pstmt.executeUpdate();
//--取得對應的自增主鍵值
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
//				trainingscheVO.setTrainingscheid(rs.getString(1));
				return rs.getString(1);
			} else {
				System.out.println("未取得自增主鍵值");
			}
			// Handle any SQL errors
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
	    return "";
    }
//自增主鍵	
	@Override
	public void insertWithHist(TrainingScheVO trainingScheVO , List<TrainingHistVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
		
    		con.setAutoCommit(false);
			
    		
			String cols[] = {"TRAININGSCHEID"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, trainingScheVO.getTrainingclsid());
			pstmt.setString(2, trainingScheVO.getMemberid());
			pstmt.setTimestamp(3, trainingScheVO.getStarttime());
			pstmt.setTimestamp(4, trainingScheVO.getEndtime());
			pstmt.executeUpdate();
		
			String next_trainingscheno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_trainingscheno = rs.getString(1);
				System.out.println("自增主鍵= " + next_trainingscheno +"(剛新增的)");
			} else {
				System.out.println("未取得自增主鍵");
			}
			rs.close();
			// 
			TrainingHistJDBCDAO dao = new TrainingHistJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (TrainingHistVO aTrainingHist : list) {
				aTrainingHist.setTrainingscheid(next_trainingscheno);
				dao.insert2(aTrainingHist,con);
			}

		
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增課程編號" + next_trainingscheno + ",....." + list.size()
					+ "個背心曾");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3���]�w���exception�o�ͮɤ�catch�϶���
					System.err.print("Transaction is being ");
					System.err.println("rolled back-��-dept");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	public void update(TrainingScheVO trainingscheVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, trainingscheVO.getTrainingclsid());
			pstmt.setString(2, trainingscheVO.getMemberid());
			pstmt.setTimestamp(3, trainingscheVO.getStarttime());
			pstmt.setTimestamp(4, trainingscheVO.getEndtime());
			pstmt.setString(5, trainingscheVO.getTrainingscheid());
			

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
//----------更新endtime----------	
	public void updateendtime(TrainingScheVO trainingscheVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEENDTIME);
			pstmt.setTimestamp(1, trainingscheVO.getEndtime());
			pstmt.setString(2, trainingscheVO.getTrainingscheid());
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
	
	public void delete(String trainingscheid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, trainingscheid);

			pstmt.executeUpdate();

			// Handle any driver errors
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

	public TrainingScheVO findByPrimaryKey(String trainingscheid) {

		TrainingScheVO trainingsche = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, trainingscheid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				trainingsche = new TrainingScheVO();
				trainingsche.setTrainingscheid(rs.getString("trainingscheid"));
				trainingsche.setTrainingscheid(rs.getString("trainingclsid"));
				trainingsche.setMemberid(rs.getString("memberid"));
				trainingsche.setStarttime(rs.getTimestamp("starttime"));
				trainingsche.setEndtime(rs.getTimestamp("endtime"));
				
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
		return trainingsche;
	}


public List<TrainingScheVO> getAll() {
	List<TrainingScheVO> list = new ArrayList<TrainingScheVO>();
	TrainingScheVO trainingscheVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();

		while (rs.next()) {

			trainingscheVO = new TrainingScheVO();
			trainingscheVO.setTrainingscheid(rs.getString("trainingScheid"));
			trainingscheVO.setTrainingclsid(rs.getString("trainingClsid"));
			trainingscheVO.setMemberid(rs.getString("memberid"));
			trainingscheVO.setStarttime(rs.getTimestamp("starttime"));
			trainingscheVO.setEndtime(rs.getTimestamp("endtime"));
			

			
			list.add(trainingscheVO); // Store the row in the list
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

//Select:用課程Memberid搜尋所有的訓練歷程
@Override
public List<TrainingScheVO> select(String memberid) {
	List<TrainingScheVO> list = new ArrayList<TrainingScheVO>();
	TrainingScheVO TrainingScheVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(memberid);
		
		
		pstmt.setString(1, memberid);
		rs = pstmt.executeQuery();

		while (rs.next()) {

			TrainingScheVO = new TrainingScheVO();
			TrainingScheVO.setTrainingscheid(rs.getString("trainingscheid"));
			TrainingScheVO.setTrainingscheid(rs.getString("trainingclsid"));
			TrainingScheVO.setMemberid(rs.getString("memberid"));
			TrainingScheVO.setStarttime(rs.getTimestamp("starttime"));
			TrainingScheVO.setEndtime(rs.getTimestamp("endtime"));
		
			
			
			
			list.add(TrainingScheVO); // Store the row in the list
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
		
		TrainingScheDAO dao = new TrainingScheDAO();
//測試自增主鍵		

		
//		TrainingScheVO trainingScheVO = new TrainingScheVO();
		
//		trainingScheVO.setTrainingclsid("CLS005");
//		trainingScheVO.setMemberid("M003");
//		trainingScheVO.setStarttime(java.sql.Timestamp.valueOf("2020-10-10 16:48:20"));
//		trainingScheVO.setEndtime(java.sql.Timestamp.valueOf("2020-10-10 16:48:20"));
//		
//		List<TrainingHistVO> testList = new ArrayList<TrainingHistVO>();
//		TrainingHistVO traininghistxx = new TrainingHistVO();
//		traininghistxx.setMemberid("M003");
//		traininghistxx.setActionid("AC003");
//		traininghistxx.setTrainingtime(50);
//		traininghistxx.setTrainingset(50);
//		traininghistxx.setTrainingrep(50);
//		traininghistxx.setTrainingwt(30);
//		
//		TrainingHistVO traininghistyy = new TrainingHistVO();
//		traininghistyy.setMemberid("M001");
//		traininghistyy.setActionid("AC001");
//		traininghistyy.setTrainingtime(40);
//		traininghistyy.setTrainingset(5);
//		traininghistyy.setTrainingrep(12);
//		traininghistyy.setTrainingwt(20);
//		
//		
//		testList.add(traininghistxx);
//		testList.add(traininghistyy);
//		
//		dao.insertWithHist(trainingScheVO , testList);
		
//		TrainingScheJDBCDAO dao = new TrainingScheJDBCDAO();

		// 新增
//		TrainingScheVO trainingScheVO1 = new TrainingScheVO();
//		trainingScheVO1.setTrainingclsid("CLS001");
//		trainingScheVO1.setMemberid("M002");
//		trainingScheVO1.setStarttime(java.sql.Timestamp.valueOf("2020-09-26 16:48:20"));
//		trainingScheVO1.setEndtime(java.sql.Timestamp.valueOf("2020-09-26 17:05:05"));
//		dao.insert(trainingScheVO1);
//		System.out.println("ok");
//
////		// 修改
//		TrainingScheVO trainingScheVO2 = new TrainingScheVO();
//		trainingScheVO2.setTrainingScheid("SCH010");
//		trainingScheVO2.setTrainingClsid("CLS002");
//		trainingScheVO2.setMemberid("M001");
//		trainingScheVO2.setStarttime(java.sql.Date.valueOf("2020-09-15"));
//		trainingScheVO2.setEndtime(java.sql.Date.valueOf("2020-09-15"));
//		dao.update(trainingScheVO2);
		
	
		TrainingScheVO trainingScheVO = new TrainingScheVO();
		trainingScheVO.setTrainingscheid("SCH010");
		trainingScheVO.setEndtime(java.sql.Timestamp.valueOf("2020-10-13 15:05:59.10"));
		dao.updateendtime(trainingScheVO);
//		

//
//		// 刪除
////	dao.delete(7014);
//
//		// 查詢
//		TrainingScheVO trainingScheVO3 = dao.findByPrimaryKey("SCH001");
//		System.out.print(trainingScheVO3.getTrainingscheid() + ",");
//		System.out.print(trainingScheVO3.getTrainingclsid() + ",");
//		System.out.print(trainingScheVO3.getMemberid() + ",");
//		System.out.print(trainingScheVO3.getStarttime() + ",");
//		System.out.print(trainingScheVO3.getEndtime() + ",");
//		System.out.println("---------------------");
		
		// 自增主鍵新增
//		TrainingScheVO trainingScheVO1 = new TrainingScheVO();
//		trainingScheVO1.setTrainingclsid("CLS007");
//		trainingScheVO1.setMemberid("M002");
//		trainingScheVO1.setStarttime(java.sql.Timestamp.valueOf("2020-09-26 16:48:20"));
//		trainingScheVO1.setEndtime(java.sql.Timestamp.valueOf("2020-09-26 17:05:05"));
//		dao.insert(trainingScheVO1);
//		System.out.println("ok");

		// 查詢
//		List<TrainingScheVO> list = dao.getAll();
//		for (TrainingScheVO xx : list) {
//			System.out.print(xx.getTrainingscheid() + ",");
//		
//			System.out.println();
//		}
	}
	

}
