package com.lj.business.member.dto.terminallogfiles;

import java.util.Date;
import java.util.List;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindTerminalLogFilesPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 344878442329230382L; 

	/**
     * 商户编号 .
     */
    private String merchantNo;
    
    /**
     * 商户名称 .
     */
    private String merchantName;
    

    /**
     * 手机串号 .
     */
    private String imei;
    
    /**
     * 终端编码 .
     */
    private String terminalCode;
    
    /**
     * 终端集合
     */
    private List<String> shopNos;
    
    /** 开始时间. */
	private Date startTime;			
	
	/** 结束时间. */
	private Date endTime;

    /**
     * 日志文件名 .
     */
    private String logFileName;

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

	public List<String> getShopNos() {
		return shopNos;
	}

	public void setShopNos(List<String> shopNos) {
		this.shopNos = shopNos;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getLogFileName() {
		return logFileName;
	}

	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindTerminalLogFilesPage [merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", terminalCode=");
		builder.append(terminalCode);
		builder.append(", shopNos=");
		builder.append(shopNos);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", logFileName=");
		builder.append(logFileName);
		builder.append("]");
		return builder.toString();
	}
}
