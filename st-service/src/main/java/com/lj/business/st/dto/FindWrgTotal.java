package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FindWrgTotal.
 */
public class FindWrgTotal implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5948703841493233776L;

	/**
     * 商户编号(必填) .
     */
    private String merchantNo;

    /**
     * 区域CODE .
     */
    private String areaCode;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 导购编号  .
     */
    private String memberNoGm;
    
    /**
     * 统计维度(必填)
     * 商户：MERCHANT
     * 区域：AREA
     * 门店：SHOP
     * 导购：GUID .
     */
    private String dimensionSt;

    /** 开始日期(必填). */
    private Date beginDate;

    /** 结束日期(必填). */
    private Date endDate;

    /**
     * 查询类型(必填)
     * 最勤快奖：QING_KUAI
     * 客户最多：KE_HU_ZUI_DUO
     * 新增客户最多：XIN_ZENG_ZUI_DUO
     * 最牛销售：ZUI_NIU_XIAO_SHOU
     * 跟进效果突出：GEN_JIN_TU_CHU
     * 活动传播：HUO_DONG_CHUAN_BO
     *  .
     */
    private String flag;
    
    /**
     * 排序  .
     */
    private String orderBy;
    
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
	 * Gets the 统计维度(必填) 商户：MERCHANT 区域：AREA 门店：SHOP 导购：GUID .
	 *
	 * @return the 统计维度(必填) 商户：MERCHANT 区域：AREA 门店：SHOP 导购：GUID 
	 */
	public String getDimensionSt() {
		return dimensionSt;
	}

	/**
	 * Sets the 统计维度(必填) 商户：MERCHANT 区域：AREA 门店：SHOP 导购：GUID .
	 *
	 * @param dimensionSt the new 统计维度(必填) 商户：MERCHANT 区域：AREA 门店：SHOP 导购：GUID 
	 */
	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	/**
	 * Gets the 开始日期(必填).
	 *
	 * @return the 开始日期(必填)
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * Sets the 开始日期(必填).
	 *
	 * @param beginDate the new 开始日期(必填)
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * Gets the 结束日期(必填).
	 *
	 * @return the 结束日期(必填)
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the 结束日期(必填).
	 *
	 * @param endDate the new 结束日期(必填)
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWrgTotal [merchantNo=").append(merchantNo)
				.append(", areaCode=").append(areaCode).append(", shopNo=")
				.append(shopNo).append(", memberNoGm=").append(memberNoGm)
				.append(", dimensionSt=").append(dimensionSt)
				.append(", beginDate=").append(beginDate).append(", endDate=")
				.append(endDate).append(", flag=").append(flag)
				.append(", orderBy=").append(orderBy).append("]");
		return builder.toString();
	}

	/**
	 * Gets the 查询类型(必填) 最勤快奖：QING_KUAI 客户最多：KE_HU_ZUI_DUO 新增客户最多：XIN_ZENG_ZUI_DUO 最牛销售：ZUI_NIU_XIAO_SHOU 跟进效果突出：GEN_JIN_TU_CHU 活动传播：HUO_DONG_CHUAN_BO .
	 *
	 * @return the 查询类型(必填) 最勤快奖：QING_KUAI 客户最多：KE_HU_ZUI_DUO 新增客户最多：XIN_ZENG_ZUI_DUO 最牛销售：ZUI_NIU_XIAO_SHOU 跟进效果突出：GEN_JIN_TU_CHU 活动传播：HUO_DONG_CHUAN_BO 
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * Sets the 查询类型(必填) 最勤快奖：QING_KUAI 客户最多：KE_HU_ZUI_DUO 新增客户最多：XIN_ZENG_ZUI_DUO 最牛销售：ZUI_NIU_XIAO_SHOU 跟进效果突出：GEN_JIN_TU_CHU 活动传播：HUO_DONG_CHUAN_BO .
	 *
	 * @param flag the new 查询类型(必填) 最勤快奖：QING_KUAI 客户最多：KE_HU_ZUI_DUO 新增客户最多：XIN_ZENG_ZUI_DUO 最牛销售：ZUI_NIU_XIAO_SHOU 跟进效果突出：GEN_JIN_TU_CHU 活动传播：HUO_DONG_CHUAN_BO 
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * Gets the 排序  .
	 *
	 * @return the 排序  
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * Sets the 排序  .
	 *
	 * @param orderBy the new 排序  
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
    

}
