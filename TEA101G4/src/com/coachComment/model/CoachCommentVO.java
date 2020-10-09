package com.coachComment.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CoachCommentVO implements Serializable{
	private String coachCommentID;
	private String memberID;
	private String memberID2;
	private String commText;
	private Integer commStar;
	private Timestamp addDate;
	private Timestamp editDate;
	private String status;
	
	public String getCoachCommentID() {
		return coachCommentID;
	}
	public void setCoachCommentID(String coachCommentID) {
		this.coachCommentID = coachCommentID;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMemberID2() {
		return memberID2;
	}
	public void setMemberID2(String memberID2) {
		this.memberID2 = memberID2;
	}
	public String getCommText() {
		return commText;
	}
	public void setCommText(String commText) {
		this.commText = commText;
	}
	public Integer getCommStar() {
		return commStar;
	}
	public void setCommStar(Integer commStar) {
		this.commStar = commStar;
	}
	public Timestamp getAddDate() {
		return addDate;
	}
	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}
	public Timestamp getEditDate() {
		return editDate;
	}
	public void setEditDate(Timestamp editDate) {
		this.editDate = editDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
