/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.dto.contacts;

import java.io.Serializable;

/**
 * 
 * 类说明：微信基本信息返回
 * <p>
 * 详细描述：用于OMS系统调用展示
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2018年1月11日10:45:21
 */
public class FindWxInfoReturnDto implements Serializable {
	
	private static final long serialVersionUID = 3265777518736591469L;
	/**
	 * 扫码ID
	 */
	private Long scanId;
	/**
	 * 导购编号
	 */
	private String memberNoGm;

	/**
	 * 客户微信二维码内容
	 */
	private String wxQrCode;
	
	/**
	 * 是否导购已添加该客户
	 */
	private boolean gmMbrFlag;
	
	/**
	 * 是否导购所属终端已添加该客户
	 */
	private boolean shopMbrFlag;
	
	/**
	 * 客户编号，shopMbrFlag=true时非空
	 */
	private String memberNo;
	
	/**
	 * 客户名称，shopMbrFlag=true时非空
	 */
	private String memberName;
	
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
	 * 微信头像地址
	 */
	private String headAddress;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * add_friends表主键
	 */
	private String addCode;
	
	/**
	 * 通过个人名片识别添加好友时需要传此参数
	 * update by 	zengchuiyu
	 * update date 	2018-08-15
	 */
	private String usernamev2;
	
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
	 * @return the gmMbrFlag
	 */
	public boolean isGmMbrFlag() {
		return gmMbrFlag;
	}

	/**
	 * @param gmMbrFlag the gmMbrFlag to set
	 */
	public void setGmMbrFlag(boolean gmMbrFlag) {
		this.gmMbrFlag = gmMbrFlag;
	}

	/**
	 * @return the shopMbrFlag
	 */
	public boolean isShopMbrFlag() {
		return shopMbrFlag;
	}

	/**
	 * @param shopMbrFlag the shopMbrFlag to set
	 */
	public void setShopMbrFlag(boolean shopMbrFlag) {
		this.shopMbrFlag = shopMbrFlag;
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
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
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
	
	public String getAddCode() {
		return addCode;
	}

	public void setAddCode(String addCode) {
		this.addCode = addCode;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWxInfoReturnDto [scanId=");
		builder.append(scanId);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", wxQrCode=");
		builder.append(wxQrCode);
		builder.append(", gmMbrFlag=");
		builder.append(gmMbrFlag);
		builder.append(", shopMbrFlag=");
		builder.append(shopMbrFlag);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", alias=");
		builder.append(alias);
		builder.append(", nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", addCode=");
		builder.append(addCode);
		builder.append(", usernamev2=");
		builder.append(usernamev2);
		builder.append("]");
		return builder.toString();
	}

}
