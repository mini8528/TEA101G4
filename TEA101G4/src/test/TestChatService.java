package test;

import java.sql.Timestamp;
import java.util.List;

import com.blog_save.model.Blog_SaveService;
import com.blog_save.model.Blog_SaveVO;
import com.chat.model.ChatService;
import com.chat.model.ChatVO;



public class TestChatService {
	public static void main(String[] args) {
		ChatService service = new ChatService();
		
		//新增
		service.addChat("M007", "M006", "你好", new Timestamp(System.currentTimeMillis()));
		System.out.println("service 新增成功");
		
		//修改
//		service.updateChat("CH00011","M003", "M004", "你好", new Timestamp(System.currentTimeMillis()));
//		System.out.println("service 修改成功");
		
		//刪除
//		service.deleteChat("CH00011");
	
		//查單一		
//		System.out.println(service.getOneChat("CH00012"));

		//查全部
//		List<ChatVO> result = service.getAll();
//		for(ChatVO chatvo : result) {
//			System.out.println(chatvo);
//		}
	}

}
