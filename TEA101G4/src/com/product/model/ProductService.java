package com.product.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.spec.model.SpecVO;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
//		dao = new ProductJDBCDAO();
		dao = new ProductDAO();
	}
//	------------
//	------------
	public ProductVO auto_addProductAndSpec(String adminid, String brandid, String name,
			String category, Integer price, Timestamp adddate,String status, byte[] photo1, byte[] photo2, byte[] photo3,
			String intro,String specific ,Integer stock) {
		ProductVO productVO = new ProductVO();
		productVO.setAdminid(adminid);
		productVO.setBrandid(brandid);
		productVO.setName(name);
		productVO.setCategory(category);
		productVO.setPrice(price);
		productVO.setAdddate(adddate);
		productVO.setStatus(status);
		productVO.setPhoto1(photo1);
		productVO.setPhoto2(photo2);
		productVO.setPhoto3(photo3);
		productVO.setIntro(intro);
		
		List<SpecVO> testList = new ArrayList<SpecVO>();
		SpecVO specVO = new SpecVO();
		specVO.setSpecific(specific);
		specVO.setStock(stock);
		testList.add(specVO);
		dao.insertWithProduct(productVO, testList);
		return productVO;
	}
//	------------
	public ProductVO addProduct(String adminid, String brandid, String name,
			String category, Integer price, Timestamp adddate,String status, byte[] photo1, byte[] photo2, byte[] photo3,
			String intro) {
		ProductVO productVO = new ProductVO();
		productVO.setAdminid(adminid);
		productVO.setBrandid(brandid);
		productVO.setName(name);
		productVO.setCategory(category);
		productVO.setPrice(price);
		productVO.setAdddate(adddate);
		productVO.setStatus(status);
		productVO.setPhoto1(photo1);
		productVO.setPhoto2(photo2);
		productVO.setPhoto3(photo3);
		productVO.setIntro(intro);
		dao.insert(productVO);
		return productVO;
	}

	public ProductVO updateProduct(String productid, String adminid,String adminid2, String brandid, String name,
			String category, Integer price, Timestamp adddate,String status,Timestamp editdate, byte[] photo1, byte[] photo2, byte[] photo3,
			String intro) {
		ProductVO productVO = new ProductVO();
		productVO.setProductid(productid);
		productVO.setAdminid(adminid);
		productVO.setAdminid2(adminid2);
		productVO.setBrandid(brandid);
		productVO.setName(name);
		productVO.setCategory(category);
		productVO.setPrice(price);
		productVO.setAdddate(adddate);
		productVO.setStatus(status);
		productVO.setEditdate(editdate);
		productVO.setPhoto1(photo1);
		productVO.setPhoto2(photo2);
		productVO.setPhoto3(photo3);
		productVO.setIntro(intro);
		dao.update(productVO);
		return productVO;
	}
	
	public ProductVO updateProducts_status(String productid,String status,Timestamp editdate) {
		ProductVO productVO = new ProductVO();
		productVO.setProductid(productid);
		productVO.setStatus(status);
		productVO.setEditdate(editdate);
		dao.update_status(productVO);
		return productVO;
	}
	public ProductVO updateProducts_status2(List<String>productidAll ,String status ,Timestamp editdate) {
		ProductVO productVO = new ProductVO();
		List<ProductVO> list = new ArrayList<ProductVO>();
		for(String aa:productidAll) {
			productVO.setProductid(aa);
			productVO.setStatus(status);
			productVO.setEditdate(editdate);
			list.add(productVO);
			dao.update_status2(list);
		}
		
		return productVO;
	}

	public void deleteProduct(String productid) {
		dao.delete(productid);
	}
	public ProductVO getOneProduct(String productid) {
		return dao.findbyPrimaryKey(productid);
	}
	public List<ProductVO> getAll(){
		return dao.getAll();
	}
	public List<ProductVO> getAll_byStatus(String status){
		return dao.getAll_byStatus(status);
	}
	public List<ProductVO> getProducts(String name){
		return dao.getProductsByName(name);
	}
	public List<ProductVO> getProductsByCategory(String category,String status){
		return dao.getProductsByCategory(category, status);
	}

}
