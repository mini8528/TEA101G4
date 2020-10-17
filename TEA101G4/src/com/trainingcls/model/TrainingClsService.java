package com.trainingcls.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.fabric.xmlrpc.base.Value;



public class TrainingClsService {

	private TrainingClsDAOIntf JDBCdao;

	public TrainingClsService() {
		JDBCdao = new TrainingClsDAO();
	}

	public TrainingClsVO addTrainingCls(String memberid, String trainingclsnm, Date posttime, Date updatetime) {
		TrainingClsVO tcVO = new TrainingClsVO();

		tcVO.setMemberid(memberid);
		tcVO.setTrainingclsnm(trainingclsnm);
		tcVO.setPosttime(posttime);
		tcVO.setUpdatetime(updatetime);
		String id = JDBCdao.insert(tcVO);
		tcVO.setTrainingclsid(id);
		return tcVO;

	}

	public TrainingClsVO updateTrainingCls(String trainingclsid, String memberid, String trainingclsnm,
			java.sql.Date posttime, java.sql.Date updatetime) {

		TrainingClsVO tcVO = new TrainingClsVO();

		tcVO.setTrainingclsid(trainingclsid);
		tcVO.setMemberid(memberid);
		tcVO.setTrainingclsnm(trainingclsnm);
		tcVO.setPosttime(posttime);
		tcVO.setUpdatetime(updatetime);
		JDBCdao.update(tcVO);

		return tcVO;
	}

	public void deleteTrainingCls(String trainingclsid) {
		JDBCdao.delete(trainingclsid);
	}

	public TrainingClsVO getOneTrainingCls(String trainingclsid) {
		return JDBCdao.findByPrimaryKey(trainingclsid);
	}

	public List<TrainingClsVO> getAll() {
		return JDBCdao.getAll();
	}
	
	public List<TrainingClsVO> getSomeByMemberid(String memberid){
		return JDBCdao.select(memberid);
	}	

	public static void main(String[] args) {

		TrainingClsService service = new TrainingClsService();

	}

}
