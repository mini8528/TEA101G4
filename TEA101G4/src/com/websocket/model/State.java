package com.websocket.model;

import java.util.Map;
import java.util.Set;

import com.member.model.MemberVO;

public class State {
	private String type;
	// the user changing the state
	private String user;
	// total users
	private Map<String, String> users;
	
	
	public State(String type, String user, Map<String, String> users) {
		super();
		this.type = type;
		this.user = user;
		this.users = users;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Map<String, String> getUsers() {
		return users;
	}
	public void setUsers(Map<String, String> users) {
		this.users = users;
	}
	
	
	

	
}
