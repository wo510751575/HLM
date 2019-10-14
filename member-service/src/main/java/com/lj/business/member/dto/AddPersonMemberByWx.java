/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 类说明：通过客户微信添加客户信息
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月25日
 */
public class AddPersonMemberByWx implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2558630466687480848L;

	/**
	 * 导购编号
	 */
	private String memberNoGuid;
	
	/**
	 * 导购微信号
	 */
	private String noWxGm;
	
	/**
	 * 客户微信号
	 */
	private String noWx;
	
	/**
	 * 客户微信别名
	 */
	private String alias;
	
	/**
	 * 客户名称
	 */
	private String memberName;
	
	/**
	 * 客户昵称
	 */
	private String nickNameWx;
	
	/**
	 * 客户微信头像地址
	 */
	private String headAddress;
	
	/**
	 * 客户昵称备注
	 */
	private String nickNameRemarkWx;

    /**
     * 性别:
             男：MALE
             女：FEMALE
             未知：UNKNOWN .
     */
    private String sex;
    
    /**
     * 微信OPENID
     */
    private String wxOpenId;
    
    /**
     * 手机号
     */
    private String mobile;
    
    /**
     * QQ号
     */
    private String noQQ;
    
    /**
     * 标签名称
     */
    private String labelName;
    
    /**
     * 客户来源
     */
    private String memberSrc;
	
	/**
	 * 纬度
	 */
	private String longitude;
	
	/**
	 * 经度
	 */
	private String latitude;
	
	/**
	 * 扫码地址
	 */
	private String scanAddress;
    
    /**
     * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子、5主动搜索添加，如手机搜索
     */
    private Integer addType;
	
	/**
	 * 关注时间
	 */
	private Date subsribeTime;
	
	/**
     * 导购主动添加标识：1是、0否
     */
    private Integer gmAddFlag;
    
    /**
     * 客户接受通过添加好友时间（即中控上传新增好友通知的时间）
     */
    private Date acceptTime;
    
    /** 旺旺账号*/
    private String noWw;
    
	/**
	 * @return the memberNoGuid
	 */
	public String getMemberNoGuid() {
		return memberNoGuid;
	}

	/**
	 * @param memberNoGuid the memberNoGuid to set
	 */
	public void setMemberNoGuid(String memberNoGuid) {
		this.memberNoGuid = memberNoGuid;
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
	 * @return the wxOpenId
	 */
	public String getWxOpenId() {
		return wxOpenId;
	}

	/**
	 * @param wxOpenId the wxOpenId to set
	 */
	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
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
	 * @return the noQQ
	 */
	public String getNoQQ() {
		return noQQ;
	}

	/**
	 * @param noQQ the noQQ to set
	 */
	public void setNoQQ(String noQQ) {
		this.noQQ = noQQ;
	}

	/**
	 * @return the labelName
	 */
	public String getLabelName() {
		return labelName;
	}

	/**
	 * @param labelName the labelName to set
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * @return the memberSrc
	 */
	public String getMemberSrc() {
		return memberSrc;
	}

	/**
	 * @param memberSrc the memberSrc to set
	 */
	public void setMemberSrc(String memberSrc) {
		this.memberSrc = memberSrc;
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
	 * @return the scanAddress
	 */
	public String getScanAddress() {
		return scanAddress;
	}

	/**
	 * @param scanAddress the scanAddress to set
	 */
	public void setScanAddress(String scanAddress) {
		this.scanAddress = scanAddress;
	}

    /**
     * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子、5主动搜索添加，如手机搜索
     */
	public Integer getAddType() {
		return addType;
	}

	/**
     * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子、5主动搜索添加，如手机搜索
     */
	public void setAddType(Integer addType) {
		this.addType = addType;
	}

	/**
	 * @return the subsribeTime
	 */
	public Date getSubsribeTime() {
		return subsribeTime;
	}

	/**
	 * @param subsribeTime the subsribeTime to set
	 */
	public void setSubsribeTime(Date subsribeTime) {
		this.subsribeTime = subsribeTime;
	}

	public Integer getGmAddFlag() {
        return gmAddFlag;
    }

    public void setGmAddFlag(Integer gmAddFlag) {
        this.gmAddFlag = gmAddFlag;
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

	public String getNoWw() {
		return noWw;
	}

	public void setNoWw(String noWw) {
		this.noWw = noWw;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddPersonMemberByWx [memberNoGuid=");
		builder.append(memberNoGuid);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", alias=");
		builder.append(alias);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", nickNameRemarkWx=");
		builder.append(nickNameRemarkWx);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", wxOpenId=");
		builder.append(wxOpenId);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", noQQ=");
		builder.append(noQQ);
		builder.append(", labelName=");
		builder.append(labelName);
		builder.append(", memberSrc=");
		builder.append(memberSrc);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", scanAddress=");
		builder.append(scanAddress);
		builder.append(", addType=");
		builder.append(addType);
		builder.append(", subsribeTime=");
		builder.append(subsribeTime);
		builder.append(", gmAddFlag=");
		builder.append(gmAddFlag);
		builder.append(", acceptTime=");
		builder.append(acceptTime);
		builder.append(", noWw=");
		builder.append(noWw);
		builder.append("]");
		return builder.toString();
	}

}
