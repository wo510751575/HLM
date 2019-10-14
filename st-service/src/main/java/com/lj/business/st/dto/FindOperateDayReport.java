package com.lj.business.st.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc

/**
 * The Class FindWorkDayGmIndex.
 */
public class FindOperateDayReport implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -1703398676249322203L;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 分店编号
     */
    private String shopNo;
    
    /**
     * 区域编号
     */
    private String areaCode;

    /**
     * 导购编号
     */
    private String memberNoGm;

    /**
     * 开始时间
     */
    private String beginDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 维度
     */
    private String dimensionSt;

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

    public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getMemberNoGm() {
        return memberNoGm;
    }

    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm;
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

    public String getDimensionSt() {
        return dimensionSt;
    }

    public void setDimensionSt(String dimensionSt) {
        this.dimensionSt = dimensionSt;
    }

	@Override
	public String toString() {
		return "FindOperateDayReport [merchantNo=" + merchantNo + ", shopNo="
				+ shopNo + ", areaCode=" + areaCode + ", memberNoGm="
				+ memberNoGm + ", beginDate=" + beginDate + ", endDate="
				+ endDate + ", dimensionSt=" + dimensionSt + "]";
	}
}
