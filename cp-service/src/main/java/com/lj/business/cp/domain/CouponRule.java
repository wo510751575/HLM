package com.lj.business.cp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class CouponRule {
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
     * 分店编号 .
     */
    

    /**
     * 分店名称 .
     */
    

    /**
     * 优惠券总数 .
     */
    private Integer couponNum;

    /**
     * 优惠券面额 .
     */
    private Double couponNotes;

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
    private Double couponMax;

    /**
     * 优惠券类型CODE .
     */
    private String couponTypeCode;

    /**
     * 优惠券类型             优惠券：COUPON             现金券：CASH             折扣券：DISCOUNT .
     */
    private String couponType;

    /**
     * 优惠券说明 .
     */
    private String couponRemark;

    /**
     * 发券条件             无：NONE             邀请人数：INVITE .
     */
    private String toCoupon;

    /**
     * 使用范围：             全店铺：ALL             指定店铺:ASSIGN .
     */
    private String useScope;

    /**
     * 是否实名             实名：YES             不实名：NO .
     */
    private String realName;

    /**
     * 规则状态             启用：YES             禁用：NO .
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
     * 终端平均的券数量
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
     * 优惠券总数 .
     *
     */
    public Integer getCouponNum() {
        return couponNum;
    }

    /**
     * 优惠券总数 .
     *
     */
    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
    }

    /**
     * 优惠券面额 .
     *
     */
    public Double getCouponNotes() {
        return couponNotes;
    }

    /**
     * 优惠券面额 .
     *
     */
    public void setCouponNotes(Double couponNotes) {
        this.couponNotes = couponNotes;
    }

    /**
     * 优惠券开始时间 .
     *
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 优惠券开始时间 .
     *
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 优惠券结束时间 .
     *
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 优惠券结束时间 .
     *
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 优惠券名称 .
     *
     */
    public String getCouponName() {
        return couponName;
    }

    /**
     * 优惠券名称 .
     *
     */
    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    /**
     * 使用金额门槛 .
     *
     */
    public Double getCouponMax() {
        return couponMax;
    }

    /**
     * 使用金额门槛 .
     *
     */
    public void setCouponMax(Double couponMax) {
        this.couponMax = couponMax;
    }

    /**
     * 优惠券类型CODE .
     *
     */
    public String getCouponTypeCode() {
        return couponTypeCode;
    }

    /**
     * 优惠券类型CODE .
     *
     */
    public void setCouponTypeCode(String couponTypeCode) {
        this.couponTypeCode = couponTypeCode == null ? null : couponTypeCode.trim();
    }

    /**
     * 优惠券类型             优惠券：COUPON             现金券：CASH             折扣券：DISCOUNT .
     *
     */
    public String getCouponType() {
        return couponType;
    }

    /**
     * 优惠券类型             优惠券：COUPON             现金券：CASH             折扣券：DISCOUNT .
     *
     */
    public void setCouponType(String couponType) {
        this.couponType = couponType == null ? null : couponType.trim();
    }

    /**
     * 优惠券说明 .
     *
     */
    public String getCouponRemark() {
        return couponRemark;
    }

    /**
     * 优惠券说明 .
     *
     */
    public void setCouponRemark(String couponRemark) {
        this.couponRemark = couponRemark == null ? null : couponRemark.trim();
    }

    /**
     * 发券条件             无：NONE             邀请人数：INVITE .
     *
     */
    public String getToCoupon() {
        return toCoupon;
    }

    /**
     * 发券条件             无：NONE             邀请人数：INVITE .
     *
     */
    public void setToCoupon(String toCoupon) {
        this.toCoupon = toCoupon == null ? null : toCoupon.trim();
    }

    /**
     * 使用范围：             全店铺：ALL             指定店铺:ASSIGN .
     *
     */
    public String getUseScope() {
        return useScope;
    }

    /**
     * 使用范围：             全店铺：ALL             指定店铺:ASSIGN .
     *
     */
    public void setUseScope(String useScope) {
        this.useScope = useScope == null ? null : useScope.trim();
    }

    /**
     * 是否实名             实名：YES             不实名：NO .
     *
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 是否实名             实名：YES             不实名：NO .
     *
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 规则状态             启用：YES             禁用：NO .
     *
     */
    public String getRuleStatus() {
        return ruleStatus;
    }

    /**
     * 规则状态             启用：YES             禁用：NO .
     *
     */
    public void setRuleStatus(String ruleStatus) {
        this.ruleStatus = ruleStatus == null ? null : ruleStatus.trim();
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
		builder.append("CouponRule [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
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
		builder.append(", couponAvgNum=");
		builder.append(couponAvgNum);
		builder.append(", useRule=");
		builder.append(useRule);
		builder.append("]");
		return builder.toString();
	}
}