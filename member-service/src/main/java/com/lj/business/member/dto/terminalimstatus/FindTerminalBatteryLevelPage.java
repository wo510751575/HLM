package com.lj.business.member.dto.terminalimstatus;

import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

public class FindTerminalBatteryLevelPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8585926470216517205L; 

	/**
     * 商户编号 .
     */
    private String merchantNo;
    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 终端类型：GM导购、ZK中控 .
     */
    private String terminalType;
    
    /**
     * 终端编码
     */
    private String terminalCode;
    
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
    /**
     * 导购名称
     */
    private String memberNameGm;
    /**
     * 终端微信
     */
    private String noWx;
    
	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}


	public String getMemberNameGm() {
		return memberNameGm;
	}

	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

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

	/**
	 * @return the terminalCode
	 */
	public String getTerminalCode() {
		return terminalCode;
	}

	/**
	 * @param terminalCode the terminalCode to set
	 */
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
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
		return "FindTerminalBatteryLevelPage [merchantNo=" + merchantNo + ", merchantName=" + merchantName
				+ ", terminalType=" + terminalType + ", terminalCode=" + terminalCode + ", imei=" + imei
				+ ", onlineFlag=" + onlineFlag + ", shopNos=" + shopNos + ", memberNameGm=" + memberNameGm + ", noWx="
				+ noWx + "]";
	}
}
