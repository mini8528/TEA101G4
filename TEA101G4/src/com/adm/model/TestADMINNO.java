package com.adm.model;

import java.util.List;

public class TestADMINNO {

	public static void main(String[] args) {
		AdminidJDBCDAO adm = new AdminidJDBCDAO();

		// 新增
//		AdminnoVO adm1 = new AdminnoVO();
//		adm1.setMembername("Dvid");
//		adm1.setMemberuser("MANAGER");
//		adm1.setPasswd("123456789");
//		adm1.setGender("Y");
//		adm1.setPhone(87879487);
//		adm1.setBirthday(java.sql.Date.valueOf("2016-08-07"));
//		adm1.setEmail("123456@TIBIME.COM");
//		adm1.setPhoto(null);;
//		adm1.setAddress("高雄");
//		adm.insert(adm1);
		
		// 修改
//		AdminnoVO adm2 = new AdminnoVO();
//		adm2.setAdminid("AD003");
//		adm2.setMembername("David");
//		adm2.setMemberuser("MANAGER");
//		adm2.setPasswd("123456789");
//		adm2.setGender("Y");
//		adm2.setPhone(878787878);
//		adm2.setBirthday(java.sql.Date.valueOf("2016-08-07"));
//		adm2.setEmail("123456@TIBIME.COM");
//		adm2.setPhoto(null);
//		adm2.setAddress("宜蘭");
//		adm.update(adm2);
		// 刪除
//		adm.delete("AD003");
//
		// 查詢
//		AdminnoVO adm2 = adm.findByPrimaryKey("AD001");
//		System.out.print(adm2.getAdminid() + ",");
//		System.out.print(adm2.getMembername() + ",");
//		System.out.print(adm2.getMemberuser() + ",");
//		System.out.print(adm2.getPasswd() + ",");
//		System.out.print(adm2.getGender() + ",");
//		System.out.print(adm2.getPhone() + ",");
//		System.out.print(adm2.getBirthday() + ",");
//		System.out.print(adm2.getEmail() + ",");
//		System.out.print(adm2.getPhoto() + ",");
//		System.out.print(adm2.getAddress() + ",");
//		System.out.println("---------------------");

		// 查詢
		List<AdminnoVO> list = adm.getAll();
		for (AdminnoVO emp : list) {
			System.out.print(emp.getAdminid() + ",");
			System.out.print(emp.getMembername() + ",");
			System.out.print(emp.getMemberuser() + ",");
			System.out.print(emp.getPasswd() + ",");
			System.out.print(emp.getGender() + ",");
			System.out.print(emp.getPhone() + ",");
			System.out.print(emp.getBirthday() + ",");
			System.out.print(emp.getEmail() + ",");
			System.out.print(emp.getPhoto() + ",");
			System.out.print(emp.getAddress() + ",");
			System.out.println();
		}
		
	}

}

	