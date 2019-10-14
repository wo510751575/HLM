package com.lj.business.st.dto.mec;

import java.io.Serializable;
import java.util.Date;

public class FindCompanyOperationDayReportPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4212204984014118520L; 

    /**
     * code .
     */
    private String code;

    /**
     * 报表日期 .
     */
    private Date reportDate;

    /**
     * 经销商代码 .
     */
    private String dealerCode;

    /**
     * 经销商编号 .
     */
    private String companyNo;

    /**
     * 经销商名称 .
     */
    private String companyName;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 客户-客户总数 .
     */
    private Integer mbrTotalCount;

    /**
     * 客户-今日新增数 .
     */
    private Integer mbrTodayCount;

    /**
     * 下单-商品件数 .
     */
    private Integer ordGoodCount;

    /**
     * 下单-订单数 .
     */
    private Integer ordOrderCount;

    /**
     * 下单-订单总额 .
     */
    private Long ordOrderAmount;

    /**
     * 下单-客单价 .
     */
    private Long ordCustomerPrice;

    /**
     * 下单-活动优惠金额 .
     */
    private Long ordActivitieAmount;

    /**
     * 下单-实际支付金额 .
     */
    private Long ordPayAmount;

    /**
     * 成交-商品件数 .
     */
    private Integer succGoodCount;

    /**
     * 成交-订单数 .
     */
    private Integer succOrderCount;

    /**
     * 成交-订单总额 .
     */
    private Long succOrderAmount;

    /**
     * 成交-客单价 .
     */
    private Long succCustomerPrice;

    /**
     * 成交-活动优惠金额 .
     */
    private Long succActivitieAmount;

    /**
     * 成交-实际支付金额 .
     */
    private Long succPayAmount;

    /**
     * 访问-商城主页访问次数 .
     */
    private Integer pvIndexTotalCount;

    /**
     * 访问-商城主页访问人数 .
     */
    private Integer pvIndexMemberCount;

    /**
     * 访问-商品详情页访问次数 .
     */
    private Integer pvDetailTotalCount;

    /**
     * 访问-商品详情页访问人数 .
     */
    private Integer pvDetailMemberCount;
    
    /**
     * 短信-发送数量
     */
    private Integer smsCount;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 创建时间 .
     */
    private Date createTime;
    
    /**
     * 下单-订单总额 字符串(导出)
     */
    private String ordOrderAmountStr;
    
    /**
     * 下单-客单价 字符串(导出)
     */
    private String ordCustomerPriceStr;
    
    /**
     * 下单-活动优惠金额 字符串(导出)
     */
    private String ordActivitieAmountStr;
    
    /**
     * 下单-实际支付金额 字符串(导出)
     */
    private String ordPayAmountStr;
    
    /**
     * 成交-订单总额 字符串(导出)
     */
    private String succOrderAmountStr;
    
    /**
     * 成交-客单价 字符串(导出)
     */
    private String succCustomerPriceStr;
    
    /**
     * 成交-活动优惠金额 字符串(导出)
     */
    private String succActivitieAmountStr;
    
    /**
     * 成交-实际支付金额 字符串(导出)
     */
    private String succPayAmountStr;

    public String getOrdOrderAmountStr() {
		return ordOrderAmountStr;
	}

	public void setOrdOrderAmountStr(String ordOrderAmountStr) {
		this.ordOrderAmountStr = ordOrderAmountStr;
	}

	public String getOrdCustomerPriceStr() {
		return ordCustomerPriceStr;
	}

	public void setOrdCustomerPriceStr(String ordCustomerPriceStr) {
		this.ordCustomerPriceStr = ordCustomerPriceStr;
	}

	public String getOrdActivitieAmountStr() {
		return ordActivitieAmountStr;
	}

	public void setOrdActivitieAmountStr(String ordActivitieAmountStr) {
		this.ordActivitieAmountStr = ordActivitieAmountStr;
	}

	public String getOrdPayAmountStr() {
		return ordPayAmountStr;
	}

	public void setOrdPayAmountStr(String ordPayAmountStr) {
		this.ordPayAmountStr = ordPayAmountStr;
	}

	public String getSuccOrderAmountStr() {
		return succOrderAmountStr;
	}

	public void setSuccOrderAmountStr(String succOrderAmountStr) {
		this.succOrderAmountStr = succOrderAmountStr;
	}

	public String getSuccCustomerPriceStr() {
		return succCustomerPriceStr;
	}

	public void setSuccCustomerPriceStr(String succCustomerPriceStr) {
		this.succCustomerPriceStr = succCustomerPriceStr;
	}

	public String getSuccActivitieAmountStr() {
		return succActivitieAmountStr;
	}

	public void setSuccActivitieAmountStr(String succActivitieAmountStr) {
		this.succActivitieAmountStr = succActivitieAmountStr;
	}

	public String getSuccPayAmountStr() {
		return succPayAmountStr;
	}

	public void setSuccPayAmountStr(String succPayAmountStr) {
		this.succPayAmountStr = succPayAmountStr;
	}

	/**
     * code .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * code .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 报表日期 .
     *
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * 报表日期 .
     *
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * 经销商代码 .
     *
     */
    public String getDealerCode() {
        return dealerCode;
    }

    /**
     * 经销商代码 .
     *
     */
    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode == null ? null : dealerCode.trim();
    }

    /**
     * 经销商编号 .
     *
     */
    public String getCompanyNo() {
        return companyNo;
    }

    /**
     * 经销商编号 .
     *
     */
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    /**
     * 经销商名称 .
     *
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 经销商名称 .
     *
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 商户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 客户-客户总数 .
     *
     */
    public Integer getMbrTotalCount() {
        return mbrTotalCount;
    }

    /**
     * 客户-客户总数 .
     *
     */
    public void setMbrTotalCount(Integer mbrTotalCount) {
        this.mbrTotalCount = mbrTotalCount;
    }

    /**
     * 客户-今日新增数 .
     *
     */
    public Integer getMbrTodayCount() {
        return mbrTodayCount;
    }

    /**
     * 客户-今日新增数 .
     *
     */
    public void setMbrTodayCount(Integer mbrTodayCount) {
        this.mbrTodayCount = mbrTodayCount;
    }

    /**
     * 下单-商品件数 .
     *
     */
    public Integer getOrdGoodCount() {
        return ordGoodCount;
    }

    /**
     * 下单-商品件数 .
     *
     */
    public void setOrdGoodCount(Integer ordGoodCount) {
        this.ordGoodCount = ordGoodCount;
    }

    /**
     * 下单-订单数 .
     *
     */
    public Integer getOrdOrderCount() {
        return ordOrderCount;
    }

    /**
     * 下单-订单数 .
     *
     */
    public void setOrdOrderCount(Integer ordOrderCount) {
        this.ordOrderCount = ordOrderCount;
    }

    /**
     * 下单-订单总额 .
     *
     */
    public Long getOrdOrderAmount() {
        return ordOrderAmount;
    }

    /**
     * 下单-订单总额 .
     *
     */
    public void setOrdOrderAmount(Long ordOrderAmount) {
        this.ordOrderAmount = ordOrderAmount;
    }

    /**
     * 下单-客单价 .
     *
     */
    public Long getOrdCustomerPrice() {
        return ordCustomerPrice;
    }

    /**
     * 下单-客单价 .
     *
     */
    public void setOrdCustomerPrice(Long ordCustomerPrice) {
        this.ordCustomerPrice = ordCustomerPrice;
    }

    /**
     * 下单-活动优惠金额 .
     *
     */
    public Long getOrdActivitieAmount() {
        return ordActivitieAmount;
    }

    /**
     * 下单-活动优惠金额 .
     *
     */
    public void setOrdActivitieAmount(Long ordActivitieAmount) {
        this.ordActivitieAmount = ordActivitieAmount;
    }

    /**
     * 下单-实际支付金额 .
     *
     */
    public Long getOrdPayAmount() {
        return ordPayAmount;
    }

    /**
     * 下单-实际支付金额 .
     *
     */
    public void setOrdPayAmount(Long ordPayAmount) {
        this.ordPayAmount = ordPayAmount;
    }

    /**
     * 成交-商品件数 .
     *
     */
    public Integer getSuccGoodCount() {
        return succGoodCount;
    }

    /**
     * 成交-商品件数 .
     *
     */
    public void setSuccGoodCount(Integer succGoodCount) {
        this.succGoodCount = succGoodCount;
    }

    /**
     * 成交-订单数 .
     *
     */
    public Integer getSuccOrderCount() {
        return succOrderCount;
    }

    /**
     * 成交-订单数 .
     *
     */
    public void setSuccOrderCount(Integer succOrderCount) {
        this.succOrderCount = succOrderCount;
    }

    /**
     * 成交-订单总额 .
     *
     */
    public Long getSuccOrderAmount() {
        return succOrderAmount;
    }

    /**
     * 成交-订单总额 .
     *
     */
    public void setSuccOrderAmount(Long succOrderAmount) {
        this.succOrderAmount = succOrderAmount;
    }

    /**
     * 成交-客单价 .
     *
     */
    public Long getSuccCustomerPrice() {
        return succCustomerPrice;
    }

    /**
     * 成交-客单价 .
     *
     */
    public void setSuccCustomerPrice(Long succCustomerPrice) {
        this.succCustomerPrice = succCustomerPrice;
    }

    /**
     * 成交-活动优惠金额 .
     *
     */
    public Long getSuccActivitieAmount() {
        return succActivitieAmount;
    }

    /**
     * 成交-活动优惠金额 .
     *
     */
    public void setSuccActivitieAmount(Long succActivitieAmount) {
        this.succActivitieAmount = succActivitieAmount;
    }

    /**
     * 成交-实际支付金额 .
     *
     */
    public Long getSuccPayAmount() {
        return succPayAmount;
    }

    /**
     * 成交-实际支付金额 .
     *
     */
    public void setSuccPayAmount(Long succPayAmount) {
        this.succPayAmount = succPayAmount;
    }

    /**
     * 备注 .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	/**
	 * @return the pvIndexTotalCount
	 */
	public Integer getPvIndexTotalCount() {
		return pvIndexTotalCount;
	}

	/**
	 * @param pvIndexTotalCount the pvIndexTotalCount to set
	 */
	public void setPvIndexTotalCount(Integer pvIndexTotalCount) {
		this.pvIndexTotalCount = pvIndexTotalCount;
	}

	/**
	 * @return the pvIndexMemberCount
	 */
	public Integer getPvIndexMemberCount() {
		return pvIndexMemberCount;
	}

	/**
	 * @param pvIndexMemberCount the pvIndexMemberCount to set
	 */
	public void setPvIndexMemberCount(Integer pvIndexMemberCount) {
		this.pvIndexMemberCount = pvIndexMemberCount;
	}

	/**
	 * @return the pvDetailTotalCount
	 */
	public Integer getPvDetailTotalCount() {
		return pvDetailTotalCount;
	}

	/**
	 * @param pvDetailTotalCount the pvDetailTotalCount to set
	 */
	public void setPvDetailTotalCount(Integer pvDetailTotalCount) {
		this.pvDetailTotalCount = pvDetailTotalCount;
	}

	/**
	 * @return the pvDetailMemberCount
	 */
	public Integer getPvDetailMemberCount() {
		return pvDetailMemberCount;
	}

	/**
	 * @param pvDetailMemberCount the pvDetailMemberCount to set
	 */
	public void setPvDetailMemberCount(Integer pvDetailMemberCount) {
		this.pvDetailMemberCount = pvDetailMemberCount;
	}

    /**
     * 短信-发送数量
     */
	public Integer getSmsCount() {
		return smsCount;
	}

	/**
     * 短信-发送数量
     */
	public void setSmsCount(Integer smsCount) {
		this.smsCount = smsCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindCompanyOperationDayReportPageReturn [code=");
		builder.append(code);
		builder.append(", reportDate=");
		builder.append(reportDate);
		builder.append(", dealerCode=");
		builder.append(dealerCode);
		builder.append(", companyNo=");
		builder.append(companyNo);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", mbrTotalCount=");
		builder.append(mbrTotalCount);
		builder.append(", mbrTodayCount=");
		builder.append(mbrTodayCount);
		builder.append(", ordGoodCount=");
		builder.append(ordGoodCount);
		builder.append(", ordOrderCount=");
		builder.append(ordOrderCount);
		builder.append(", ordOrderAmount=");
		builder.append(ordOrderAmount);
		builder.append(", ordCustomerPrice=");
		builder.append(ordCustomerPrice);
		builder.append(", ordActivitieAmount=");
		builder.append(ordActivitieAmount);
		builder.append(", ordPayAmount=");
		builder.append(ordPayAmount);
		builder.append(", succGoodCount=");
		builder.append(succGoodCount);
		builder.append(", succOrderCount=");
		builder.append(succOrderCount);
		builder.append(", succOrderAmount=");
		builder.append(succOrderAmount);
		builder.append(", succCustomerPrice=");
		builder.append(succCustomerPrice);
		builder.append(", succActivitieAmount=");
		builder.append(succActivitieAmount);
		builder.append(", succPayAmount=");
		builder.append(succPayAmount);
		builder.append(", pvIndexTotalCount=");
		builder.append(pvIndexTotalCount);
		builder.append(", pvIndexMemberCount=");
		builder.append(pvIndexMemberCount);
		builder.append(", pvDetailTotalCount=");
		builder.append(pvDetailTotalCount);
		builder.append(", pvDetailMemberCount=");
		builder.append(pvDetailMemberCount);
		builder.append(", smsCount=");
		builder.append(smsCount);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", ordOrderAmountStr=");
		builder.append(ordOrderAmountStr);
		builder.append(", ordCustomerPriceStr=");
		builder.append(ordCustomerPriceStr);
		builder.append(", ordActivitieAmountStr=");
		builder.append(ordActivitieAmountStr);
		builder.append(", ordPayAmountStr=");
		builder.append(ordPayAmountStr);
		builder.append(", succOrderAmountStr=");
		builder.append(succOrderAmountStr);
		builder.append(", succCustomerPriceStr=");
		builder.append(succCustomerPriceStr);
		builder.append(", succActivitieAmountStr=");
		builder.append(succActivitieAmountStr);
		builder.append(", succPayAmountStr=");
		builder.append(succPayAmountStr);
		builder.append("]");
		return builder.toString();
	}
}
