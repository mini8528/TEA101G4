package com.adm.model;

import java.util.List;
import java.util.Set;


public interface Adminno_interface {
	public void insert(AdminnoVO adminnoVO);
	public void update(AdminnoVO adminnoVO);
	public void delete(String adminnoVO);
	public AdminnoVO findByPrimaryKey(String adminid);
	public List<AdminnoVO> getAll();
	
	public AdminnoVO findByMemberuser (String memberuser);
}
