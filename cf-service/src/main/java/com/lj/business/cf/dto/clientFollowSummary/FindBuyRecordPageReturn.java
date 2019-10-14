package com.lj.business.cf.dto.clientFollowSummary;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FindBuyRecordPageReturn.
 */
public class FindBuyRecordPageReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8281641226061491803L;
	
	/**
     * CODE .
     */
    private String code;

    /**
     * 跟踪编号 .
     */
    private String cfNo;

    /**
     * 跟踪次数 .
     */
    private Integer followNum;


    /**
     * 订单金额 .
     */
    private Long orderAmount;
    
    /**
     * 所需产品编号
     */
    private String bomCode;
    
    /**
     * 所需产品名称
     */
    private String bomName;
    
    /**
     * 送货时间 .
     */
    private Date deliverTime;


    /**
     * 跟踪结束时间 .
     */
    private Date endDate;

    /**备注 */
    private String remark;
    
    /**备注2 */
    private String remark2;
    
    /**
	 * 客户编号
	 */
	private String memberNo;
	/**
	 * 客户名称
	 */
	private String memberName;
	/**
	 * 导购编号
	 */
	private String memberNoGm;
	/**
	 * 导购名称
	 */
	private String memberNameGm;
	/**
	 * 门店编号
	 */
	private String shopNo;
	/**
	 * 门店名称
	 */
	private String shopName;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 微信昵称
	 */
	private String nickNameWx;
	/**
	 * 头像地址
	 */
	private String headAddress;
	/** 客户类型id. */
	private String pmTypeType;
    
	public String getPmTypeType() {
		return pmTypeType;
	}


	public void setPmTypeType(String pmTypeType) {
		this.pmTypeType = pmTypeType;
	}


	/**
	 * Gets the cODE .
	 *
	 * @return the cODE 
	 */
	public String getCode() {
		return code;
	}


	/**
	 * Sets the cODE .
	 *
	 * @param code the new cODE 
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * Gets the 跟踪编号 .
	 *
	 * @return the 跟踪编号 
	 */
	public String getCfNo() {
		return cfNo;
	}


	/**
	 * Sets the 跟踪编号 .
	 *
	 * @param cfNo the new 跟踪编号 
	 */
	public void setCfNo(String cfNo) {
		this.cfNo = cfNo;
	}


	/**
	 * Gets the 跟踪次数 .
	 *
	 * @return the 跟踪次数 
	 */
	public Integer getFollowNum() {
		return followNum;
	}


	/**
	 * Sets the 跟踪次数 .
	 *
	 * @param followNum the new 跟踪次数 
	 */
	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}


	/**
	 * Gets the 订单金额 .
	 *
	 * @return the 订单金额 
	 */
	public Long getOrderAmount() {
		return orderAmount;
	}


	/**
	 * Sets the 订单金额 .
	 *
	 * @param orderAmount the new 订单金额 
	 */
	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}


	public String getBomCode() {
		return bomCode;
	}


	public void setBomCode(String bomCode) {
		this.bomCode = bomCode;
	}


	public String getBomName() {
		return bomName;
	}


	public void setBomName(String bomName) {
		this.bomName = bomName;
	}


	public Date getDeliverTime() {
		return deliverTime;
	}


	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}


	/**
	 * Gets the 跟踪结束时间 .
	 *
	 * @return the 跟踪结束时间 
	 */
	public Date getEndDate() {
		return endDate;
	}


	/**
	 * Sets the 跟踪结束时间 .
	 *
	 * @param endDate the new 跟踪结束时间 
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}


	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * @return the remark2
	 */
	public String getRemark2() {
		return remark2;
	}


	/**
	 * @param remark2 the remark2 to set
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}


	public String getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getMemberNoGm() {
		return memberNoGm;
	}


	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}


	public String getMemberNameGm() {
		return memberNameGm;
	}


	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}


	public String getShopNo() {
		return shopNo;
	}


	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}


	public String getShopName() {
		return shopName;
	}


	public void setShopName(String shopName) {
		this.shopName = shopName;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getNickNameWx() {
		return nickNameWx;
	}


	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
	}


	public String getHeadAddress() {
		return headAddress;
	}


	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindBuyRecordPageReturn [code=");
		builder.append(code);
		builder.append(", cfNo=");
		builder.append(cfNo);
		builder.append(", followNum=");
		builder.append(followNum);
		builder.append(", orderAmount=");
		builder.append(orderAmount);
		builder.append(", bomCode=");
		builder.append(bomCode);
		builder.append(", bomName=");
		builder.append(bomName);
		builder.append(", deliverTime=");
		builder.append(deliverTime);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", remark2=");
		builder.append(remark2);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", shopNo=");
		builder.append(shopNo);
		builder.append(", shopName=");
		builder.append(shopName);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", pmTypeType=");
		builder.append(pmTypeType);
		builder.append("]");
		return builder.toString();
	}

}
