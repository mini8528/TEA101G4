 package com.spec.model;

import java.util.List;
public interface SpecDAO_interface {
	public void insert(SpecVO specVO);						//(管理員）新增商品新規格
	public void update(SpecVO specVO);						//(管理員）修改商品規格
	public void delete(String specid);						//(管理員）刪除商品規格
	
	public SpecVO findByPrimaryKey(String specid);			//(管理員）查詢單筆商品規格
	public List<SpecVO> getAll();							//(管理員）查詢全部商品規格
	
	public List<SpecVO> getSpecByProductid(String productid);//(管理員、會員)查詢（瀏覽）某商品的所有規格
	
//	------------------
	//同時新增商品主檔與規格檔 (訂單主檔與明細檔一次新增成功)多
    public void insert2 (SpecVO specVO , java.sql.Connection con);
}
