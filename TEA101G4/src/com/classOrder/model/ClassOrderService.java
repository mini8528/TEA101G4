package com.classOrder.model;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.classDetail.model.ClassDetailVO;


public class ClassOrderService {
	
	private ClassOrderDAO_interface dao;

	public ClassOrderService() {
		dao = new ClassOrderDAO();
	}

	
	public ClassOrderVO auto_addClassOrderAndDetail( String memberID, String payment, String paymentStatus,
			Date payExpire, String payCode, Timestamp orderDate, List<String> coachClassIDAll, List<Integer> quantityAll) {
		
		System.out.println("==================================================");
		System.out.println("===  Class Order Service  __  auto_addClassOrderAndDetail ================");
		System.out.println("==================================================");
		
		ClassOrderVO classOrderVO = new ClassOrderVO();
		classOrderVO.setMemberID(memberID);
		classOrderVO.setPayment(payment);
		classOrderVO.setPaymentStatus(paymentStatus);
		classOrderVO.setPayExpire(payExpire);
		classOrderVO.setPayCode(payCode);
		classOrderVO.setOrderDate(orderDate);
		
		ArrayList<ClassDetailVO> testList =new ArrayList<ClassDetailVO>();
		
		for( int i = 0; i < coachClassIDAll.size() ; i++) {
			ClassDetailVO classDetailVO = new ClassDetailVO();
			classDetailVO.setCoachClassID(coachClassIDAll.get(i));
			classDetailVO.setQuantity(quantityAll.get(i));
			System.out.println("classDetailVO=>"+classDetailVO.toString());
			testList.add(classDetailVO);
			System.out.println("testList===>"+testList.toString());

		}
		dao.insertWithClassOrder( classOrderVO,  testList); 
		return classOrderVO;
	}
	
	
	
	public ClassOrderVO addClassOrder( String memberID, String payment, String paymentStatus,
			Date payExpire, String payCode, Timestamp orderDate) {

		ClassOrderVO classOrderVO = new ClassOrderVO();
		
		classOrderVO.setMemberID(memberID);
		classOrderVO.setPayment(payment);
		classOrderVO.setPaymentStatus(paymentStatus);
		classOrderVO.setPayExpire(payExpire);
		classOrderVO.setPayCode(payCode);
		classOrderVO.setOrderDate(orderDate);
		
		dao.insert(classOrderVO);
		
		return classOrderVO;
	}

	public ClassOrderVO updateClassOrder(String classOrderID, String memberID, String payment, String paymentStatus,
			Date payExpire, String payCode, Timestamp orderDate) {

		ClassOrderVO classOrderVO = new ClassOrderVO();
		
		classOrderVO.setClassOrderID(classOrderID);
		classOrderVO.setMemberID(memberID);
		classOrderVO.setPayment(payment);
		classOrderVO.setPaymentStatus(paymentStatus);
		classOrderVO.setPayExpire(payExpire);
		classOrderVO.setPayCode(payCode);
		classOrderVO.setOrderDate(orderDate);
		
		dao.update(classOrderVO);
		
		return classOrderVO;
	}
	
	public void deleteClassOrder(String classOrderID) {
		dao.delete(classOrderID);
	}

	public ClassOrderVO getOneClassOrder(String classOrderID) {
		return dao.findByPrimaryKey(classOrderID);
	}

	public List<ClassOrderVO> getAll() {
		return dao.getAll();
	}

	public ClassOrderVO updatePaymentStatus(String classOrderID,String paymentStatus) {
		ClassOrderVO classOrderVO = new ClassOrderVO();
		classOrderVO.setClassOrderID(classOrderID);
		classOrderVO.setPaymentStatus(paymentStatus);
		dao.update_paymentStatus(classOrderVO);
		return classOrderVO;
	}

	public List<ClassOrderVO> getOrderByMemberId(String memberId){
		return dao.getSomeByMemberId(memberId);
	}	
	public List<ClassOrderVO> getOrderByOrderstatus(String paymentStatus){
		return dao.getSomeByPaymentStatus(paymentStatus);
	}

}
