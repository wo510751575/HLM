package com.lj.business.cp.dto.couponMemberRelation;

import java.util.Date;

/**
 * 
 * 
 * 类说明：实体返回vo
 * 
 * 
 * 
 * 详细描述：
 * 
 * @Company: 杨恩科技有限公司
 * @author 杨杰
 * 
 * @date CreateDate: 2017年9月20日
 */
public class FindCouponMemberRelationVoReturn implements java.io.Serializable {
	private static final long serialVersionUID = -1657929724771566234L;
	// 导购编号
	private String memberNoGm;

	// 导购姓名
	private String memberNameGm;

	// 用户编号
	private String memberNo;

	// 用户姓名
	private String memberName;

	// 商户编号
	private String merchantNo;

	// 商户名称
	private String merchantName;

	// 分店编号
	

	// 分店名称
	

	// 规则编号
	private String ruleNo;

	// 优惠券编号
	private String couponNo;

	// 优惠券状态
	// 未使用：UNUSED
	// 已使用：USED
	// 已过期：EXPIRED
	private String couponStatus;

	// 使用时间
	private Date useDate;

	// 优惠券面额
	private Double couponNotes;

	// 优惠券开始时间
	private Date beginDate;

	// 优惠券结束时间
	private Date endDate;

	// 优惠券名称
	private String couponName;

	// 使用金额门槛
	private Double couponMax;

	// 优惠券类型CODE
	private String couponTypeCode;

	// 优惠券类型
	private String couponType;

	// 优惠券说明
	private String couponRemark;

	// 微信头像
	private String headAddress;

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

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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

	public Double getCouponNotes() {
		return couponNotes;
	}

	public void setCouponNotes(Double couponNotes) {
		this.couponNotes = couponNotes;
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

	public Double getCouponMax() {
		return couponMax;
	}

	public void setCouponMax(Double couponMax) {
		this.couponMax = couponMax;
	}

	public String getCouponTypeCode() {
		return couponTypeCode;
	}

	public void setCouponTypeCode(String couponTypeCode) {
		this.couponTypeCode = couponTypeCode;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getCouponRemark() {
		return couponRemark;
	}

	public void setCouponRemark(String couponRemark) {
		this.couponRemark = couponRemark;
	}

	public String getHeadAddress() {
		return headAddress;
	}

	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

}
