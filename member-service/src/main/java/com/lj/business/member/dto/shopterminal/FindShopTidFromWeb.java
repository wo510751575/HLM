/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.shopterminal;

import java.io.Serializable;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月1日
 */
public class FindShopTidFromWeb implements Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = -8245666412805437185L;
	
	/**
	 * 导购助手所属商户编号，非空
	 */
	private String merchantNo;
	
	/**
	 * 导购助手编号，非空
	 */
	private String assistantNo;

	/**
	 * 搜索关键字：微信名称、导购名称
	 */
	private String searchWords;
	
	/**
	 * 客户分组编码
	 */
	private String pmTypeCode;

    /**
     * 省CODE .
     */
    private String provinceCode;

    /**
     * 市CODE .
     */
    private String cityCode;

    /**
     * 区CODE .
     */
    private String cityAreaCode;
    
    /**
     * 是否查询终端在线状态，默认为不查询
     */
    private boolean queryOnlineBool;

	/**
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * @return the assistantNo
	 */
	public String getAssistantNo() {
		return assistantNo;
	}

	/**
	 * @param assistantNo the assistantNo to set
	 */
	public void setAssistantNo(String assistantNo) {
		this.assistantNo = assistantNo;
	}

	/**
	 * @return the searchWords
	 */
	public String getSearchWords() {
		return searchWords;
	}

	/**
	 * @param searchWords the searchWords to set
	 */
	public void setSearchWords(String searchWords) {
		this.searchWords = searchWords;
	}

	/**
	 * @return the pmTypeCode
	 */
	public String getPmTypeCode() {
		return pmTypeCode;
	}

	/**
	 * @param pmTypeCode the pmTypeCode to set
	 */
	public void setPmTypeCode(String pmTypeCode) {
		this.pmTypeCode = pmTypeCode;
	}

	/**
	 * @return the provinceCode
	 */
	public String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * @param provinceCode the provinceCode to set
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the cityAreaCode
	 */
	public String getCityAreaCode() {
		return cityAreaCode;
	}

	/**
	 * @param cityAreaCode the cityAreaCode to set
	 */
	public void setCityAreaCode(String cityAreaCode) {
		this.cityAreaCode = cityAreaCode;
	}

	/**
	 * @return the queryOnlineBool
	 */
	public boolean isQueryOnlineBool() {
		return queryOnlineBool;
	}

	/**
	 * @param queryOnlineBool the queryOnlineBool to set
	 */
	public void setQueryOnlineBool(boolean queryOnlineBool) {
		this.queryOnlineBool = queryOnlineBool;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindShopTidFromWeb [merchantNo=");
		builder.append(merchantNo);
		builder.append(", assistantNo=");
		builder.append(assistantNo);
		builder.append(", searchWords=");
		builder.append(searchWords);
		builder.append(", pmTypeCode=");
		builder.append(pmTypeCode);
		builder.append(", provinceCode=");
		builder.append(provinceCode);
		builder.append(", cityCode=");
		builder.append(cityCode);
		builder.append(", cityAreaCode=");
		builder.append(cityAreaCode);
		builder.append(", queryOnlineBool=");
		builder.append(queryOnlineBool);
		builder.append("]");
		return builder.toString();
	}
}
