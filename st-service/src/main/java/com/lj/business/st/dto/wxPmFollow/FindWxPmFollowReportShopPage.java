package com.lj.business.st.dto.wxPmFollow;

import java.util.Date;
import com.lj.base.core.pagination.PageParamEntity; 

public class FindWxPmFollowReportShopPage extends PageParamEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -3579630814852680308L; 

    /**
     * 商户编号
     */
    private String merchantNo;
    
    /**
     * 日报日期
     */
    private Date reportDate;
    
    /**
     * 经销商代码
     */
    private String dealerCode;
    
    /**
     * 经销商名称
     */
    private String companyName;
    
    /**
     * 门店代码
     */
    private String shopCode;
    
    /**
     * 门店名称
     */
    private String shopName;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindWxPmFollowReportGmPage [merchantNo=");
        builder.append(merchantNo);
        builder.append(", reportDate=");
        builder.append(reportDate);
        builder.append(", dealerCode=");
        builder.append(dealerCode);
        builder.append(", companyName=");
        builder.append(companyName);
        builder.append(", shopCode=");
        builder.append(shopCode);
        builder.append(", shopName=");
        builder.append(shopName);
        builder.append("]");
        return builder.toString();
    }
    
}
