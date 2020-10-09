package com.blog.model;

import java.util.*;
import java.sql.*;

public class BlogJDBCDAO implements BlogDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G4";
	String passwd = "123456";
	
//	String url = "jdbc:oracle:thin:@database-1.cxrxyl0rldfo.ap-northeast-1.rds.amazonaws.com:1521:orcl";
//	String userid = "admin";
//	String passwd = "mini3010";
    

	private static final String INSERT_STMT = "INSERT INTO blog (blogno,memberid,blogclass,postdate,title,text,photo,video,status,updatetime) VALUES ('B' || lpad(BLOG_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT blogno,memberid,blogclass,to_char(postdate,'yyyy-mm-dd hh24:mi:ss') postdate,title,text,status,to_char(updatetime,'yyyy-mm-dd hh24:mi:ss') updatetime FROM blog order by blogno";
	private static final String GET_ONE_STMT = "SELECT blogno,memberid,blogclass,to_char(postdate,'yyyy-mm-dd hh24:mi:ss') postdate,title,text,status,to_char(updatetime,'yyyy-mm-dd hh24:mi:ss') updatetime FROM blog where blogno = ? order by blogno";
	private static final String DELETE = "DELETE FROM blog where blogno = ?";
	private static final String UPDATE = "UPDATE blog set memberid=?, blogclass=?, postdate=?, title=?, text=? , photo=?, video=?, status=?, updatetime=? where blogno = ?";
	private static final String SEARCH_TITLE = "SELECT blogno,memberid,blogclass,to_char(postdate,'yyyy-mm-dd hh24:mi:ss') postdate,title,text,status,to_char(updatetime,'yyyy-mm-dd hh24:mi:ss') updatetime FROM blog where title like ? order by blogno";
	private static final String GET_MEMBER_BLOG = "SELECT blogno,memberid,blogclass,to_char(postdate,'yyyy-mm-dd hh24:mi:ss') postdate,title,text,status,to_char(updatetime,'yyyy-mm-dd hh24:mi:ss') updatetime FROM blog where memberid = ? order by blogno";
	private static final String UPDATE_STATUS = "UPDATE blog set status='Y' where blogno = ?";
	private static final String ADMIN_STATUS = "UPDATE blog set status=?, updatetime=? where blogno = ?";
	
	
	@Override
	public void insert(BlogVO blogVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, blogVO.getMemberId());
			pstmt.setString(2, blogVO.getBlogClass());
			pstmt.setTimestamp(3, blogVO.getPostDate());
			pstmt.setString(4, blogVO.getTitle());
			pstmt.setString(5, blogVO.getText());
			pstmt.setBytes(6, blogVO.getPhoto());
			pstmt.setBytes(7, blogVO.getVideo());
			pstmt.setString(8, blogVO.getStatus());
			pstmt.setTimestamp(9, blogVO.getUpdateTime());

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
	public void update(BlogVO blogVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, blogVO.getMemberId());
			pstmt.setString(2, blogVO.getBlogClass());
			pstmt.setTimestamp(3, blogVO.getPostDate());
			pstmt.setString(4, blogVO.getTitle());
			pstmt.setString(5, blogVO.getText());
			pstmt.setBytes(6, blogVO.getPhoto());
			pstmt.setBytes(7, blogVO.getVideo());
			pstmt.setString(8, blogVO.getStatus());
			pstmt.setTimestamp(9, blogVO.getUpdateTime());
			pstmt.setString(10, blogVO.getBlogno());

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
	public void delete(String blogno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, blogno);

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
	public BlogVO findByPrimaryKey(String blogno) {
		BlogVO blogVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, blogno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				blogVO = new BlogVO();
				
				blogVO.setBlogno(rs.getString("blogno"));
				blogVO.setMemberId(rs.getString("memberid"));
				blogVO.setBlogClass(rs.getString("blogclass"));
				blogVO.setPostDate(rs.getTimestamp("postdate"));
				blogVO.setTitle(rs.getString("title"));
				blogVO.setText(rs.getString("text"));
//				blogVO.setPhoto(rs.getBytes("photo"));
//				blogVO.setVideo(rs.getBytes("video"));
				blogVO.setStatus(rs.getString("status"));
				blogVO.setUpdateTime(rs.getTimestamp("updatetime"));
				
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
		return blogVO;
	}

	@Override
	public List<BlogVO> getAll() {
		List<BlogVO> list = new ArrayList<BlogVO>();
		BlogVO blogVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				blogVO = new BlogVO();
				
				blogVO.setBlogno(rs.getString("blogno"));
				blogVO.setMemberId(rs.getString("memberid"));
				blogVO.setBlogClass(rs.getString("blogclass"));
				blogVO.setPostDate(rs.getTimestamp("postdate"));
				blogVO.setTitle(rs.getString("title"));
				blogVO.setText(rs.getString("text"));
//				blogVO.setPhoto(rs.getBytes("photo"));
//				blogVO.setVideo(rs.getBytes("video"));
				blogVO.setStatus(rs.getString("status"));
				blogVO.setUpdateTime(rs.getTimestamp("updatetime"));
				
				list.add(blogVO);
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
	public List<BlogVO> searchTitle(String title) {
		List<BlogVO> list = new ArrayList<BlogVO>();
		BlogVO blogVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SEARCH_TITLE);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				blogVO = new BlogVO();
				
				blogVO.setBlogno(rs.getString("blogno"));
				blogVO.setMemberId(rs.getString("memberid"));
				blogVO.setBlogClass(rs.getString("blogclass"));
				blogVO.setPostDate(rs.getTimestamp("postdate"));
				blogVO.setTitle(rs.getString("title"));
				blogVO.setText(rs.getString("text"));
//				blogVO.setPhoto(rs.getBytes("photo"));
//				blogVO.setVideo(rs.getBytes("video"));
				blogVO.setStatus(rs.getString("status"));
				blogVO.setUpdateTime(rs.getTimestamp("updatetime"));
				
				list.add(blogVO);
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
	public List<BlogVO> findByMemberid(String memberId) {
		List<BlogVO> list = new ArrayList<BlogVO>();
		BlogVO blogVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MEMBER_BLOG);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				blogVO = new BlogVO();
				
				blogVO.setBlogno(rs.getString("blogno"));
				blogVO.setMemberId(rs.getString("memberid"));
				blogVO.setBlogClass(rs.getString("blogclass"));
				blogVO.setPostDate(rs.getTimestamp("postdate"));
				blogVO.setTitle(rs.getString("title"));
				blogVO.setText(rs.getString("text"));
//				blogVO.setPhoto(rs.getBytes("photo"));
//				blogVO.setVideo(rs.getBytes("video"));
				blogVO.setStatus(rs.getString("status"));
				blogVO.setUpdateTime(rs.getTimestamp("updatetime"));
				
				list.add(blogVO);
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
	public void hideBlog(String blogno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, blogno);

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
	public void changeStatus(String blogno, String status, Timestamp updatetime) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(ADMIN_STATUS);

			pstmt.setString(1, status);
			pstmt.setTimestamp(2, updatetime);
			pstmt.setString(3, blogno);

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
	
	
	
	
}