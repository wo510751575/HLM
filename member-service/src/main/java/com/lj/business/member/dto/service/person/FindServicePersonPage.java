package com.lj.business.member.dto.service.person;

import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * The Class FindPhoneInfoPage.
 */
public class FindServicePersonPage extends PageParamEntity { 

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -892932051970194049L;

	/**
     * 商户编号 .
     */
    private String merchantNo;
    /**
     * 人员姓名 .
     */
    private String personName;
    /**
     * 分店CODE .
     */
    
    /**
     * 分店名称 .
     */
    
    /**
     * 终端编号数组
     */
    private List<String> shopNos;
    

	public List<String> getShopNos() {
		return shopNos;
	}

	public void setShopNos(List<String> shopNos) {
		this.shopNos = shopNos;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
    
}
