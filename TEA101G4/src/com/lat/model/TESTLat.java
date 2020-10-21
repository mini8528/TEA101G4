package com.lat.model;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TESTLat {

	public static void main(String[] args) {
		LatJDBCDAO dao = new LatJDBCDAO();

		// �s�W
//		LatVO lad1 = new LatVO();
//		lad1.setAdminid("AD004");
//		lad1.setAdmin2id("AD004");
//		lad1.setText("hehehehehehehe");
//		lad1.setImage(null);
//		lad1.setAdddate(java.sql.Date.valueOf("2051-8-10"));
//		lad1.setUpdatetime(java.sql.Date.valueOf("2051-8-16"));
//		lad1.setUploaddate(java.sql.Date.valueOf("2051-8-16"));
//		dao.insert(lad1);
	
		// �ק�
//		LatVO lad2 = new LatVO();
//		lad2.setLatestnewsid("LA16");
//		lad2.setAdminid("AD003");
//		lad2.setAdmin2id("AD003");
//		lad2.setText("�W��");
//		lad2.setImage(null);
//		lad2.setAdddate(java.sql.Date.valueOf("2051-8-4"));
//		lad2.setUpdatetime(java.sql.Date.valueOf("2051-8-9"));
//		lad2.setUploaddate(java.sql.Date.valueOf("2051-8-9"));
//		dao.update(lad2);

//		
		
//		dao.delete("LA0003");
		 //�d��
//		LatVO lad3 = dao.findByPrimaryKey("LA0001");
//		System.out.print(lad3.getLatestnewsid() + ",");
//		System.out.print(lad3.getAdminid() + ",");
//		System.out.print(lad3.getAdmin2id() + ",");
//		System.out.print(lad3.getText() + ",");
//		System.out.print(lad3.getImage() + ",");
//		System.out.print(lad3.getAdddate() + ",");
//		System.out.print(lad3.getUpdatetime()+",");
//		System.out.print(lad3.getUploaddate() + ",");
//		System.out.println("---------------------");

		// �d��
		List<LatVO> list = dao.getAll();
		for (LatVO emp : list) {
			System.out.print(emp.getLatestnewsid() + ",");
			System.out.print(emp.getAdminid() + ",");
			System.out.print(emp.getAdmin2id() + ",");
			System.out.print(emp.getText() + ",");
			System.out.print(emp.getImage() + ",");
			System.out.print(emp.getAdddate() + ",");
			System.out.print(emp.getUpdatetime()+",");
			System.out.print(emp.getUploaddate() + ",");
			System.out.println();
					
		}
	}

}
