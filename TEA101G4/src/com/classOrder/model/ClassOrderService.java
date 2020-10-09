package com.classOrder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


public class ClassOrderService {
	
	private ClassOrderDAO_interface dao;

	public ClassOrderService() {
		dao = new ClassOrderJDBCDAO();
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
