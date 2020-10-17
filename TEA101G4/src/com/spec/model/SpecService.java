package com.spec.model;

import java.util.List;

public class SpecService {
	private SpecDAO_interface dao;
	public SpecService() {
//		dao = new SpecJDBCDAO();
		dao = new SpecDAO();
	}
	public SpecVO addSpec(String productid,String specific,Integer stock) {
		SpecVO specVO = new SpecVO();
//		specVO.setSpecid(specid);
		specVO.setProductid(productid);
		specVO.setSpecific(specific);
		specVO.setStock(stock);
		dao.insert(specVO);
		return specVO;
	}
	public SpecVO updateSpec(String specid,String productid,String specific,Integer stock) {
		SpecVO specVO = new SpecVO();
		specVO.setSpecid(specid);
		specVO.setProductid(productid);
		specVO.setSpecific(specific);
		specVO.setStock(stock);
		dao.update(specVO);
		return specVO;
	}
	public void deleteSpec(String specid) {
		dao.delete(specid);
	}
	public SpecVO getOneSpec(String specid) {
		return dao.findByPrimaryKey(specid);
	}
	public List<SpecVO> getAll(){
		return dao.getAll();
	}
	public List<SpecVO> getSome(String productid){
		return dao.getSpecByProductid(productid);
	}
}
