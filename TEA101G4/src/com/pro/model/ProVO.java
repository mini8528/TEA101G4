package com.pro.model;

import java.sql.Date;

public class ProVO {
	 private String prodcommid;
	 private String productid;	
	 private String memberid;	
	 private String commtext;	
	 private Integer commstar;	
	 private Date adddate;
	 private Date editdate;
	 private String status;
	 
	 public ProVO() {
		}
	 
	 
	 public ProVO( String prodcommid,String productid,String memberid,String commtext,Integer commstar,Date adddate,Date editdate,String status) {
		 	this.prodcommid = prodcommid;
			this.productid = productid;
			this.memberid = memberid;
			this.commtext = commtext;
			this.commstar = commstar;
			this.adddate = adddate;
			this.editdate = editdate;
			this.status = status;

	 }
	 
	public String getProdcommid() {
		return prodcommid;
	}
	public void setProdcommid(String prodcommid) {
		this.prodcommid = prodcommid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getCommtext() {
		return commtext;
	}
	public void setCommtext(String commtext) {
		this.commtext = commtext;
	}
	public Integer getCommstar() {
		return commstar;
	}
	public void setCommstar(Integer commstar) {
		this.commstar = commstar;
	}
	public Date getAdddate() {
		return adddate;
	}
	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}
	public Date getEditdate() {
		return editdate;
	}
	public void setEditdate(Date editdate) {
		this.editdate = editdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 

}
