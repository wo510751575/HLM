package com.lj.business.cf.dto.clientFollowSummary;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

public class FindBuyRecordPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5547617805729094343L;

	 /**
     * 商户编号（必填） .
     */
    private String merchantNo;

    /**
     * 客户编号（必填） .
     */
    private String memberNo;
    
    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 导购编号（必填） .
     */
    private String memberNoGm;
    
    /**
     * 导购名称
     */
    private String memberNameGm;
    
    /**
     * 开始时间 .
     */
    private Date beginTime;

    /**
     * 结束时间 .
     */
    private Date endTime;

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNameGm() {
		return memberNameGm;
	}

	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the 商户编号 .
	 *
	 * @return the 商户编号 
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号 .
	 *
	 * @param merchantNo the new 商户编号 
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the 客户编号 .
	 *
	 * @return the 客户编号 
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * Sets the 客户编号 .
	 *
	 * @param memberNo the new 客户编号 
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * Gets the 导购编号 .
	 *
	 * @return the 导购编号 
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the 导购编号 .
	 *
	 * @param memberNoGm the new 导购编号 
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindBuyRecordPage [merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", beginTime=");
		builder.append(beginTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append("]");
		return builder.toString();
	}

}
