package com.member.model;

import java.util.List;
import java.util.Set;

import com.bodyrecord.model.BodyrecordVO;

public interface MemberDAO_interface {
			public void insert(MemberVO memberVO);
			public void update(MemberVO memberVO);
			public void delete(String memberId);
			public MemberVO findByPrimaryKey(String memberId);
			public List<MemberVO> getAll();
			public Set<BodyrecordVO> getBodyrecordsByMemberId(String memberId);
}
