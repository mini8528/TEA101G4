package com.adm.model;


import java.util.List;



public class AdminnoService {

	private Adminno_interface dao;

	public AdminnoService() {
		dao = new AdminidDAO();
//		dao = new AdminidJDBCDAO();
}
	public AdminnoVO addAdm(String membername, String memberuser,
			String passwd, String gender,String phone,java.sql.Date birthday, String email,
			byte[] photo, String address) {

		AdminnoVO admVO = new AdminnoVO();


		admVO.setMembername(membername);
		admVO.setMemberuser(memberuser);
		admVO.setPasswd(passwd);
		admVO.setGender(gender);
		admVO.setPhone(phone);
		admVO.setBirthday(birthday);
		admVO.setEmail(email);
		admVO.setPhoto(photo);
		admVO.setAddress(address);
		
		dao.insert(admVO);

		return admVO;
	}

	
	
	public AdminnoVO updateAdm(String adminid, String membername, String memberuser,
			String passwd, String gender,String phone,java.sql.Date birthday, String email,
			byte[] photo, String address) {

		AdminnoVO admVO = new AdminnoVO();

		admVO.setAdminid(adminid);
		admVO.setMembername(membername);
		admVO.setMemberuser(memberuser);
		admVO.setPasswd(passwd);
		admVO.setGender(gender);
		admVO.setPhone(phone);
		admVO.setBirthday(birthday);
		admVO.setEmail(email);
		admVO.setPhoto(photo);
		admVO.setAddress(address);
		
		dao.update(admVO);

		return admVO;
	}

	
//	public AdminnoVO addAdm( String membername, String memberuser,
//			String passwd, String gender,int phone,java.sql.Date birthday, String email,
//			byte[] photo, String address) {
//
//		AdminnoVO admVO = new AdminnoVO();
//		admVO.setMembername(membername);
//		admVO.setMemberuser(memberuser);
//		admVO.setPasswd(passwd);
//		admVO.setGender(gender);
//		admVO.setPhone(phone);
//		admVO.setBirthday(birthday);
//		admVO.setEmail(email);
//		admVO.setPhoto(photo);
//		admVO.setAddress(address);
//		
//		dao.update(admVO);
//
//		return admVO;
//	}


	public void deleteAdm(String adminnoVO) {
		dao.delete(adminnoVO);
	}

	public AdminnoVO getOneAdm(String adminid) {
		return dao.findByPrimaryKey(adminid);
	}
public List<AdminnoVO> getAll() {
	return dao.getAll();
}
	public AdminnoVO getAlluser(String memberuser) {
		return dao.findByMemberuser(memberuser);
	}
}