package com.lj.business.member.domain;

import java.util.Date;

public class TerminalLogFiles {
    /**
     * CODE .
     */
    private String code;

    /**
     * 手机串号 .
     */
    private String imei;

    /**
     * 终端编码 .
     */
    private String terminalCode;

    /**
     * 日志开始时间 .
     */
    private Date logBeginTime;

    /**
     * 日志文件名称 .
     */
    private String logFileName;

    /**
     * 日志文件地址 .
     */
    private String logAddr;

    /**
     * 日志上传时间 .
     */
    private Date uploadTime;

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
     * 日志开始时间 .
     *
     */
    public Date getLogBeginTime() {
        return logBeginTime;
    }

    /**
     * 日志开始时间 .
     *
     */
    public void setLogBeginTime(Date logBeginTime) {
        this.logBeginTime = logBeginTime;
    }

    /**
     * 日志文件名称 .
     *
     */
    public String getLogFileName() {
        return logFileName;
    }

    /**
     * 日志文件名称 .
     *
     */
    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName == null ? null : logFileName.trim();
    }

    /**
     * 日志文件地址 .
     *
     */
    public String getLogAddr() {
        return logAddr;
    }

    /**
     * 日志文件地址 .
     *
     */
    public void setLogAddr(String logAddr) {
        this.logAddr = logAddr == null ? null : logAddr.trim();
    }

    /**
     * 日志上传时间 .
     *
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * 日志上传时间 .
     *
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
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
        builder.append("TerminalLogFiles [code=").append(code);
        builder.append(",imei=").append(imei); 
        builder.append(",terminalCode=").append(terminalCode); 
        builder.append(",logBeginTime=").append(logBeginTime); 
        builder.append(",logFileName=").append(logFileName); 
        builder.append(",logAddr=").append(logAddr); 
        builder.append(",uploadTime=").append(uploadTime); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}