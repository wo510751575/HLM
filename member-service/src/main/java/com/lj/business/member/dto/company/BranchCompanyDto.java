package com.lj.business.member.dto.company;

import java.io.Serializable;

public class BranchCompanyDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8028142402171798425L;
    
    /**
     * 分公司编号 .
     */
    private String companyNo;
    
    /**
     * 分公司名称 .
     */
    private String companyName;

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BranchCompanyDto [companyNo=");
        builder.append(companyNo);
        builder.append(", companyName=");
        builder.append(companyName);
        builder.append("]");
        return builder.toString();
    }

}
