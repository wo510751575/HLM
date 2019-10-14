/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.im;

import java.io.Serializable;

/**
 * 
 * 类说明：同步客户微信基本信息请求参数
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2018年1月8日
 */
public class SyncPersonWxInfoRequest implements Serializable {

	private static final long serialVersionUID = 4537636028097012719L;

	/**
	 * 导购编号，导购编号与导购微信号不能同时为空
	 */
	private String memberNoGm;

	/**
	 * 导购微信号（终端微信），导购编号与导购微信号不能同时为空
	 */
	private String noWxGm;

	/**
	 * 客户编号，客户编号与客户微信不能同时为空
	 */
	private String memberNo;

	/**
	 * 客户微信，客户编号与客户微信不能同时为空
	 */
	private String noWx;

	/**
	 * 客户微信别名
	 */
	private String alias;

	/**
	 * 微信昵称
	 */
	private String nickNameWx;

	/**
	 * 微信头像地址
	 */
	private String headAddress;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 手机号
	 */
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * @param memberNoGm the memberNoGm to set
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * @return the noWxGm
	 */
	public String getNoWxGm() {
		return noWxGm;
	}

	/**
	 * @param noWxGm the noWxGm to set
	 */
	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	/**
	 * @return the memberNo
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return the noWx
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * @param noWx the noWx to set
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the nickNameWx
	 */
	public String getNickNameWx() {
		return nickNameWx;
	}

	/**
	 * @param nickNameWx the nickNameWx to set
	 */
	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
	}

	/**
	 * @return the headAddress
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * @param headAddress the headAddress to set
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "SyncPersonWxInfoRequest [memberNoGm=" + memberNoGm + ", noWxGm=" + noWxGm + ", memberNo=" + memberNo
				+ ", noWx=" + noWx + ", alias=" + alias + ", nickNameWx=" + nickNameWx + ", headAddress=" + headAddress
				+ ", sex=" + sex + ", mobile=" + mobile + "]";
	}

}
