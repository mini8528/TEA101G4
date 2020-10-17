package com.ordermaster.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.orderdetail.model.OrderdetailVO;

public class OrdermasterService {
	
	private OrdermasterDAO_interface dao;

	public OrdermasterService() {
//		dao = new OrdermasterJDBCDAO();
		dao = new OrdermasterDAO();
	}
//	------------
//	------------
	public OrdermasterVO auto_addOrderMasterAndDetail(String memberid,String payment,String paystatus,Timestamp orderdate,Date payexpire,String paycode,String receiver,String tel,String address,String orderstatus,List<String> specidAll,List<Integer> quantityAll) {
		OrdermasterVO ordermasterVO = new OrdermasterVO();
		ordermasterVO.setMemberid(memberid);
		ordermasterVO.setPayment(payment);
		ordermasterVO.setPaystatus(paystatus);
		ordermasterVO.setOrderdate(orderdate);
		ordermasterVO.setPayexpire(payexpire);
		ordermasterVO.setPaycode(paycode);
		ordermasterVO.setReceiver(receiver);
		ordermasterVO.setTel(tel);
		ordermasterVO.setAddress(address);
		ordermasterVO.setOrderstatus(orderstatus);
		
		
		List<OrderdetailVO> testList = new ArrayList<OrderdetailVO>();
//		List<OrderdetailVO> orderdetailVO2 = new ArrayList<OrderdetailVO>();
		
//		OrderdetailVO orderdetailVO = new OrderdetailVO();
		for(int i = 0; i<specidAll.size();i++) {
			OrderdetailVO orderdetailVO = new OrderdetailVO();
			orderdetailVO.setSpecid(specidAll.get(i));
			orderdetailVO.setQuantity(quantityAll.get(i));
//			orderdetailVO2.add(orderdetailVO);
			System.out.println("orderdetailVO=>"+orderdetailVO.toString());
//			testList.add((orderdetailVO2.get(i)));
			testList.add(orderdetailVO);
			System.out.println("testList===>"+testList.toString());
		}
//		testList.add(orderdetailVO);
		dao.insertWithOderMaster(ordermasterVO, testList);
		return ordermasterVO;
	}
	
//	------------
	public OrdermasterVO addOrdermaster(String memberid,String payment,String paystatus,Timestamp orderdate,Date payexpire,String paycode,String receiver,String tel,String address,String orderstatus) {
		OrdermasterVO ordermasterVO = new OrdermasterVO();
//		ordermasterVO.setOrdermasterid(ordermasterid);
		ordermasterVO.setMemberid(memberid);
		ordermasterVO.setPayment(payment);
		ordermasterVO.setPaystatus(paystatus);
		ordermasterVO.setOrderdate(orderdate);
		ordermasterVO.setPayexpire(payexpire);
		ordermasterVO.setPaycode(paycode);
		ordermasterVO.setReceiver(receiver);
		ordermasterVO.setTel(tel);
		ordermasterVO.setAddress(address);
		ordermasterVO.setOrderstatus(orderstatus);
		dao.insert(ordermasterVO);
		return ordermasterVO;
	}
	public OrdermasterVO updateOrdermaster(String ordermasterid,String memberid,String payment,String paystatus,Timestamp orderdate,Date payexpire,String paycode,String receiver,String tel,String address,String orderstatus) {
		OrdermasterVO ordermasterVO = new OrdermasterVO();
		ordermasterVO.setOrdermasterid(ordermasterid);
		ordermasterVO.setMemberid(memberid);
		ordermasterVO.setPayment(payment);
		ordermasterVO.setPaystatus(paystatus);
		ordermasterVO.setOrderdate(orderdate);
		ordermasterVO.setPayexpire(payexpire);
		ordermasterVO.setPaycode(paycode);
		ordermasterVO.setReceiver(receiver);
		ordermasterVO.setTel(tel);
		ordermasterVO.setAddress(address);
		ordermasterVO.setOrderstatus(orderstatus);
		dao.update(ordermasterVO);
		return ordermasterVO;
	}
	public void deleteOrdermaster(String ordermasterid) {
		dao.delete(ordermasterid);
	}
	public OrdermasterVO getOneOrdermaster(String ordermasterid) {
		return dao.findbyPrimaryKey(ordermasterid);
	}
	public List<OrdermasterVO> getAll(){
		return dao.getAll();
	}
	public OrdermasterVO updateOrderStatus(String ordermasterid,String orderstatus) {
		OrdermasterVO ordermasterVO = new OrdermasterVO();
		ordermasterVO.setOrdermasterid(ordermasterid);
		ordermasterVO.setOrderstatus(orderstatus);
		dao.update_orderstatus(ordermasterVO);
		return ordermasterVO;
	}

	public List<OrdermasterVO> getOrderByMemberid(String memberid){
		return dao.getSomeByMemberid(memberid);
	}	
	public List<OrdermasterVO> getOrderByOrderstatus(String orderstatus){
		return dao.getSomeByOrderstatus(orderstatus);
	}
	
	
	public List<OrdermasterVO> getOrderByPayAndExpire(String paystatus,Date pickdate){
		return dao.getSomeByPayAndExpire(paystatus, pickdate);
	}
	public List<OrdermasterVO> getOrderByPayAndOrderstatus(String paystatus,String orderstatus){
		return dao.getSomeByPayAndOrderstatus(paystatus, orderstatus);
	}
	public OrdermasterVO updateOrderStatus2(List<String> list,String orderstatus) {
		OrdermasterVO ordermasterVO = new OrdermasterVO();
		List<OrdermasterVO> ordermasterVOlist = new ArrayList<OrdermasterVO>();
		for(String aa: list) {
			ordermasterVO.setOrdermasterid(aa.toString());
			ordermasterVO.setOrderstatus(orderstatus);
			ordermasterVOlist.add(ordermasterVO);
		}
		dao.update_orderstatus2(ordermasterVOlist, orderstatus);
		return ordermasterVO;
	}
}
