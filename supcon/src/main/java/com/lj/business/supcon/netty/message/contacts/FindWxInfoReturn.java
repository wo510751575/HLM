/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.contacts;

import com.lj.business.supcon.netty.message.BaseResponse;

/**
 * 
 * 类说明：微信基本信息返回
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
public class FindWxInfoReturn extends BaseResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3265777518736591469L;
	
	/**
	 * 中控客户端扫码ID
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
	 * 是否导购所属门店已添加该客户
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
	 * 1、此字段对应微信app数据库存里的username字段
	 * 2、如果客户是中控微信的好友，该字段将客户微信真实的username
	 * 3、如果客户不是中控微信的好友（不包括客户将中控微信从好友列表中删除，即客户单向删除），该字段将返回以@stranger结尾的username，如：v1_90dc6fe8176a7753211f776f14b87363b92f018680f14d4e91cae823428c12a2@stranger
	 * 4、该字段为空时，表示没有查到客户微信基本信息、获取失败、或者客户设置了隐私
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
	 * 标记微信是否已经存在
	 */
	private Boolean wxIsExistFlag;
	



	/**
	 * 通过个人名片识别添加好友时需要传此参数
	 * update by 	zengchuiyu
	 * update date 	2018-08-15
	 */
	private String usernamev2;
	


	public Boolean getWxIsExistFlag() {
		return wxIsExistFlag;
	}

	public void setWxIsExistFlag(Boolean wxIsExistFlag) {
		this.wxIsExistFlag = wxIsExistFlag;
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
		builder.append("FindWxInfoReturn [");
		builder.append(super.toString());
		builder.append(", scanId=");
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
