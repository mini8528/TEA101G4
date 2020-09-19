package test;

import java.sql.Timestamp;
import java.util.List;

import com.blog_save.model.Blog_SaveService;
import com.blog_save.model.Blog_SaveVO;



public class TestBlog_SaveService {
	public static void main(String[] args) {
		Blog_SaveService service = new Blog_SaveService();
		
		//新增
		service.addBlogSave("M007", "B00002","Y", new Timestamp(System.currentTimeMillis()), null);
		System.out.println("service 新增成功");
		
		//修改
//		service.updateBlogSave("BS00006", "M001", "B00003", "N", java.sql.Timestamp.valueOf("2020-09-10 16:23:38"), new Timestamp(System.currentTimeMillis()));
//		System.out.println("service 修改成功");
		
		//刪除
//		service.deleteBlogSave("BS00005");

		
		//查單一		
//		System.out.println(service.getOneBlogSave("BS00006"));

		//查全部
//		List<Blog_SaveVO> result = service.getAll();
//		for(Blog_SaveVO blogsavevo : result) {
//			System.out.println(blogsavevo);
//		}
	}

}
