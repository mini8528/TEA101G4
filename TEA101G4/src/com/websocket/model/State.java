package com.websocket.model;

import java.util.Set;

import com.member.model.MemberVO;

public class State {
	private String type;
	// the user changing the state
	private String user;
	// total users
	private Set<String> users;
	
	private Set<MemberVO> members;

	public State(String type, String user, Set<String> users) {
		super();
		this.type = type;
		this.user = user;
		this.users = users;
	}
	
	public State(String type, Set<MemberVO> members, String user) {
		super();
		this.type = type;
		this.user = user;
		this.members = members;
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

	public Set<String> getUsers() {
		return users;
	}

	public void setUsers(Set<String> users) {
		this.users = users;
	}

}
