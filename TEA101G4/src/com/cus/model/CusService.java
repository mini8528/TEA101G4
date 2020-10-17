package com.cus.model;

import java.sql.Date;
import java.util.List;





public class CusService {
	private Cus_interface dao;

	public CusService() {
		dao = new CusDAO();
}
	public CusVO addCus(String memberid,String subject,String email,
		  String problemtext,
		  Date complaintdate) {

		CusVO cusVO = new CusVO();
		
		cusVO.setMemberid(memberid);
		cusVO.setSubject(subject);
		cusVO.setEmail(email);
		cusVO.setProblemtext(problemtext);
		cusVO.setComplaintdate(complaintdate);
		
		
		dao.insert(cusVO);

		return cusVO;
	}

	
	
	public CusVO updateCus(String customerserviceid,String adminid,
			  String replytext,
			  Date replydate
			  ) {

		CusVO cusVO = new CusVO();

		cusVO.setCustomerserviceid(customerserviceid);
		cusVO.setAdminid(adminid);

		cusVO.setReplytext(replytext);
		cusVO.setReplydate(replydate);
		
		dao.update(cusVO);

		return cusVO;
	}

	


	public void deleteAdm(String cusVO) {
		dao.delete(cusVO);
	}

	public CusVO getOneCus(String customerserviceid) {
		return dao.findByPrimaryKey(customerserviceid);
	}

	public List<CusVO> getAll() {
		return dao.getAll();
	}
	
	public List<CusVO> getCusByMid(String memberid){
		return dao.getCusByMid(memberid);
	}
	
	
	//測試
		public static void main(String[] args) {
			CusService dao = new CusService();	
			
			
			List<CusVO> bb = dao.getCusByMid("M004");
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
			
			System.out.println();}
//		dao.deleteLat("LA14");
		
//
//			CusVO cus2 = new CusVO();
//			cus2.setCustomerserviceid("LD0001");
//			cus2.setMemberid("M005");
//			cus2.setAdminid("AD002");
//			cus2.setSubject("�W�n132");
//			cus2.setEmail("PETEP@TIBIME.COM");
//			cus2.setProblemtext("�N�O���岫123");
//			cus2.setComplaintdate(java.sql.Date.valueOf("2016-08-07"));
//			cus2.setReplytext("�ԩ�123");
//			cus2.setReplydate(java.sql.Date.valueOf("2016-08-07"));
//			
//			
//			CusVO cus3 = dao.getOneCus("LD0001");
//			System.out.print(cus3.getCustomerserviceid() + ",");			                         
//			System.out.print(cus3.getMemberid() + ",");									
//			System.out.print(cus3.getAdminid() + ",");									
//			System.out.print(cus3.getSubject() + ",");									
//			System.out.print(cus3.getEmail() + ",");									
//			System.out.print(cus3.getProblemtext() + ",");									 
//			System.out.print(cus3.getComplaintdate() + ",");									 
//			System.out.print(cus3.getReplytext() + ",");									 
//			System.out.print(cus3.getReplydate() + ",");									 
//			System.out.println("---------------------");
	}
		
	
	
	
}