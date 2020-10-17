package com.pro.model;

import java.util.List;



public interface Pro_interface {
	public void insert(ProVO proVO);//----新增評論----
	public void update(ProVO proVO);//----修改評論狀態----
	public void updatetext(ProVO proVO);//----修改評論內容----
	public void delete(String prodcommid);
	public ProVO findByPrimaryKey(String prodcommid);//----查詢單筆商品評論----
	 public List<ProVO> getAll();//----查詢全部評論----
//	 public List<ProVO> getproductid(String status);//----查詢多筆商品評論----
}
