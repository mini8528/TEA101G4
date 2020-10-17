package com.action.model;

import java.util.*;


public interface ActionDaoIntf {
	public void insert(ActionVO actionVO);

	public void update(ActionVO actionVO);

	public void delete(String actionid);

	public ActionVO findByPrimaryKey(String actionid);

	public List<ActionVO> getAll();
}
