package com.cus.model;

import java.util.List;

import com.lat.model.LatJDBCDAO;

public class TestCus {

	public static void main(String[] args) {
		CusJDBCDAO cus = new CusJDBCDAO();
		
		// 新增
//		CusVO cus1 = new CusVO();
//		cus1.setMemberid("M003");
//		cus1.setSubject("嗨");
//		cus1.setEmail("PETEP@TIBIME.COM");
//		cus1.setProblemtext("你是在哈搂");
//		cus1.setComplaintdate(java.sql.Date.valueOf("2016-08-07"));
//		cus.insert(cus1);
		// 修改(回復)
		CusVO cus2 = new CusVO();
		cus2.setCustomerserviceid("LD0012");
//		cus2.setMemberid("M005");
		cus2.setAdminid("AD002");
//		cus2.setSubject("cooafuck");
//		cus2.setEmail("PETEP@TIBIME.COM");
//		cus2.setProblemtext("掰");
//		cus2.setComplaintdate(java.sql.Date.valueOf("2016-08-07"));
		cus2.setReplytext("coo4fuck");
		cus2.setReplydate(java.sql.Date.valueOf("2016-08-07"));
		cus.update(cus2);
		
		// �R��
//		dao.delete("LD0005");
//		 �d��
//		CusVO cus3 = cus.findByPrimaryKey("LD0001");
//		System.out.print(cus3.getCustomerserviceid() + ",");			                         
//		System.out.print(cus3.getMemberid() + ",");									
//		System.out.print(cus3.getAdminid() + ",");									
//		System.out.print(cus3.getSubject() + ",");									
//		System.out.print(cus3.getEmail() + ",");									
//		System.out.print(cus3.getProblemtext() + ",");									 
//		System.out.print(cus3.getComplaintdate() + ",");									 
//		System.out.print(cus3.getReplytext() + ",");									 
//		System.out.print(cus3.getReplydate() + ",");									 
//		System.out.println("---------------------");

		// 全部查詢
//		List<CusVO> list = cus.getAll();
//		for (CusVO cus4 : list) {
//			System.out.print(cus4.getCustomerserviceid() + ",");			                         
//			System.out.print(cus4.getMemberid() + ",");									
//			System.out.print(cus4.getAdminid() + ",");									
//			System.out.print(cus4.getSubject() + ",");									
//			System.out.print(cus4.getEmail() + ",");									
//			System.out.print(cus4.getProblemtext() + ",");									 
//			System.out.print(cus4.getComplaintdate() + ",");									 
//			System.out.print(cus4.getReplytext() + ",");									 
//			System.out.print(cus4.getReplydate() + ",");									 
//			System.out.println();
//		}
	
//		 單獨查詢
			Cus_interface aa = new CusJDBCDAO();
		List<CusVO> bb = aa.getCusByMid("M005");
		System.out.println(""+bb);
		for(CusVO cus4: bb) {
			System.out.print(cus4.getCustomerserviceid() + ",");			                         
			System.out.print(cus4.getMemberid() + ",");									
			System.out.print(cus4.getAdminid() + ",");									
			System.out.print(cus4.getSubject() + ",");									
			System.out.print(cus4.getEmail() + ",");									
			System.out.print(cus4.getProblemtext() + ",");									 
			System.out.print(cus4.getComplaintdate() + ",");									 
			System.out.print(cus4.getReplytext() + ",");									 
			System.out.print(cus4.getReplydate() + ",");
		}
		System.out.println("------------------");
}

}
