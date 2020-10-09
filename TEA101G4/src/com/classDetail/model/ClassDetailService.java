package com.classDetail.model;

import java.sql.Date;
import java.util.List;

public class ClassDetailService {
	
	private ClassDetailDAO_interface dao;

	public ClassDetailService() {
		dao = new ClassDetailJDBCDAO();
	}

	public ClassDetailVO addClassDetail( String classOrderID,
			String coachClassID, Integer quantity) {

		ClassDetailVO classDetailVO = new ClassDetailVO();
		
		classDetailVO.setClassOrderID(classOrderID);
		classDetailVO.setCoachClassID(coachClassID);
		classDetailVO.setQuantity(quantity);
		dao.insert(classDetailVO);
		
		return classDetailVO;
	}

	public ClassDetailVO updateClassDetail(String classDetailID, String classOrderID,
			String coachClassID, Integer quantity) {

		ClassDetailVO classDetailVO = new ClassDetailVO();
		
		classDetailVO.setClassDetailID(classDetailID);
		classDetailVO.setClassOrderID(classOrderID);
		classDetailVO.setCoachClassID(coachClassID);
		classDetailVO.setQuantity(quantity);
		dao.update(classDetailVO);
		
		return classDetailVO;
	}
	
	public void deleteClassDetail(String classDetailID) {
		dao.delete(classDetailID);
	}

	public ClassDetailVO getOneClassDetail(String classDetailID) {
		return dao.findByPrimaryKey(classDetailID);
	}

	public List<ClassDetailVO> getAll() {
		return dao.getAll();
	}
	
	public List<String> studentChat(String memberid) {
		return dao.studentFindCoach(memberid);
	}
}
