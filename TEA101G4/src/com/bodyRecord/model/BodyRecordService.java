package com.bodyRecord.model;

import java.sql.Timestamp;
import java.util.List;


public class BodyRecordService {

	private BodyRecord_interface dao;

	public BodyRecordService() {
		dao = new BodyRecordJDBCDAO();
	}

	public BodyRecordVO addBodyRecord( String memberID,
			Timestamp uploadDate ,byte[] photo1 ,String description ,String describeStatus, Timestamp updateTime) {

		BodyRecordVO bodyRecordVO = new BodyRecordVO();
		
		bodyRecordVO.setMemberID(memberID);
		bodyRecordVO.setUploadDate(uploadDate);
		bodyRecordVO.setPhoto1(photo1);
		bodyRecordVO.setDescription(description);
		bodyRecordVO.setDescribeStatus(describeStatus);
		bodyRecordVO.setUpdateTime(updateTime);
		dao.insert(bodyRecordVO);
		
		return bodyRecordVO;
	}

	public BodyRecordVO updateBodyRecord(String bodyRecordID, String memberID,
			Timestamp uploadDate ,byte[] photo1 ,String description ,String describeStatus, Timestamp updateTime) {

		BodyRecordVO bodyRecordVO = new BodyRecordVO();
		
		bodyRecordVO.setBodyRecordID(bodyRecordID);
		bodyRecordVO.setMemberID(memberID);
		bodyRecordVO.setUploadDate(uploadDate);
		bodyRecordVO.setPhoto1(photo1);
		bodyRecordVO.setDescription(description);
		bodyRecordVO.setDescribeStatus(describeStatus);
		bodyRecordVO.setUpdateTime(updateTime);
		dao.update(bodyRecordVO);
		
		return bodyRecordVO;
	}
	
	public void deleteBodyRecord(String bodyRecordID) {
		dao.delete(bodyRecordID);
	}

	public BodyRecordVO getOneBodyRecord(String bodyRecordID) {
		return dao.findByPrimaryKey(bodyRecordID);
	}

	public List<BodyRecordVO> getAll() {
		return dao.getAll();
	}
}
