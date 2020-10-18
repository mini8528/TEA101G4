package com.classDetail.model;

import java.sql.Connection;
import java.util.*;

public interface ClassDetailDAO_interface {
	public void insert(ClassDetailVO classDetailVO);
	public void update(ClassDetailVO classDetailVO);
	public void delete(String classDetailID);
	public ClassDetailVO findByPrimaryKey(String classDetailID);
	public List<ClassDetailVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
	public void insert2(ClassDetailVO classDetailVO, Connection con);
	
	public List<ClassDetailVO> findbyFK(String classOrderID);
	//顏慈
	public List<String> studentFindCoach(String memberid);
}
