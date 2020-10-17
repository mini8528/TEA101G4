package com.cus.model;

import java.util.List;


import com.lat.model.LatVO;

public interface Cus_interface {
	public void insert(CusVO cusVO);
	public void update(CusVO cusVO);
	public void delete(String customerserviceid);
	public CusVO findByPrimaryKey(String customerserviceid);
	 public List<CusVO> getAll();
	 public List<CusVO> getCusByMid(String memberid);
}
