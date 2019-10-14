package com.lj.business.cp.dto.couponRule;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FindCouponRule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6910245093095933821L;

	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 商户编号 .
	 */
	private String merchantNo;

	/**
	 * 商户名称 .
	 */
	private String merchantName;
	/**
     * 已选择的网点微信号 (导购助手).
     */
    private String noWxShops;

	/**
	 * 优惠券总数 .
	 */
	private Integer couponNum;

	/**
	 * 优惠券面额 .
	 */
	private BigDecimal couponNotes;

	/**
	 * 优惠券开始时间 .
	 */
	private Date beginDate;

	/**
	 * 优惠券结束时间 .
	 */
	private Date endDate;

	/**
	 * 优惠券名称 .
	 */
	private String couponName;

	/**
	 * 使用金额门槛 .
	 */
	private BigDecimal couponMax;

	/**
	 * 优惠券类型CODE .
	 */
	private String couponTypeCode;

	/**
	 * 优惠券类型 优惠券：COUPON 现金券：CASH 折扣券：DISCOUNT .
	 */
	private String couponType;

	/**
	 * 优惠券说明 .
	 */
	private String couponRemark;

	/**
	 * 发券条件 无：NONE 邀请人数：INVITE .
	 */
	private String toCoupon;

	/**
	 * 使用范围： 全店铺：ALL 指定店铺:ASSIGN .
	 */
	private String useScope;

	/**
	 * 是否实名 实名：YES 不实名：NO .
	 */
	private String realName;

	/**
	 * 规则状态 启用：YES 禁用：NO .
	 */
	private String ruleStatus;
	
	/**
     *  是否已生成优惠券
     */
    private String produce;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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


	public Integer getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
	}

	public BigDecimal getCouponNotes() {
		return couponNotes;
	}

	public void setCouponNotes(BigDecimal couponNotes) {
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

	public BigDecimal getCouponMax() {
		return couponMax;
	}

	public void setCouponMax(BigDecimal couponMax) {
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

	public String getToCoupon() {
		return toCoupon;
	}

	public void setToCoupon(String toCoupon) {
		this.toCoupon = toCoupon;
	}

	public String getUseScope() {
		return useScope;
	}

	public void setUseScope(String useScope) {
		this.useScope = useScope;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRuleStatus() {
		return ruleStatus;
	}

	public void setRuleStatus(String ruleStatus) {
		this.ruleStatus = ruleStatus;
	}

    public String getNoWxShops() {
        return noWxShops;
    }

    public void setNoWxShops(String noWxShops) {
        this.noWxShops = noWxShops;
    }


    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindCouponRule [code=");
        builder.append(code);
        builder.append(", merchantNo=");
        builder.append(merchantNo);
        builder.append(", merchantName=");
        builder.append(merchantName);
        builder.append(", noWxShops=");
        builder.append(noWxShops);
        builder.append(", couponNum=");
        builder.append(couponNum);
        builder.append(", couponNotes=");
        builder.append(couponNotes);
        builder.append(", beginDate=");
        builder.append(beginDate);
        builder.append(", endDate=");
        builder.append(endDate);
        builder.append(", couponName=");
        builder.append(couponName);
        builder.append(", couponMax=");
        builder.append(couponMax);
        builder.append(", couponTypeCode=");
        builder.append(couponTypeCode);
        builder.append(", couponType=");
        builder.append(couponType);
        builder.append(", couponRemark=");
        builder.append(couponRemark);
        builder.append(", toCoupon=");
        builder.append(toCoupon);
        builder.append(", useScope=");
        builder.append(useScope);
        builder.append(", realName=");
        builder.append(realName);
        builder.append(", ruleStatus=");
        builder.append(ruleStatus);
        builder.append(", produce=");
        builder.append(produce);
        builder.append("]");
        return builder.toString();
    }

}
