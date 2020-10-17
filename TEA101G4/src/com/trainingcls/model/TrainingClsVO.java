package com.trainingcls.model;

import java.sql.Date;

public class TrainingClsVO {
	@Override
	public String toString() {
		return "TrainingClsVO [trainingclsid=" + trainingclsid + ", memberid=" + memberid + ", trainingclsnm="
				+ trainingclsnm + ", posttime=" + posttime + ", updatetime=" + updatetime + "]";
	}
	private String trainingclsid;
	private String memberid;
	private String trainingclsnm;
	private Date posttime;
	private Date updatetime;
	public String getTrainingclsid() {
		return trainingclsid;
	}
	public void setTrainingclsid(String trainingclsid) {
		this.trainingclsid = trainingclsid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getTrainingclsnm() {
		return trainingclsnm;
	}
	public void setTrainingclsnm(String trainingclsnm) {
		this.trainingclsnm = trainingclsnm;
	}

	public Date getPosttime() {
		return posttime;
	}
	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	
}

