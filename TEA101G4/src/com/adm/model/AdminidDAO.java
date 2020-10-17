package com.adm.model;

import java.sql.Blob;
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

public class AdminidDAO implements Adminno_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO ADMINNO (adminid, membername, memberuser, passwd,gender, phone,birthday, email,  photo, address)"
			+ "VALUES ('AD' || lpad(ADMINNO_SEQ.NEXTVAL, 3, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE ADMINNO set  membername = ? , memberuser = ? , passwd = ? ,gender = ? , phone = ? ,birthday = ? ,"
			+ " email = ? ,  photo = ? , address = ? WHERE adminid = ? ";
	private static final String DELETE_STMT = "DELETE FROM ADMINNO WHERE adminid = ?";
	private static final String FIND_BY_PK = "SELECT * FROM ADMINNO WHERE adminid = ?";
	private static final String GET_ALL = "SELECT * FROM ADMINNO";
	private static final String GET_ALL_MEMBERUSER = "SELECT * FROM ADMINNO WHERE memberuser = ?";

	@Override
	public void insert(AdminnoVO adminnoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setString(1, adminnoVO.getAdminid());
			pstmt.setString(1, adminnoVO.getMembername());
			pstmt.setString(2, adminnoVO.getMemberuser());
			pstmt.setString(3, adminnoVO.getPasswd());
			pstmt.setString(4, adminnoVO.getGender());
			pstmt.setString(5, adminnoVO.getPhone());
			pstmt.setDate(6, adminnoVO.getBirthday());
			pstmt.setString(7, adminnoVO.getEmail());
			pstmt.setBytes(8, adminnoVO.getPhoto());
			pstmt.setString(9, adminnoVO.getAddress());

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
	public void update(AdminnoVO adminnoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, adminnoVO.getMembername());
			pstmt.setString(2, adminnoVO.getMemberuser());
			pstmt.setString(3, adminnoVO.getPasswd());
			pstmt.setString(4, adminnoVO.getGender());
			pstmt.setString(5, adminnoVO.getPhone());
			pstmt.setDate(6, adminnoVO.getBirthday());
			pstmt.setString(7, adminnoVO.getEmail());
			pstmt.setBytes(8, adminnoVO.getPhoto());
			pstmt.setString(9, adminnoVO.getAddress());
			pstmt.setString(10, adminnoVO.getAdminid());
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
	public void delete(String adminid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, adminid);

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
	public AdminnoVO findByPrimaryKey(String adminid) {
		AdminnoVO adm = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, adminid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adm = new AdminnoVO();
				adm.setAdminid(rs.getString("ADMINID"));
				adm.setMembername(rs.getString("MEMBERNAME"));
				adm.setMemberuser(rs.getString("MEMBERUSER"));
				adm.setPasswd(rs.getString("PASSWD"));
				adm.setGender(rs.getString("GENDER"));
				adm.setPhone(rs.getString("PHONE"));
				adm.setBirthday(rs.getDate("BIRTHDAY"));
				adm.setEmail(rs.getString("EMAIL"));
				adm.setPhoto(rs.getBytes("PHOTO"));
				adm.setAddress(rs.getString("ADDRESS"));
			}

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
		return adm;
	}

	@Override
	public List<AdminnoVO> getAll() {
		List<AdminnoVO> avolist = new ArrayList<>();
		AdminnoVO adm = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adm = new AdminnoVO();
				adm.setAdminid(rs.getString("adminid"));
				adm.setMembername(rs.getString("membername"));
				adm.setMemberuser(rs.getString("memberuser"));
				adm.setPasswd(rs.getString("passwd"));
				adm.setGender(rs.getString("gender"));
				adm.setPhone(rs.getString("phone"));
				adm.setBirthday(rs.getDate("birthday"));
				adm.setEmail(rs.getString("email"));
				adm.setPhoto(rs.getBytes("photo"));
				adm.setAddress(rs.getString("address"));
				avolist.add(adm);
			}

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
		return avolist;
	}

	public AdminnoVO findByMemberuser(String memberuser) {
		AdminnoVO adm = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MEMBERUSER);
			pstmt.setString(1, memberuser);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adm = new AdminnoVO();
				adm.setAdminid(rs.getString("ADMINID"));
				adm.setMembername(rs.getString("MEMBERNAME"));
				adm.setMemberuser(rs.getString("MEMBERUSER"));
				adm.setPasswd(rs.getString("PASSWD"));
				adm.setGender(rs.getString("GENDER"));
				adm.setPhone(rs.getString("PHONE"));
				adm.setBirthday(rs.getDate("BIRTHDAY"));
				adm.setEmail(rs.getString("EMAIL"));
				adm.setPhoto(rs.getBytes("PHOTO"));
				adm.setAddress(rs.getString("ADDRESS"));
			}

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
		return adm;
	}

}
