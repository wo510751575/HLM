package com.lj.business.cp.dto.coupon;

import java.io.Serializable;

public class EmployCoupon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8099506769716789949L;

	

	/**
	 * 规则编号 .
	 */
	private String ruleNo;
    
	/**
	 * 优惠券编号
	 */
    private String couponNo;
    
    /**
     * 客户编号
     */
    private String memberNo;
    
    /**
     * 微信好友编号
     */
    private String addFriendsCode;
    
    /**
     * 优惠券名称
     */
    private String couponName;
    
    /**
     * 客户名称
     */
    private String memberName;

    
    
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getAddFriendsCode() {
		return addFriendsCode;
	}

	public void setAddFriendsCode(String addFriendsCode) {
		this.addFriendsCode = addFriendsCode;
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

    
    
}
