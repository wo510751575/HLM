package com.lj.business.member.dto.company;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindCompanyPushConfigPage extends PageParamEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 8822246618156197308L; 

    /**
     * 分公司编号 .
     */
    private String companyNo;
    
    /**
     * 商户编号 .
     */
    private String merchantNo;

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }
    
}
