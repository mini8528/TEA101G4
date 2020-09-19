package com.chat.model;

import java.sql.Timestamp;
import java.util.List;

public class ChatService {
	private ChatDAO_interface dao;

	public ChatService() {
		dao = new ChatJDBCDAO();
	}

	public ChatVO addChat(String memberId, String memberId2, String text, Timestamp sendTime) {
		ChatVO chatVO = new ChatVO();

		chatVO.setMemberId(memberId);
		chatVO.setMemberId2(memberId2);
		chatVO.setText(text);
		chatVO.setSendTime(sendTime);

		dao.insert(chatVO);

		return chatVO;

	}

	public ChatVO updateChat(String chatid, String memberId, String memberId2, String text, Timestamp sendTime) {

		ChatVO chatVO = new ChatVO();

		chatVO.setChatId(chatid);
		chatVO.setMemberId(memberId);
		chatVO.setMemberId2(memberId2);
		chatVO.setText(text);
		chatVO.setSendTime(sendTime);
		
		dao.update(chatVO);

		return chatVO;

	}

	public void deleteChat(String chatid) {
		dao.delete(chatid);
	}

	public ChatVO getOneChat(String chatid) {
		return dao.findByPrimaryKey(chatid);
	}

	public List<ChatVO> getAll() {
		return dao.getAll();
	}

}
