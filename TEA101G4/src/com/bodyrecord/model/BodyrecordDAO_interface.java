package com.bodyrecord.model;

import java.util.List;

public interface BodyrecordDAO_interface {
		public void insert(BodyrecordVO bodyrecordVO);
		public void update(BodyrecordVO bodyrecordVO);
		public void delete(String bodyrecordVO);
		public BodyrecordVO findByPrimaryKey(String bodyrecordid);
		public List<BodyrecordVO> getAll();
}
