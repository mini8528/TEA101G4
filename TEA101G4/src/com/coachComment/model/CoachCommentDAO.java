package com.coachComment.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
import java.sql.Date;

import com.coachClass.model.CoachClassVO;
import com.coachComment.model.CoachCommentVO;
import com.member.model.MemberVO;

public class CoachCommentDAO implements CoachCommentDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO CoachComment (coachCommentID,memberID,memberID2,commText,commStar,addDate,editDate,status) VALUES ('CC' || lpad(COACHCOMMENT_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?, ? )";
	private static final String GET_ALL_STMT = "SELECT coachCommentID,memberID,memberID2,commText,commStar,addDate,editDate,status FROM CoachComment order by coachCommentID";
	private static final String GET_ONE_STMT = "SELECT coachCommentID,memberID,memberID2,commText,commStar,addDate,editDate,status FROM CoachComment where coachCommentID = ?";
	private static final String GET_ONE_STMT_MEMBER = "SELECT coachCommentID,memberID,memberID2,commText,commStar,addDate,editDate,status FROM CoachComment where memberID = ?";
	private static final String GET_ONE_COACH_COMMENT = "select coachCommentID,memberID,memberID2,commText,commStar,addDate,editDate,status from coachcomment where memberId = ?";
	private static final String GET_MEMBERID_FROM_COACHCLASSID ="select memberID from coachclass where coachclassid = ? ";
	private static final String GET_MEMBER_NAME_FROM_MEMBERID ="select name from member where memberid = ? ";
	
	private static final String DELETE = "DELETE FROM CoachComment where coachCommentID = ?";
	private static final String UPDATE = "UPDATE COACHCOMMENT set memberID=? ,memberID2=?, commText=?, commStar=? ,addDate=?, editDate=? ,status=?  where coachCommentID = ? ";

	private static final String UPDATE_SATAUS = "UPDATE CoachComment SET status=?,editDate=? WHERE coachCommentID = ?";
	
	private static final String coach_Star = "select COMMSTAR from coachComment where memberid = ?";
	
//	 memberID2=?

	@Override
	public void insert(CoachCommentVO coachCommentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, coachCommentVO.getMemberID());
			pstmt.setString(2, coachCommentVO.getMemberID2());
			pstmt.setString(3, coachCommentVO.getCommText());
			pstmt.setInt(4, coachCommentVO.getCommStar());
			pstmt.setTimestamp(5, coachCommentVO.getAddDate());
			pstmt.setTimestamp(6, coachCommentVO.getEditDate());
			pstmt.setString(7, coachCommentVO.getStatus());

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

	@Override
	public void update(CoachCommentVO coachCommentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setString(1, coachCommentVO.getMemberID());
			pstmt.setString(2, coachCommentVO.getMemberID2());
			pstmt.setString(3, coachCommentVO.getCommText());
			pstmt.setInt(4, coachCommentVO.getCommStar());
			pstmt.setTimestamp(5, coachCommentVO.getAddDate());
			pstmt.setTimestamp(6, coachCommentVO.getEditDate());
			pstmt.setString(7, coachCommentVO.getStatus());
			pstmt.setString(8, coachCommentVO.getCoachCommentID());

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
	public void delete(String coachCommentID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, coachCommentID);

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

	@Override
	public CoachCommentVO findByPrimaryKey(String coachCommentID) {

		CoachCommentVO coachCommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, coachCommentID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 銋迂� Domain objects
				coachCommentVO = new CoachCommentVO();
				coachCommentVO.setCoachCommentID(rs.getString("coachCommentID"));
				coachCommentVO.setMemberID(rs.getString("memberID"));
				coachCommentVO.setMemberID2(rs.getString("memberID2"));
				coachCommentVO.setCommText(rs.getString("commText"));
				coachCommentVO.setCommStar(rs.getInt("commStar"));
				coachCommentVO.setAddDate(rs.getTimestamp("addDate"));
				coachCommentVO.setEditDate(rs.getTimestamp("editDate"));
				coachCommentVO.setStatus(rs.getString("status"));
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
		return coachCommentVO;
	}

	@Override
	public List<CoachCommentVO> getAll() {
		List<CoachCommentVO> list = new ArrayList<CoachCommentVO>();
		CoachCommentVO coachCommentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				coachCommentVO = new CoachCommentVO();
				coachCommentVO.setCoachCommentID(rs.getString("coachCommentID"));
				coachCommentVO.setMemberID(rs.getString("memberID"));
				coachCommentVO.setMemberID2(rs.getString("memberID2"));
				coachCommentVO.setCommText(rs.getString("commText"));
				coachCommentVO.setCommStar(rs.getInt("commStar"));
				coachCommentVO.setAddDate(rs.getTimestamp("addDate"));
				coachCommentVO.setEditDate(rs.getTimestamp("editDate"));
				coachCommentVO.setStatus(rs.getString("status"));
				list.add(coachCommentVO); // Store the row in the list
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
	public void update_status(String coachCommentID, String status, Timestamp editDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE_SATAUS);

			pstmt.setString(1, status);
			pstmt.setTimestamp(2, editDate);
			pstmt.setString(3, coachCommentID);

			pstmt.executeUpdate();
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

	@Override
	public CoachCommentVO findByMemberID(String memberID) {
		
		CoachCommentVO coachCommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_MEMBER);

			pstmt.setString(1, memberID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 銋迂� Domain objects
				coachCommentVO = new CoachCommentVO();
				coachCommentVO.setCoachCommentID(rs.getString("coachCommentID"));
				coachCommentVO.setMemberID(rs.getString("memberID"));
				coachCommentVO.setMemberID2(rs.getString("memberID2"));
				coachCommentVO.setCommText(rs.getString("commText"));
				coachCommentVO.setCommStar(rs.getInt("commStar"));
				coachCommentVO.setAddDate(rs.getTimestamp("addDate"));
				coachCommentVO.setEditDate(rs.getTimestamp("editDate"));
				coachCommentVO.setStatus(rs.getString("status"));
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
		return coachCommentVO;
	}

	@Override
	public List<CoachCommentVO> getOneCoachCommentByMember(String memberID) {
		
		
		List<CoachCommentVO> list = new ArrayList<CoachCommentVO>();
		CoachCommentVO coachCommentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_COACH_COMMENT);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				coachCommentVO = new CoachCommentVO();
				coachCommentVO.setCoachCommentID(rs.getString("coachCommentID"));
				coachCommentVO.setMemberID(rs.getString("memberID"));
				coachCommentVO.setMemberID2(rs.getString("memberID2"));
				coachCommentVO.setCommText(rs.getString("commText"));
				coachCommentVO.setCommStar(rs.getInt("commStar"));
				coachCommentVO.setAddDate(rs.getTimestamp("addDate"));
				coachCommentVO.setEditDate(rs.getTimestamp("editDate"));
				coachCommentVO.setStatus(rs.getString("status"));
				
				
				list.add(coachCommentVO); // Store the row in the list
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
	public String getMemberIDFromCoachClassID(String coachClassID) {
		
		CoachClassVO coachClassVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String getM = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEMBERID_FROM_COACHCLASSID);
			pstmt.setString(1, coachClassID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 銋迂� Domain objects
				//coachClassVO = new CoachClassVO();
				//coachClassVO.setMemberID(rs.getString("memberID"));
				getM=rs.getString("memberID");                 
			}

//			getM =  coachClassVO.getMemberID();

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
		return getM;
	}

	@Override
	public List<MemberVO> getMemberCommentName(String memberID) {
		
		
		List<MemberVO> list_MemberName = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEMBER_NAME_FROM_MEMBERID);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setName(rs.getString("name"));
				
				
				list_MemberName.add(memberVO); // Store the row in the list
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
		return list_MemberName;
	}

	@Override
	public double getCoachStarAVG(String memberID) {
		
		List<CoachCommentVO> list_CoachCommentVO = new ArrayList<CoachCommentVO>();
		CoachCommentVO coachCommentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		double AVG= 0,AVG1= 0,AVG2 = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(coach_Star);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				coachCommentVO = new CoachCommentVO();
				coachCommentVO.setCommStar(rs.getInt("commStar"));
				list_CoachCommentVO.add(coachCommentVO); // Store the row in the list
			}
			for(CoachCommentVO a : list_CoachCommentVO) {
				AVG1 += a.getCommStar();
			}
			AVG2 = AVG1 / list_CoachCommentVO.size();
			AVG = Math.round(AVG2 * 10.0) / 10.0;
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
		return AVG;
	}



	
}
