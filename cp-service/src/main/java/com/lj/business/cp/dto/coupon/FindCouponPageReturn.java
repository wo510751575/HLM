package com.lj.business.cp.dto.coupon;

import java.io.Serializable;
import java.util.Date;

public class FindCouponPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5959618722722885194L; 
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
     * 规则编号 .
     */
    private String ruleNo;

    /**
     * 优惠券编号 .
     */
    private String couponNo;

    /**
     * 优惠券状态
             未使用：UNUSED
             已使用：USED
             已过期：EXPIRED .
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
     * 优惠券总数
     */
    private Integer couponNum;
    /**
     * 优惠券面额
     */
    private String couponNotes;
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
     * 优惠券类型
             优惠券：COUPON
             现金券：CASH
             折扣券：DISCOUNT .
     */
    private String couponType;
    
    


	public Integer getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
	}

	public String getCouponNotes() {
		return couponNotes;
	}

	public void setCouponNotes(String couponNotes) {
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

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindCouponPageReturn [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
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
		builder.append(", couponType=");
		builder.append(couponType);
		builder.append("]");
		return builder.toString();
	}

}
