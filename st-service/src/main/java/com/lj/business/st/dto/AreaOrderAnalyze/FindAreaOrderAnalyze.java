package com.lj.business.st.dto.AreaOrderAnalyze;

import java.io.Serializable;
import java.util.Date;

public class FindAreaOrderAnalyze implements Serializable {

    /**
     * Generate cron.
     *
     * @param
     * @param
     * @throws
     */
    private static final long serialVersionUID = -1488544267958979921L;

    /**
     * 商户编号 .
     */
    private String merchantNo;


    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 统计维度\r\n            商户：MERCHANT\r\n            区域：AREA\r\n            省份：PROVINCE\r\n            门店：SHOP
     */
    private String dimensionSt;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
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
}
