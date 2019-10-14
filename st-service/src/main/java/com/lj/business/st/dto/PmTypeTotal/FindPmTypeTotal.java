package com.lj.business.st.dto.PmTypeTotal;

import java.io.Serializable;
import java.util.Date;

public class FindPmTypeTotal implements Serializable {

    /**
     * Generate cron.
     *
     * @param
     * @param
     * @throws
     */
    private static final long serialVersionUID = 2386193391093281955L;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 分店编号 .
     */
    private String shopNo;
    
    /**
     * 导购编号
     */
    private String memberNoGm;
    
	/**
	 * 统计维度 商户：MERCHANT 区域：AREA 门店：SHOP 导购：GUID .(必填)
	 */
	private String dimensionSt;

    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 结束日期
     */
    private String endDate;

	/**
	 * 客户分类类型
	 */
	private String pmTypeType;
	/**
	 * 區域code
	 */
	private String areaCode;
	/**
	 * 區域name
	 */
	private String areaName;
	/**
	 * 统计时间
	 */
	private Date daySt;
	
	  
	public Date getDaySt() {
		return daySt;
	}

	public void setDaySt(Date daySt) {
		this.daySt = daySt;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getDimensionSt() {
		return dimensionSt;
	}

	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPmTypeType() {
		return pmTypeType;
	}

	public void setPmTypeType(String pmTypeType) {
		this.pmTypeType = pmTypeType;
	}

	@Override
	public String toString() {
		return "FindPmTypeTotal [merchantNo=" + merchantNo + ", shopNo="
				+ shopNo + ", memberNoGm=" + memberNoGm + ", dimensionSt="
				+ dimensionSt + ", beginDate=" + beginDate + ", endDate="
				+ endDate + ", pmTypeType=" + pmTypeType + ", areaCode="
				+ areaCode + ", areaName=" + areaName + "]";
	}
	
}
