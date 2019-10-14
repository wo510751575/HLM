package com.lj.business.cp.dto.couponMemberRelation;

import java.io.Serializable;
import java.util.Date;

public class AddCouponMemberRelation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3224483133408469871L;

	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 导购编号 .
	 */
	private String memberNoGm;

	/**
	 * 导购姓名 .
	 */
	private String memberNameGm;

	/**
	 * 用户编号 .
	 */
	private String memberNo;

	/**
	 * 用户姓名 .
	 */
	private String memberName;

	/**
	 * 优惠券编号 .
	 */
	private String couponNo;

	/**
	 * 优惠券状态 未使用：UNUSED 已使用：USED 已过期：EXPIRED .
	 */
	private String couponStatus;

	/**
	 * 使用时间 .
	 */
	private Date useDate;

	/**
	 * 更新人 .
	 */
	private String updateId;

	/**
	 * 更新时间 .
	 */
	private Date updateDate;

	/**
	 * 创建人 .
	 */
	private String createId;

	/**
	 * 创建时间 .
	 */
	private Date createDate;
	
	/**
	 * 规则编号
	 */
	private String ruleNo;
	
	/**
	 * 微信好友CODE
	 */
	private String addFriendsCode;
	
	

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public String getAddFriendsCode() {
		return addFriendsCode;
	}

	public void setAddFriendsCode(String addFriendsCode) {
		this.addFriendsCode = addFriendsCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
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

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
