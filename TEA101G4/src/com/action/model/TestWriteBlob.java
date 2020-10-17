package com.action.model;

import java.io.RandomAccessFile;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestWriteBlob {
	private static final String INSERT_STMT = 
			"INSERT INTO action (actionid,actionnm,part,vedio,posttime,updatetime)"
			+ " VALUES (?, ?, ?, ?, ?,?)";
	
	public void insert(ActionVO actionVO, Connection conn) {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setString(1, actionVO.getActionid());
			pstmt.setString(4, actionVO.getActionnm());
			pstmt.setString(5, actionVO.getPart());
			
			Blob blob = conn.createBlob(); 
			blob.setBytes(1, actionVO.getVideo());
			pstmt.setBlob(6, blob); 
			
			pstmt.setDate(7, (Date) actionVO.getPosttime());
			pstmt.setDate(8, (Date) actionVO.getUpdatetime());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " +
		           se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
	
	void testIt() {
		System.out.println(" hi ~  ");
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userid = "test";
			String passwd = "1234";

			conn = DriverManager.getConnection(  
					url,userid,passwd);  
			if (conn != null) {
				System.out.println("u got a conneciton");
			}
			 
			
			
			ActionVO newData = new ActionVO();
			newData.setActionid("AC999");

			newData.setActionnm("QQQQQ");
			newData.setActionnm("imaman");

			newData.setPart("leg");
			newData.setPosttime(new Date(System.currentTimeMillis()));
			
			RandomAccessFile f = new RandomAccessFile("C:\\Test\\01.jpg", "r");
			byte[] myFileArray = new byte[(int) f.length()];
			f.readFully(myFileArray);
			System.out.println("myFileArray "+myFileArray.length);
			
			newData.setVideo(myFileArray);
			
			insert(newData, conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            if(conn != null) {
            	try {
                    conn.close();
        		} catch (Exception e1) {
        			e1.printStackTrace();
        		}   	
            }
		}
		
		
		System.out.println("see u ~");
		
	}
    public static void main(String[] args) {
    	TestWriteBlob twb = new TestWriteBlob();
    	twb.testIt();
    }
}
