package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spec.model.SpecJDBCDAO;
import com.spec.model.SpecVO;

public class ProductJDBCDAO implements ProductDAO_interface {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "TEA101G4";
	private static final String passwd = "123456";
//	String url = "jdbc:oracle:thin:@database-2.c25eusi5ih7r.ap-northeast-1.rds.amazonaws.com:1520:orcl";
//	String userid = "admin";
//	String passwd = "admin1234";
//----新增商品----
	private static final String INSERT_STMT = "INSERT INTO PRODUCT "
			+ "(PRODUCTID, ADMINID, ADMINID2, BRANDID, NAME, CATEGORY, PRICE, "
			+ "ADDDATE, STATUS,EDITDATE, PHOTO1, PHOTO2, PHOTO3, INTRO) "
			+ "VALUES ('PD' || LPAD(PRODUCT_SEQ.NEXTVAL, 4, '0'),?,?,?,?,?,?,?,?,?,?,?,?,?)";
//----查詢所有商品----
	private static final String GET_ALL_STMT = "SELECT PRODUCTID, "
			+ "ADMINID, ADMINID2, BRANDID, NAME, CATEGORY, PRICE, "
			+ "ADDDATE, STATUS, EDITDATE, PHOTO1, PHOTO2, PHOTO3, INTRO  FROM PRODUCT ORDER BY PRODUCTID";
//----查詢上架商品----
	private static final String GET_ALL_STMT_customer = "SELECT * FROM PRODUCT WHERE STATUS = ? ORDER BY PRICE";
//----查詢單筆商品----
	private static final String GET_ONE_STMT = "SELECT PRODUCTID, "
			+ "ADMINID, ADMINID2, BRANDID, NAME, CATEGORY, PRICE, "
			+ "ADDDATE, STATUS, EDITDATE, PHOTO1, PHOTO2, PHOTO3, INTRO  FROM PRODUCT WHERE PRODUCTID = ?";
//----刪除商品----
	private static final String DELETE = "DELETE FROM PRODUCT WHERE PRODUCTID = ?";
//----編輯商品----
	private static final String UPDATE = "UPDATE PRODUCT SET ADMINID=?,ADMINID2=?,BRANDID=?, NAME=?, CATEGORY=?,PRICE=?,"
			+ "ADDDATE=?,STATUS=?,EDITDATE=?,PHOTO1=?,PHOTO2=?,PHOTO3=?,INTRO=? WHERE PRODUCTID = ?";
//----編輯商品上下架狀態----
	private static final String UPDATE_SATAUS = "UPDATE PRODUCT SET STATUS=?,EDITDATE=? WHERE PRODUCTID = ?";
//-----------------------
//（管理員）瀏覽商品照片
//	private static final String SHOW_PHOTO = "SELECT PHOTO1 FROM PRODUCT WHERE PRODUCTID =?";
	
////	--（管理員）查多筆商品，用NAME(KW)。
	private static final String GET_STMT_BY_NAME = "SELECT * FROM PRODUCT WHERE NAME LIKE ? ORDER BY PRICE ASC";
////	--（會員）查多筆商品，用種類。
	private static final String GET_STMT_BY_CATEGORY = "SELECT * FROM PRODUCT WHERE CATEGORY LIKE ? AND STATUS = ?";
////	--（管理員）查多筆商品，用PRICE(dec.inc.BETWEEN)。
//	private static final String GET_STMT_BY_PRICE = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ?";
////	--（管理員）查多筆商品，用PRICE(inc.)。
//	private static final String GET_STMT_BY_PRICE_ASC = "SELECT * FROM PRODUCT ORDER BY PRICE ASC";
////	--（管理員）查多筆商品，用PRICE(dec.)。
//	private static final String GET_STMT_BY_PRICE_DESC = "SELECT * FROM PRODUCT ORDER BY PRICE DESC";
////	--（管理員）查多筆商品，用ADDDATE(BETWEEN)。
//	private static final String GET_STMT_BY_ADDDATE = "SELECT * FROM PRODUCT WHERE ADDDATE BETWEEN ? AND ?";
////	--（管理員）查多筆商品，用BRANDID搜尋(inc.價格排列)。
//	private static final String GET_BY_BRANDID_ASC_PRICE = "SELECT * FROM PRODUCT WHERE BRANDID LIKE ? ORDER BY PRICE ASC";
////	--（管理員）查多筆商品，用BRANDID搜尋(inc.上架日排列)。
//	private static final String GET_BY_BRANDID_ASC_ADDDATE = "SELECT * FROM PRODUCT WHERE BRANDID LIKE ? ORDER BY ADDDATE ASC";

//新增商品----
	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productVO.getAdminid());
			pstmt.setString(2, productVO.getAdminid2());
			pstmt.setString(3, productVO.getBrandid());
			pstmt.setString(4, productVO.getName());
			pstmt.setString(5, productVO.getCategory());
			pstmt.setInt(6, productVO.getPrice());
			pstmt.setTimestamp(7, productVO.getAdddate());
			pstmt.setString(8, productVO.getStatus());
			pstmt.setTimestamp(9, productVO.getEditdate());
			pstmt.setBytes(10, productVO.getPhoto1());
			pstmt.setBytes(11, productVO.getPhoto2());
			pstmt.setBytes(12, productVO.getPhoto3());
			pstmt.setString(13, productVO.getIntro());

			pstmt.executeUpdate();
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

