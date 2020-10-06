package com.member.model;

import java.util.List;
import java.util.Set;


public interface MemberDAO_interface {
			public void insert(MemberVO memberVO);
			public void update(MemberVO memberVO);
			public void delete(String memberid);
			public MemberVO findByPrimaryKey(String memberid);
			public List<MemberVO> getAll();
			
			public MemberVO findByAccount(String account);
			
}
