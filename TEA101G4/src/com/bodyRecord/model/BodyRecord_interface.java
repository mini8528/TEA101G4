package com.bodyRecord.model;

import java.util.List;


public interface BodyRecord_interface {
	
	public void insert(BodyRecordVO bodyRecordVO);
	public void update(BodyRecordVO bodyRecordVO);
	public void delete(String bodyRecordID);
	public BodyRecordVO findByPrimaryKey(String bodyRecordID);
	public List<BodyRecordVO> getAll();

}
