package com.lj.business.st.dto.WorkRatioShop;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FindExcellentShop.
 */
public class FindExcellentShop implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6527441148813381818L;

	
	 /**
     * 商户编号(必填) .
     */
    private String merchantNo;
    
    /**
     * 区域CODE .
     */
    private String areaCode;
    
    /**
     * 市CODE .
     */
    private String cityCode;
    
    /**
     * 门店CODE .
     */
    private String shopNo;
    
    /**
     * 统计日期 ，默认昨天.
     */
    private Date daySt;
    
    
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


	/**
	 * Gets the 区域CODE .
	 *
	 * @return the 区域CODE 
	 */
	public String getAreaCode() {
		return areaCode;
	}


	/**
	 * Sets the 区域CODE .
	 *
	 * @param areaCode the new 区域CODE 
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


	/**
	 * Gets the 市CODE .
	 *
	 * @return the 市CODE 
	 */
	public String getCityCode() {
		return cityCode;
	}


	/**
	 * Sets the 市CODE .
	 *
	 * @param cityCode the new 市CODE 
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}


	public String getShopNo() {
		return shopNo;
	}


	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}


	/**
	 * Gets the 统计日期 ，默认昨天.
	 *
	 * @return the 统计日期 ，默认昨天
	 */
	public Date getDaySt() {
		return daySt;
	}


	/**
	 * Sets the 统计日期 ，默认昨天.
	 *
	 * @param daySt the new 统计日期 ，默认昨天
	 */
	public void setDaySt(Date daySt) {
		this.daySt = daySt;
	}
	

	public String getDimensionSt() {
		return dimensionSt;
	}


	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindExcellentShop [merchantNo=").append(merchantNo)
				.append(", areaCode=").append(areaCode).append(", cityCode=")
				.append(cityCode).append(", shopNo=").append(shopNo)
				.append(", daySt=").append(daySt).append(", dimensionSt=")
				.append(dimensionSt).append("]");
		return builder.toString();
	}


}
