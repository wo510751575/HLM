package com.lj.business.st.dto.PmTalkTotal;

import java.io.Serializable;
import java.util.Date;

public class FindPmTalkTotal implements Serializable {

    /**
     * Generate cron.
     *
     * @param
     * @param
     * @throws
     */
    private static final long serialVersionUID = -4122623716699263937L;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 导购编号  .
     */
    private String memberNoGm;
    /**
     * 统计维度
             商户：MERCHANT
             区域：AREA
             门店：SHOP
             导购：GUID .
     */
    private String dimensionSt;

    /**
     * 开始日期 .
     */
    private String beginDate;
    
    /**
     * 区域code
     */
    private String areaCode; 
    /**
     * 区域
     */
    private String areaName;
    
    /**
     * 统计结束时间 .
     */
    private String endDate;

    
    
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

	@Override
	public String toString() {
		return "FindPmTalkTotal [merchantNo=" + merchantNo + ", shopNo="
				+ shopNo + ", memberNoGm=" + memberNoGm + ", dimensionSt="
				+ dimensionSt + ", beginDate=" + beginDate + ", areaCode="
				+ areaCode + ", areaName=" + areaName + ", endDate=" + endDate
				+ "]";
	}

}
