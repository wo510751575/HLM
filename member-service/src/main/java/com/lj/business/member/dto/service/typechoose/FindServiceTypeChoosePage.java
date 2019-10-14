package com.lj.business.member.dto.service.typechoose;

import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * The Class FindServiceTypeChoosePage.
 */
public class FindServiceTypeChoosePage extends PageParamEntity {

	 /** Generate cron. */
	private static final long serialVersionUID = -3858551299850161011L; 
	
	/** 商户编号. */
	private String merchantNo;
	
	/**
	 * 服务名称
	 */
	private String serviceName;
	
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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	
	/**
	 * Gets the merchant no.
	 *
	 * @return the merchant no
	 */
	public String getMerchantNo() {
		return merchantNo;
	}
	
	/**
	 * Sets the merchant no.
	 *
	 * @param merchantNo the merchant no
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

}
