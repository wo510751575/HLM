/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * 
 * 类说明：统计终端客户情况
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年6月13日
 */
public class PersonMemberStsGroupByShop implements Serializable {

	private static final long serialVersionUID = 2858384564414347069L;

	/**
     * 分店编号 .
     */
    
    
    /**
     * 分店名称 .
     */
    
    
    /** 终端代码. */
    private String shopNoMerchant;
    
    /**
     * 经销商代码
     */
    private String dealerCode;

    /**
     * 经销商编号 .
     */
    private String companyNo;

    /**
     * 经销商名称 .
     */
    private String companyName;
	
	/**
	 * 客户总数
	 */
	private int totalCount;
	
	/**
	 * 今日新增客户数
	 */
	private int todayCount;

	/**
	 * @return the shopNoMerchant
	 */
	public String getShopNoMerchant() {
		return shopNoMerchant;
	}

	/**
	 * @param shopNoMerchant the shopNoMerchant to set
	 */
	public void setShopNoMerchant(String shopNoMerchant) {
		this.shopNoMerchant = shopNoMerchant;
	}

	/**
	 * @return the dealerCode
	 */
	public String getDealerCode() {
		return dealerCode;
	}

	/**
	 * @param dealerCode the dealerCode to set
	 */
	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	/**
	 * @return the companyNo
	 */
	public String getCompanyNo() {
		return companyNo;
	}

	/**
	 * @param companyNo the companyNo to set
	 */
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the todayCount
	 */
	public int getTodayCount() {
		return todayCount;
	}

	/**
	 * @param todayCount the todayCount to set
	 */
	public void setTodayCount(int todayCount) {
		this.todayCount = todayCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonMemberStsGroupByShop [");
		builder.append("shopNoMerchant=");
		builder.append(shopNoMerchant);
		builder.append(", dealerCode=");
		builder.append(dealerCode);
		builder.append(", companyNo=");
		builder.append(companyNo);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", totalCount=");
		builder.append(totalCount);
		builder.append(", todayCount=");
		builder.append(todayCount);
		builder.append("]");
		return builder.toString();
	}

}
