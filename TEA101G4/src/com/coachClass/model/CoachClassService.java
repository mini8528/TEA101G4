package com.coachClass.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


public class CoachClassService {
	private CoachClassDAO_interface dao;

	public CoachClassService() {
		dao = new CoachClassDAO();
	}

	public CoachClassVO addCoachClass(  String memberID, String className, String classContext, byte[] photo, 
			Timestamp startTime, Integer price, Integer quantity, String address, Timestamp addDate, Timestamp editDate) {

		CoachClassVO coachClassVO = new CoachClassVO();
		
		coachClassVO.setMemberID(memberID);
		coachClassVO.setClassName(className);
		coachClassVO.setClassContext(classContext);
		coachClassVO.setPhoto(photo);
		coachClassVO.setStartTime(startTime);
		coachClassVO.setPrice(price);
		coachClassVO.setQuantity(quantity);
		coachClassVO.setAddress(address);
		coachClassVO.setAddDate(addDate);
		coachClassVO.setEditDate(editDate);
		dao.insert(coachClassVO);
		
		return coachClassVO;
	}

	public CoachClassVO updateCoachClass(String coachClassID, String memberID, String className, String classContext, byte[] photo, 
			Timestamp startTime, Integer price, Integer quantity, String address, Timestamp addDate, Timestamp editDate) {

		CoachClassVO coachClassVO = new CoachClassVO();
		
		coachClassVO.setCoachClassID(coachClassID);
		coachClassVO.setMemberID(memberID);
		coachClassVO.setClassName(className);
		coachClassVO.setClassContext(classContext);
		coachClassVO.setPhoto(photo);
		coachClassVO.setStartTime(startTime);
		coachClassVO.setPrice(price);
		coachClassVO.setQuantity(quantity);
		coachClassVO.setAddress(address);
		coachClassVO.setAddDate(addDate);
		coachClassVO.setEditDate(editDate);
		dao.update(coachClassVO);
		
		return coachClassVO;
	}
	
	public void deleteCoachClass(String coachClassID) {
		dao.delete(coachClassID);
	}

	public CoachClassVO getOneCoachClass(String coachClassID) {
		return dao.findByPrimaryKey(coachClassID);
	}

	public List<CoachClassVO> getAll() {
		return dao.getAll();
	}
	
	public List<CoachClassVO> getCoachClass(String className){
		return dao.getCoachClassByName(className);
	}
	
	public String getMemberName(String coachClassID) {
		return dao.getMemberName(coachClassID);
	}
	
}
