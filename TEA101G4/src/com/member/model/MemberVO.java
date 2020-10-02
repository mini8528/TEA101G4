package com.member.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class MemberVO implements java.io.Serializable{
	private String memberid;
	private String name;
	private String account;
	private String password;
	private String gender;
	private String phone;
	private Date birthday;
	private String email;
	private byte[] photo;
	private String address;
	private String authority;
	private String qualifications;
	private String expertise;
	private String introduction;
	private byte[] photo1;
	private byte[] photo2;
	private byte[] photo3;
	private Date adddate;
	
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getQualifications() {
		return qualifications;
	}
	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public byte[] getPhoto1() {
		return photo1;
	}
	public void setPhoto1(byte[] photo1) {
		this.photo1 = photo1;
	}
	public byte[] getPhoto2() {
		return photo2;
	}
	public void setPhoto2(byte[] photo2) {
		this.photo2 = photo2;
	}
	public byte[] getPhoto3() {
		return photo3;
	}
	public void setPhoto3(byte[] photo3) {
		this.photo3 = photo3;
	}
	public Date getAdddate() {
		return adddate;
	}
	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}
	@Override
	public String toString() {
		return "MemberVO [memberid=" + memberid + ",name=" + name +	",account="+account+",password="+password+ ",gender="+gender+ ",phone="+phone +",birthday="+birthday +
				",email="+email +",photo="+Arrays.toString(photo)+ ",address="+address+ ",authority="+authority+ ",qualifications="+qualifications+",expertise="+ expertise+
				",introduction="+introduction+",photo1="+Arrays.toString(photo1)+ ",photo2="+Arrays.toString(photo2)+ ",photo3="+Arrays.toString(photo3)+ ",adddate="+adddate
				+ "]";
	}
	
}
