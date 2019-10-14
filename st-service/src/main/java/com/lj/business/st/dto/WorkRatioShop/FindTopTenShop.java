package com.lj.business.st.dto.WorkRatioShop;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FindTopTenShop.
 */
public class FindTopTenShop implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2013763872277294809L;
	
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


    /** 统计维度 商户：MERCHANT 区域：AREA 市：CITY. */
    //private String dimensionSt;
    
    /**
     * 统计日期 ，默认昨天.
     */
    private Date daySt;

	/**
	 * Gets the 商户编号 .
	 *
	 * @return the 商户编号 
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号 .
	 *
	 * @param merchantNo the new 商户编号 
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

	/**
	 * Gets the 统计日期 .
	 *
	 * @return the 统计日期 
	 */
	public Date getDaySt() {
		return daySt;
	}

	/**
	 * Sets the 统计日期 .
	 *
	 * @param daySt the new 统计日期 
	 */
	public void setDaySt(Date daySt) {
		this.daySt = daySt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindTopTenShop [merchantNo=").append(merchantNo)
				.append(", areaCode=").append(areaCode).append(", cityCode=")
				.append(cityCode).append(", daySt=").append(daySt).append("]");
		return builder.toString();
	}

    

}
