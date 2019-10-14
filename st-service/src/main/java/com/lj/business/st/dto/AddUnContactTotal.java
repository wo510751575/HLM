package com.lj.business.st.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class AddUnContactTotal.
 */
public class AddUnContactTotal implements Serializable { 

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5364934481612292253L;

	/**
     * CODE .
     */
    private String code;

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
     * 导购编号  .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 1周到1月未联系 .
     */
    private Integer yizhouYiyue;

    /**
     * 1月到3月未联系 .
     */
    private Integer yiyueSanyue;

    /**
     * 3月到6月未联系 .
     */
    private Integer sanyueLiuyue;

    /**
     * 6月以上未联系 .
     */
    private Integer liuyueEnd;
    
    /** 统计维度. */
    private String dimensionSt;

	/**
	 * Gets the cODE .
	 *
	 * @return the cODE 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the cODE .
	 *
	 * @param code the new cODE 
	 */
	public void setCode(String code) {
		this.code = code;
	}

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
	 * Gets the 导购编号  .
	 *
	 * @return the 导购编号  
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the 导购编号  .
	 *
	 * @param memberNoGm the new 导购编号  
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the 导购姓名 .
	 *
	 * @return the 导购姓名 
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}

	/**
	 * Sets the 导购姓名 .
	 *
	 * @param memberNameGm the new 导购姓名 
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	/**
	 * Gets the 1周到1月未联系 .
	 *
	 * @return the 1周到1月未联系 
	 */
	public Integer getYizhouYiyue() {
		return yizhouYiyue;
	}

	/**
	 * Sets the 1周到1月未联系 .
	 *
	 * @param yizhouYiyue the new 1周到1月未联系 
	 */
	public void setYizhouYiyue(Integer yizhouYiyue) {
		this.yizhouYiyue = yizhouYiyue;
	}

	/**
	 * Gets the 1月到3月未联系 .
	 *
	 * @return the 1月到3月未联系 
	 */
	public Integer getYiyueSanyue() {
		return yiyueSanyue;
	}

	/**
	 * Sets the 1月到3月未联系 .
	 *
	 * @param yiyueSanyue the new 1月到3月未联系 
	 */
	public void setYiyueSanyue(Integer yiyueSanyue) {
		this.yiyueSanyue = yiyueSanyue;
	}

	/**
	 * Gets the 3月到6月未联系 .
	 *
	 * @return the 3月到6月未联系 
	 */
	public Integer getSanyueLiuyue() {
		return sanyueLiuyue;
	}

	/**
	 * Sets the 3月到6月未联系 .
	 *
	 * @param sanyueLiuyue the new 3月到6月未联系 
	 */
	public void setSanyueLiuyue(Integer sanyueLiuyue) {
		this.sanyueLiuyue = sanyueLiuyue;
	}

	/**
	 * Gets the 6月以上未联系 .
	 *
	 * @return the 6月以上未联系 
	 */
	public Integer getLiuyueEnd() {
		return liuyueEnd;
	}

	/**
	 * Sets the 6月以上未联系 .
	 *
	 * @param liuyueEnd the new 6月以上未联系 
	 */
	public void setLiuyueEnd(Integer liuyueEnd) {
		this.liuyueEnd = liuyueEnd;
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
	 * @param dimensionSt the new dimension st
	 */
	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	/**
	 * Gets the 分店编号 .
	 *
	 * @return the 分店编号 
	 */
	public String getShopNo() {
		return shopNo;
	}

	/**
	 * Sets the 分店编号 .
	 *
	 * @param shopNo the new 分店编号 
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	/**
	 * Gets the 分店名称 .
	 *
	 * @return the 分店名称 
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * Sets the 分店名称 .
	 *
	 * @param shopName the new 分店名称 
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddUnContactTotal [code=").append(code)
				.append(", merchantNo=").append(merchantNo).append(", shopNo=")
				.append(shopNo).append(", shopName=").append(shopName)
				.append(", memberNoGm=").append(memberNoGm)
				.append(", memberNameGm=").append(memberNameGm)
				.append(", yizhouYiyue=").append(yizhouYiyue)
				.append(", yiyueSanyue=").append(yiyueSanyue)
				.append(", sanyueLiuyue=").append(sanyueLiuyue)
				.append(", liuyueEnd=").append(liuyueEnd)
				.append(", dimensionSt=").append(dimensionSt).append("]");
		return builder.toString();
	}
	
}
