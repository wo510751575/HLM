package com.lj.business.member.dto;

import com.lj.base.core.pagination.PageParamEntity; 

/**
 * The Class FindMerchantPage.
 */
public class FindMerchantPage extends PageParamEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 246245244254900774L; 

	
	
	/** The merchant no. */
	private String merchantNo;

	/** The merchant name. */
	private String merchantName;
	
	/**
	 * 产品类型
	 */
    private String productType;
    
    /**
     * 电商状态 
     */
    private String eshopStatus;

	public String getProductType() {
		return productType;
	}



	public void setProductType(String productType) {
		this.productType = productType;
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



	public String getMerchantName() {
		return merchantName;
	}



	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}



	/**
	 * @return the eshopStatus
	 */
	public String getEshopStatus() {
		return eshopStatus;
	}



	/**
	 * @param eshopStatus the eshopStatus to set
	 */
	public void setEshopStatus(String eshopStatus) {
		this.eshopStatus = eshopStatus;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindMerchantPage [merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", productType=");
		builder.append(productType);
		builder.append(", eshopStatus=");
		builder.append(eshopStatus);
		builder.append("]");
		return builder.toString();
	}

}
