package com.orderdetail.model;

import java.util.List;

public class OrderdetailService {
	private OrderdetailDAO_interface dao;
	
	public OrderdetailService() {
//		dao = new OrderdetailJDBCDAO();
		dao = new OrderdetailDAO();
	}
	public OrderdetailVO addOrderdetail(String ordermasterid,String specid,Integer quantity) {
		OrderdetailVO orderdetailVO = new OrderdetailVO();
//		orderdetailVO.setOrderdetailid(orderdetailid);
		orderdetailVO.setOrdermasterid(ordermasterid);
		orderdetailVO.setSpecid(specid);
		orderdetailVO.setQuantity(quantity);
		dao.insert(orderdetailVO);
		return orderdetailVO;
	}
	public OrderdetailVO updateOrderdetail(String orderdetailid,String ordermasterid,String specid,Integer quantity) {
		OrderdetailVO orderdetailVO = new OrderdetailVO();
		orderdetailVO.setOrderdetailid(orderdetailid);
		orderdetailVO.setOrdermasterid(ordermasterid);
		orderdetailVO.setSpecid(specid);
		orderdetailVO.setQuantity(quantity);
		dao.update(orderdetailVO);
		return orderdetailVO;
	}
	public void deleteOrderdetail(String orderdetailid) {
		dao.delete(orderdetailid);
	}
	public OrderdetailVO getOneOrderdetail(String orderdetailid) {
		return dao.findbyPrimaryKey(orderdetailid);
	}
	public List<OrderdetailVO> getAll(){
		return dao.getAll();
	}
	public List<OrderdetailVO> getDetailByMaster(String ordermasterid) {
		return dao.findbyFK(ordermasterid);
	}
}
