package com.bodyrecord.model;

import java.util.List;



public class BodyrecordService {
	
	private BodyrecordDAO_interface dao;

	public BodyrecordService() {
		dao = new BodyrecordDAO();
	}

	public BodyrecordVO addBodyrecord(String memberid, java.sql.Date uploaddate, byte[] photo1,
			String description, String describestatus, java.sql.Date updatetime) {

		BodyrecordVO bodyrecordVO = new BodyrecordVO();

		bodyrecordVO.setmemberId(memberid);
		bodyrecordVO.setuploaddate(uploaddate);
		bodyrecordVO.setphoto1(photo1);
		bodyrecordVO.setdescription(description);
		bodyrecordVO.setdescribestatus(describestatus);
		bodyrecordVO.setupdatetime(updatetime);
		dao.insert(bodyrecordVO);

		return bodyrecordVO;
	}

	public BodyrecordVO updateBodyrecord(String bodyrecordid , String memberid, java.sql.Date uploaddate, byte[] photo1,
			String description, String describestatus, java.sql.Date updatetime) {

		BodyrecordVO bodyrecordVO = new BodyrecordVO();

		bodyrecordVO.setbodyrecordid(bodyrecordid);
		bodyrecordVO.setmemberId(memberid);
		bodyrecordVO.setuploaddate(uploaddate);
		bodyrecordVO.setphoto1(photo1);
		bodyrecordVO.setdescription(description);
		bodyrecordVO.setdescribestatus(describestatus);
		bodyrecordVO.setupdatetime(updatetime);
		dao.update(bodyrecordVO);

		return bodyrecordVO;
	}

	public void deleteBodyrecord(String bodyrecordid) {
		dao.delete(bodyrecordid);
	}

	public BodyrecordVO getOneBodyrecord(String bodyrecordid) {
		return dao.findByPrimaryKey(bodyrecordid);
	}

	public List<BodyrecordVO> getAll() {
		return dao.getAll();
	}
}