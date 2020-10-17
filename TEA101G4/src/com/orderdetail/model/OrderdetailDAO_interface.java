 package com.orderdetail.model;

import java.util.List;
public interface OrderdetailDAO_interface {

	public void insert(OrderdetailVO orderdetailVO); 				//（會員？/管理員？？）新增訂單明細
	public void update(OrderdetailVO orderdetailVO); 				//（管理員？）編輯訂單明細
	public void delete(String orderdetailid); 						//（管理員？）刪除商品
	
																	//------------查詢----------------
	public List<OrderdetailVO> getAll();  							//（管理員）查詢全部訂單明細
	public OrderdetailVO findbyPrimaryKey(String orderdetailid); 	//（管理員）輸入PK查詢單筆訂單明細
	
	public List<OrderdetailVO> findbyFK(String ordermasterid); 		//（管理員/會員？）輸入（訂單編號FK）
																					//	查詢單筆訂單明細
//	------------------
	//同時新增訂單主檔與明細檔 (訂單主檔與明細檔一次新增成功)多
    public void insert2 (OrderdetailVO orderdetailVO , java.sql.Connection con);
}
