package test;

import java.sql.Timestamp;
import java.util.List;

import com.blog_likes.model.Blog_LikesService;
import com.blog_likes.model.Blog_LikesVO;




public class TestBlog_LikesService {
	public static void main(String[] args) {
		Blog_LikesService service = new Blog_LikesService();
		
		//新增
//		service.addBlogLikes("B00002", "M007", "Y", new Timestamp(System.currentTimeMillis()), null);
//		System.out.println("service 新增成功");
		
		//修改
//		service.updateBlogLikes("BL00008", "B00003", "M001", "N", java.sql.Timestamp.valueOf("2020-09-10 16:23:38"), new Timestamp(System.currentTimeMillis()));
//		System.out.println("service 修改成功");
		
		//刪除
//		service.deleteBlogLikes("BL00009");

		
		//查單一		
//		System.out.println(service.getOneBlogLikes("BL00009"));

		//查全部
		List<Blog_LikesVO> result = service.getAll();
		for(Blog_LikesVO bloglikesvo : result) {
			System.out.println(bloglikesvo);
		}
	}

}
