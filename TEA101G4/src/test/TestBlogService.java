package test;

import java.sql.Timestamp;
import java.util.List;

import com.blog.model.BlogService;
import com.blog.model.BlogVO;


public class TestBlogService {
	
	public static void main(String[] args) {
		BlogService service = new BlogService();
		
		//新增
//		service.addBlog("M005", "心得交流", java.sql.Timestamp.valueOf("2020-05-01 22:15:38"), 
//				"減肥", "開始減肥囉", null, null, "Y", new Timestamp(System.currentTimeMillis()));
//		System.out.println("service 新增成功");
		
		//修改
//		service.updateBlog("B00010","M005", "健身食譜", java.sql.Timestamp.valueOf("2020-05-01 22:15:38"), 
//				"減肥", "開始減肥囉", null, null, "Y", new Timestamp(System.currentTimeMillis()));
//		System.out.println("service 修改成功");
		
		//刪除
//		service.deleteBlog("B00010");
		
		
		//查單一
//		System.out.println( service.getOneBlog("B00010"));
		
		
//		//查全部
		List<BlogVO> result = service.getAll();
		for(BlogVO blogvo : result) {
			System.out.println(blogvo);
		}
	}
}
