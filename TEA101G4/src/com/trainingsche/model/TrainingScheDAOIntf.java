

package com.trainingsche.model;

import java.util.List;

import com.traininghist.model.TrainingHistVO;


public interface TrainingScheDAOIntf {
	
	public String insert(TrainingScheVO trainingscheVO);

	public void update(TrainingScheVO trainingscheVO);
	
	public void updateendtime(TrainingScheVO trainingscheVO);

	public void delete(String trainingscheid);

	public TrainingScheVO findByPrimaryKey(String trainingscheid);

	public List<TrainingScheVO> getAll();

	void insertWithHist(TrainingScheVO trainingScheVO, List<TrainingHistVO> list);

	public List<TrainingScheVO> select(String memberid);

}
