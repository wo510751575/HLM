package com.lj.business.st.dto.salesGmDayFirst;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class FindSalesGmDayFirst.
 */
public class FindSalesGmDayFirst implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -544378416905369080L; 
	
	/** The code. */
	private String code;
	
	/** The area code. */
	private String areaCode;
	
	/** The merchant no. */
	private String merchantNo;
	
	/** The day st. */
	private Date daySt;

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Gets the area code.
	 *
	 * @return the area code
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * Sets the area code.
	 *
	 * @param areaCode the new area code
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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
	 * @param merchantNo the new merchant no
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the day st.
	 *
	 * @return the day st
	 */
	public Date getDaySt() {
		return daySt;
	}

	/**
	 * Sets the day st.
	 *
	 * @param daySt the new day st
	 */
	public void setDaySt(Date daySt) {
		this.daySt = daySt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindSalesGmDayFirst [code=").append(code)
				.append(", areaCode=").append(areaCode).append(", merchantNo=")
				.append(merchantNo).append(", daySt=").append(daySt)
				.append("]");
		return builder.toString();
	}

}
