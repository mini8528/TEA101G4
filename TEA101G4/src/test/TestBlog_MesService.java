package test;

import java.sql.Timestamp;
import java.util.List;

import com.blog_mes.model.Blog_MesService;
import com.blog_mes.model.Blog_MesVO;

public class TestBlog_MesService {

	public static void main(String[] args) {
		Blog_MesService service = new Blog_MesService();
		
		//新增
//		service.addBlogMes("B00002", "M007", "哈哈哈", new Timestamp(System.currentTimeMillis()), null, "Y");
//		System.out.println("service 新增成功");
		
		//修改
//		service.updateBlogMes("BM00005", "B00003", "M007", "hahaha", new Timestamp(System.currentTimeMillis()), null, "Y");
//		System.out.println("service 修改成功");
		
		//刪除
//		service.deleteBlogMes("BM00006");

		
		//查單一		
//		System.out.println(service.getOneBlogMes("BM00006"));

		//查全部
		List<Blog_MesVO> result = service.getAll();
		for(Blog_MesVO blogmesvo : result) {
			System.out.println(blogmesvo);
		}
		
	}
}
