package com.lj.business.st.dto.mec;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

public class FindCompanyOperationDayReportPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2883497805305962571L;
	
	/**
	 * 报表日期
	 */
	private Date reportDate;

    /**
     * 报表日期 - 从 .
     */
    private Date reportDateBegin;
    
    /**
     * 报表日期 - 到 .
     */
    private Date reportDateEnd;

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
	 * 报表日期
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * 报表日期
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
     * 报表日期 - 从 .
     */
	public Date getReportDateBegin() {
		return reportDateBegin;
	}

	/**
     * 报表日期 - 从 .
     */
	public void setReportDateBegin(Date reportDateBegin) {
		this.reportDateBegin = reportDateBegin;
	}

	/**
     * 报表日期 - 到 .
     */
	public Date getReportDateEnd() {
		return reportDateEnd;
	}

	/**
     * 报表日期 - 到 .
     */
	public void setReportDateEnd(Date reportDateEnd) {
		this.reportDateEnd = reportDateEnd;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindCompanyOperationDayReportPage [reportDate=");
		builder.append(reportDate);
		builder.append(", reportDateBegin=");
		builder.append(reportDateBegin);
		builder.append(", reportDateEnd=");
		builder.append(reportDateEnd);
		builder.append(", dealerCode=");
		builder.append(dealerCode);
		builder.append(", companyNo=");
		builder.append(companyNo);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}

}
