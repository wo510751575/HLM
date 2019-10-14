package com.lj.business.st.dto.PmTalkTotal;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class FindPmTalkTotalReturn.
 */
public class FindPmTalkTotalReturn implements Serializable {

    /** Generate cron. */
    private static final long serialVersionUID = -3636031494876038194L;

    /**
     * 开始日期 .
     */
    private String startDate;

    /**
     * 统计结束时间 .
     */
    private String endDate;

    /**
     * 咨询数量 .
     */
    private Integer numTalk;

    /** 总数量. */
    private String ratio;
    
    /** 商户编号. */
   private String merchantNo;
   
   /** 导购编号. */
   private String memberNoGm;
   
   
   
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
public String toString() {
	return "FindPmTalkTotalReturn [startDate=" + startDate + ", endDate="
			+ endDate + ", numTalk=" + numTalk + ", ratio=" + ratio
			+ ", merchantNo=" + merchantNo + ", memberNoGm=" + memberNoGm + "]";
}

	/**
	 * Gets the merchant no.
	 *
	 * @return the merchant no
	 */
	public String getMerchantNo() {
	return merchantNo;
}

/**
 * Sets the merchant no.
 *
 * @param merchantNo the merchant no
 */
public void setMerchantNo(String merchantNo) {
	this.merchantNo = merchantNo;
}

/**
 * Gets the member no gm.
 *
 * @return the member no gm
 */
public String getMemberNoGm() {
	return memberNoGm;
}

/**
 * Sets the member no gm.
 *
 * @param memberNoGm the member no gm
 */
public void setMemberNoGm(String memberNoGm) {
	this.memberNoGm = memberNoGm;
}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the num talk.
     *
     * @return the num talk
     */
    public Integer getNumTalk() {
        return numTalk;
    }

    /**
     * Sets the num talk.
     *
     * @param numTalk the num talk
     */
    public void setNumTalk(Integer numTalk) {
        this.numTalk = numTalk;
    }

    /**
     * Gets the ratio.
     *
     * @return the ratio
     */
    public String getRatio() {
        return ratio;
    }

    /**
     * Sets the ratio.
     *
     * @param ratio the ratio
     */
    public void setRatio(String ratio) {
        this.ratio = ratio;
    }
}
