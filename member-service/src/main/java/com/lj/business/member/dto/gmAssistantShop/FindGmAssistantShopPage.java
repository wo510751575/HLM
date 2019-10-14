package com.lj.business.member.dto.gmAssistantShop;

import java.util.List;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindGmAssistantShopPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7780824462330207732L; 
	/**
     * CODE .
     */
    private String code;

    /**
     * 助手编号 .
     */
    private String assistantNo;

    /**
     * 助手名称 .
     */
    private String assistantName;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;
    /**
     * 终端编号
     */
    private String tidCode;
    
    private List<String> assistantNos;
    
    private String loginName;
    /**
     * 来源：下属微信
     */
    private boolean source;
    
    private String noWx;
    
	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public boolean isSource() {
		return source;
	}

	public void setSource(boolean source) {
		this.source = source;
	}
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getTidCode() {
		return tidCode;
	}

	public void setTidCode(String tidCode) {
		this.tidCode = tidCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAssistantNo() {
		return assistantNo;
	}

	public void setAssistantNo(String assistantNo) {
		this.assistantNo = assistantNo;
	}

	public String getAssistantName() {
		return assistantName;
	}

	public void setAssistantName(String assistantName) {
		this.assistantName = assistantName;
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

	
	public List<String> getAssistantNos() {
		return assistantNos;
	}

	public void setAssistantNos(List<String> assistantNos) {
		this.assistantNos = assistantNos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindGmAssistantShopPage [code=");
		builder.append(code);
		builder.append(", assistantNo=");
		builder.append(assistantNo);
		builder.append(", assistantName=");
		builder.append(assistantName);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", tidCode=");
		builder.append(tidCode);
		builder.append(", assistantNos=");
		builder.append(assistantNos);
		builder.append("]");
		return builder.toString();
	}

}
