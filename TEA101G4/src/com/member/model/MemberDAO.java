//package com.member.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import com.bodyrecord.model.BodyrecordVO;
//
//public class MemberDAO implements MemberDAO_interface{
//	
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("");
//		}catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private static final String INSERT_STMT =
//			"INSERT INTO member2 (memberid,name,account,password,gender,phone,birthday,email,photo,address,authority,qualifications,expertise,introduction,photo1,photo2,photo3,adddate) VALUES (member2_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?))";
//	private static final String GET_ALL_STMT =
//			"SELECT memberid,name,account,password,gender,phone,to_char(birthday,'yyyy-mm--dd') birthday,email,photo,address,authority,qualifications,expertise,iniroduction,photo1,photo2,photo3,to_char(adddate,'yyyy-mm--dd') adddate FROM member2";
//	private static final String GET_ONE_STMT =
//			"SELECT memberid,name,account,password,gender,phone,to_char(birthday,'yyyy-mm--dd') birthday,email,photo,address,authority,qualifications,expertise,iniroduction,photo1,photo2,photo3,to_char(adddate,'yyyy-mm--dd') adddate FROM member2 where memberid = ?";
//	private static final String GET_BODYRECORDs_ByMemberid_MEMBER =
//			"SELECT bodyrecordid,memberid,to_char(uploaddate,'yyyy-mm--dd') uploaddate,photo1,description,describestatus,to_char(updatetime,'yyyy-mm--dd') updatetime FROM bodyrecord2 where memberid= ? order by bodyrecordid";
//	private static final String DELETE_BODYRECORDs =
//			"DELETE FROM bodyrecord2 where memberid = ?";
//	private static final String DELETE_MEMBER =
//			"DELETE FROM member2 where memberid = ?";
//	private static final String UPDATE =
//			"UPDATE member2 set name = ?,account = ?,password = ?,gender = ?,phone = ?,birthday = ?,email = ?,photo = ?,address = ?,authority = ?,qualifications = ?,expertise = ?,introduction = ?,photo1 = ?,photo2 = ?,photo3 = ?,adddate = ? where memberid = ?";
//	@Override
//	public void insert(MemberVO memberVO) {
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//			
//			pstmt.setString(1, memberVO.getName());
//			pstmt.setString(2, memberVO.getAccount());
//			pstmt.setString(3, memberVO.getPassword());
//			pstmt.setString(4, memberVO.getGender());
//			pstmt.setString(5, memberVO.getPhone());
//			pstmt.setDate(6, memberVO.getBirthday());
//			pstmt.setString(7, memberVO.getEmail());
//			pstmt.setBytes(8, memberVO.getPhoto());
//			pstmt.setString(9, memberVO.getAddress());
//			pstmt.setString(10, memberVO.getAuthority());
//			pstmt.setString(11, memberVO.getQualifications());
//			pstmt.setString(12, memberVO.getExpertise());
//			pstmt.setString(13, memberVO.getIntroduction());
//			pstmt.setBytes(14, memberVO.getPhoto1());
//			pstmt.setBytes(15, memberVO.getPhoto2());
//			pstmt.setBytes(16, memberVO.getPhoto3());
//			pstmt.setDate(17, memberVO.getAdddate());
//			
//			pstmt.executeUpdate();
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//	@Override
//	public void update(MemberVO memberVO) {
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//			
//			pstmt.setString(1, memberVO.getName());
//			pstmt.setString(2, memberVO.getAccount());
//			pstmt.setString(3, memberVO.getPassword());
//			pstmt.setString(4, memberVO.getGender());
//			pstmt.setString(5, memberVO.getPhone());
//			pstmt.setDate(6, memberVO.getBirthday());
//			pstmt.setString(7, memberVO.getEmail());
//			pstmt.setBytes(8, memberVO.getPhoto());
//			pstmt.setString(9, memberVO.getAddress());
//			pstmt.setString(10, memberVO.getAuthority());
//			pstmt.setString(11, memberVO.getQualifications());
//			pstmt.setString(12, memberVO.getExpertise());
//			pstmt.setString(13, memberVO.getIntroduction());
//			pstmt.setBytes(14, memberVO.getPhoto1());
//			pstmt.setBytes(15, memberVO.getPhoto2());
//			pstmt.setBytes(16, memberVO.getPhoto3());
//			pstmt.setDate(17, memberVO.getAdddate());
//			pstmt.setString(18, memberVO.getMemberid());
//			
//			pstmt.executeUpdate();
//			
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//	@Override
//	public void delete(String memberid) {
//		int updateCount_BODYRECORDs = 0;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//
//			con = ds.getConnection();
//			
//			con.setAutoCommit(false);
//			
//			pstmt = con.prepareStatement(DELETE_BODYRECORDs);
//			pstmt.setString(1, memberid);
//			updateCount_BODYRECORDs = pstmt.executeUpdate();
//			
//			pstmt = con.prepareStatement(DELETE_MEMBER);
//			pstmt.setString(1, memberid);
//			pstmt.executeUpdate();
//			
//			con.commit();
//			con.setAutoCommit(true);
//			System.out.println("刪除會員編號"+memberid+"體態紀錄有"+updateCount_BODYRECORDs+"個紀錄刪除");
//			
//		}catch(SQLException se) {
//			if(con != null) {
//				try {
//					con.rollback();
//				}catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured."+excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured."+se.getMessage());
//		}finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//	@Override
//	public MemberVO findByPrimaryKey(String memberid) {
//		
//		MemberVO memberVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//			
//			pstmt.setString(1, memberid);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				memberVO = new MemberVO();
//				memberVO.setMemberid(rs.getString("memberid"));
//				memberVO.setName(rs.getString("name"));
//				memberVO.setAccount(rs.getString("account"));
//				memberVO.setPassword(rs.getString("password"));
//				memberVO.setGender(rs.getString("gender"));
//				memberVO.setPhone(rs.getString("phone"));
//				memberVO.setBirthday(rs.getDate("birthday"));
//				memberVO.setEmail(rs.getString("email"));
//				memberVO.setPhoto(rs.getBytes("photo"));
//				memberVO.setAddress(rs.getString("address"));
//				memberVO.setAuthority(rs.getString("authority"));
//				memberVO.setQualifications(rs.getString("qualifications"));
//				memberVO.setExpertise(rs.getString("expertise"));
//				memberVO.setIntroduction(rs.getString("introduction"));
//				memberVO.setPhoto1(rs.getBytes("photo1"));
//				memberVO.setPhoto2(rs.getBytes("photo2"));
//				memberVO.setPhoto3(rs.getBytes("photo3"));
//				memberVO.setAdddate(rs.getDate("adddate"));
//			}
//
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return memberVO;
//	}
//	@Override
//	public List<MemberVO> getAll(){
//		List<MemberVO> list = new ArrayList<MemberVO>();
//		MemberVO memberVO = null;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//	
//		try {
//			
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				memberVO = new MemberVO();
//				memberVO.setMemberid(rs.getString("memberid"));
//				memberVO.setName(rs.getString("name"));
//				memberVO.setAccount(rs.getString("account"));
//				memberVO.setPassword(rs.getString("password"));
//				memberVO.setGender(rs.getString("gender"));
//				memberVO.setPhone(rs.getString("phone"));
//				memberVO.setBirthday(rs.getDate("birthday"));
//				memberVO.setEmail(rs.getString("email"));
//				memberVO.setPhoto(rs.getBytes("photo"));
//				memberVO.setAddress(rs.getString("address"));
//				memberVO.setAuthority(rs.getString("authority"));
//				memberVO.setQualifications(rs.getString("qualifications"));
//				memberVO.setExpertise(rs.getString("expertise"));
//				memberVO.setIntroduction(rs.getString("introduction"));
//				memberVO.setPhoto1(rs.getBytes("photo1"));
//				memberVO.setPhoto2(rs.getBytes("photo2"));
//				memberVO.setPhoto3(rs.getBytes("photo3"));
//				memberVO.setAdddate(rs.getDate("adddate"));
//				list.add(memberVO);
//			}
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
//	@Override
//	public Set<BodyrecordVO> getBodyrecordsByMemberId(String memberid){
//		Set<BodyrecordVO> set = new LinkedHashSet<BodyrecordVO>();
//		BodyrecordVO bodyrecordVO = null;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_BODYRECORDs_ByMemberid_MEMBER);
//			pstmt.setString(1, memberid);
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				bodyrecordVO = new BodyrecordVO();
//				bodyrecordVO.setbodyrecordid(rs.getString("bodyrecordid"));
//				bodyrecordVO.setmemberId(rs.getString("memberid"));
//				bodyrecordVO.setuploaddate(rs.getDate("uploaddate"));
//				bodyrecordVO.setphoto1(rs.getBytes("photo1"));
//				bodyrecordVO.setdescription(rs.getString("description"));
//				bodyrecordVO.setdescribestatus(rs.getString("describestatus"));
//				bodyrecordVO.setupdatetime(rs.getDate("updatetime"));
//				set.add(bodyrecordVO);
//			}
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return set;
//	}
//}
