package com.lj.business.cp.dto.couponRule;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AddCouponRule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6047408014370883742L;

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
	 * 优惠券总数 .
	 */
	private Integer couponNum;

	/**
	 * 优惠券面额 .
	 */
	private BigDecimal couponNotes;
	/**
	 * 优惠券面额 .
	 */
	private Double doubleCouponNotes;

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
	 * 使用金额门槛 .
	 */
	private Double doubleCouponMax;

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
     * 启用时间 .
     */
    private Date enableDate;

    /**
     * 是否核销 .
     */
    private String isWriteoff;

    /**
     * 使用范围数量集合 .
     */
    private String useNums;
    

	/**
     *  是否已生成优惠券
     */
    private String isProduce;
    
    /**
     * 限定数量
     */
    private Integer useNum;
    
    /**
     * 终端优惠券平均数
     */
    private Integer couponAvgNum;
    
    /**
     * 使用规则
     */
    private String useRule;
    

	public String getUseRule() {
		return useRule;
	}

	public void setUseRule(String useRule) {
		this.useRule = useRule;
	}

	public Integer getCouponAvgNum() {
		return couponAvgNum;
	}

	public void setCouponAvgNum(Integer couponAvgNum) {
		this.couponAvgNum = couponAvgNum;
	}

	public Integer getUseNum() {
		return useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

	public Date getEnableDate() {
		return enableDate;
	}

	public void setEnableDate(Date enableDate) {
		this.enableDate = enableDate;
	}

	public String getIsWriteoff() {
		return isWriteoff;
	}

	public void setIsWriteoff(String isWriteoff) {
		this.isWriteoff = isWriteoff;
	}

	public String getUseNums() {
		return useNums;
	}

	public void setUseNums(String useNums) {
		this.useNums = useNums;
	}

	public String getIsProduce() {
		return isProduce;
	}

	public void setIsProduce(String isProduce) {
		this.isProduce = isProduce;
	}

	public Double getDoubleCouponNotes() {
		return doubleCouponNotes;
	}

	public void setDoubleCouponNotes(Double doubleCouponNotes) {
		this.doubleCouponNotes = doubleCouponNotes;
	}

	public Double getDoubleCouponMax() {
		return doubleCouponMax;
	}

	public void setDoubleCouponMax(Double doubleCouponMax) {
		this.doubleCouponMax = doubleCouponMax;
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
		builder.append("AddCouponRule [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", couponNum=");
		builder.append(couponNum);
		builder.append(", couponNotes=");
		builder.append(couponNotes);
		builder.append(", doubleCouponNotes=");
		builder.append(doubleCouponNotes);
		builder.append(", beginDate=");
		builder.append(beginDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", couponName=");
		builder.append(couponName);
		builder.append(", couponMax=");
		builder.append(couponMax);
		builder.append(", doubleCouponMax=");
		builder.append(doubleCouponMax);
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
		builder.append(", updateId=");
		builder.append(updateId);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", enableDate=");
		builder.append(enableDate);
		builder.append(", isWriteoff=");
		builder.append(isWriteoff);
		builder.append(", useNums=");
		builder.append(useNums);
		builder.append(", isProduce=");
		builder.append(isProduce);
		builder.append(", useNum=");
		builder.append(useNum);
		builder.append(", couponAvgNum=");
		builder.append(couponAvgNum);
		builder.append(", useRule=");
		builder.append(useRule);
		builder.append("]");
		return builder.toString();
	}

	
}
