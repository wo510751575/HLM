package com.lj.business.st.dto.MaterialTotal;

import java.io.Serializable;
import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The Class FindMaterialTotalReturn.
 */
public class FindMaterialTotalReturn implements Serializable { 

     /** Generate cron. */
	private static final long serialVersionUID = -6311870921764630266L;

    /**
     * 回应数量 .
     */
    private Integer respondNum;

    /**
     * 回应所占比例 .
     */
    private Double ratioRespond;

    /**
     * 类型ID .
     */
    private String materialTypeCode;

    /**
     * 类型名称 .
     */
    private String materialTypeName;
    
    /** 导购编号. */
    private String memberNoGm;
    
    /** 商户编号. */
    private String merchantNo;
    

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
	 * Gets the respond num.
	 *
	 * @return the respond num
	 */
	public Integer getRespondNum() {
		return respondNum;
	}

	/**
	 * Sets the respond num.
	 *
	 * @param respondNum the respond num
	 */
	public void setRespondNum(Integer respondNum) {
		this.respondNum = respondNum;
	}

	/**
	 * Gets the ratio respond.
	 *
	 * @return the ratio respond
	 */
	public Double getRatioRespond() {
		return ratioRespond;
	}

	/**
	 * Sets the ratio respond.
	 *
	 * @param ratioRespond the ratio respond
	 */
	public void setRatioRespond(Double ratioRespond) {
		this.ratioRespond = ratioRespond;
	}

	/**
	 * Gets the material type code.
	 *
	 * @return the material type code
	 */
	public String getMaterialTypeCode() {
		return materialTypeCode;
	}

	/**
	 * Sets the material type code.
	 *
	 * @param materialTypeCode the material type code
	 */
	public void setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
	}

	/**
	 * Gets the material type name.
	 *
	 * @return the material type name
	 */
	public String getMaterialTypeName() {
		return materialTypeName;
	}

	/**
	 * Sets the material type name.
	 *
	 * @param materialTypeName the material type name
	 */
	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FindMaterialTotalReturn [respondNum=" + respondNum
				+ ", ratioRespond=" + ratioRespond + ", materialTypeCode="
				+ materialTypeCode + ", materialTypeName=" + materialTypeName
				+ ", memberNoGm=" + memberNoGm + ", merchantNo=" + merchantNo
				+ "]";
	}
}
