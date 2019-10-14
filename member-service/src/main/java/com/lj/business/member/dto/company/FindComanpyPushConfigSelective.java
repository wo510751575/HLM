package com.lj.business.member.dto.company;

import java.io.Serializable;

public class FindComanpyPushConfigSelective implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7322851532326797452L;
    
    /**
     * 商户编号 .
     */
    private String merchantNo;
    
    /**
     * 经销商编号 .
     */
    private String companyNo;
    
    /**
     * 状态：USE有效、STOP失效 .
     */
    private String status;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindComanpyPushConfigSelective [merchantNo=");
        builder.append(merchantNo);
        builder.append(", companyNo=");
        builder.append(companyNo);
        builder.append(", status=");
        builder.append(status);
        builder.append("]");
        return builder.toString();
    }

}
