package com.lj.business.member.dto.terminallogfiles;

import java.io.Serializable;
import java.util.Date;

public class AddTerminalLogFiles implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 9153893384703095897L;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public Date getLogBeginTime() {
		return logBeginTime;
	}

	public void setLogBeginTime(Date logBeginTime) {
		this.logBeginTime = logBeginTime;
	}

	public String getLogFileName() {
		return logFileName;
	}

	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}

	public String getLogAddr() {
		return logAddr;
	}

	public void setLogAddr(String logAddr) {
		this.logAddr = logAddr;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
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


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddTerminalLogFiles [code=");
		builder.append(code);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", terminalCode=");
		builder.append(terminalCode);
		builder.append(", logBeginTime=");
		builder.append(logBeginTime);
		builder.append(", logFileName=");
		builder.append(logFileName);
		builder.append(", logAddr=");
		builder.append(logAddr);
		builder.append(", uploadTime=");
		builder.append(uploadTime);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}
    
}
