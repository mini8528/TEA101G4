package com.wishList.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.classDetail.model.ClassDetailJDBCDAO;
import com.classDetail.model.ClassDetailVO;

public class WishListJDBCDAO implements WishListDAO_interface {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "TEA101G4";
	private static final String passwd = "123456";
	
	private static final String INSERT_STMT =  
			"INSERT INTO wishList (wishListId, memberId, productId, likeStatus, addDate, editDate) VALUES ('WL'|| lpad(WISHLIST_SEQ.NEXTVAL, 5,'0'), ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT wishListId, memberId, productId, likeStatus, addDate, editDate FROM wishList order by wishListId";
		private static final String GET_ONE_STMT = 
			"SELECT wishListId, memberId, productId, likeStatus, addDate, editDate FROM wishList where wishListId = ?";
		private static final String DELETE = 
			"DELETE FROM wishList where wishListId = ?";
		private static final String UPDATE = 
			"UPDATE wishList set  memberId=?, productId=?, likeStatus=?, addDate=?, editDate=? where wishListId = ?";

		
	@Override
	public void insert(WishListVO wishListVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, wishListVO.getMemberId());
			pstmt.setString(2, wishListVO.getProductId());
			pstmt.setString(3, wishListVO.getLikeStatus());
			pstmt.setDate(4, wishListVO.getAddDate());
			pstmt.setDate(5, wishListVO.getEditDate());
			
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
	public void update(WishListVO wishListVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setString(1, wishListVO.getMemberId());
			pstmt.setString(2, wishListVO.getProductId());
			pstmt.setString(3, wishListVO.getLikeStatus());
			pstmt.setDate(4, wishListVO.getAddDate());
			pstmt.setDate(5, wishListVO.getEditDate());
			pstmt.setString(6, wishListVO.getWishListId());

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
	public void delete(String wishListId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, wishListId);

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
	public WishListVO findByPrimaryKey(String wishListId) {
		
		WishListVO wishListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, wishListId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// classDetailVo 也稱為 Domain objects
				wishListVO = new WishListVO();
				wishListVO.setWishListId(rs.getString("wishListId"));
				wishListVO.setMemberId(rs.getString("memberId"));
				wishListVO.setProductId(rs.getString("productId"));
				wishListVO.setLikeStatus(rs.getString("likeStatus"));
				wishListVO.setAddDate(rs.getDate("addDate"));
				wishListVO.setEditDate(rs.getDate("editDate"));
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
		return wishListVO;
	}

	@Override
	public List<WishListVO> getAll() {
		
		List<WishListVO> list = new ArrayList<WishListVO>();
		WishListVO wishListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// classDetailVO 也稱為 Domain objects
				wishListVO = new WishListVO();
				wishListVO.setWishListId(rs.getString("wishListId"));
				wishListVO.setMemberId(rs.getString("memberId"));
				wishListVO.setProductId(rs.getString("productId"));
				wishListVO.setLikeStatus(rs.getString("likeStatus"));
				wishListVO.setAddDate(rs.getDate("addDate"));
				wishListVO.setEditDate(rs.getDate("editDate"));
				list.add(wishListVO); // Store the row in the list
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
	
	
//	public static void main(String[] args) {
//
//		WishListJDBCDAO dao = new WishListJDBCDAO();

		// 新增
//		WishListVO WishListVO1 = new WishListVO();
//		WishListVO1.setWishListId("CO00002");
//		WishListVO1.setMemberId("M001");
//		WishListVO1.setProductId("PD0002");
//		WishListVO1.setLikeStatus("N");
//		WishListVO1.setAddDate(new Date(System.currentTimeMillis()));
//		WishListVO1.setEditDate(new Date(System.currentTimeMillis()));
//		dao.insert(WishListVO1);

		// 修改
//		WishListVO WishListVO2 = new WishListVO();
//		WishListVO2.setWishListId("WL00001");
//		WishListVO2.setMemberId("M001");
//		WishListVO2.setProductId("PD0002");
//		WishListVO2.setLikeStatus("N");
//		WishListVO2.setAddDate(new Date(System.currentTimeMillis()));
//		WishListVO2.setEditDate(new Date(System.currentTimeMillis()));
//		dao.update(WishListVO2);

		// 刪除
//		dao.delete("WL00007");

		// 查詢
//		WishListVO WishListVO3 = dao.findByPrimaryKey("WL00001");
//		System.out.print(WishListVO3.getWishListId() + ",");
//		System.out.print(WishListVO3.getMemberId() + ",");
//		System.out.print(WishListVO3.getProductId() + ",");
//		System.out.print(WishListVO3.getLikeStatus() + ",");
//		System.out.print(WishListVO3.getAddDate() + ",");
//		System.out.println(WishListVO3.getEditDate() );
//		System.out.println("---------------------");

		// 查詢
//		List<WishListVO> list = dao.getAll();
//		for (WishListVO aWishList : list) {
//			System.out.print(aWishList.getWishListId() + ",");
//			System.out.print(aWishList.getMemberId() + ",");
//			System.out.print(aWishList.getProductId() + ",");
//			System.out.print(aWishList.getLikeStatus() + ",");
//			System.out.print(aWishList.getAddDate() + ",");
//			System.out.print(aWishList.getEditDate() );
//			System.out.println();
//		}
//	}

}
