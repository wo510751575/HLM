package com.lj.business.weixin.domain;

import java.util.Date;

public class WxJobRedPackInfo {
    /**
     * code .
     */
    private String code;

    /**
     * JOB_CODE .
     */
    private String jobCode;

    /**
     * 商户号 .
     */
    private String merchantNo;

    /**
     * 终端微信号 .
     */
    private String wxNoShop;

    /**
     * 终端号 .
     */
    

    /**
     * 发红包导购号 .
     */
    private String sendRedpackGm;

    /**
     * 红包类型 .
     */
    private String redpackType;

    /**
     * 红包文字 .
     */
    private String redpackContent;

    /**
     * 红包总额 .
     */
    private Long redpackAmount;

    /**
     * 总红包个数 .
     */
    private Integer redpackCount;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 更新时间 .
     */
    private Date updateDate;

    /**
     * 状态 .
     */
    private String status;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * code .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * code .
     *
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * JOB_CODE .
     *
     */
    public String getJobCode() {
        return jobCode;
    }

    /**
     * JOB_CODE .
     *
     */
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
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
        this.merchantNo = merchantNo;
    }

    /**
     * 终端微信号 .
     *
     */
    public String getWxNoShop() {
        return wxNoShop;
    }

    /**
     * 终端微信号 .
     *
     */
    public void setWxNoShop(String wxNoShop) {
        this.wxNoShop = wxNoShop;
    }


    /**
     * 发红包导购号 .
     *
     */
    public String getSendRedpackGm() {
        return sendRedpackGm;
    }

    /**
     * 发红包导购号 .
     *
     */
    public void setSendRedpackGm(String sendRedpackGm) {
        this.sendRedpackGm = sendRedpackGm;
    }

    /**
     * 红包类型 .
     *
     */
    public String getRedpackType() {
        return redpackType;
    }

    /**
     * 红包类型 .
     *
     */
    public void setRedpackType(String redpackType) {
        this.redpackType = redpackType;
    }

    /**
     * 红包文字 .
     *
     */
    public String getRedpackContent() {
        return redpackContent;
    }

    /**
     * 红包文字 .
     *
     */
    public void setRedpackContent(String redpackContent) {
        this.redpackContent = redpackContent;
    }

    /**
     * 红包总额 .
     *
     */
    public Long getRedpackAmount() {
        return redpackAmount;
    }

    /**
     * 红包总额 .
     *
     */
    public void setRedpackAmount(Long redpackAmount) {
        this.redpackAmount = redpackAmount;
    }

    /**
     * 总红包个数 .
     *
     */
    public Integer getRedpackCount() {
        return redpackCount;
    }

    /**
     * 总红包个数 .
     *
     */
    public void setRedpackCount(Integer redpackCount) {
        this.redpackCount = redpackCount;
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
     * 状态 .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态 .
     *
     */
    public void setStatus(String status) {
        this.status = status;
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
        this.remark = remark;
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WxJobRedPackInfo [code=").append(code);
        builder.append(",jobCode=").append(jobCode); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",wxNoShop=").append(wxNoShop); 
        builder.append(",sendRedpackGm=").append(sendRedpackGm); 
        builder.append(",redpackType=").append(redpackType); 
        builder.append(",redpackContent=").append(redpackContent); 
        builder.append(",redpackAmount=").append(redpackAmount); 
        builder.append(",redpackCount=").append(redpackCount); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",updateDate=").append(updateDate); 
        builder.append(",status=").append(status); 
        builder.append(",remark=").append(remark); 
        builder.append("]");
        return builder.toString();
    }
}