package com.lj.business.member.domain;

import java.util.Date;

public class TmallBonusConfig {
    /** CODE*/
    private String code;

    /** 商户编号*/
    private String merchantNo;

    /** 订单金额下限*/
    private Long ordAmtMin;

    /** 订单金额上限*/
    private Long ordAmtMax;

    /** 红包金额上限*/
    private Long bonuxMin;

    /** 红包金额下限*/
    private Long bonuxMax;

    /** 创建时间*/
    private Date createDate;

    /** 创建人*/
    private String createId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public Long getOrdAmtMin() {
        return ordAmtMin;
    }

    public void setOrdAmtMin(Long ordAmtMin) {
        this.ordAmtMin = ordAmtMin;
    }

    public Long getOrdAmtMax() {
        return ordAmtMax;
    }

    public void setOrdAmtMax(Long ordAmtMax) {
        this.ordAmtMax = ordAmtMax;
    }

    public Long getBonuxMin() {
        return bonuxMin;
    }

    public void setBonuxMin(Long bonuxMin) {
        this.bonuxMin = bonuxMin;
    }

    public Long getBonuxMax() {
        return bonuxMax;
    }

    public void setBonuxMax(Long bonuxMax) {
        this.bonuxMax = bonuxMax;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }
}