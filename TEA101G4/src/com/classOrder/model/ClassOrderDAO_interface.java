package com.classOrder.model;
import java.util.*;

import com.classDetail.model.ClassDetailVO;
import com.coachComment.model.CoachCommentVO;

public interface ClassOrderDAO_interface {

	public void insert(ClassOrderVO classOrderVO);
    public void update(ClassOrderVO classOrderVO);
    public void update_paymentStatus(ClassOrderVO classOrderVO);
    
    public void delete(String classOrderID);
    
    public ClassOrderVO findByPrimaryKey(String classOrderID);
    
    public List<ClassOrderVO> getAll();
    public List<ClassOrderVO> getSomeByMemberId(String memberID); 			//（管理員）依照會員ID查詢有成立的訂單
	public List<ClassOrderVO> getSomeByPaymentStatus(String paymentStatus); 	//（管理員）依照出貨狀態查詢訂單
	
	public void insertWithClassOrder(ClassOrderVO classOrderVO, List<ClassDetailVO> testList);
}

