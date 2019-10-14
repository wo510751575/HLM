package com.lj.business.st.dto;

import java.io.Serializable;

/**
 * The Class FindBgcIndex.
 */
public class FindBgcIndex implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1675182784796818319L;
	
	  /**
     * 商户编号(必填) .
     */
    private String merchantNo;
    
    private String shopNo;
    
    private String areaCode;
    
    private String dimensionSt;

	/**
	 * Gets the 商户编号(必填) .
	 *
	 * @return the 商户编号(必填) 
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号(必填) .
	 *
	 * @param merchantNo the new 商户编号(必填) 
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getDimensionSt() {
		return dimensionSt;
	}

	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	@Override
	public String toString() {
		return "FindBgcIndex [merchantNo=" + merchantNo + ", shopNo=" + shopNo
				+ ", areaCode=" + areaCode + ", dimensionSt=" + dimensionSt
				+ "]";
	}
	

}
