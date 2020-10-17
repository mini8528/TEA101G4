package com.action.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.sql.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.trainingcls.model.TrainingClsJDBCDAO;
import com.trainingcls.model.TrainingClsVO;

public class ActionJDBCDAO implements ActionDaoIntf {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO action (actionid,actionnm,part,video,posttime,updatetime) VALUES(('AC' || lpad(ACT_SEQ.NEXTVAL, 3, '0')),?,?, ?,?, ?)";
	private static final String GET_ALL_STMT = "SELECT actionid,actionnm,part,video,posttime,updatetime FROM action order by actionid";
	private static final String GET_ONE_STMT = "SELECT actionid,actionnm,part,video,posttime,updatetime FROM action where actionid = ?";
	private static final String DELETE = "DELETE FROM action where actionid = ?";
	private static final String UPDATE = "UPDATE action set actionnm=?, part=?, video=? ,posttime=? , updatetime=? where actionid = ?";

	public void insert(ActionVO actionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, actionVO.getActionnm());
			pstmt.setString(2, actionVO.getPart());
			pstmt.setBytes(3, actionVO.getVideo());
			pstmt.setDate(4, actionVO.getPosttime());
			pstmt.setDate(5, actionVO.getUpdatetime());

			pstmt.executeUpdate();

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
	}

	@Override
	public void update(ActionVO actionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setString(1, actionVO.getActionnm());
			pstmt.setString(2, actionVO.getPart());
			pstmt.setBytes(3, actionVO.getVideo());
			pstmt.setDate(4, actionVO.getPosttime());
			pstmt.setDate(5, actionVO.getUpdatetime());
			pstmt.setString(6, actionVO.getActionid());

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

	public void delete(String actionid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, actionid);

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

	public ActionVO findByPrimaryKey(String actionid) {

		ActionVO act = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, actionid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
                
				act = new ActionVO();
				act.setActionid(rs.getString("actionid"));
				act.setActionnm(rs.getString("actionnm"));
				act.setPart(rs.getString("part"));
				System.out.println("ACTION FOUND...." + actionid);
				
				byte[] videoBytes = rs.getBytes("video");
				if (videoBytes == null || videoBytes.length == 0) {
					System.out.println("VIDEO NOT FOUND...." + actionid);
				} else {
					String base64Viedo = null;
					try {
						base64Viedo = Base64.getEncoder().encodeToString(videoBytes);
						act.setVideoBase64Url(base64Viedo);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				act.setPosttime(rs.getDate("posttime"));
				act.setUpdatetime(rs.getDate("updatetime"));

			}
		}  

		catch (SQLException se) {
			se.printStackTrace(System.err);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		finally {
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
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
		return act;
	}

	public List<ActionVO> getAll() {
		List<ActionVO> list = new ArrayList<ActionVO>();
		ActionVO actionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] base64Video = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				actionVO = new ActionVO();
				actionVO.setActionid(rs.getString("actionid"));
				actionVO.setActionnm(rs.getString("actionnm"));
				;
				actionVO.setPart(rs.getString("part"));
				actionVO.setVideo(rs.getBytes("video"));
				actionVO.setPosttime(rs.getDate("posttime"));
				actionVO.setUpdatetime(rs.getDate("updatetime"));
				list.add(actionVO); // Store the row in the list
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

		ActionJDBCDAO dao = new ActionJDBCDAO();

		// �s�W
//		ActionVO actionVO1 = new ActionVO();
//		actionVO1.setActionnm("xxx");
//		actionVO1.setPart("xxx"); 
//		byte[] myFileArray=null;
//		try {
//			RandomAccessFile f = new RandomAccessFile("C:\\video\\LatPullDown.mp4", "r");
//			myFileArray = new byte[(int) f.length()];
//			f.readFully(myFileArray);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("myFileArray "+myFileArray.length);
//		
//		actionVO1.setVideo(myFileArray);
//		actionVO1.setUpdatetime(java.sql.Date.valueOf("2020-09-16"));
//		actionVO1.setPosttime(java.sql.Date.valueOf("2020-09-16"));
//		dao.insert(actionVO1);

		// 新增
//	ActionVO ActionVO2 = new ActionVO();
//	ActionVO2.setActionid("AC001");

//	ActionVO2.setActionnm("ooooooooo");
//	ActionVO2.setPart("ooooooo");
//	byte[] myFileArray=null;
//	try {
//		RandomAccessFile f = new RandomAccessFile("C:\\video\\ReverseFly.mp4", "r");
//		myFileArray = new byte[(int) f.length()];
//		f.readFully(myFileArray);
//	} catch (FileNotFoundException e) {
//		e.printStackTrace();
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//	System.out.println("myFileArray "+myFileArray.length);
//	
//	ActionVO2.setVideo(myFileArray);
//	ActionVO2.setPosttime(java.sql.Date.valueOf("2020-09-16"));
//	ActionVO2.setUpdatetime(java.sql.Date.valueOf("2020-09-16"));
//	dao.update(ActionVO2);

		// 刪除
	dao.delete("AC001");

		// 查主鍵
//	System.out.println(dao.findByPrimaryKey("AC001"));


		// 茶全部資料
//		List<ActionVO> list = dao.getAll();
//		for (ActionVO xx : list) {
//			System.out.print(xx.getActionid() + ",");
//			System.out.println();
//		}
	}

}
