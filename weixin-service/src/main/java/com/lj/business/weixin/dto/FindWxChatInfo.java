package com.lj.business.weixin.dto;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.io.Serializable;
import java.util.Date;

/**
 * The Class FindWxChatInfo.
 */
public class FindWxChatInfo implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -328508040178572212L;

	/** The code. */
     private String code;
     /**
      * 商户编号
      */
     private String merchantNo;
   
   /** 导购编号. */
     private String memberNo;
     
     /** 开始时间. */
     private String startTime;
     
     /** 结束时间. */
     private String endTime;
     
     /**
      * 微信号 .
      */
     private String talker;
     
     /**
 	 * 店员姓名 .
 	 */
 	private String memberName;
 	
 	/**
 	 * 咨询数量
 	 */
 	private Integer count;
 	
 	
     /** 区域code. */
     private String areaCode;
     
    /**区域名称 */
     private String areaName;

 	/**
 	 * 入职时间 .
 	 */
 	private Date workDate;
 	
 	/** 分店编号. */
 	
 	
 	/** 分店名称. */
 	
 	
     /**统计维度*/
 	private String dimensionSt;
 	
 	/**
 	 * 导购微信号
 	 * @return
 	 */
 	private String noWxGm;
 	
     
	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	public String getDimensionSt() {
		return dimensionSt;
	}

	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}


	/**
	 * Gets the member no.
	 *
	 * @return the member no
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * Sets the member no.
	 *
	 * @param memberNo the member no
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime the start time
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the end time
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTalker() {
		return talker;
	}

	public void setTalker(String talker) {
		this.talker = talker;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWxChatInfo [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", talker=");
		builder.append(talker);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", count=");
		builder.append(count);
		builder.append(", areaCode=");
		builder.append(areaCode);
		builder.append(", areaName=");
		builder.append(areaName);
		builder.append(", workDate=");
		builder.append(workDate);
		builder.append(", dimensionSt=");
		builder.append(dimensionSt);
		builder.append("]");
		return builder.toString();
	}

}
