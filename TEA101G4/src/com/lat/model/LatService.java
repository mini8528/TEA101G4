package com.lat.model;

import java.sql.Date;
import java.util.List;

import javax.print.DocFlavor.STRING;


public class LatService {

	private Lat_interface dao;

	public LatService() {
		dao = new LatDAO();
}
	public LatVO addLat(String adminid,String admin2id,	
	  String text,byte[] image,Date adddate,Date updatetime,Date uploaddate) {

		LatVO latVO = new LatVO();
		latVO.setAdminid(adminid);
		latVO.setAdmin2id(admin2id);
		latVO.setText(text);
		latVO.setImage(image);
		latVO.setAdddate(adddate);
		latVO.setUpdatetime(updatetime);;
		latVO.setUploaddate(uploaddate);
	
		dao.insert(latVO);

		return latVO;
	}

	
	
	public LatVO updateLat(String latestnewsid,String adminid,String admin2id,	
			  String text,byte[] image,Date adddate,Date updatetime,Date uploaddate) {

		LatVO latVO = new LatVO();
		
		latVO.setLatestnewsid(latestnewsid);
		latVO.setAdminid(adminid);
		latVO.setAdmin2id(admin2id);
		latVO.setText(text);
		latVO.setImage(image);
		latVO.setAdddate(adddate);
		latVO.setUpdatetime(updatetime);
		latVO.setUploaddate(uploaddate);
		
		dao.update(latVO);

		return latVO;
	}

	



	public void deleteLat(String latVO) {
		dao.delete(latVO);
	}

	public LatVO getOneLat(String latestnewsid) {
		return dao.findByPrimaryKey(latestnewsid);
	}

	public List<LatVO> getAll() {
		return dao.getAll();
	}
	
	
	//����
	public static void main(String[] args) {
		LatService dao = new LatService();
		
		
//	dao.deleteLat("LA14");
	
//	List<LatVO> list = dao.getAll();
//	for (LatVO emp : list) {
//		System.out.print(emp.getLatestnewsid() + ",");
//		System.out.print(emp.getAdminid() + ",");
//		System.out.print(emp.getAdmin2id() + ",");
//		System.out.print(emp.getText() + ",");
//		System.out.print(emp.getImage() + ",");
//		System.out.print(emp.getAdddate() + ",");
//		System.out.print(emp.getUpdatetime()+",");
//		System.out.print(emp.getUploaddate() + ",");
//		System.out.println();
//	}
//		
//		LatVO lad2 = new LatVO();
//		lad2.setLatestnewsid("LA16");
//		lad2.setAdminid("AD003");
//		lad2.setAdmin2id("ACCOUNT2");
//		lad2.setText("�W��");
//		lad2.setImage(null);
//		lad2.setAdddate(java.sql.Date.valueOf("2051-8-4"));
//		lad2.setUpdatetime(java.sql.Date.valueOf("2051-8-9"));
//		lad2.setUploaddate(java.sql.Date.valueOf("2051-8-9"));
//		dao.updateLat(lad2);
		
		
		LatVO lad3 = dao.getOneLat("LA16");
		System.out.print(lad3.getAdminid() + ",");
		System.out.print(lad3.getAdmin2id() + ",");
		System.out.print(lad3.getText() + ",");
		System.out.print(lad3.getImage() + ",");
		System.out.print(lad3.getAdddate() + ",");
		System.out.print(lad3.getUpdatetime()+",");
		System.out.print(lad3.getUploaddate() + ",");
		System.out.println("---------------------");
}
	
	
	
}