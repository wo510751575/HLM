package com.lj.business.st.domain;

import java.util.Date;

public class TodayMemberSummary {
    /**
     * CODE .
     */
    private String code;

    /**
     * 汇总日期 .
     */
    private Date summaryDate;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 分店名称 .
     */
    private String shopName;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 到店客户数 .
     */
    private Integer shopCount;

    /**
     * 意向客户数 .
     */
    private Integer intentionCount;

    /**
     * 扫码客户数 .
     */
    private Integer scanCount;

    /**
     * 未扫码原因 .
     */
    private String unscanReason;

    /**
     * 填写资料客户数 .
     */
    private Integer infoCount;

    /**
     * 未填写资料原因 .
     */
    private String uninfoReason;

    /**
     * 创建时间 .
     */
    private Date createDate;

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
     * 汇总日期 .
     *
     */
    public Date getSummaryDate() {
        return summaryDate;
    }

    /**
     * 汇总日期 .
     *
     */
    public void setSummaryDate(Date summaryDate) {
        this.summaryDate = summaryDate;
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
     * 分店编号 .
     *
     */
    public String getShopNo() {
        return shopNo;
    }

    /**
     * 分店编号 .
     *
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    /**
     * 分店名称 .
     *
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 分店名称 .
     *
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    /**
     * 导购编号 .
     *
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号 .
     *
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    /**
     * 到店客户数 .
     *
     */
    public Integer getShopCount() {
        return shopCount;
    }

    /**
     * 到店客户数 .
     *
     */
    public void setShopCount(Integer shopCount) {
        this.shopCount = shopCount;
    }

    /**
     * 意向客户数 .
     *
     */
    public Integer getIntentionCount() {
        return intentionCount;
    }

    /**
     * 意向客户数 .
     *
     */
    public void setIntentionCount(Integer intentionCount) {
        this.intentionCount = intentionCount;
    }

    /**
     * 扫码客户数 .
     *
     */
    public Integer getScanCount() {
        return scanCount;
    }

    /**
     * 扫码客户数 .
     *
     */
    public void setScanCount(Integer scanCount) {
        this.scanCount = scanCount;
    }

    /**
     * 未扫码原因 .
     *
     */
    public String getUnscanReason() {
        return unscanReason;
    }

    /**
     * 未扫码原因 .
     *
     */
    public void setUnscanReason(String unscanReason) {
        this.unscanReason = unscanReason == null ? null : unscanReason.trim();
    }

    /**
     * 填写资料客户数 .
     *
     */
    public Integer getInfoCount() {
        return infoCount;
    }

    /**
     * 填写资料客户数 .
     *
     */
    public void setInfoCount(Integer infoCount) {
        this.infoCount = infoCount;
    }

    /**
     * 未填写资料原因 .
     *
     */
    public String getUninfoReason() {
        return uninfoReason;
    }

    /**
     * 未填写资料原因 .
     *
     */
    public void setUninfoReason(String uninfoReason) {
        this.uninfoReason = uninfoReason == null ? null : uninfoReason.trim();
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

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TodayMemberSummary [code=").append(code);
        builder.append(",summaryDate=").append(summaryDate); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",shopName=").append(shopName); 
        builder.append(",memberNoGm=").append(memberNoGm); 
        builder.append(",memberNameGm=").append(memberNameGm); 
        builder.append(",shopCount=").append(shopCount); 
        builder.append(",intentionCount=").append(intentionCount); 
        builder.append(",scanCount=").append(scanCount); 
        builder.append(",unscanReason=").append(unscanReason); 
        builder.append(",infoCount=").append(infoCount); 
        builder.append(",uninfoReason=").append(uninfoReason); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}