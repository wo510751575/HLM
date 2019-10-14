package com.ye.business.ad.domain;

import java.util.Date;

public class Bill {
    /**
     * CODE .
     */
    private String code;

    /**
     * 流水号 .
     */
    private String tradeNo;

    /**
     * 账单类型:1-充值 2-提现 3-广告支出 4-广告收入 .
     */
    private String tradeType;

    /**
     * 员工编号 .
     */
    private String memberNo;

    /**
     * 员工姓名 .
     */
    private String memberName;

    /**
     * 商户号 .
     */
    private String merchantNo;

    /**
     * 商户名 .
     */
    private String merchantName;

    /**
     * 登录账号 .
     */
    private String loginName;

    /**
     * 金额 .
     */
    private Integer amount;

    /**
     * 创建时间 .
     */
    private Date createTime;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注 .
     */
    private String remark2;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark4;

	/**
	 * 广告code .
	 */
	private String advertiseCode;
	
	/**
	 * 文章code
	 */
	private String articleCode;

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
     * 流水号 .
     *
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * 流水号 .
     *
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    /**
     * 账单类型:1-充值 2-提现 3-广告支出 4-广告收入 .
     *
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 账单类型:1-充值 2-提现 3-广告支出 4-广告收入 .
     *
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 员工编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 员工编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * 员工姓名 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 员工姓名 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 商户号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 商户名 .
     *
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名 .
     *
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /**
     * 登录账号 .
     *
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 登录账号 .
     *
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 金额 .
     *
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 金额 .
     *
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
     * 备注 .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    public String getAdvertiseCode() {
		return advertiseCode;
	}

	public void setAdvertiseCode(String advertiseCode) {
		this.advertiseCode = advertiseCode;
	}

	public String getArticleCode() {
		return articleCode;
	}

	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	/**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Bill [code=").append(code);
        builder.append(",tradeNo=").append(tradeNo); 
        builder.append(",tradeType=").append(tradeType); 
        builder.append(",memberNo=").append(memberNo); 
        builder.append(",memberName=").append(memberName); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",loginName=").append(loginName); 
        builder.append(",amount=").append(amount); 
        builder.append(",createTime=").append(createTime); 
        builder.append(",createId=").append(createId); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}