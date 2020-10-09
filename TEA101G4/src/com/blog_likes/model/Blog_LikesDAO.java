package com.blog_likes.model;

import java.sql.Connection;
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

public class Blog_LikesDAO implements Blog_LikesDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO blog_likes (bloglikesno,blogno,memberid,status,likesdate,updatetime) VALUES ('BL' || lpad(BLOGSAVE_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT bloglikesno,blogno,memberid,status,to_char(likesdate,'yyyy-mm-dd hh24:mi:ss') likesdate,to_char(updatetime,'yyyy-mm-dd hh24:mi:ss') updatetime FROM blog_likes order by bloglikesno";
	private static final String GET_ONE_STMT = "SELECT bloglikesno,blogno,memberid,status,to_char(likesdate,'yyyy-mm-dd hh24:mi:ss') likesdate,to_char(updatetime,'yyyy-mm-dd hh24:mi:ss') updatetime FROM blog_likes where bloglikesno = ?";
	private static final String DELETE = "DELETE FROM blog_likes where bloglikesno = ?";
	private static final String UPDATE = "UPDATE blog_likes set blogno=?, memberid=?, status=?, likesdate=? , updatetime=? where bloglikesno = ?";
	private static final String GET_STATUS = "SELECT *FROM blog_likes where blogno = ? and memberid = ?";
	private static final String GET_BLOG_LIKES = "SELECT *FROM blog_likes where blogno = ?";
	
	@Override
	public void insert(Blog_LikesVO blogLikesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, blogLikesVO.getBlogno());
			pstmt.setString(2, blogLikesVO.getMemberId());
			pstmt.setString(3, blogLikesVO.getStatus());
			pstmt.setTimestamp(4, blogLikesVO.getLikesDate());
			pstmt.setTimestamp(5, blogLikesVO.getUpdateTime());

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
	public void update(Blog_LikesVO blogLikesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, blogLikesVO.getBlogno());
			pstmt.setString(2, blogLikesVO.getMemberId());			
			pstmt.setString(3, blogLikesVO.getStatus());	
			pstmt.setTimestamp(4, blogLikesVO.getLikesDate());
			pstmt.setTimestamp(5, blogLikesVO.getUpdateTime());
			pstmt.setString(6, blogLikesVO.getBlogLikesno());

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
	public void delete(String blogLikesno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, blogLikesno);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public Blog_LikesVO findByPrimaryKey(String blogLikesno) {
		Blog_LikesVO bloglikesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, blogLikesno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				bloglikesVO = new Blog_LikesVO();
				
				bloglikesVO.setBlogLikesno(rs.getString("bloglikesno"));
				bloglikesVO.setBlogno(rs.getString("blogno"));
				bloglikesVO.setMemberId(rs.getString("memberid"));
				bloglikesVO.setStatus(rs.getString("status"));
				bloglikesVO.setLikesDate(rs.getTimestamp("likesdate"));
				bloglikesVO.setUpdateTime(rs.getTimestamp("updatetime"));
				
			}

			// Handle any driver errors
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
		return bloglikesVO;
	}

	@Override
	public List<Blog_LikesVO> getAll() {
		List<Blog_LikesVO> list = new ArrayList<Blog_LikesVO>();
		Blog_LikesVO bloglikesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bloglikesVO = new Blog_LikesVO();
				

				bloglikesVO.setBlogLikesno(rs.getString("bloglikesno"));
				bloglikesVO.setBlogno(rs.getString("blogno"));
				bloglikesVO.setMemberId(rs.getString("memberid"));
				bloglikesVO.setStatus(rs.getString("status"));
				bloglikesVO.setLikesDate(rs.getTimestamp("likesdate"));
				bloglikesVO.setUpdateTime(rs.getTimestamp("updatetime"));
				
				list.add(bloglikesVO);
			}

			// Handle any driver errors
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
	public Blog_LikesVO findRecordStatus(String blogno, String memberId) {
		Blog_LikesVO bloglikesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STATUS);

			pstmt.setString(1, blogno);
			pstmt.setString(2, memberId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				bloglikesVO = new Blog_LikesVO();
				
				bloglikesVO.setBlogLikesno(rs.getString("bloglikesno"));
				bloglikesVO.setBlogno(rs.getString("blogno"));
				bloglikesVO.setMemberId(rs.getString("memberid"));
				bloglikesVO.setStatus(rs.getString("status"));
				bloglikesVO.setLikesDate(rs.getTimestamp("likesdate"));
				bloglikesVO.setUpdateTime(rs.getTimestamp("updatetime"));
				
				
			}

			// Handle any driver errors
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
		return bloglikesVO;
	}

	@Override
	public List<Blog_LikesVO> findByBlogno(String blogno) {
		List<Blog_LikesVO> list = new ArrayList<Blog_LikesVO>();
		Blog_LikesVO bloglikesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BLOG_LIKES);

			pstmt.setString(1, blogno);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				bloglikesVO = new Blog_LikesVO();
				
				bloglikesVO.setBlogLikesno(rs.getString("bloglikesno"));
				bloglikesVO.setBlogno(rs.getString("blogno"));
				bloglikesVO.setMemberId(rs.getString("memberid"));
				bloglikesVO.setStatus(rs.getString("status"));
				bloglikesVO.setLikesDate(rs.getTimestamp("likesdate"));
				bloglikesVO.setUpdateTime(rs.getTimestamp("updatetime"));
				list.add(bloglikesVO);
				
			}

			// Handle any driver errors
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

}
