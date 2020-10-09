package com.websocket.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.classDetail.model.ClassDetailService;
import com.google.gson.Gson;
import com.websocket.jedis.JedisHandleMessage;
import com.websocket.model.ChatMessage;
import com.websocket.model.State;

@ServerEndpoint("/FriendWS/{memberid}")
public class FriendWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>(); // ConcurrentHashMap並行(java5)效率較高，與Hashtable做比較
	// 1對1聊天的重點 Map<Username, Session>
	Gson gson = new Gson(); // google的

	@OnOpen
	public void onOpen(@PathParam("memberid") String userid, Session userSession) throws IOException {
		/* save the new user in the map */
		sessionsMap.put(userid, userSession);
		/* Sends all the connected users to the new user */
		Set<String> onlineSet = sessionsMap.keySet();
		Set<String> coachesidSet = coachsOnLine(userid, onlineSet);
		State coachMessage = new State("open", userid, coachesidSet);// 後端交給前端判斷
		String statecoachMessageJson = gson.toJson(coachMessage);

		// 把上線教練通知給自己
		if (userSession.isOpen())
		{
			userSession.getAsyncRemote().sendText(statecoachMessageJson);
		}

		Set<String> useridSet = new HashSet<String>();
		useridSet.add(userid);
		State userMessage = new State("open", userid, useridSet);// 後端交給前端判斷
		String stateuserMessageJson = gson.toJson(userMessage);
		
		// 學員名稱通知教練
		for (String coachid : coachesidSet) {
			Session coachsession = sessionsMap.get(coachid);
			if (coachsession.isOpen()) {
				coachsession.getAsyncRemote().sendText(stateuserMessageJson);
			}
		}

		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(), userid,
				coachesidSet);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);// 不是用key
																			// value取資料，直接對應參考類別，還原成一個chatmessage物件
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();

		if ("history".equals(chatMessage.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
				System.out.println("history = " + gson.toJson(cmHistory));
				return;
			}
		}

		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
			userSession.getAsyncRemote().sendText(message);
			JedisHandleMessage.saveChatMessage(sender, receiver, message);
		}
		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

//		if (userNameClose != null) {
//			State stateMessage = new State("close", userNameClose, userNames);
//			String stateMessageJson = gson.toJson(stateMessage);
//			Collection<Session> sessions = sessionsMap.values();
//			for (Session session : sessions) {
//				session.getAsyncRemote().sendText(stateMessageJson);
//			}
//		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}

	public Set<String> coachsOnLine(String userid, Set<String> onlineSet) {

		List<String> list = new ArrayList<String>();
		ClassDetailService classdetSer = new ClassDetailService();
		list = classdetSer.studentChat(userid);
		Set<String> coachlist = new HashSet<String>();

		for (String onlineid : onlineSet) {
			for (String coachid : list) {
				if (onlineid.equals(coachid)) {
					coachlist.add(coachid);
				}
			}
		}

		return coachlist;
	}
}
