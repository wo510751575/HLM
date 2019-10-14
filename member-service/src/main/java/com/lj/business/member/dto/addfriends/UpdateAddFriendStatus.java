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
 * 类说明：添加好友结果参数
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2017年10月23日
 */
public class UpdateAddFriendStatus implements Serializable {

	private static final long serialVersionUID = 6474673924795609824L;

	/**
	 * 添加好友code,不为空时表示为导购主动添加好友(扫友加、搜索加)
	 */
	private String code;

	/**
	 * 商户编号,非空
	 */
	private String merchantNo;

	/**
	 * 导购编号
	 */
	private String memberNoGm;

	/**
	 * 导购微信号，非空
	 */
	private String noWxGm;

	/**
	 * 通过状态, true通过、false拒绝
	 */
	private boolean status = Boolean.TRUE;

	/**
	 * 客户微信
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
	 * 性别: 男：male 女：female 未知：unknown .
	 */
	private String sex;

	/**
	 * 纬度
	 */
	private String longitude;

	/**
	 * 经度
	 */
	private String latitude;
	/**
	 * 标签名称
	 */
	private String labelName;
	/**
	 * 被动加好友时，客户请求的验证信息
	 */
	private String validation;
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

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
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

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "UpdateAddFriendStatus [code=" + code + ", merchantNo=" + merchantNo + ", memberNoGm=" + memberNoGm
				+ ", noWxGm=" + noWxGm + ", status=" + status + ", noWx=" + noWx + ", alias=" + alias + ", nickNameWx="
				+ nickNameWx + ", nickNameRemarkWx=" + nickNameRemarkWx + ", headAddress=" + headAddress + ", sex="
				+ sex + ", longitude=" + longitude + ", latitude=" + latitude + ", labelName=" + labelName
				+ ", validation=" + validation + ", mobile=" + mobile + "]";
	}

}
