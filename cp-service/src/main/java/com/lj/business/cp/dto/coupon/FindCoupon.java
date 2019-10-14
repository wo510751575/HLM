package com.lj.business.cp.dto.coupon;

import java.io.Serializable;
import java.util.Date;

public class FindCoupon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8793693461529770713L;

	/**
	 * CODE .
	 */
	private String code;

	/** 商戶編號. */
	private String merchantNo;

	/** 門店編號. */
	

	/** 状态. */
	private String couponStatus;

	/** 是用时间. */
	private Date useDate;

	/** 终端名称. */
	

	/** 开始时间. */
	private Date startTime;

	/** 结束时间. */
	private Date endTime;

	/**
	 * 优惠券开始时间
	 */
	private Date beginDate;
	/**
	 * 优惠券结束时间
	 */
	private Date endDate;

	/**
	 * 优惠券名称
	 */
	private String couponName;
	/**
	 * 
	 * 优惠券类型
	 */
	private String couponType;

	/**
	 * 优惠券编号 .
	 */
	private String couponNo;
	
	/**
	 *规则编号
	 */
	private String ruleNo;
	
	/**
	 * 导购编号
	 */
	private String memberNoGm;
	
	/**
	 * 客户编号
	 */
	private String memberNo;
	
	/**
	 * 导购姓名
	 */
	private String memberNameGm;
	
	/**
	 * 客户姓名
	 */
	private String memberName;
	
	/**
	 * 优惠券领取码
	 */
	private String couponCode;
	
	/**
	 * 邀请人编号
	 */
	private String  InvitationNo;
	
	/**
	 * 微信好友编号
	 */
	private String  addFriendsCode;
	
	/**
	 * 分享状态（GUID:导购分享/MEMBER:客户分享）
	 */
	private String ShareStatus;
	
	/**
	 * 中控机微信号
	 */
	private String noWxGm;
	
	
	
	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	public String getShareStatus() {
		return ShareStatus;
	}

	public void setShareStatus(String shareStatus) {
		ShareStatus = shareStatus;
	}

	public String getAddFriendsCode() {
		return addFriendsCode;
	}

	public void setAddFriendsCode(String addFriendsCode) {
		this.addFriendsCode = addFriendsCode;
	}

	public String getInvitationNo() {
		return InvitationNo;
	}

	public void setInvitationNo(String invitationNo) {
		InvitationNo = invitationNo;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getMemberNameGm() {
		return memberNameGm;
	}

	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
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

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}


	public String getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindCoupon [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", couponStatus=");
		builder.append(couponStatus);
		builder.append(", useDate=");
		builder.append(useDate);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", beginDate=");
		builder.append(beginDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", couponName=");
		builder.append(couponName);
		builder.append(", couponType=");
		builder.append(couponType);
		builder.append(", couponNo=");
		builder.append(couponNo);
		builder.append(", ruleNo=");
		builder.append(ruleNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", couponCode=");
		builder.append(couponCode);
		builder.append(", InvitationNo=");
		builder.append(InvitationNo);
		builder.append(", addFriendsCode=");
		builder.append(addFriendsCode);
		builder.append(", ShareStatus=");
		builder.append(ShareStatus);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append("]");
		return builder.toString();
	}
	
}
