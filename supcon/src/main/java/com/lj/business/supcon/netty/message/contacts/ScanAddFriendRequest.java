/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.contacts;

import com.lj.business.supcon.netty.message.BaseRequest;

/**
 * 
 * 类说明：导购扫码加好友请求报文参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月23日
 */
public class ScanAddFriendRequest extends BaseRequest {

	private static final long serialVersionUID = 1371206896558981124L;
	
	/**
	 * 中控客户端扫码ID
	 */
	private Long scanId;
	
	/**
	 * 服务器添加好友code
	 */
	private String addCode;
	
	/**
	 * 导购编号，非空
	 */
	private String memberNoGm;

	/**
	 * 导购微信号，非空
	 */
	private String noWxGm;
	
	/**
	 * 客户微信二维码内容，非空
	 */
	private String wxQrCode;
	
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
	 * 昵称备注
	 */
	private String nickRemark;
	
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
	
	/**
	 * 验证信息
	 */
	private String validation;
	
	/**
	 * 通过个人名片识别添加好友时需要传此参数
	 * update by 	zengchuiyu
	 * update date 	2018-08-15
	 */
	private String usernamev2;
	/**
	 * 客户名称集合，以','分隔
	 * 只在服务器端打标签-不同步到微信端
	 */
	private String labelName;

	
	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * @return the scanId
	 */
	public Long getScanId() {
		return scanId;
	}

	/**
	 * @param scanId the scanId to set
	 */
	public void setScanId(Long scanId) {
		this.scanId = scanId;
	}

	/**
	 * @return the addCode
	 */
	public String getAddCode() {
		return addCode;
	}

	/**
	 * @param addCode the addCode to set
	 */
	public void setAddCode(String addCode) {
		this.addCode = addCode;
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
	 * @return the wxQrCode
	 */
	public String getWxQrCode() {
		return wxQrCode;
	}

	/**
	 * @param wxQrCode the wxQrCode to set
	 */
	public void setWxQrCode(String wxQrCode) {
		this.wxQrCode = wxQrCode;
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
	 * @return the nickRemark
	 */
	public String getNickRemark() {
		return nickRemark;
	}

	/**
	 * @param nickRemark the nickRemark to set
	 */
	public void setNickRemark(String nickRemark) {
		this.nickRemark = nickRemark;
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

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the validation
	 */
	public String getValidation() {
		return validation;
	}

	/**
	 * @param validation the validation to set
	 */
	public void setValidation(String validation) {
		this.validation = validation;
	}

	/**
	 * @return the usernamev2
	 */
	public String getUsernamev2() {
		return usernamev2;
	}

	/**
	 * @param usernamev2 the usernamev2 to set
	 */
	public void setUsernamev2(String usernamev2) {
		this.usernamev2 = usernamev2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScanAddFriendRequest [scanId=");
		builder.append(scanId);
		builder.append(", addCode=");
		builder.append(addCode);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", wxQrCode=");
		builder.append(wxQrCode);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", alias=");
		builder.append(alias);
		builder.append(", nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", nickRemark=");
		builder.append(nickRemark);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", validation=");
		builder.append(validation);
		builder.append(", usernamev2=");
		builder.append(usernamev2);
		builder.append(", labelName=");
		builder.append(labelName);
		builder.append("]");
		return builder.toString();
	}

}
