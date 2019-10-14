/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.addfriends;

import java.io.Serializable;

/**
 * 
 * 
 * 类说明：同步微信客户参数
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2017年11月22日
 */
public class SyncFriendsDetail implements Serializable {

	private static final long serialVersionUID = 6366901743968475121L;

	/**
	 * 客户微信，非空
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
	 * 微信昵称备注
	 */
	private String nickNameRemarkWx;

	/**
	 * 微信头像地址
	 */
	private String headAddress;

	/**
	 * 性别: 男：MALE 女：FEMALE 未知：UNKNOWN .
	 */
	private String sex;
	/**
	 * 标签名称
	 */
	private String labelName;
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

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
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
	 * @return the nickNameRemarkWx
	 */
	public String getNickNameRemarkWx() {
		return nickNameRemarkWx;
	}

	/**
	 * @param nickNameRemarkWx the nickNameRemarkWx to set
	 */
	public void setNickNameRemarkWx(String nickNameRemarkWx) {
		this.nickNameRemarkWx = nickNameRemarkWx;
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
	 * 性别: 男：male 女：female 未知：unknown .
	 *
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 性别: 男：male 女：female 未知：unknown .
	 *
	 */
	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	@Override
	public String toString() {
		return "SyncFriendsDetail [noWx=" + noWx + ", alias=" + alias + ", nickNameWx=" + nickNameWx
				+ ", nickNameRemarkWx=" + nickNameRemarkWx + ", headAddress=" + headAddress + ", sex=" + sex
				+ ", labelName=" + labelName + ", mobile=" + mobile + "]";
	}

}
