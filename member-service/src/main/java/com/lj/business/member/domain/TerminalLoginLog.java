package com.lj.business.member.domain;

import java.util.Date;

public class TerminalLoginLog {
    /**
     * CODE .
     */
    private String code;

    /**
     * 操作类型：0登录、1登出 .
     */
    private Integer optType;

    /**
     * 操作时间 .
     */
    private Date optTime;

    /**
     * 终端类型：GM导购、ZK中控 .
     */
    private String terminalType;

    /**
     * 终端编码 .
     */
    private String terminalCode;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberName;

    /**
     * 微信号 .
     */
    private String noWx;

    /**
     * 手机串号 .
     */
    private String imei;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

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
     * 操作类型：0登录、1登出 .
     *
     */
    public Integer getOptType() {
        return optType;
    }

    /**
     * 操作类型：0登录、1登出 .
     *
     */
    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    /**
     * 操作时间 .
     *
     */
    public Date getOptTime() {
        return optTime;
    }

    /**
     * 操作时间 .
     *
     */
    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    /**
     * 终端类型：GM导购、ZK中控 .
     *
     */
    public String getTerminalType() {
        return terminalType;
    }

    /**
     * 终端类型：GM导购、ZK中控 .
     *
     */
    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType == null ? null : terminalType.trim();
    }

    /**
     * 终端编码 .
     *
     */
    public String getTerminalCode() {
        return terminalCode;
    }

    /**
     * 终端编码 .
     *
     */
    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode == null ? null : terminalCode.trim();
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
    public String getMemberName() {
        return memberName;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 微信号 .
     *
     */
    public String getNoWx() {
        return noWx;
    }

    /**
     * 微信号 .
     *
     */
    public void setNoWx(String noWx) {
        this.noWx = noWx == null ? null : noWx.trim();
    }

    /**
     * 手机串号 .
     *
     */
    public String getImei() {
        return imei;
    }

    /**
     * 手机串号 .
     *
     */
    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TerminalLoginLog [code=").append(code);
        builder.append(",optType=").append(optType); 
        builder.append(",optTime=").append(optTime); 
        builder.append(",terminalType=").append(terminalType); 
        builder.append(",terminalCode=").append(terminalCode); 
        builder.append(",memberNoGm=").append(memberNoGm); 
        builder.append(",memberName=").append(memberName); 
        builder.append(",noWx=").append(noWx); 
        builder.append(",imei=").append(imei); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append("]");
        return builder.toString();
    }
}