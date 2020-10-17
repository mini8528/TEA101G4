package com.lat.model;

import java.util.List;

public interface  Lat_interface {
	public void insert(LatVO latestnewsVO);
	public void update(LatVO latestnewsVO);
	public void delete(String latestnewsVO);//
	public LatVO findByPrimaryKey(String latestnewsid);
	 public List<LatVO> getAll();
}
