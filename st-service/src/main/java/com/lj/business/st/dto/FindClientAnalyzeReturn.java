package com.lj.business.st.dto;

import java.io.Serializable;
import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * 类说明：客户画像
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 * 
 * CreateDate: 2017年7月31日
 */
public class FindClientAnalyzeReturn implements Serializable { 

     /** Generate cron. */
	private static final long serialVersionUID = -3938336456519886415L;

   /** 男性比例. */
	private Double ratioMale;
	
	/** 女性比例. */
    private Double ratioFemale;
    
    /** 客户总量. */
    private Long numPm;
    
    /** 男性客户数量. */
    private BigDecimal numMale;
    
    /** 女性客户数量. */
    private BigDecimal numFemale;
    
    /** 未知性别客户数量. */
    private BigDecimal numUnKnown;
    
    /**商户编号*/
    private String merchantNo;
    
	/**
	 * @return the numUnKnown
	 */
	public BigDecimal getNumUnKnown() {
		return numUnKnown;
	}

	/**
	 * @param numUnKnown the numUnKnown to set
	 */
	public void setNumUnKnown(BigDecimal numUnKnown) {
		this.numUnKnown = numUnKnown;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the num male.
	 *
	 * @return the num male
	 */
	public BigDecimal getNumMale() {
		return numMale;
	}

	/**
	 * Sets the num male.
	 *
	 * @param bigDecimal the num male
	 */
	public void setNumMale(BigDecimal bigDecimal) {
		this.numMale = bigDecimal;
	}

	/**
	 * Gets the num female.
	 *
	 * @return the num female
	 */
	public BigDecimal getNumFemale() {
		return numFemale;
	}

	/**
	 * Sets the num female.
	 *
	 * @param numFemale the num female
	 */
	public void setNumFemale(BigDecimal numFemale) {
		this.numFemale = numFemale;
	}

	/**
	 * Gets the ratio male.
	 *
	 * @return the ratio male
	 */
	public Double getRatioMale() {
		return ratioMale;
	}
	
	/**
	 * Sets the ratio male.
	 *
	 * @param ratioMale the ratio male
	 */
	public void setRatioMale(Double ratioMale) {
		this.ratioMale = ratioMale;
	}
	
	/**
	 * Gets the ratio female.
	 *
	 * @return the ratio female
	 */
	public Double getRatioFemale() {
		return ratioFemale;
	}
	
	/**
	 * Sets the ratio female.
	 *
	 * @param ratioFemale the ratio female
	 */
	public void setRatioFemale(Double ratioFemale) {
		this.ratioFemale = ratioFemale;
	}
	
	/**
	 * Gets the num pm.
	 *
	 * @return the num pm
	 */
	public Long getNumPm() {
		return numPm;
	}
	
	/**
	 * Sets the num pm.
	 *
	 * @param numPm the num pm
	 */
	public void setNumPm(Long numPm) {
		this.numPm = numPm;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindClientAnalyzeReturn [ratioMale=");
		builder.append(ratioMale);
		builder.append(", ratioFemale=");
		builder.append(ratioFemale);
		builder.append(", numPm=");
		builder.append(numPm);
		builder.append(", numMale=");
		builder.append(numMale);
		builder.append(", numFemale=");
		builder.append(numFemale);
		builder.append(", numUnKnown=");
		builder.append(numUnKnown);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}
    
    
    
    
}
