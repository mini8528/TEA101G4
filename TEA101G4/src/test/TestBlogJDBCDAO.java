package test;

import java.sql.Timestamp;
import java.util.List;

import com.blog.model.BlogJDBCDAO;
import com.blog.model.BlogVO;


public class TestBlogJDBCDAO {

	public static void main(String[] args) {

		BlogJDBCDAO dao = new BlogJDBCDAO();
		
//		dao.changeStatus("B00001","N");

//		// 新增
//		BlogVO blogVO1 = new BlogVO();
//		
//		blogVO1.setMemberId("M003");
//		blogVO1.setBlogClass("心得交流");
//		blogVO1.setPostDate(java.sql.Timestamp.valueOf("2020-01-01 22:15:38"));
//		blogVO1.setTitle("減肥");
//		blogVO1.setText("開始減肥囉");
//		blogVO1.setPhoto(null);
//		blogVO1.setVideo(null);
//		blogVO1.setStatus("Y");
//		blogVO1.setUpdateTime(new Timestamp(System.currentTimeMillis()));
//		
//		dao.insert(blogVO1);
//		
//		System.out.println("success");

		// 修改
//		BlogVO blogVO2 = new BlogVO();
//
//		blogVO2.setBlogno("B00009");
//		blogVO2.setMemberId("M004");
//		blogVO2.setBlogClass("健身影片");
//		blogVO2.setPostDate(java.sql.Timestamp.valueOf("2007-01-01 22:15:38"));
//		blogVO2.setTitle("減肥2");
//		blogVO2.setText("開始減肥囉2");
//		blogVO2.setPhoto(null);
//		blogVO2.setVideo(null);
//		blogVO2.setStatus("N");
//		blogVO2.setUpdateTime(null);
//	
//		
//		dao.update(blogVO2);
//		System.out.println("success2");

//		// 刪除
//		dao.delete("8");
//
//		// 查詢
//		BlogVO blogVO3 = dao.findByPrimaryKey("B00009");
//		System.out.print(blogVO3.getBlogno() + ",");
//		System.out.print(blogVO3.getMemberId() + ",");
//		System.out.print(blogVO3.getBlogClass() + ",");
//		System.out.print(blogVO3.getPostDate() + ",");
//		System.out.print(blogVO3.getTitle() + ",");
//		System.out.print(blogVO3.getText() + ",");
//		System.out.print(blogVO3.getPhoto() + ",");
//		System.out.print(blogVO3.getVideo()+ ",");
//		System.out.print(blogVO3.getStatus()+ ",");
//		System.out.println(blogVO3.getUpdateTime());
//		System.out.println("---------------------");
		

//		// 查詢
//		List<BlogVO> list = dao.getAll();
//		for (BlogVO aEmp : list) {
//			System.out.print(aEmp.getBlogno() + ",");
//			System.out.print(aEmp.getMemberId() + ",");
//			System.out.print(aEmp.getBlogClass()+ ",");
//			System.out.print(aEmp.getPostDate()+ ",");
//			System.out.print(aEmp.getTitle()+ ",");
//			System.out.print(aEmp.getText()+ ",");
//			System.out.print(aEmp.getPhoto()+ ",");
//			System.out.print(aEmp.getVideo()+ ",");
//			System.out.print(aEmp.getStatus()+ ",");
//			System.out.print(aEmp.getUpdateTime());
//			System.out.println();
//			
//		}

	}

}
