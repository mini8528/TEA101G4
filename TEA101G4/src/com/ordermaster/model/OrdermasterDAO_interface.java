 package com.ordermaster.model;

import java.sql.Date;
import java.util.List;

import com.orderdetail.model.OrderdetailVO;


public interface OrdermasterDAO_interface {
	public void insert(OrdermasterVO ordermasterVO); 				//（會員）新增訂單
	
	public void update(OrdermasterVO ordermasterVO); 				//（管理員）編輯訂單
	public void update_orderstatus(OrdermasterVO ordermasterVO); 	//（管理員）編輯訂單出貨狀況
	public void update_orderstatus2(List<OrdermasterVO> list ,String orderstatus); 	//（管理員）編輯多筆訂單出貨狀況**
	
	
	public void delete(String ordermasterid); 						//（管理員）刪除訂單

	public OrdermasterVO findbyPrimaryKey(String ordermasterid); 	// (管理員/會員）查詢單筆訂單
	public List<OrdermasterVO> getAll();  							//（管理員）查詢全部訂單
	public List<OrdermasterVO> getSomeByMemberid(String memberid); 			//（管理員）依照會員ID查詢有成立的訂單
	public List<OrdermasterVO> getSomeByOrderstatus(String orderstatus); 	//（管理員）依照出貨狀態查詢訂單
	
	public List<OrdermasterVO> getSomeByPayAndExpire(String paystatus,Date pickdate); 	//**（管理員）依照付款狀態(XX日期前未付款)
																						//	查詢訂單
	public List<OrdermasterVO> getSomeByPayAndOrderstatus(String paystatus,String orderstatus); 	
																						//**（管理員）已付款、未出貨
//	-------------------
	 //同時新增訂單主檔與明細檔 (訂單主檔與明細檔一次新增成功)一
	public void insertWithOderMaster(OrdermasterVO ordermasterVO , List<OrderdetailVO> list);
	 
}
