package com.blog_mes.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Blog_MesJDBCDAO implements Blog_MesDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO blog_mes (blogmesno,blogno,memberid,text,postdate,updatetime,status) VALUES ('BM' || lpad(BLOGMES_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT blogmesno,blogno,memberid,text,to_char(postdate,'yyyy-mm-dd hh24:mi:ss') postdate,to_char(updatetime,'yyyy-mm-dd hh24:mi:ss') updatetime,status FROM blog_mes order by blogmesno";
	private static final String GET_ONE_STMT = "SELECT blogmesno,blogno,memberid,text,to_char(postdate,'yyyy-mm-dd hh24:mi:ss') postdate,to_char(updatetime,'yyyy-mm-dd hh24:mi:ss') updatetime,status FROM blog_mes where blogmesno = ?";
	private static final String DELETE = "DELETE FROM blog_mes where blogmesno = ?";
	private static final String UPDATE = "UPDATE blog_mes set blogno=?, memberid=?, text=?, postdate=? , updatetime=?, status=? where blogmesno = ?";
	private static final String GET_ONE_BLOGNO = "SELECT blogmesno,blogno,memberid,text,to_char(postdate,'yyyy-mm-dd hh24:mi:ss') postdate,to_char(updatetime,'yyyy-mm-dd hh24:mi:ss') updatetime,status FROM blog_mes where blogno = ? and status='N' order by blogmesno";
	private static final String ADMIN_CHANGE_MES_STATUS = "UPDATE blog_mes set status=?, updatetime=? where blogmesno = ?";
	private static final String SEARCH_TEXT = "SELECT * FROM blog_mes where text like ? order by blogmesno";
	
	@Override
	public void insert(Blog_MesVO blogMesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, blogMesVO.getBlogno());
			pstmt.setString(2, blogMesVO.getMemberId());
			pstmt.setString(3, blogMesVO.getText());
			pstmt.setTimestamp(4, blogMesVO.getPostDate());
			pstmt.setTimestamp(5, blogMesVO.getUpdateTime());
			pstmt.setString(6, blogMesVO.getStatus());

			pstmt.executeUpdate();

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
	public void update(Blog_MesVO blogMesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, blogMesVO.getBlogno());
			pstmt.setString(2, blogMesVO.getMemberId());
			pstmt.setString(3, blogMesVO.getText());
			pstmt.setTimestamp(4, blogMesVO.getPostDate());
			pstmt.setTimestamp(5, blogMesVO.getUpdateTime());
			pstmt.setString(6, blogMesVO.getStatus());
			pstmt.setString(7, blogMesVO.getBlogMesno());

			pstmt.executeUpdate();

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
	public void delete(String blogMesno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, blogMesno);

			pstmt.executeUpdate();

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
	public Blog_MesVO findByPrimaryKey(String blogMesno) {
		Blog_MesVO blogmesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, blogMesno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				blogmesVO = new Blog_MesVO();

				blogmesVO.setBlogMesno(rs.getString("blogmesno"));
				blogmesVO.setBlogno(rs.getString("blogno"));
				blogmesVO.setMemberId(rs.getString("memberid"));
				blogmesVO.setText(rs.getString("text"));
				blogmesVO.setPostDate(rs.getTimestamp("postdate"));
				blogmesVO.setUpdateTime(rs.getTimestamp("updatetime"));
				blogmesVO.setStatus(rs.getString("status"));

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
		return blogmesVO;
	}

	@Override
	public List<Blog_MesVO> getAll() {
		List<Blog_MesVO> list = new ArrayList<Blog_MesVO>();
		Blog_MesVO blogmesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				blogmesVO = new Blog_MesVO();

				blogmesVO.setBlogMesno(rs.getString("blogmesno"));
				blogmesVO.setBlogno(rs.getString("blogno"));
				blogmesVO.setMemberId(rs.getString("memberid"));
				blogmesVO.setText(rs.getString("text"));
				blogmesVO.setPostDate(rs.getTimestamp("postdate"));
				blogmesVO.setUpdateTime(rs.getTimestamp("updatetime"));
				blogmesVO.setStatus(rs.getString("status"));

				list.add(blogmesVO);
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
		return list;
	}

	@Override
	public List<Blog_MesVO> findByBlogno(String blogno) {

		List<Blog_MesVO> list = new ArrayList<Blog_MesVO>();
		Blog_MesVO blogmesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BLOGNO);
			pstmt.setString(1, blogno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				blogmesVO = new Blog_MesVO();

				blogmesVO.setBlogMesno(rs.getString("blogmesno"));
				blogmesVO.setBlogno(rs.getString("blogno"));
				blogmesVO.setMemberId(rs.getString("memberid"));
				blogmesVO.setText(rs.getString("text"));
				blogmesVO.setPostDate(rs.getTimestamp("postdate"));
				blogmesVO.setUpdateTime(rs.getTimestamp("updatetime"));
				blogmesVO.setStatus(rs.getString("status"));

				list.add(blogmesVO);
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
		return list;
	}

	@Override
	public void changeStatus(String blogmesno, String status, Timestamp updatetime) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(ADMIN_CHANGE_MES_STATUS);

			pstmt.setString(1, status);
			pstmt.setTimestamp(2, updatetime);
			pstmt.setString(3, blogmesno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			se.printStackTrace();
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
	public List<Blog_MesVO> searchText(String text) {
		List<Blog_MesVO> list = new ArrayList<Blog_MesVO>();
		Blog_MesVO blogmesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SEARCH_TEXT);
			pstmt.setString(1, text);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				blogmesVO = new Blog_MesVO();

				blogmesVO.setBlogMesno(rs.getString("blogmesno"));
				blogmesVO.setBlogno(rs.getString("blogno"));
				blogmesVO.setMemberId(rs.getString("memberid"));
				blogmesVO.setText(rs.getString("text"));
				blogmesVO.setPostDate(rs.getTimestamp("postdate"));
				blogmesVO.setUpdateTime(rs.getTimestamp("updatetime"));
				blogmesVO.setStatus(rs.getString("status"));

				list.add(blogmesVO);
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
		return list;
	}

}
