package com.lj.business.member.dto;

import java.io.Serializable;

public class FindGmDistantPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 499450960543158184L;
	
	/**
	 * 导购编号
	 */
	private String memberNoGm;

	/**
	 * 导购姓名
	 */
	private String memberNoGmName;

	/**
	 * 终端编号
	 */
	
	
	/**
	 * 终端名称
	 */
	
	
	/**
	 * 图片
	 */
	private String imgUrl;
	
	/**
	 * 店长电话
	 */
	private String mobile;
	
	/**
	 * 店长微信
	 */
	private String noWx;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 店长二维码
	 */
	private String qcord;

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMemberNoGmName() {
		return memberNoGmName;
	}

	public void setMemberNoGmName(String memberNoGmName) {
		this.memberNoGmName = memberNoGmName;
	}


	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQcord() {
		return qcord;
	}

	public void setQcord(String qcord) {
		this.qcord = qcord;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindGmDistantPageReturn [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNoGmName=");
		builder.append(memberNoGmName);
		builder.append(", imgUrl=");
		builder.append(imgUrl);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", address=");
		builder.append(address);
		builder.append(", qcord=");
		builder.append(qcord);
		builder.append("]");
		return builder.toString();
	}

}
