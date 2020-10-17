package com.action.model;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.util.Arrays;

public class ActionVO implements java.io.Serializable{
	private String actionid;
	private String actionnm;
	private String part;
	private byte[] video;
	private Date posttime;
	private Date updatetime;
	private String videoBase64Url;
	
	public String getActionid() {
		return actionid;
	}
	public void setActionid(String actionid) {
		this.actionid = actionid;
	}
	public String getActionnm() {
		return actionnm;
	}
	public void setActionnm(String actionnm) {
		this.actionnm = actionnm;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public byte[] getVideo() {
		return video;
	}
	public void setVideo(byte[] video) {
		this.video = video;
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
	
	
	public String getVideoBase64Url() {
		return videoBase64Url;
	}
	public void setVideoBase64Url(String videoBase64Url) {
		this.videoBase64Url = videoBase64Url;
	}
	@Override
	public String toString() {
		return "ActionVO [actionid=" + actionid + ", actionnm=" + actionnm + ", part=" + part +", posttime=" + posttime + ", updatetime=" + updatetime + ", videoBase64Url="
				+ videoBase64Url + "]";
	}
	
	
	
}

