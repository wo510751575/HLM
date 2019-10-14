package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Map;

public class MerchantDayOperateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1933691997082390043L;

	/**
     * 商户编号 .
     */
    private String merchantNo;
    
    /**
     * 商户名称 .
     */
    private String merchantName;
    
    /**
     * 门店数量 .
     */
    private int shopNum;
    
    /**
     * 店员数量 .
     */
    private int guidNum;
    
    /**
     * 今日销售额 .
     */
    private double daySale;
    
    /**
     * 门店数据 .
     */
    private Map<String, ShopDayOperateDto> shopData;
    
    /**
     * 查询日期 .
     */
    private String queryDate;

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

	public int getShopNum() {
		return shopNum;
	}

	public void setShopNum(int shopNum) {
		this.shopNum = shopNum;
	}

	public int getGuidNum() {
		return guidNum;
	}

	public void setGuidNum(int guidNum) {
		this.guidNum = guidNum;
	}

	public double getDaySale() {
		return daySale;
	}

	public void setDaySale(double daySale) {
		this.daySale = daySale;
	}

	public Map<String, ShopDayOperateDto> getShopData() {
		return shopData;
	}

	public void setShopData(Map<String, ShopDayOperateDto> shopDate) {
		this.shopData = shopDate;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	@Override
	public String toString() {
		return "MerchantDayOperateDto [merchantNo=" + merchantNo
				+ ", merchantName=" + merchantName + ", shopNum=" + shopNum
				+ ", guidNum=" + guidNum + ", daySale=" + daySale
				+ ", shopData=" + shopData + ", queryDate=" + queryDate + "]";
	}
}
