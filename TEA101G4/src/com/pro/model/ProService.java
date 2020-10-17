package com.pro.model;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;



public class ProService {

	private Pro_interface dao;

	public ProService() {
		dao = new ProJDBCDAO();
}
	public ProVO addPro( String productid,String memberid,
	  String commtext, Integer commstar, Date adddate,
	  Date editdate,String status) {

		ProVO proVO = new ProVO();
		proVO.setProductid(productid);
		proVO.setMemberid(memberid);
		proVO.setCommtext(commtext);
		proVO.setCommstar(commstar);
		proVO.setAdddate(adddate);
		proVO.setEditdate(editdate);
		proVO.setStatus(status);
	
		dao.insert(proVO);

		return proVO;
	}

	
	
	public ProVO updatePro(String prodcommid, String productid,String memberid,
			  String commtext, Integer commstar, Date adddate,
			  Date editdate,String status) {

		ProVO proVO = new ProVO();
		proVO.setProdcommid(prodcommid);
		proVO.setProductid(productid);
		proVO.setMemberid(memberid);
		proVO.setCommtext(commtext);
		proVO.setCommstar(commstar);
		proVO.setAdddate(adddate);
		proVO.setEditdate(editdate);
		proVO.setStatus(status);
		
		dao.update(proVO);

		return proVO;
	}

	public ProVO updatestatus(String prodcommid, String status) {

		ProVO proVO = new ProVO();
		proVO.setProdcommid(prodcommid);
//		proVO.setProductid(productid);
//		proVO.setMemberid(memberid);
//		proVO.setCommtext(commtext);
//		proVO.setCommstar(commstar);
//		proVO.setAdddate(adddate);
//		proVO.setEditdate(editdate);
		proVO.setStatus(status);
		
		dao.updatetext(proVO);

		return proVO;
	}



	public void deletePro(String proVO) {
		dao.delete(proVO);
	}

	public ProVO getOnePro(String prodcommid) {
		return dao.findByPrimaryKey(prodcommid);
	}

	public List<ProVO> getAll() {
		return dao.getAll();
	}
	
	public List<ProVO> getAllStatusYById(String productid) {
		List<ProVO> all = dao.getAll();
		List<ProVO> statusYById = all.stream()
				.filter(p -> p.getStatus().equals("Y"))
				.filter(p -> p.getProductid().equals(productid))
				.collect(Collectors.toList());

		return statusYById;
	}
}