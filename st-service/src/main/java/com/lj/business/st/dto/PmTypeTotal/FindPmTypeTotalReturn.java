package com.lj.business.st.dto.PmTypeTotal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class FindPmTypeTotalReturn.
 */
public class FindPmTypeTotalReturn implements Serializable { 

     /** Generate cron. */
	private static final long serialVersionUID = 2107098670174957703L;
    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 分店名称 .
     */
    private String shopName;

    /**
     * 客户分类CODE .
     */
    private String pmTypeCode;

    /**
     * 客户分类名称 .
     */
    private String typeName;

    /**
     * 客户数量 .
     */
    private Integer numPm;

    /**
     * 客户占比 .
     */
    private String ratioPm;

    /**
     * 统计日期 .
     */
    private Date daySt;

    /**
     * 统计维度
             商户：MERCHANT
             区域：AREA
             门店：SHOP
             导购：GUID .
     */
    private String dimensionSt;
    
    /** 导购编号. */
    private String memberNoGm;

	/** 客户分类类型. */
	private String pmTypeType;
	
	

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FindPmTypeTotalReturn [merchantNo=" + merchantNo + ", shopNo="
				+ shopNo + ", shopName=" + shopName + ", pmTypeCode="
				+ pmTypeCode + ", typeName=" + typeName + ", numPm=" + numPm
				+ ", ratioPm=" + ratioPm + ", daySt=" + daySt
				+ ", dimensionSt=" + dimensionSt + ", memberNoGm=" + memberNoGm
				+ ", pmTypeType=" + pmTypeType + "]";
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
	 * Gets the shop no.
	 *
	 * @return the shop no
	 */
	public String getShopNo() {
		return shopNo;
	}

	/**
	 * Sets the shop no.
	 *
	 * @param shopNo the shop no
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	/**
	 * Gets the shop name.
	 *
	 * @return the shop name
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * Sets the shop name.
	 *
	 * @param shopName the shop name
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * Gets the pm type code.
	 *
	 * @return the pm type code
	 */
	public String getPmTypeCode() {
		return pmTypeCode;
	}

	/**
	 * Sets the pm type code.
	 *
	 * @param pmTypeCode the pm type code
	 */
	public void setPmTypeCode(String pmTypeCode) {
		this.pmTypeCode = pmTypeCode;
	}

	/**
	 * Gets the type name.
	 *
	 * @return the type name
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * Sets the type name.
	 *
	 * @param typeName the type name
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * Gets the num pm.
	 *
	 * @return the num pm
	 */
	public Integer getNumPm() {
		return numPm;
	}

	/**
	 * Sets the num pm.
	 *
	 * @param numPm the num pm
	 */
	public void setNumPm(Integer numPm) {
		this.numPm = numPm;
	}

	/**
	 * Gets the ratio pm.
	 *
	 * @return the ratio pm
	 */
	public String getRatioPm() {
		return ratioPm;
	}

	/**
	 * Sets the ratio pm.
	 *
	 * @param ratioPm the ratio pm
	 */
	public void setRatioPm(String ratioPm) {
		this.ratioPm = ratioPm;
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
	 * @param daySt the day st
	 */
	public void setDaySt(Date daySt) {
		this.daySt = daySt;
	}

	/**
	 * Gets the dimension st.
	 *
	 * @return the dimension st
	 */
	public String getDimensionSt() {
		return dimensionSt;
	}

	/**
	 * Sets the dimension st.
	 *
	 * @param dimensionSt the dimension st
	 */
	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	/**
	 * Gets the pm type type.
	 *
	 * @return the pm type type
	 */
	public String getPmTypeType() {
		return pmTypeType;
	}

	/**
	 * Sets the pm type type.
	 *
	 * @param pmTypeType the pm type type
	 */
	public void setPmTypeType(String pmTypeType) {
		this.pmTypeType = pmTypeType;
	}
}
