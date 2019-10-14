package com.lj.business.cp.dto.couponRule;

import java.io.Serializable;
import java.util.Date;

public class FindCouponRuleReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2879510099759753826L;

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
	private Integer couponNotes;

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
	private Integer couponMax;

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
	 * 分享URL .
	 */
	private String shareUrl;

	/**
	 * 客户端URL .
	 */
	private String clientUrl;

	/**
	 * 商户图标地址 .
	 */
	private String merchantLogoUrl;
	
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
     * 导购编号
     */
    private String memberNoGm;
    
    /**
     * 客户编号
     */
    private String memberNo;
    

    /**
     * 优惠券编号
     */
    private String couponNo;
   
    
    /**
     * 微信好友编号
     */
    private String addFriendsCode;
    
    /**
     * 优惠券状态
     */
    private String couponStatus;
     
    /**
     * 商户图标
     */
    private String resources;
    
    /**
     * 剩余数
     */
    private Integer surplusNum;
    
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

	public Integer getSurplusNum() {
		return surplusNum;
	}

	public void setSurplusNum(Integer surplusNum) {
		this.surplusNum = surplusNum;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public String getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
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

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public String getAddFriendsCode() {
		return addFriendsCode;
	}

	public void setAddFriendsCode(String addFriendsCode) {
		this.addFriendsCode = addFriendsCode;
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

	public Integer getCouponNotes() {
        return couponNotes;
    }

    public void setCouponNotes(Integer couponNotes) {
        this.couponNotes = couponNotes;
    }

    public Integer getCouponMax() {
        return couponMax;
    }

    public void setCouponMax(Integer couponMax) {
        this.couponMax = couponMax;
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

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getClientUrl() {
		return clientUrl;
	}

	public void setClientUrl(String clientUrl) {
		this.clientUrl = clientUrl;
	}

	public String getMerchantLogoUrl() {
		return merchantLogoUrl;
	}

	public void setMerchantLogoUrl(String merchantLogoUrl) {
		this.merchantLogoUrl = merchantLogoUrl;
	}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindCouponRuleReturn [code=");
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
        builder.append(", shareUrl=");
        builder.append(shareUrl);
        builder.append(", clientUrl=");
        builder.append(clientUrl);
        builder.append(", merchantLogoUrl=");
        builder.append(merchantLogoUrl);
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
        builder.append(", memberNoGm=");
        builder.append(memberNoGm);
        builder.append(", memberNo=");
        builder.append(memberNo);
        builder.append(", couponNo=");
        builder.append(couponNo);
        builder.append(", addFriendsCode=");
        builder.append(addFriendsCode);
        builder.append(", couponStatus=");
        builder.append(couponStatus);
        builder.append(", resources=");
        builder.append(resources);
        builder.append(", surplusNum=");
        builder.append(surplusNum);
        builder.append(", useRule=");
        builder.append(useRule);
        builder.append("]");
        return builder.toString();
    }

}