//編輯商品----
	@Override
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productVO.getAdminid());
			pstmt.setString(2, productVO.getAdminid2());
			pstmt.setString(3, productVO.getBrandid());
			pstmt.setString(4, productVO.getName());
			pstmt.setString(5, productVO.getCategory());
			pstmt.setInt(6, productVO.getPrice());
			pstmt.setTimestamp(7, productVO.getAdddate());
			pstmt.setString(8, productVO.getStatus());
			pstmt.setTimestamp(9, productVO.getEditdate());
			pstmt.setBytes(10, productVO.getPhoto1());
			pstmt.setBytes(11, productVO.getPhoto2());
			pstmt.setBytes(12, productVO.getPhoto3());
			pstmt.setString(13, productVO.getIntro());
			pstmt.setString(14, productVO.getProductid());

			pstmt.executeUpdate();
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

//刪除商品----
	@Override
	public void delete(String productid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, productid);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

//查詢單筆商品----
	@Override
	public ProductVO findbyPrimaryKey(String productid) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, productid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductid(rs.getString("productid"));
				productVO.setAdminid(rs.getString("adminid"));
				productVO.setAdminid2(rs.getString("adminid2"));
				productVO.setBrandid(rs.getString("brandid"));
				productVO.setName(rs.getString("name"));
				productVO.setCategory(rs.getString("category"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setAdddate(rs.getTimestamp("adddate"));
				productVO.setStatus(rs.getString("status"));
				productVO.setEditdate(rs.getTimestamp("editdate"));
				productVO.setPhoto1(rs.getBytes("photo1"));
				productVO.setPhoto2(rs.getBytes("photo2"));
				productVO.setPhoto3(rs.getBytes("photo3"));
				productVO.setIntro(rs.getString("intro"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
		return productVO;
	}

//查詢所有商品----
	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			System.out.println("ProductJDBDDAO_getAll_test");
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductid(rs.getString("productid"));
				productVO.setAdminid(rs.getString("adminid"));
				productVO.setAdminid2(rs.getString("adminid2"));
				productVO.setBrandid(rs.getString("brandid"));
				productVO.setName(rs.getString("name"));
				productVO.setCategory(rs.getString("category"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setAdddate(rs.getTimestamp("adddate"));
				productVO.setStatus(rs.getString("status"));
				productVO.setEditdate(rs.getTimestamp("editdate"));
				productVO.setPhoto1(rs.getBytes("photo1"));
				productVO.setPhoto2(rs.getBytes("photo2"));
				productVO.setPhoto3(rs.getBytes("photo3"));
				productVO.setIntro(rs.getString("intro"));
				list.add(productVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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

//編輯商品上下架狀態----
	@Override
	public void update_status(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(UPDATE_SATAUS);

			pstmt.setString(1, productVO.getStatus());
			pstmt.setTimestamp(2, productVO.getEditdate());
			pstmt.setString(3, productVO.getProductid());

			pstmt.executeUpdate();
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

//查多筆商品，用NAME(KW)。
	@Override
	public List<ProductVO> getProductsByName(String name) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = new ProductVO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(GET_STMT_BY_NAME);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				productVO = new ProductVO();
				productVO.setProductid(rs.getString("productid"));
				productVO.setAdminid(rs.getString("adminid"));
				productVO.setAdminid2(rs.getString("adminid2"));
				productVO.setBrandid(rs.getString("brandid"));
				productVO.setName(rs.getString("name"));
				productVO.setCategory(rs.getString("category"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setAdddate(rs.getTimestamp("adddate"));
				productVO.setStatus(rs.getString("status"));
				productVO.setEditdate(rs.getTimestamp("editdate"));
				productVO.setPhoto1(rs.getBytes("photo1"));
				productVO.setPhoto2(rs.getBytes("photo2"));
				productVO.setPhoto3(rs.getBytes("photo3"));
				productVO.setIntro(rs.getString("intro"));
				list.add(productVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't Load DataBase Driver. "+ e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A DataBase Error Occured. "+ e.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con!=null) {
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
	public void insertWithProduct(ProductVO productVO, List<SpecVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
    		// 先新增部門
			String cols[] = {"PRODUCTID"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);	
			pstmt.setString(1, productVO.getAdminid());
			pstmt.setString(2, productVO.getAdminid2());
			pstmt.setString(3, productVO.getBrandid());
			pstmt.setString(4, productVO.getName());
			pstmt.setString(5, productVO.getCategory());
			pstmt.setInt(6, productVO.getPrice());
			pstmt.setTimestamp(7, productVO.getAdddate());
			pstmt.setString(8, productVO.getStatus());
			pstmt.setTimestamp(9, productVO.getEditdate());
			pstmt.setBytes(10, productVO.getPhoto1());
			pstmt.setBytes(11, productVO.getPhoto2());
			pstmt.setBytes(12, productVO.getPhoto3());
			pstmt.setString(13, productVO.getIntro());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_productid = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_productid = rs.getString(1);
				System.out.println("自增主鍵值= " + next_productid +"(剛新增成功的商品編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增規格
			SpecJDBCDAO dao = new SpecJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (SpecVO aSpec : list) {
				aSpec.setProductid(new String(next_productid));
				dao.insert2(aSpec,con);
			}
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增商品編號" + next_productid + "時,共有規格" + list.size()
					+ "筆同時被新增");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-Product");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
	public List<ProductVO> getAll_byStatus(String status) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_customer);
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();
			
//			System.out.println("getAll_byStatus_test");
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductid(rs.getString("productid"));
				productVO.setAdminid(rs.getString("adminid"));
				productVO.setAdminid2(rs.getString("adminid2"));
				productVO.setBrandid(rs.getString("brandid"));
				productVO.setName(rs.getString("name"));
				productVO.setCategory(rs.getString("category"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setAdddate(rs.getTimestamp("adddate"));
				productVO.setStatus(rs.getString("status"));
				productVO.setEditdate(rs.getTimestamp("editdate"));
				productVO.setPhoto1(rs.getBytes("photo1"));
				productVO.setPhoto2(rs.getBytes("photo2"));
				productVO.setPhoto3(rs.getBytes("photo3"));
				productVO.setIntro(rs.getString("intro"));
				list.add(productVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public List<ProductVO> getProductsByCategory(String category,String status) {
		// "SELECT * FROM PRODUCT WHERE CATEGORY = ? AND STATUS = ?";
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = new ProductVO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(GET_STMT_BY_CATEGORY);
			pstmt.setString(1, "%"+category+"%");
			pstmt.setString(2, status);
			
			rs = pstmt.executeQuery();
			
//			System.out.println("getProductsByCategory_test");
			while(rs.next()) {
				productVO = new ProductVO();
				productVO.setProductid(rs.getString("productid"));
				productVO.setAdminid(rs.getString("adminid"));
				productVO.setAdminid2(rs.getString("adminid2"));
				productVO.setBrandid(rs.getString("brandid"));
				productVO.setName(rs.getString("name"));
				productVO.setCategory(rs.getString("category"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setAdddate(rs.getTimestamp("adddate"));
				productVO.setStatus(rs.getString("status"));
				productVO.setEditdate(rs.getTimestamp("editdate"));
				productVO.setPhoto1(rs.getBytes("photo1"));
				productVO.setPhoto2(rs.getBytes("photo2"));
				productVO.setPhoto3(rs.getBytes("photo3"));
				productVO.setIntro(rs.getString("intro"));
				list.add(productVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public void update_status2(List<ProductVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_SATAUS);
//			System.out.println("list==>"+list);
			for(ProductVO productVO:list) {
				pstmt.setString(1, productVO.getStatus());
				pstmt.setTimestamp(2, productVO.getEditdate());
				pstmt.setString(3, productVO.getProductid());
				pstmt.executeUpdate();
			}
//System.out.println("test");
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

//	@Override
//	public byte[] showPhoto(String productid) {
//		ProductVO productVO = new ProductVO();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(SHOW_PHOTO);
//			pstmt.setString(1, productid);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("PHOTO1"));
//				byte[] buf = new byte[4 * 1024];
//				int len;
//				System.out.println("PHOTO1_TEST_SHOW");
//				while ((len = in.read(buf)) != -1) {
//					outpho.write(buf, 0, len);
//				}
//				in.close();
//			} else {
//				InputStream in = getServletContext().getResourceAsStream("");
//				byte[] b = new byte[in.available()];
//				in.read(b);
//				outpho.write(b);
//				in.close();
//			}
//			rs.close();
//			pstmt.close();
//			con.close();
//			
//			
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't Load DataBase Driver. "+ e.getMessage());
//		} catch (SQLException e) {
//			throw new RuntimeException("A DataBase Error Occured. "+ e.getMessage());
//		}finally {
//			if(rs!=null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if(pstmt!=null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if(con!=null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return null;
//	}
}