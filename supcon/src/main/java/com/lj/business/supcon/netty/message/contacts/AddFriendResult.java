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
 * 类说明：添加好友结果参数
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
public class AddFriendResult extends BaseResponse {

	private static final long serialVersionUID = -7392045532342137681L;
	
	/**
	 * 服务器添加好友code
	 */
	private String addCode;
	
	/**
	 * 导购编号
	 */
	private String memberNoGm;

	/**
	 * 导购微信号
	 */
	private String noWxGm;

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
	 * 昵称备注_本地
	 */
	private String nickNameRemarkLocal;
	
	/**
	 * 微信头像地址
	 */
	private String headAddress;

    /**
     * 性别:
             男：MALE
             女：FEMALE
             未知：UNKNOWN .
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
	 * 客户编号
	 */
	private String memberNo;
	
	/**
	 * 客户名称
	 */
	private String memberName;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 客户版本号
	 */
	private long version;
	
	/**
	 * 客户创建时间
	 */
	private long createTime;
	/**
     * 标签名称
     */
    private String labelName;
    /**
    * 加好友 类型(6 通讯录手机号添加好友)
    *       
    */
	private Integer type;
	/**
	 * 被动加好友时，客户请求的验证信息
	 */
	private String validation;
	
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


	/**
	 * 客户分类相关信息
	 * DZP 2018-12-14
	 */
	private String codePm;
	private String pmTypeCode;
	private String pmTypeName;
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getCodePm() {
		return codePm;
	}
	
	public void setCodePm(String codePm) {
		this.codePm = codePm;
	}
	
	public String getPmTypeCode() {
		return pmTypeCode;
	}
	
	public void setPmTypeCode(String pmTypeCode) {
		this.pmTypeCode = pmTypeCode;
	}
	
	public String getPmTypeName() {
		return pmTypeName;
	}
	
	public void setPmTypeName(String pmTypeName) {
		this.pmTypeName = pmTypeName;
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
	 * @return the nickNameRemarkLocal
	 */
	public String getNickNameRemarkLocal() {
		return nickNameRemarkLocal;
	}

	/**
	 * @param nickNameRemarkLocal the nickNameRemarkLocal to set
	 */
	public void setNickNameRemarkLocal(String nickNameRemarkLocal) {
		this.nickNameRemarkLocal = nickNameRemarkLocal;
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
	 * @return the version
	 */
	public long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(long version) {
		this.version = version;
	}

	/**
	 * @return the createTime
	 */
	public long getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "AddFriendResult [addCode=" + addCode + ", memberNoGm=" + memberNoGm + ", noWxGm=" + noWxGm + ", noWx="
				+ noWx + ", alias=" + alias + ", nickNameWx=" + nickNameWx + ", nickNameRemarkWx=" + nickNameRemarkWx
				+ ", nickNameRemarkLocal=" + nickNameRemarkLocal + ", headAddress=" + headAddress + ", sex=" + sex
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", memberNo=" + memberNo + ", memberName="
				+ memberName + ", mobile=" + mobile + ", version=" + version + ", createTime=" + createTime
				+ ", labelName=" + labelName + ", type=" + type + ", codePm=" + codePm + ", pmTypeCode=" + pmTypeCode
				+ ", pmTypeName=" + pmTypeName + "]";
	}

}
