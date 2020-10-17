package com.traininghist.model;

import java.util.List;

import com.trainingclsdetail.model.TrainingClsDetailVO;




public interface TrainingHistDAOIntf {
	public String insert(TrainingHistVO traininghistVO);

	public void update(TrainingHistVO traininghistVO);
	
	public void updatehist(TrainingHistVO traininghistVO);

	public void delete(String traininghistid);

	public TrainingHistVO findByPrimaryKey(String traininghistid);

	public List<TrainingHistVO> getAll();
	
	 public List<TrainingHistVO> SELECT_BY_MEMBERID(String trainingclsid);

	List<TrainingHistVO> select(String memberid);

}
