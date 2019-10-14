	package com.lj.business.st.dto.tms;

import java.io.Serializable;

public class AddTodayMemberSummary implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -6405837175247920128L;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddTodayMemberSummary [memberNoGm=");
		builder.append(memberNoGm);
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
		builder.append("]");
		return builder.toString();
	}

}
