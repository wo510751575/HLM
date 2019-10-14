package com.lj.business.member.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TmallBonusConfigDto implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
     
	 /** 订单金额下限*/
    private BigDecimal ordAmtMinDec;

    /** 订单金额上限*/
    private BigDecimal ordAmtMaxDec;

    /** 红包金额上限*/
    private BigDecimal bonuxMinDec;

    /** 红包金额下限*/
    private BigDecimal bonuxMaxDec;
    
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

	public BigDecimal getOrdAmtMinDec() {
		return ordAmtMinDec;
	}

	public void setOrdAmtMinDec(BigDecimal ordAmtMinDec) {
		this.ordAmtMinDec = ordAmtMinDec;
	}

	public BigDecimal getOrdAmtMaxDec() {
		return ordAmtMaxDec;
	}

	public void setOrdAmtMaxDec(BigDecimal ordAmtMaxDec) {
		this.ordAmtMaxDec = ordAmtMaxDec;
	}

	public BigDecimal getBonuxMinDec() {
		return bonuxMinDec;
	}

	public void setBonuxMinDec(BigDecimal bonuxMinDec) {
		this.bonuxMinDec = bonuxMinDec;
	}

	public BigDecimal getBonuxMaxDec() {
		return bonuxMaxDec;
	}

	public void setBonuxMaxDec(BigDecimal bonuxMaxDec) {
		this.bonuxMaxDec = bonuxMaxDec;
	}

	@Override
	public String toString() {
		return "TmallBonusConfigDto [code=" + code + ", merchantNo=" + merchantNo + ", ordAmtMin=" + ordAmtMin
				+ ", ordAmtMax=" + ordAmtMax + ", bonuxMin=" + bonuxMin + ", bonuxMax=" + bonuxMax + ", createDate="
				+ createDate + ", createId=" + createId + ", ordAmtMinDec=" + ordAmtMinDec + ", ordAmtMaxDec="
				+ ordAmtMaxDec + ", bonuxMinDec=" + bonuxMinDec + ", bonuxMaxDec=" + bonuxMaxDec + "]";
	}
    
    
}
