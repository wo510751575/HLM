package com.lj.business.member.dto.company;

import java.io.Serializable;

public class Bank implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1409064230485021174L;
    
    /**
     * 银行CODE
     */
    private String bankCode;
    
    /**
     * 银行名称
     */
    private String bankName;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Bank [bankCode=");
        builder.append(bankCode);
        builder.append(", bankName=");
        builder.append(bankName);
        builder.append("]");
        return builder.toString();
    }
    
}
