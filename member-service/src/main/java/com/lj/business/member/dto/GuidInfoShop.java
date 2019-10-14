package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class GuidInfoShop.
 */
public class GuidInfoShop implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6846072547132425243L;


	/**
	 * 店员姓名 .
	 */
	private String memberName;
	
	/** 店员编号. */
	private String memberNo;
	
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
	
	
	/**商户编号*/
	private String merchantNo;
	
	
	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the area name.
	 *
	 * @return the area name
	 */
	public String getAreaName() {
	return areaName;
 }

 public void setAreaName(String areaName) {
	this.areaName = areaName;
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
	 * Gets the 店员姓名 .
	 *
	 * @return the 店员姓名 
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * Sets the 店员姓名 .
	 *
	 * @param memberName the new 店员姓名 
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * Gets the 入职时间 .
	 *
	 * @return the 入职时间 
	 */
	public Date getWorkDate() {
		return workDate;
	}

	/**
	 * Sets the 入职时间 .
	 *
	 * @param workDate the new 入职时间 
	 */
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	
	/**
	 * Gets the area code.
	 *
	 * @return the area code
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * Sets the area code.
	 *
	 * @param areaCode the area code
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GuidInfoShop [memberName=");
		builder.append(memberName);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", areaCode=");
		builder.append(areaCode);
		builder.append(", areaName=");
		builder.append(areaName);
		builder.append(", workDate=");
		builder.append(workDate);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}

}
