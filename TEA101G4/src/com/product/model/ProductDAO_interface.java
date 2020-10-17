 package com.product.model;

import java.util.List;

import com.ordermaster.model.OrdermasterVO;
import com.spec.model.SpecVO;

public interface ProductDAO_interface {
	public void insert(ProductVO productVO); 							//（管理員）新增商品
	public void update(ProductVO productVO); 							//（管理員）編輯商品
	public void update_status(ProductVO productVO); 					//（管理員）編輯商品上下架
	public void update_status2(List<ProductVO> list); 					//（管理員）編輯商品上下架
	
	public void delete(String productid); 								//（管理員）刪除商品

	public ProductVO findbyPrimaryKey(String productid); 				//（管理員）輸入PK查詢單筆商品
	public List<ProductVO> getAll();  									//（管理員）查詢所有商品
	public List<ProductVO> getAll_byStatus(String status);  			//（管理員、會員）查詢上下架商品
	
	public List<ProductVO> getProductsByName(String name); 				//（管理員）查多筆商品，用NAME(KW)
	public List<ProductVO> getProductsByCategory(String category,String status);		//（管理員）查多筆商品，用商品種類
	
	
//	-------------------
	 //同時新增商品主檔與規格檔 (訂單主檔與明細檔一次新增成功)一
	public void insertWithProduct(ProductVO productVO , List<SpecVO> list);
}
