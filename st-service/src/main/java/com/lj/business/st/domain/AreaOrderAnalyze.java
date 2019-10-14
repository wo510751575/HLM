package com.lj.business.st.domain;

import java.io.Serializable;
import java.util.Date;

public class AreaOrderAnalyze implements Serializable {

    private static final long serialVersionUID = -1488544267958979921L;

    /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 区域CODE .
     */
    private String areaCode;

    /**
     * 区域名称 .
     */
    private String areaName;

    /**
     * 统计日期 .
     */
    private Date stDate;

    /**
     * 成单数量 .
     */
    private Long numOrder;

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
     * 区域CODE .
     *
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 区域CODE .
     *
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 区域名称 .
     *
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 区域名称 .
     *
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * 统计日期 .
     *
     */
    public Date getStDate() {
        return stDate;
    }

    /**
     * 统计日期 .
     *
     */
    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }

    /**
     * 成单数量 .
     *
     */
    public Long getNumOrder() {
        return numOrder;
    }

    /**
     * 成单数量 .
     *
     */
    public void setNumOrder(Long numOrder) {
        this.numOrder = numOrder;
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
        builder.append("AreaOrderAnalyze [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",areaCode=").append(areaCode); 
        builder.append(",areaName=").append(areaName); 
        builder.append(",stDate=").append(stDate); 
        builder.append(",numOrder=").append(numOrder); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}