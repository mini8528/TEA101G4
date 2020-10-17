package com.trainingsche.model;

import java.sql.Timestamp;
import java.util.List;

import com.trainingcls.model.TrainingClsVO;
import com.trainingclsdetail.model.TrainingClsDetailVO;



public class TrainingScheService {
	
    private TrainingScheDAOIntf JDBCdao;
	
	public TrainingScheService() {
		JDBCdao = new TrainingScheJDBCDAO();
	}
	
	public TrainingScheVO addTrainingSche(
			String trainingclsid, String memberid, Timestamp starttime , Timestamp endtime ) {
		 
		TrainingScheVO tsVO = new TrainingScheVO();


		tsVO.setTrainingclsid(trainingclsid);
		tsVO.setMemberid(memberid);
		tsVO.setStarttime(starttime);
		tsVO.setEndtime(endtime);
	    tsVO.setTrainingscheid(JDBCdao.insert(tsVO));
//		JDBCdao.insert(tsVO);
		
	    return tsVO;
	 }
//更新 運動結束時間	
	public TrainingScheVO updateenddtimeTrainingSche(
			Timestamp endtime, String trainingscheid) {
		 
		TrainingScheVO tsVO = new TrainingScheVO();
	 
		tsVO.setEndtime(endtime);
		tsVO.setTrainingscheid(trainingscheid);
	     JDBCdao.updateendtime(tsVO);
	    	
	    	return  tsVO;
	 }
	
	public TrainingScheVO updateTrainingSche(
			String trainingsche, String trainingclsid, String memberid, Timestamp starttime , Timestamp endtime ) {
		 
		TrainingScheVO tsVO = new TrainingScheVO();
	 
		tsVO.setTrainingscheid(trainingsche);
		tsVO.setTrainingclsid(trainingclsid);
		tsVO.setMemberid(memberid);
		tsVO.setStarttime(starttime);
		tsVO.setEndtime(endtime);
	     JDBCdao.update(tsVO);
	    	
	    	return  tsVO;
	 }
	
	 public void deleteTrainingSche(String trainingclsdetailid) {
	    	JDBCdao.delete(trainingclsdetailid);
		}

		public TrainingScheVO getOneTrainingSche(String trainingscheid) {
			return JDBCdao.findByPrimaryKey(trainingscheid);
		}

		public List<TrainingScheVO> getAll() {
			return JDBCdao.getAll();
		}

		public List<TrainingScheVO> SELECT_BY_MEMBERID(String memberid){
			return JDBCdao.select(memberid);
		}	
	 

}
