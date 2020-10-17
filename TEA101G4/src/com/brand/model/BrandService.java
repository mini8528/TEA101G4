package com.brand.model;

import java.util.List;

public class BrandService {
	
	private BrandDAO_interface dao;
	
	public BrandService() {
//		dao = new BrandJDBCDAO();
		dao = new BrandDAO();
	}
	
	public BrandVO addBrand(String name) {
		BrandVO brandVO = new BrandVO();
		brandVO.setName(name);
		dao.insert(brandVO);
		return brandVO;
	}
	
	public BrandVO updateBrand(String brandid,String name) {
		BrandVO brandVO = new BrandVO();
		brandVO.setBrandid(brandid);
		brandVO.setName(name);
		dao.update(brandVO);
		
		return brandVO;	
	}
	
	public void deleteBrand(String brandid) {
		dao.delete(brandid);
	}
	
	public BrandVO getOneBrand(String brandid) {
		return dao.findByPrimaryKey(brandid);	
	}
	
	public List<BrandVO> getAll(){
		return dao.getAll();
		
	}
	public List<BrandVO> getBrandsByName(String name){
		return dao.getBrandsByName(name);
		
	}
	
}
