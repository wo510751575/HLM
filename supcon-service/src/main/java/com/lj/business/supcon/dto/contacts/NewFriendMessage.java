/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.dto.contacts;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 类说明：发送新好友消息
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月14日
 */
public class NewFriendMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8983422716711687291L;
	
	/**
	 * 认领code，非空
	 */
	private String code;

	/**
	 * 导购编号，非空
	 */
	private String memberNoGm;
	
	/**
	 * 导购名称，非空
	 */
	private String memberNameGm;

	/**
	 * 导购微信号，非空
	 */
	private String noWxGm;
	
	/**
	 * 客户编号，非空
	 */
	private String memberNo;
	
	/**
	 * 客户名称，非空
	 */
	private String memberName;

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
	 * 昵称备注_本地
	 */
	private String nickNameRemarkLocal;
	
	/**
	 * 微信头像地址
	 */
	private String headAddress;

    /**
     * 性别:
             男：male
             女：female
             未知：unknown .
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
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 客户版本号
	 */
	private long version;
	
	/**
	 * 客户通过时间
	 */
	private Date acceptTime;
	
	/**
	 * 认领时间
	 */
	private Date claimTime;
	/**
	 * 客户分类相关信息
	 * DZP 2018-12-14
	 */
	private String codePm;
	private String pmTypeCode;
	private String pmTypeName;
	
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
	 * @return the memberNameGm
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}

	/**
	 * @param memberNameGm the memberNameGm to set
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
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
	 * @return the acceptTime
	 */
	public Date getAcceptTime() {
		return acceptTime;
	}

	/**
	 * @param acceptTime the acceptTime to set
	 */
	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	/**
	 * @return the claimTime
	 */
	public Date getClaimTime() {
		return claimTime;
	}

	/**
	 * @param claimTime the claimTime to set
	 */
	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NewFriendMessage [code=");
		builder.append(code);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
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
		builder.append(", nickNameRemarkWx=");
		builder.append(nickNameRemarkWx);
		builder.append(", nickNameRemarkLocal=");
		builder.append(nickNameRemarkLocal);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", version=");
		builder.append(version);
		builder.append(", acceptTime=");
		builder.append(acceptTime);
		builder.append(", claimTime=");
		builder.append(claimTime);
		builder.append("]");
		return builder.toString();
	}

}
