package com.bodyrecord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BodyrecordJDBCDAO implements BodyrecordDAO_interface{
	String driver ="oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "tea101g4";
	String passwd = "123456";
	
	
	private static final String INSERT_STMT =
			"INSERT INTO bodyrecord2 (bodyrecordid,memberid,uploaddate,photo1,description,describestatus,updatetime) VALUES (bodyrecord2_seq.NEXTVAL,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT bodyrecordid,memberid,to_char(uploaddate,'yyyy-mm-dd') uploaddate,photo1,description,describestatus,to_char(updatetime,'yyyy-mm--dd') updatetime FROM bodyrecord2 order by bodyrecordid";
	private static final String GET_ONE_STMT =
			"SELECT bodyrecordid,memberid,to_char(uploaddate,'yyyy-mm-dd') uploaddate,photo1,description,describestatus,to_char(updatetime,'yyyy-mm--dd') updatetime FROM bodyrecord2 where bodyrecordid = ?";
	private static final String DELETE =
			"DELETE FROM bodyrecord2 where bodyrecordid = ?";
	private static final String UPDATE =
			"UPDATE bodyrecord2 set memberid = ?,uploaddate = ?,photo1 = ?,description = ?,describestatus = ?,updatetime = ?";
	
	@Override
	public void insert(BodyrecordVO bodyrecordVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, bodyrecordVO.getmemberId());
			pstmt.setDate(2, bodyrecordVO.getuploaddate());
			pstmt.setBytes(3, bodyrecordVO.getphoto1());
			pstmt.setString(4, bodyrecordVO.getdescription());
			pstmt.setString(5, bodyrecordVO.getdescribestatus());
			pstmt.setDate(6, bodyrecordVO.getupdatetime());
			
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occures."+ se.getMessage());
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void update(BodyrecordVO bodyrecordVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, bodyrecordVO.getmemberId());
			pstmt.setDate(2, bodyrecordVO.getuploaddate());
			pstmt.setBytes(3, bodyrecordVO.getphoto1());
			pstmt.setString(4, bodyrecordVO.getdescription());
			pstmt.setString(5, bodyrecordVO.getdescribestatus());
			pstmt.setDate(6, bodyrecordVO.getupdatetime());
			pstmt.setString(7, bodyrecordVO.getbodyrecordid());
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occures."+ se.getMessage());
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void delete(String bodyrecordid) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);			
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, bodyrecordid);
			pstmt.executeUpdate();
			
			
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
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
	public BodyrecordVO findByPrimaryKey(String bodyrecordid) {
		
		BodyrecordVO bodyrecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, bodyrecordid);
			
			while (rs.next()) {
				
				bodyrecordVO = new BodyrecordVO();
				bodyrecordVO.setbodyrecordid(rs.getString("bodyrecordid"));
				bodyrecordVO.setmemberId(rs.getString("memberid"));
				bodyrecordVO.setuploaddate(rs.getDate("uploaddate"));
				bodyrecordVO.setphoto1(rs.getBytes("photo1"));
				bodyrecordVO.setdescription(rs.getString("description"));
				bodyrecordVO.setdescribestatus(rs.getString("describestatus"));
				bodyrecordVO.setupdatetime(rs.getDate("updatetime"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

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
		return bodyrecordVO;
	}
	@Override
	public List<BodyrecordVO> getAll(){
		List<BodyrecordVO> list = new ArrayList<BodyrecordVO>();
		BodyrecordVO bodyrecordVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bodyrecordVO = new BodyrecordVO();
				bodyrecordVO.setbodyrecordid(rs.getString("bodyrecordid"));
				bodyrecordVO.setmemberId(rs.getString("memberid"));
				bodyrecordVO.setuploaddate(rs.getDate("uploaddate"));
				bodyrecordVO.setphoto1(rs.getBytes("photo1"));
				bodyrecordVO.setdescription(rs.getString("description"));
				bodyrecordVO.setdescribestatus(rs.getString("describestatus"));
				bodyrecordVO.setupdatetime(rs.getDate("updatetime"));
				list.add(bodyrecordVO);
			}
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

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
		
		BodyrecordJDBCDAO dao = new BodyrecordJDBCDAO();
		
		//�s�W
//		BodyrecordVO bodyrecordVO1 = new BodyrecordVO();
//		bodyrecordVO1.setMemberid("M001");
//		bodyrecordVO1.setUploaddate(java.sql.Date.valueOf("2005-01-01"));
//		bodyrecordVO1.setPhoto1("");
//		bodyrecordVO1.setDescription("");
//		bodyrecordVO1.setDescribestatus("");
//		bodyrecordVO1.setUpdatetime("");
//		dao.insert(bodyrecordVO1);
//		
//		dao.delete();
		
		//�d��
		BodyrecordVO bodyrecordVO3 = dao.findByPrimaryKey(null);
		System.out.print(bodyrecordVO3.getbodyrecordid() + ",");
		System.out.print(bodyrecordVO3.getmemberId() + ",");
		System.out.print(bodyrecordVO3.getuploaddate() + ",");
		System.out.print(bodyrecordVO3.getphoto1() + ",");
		System.out.print(bodyrecordVO3.getdescription() + ",");
		System.out.print(bodyrecordVO3.getdescribestatus() + ",");
		System.out.print(bodyrecordVO3.getupdatetime() + ",");
		System.out.println(bodyrecordVO3.getmemberId());
		System.out.println("---------------------");
		
		List<BodyrecordVO> list = dao.getAll();
		for (BodyrecordVO aEmp : list) {
			System.out.print(aEmp.getbodyrecordid() + ",");
			System.out.print(aEmp.getmemberId() + ",");
			System.out.print(aEmp.getuploaddate() + ",");
			System.out.print(aEmp.getphoto1() + ",");
			System.out.print(aEmp.getdescription() + ",");
			System.out.print(aEmp.getdescribestatus() + ",");
			System.out.print(aEmp.getupdatetime() + ",");
			System.out.print(aEmp.getmemberId());
			System.out.println();
		}
	}
}
