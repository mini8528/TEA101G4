package com.member.model;

import java.sql.Date;
import java.util.List;

public class MemberService {
	
	private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberJDBCDAO();
	}
	public MemberVO addMember(
			String name, String account, String password, String gender, String phone, 
			Date birthday, String email, byte[] photo, String address, String authority,
			String qualifications, String expertise, String introduction, byte[] photo1, byte[] photo2,
			byte[] photo3, Date adddate) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setName(name);
		memberVO.setAccount(account);
		memberVO.setPassword(password);
		memberVO.setGender(gender);
		memberVO.setPhone(phone);
		memberVO.setBirthday(birthday);
		memberVO.setEmail(email);
		memberVO.setPhoto(photo);
		memberVO.setAddress(address);
		memberVO.setAuthority(authority);
		memberVO.setQualifications(qualifications);
		memberVO.setExpertise(expertise);
		memberVO.setIntroduction(introduction);
		memberVO.setPhoto1(photo1);
		memberVO.setPhoto2(photo2);
		memberVO.setPhoto3(photo3);
		memberVO.setAdddate(adddate);
		
		dao.insert(memberVO);
		
		return memberVO;
	}

	public MemberVO updateMember( String memberid,
			String name, String account, String password, String gender, String phone, 
			Date birthday, String email, byte[] photo, String address, String authority,
			String qualifications, String expertise, String introduction, byte[] photo1, byte[] photo2,
			byte[] photo3, Date adddate) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemberid(memberid);
		memberVO.setName(name);
		memberVO.setAccount(account);
		memberVO.setPassword(password);
		memberVO.setGender(gender);
		memberVO.setPhone(phone);
		memberVO.setBirthday(birthday);
		memberVO.setEmail(email);
		memberVO.setPhoto(photo);
		memberVO.setAddress(address);
		memberVO.setAuthority(authority);
		memberVO.setQualifications(qualifications);
		memberVO.setExpertise(expertise);
		memberVO.setIntroduction(introduction);
		memberVO.setPhoto1(photo1);
		memberVO.setPhoto2(photo2);
		memberVO.setPhoto3(photo3);
		memberVO.setAdddate(adddate);
		
		dao.update(memberVO);
		return memberVO;
	}
	
	public List<MemberVO> getAll(){
		return dao.getAll();
	}
	public MemberVO getOneMember(String memberid) {
		return dao.findByPrimaryKey(memberid);
	}
	
	public void deleteMember(String memberid) {
		dao.delete(memberid);
	}
	
	public MemberVO getOneAccount(String account) {
		return dao.findByAccount(account);
	}
}
