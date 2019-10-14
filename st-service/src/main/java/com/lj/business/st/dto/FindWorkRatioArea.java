package com.lj.business.st.dto;

import java.io.Serializable;

public class FindWorkRatioArea implements Serializable {

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

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
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
}
