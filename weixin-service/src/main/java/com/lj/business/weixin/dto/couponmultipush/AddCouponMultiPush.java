package com.lj.business.weixin.dto.couponmultipush;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AddCouponMultiPush implements Serializable {
    private static final long serialVersionUID = 1753859473145394017L;
    
    /**
     * 导购助手编号(必填).
     */
    private String assistantNo;
    
    /**
     * 商户编号 (必填).
     */
    private String merchantNo;
    
    /**
     * 优惠券规则CODE
     */
    private String couponRuleCode;
    
    /**
     * 终端微信(多个终端微信逗号隔开)
     */
    private String shopNoWxs;
    
    /**
     * 终端微信和客户对应，字符拼接noWx#num#codes;noWx#num#codes
     */
    private String noWxMapping;
    
    /**
     * 终端微信和客户对应
     */
    private List<ShopTerminalMappingPm> shopNoWxMappingPm;
    
    /**
     * 执行时间 (如果立即执行，会忽略此字段).
     */
    private Date executeTime;
    
    /**
     * 执行类型(1：保存任务，2：立即执行) .
     */
    private Integer executeType;

    /**
     * 延迟时间(分钟) .
     */
    private Integer delayTimes;

    public String getAssistantNo() {
        return assistantNo;
    }

    public void setAssistantNo(String assistantNo) {
        this.assistantNo = assistantNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getShopNoWxs() {
        return shopNoWxs;
    }

    public void setShopNoWxs(String shopNoWxs) {
        this.shopNoWxs = shopNoWxs;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public Integer getExecuteType() {
        return executeType;
    }

    public void setExecuteType(Integer executeType) {
        this.executeType = executeType;
    }

    public Integer getDelayTimes() {
        return delayTimes;
    }

    public void setDelayTimes(Integer delayTimes) {
        this.delayTimes = delayTimes;
    }

    public String getCouponRuleCode() {
        return couponRuleCode;
    }

    public void setCouponRuleCode(String couponRuleCode) {
        this.couponRuleCode = couponRuleCode;
    }

    public List<ShopTerminalMappingPm> getShopNoWxMappingPm() {
        return shopNoWxMappingPm;
    }

    public void setShopNoWxMappingPm(List<ShopTerminalMappingPm> shopNoWxMappingPm) {
        this.shopNoWxMappingPm = shopNoWxMappingPm;
    }

    public String getNoWxMapping() {
        return noWxMapping;
    }

    public void setNoWxMapping(String noWxMapping) {
        this.noWxMapping = noWxMapping;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AddCouponMultiPush [assistantNo=");
        builder.append(assistantNo);
        builder.append(", merchantNo=");
        builder.append(merchantNo);
        builder.append(", couponRuleCode=");
        builder.append(couponRuleCode);
        builder.append(", shopNoWxs=");
        builder.append(shopNoWxs);
        builder.append(", noWxMapping=");
        builder.append(noWxMapping);
        builder.append(", shopNoWxMappingPm=");
        builder.append(shopNoWxMappingPm);
        builder.append(", executeTime=");
        builder.append(executeTime);
        builder.append(", executeType=");
        builder.append(executeType);
        builder.append(", delayTimes=");
        builder.append(delayTimes);
        builder.append("]");
        return builder.toString();
    }

}
