package com.ye.business.hx.domain;

public class PayDetail {
    /** CODE*/
    private String code;

    /** 账单code*/
    private String billCode;

    /** 操作code*/
    private String operateCode;

    /** 项目code*/
    private String projectCode;

    /** 项目名称*/
    private String projectName;

    /** 本次收费实收费用金额（分为单位）*/
    private Long payAmount;

    /** 本次收费应收金额（分为单位）*/
    private Long reallyAmount;

    /** 本次收费后欠费总金额（分为单位）*/
    private Long debtAmount;

    /** 项目原实收(分为单位）*/
    private Long originalPayAmount;

    /** 项目原应收(分为单位）*/
    private Long originalReallyAmount;

    /** 项目原欠费(分为单位）*/
    private Long originalDebtAmount;

    /** 序号*/
    private Integer indexNo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode == null ? null : billCode.trim();
    }

    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode == null ? null : operateCode.trim();
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Long getReallyAmount() {
        return reallyAmount;
    }

    public void setReallyAmount(Long reallyAmount) {
        this.reallyAmount = reallyAmount;
    }

    public Long getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(Long debtAmount) {
        this.debtAmount = debtAmount;
    }

    public Long getOriginalPayAmount() {
        return originalPayAmount;
    }

    public void setOriginalPayAmount(Long originalPayAmount) {
        this.originalPayAmount = originalPayAmount;
    }

    public Long getOriginalReallyAmount() {
        return originalReallyAmount;
    }

    public void setOriginalReallyAmount(Long originalReallyAmount) {
        this.originalReallyAmount = originalReallyAmount;
    }

    public Long getOriginalDebtAmount() {
        return originalDebtAmount;
    }

    public void setOriginalDebtAmount(Long originalDebtAmount) {
        this.originalDebtAmount = originalDebtAmount;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }
}