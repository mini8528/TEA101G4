package com.member.model;

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

public class MemberDAO implements MemberDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO member (memberid,name,account,password,gender,phone,birthday,email,photo,address,authority,qualifications,expertise,introduction,photo1,photo2,photo3,adddate) VALUES ('M' || lpad(MEMBER_SEQ.NEXTVAL, 3, '0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT memberid,name,account,password,gender,phone,birthday,email,address,authority,qualifications,expertise,introduction,adddate FROM member order by memberid";
	private static final String GET_ONE_STMT = "SELECT memberid,name,account,password,gender,phone,birthday,email,photo,address,authority,qualifications,expertise,introduction,photo1,photo2,photo3,adddate FROM member where memberid = ?";
	private static final String DELETE = "DELETE FROM member where memberid = ?";
	private static final String UPDATE = "UPDATE member set  name=? ,account=? ,password=? ,gender=? ,phone=? ,birthday=? ,email=? ,photo=? ,address=? ,authority=? ,qualifications=? ,expertise=? ,introduction=? ,photo1=? ,photo2=? ,photo3=? ,adddate=?  where memberid =? ";
	private static final String GET_MEMBER_ACCOUNT = "SELECT memberid,name,account,password,gender,phone,to_char(birthday,'yyyy-mm-dd') birthday,email,photo,address,authority,qualifications,expertise,introduction,photo1,photo2,photo3,to_char(adddate,'yyyy-mm-dd') adddate FROM member where account = ?";

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, memberVO.getName());
			pstmt.setString(2, memberVO.getAccount());
			pstmt.setString(3, memberVO.getPassword());
			pstmt.setString(4, memberVO.getGender());
			pstmt.setString(5, memberVO.getPhone());
			pstmt.setDate(6, memberVO.getBirthday());
			pstmt.setString(7, memberVO.getEmail());
			pstmt.setBytes(8, memberVO.getPhoto());
			pstmt.setString(9, memberVO.getAddress());
			pstmt.setString(10, memberVO.getAuthority());
			pstmt.setString(11, memberVO.getQualifications());
			pstmt.setString(12, memberVO.getExpertise());
			pstmt.setString(13, memberVO.getIntroduction());
			pstmt.setBytes(14, memberVO.getPhoto1());
			pstmt.setBytes(15, memberVO.getPhoto2());
			pstmt.setBytes(16, memberVO.getPhoto3());
			pstmt.setDate(17, memberVO.getAdddate());
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
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberVO.getName());
			pstmt.setString(2, memberVO.getAccount());
			pstmt.setString(3, memberVO.getPassword());
			pstmt.setString(4, memberVO.getGender());
			pstmt.setString(5, memberVO.getPhone());
			pstmt.setDate(6, memberVO.getBirthday());
			pstmt.setString(7, memberVO.getEmail());
			pstmt.setBytes(8, memberVO.getPhoto());
			pstmt.setString(9, memberVO.getAddress());
			pstmt.setString(10, memberVO.getAuthority());
			pstmt.setString(11, memberVO.getQualifications());
			pstmt.setString(12, memberVO.getExpertise());
			pstmt.setString(13, memberVO.getIntroduction());
			pstmt.setBytes(14, memberVO.getPhoto1());
			pstmt.setBytes(15, memberVO.getPhoto2());
			pstmt.setBytes(16, memberVO.getPhoto3());
			pstmt.setDate(17, memberVO.getAdddate());
			pstmt.setString(18, memberVO.getMemberid());

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
	public void delete(String memberid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memberid);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public MemberVO findByPrimaryKey(String memberid) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, memberid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// classDetailVo 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMemberid(rs.getString("memberid"));
				memberVO.setName(rs.getString("name"));
				memberVO.setAccount(rs.getString("account"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setGender(rs.getString("gender"));
				memberVO.setPhone(rs.getString("phone"));
				memberVO.setBirthday(rs.getDate("birthday"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setPhoto(rs.getBytes("photo"));
				memberVO.setAddress(rs.getString("address"));
				memberVO.setAuthority(rs.getString("authority"));
				memberVO.setQualifications(rs.getString("qualifications"));
				memberVO.setExpertise(rs.getString("expertise"));
				memberVO.setIntroduction(rs.getString("introduction"));
				memberVO.setPhoto1(rs.getBytes("photo1"));
				memberVO.setPhoto2(rs.getBytes("photo2"));
				memberVO.setPhoto3(rs.getBytes("photo3"));
				memberVO.setAdddate(rs.getDate("adddate"));
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {

		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// classDetailVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMemberid(rs.getString("memberid"));
				memberVO.setName(rs.getString("name"));
				memberVO.setAccount(rs.getString("account"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setGender(rs.getString("gender"));
				memberVO.setPhone(rs.getString("phone"));
				memberVO.setBirthday(rs.getDate("birthday"));
				memberVO.setEmail(rs.getString("email"));
//				memberVO.setPhoto(rs.getBytes("photo"));
				memberVO.setAddress(rs.getString("address"));
				memberVO.setAuthority(rs.getString("authority"));
				memberVO.setQualifications(rs.getString("qualifications"));
				memberVO.setExpertise(rs.getString("expertise"));
				memberVO.setIntroduction(rs.getString("introduction"));
//				memberVO.setPhoto1(rs.getBytes("photo1"));
//				memberVO.setPhoto2(rs.getBytes("photo2"));
//				memberVO.setPhoto3(rs.getBytes("photo3"));
				memberVO.setAdddate(rs.getDate("adddate"));
				list.add(memberVO); // Store the row in the list
			}
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
	public MemberVO findByAccount(String account) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEMBER_ACCOUNT);

			pstmt.setString(1, account);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberid(rs.getString("memberid"));
				memberVO.setName(rs.getString("name"));
				memberVO.setAccount(rs.getString("account"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setGender(rs.getString("gender"));
				memberVO.setPhone(rs.getString("phone"));
				memberVO.setBirthday(rs.getDate("birthday"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setPhoto(rs.getBytes("photo"));
				memberVO.setAddress(rs.getString("address"));
				memberVO.setAuthority(rs.getString("authority"));
				memberVO.setQualifications(rs.getString("qualifications"));
				memberVO.setExpertise(rs.getString("expertise"));
				memberVO.setIntroduction(rs.getString("introduction"));
				memberVO.setPhoto1(rs.getBytes("photo1"));
				memberVO.setPhoto2(rs.getBytes("photo2"));
				memberVO.setPhoto3(rs.getBytes("photo3"));
				memberVO.setAdddate(rs.getDate("adddate"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		return memberVO;
	}

//	
//	public static void main(String[] args) {
//
//		MemberJDBCDAO dao = new MemberJDBCDAO();

	// 新增
//		MemberVO WishListVO1 = new MemberVO();
//		WishListVO1.setName("PETER");
//		WishListVO1.setAccount("TIBIME");
//		WishListVO1.setPassword("PD0002");
//		WishListVO1.setGender("F");
//		WishListVO1.setPhone("0912345678");
//		
//		WishListVO1.setBirthday(new Date(System.currentTimeMillis()));
//		WishListVO1.setEmail("Email  @ Email ");
//		WishListVO1.setPhoto(null);
//		WishListVO1.setAddress("Address ");
//		WishListVO1.setAuthority("Y");
//		
//		WishListVO1.setQualifications("Qualifications ");
//		WishListVO1.setExpertise("Expertise ");
//		WishListVO1.setIntroduction("Introduction ");
//		WishListVO1.setPhoto1(null);
//		WishListVO1.setPhoto2(null);
//		
//		WishListVO1.setPhoto3(null);
//		WishListVO1.setAdddate(new Date(System.currentTimeMillis()));
//		dao.insert(WishListVO1);

//		// 修改
//		MemberVO WishListVO1 = new MemberVO();
//		
//		WishListVO1.setmemberid("M005");
//		
//		WishListVO1.setName("PETER");
//		WishListVO1.setAccount("TIBIME");
//		WishListVO1.setPassword("PD0002");
//		WishListVO1.setGender("F");
//		WishListVO1.setPhone("0912345678");
//		
//		WishListVO1.setBirthday(new Date(System.currentTimeMillis()));
//		WishListVO1.setEmail("Email  @ Email ");
//		WishListVO1.setPhoto(null);
//		WishListVO1.setAddress("Address ");
//		WishListVO1.setAuthority("Y");
//		
//		WishListVO1.setQualifications("Qualifications ");
//		WishListVO1.setExpertise("Expertise ");
//		WishListVO1.setIntroduction("Introduction ");
//		WishListVO1.setPhoto1(null);
//		WishListVO1.setPhoto2(null);
//		
//		WishListVO1.setPhoto3(null);
//		WishListVO1.setAdddate(new Date(System.currentTimeMillis()));
//		dao.update(WishListVO1);

	// 刪除
//		dao.delete("M011");

	// 查詢
//		MemberVO aWishList = dao.findByPrimaryKey("M001");
//		System.out.print(aWishList.getMemberid() + ",");
//		System.out.print(aWishList.getName() + ",");
//		System.out.print(aWishList.getAccount() + ",");
//		System.out.print(aWishList.getPassword() + ",");
//		System.out.print(aWishList.getGender() + ",");
//		System.out.print(aWishList.getPhone() + ",");
//		System.out.print(aWishList.getBirthday() + ",");
//		System.out.print(aWishList.getEmail() + ",");
//		System.out.print(aWishList.getPhoto() + ",");
//		System.out.print(aWishList.getAddress() + ",");
//		System.out.print(aWishList.getAuthority() + ",");
//		System.out.print(aWishList.getQualifications() + ",");
//		System.out.print(aWishList.getExpertise() + ",");
//		System.out.print(aWishList.getIntroduction() + ",");
//		System.out.print(aWishList.getPhoto1() + ",");
//		System.out.print(aWishList.getPhoto2() + ",");
//		System.out.print(aWishList.getPhoto3() + ",");
//		System.out.print(aWishList.getAdddate() );
//		System.out.println();

	// 查詢
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO aWishList : list) {
//			System.out.print(aWishList.getMemberid() + ",");
//			System.out.print(aWishList.getName() + ",");
//			System.out.print(aWishList.getAccount() + ",");
//			System.out.print(aWishList.getPassword() + ",");
//			System.out.print(aWishList.getGender() + ",");
//			System.out.print(aWishList.getPhone() + ",");
//			System.out.print(aWishList.getBirthday() + ",");
//			System.out.print(aWishList.getEmail() + ",");
//			System.out.print(aWishList.getPhoto() + ",");
//			System.out.print(aWishList.getAddress() + ",");
//			System.out.print(aWishList.getAuthority() + ",");
//			System.out.print(aWishList.getQualifications() + ",");
//			System.out.print(aWishList.getExpertise() + ",");
//			System.out.print(aWishList.getIntroduction() + ",");
//			System.out.print(aWishList.getPhoto1() + ",");
//			System.out.print(aWishList.getPhoto2() + ",");
//			System.out.print(aWishList.getPhoto3() + ",");
//			System.out.print(aWishList.getAdddate() );
//			System.out.println();
//		}
//	}

}
