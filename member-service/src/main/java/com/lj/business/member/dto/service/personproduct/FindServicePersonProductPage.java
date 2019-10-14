package com.lj.business.member.dto.service.personproduct;

import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * The Class FindPhoneInfoPage.
 */
public class FindServicePersonProductPage extends PageParamEntity { 

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8454748306183872354L;
	/**
     * 商户编号 .
     */
    private String merchantNo;
    /**
     * 人员CODE .
     */
    private String personNo;
    /**
     * 人员姓名 .
     */
    private String personName;
    /**
     * 终端CODE .
     */
    
    /**
     * 终端名称 .
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

	public String getPersonNo() {
		return personNo;
	}

	public void setPersonNo(String personNo) {
		this.personNo = personNo;
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
