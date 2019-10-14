package com.lj.business.st.dto.mec;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindMerchantOperationDayReportPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4940102876083904115L; 

    /**
     * 报表日期 - 从 .
     */
    private Date reportDateBegin;
    
    /**
     * 报表日期 - 到 .
     */
    private Date reportDateEnd;

    /**
     * 商户编号 .
     */
    private String merchantNo;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindMerchantOperationDayReportPage [reportDateBegin=");
		builder.append(reportDateBegin);
		builder.append(", reportDateEnd=");
		builder.append(reportDateEnd);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}
}
