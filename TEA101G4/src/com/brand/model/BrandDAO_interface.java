 package com.brand.model;

import java.util.*;

public interface BrandDAO_interface {
	public void insert(BrandVO brandVO);				//（管理員）新增品牌
	public void update(BrandVO brandVO);				//（管理員）編輯品牌
	public void delete(String brandid);					//（管理員）刪除品牌
	
	public BrandVO findByPrimaryKey(String brandid);	//（管理員）查詢單筆品牌
	public List<BrandVO> getAll();						//（管理員）查詢全部品牌
	
	public List<BrandVO> getBrandsByName(String name);	//(管理員、會員？) 用品牌名稱關鍵字搜出所有品牌項目
}
