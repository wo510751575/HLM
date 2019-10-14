package com.lj.business.st.dto.tms;

import java.io.Serializable;
import java.util.Date;

public class UpdateTodayMemberSummary implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -6097575757624151521L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 汇总日期 .
     */
    private Date summaryDate;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 分店名称 .
     */
    private String shopName;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 到店客户数 .
     */
    private Integer shopCount;

    /**
     * 意向客户数 .
     */
    private Integer intentionCount;

    /**
     * 扫码客户数 .
     */
    private Integer scanCount;

    /**
     * 未扫码原因 .
     */
    private String unscanReason;

    /**
     * 填写资料客户数 .
     */
    private Integer infoCount;

    /**
     * 未填写资料原因 .
     */
    private String uninfoReason;

    /**
     * 创建时间 .
     */
    private Date createDate;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the summaryDate
	 */
	public Date getSummaryDate() {
		return summaryDate;
	}

	/**
	 * @param summaryDate the summaryDate to set
	 */
	public void setSummaryDate(Date summaryDate) {
		this.summaryDate = summaryDate;
	}

	/**
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * @return the shopNo
	 */
	public String getShopNo() {
		return shopNo;
	}

	/**
	 * @param shopNo the shopNo to set
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * @param shopName the shopName to set
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * @param memberNoGm the memberNoGm to set
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * @return the memberNameGm
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}

	/**
	 * @param memberNameGm the memberNameGm to set
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	/**
	 * @return the shopCount
	 */
	public Integer getShopCount() {
		return shopCount;
	}

	/**
	 * @param shopCount the shopCount to set
	 */
	public void setShopCount(Integer shopCount) {
		this.shopCount = shopCount;
	}

	/**
	 * @return the intentionCount
	 */
	public Integer getIntentionCount() {
		return intentionCount;
	}

	/**
	 * @param intentionCount the intentionCount to set
	 */
	public void setIntentionCount(Integer intentionCount) {
		this.intentionCount = intentionCount;
	}

	/**
	 * @return the scanCount
	 */
	public Integer getScanCount() {
		return scanCount;
	}

	/**
	 * @param scanCount the scanCount to set
	 */
	public void setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
	}

	/**
	 * @return the unscanReason
	 */
	public String getUnscanReason() {
		return unscanReason;
	}

	/**
	 * @param unscanReason the unscanReason to set
	 */
	public void setUnscanReason(String unscanReason) {
		this.unscanReason = unscanReason;
	}

	/**
	 * @return the infoCount
	 */
	public Integer getInfoCount() {
		return infoCount;
	}

	/**
	 * @param infoCount the infoCount to set
	 */
	public void setInfoCount(Integer infoCount) {
		this.infoCount = infoCount;
	}

	/**
	 * @return the uninfoReason
	 */
	public String getUninfoReason() {
		return uninfoReason;
	}

	/**
	 * @param uninfoReason the uninfoReason to set
	 */
	public void setUninfoReason(String uninfoReason) {
		this.uninfoReason = uninfoReason;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateTodayMemberSummary [code=");
		builder.append(code);
		builder.append(", summaryDate=");
		builder.append(summaryDate);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", shopNo=");
		builder.append(shopNo);
		builder.append(", shopName=");
		builder.append(shopName);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", shopCount=");
		builder.append(shopCount);
		builder.append(", intentionCount=");
		builder.append(intentionCount);
		builder.append(", scanCount=");
		builder.append(scanCount);
		builder.append(", unscanReason=");
		builder.append(unscanReason);
		builder.append(", infoCount=");
		builder.append(infoCount);
		builder.append(", uninfoReason=");
		builder.append(uninfoReason);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}
}
