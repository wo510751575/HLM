package com.lj.business.cp.domain;

import java.util.Date;

public class Coupon {
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
     * 终端微信 .
     */
    private String shopWx;

    /**
     * 规则编号 .
     */
    private String ruleNo;

    /**
     * 优惠券编号 .
     */
    private String couponNo;

    /**
     * 优惠券状态             未使用：UNUSED             已使用：USED             已过期：EXPIRED .
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

    public String getShopWx() {
		return shopWx;
	}

	public void setShopWx(String shopWx) {
		this.shopWx = shopWx;
	}

	/**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 商户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 商户名称 .
     *
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名称 .
     *
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /**
     * 规则编号 .
     *
     */
    public String getRuleNo() {
        return ruleNo;
    }

    /**
     * 规则编号 .
     *
     */
    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo == null ? null : ruleNo.trim();
    }

    /**
     * 优惠券编号 .
     *
     */
    public String getCouponNo() {
        return couponNo;
    }

    /**
     * 优惠券编号 .
     *
     */
    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo == null ? null : couponNo.trim();
    }

    /**
     * 优惠券状态             未使用：UNUSED             已使用：USED             已过期：EXPIRED .
     *
     */
    public String getCouponStatus() {
        return couponStatus;
    }

    /**
     * 优惠券状态             未使用：UNUSED             已使用：USED             已过期：EXPIRED .
     *
     */
    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus == null ? null : couponStatus.trim();
    }

    /**
     * 使用时间 .
     *
     */
    public Date getUseDate() {
        return useDate;
    }

    /**
     * 使用时间 .
     *
     */
    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    /**
     * 更新人 .
     *
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 更新人 .
     *
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * 更新时间 .
     *
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间 .
     *
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 创建人 .
     *
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coupon [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", shopWx=");
		builder.append(shopWx);
		builder.append(", ruleNo=");
		builder.append(ruleNo);
		builder.append(", couponNo=");
		builder.append(couponNo);
		builder.append(", couponStatus=");
		builder.append(couponStatus);
		builder.append(", useDate=");
		builder.append(useDate);
		builder.append(", updateId=");
		builder.append(updateId);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}

}