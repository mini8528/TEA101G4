package com.cus.model;

import java.sql.Blob;
import java.sql.Date;

public class CusVO {

	 private String customerserviceid;
	 private String memberid;	
	 private String adminid;	
	 private String subject;	
	 private String email;	
	 private String problemtext;
	 private Date complaintdate;
	 private String replytext;
	 private Date replydate;
	 

	 public CusVO() {
		}
	 
	 
	 public CusVO( String customerserviceid,String memberid,String adminid,	String subject, String email, String problemtext, Date complaintdate,  String replytext, Date replydate) {
		 	this.customerserviceid = customerserviceid;
			this.memberid = memberid;
			this.adminid = adminid;
			this.subject = subject;
			this.email = email;
			this.problemtext = problemtext;
			this.complaintdate = complaintdate;
			this.replytext = replytext;
			this.replydate = replydate;
	 
	 
	 }
	 
	 
	 
	 
	 
	 
	 
	public String getCustomerserviceid() {
		return customerserviceid;
	}
	public void setCustomerserviceid(String customerserviceid) {
		this.customerserviceid = customerserviceid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProblemtext() {
		return problemtext;
	}
	public void setProblemtext(String problemtext) {
		this.problemtext = problemtext;
	}
	public Date getComplaintdate() {
		return complaintdate;
	}
	public void setComplaintdate(Date complaintdate) {
		this.complaintdate = complaintdate;
	}
	public String getReplytext() {
		return replytext;
	}
	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}
	public Date getReplydate() {
		return replydate;
	}
	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}
	
	
	
	
	
	
	
	
	
}
