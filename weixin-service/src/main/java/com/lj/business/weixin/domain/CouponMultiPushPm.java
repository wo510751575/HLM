package com.lj.business.weixin.domain;

import java.util.Date;

public class CouponMultiPushPm {
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
     * 分店编号
     */
    
    /**
     * 分店名称
     */
    
    /**
     * 终端微信
     */
    private String noWxShop;
    /**
     * 客户编号
     */
    private String memberNo;
    /**
     * 导购编号
     */
    private String memberNoGm;
    /**
     * 优惠券群发CODE
     */
    private String pushCode;
    /**
     * 优惠券群发终端CODE
     */
    private String pushTerminalCode;
    /**
     * 发送状态
     */
    private Integer status;
    /**
     * 失败原因
     */
    private String failReason;
    /**
     * 优惠券CODE
     */
    private String couponCode;
    /**
     * 创建人
     */
    private String createId;
    /**
     * 创建时间
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


    public String getNoWxShop() {
        return noWxShop;
    }

    public void setNoWxShop(String noWxShop) {
        this.noWxShop = noWxShop == null ? null : noWxShop.trim();
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    public String getMemberNoGm() {
        return memberNoGm;
    }

    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    public String getPushCode() {
        return pushCode;
    }

    public void setPushCode(String pushCode) {
        this.pushCode = pushCode == null ? null : pushCode.trim();
    }

    public String getPushTerminalCode() {
        return pushTerminalCode;
    }

    public void setPushTerminalCode(String pushTerminalCode) {
        this.pushTerminalCode = pushTerminalCode == null ? null : pushTerminalCode.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode == null ? null : couponCode.trim();
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