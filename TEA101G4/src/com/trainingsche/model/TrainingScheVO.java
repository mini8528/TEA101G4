package com.trainingsche.model;

import java.sql.Date;
import java.sql.Timestamp;



public class TrainingScheVO {
	private String trainingscheid;
	private String trainingclsid;
	private String memberid;
	private Timestamp starttime;
	private Timestamp endtime;
	public String getTrainingscheid() {
		return trainingscheid;
	}
	public void setTrainingscheid(String trainingscheid) {
		this.trainingscheid = trainingscheid;
	}
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
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	
}
