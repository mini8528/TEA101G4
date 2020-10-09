package com.blog_save.model;

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



public class Blog_SaveDAO implements Blog_SaveDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO blog_save (blogsaveno,memberid,blogno,status,savedate,updatetime) VALUES ('BS' || lpad(BLOGSAVE_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT blogsaveno,memberid,blogno,status,to_char(savedate,'yyyy-mm-dd hh24:mi:ss') savedate,to_char(updatetime,'yyyy-mm-dd hh24:mi:ss') updatetime FROM blog_save order by blogsaveno";
	private static final String GET_ONE_STMT = "SELECT blogsaveno,memberid,blogno,status,to_char(savedate,'yyyy-mm-dd hh24:mi:ss') savedate,to_char(updatetime,'yyyy-mm-dd hh24:mi:ss') updatetime FROM blog_save where blogsaveno = ?";
	private static final String DELETE = "DELETE FROM blog_save where blogsaveno = ?";
	private static final String UPDATE = "UPDATE blog_save set memberid=?, blogno=?, status=?, savedate=? , updatetime=? where blogsaveno = ?";
	private static final String GET_MEMBER_SAVE = "SELECT blogsaveno,memberid,blogno,status,to_char(savedate,'yyyy-mm-dd hh24:mi:ss') savedate,to_char(updatetime,'yyyy-mm-dd hh24:mi:ss') updatetime FROM blog_save where memberid = ? order by blogsaveno";
	private static final String BLOGSAVE_LEFT_JOIN_BLOG = "SELECT * FROM BLOG_SAVE LEFT JOIN BLOG ON BLOG_SAVE.BLOGNO = BLOG.BLOGNO where memberid = ?";
	private static final String GET_STATUS = "SELECT *FROM blog_save where blogno = ? and memberid = ?";
	
	@Override
	public void insert(Blog_SaveVO blogSaveVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, blogSaveVO.getMemberId());
			pstmt.setString(2, blogSaveVO.getBlogno());
			pstmt.setString(3, blogSaveVO.getStatus());
			pstmt.setTimestamp(4, blogSaveVO.getSaveDate());
			pstmt.setTimestamp(5, blogSaveVO.getUpdateTime());

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
	public void update(Blog_SaveVO blogSaveVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, blogSaveVO.getMemberId());
			pstmt.setString(2, blogSaveVO.getBlogno());
			pstmt.setString(3, blogSaveVO.getStatus());	
			pstmt.setTimestamp(4, blogSaveVO.getSaveDate());
			pstmt.setTimestamp(5, blogSaveVO.getUpdateTime());
			pstmt.setString(6, blogSaveVO.getBlogSaveno());

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
	public void delete(String blogSaveno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, blogSaveno);

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
	public Blog_SaveVO findByPrimaryKey(String blogSaveno) {
		Blog_SaveVO blogsaveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, blogSaveno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				blogsaveVO = new Blog_SaveVO();
				
				blogsaveVO.setBlogSaveno(rs.getString("blogsaveno"));
				blogsaveVO.setMemberId(rs.getString("memberid"));
				blogsaveVO.setBlogno(rs.getString("blogno"));
				blogsaveVO.setStatus(rs.getString("status"));
				blogsaveVO.setSaveDate(rs.getTimestamp("savedate"));
				blogsaveVO.setUpdateTime(rs.getTimestamp("updatetime"));
				
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
		return blogsaveVO;
	}

	@Override
	public List<Blog_SaveVO> getAll() {
		List<Blog_SaveVO> list = new ArrayList<Blog_SaveVO>();
		Blog_SaveVO blogsaveVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				blogsaveVO = new Blog_SaveVO();
				
				blogsaveVO.setBlogSaveno(rs.getString("blogsaveno"));
				blogsaveVO.setMemberId(rs.getString("memberid"));
				blogsaveVO.setBlogno(rs.getString("blogno"));
				blogsaveVO.setStatus(rs.getString("status"));
				blogsaveVO.setSaveDate(rs.getTimestamp("savedate"));
				blogsaveVO.setUpdateTime(rs.getTimestamp("updatetime"));
				
				list.add(blogsaveVO);
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
	public List<Blog_SaveVO> findByMemberId(String memberId) {
		
		List<Blog_SaveVO> list = new ArrayList<Blog_SaveVO>();
		Blog_SaveVO blogsaveVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEMBER_SAVE);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				blogsaveVO = new Blog_SaveVO();
				
				blogsaveVO.setBlogSaveno(rs.getString("blogsaveno"));
				blogsaveVO.setMemberId(rs.getString("memberid"));
				blogsaveVO.setBlogno(rs.getString("blogno"));
				blogsaveVO.setStatus(rs.getString("status"));
				blogsaveVO.setSaveDate(rs.getTimestamp("savedate"));
				blogsaveVO.setUpdateTime(rs.getTimestamp("updatetime"));
				
				list.add(blogsaveVO);
				
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
	
	@Override
	public Blog_SaveVO findBlogSaveStatus(String blogno, String memberId) {
		Blog_SaveVO blogsaveVO = null;
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
				blogsaveVO = new Blog_SaveVO();
				
				blogsaveVO.setBlogSaveno(rs.getString("blogsaveno"));
				blogsaveVO.setMemberId(rs.getString("memberid"));
				blogsaveVO.setBlogno(rs.getString("blogno"));
				blogsaveVO.setStatus(rs.getString("status"));
				blogsaveVO.setSaveDate(rs.getTimestamp("savedate"));
				blogsaveVO.setUpdateTime(rs.getTimestamp("updatetime"));
				
				
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
		return blogsaveVO;
	}
	
	

}
