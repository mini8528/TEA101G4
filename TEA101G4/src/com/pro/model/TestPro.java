package com.pro.model;

import java.util.List;


public class TestPro {

	public static void main(String[] args) {
		ProJDBCDAO pro = new ProJDBCDAO();
//		
		// �s�W
//		ProVO pro1 = new ProVO();
//		
//		pro1.setProductid("PD0007");
//		pro1.setMemberid("M007");
//		pro1.setCommtext("�W��");
//		pro1.setCommstar(8);
//		pro1.setAdddate(java.sql.Date.valueOf("2016-08-07"));
//		pro1.setEditdate(null);
//		pro1.setStatus("N");
//		pro.insert(pro1);
//		// 修改
//		ProVO pro2 = new ProVO();
//	
//		pro2.setProdcommid("PROD0009");
//		pro2.setProductid("PD0007");
//		pro2.setMemberid("M007");
//		pro2.setCommtext("�W��123");
//		pro2.setCommstar(8);
//		pro2.setAdddate(java.sql.Date.valueOf("2016-08-11"));
//		pro2.setEditdate(java.sql.Date.valueOf("2016-08-11"));
//		pro2.setStatus("N");
//		pro.update(pro2);
		
		// �R��
//		pro.delete("PROD0008");
		// �d��
//		ProVO pro3 = pro.findByPrimaryKey("PROD0009");
//		System.out.print(pro3.getProdcommid() + ",");			                         
//		System.out.print(pro3.getProductid() + ",");
//		System.out.print(pro3.getMemberid() + ",");
//		System.out.print(pro3.getCommtext() + ",");
//		System.out.print(pro3.getCommstar() + ",");
//		System.out.print(pro3.getAdddate() + ",");
//		System.out.print(pro3.getEditdate() + ",");
//		System.out.print(pro3.getStatus() + ",");									 
//		System.out.println("---------------------");

		// �d��
//		List<ProVO> list = pro.getAll();
//		for (ProVO pro4 : list) {
//		System.out.print(pro4.getProdcommid() + ",");			                         
//		System.out.print(pro4.getProductid() + ",");
//		System.out.print(pro4.getMemberid() + ",");
//		System.out.print(pro4.getCommtext() + ",");
//		System.out.print(pro4.getCommstar() + ",");
//		System.out.print(pro4.getAdddate() + ",");
//		System.out.print(pro4.getEditdate() + ",");
//		System.out.print(pro4.getStatus() + ",");
//		System.out.println();		
//		}
		
		ProService ps = new ProService();
		List<ProVO> list = ps.getAllStatusYById("PD0001");
		for(ProVO p : list) {
			System.out.println(p.getProdcommid() + ",");			                         
			System.out.println(p.getProductid() + ",");
			System.out.println(p.getMemberid() + ",");
			System.out.println(p.getCommtext() + ",");
			System.out.println(p.getCommstar() + ",");
			System.out.println(p.getAdddate() + ",");
			System.out.println(p.getEditdate() + ",");
			System.out.println("status:" + p.getStatus());
		}

	}

}
