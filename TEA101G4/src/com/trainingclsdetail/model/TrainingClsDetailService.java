package com.trainingclsdetail.model;

import java.sql.Date;
import java.util.List;

import com.trainingcls.model.TrainingClsVO;



public class TrainingClsDetailService {
	
	private TrainingClsDetailDAOIntf JDBCdao;
	
	public TrainingClsDetailService() {
		JDBCdao = new TrainingClsDetailDAO();
	}
	
	public TrainingClsDetailVO addTrainingClsDetail(
			String trainingclsid, String actionid) {
		 
		 TrainingClsDetailVO tcdVO = new TrainingClsDetailVO();
		 
//		 tcdVO.setTrainingclsdetailid(trainingclsdetailid);
		 tcdVO.setTrainingclsid(trainingclsid);
		 tcdVO.setActionid(actionid);
		 
		 // FIXME
	     JDBCdao.insert(tcdVO);
	    	
	    	return  tcdVO;
	 }
	 
	 public TrainingClsDetailVO updateTrainingClsDetail(
			 String trainingclsdetailid,String trainingclsid, String actionid) {
			 
			 TrainingClsDetailVO tcdVO = new TrainingClsDetailVO();
			 
			 tcdVO.setTrainingclsdetailid(trainingclsdetailid);
			 tcdVO.setTrainingclsid(trainingclsid);
			 tcdVO.setActionid(actionid);
		     JDBCdao.update(tcdVO);
		    	
		    	return  tcdVO;
		 }
	 
	 public void deleteTrainingClsDetail(String trainingclsdetailid) {
	    	JDBCdao.delete(trainingclsdetailid);
		}

		public TrainingClsDetailVO getOneTrainingClsDetail(String trainingclsdetailid) {
			return JDBCdao.findByPrimaryKey(trainingclsdetailid);
		}

		public List<TrainingClsDetailVO> getAll() {
			return JDBCdao.getAll();
		}
	 
		public List<TrainingClsDetailVO> select(String trainingclsid) {
			return JDBCdao.select(trainingclsid);
		}
			
		public List<TrainingClsDetailVO> select_memberid(String memberid) {
			return JDBCdao.select(memberid);
		}

}
