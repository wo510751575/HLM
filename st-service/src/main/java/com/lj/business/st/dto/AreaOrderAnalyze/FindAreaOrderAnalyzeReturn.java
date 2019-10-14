package com.lj.business.st.dto.AreaOrderAnalyze;

import java.io.Serializable;
import java.util.Date;

public class FindAreaOrderAnalyzeReturn implements Serializable {

    /**
     * Generate cron.
     *
     * @param
     * @param
     * @throws
     */
    private static final long serialVersionUID = -6520056290403091038L;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 区域CODE .
     */
    private String areaCode;

    /**
     * 区域名称 .
     */
    private String areaName;


    /**
     * 成单数量 .
     */
    private Long numOrder;

    @Override
	public String toString() {
		return "FindAreaOrderAnalyzeReturn [merchantNo=" + merchantNo
				+ ", areaCode=" + areaCode + ", areaName=" + areaName
				+ ", numOrder=" + numOrder + "]";
	}

	public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(Long numOrder) {
        this.numOrder = numOrder;
    }
}
