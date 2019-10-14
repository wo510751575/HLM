package com.lj.business.st.dto.PmLabelTotal;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class FindPmLabelTotalReturnDto.
 */
public class FindPmLabelTotalReturnDto implements Serializable { 

     /** Generate cron. */
	private static final long serialVersionUID = -1150522238170178895L;

	   /**
     * 标签id .
     */
    private String labelId;

    /**
     * 标签名称 .
     */
    private String labelName;

    /**
     * 回应数量 .
     */
    private Integer pmNum;

    /**
     * 客户所占比例 .
     */
    private Double ratioPm;
    
    /** 商户编号. */
    private String merchantNo;
    
    /** 导购编号. */
    private String memberNoGm;
    
    

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

	/**
	 * Gets the member no gm.
	 *
	 * @return the member no gm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the member no gm.
	 *
	 * @param memberNoGm the member no gm
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the label id.
	 *
	 * @return the label id
	 */
	public String getLabelId() {
		return labelId;
	}

	/**
	 * Sets the label id.
	 *
	 * @param labelId the label id
	 */
	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	/**
	 * Gets the label name.
	 *
	 * @return the label name
	 */
	public String getLabelName() {
		return labelName;
	}

	/**
	 * Sets the label name.
	 *
	 * @param labelName the label name
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * Gets the pm num.
	 *
	 * @return the pm num
	 */
	public Integer getPmNum() {
		return pmNum;
	}

	/**
	 * Sets the pm num.
	 *
	 * @param pmNum the pm num
	 */
	public void setPmNum(Integer pmNum) {
		this.pmNum = pmNum;
	}

	/**
	 * Gets the ratio pm.
	 *
	 * @return the ratio pm
	 */
	public Double getRatioPm() {
		return ratioPm;
	}

	/**
	 * Sets the ratio pm.
	 *
	 * @param ratioPm the ratio pm
	 */
	public void setRatioPm(Double ratioPm) {
		this.ratioPm = ratioPm;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FindPmLabelTotalReturnDto [labelId=" + labelId + ", labelName="
				+ labelName + ", pmNum=" + pmNum + ", ratioPm=" + ratioPm
				+ ", merchantNo=" + merchantNo + ", memberNoGm=" + memberNoGm
				+ "]";
	}
}
