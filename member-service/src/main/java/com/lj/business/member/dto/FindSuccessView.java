package com.lj.business.member.dto;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * The Class FindPersonMemberPage.
 */
public class FindSuccessView extends PageParamEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7146345596624141287L; 
	
	/** 开始时间. */
	private Date startTime;			
	
	/** 结束时间. */
	private Date endTime;			
	
	/** 商户编号. */
	private String merchantNo;
	
	/**
     * 导购编号
     */
    private String memberNoGm;
    
    /**
     * 导购名称
     */
    private String memberNameGm;
    
    /**
     * 会员编号
     */
    private String memberNo;
    
    /**
     * 会员名称
     */
    private String memberName;

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindSuccessView [startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append("]");
		return builder.toString();
	}

}
