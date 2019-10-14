package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StDailyPayDto implements Serializable { 

    /** CODE*/
    private String code;

    /** 门诊编号*/
    private String shopNo;

    /** 门诊名称*/
    private String shopName;

    /** 商户编号*/
    private String merchantNo;

    /** 商户名称*/
    private String merchantName;

    /** 统计日期*/
    private Date stDate;

    /** 支付方式code*/
    private String payType;

    /** 支付方式名称*/
    private String payTypeName;

    /** 金额（分为单位）*/
    private Long payAmount;

    /** 支付类型（ADD入账，SUB：出账）*/
    private String payMode;

    /** 创建时间*/
    private Date createDate;
    
    /** 金额（分为单位）*/
    private String payAmountStr; 
    /** 当日支付方式对应的金额 */
    private List<StDailyPayDto> payDetails;
    /** 查询开始日期String：YYYY-MM-DD*/
    private String startDateStr;
    
    /** 查询结束日期String：YYYY-MM-DD*/
    private String endDateStr;
    /** 查询开始日期*/
    private Date startDate;
    
    /** 查询结束日期*/
    private Date endDate;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public Date getStDate() {
        return stDate;
    }

    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName == null ? null : payTypeName.trim();
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode == null ? null : payMode.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public String getPayAmountStr() {
		return payAmountStr;
	}

	public void setPayAmountStr(String payAmountStr) {
		this.payAmountStr = payAmountStr;
	}

	public List<StDailyPayDto> getPayDetails() {
		return payDetails;
	}

	public void setPayDetails(List<StDailyPayDto> payDetails) {
		this.payDetails = payDetails;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
    
    
}
