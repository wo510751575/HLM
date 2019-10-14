package com.lj.business.weixin.domain;

import java.util.Date;

public class CouponMultiPush {
    /**
     * CODE
     */
    private String code;
    /**
     * 商户编号
     */
    private String merchantNo;
    /**
     * 商户名称
     */
    private String merchantName;
    /**
     * 优惠券规则CODE
     */
    private String couponRuleCode;
    /**
     * 优惠券名称
     */
    private String couponName;
    /**
     * 终端微信(多个终端微信逗号隔开)
     */
    private String shopNoWxs;
    /**
     * 客户总数量
     */
    private Integer pmNum;
    /**
     * 已发送客户数量
     */
    private Integer sentPmNum;
    /**
     * 执行时间
     */
    private Date executeTime;
    /**
     * 任务状态：1创建、2执行中、3执行完成
     */
    private Integer status;
    /**
     * 延迟时间(分钟)
     */
    private Integer delayTimes;
    /**
     * 创建人
     */
    private String createId;
    /**
     * 创建时间,
     */
    private Date createDate;
    /**
     * 更新人
     */
    private String updateId;
    /**
     * 更新时间
     */
    private Date updateDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getCouponRuleCode() {
        return couponRuleCode;
    }

    public void setCouponRuleCode(String couponRuleCode) {
        this.couponRuleCode = couponRuleCode == null ? null : couponRuleCode.trim();
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public String getShopNoWxs() {
        return shopNoWxs;
    }

    public void setShopNoWxs(String shopNoWxs) {
        this.shopNoWxs = shopNoWxs == null ? null : shopNoWxs.trim();
    }

    public Integer getPmNum() {
        return pmNum;
    }

    public void setPmNum(Integer pmNum) {
        this.pmNum = pmNum;
    }

    public Integer getSentPmNum() {
        return sentPmNum;
    }

    public void setSentPmNum(Integer sentPmNum) {
        this.sentPmNum = sentPmNum;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelayTimes() {
        return delayTimes;
    }

    public void setDelayTimes(Integer delayTimes) {
        this.delayTimes = delayTimes;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}