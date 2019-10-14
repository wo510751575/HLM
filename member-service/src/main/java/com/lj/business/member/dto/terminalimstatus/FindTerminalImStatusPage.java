package com.lj.business.member.dto.terminalimstatus;

import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

public class FindTerminalImStatusPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8585926470216517205L; 

	/**
     * 商户编号 .
     */
    private String merchantNo;


    /**
     * 终端类型：GM导购、ZK中控 .
     */
    private String terminalType;
    
    /**
     * 手机串号 .
     */
    private String imei;

    /**
     * 是否在线：0离线、1在线 .
     */
    private Integer onlineFlag;
    /**
     * 终端集合
     */
    private List<String> shopNos;
    

	public List<String> getShopNos() {
		return shopNos;
	}

	public void setShopNos(List<String> shopNos) {
		this.shopNos = shopNos;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}


	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Integer getOnlineFlag() {
		return onlineFlag;
	}

	public void setOnlineFlag(Integer onlineFlag) {
		this.onlineFlag = onlineFlag;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindTerminalImStatusPage [merchantNo=");
		builder.append(merchantNo);
		builder.append(", terminalType=");
		builder.append(terminalType);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", onlineFlag=");
		builder.append(onlineFlag);
		builder.append("]");
		return builder.toString();
	}
    
}
