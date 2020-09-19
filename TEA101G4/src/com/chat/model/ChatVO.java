package com.chat.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ChatVO implements Serializable {
	private String chatId;
	private String memberId;
	private String memberId2;
	private String text;
	private Timestamp sendTime;
	
	
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberId2() {
		return memberId2;
	}
	public void setMemberId2(String memberId2) {
		this.memberId2 = memberId2;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	@Override
	public String toString() {
		return "ChatVO [chatId=" + chatId + ", memberId=" + memberId + ", memberId2=" + memberId2 + ", text=" + text
				+ ", sendTime=" + sendTime + "]";
	}
	
	
}
