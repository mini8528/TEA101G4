package com.trainingcls.model;

import java.util.List;


public interface TrainingClsDAOIntf {
	
	public String insert(TrainingClsVO TrainingclsVO);

	public void update(TrainingClsVO TrainingclsVO);

	public void delete(String actionid);

	public TrainingClsVO findByPrimaryKey(String trainingclsid);

	public List<TrainingClsVO> getAll();

	List<TrainingClsVO> select(String memberid);

	
}
