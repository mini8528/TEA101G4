package com.trainingclsdetail.model;

import java.util.List;
import java.util.Map;

import com.traininghist.model.TrainingHistVO;



public interface TrainingClsDetailDAOIntf {

	 public void insert(TrainingClsDetailVO trainingclsdetailVO);
     public void update(TrainingClsDetailVO trainingclsdetailVO);
     public void delete(String trainingclsdetailid);
     public TrainingClsDetailVO findByPrimaryKey(String trainingclsdetailid);
     public List<TrainingClsDetailVO> getAll();
     //萬用複合查詢(傳入參數型態Map)(回傳 List)
     public List<TrainingClsDetailVO> getAll(Map<String, String[]> map);
     public List<TrainingClsDetailVO> select(String trainingclsid);
     public List<TrainingClsDetailVO> select_memberid(String memberid);
}