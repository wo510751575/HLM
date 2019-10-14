package com.lj.business.st.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class FindSalesGmDayFirstCompleteRateReturn.
 */
public class FindSalesGmDayFirstCompleteRateReturn implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4569223394869884704L;

    /**
     * 销售额 .
     */
    private Long numSale;

    /**
     * 销售目标 .
     */
    private Long numSaleTarget;
    
    /** 完成率. */
    private Double completeRate;

	/**
	 * Gets the num sale.
	 *
	 * @return the num sale
	 */
	public Long getNumSale() {
		return numSale;
	}

	/**
	 * Sets the num sale.
	 *
	 * @param numSale the new num sale
	 */
	public void setNumSale(Long numSale) {
		this.numSale = numSale;
	}

	/**
	 * Gets the num sale target.
	 *
	 * @return the num sale target
	 */
	public Long getNumSaleTarget() {
		return numSaleTarget;
	}

	/**
	 * Sets the num sale target.
	 *
	 * @param numSaleTarget the new num sale target
	 */
	public void setNumSaleTarget(Long numSaleTarget) {
		this.numSaleTarget = numSaleTarget;
	}

	/**
	 * Gets the complete rate.
	 *
	 * @return the complete rate
	 */
	public Double getCompleteRate() {
		return completeRate;
	}

	/**
	 * Sets the complete rate.
	 *
	 * @param completeRate the new complete rate
	 */
	public void setCompleteRate(Double completeRate) {
		this.completeRate = completeRate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindSalesGmDayFirstCompleteRateReturn [numSale=")
				.append(numSale).append(", numSaleTarget=")
				.append(numSaleTarget).append(", completeRate=")
				.append(completeRate).append("]");
		return builder.toString();
	}
    
}
