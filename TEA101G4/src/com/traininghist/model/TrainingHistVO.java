package com.traininghist.model;

import sun.misc.JavaAWTAccess;

public class TrainingHistVO implements java.io.Serializable {
	private String traininghistid;
	private String memberid;
	private String trainingscheid;
	private String actionid;
	private Integer trainingtime;
	private Integer trainingset;
	private Integer trainingrep;
	private Integer trainingwt;
	public String getTraininghistid() {
		return traininghistid;
	}
	public void setTraininghistid(String traininghistid) {
		this.traininghistid = traininghistid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getTrainingscheid() {
		return trainingscheid;
	}
	public void setTrainingscheid(String trainingscheid) {
		this.trainingscheid = trainingscheid;
	}
	public String getActionid() {
		return actionid;
	}
	public void setActionid(String actionid) {
		this.actionid = actionid;
	}
	public Integer getTrainingtime() {
		return trainingtime;
	}
	public void setTrainingtime(Integer trainingtime) {
		this.trainingtime = trainingtime;
	}
	public Integer getTrainingset() {
		return trainingset;
	}
	@Override
	public String toString() {
		return "TrainingHistVO [traininghistid=" + traininghistid + ", memberid=" + memberid + ", trainingscheid="
				+ trainingscheid + ", actionid=" + actionid + ", trainingtime=" + trainingtime + ", trainingset="
				+ trainingset + ", trainingrep=" + trainingrep + ", trainingwt=" + trainingwt + "]";
	}
	public void setTrainingset(Integer trainingset) {
		this.trainingset = trainingset;
	}
	public Integer getTrainingrep() {
		return trainingrep;
	}
	public void setTrainingrep(Integer trainingrep) {
		this.trainingrep = trainingrep;
	}
	public Integer getTrainingwt() {
		return trainingwt;
	}
	public void setTrainingwt(Integer trainingwt) {
		this.trainingwt = trainingwt;
	}

	}

	



