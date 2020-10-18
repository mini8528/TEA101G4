package com.coachClass.model;

import java.util.List;
import java.util.Set;

import com.classDetail.model.ClassDetailVO;


public interface CoachClassDAO_interface {
	
	public void insert(CoachClassVO coachClassVO);
	public void update(CoachClassVO coachClassVO);
	
	public void delete(String coachClassID);
	
	public CoachClassVO findByPrimaryKey(String coachClassID);
	
	public List<CoachClassVO> getAll();
	public List<CoachClassVO> getCoachClassByName(String className); 				//（管理員）查多筆商品，用NAME(KW)
	
	public String getMemberName(String coachClassID);
	
}

