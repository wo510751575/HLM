package com.lj.business.member.dto.gmAssistantShop;

import java.io.Serializable;
import java.util.Arrays;

public class FindGmAssistantShop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5801131844285622185L; 

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
     * 微信号
     */
    private String noWx;

	/**
     * tidCode .
     */
    private String tidCode;
    
    
    /**
     * 是否过滤掉没有添加终端的终端，默认为不过滤
     */
    private boolean filterNoTerminal;
    
    /**
     * 登錄名稱
     */
    private String loginName;
    /**
     * 登录名称，多个已拼接单引号
     */
    private String[] loginNames;
    /**
     * 来源：下属微信
     */
    private Boolean source;
    
    private String searchVal;
	public String getSearchVal() {
		return searchVal;
	}

	public void setSearchVal(String searchVal) {
		this.searchVal = searchVal;
	}

	public Boolean getSource() {
		return source;
	}

	public void setSource(Boolean source) {
		this.source = source;
	}

	public String[] getLoginNames() {
		return loginNames;
	}

	public void setLoginNames(String[] loginNames) {
		this.loginNames = loginNames;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
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


	public String getTidCode() {
		return tidCode;
	}

	public void setTidCode(String tidCode) {
		this.tidCode = tidCode;
	}
	
	
	public boolean isFilterNoTerminal() {
		return filterNoTerminal;
	}

	public void setFilterNoTerminal(boolean filterNoTerminal) {
		this.filterNoTerminal = filterNoTerminal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindGmAssistantShop [code=");
		builder.append(code);
		builder.append(", assistantNo=");
		builder.append(assistantNo);
		builder.append(", assistantName=");
		builder.append(assistantName);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", tidCode=");
		builder.append(tidCode);
		builder.append(", filterNoTerminal=");
		builder.append(filterNoTerminal);
		builder.append(", loginName=");
		builder.append(loginName);
		builder.append(", loginNames=");
		builder.append(Arrays.toString(loginNames));
		builder.append(", source=");
		builder.append(source);
		builder.append("]");
		return builder.toString();
	}

}
