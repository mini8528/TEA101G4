package com.traininghist.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.trainingclsdetail.model.TrainingClsDetailDAOIntf;
import com.trainingclsdetail.model.TrainingClsDetailJDBCDAO;
import com.trainingclsdetail.model.TrainingClsDetailVO;

public class TrainingHistService {

	private TrainingHistDAOIntf JDBCdao;

	public TrainingHistService() {
		JDBCdao = new TrainingHistDAO();
	}

	public TrainingHistVO addTrainingHist(String memberid, String trainingScheid, String actionid, Integer trainingtime,
			Integer trainingset, Integer trainingrep, Integer trainingwt) {

		TrainingHistVO thVO = new TrainingHistVO();

		thVO.setMemberid(memberid);
		thVO.setTrainingscheid(trainingScheid);
		thVO.setActionid(actionid);
		thVO.setTrainingtime(trainingtime);
		thVO.setTrainingset(trainingset);
		thVO.setTrainingrep(trainingrep);
		thVO.setTrainingwt(trainingwt);
		thVO.setTraininghistid(JDBCdao.insert(thVO));

		return thVO;
	}

	public TrainingHistVO updateTrainingHist(String memberid, String trainingScheid, String actionid,
			Integer trainingtime, Integer trainingset, Integer trainingrep, Integer trainingwt) {

		TrainingHistVO thVO = new TrainingHistVO();

		thVO.setTrainingset(trainingset);
		thVO.setTrainingrep(trainingrep);
		thVO.setTrainingwt(trainingwt);
		JDBCdao.update(thVO);

		return thVO;
	}

	public TrainingHistVO updatehistTrainingHist(Integer trainingset, Integer trainingrep, Integer trainingwt, String traininghistid) {

		TrainingHistVO thVO = new TrainingHistVO();

		thVO.setTrainingset(trainingset);
		thVO.setTrainingrep(trainingrep);
		thVO.setTrainingwt(trainingwt);
		thVO.setTraininghistid(traininghistid);
		JDBCdao.updatehist(thVO);

		return thVO;
	}

	public void deleteTrainingHist(String traininghistid) {
		JDBCdao.delete(traininghistid);
	}

	public TrainingHistVO getOneTrainingHist(String traininghistid) {
		return JDBCdao.findByPrimaryKey(traininghistid);
	}

	public List<TrainingHistVO> getAll() {
		return JDBCdao.getAll();
	}

	public List<TrainingHistVO> select(String memberid) {
		return JDBCdao.select(memberid);
	}

	public static void main(String[] args) {
		TrainingHistService svc = new TrainingHistService();
		System.out.println(svc.getAll());
	}
}
