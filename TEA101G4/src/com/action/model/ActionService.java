package com.action.model;

import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trainingsche.model.TrainingScheJDBCDAO;
import com.trainingsche.model.TrainingScheVO;

public class ActionService {
	private static Map<String, ActionVO> map;
	private ActionDaoIntf JDBCdao;

	public ActionService() {
		JDBCdao = new ActionDAO();
	}

	public ActionVO addAction(String actionnm, String part, byte[] viedo, java.sql.Date posttime,
			java.sql.Date updatetime) {

		ActionVO actVO = new ActionVO();

		actVO.setActionnm(actionnm);
		actVO.setPart(part);
		actVO.setVideo(viedo);
		actVO.setPosttime(posttime);
		actVO.setUpdatetime(updatetime);
		JDBCdao.insert(actVO);

		return actVO;
	}

	public ActionVO updateAction(String actionid, String actionnm, String part, byte[] video, java.sql.Date posttime,
			java.sql.Date updatetime) {

		ActionVO actVO = new ActionVO();

		actVO.setActionid(actionid);
		actVO.setActionnm(actionnm);
		actVO.setPart(part);
		actVO.setVideo(video);
		actVO.setPosttime(posttime);
		actVO.setUpdatetime(updatetime);
		JDBCdao.update(actVO);

		return actVO;
	}

	public void deleteAction(String actionid) {
		JDBCdao.delete(actionid);
	}

	public ActionVO getOneAction(String actionid) {
		return JDBCdao.findByPrimaryKey(actionid);
	}

	public List<ActionVO> getAll() {
		return JDBCdao.getAll();
	}

	public ActionVO addAction(String actionnm, String part) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, ActionVO> getActionMap() {
		if (map == null) {
			map = new HashMap<String, ActionVO>();
			List<ActionVO> list = getAll();

			for (ActionVO actionVO : list) {
				map.put(actionVO.getActionid(), actionVO);
			}
		}

		return map;
	}
	
	public List<String> getActionParts(){
		
		List<String> list = new ArrayList<String>();
		
		list.add("手臂");
		list.add("背部");
		list.add("胸部");
		list.add("腿部");
		list.add("腹部");
		list.add("臀部");
		
		return list;
	}
	
}
