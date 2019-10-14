package com.lj.business.st.dto.SocialAnalyze;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class FindSocialAnalyzeReturn.
 */
public class FindSocialAnalyzeReturn implements Serializable { 

     /** Generate cron. */
	private static final long serialVersionUID = 2899598606289476606L;

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 统计日期 .
     */
    private Date stDate;

    /**
     * 社交数量 .
     */
    private Integer numSocial;
    
    /** 商户编号. */
    private String merchantNo;
    
    /** 导购编号. */
    private String memberNoGm;
    

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
	 * Gets the member no.
	 *
	 * @return the member no
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * Sets the member no.
	 *
	 * @param memberNo the new member no
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * Gets the member name.
	 *
	 * @return the member name
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * Sets the member name.
	 *
	 * @param memberName the new member name
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * Gets the st date.
	 *
	 * @return the st date
	 */
	public Date getStDate() {
		return stDate;
	}

	/**
	 * Sets the st date.
	 *
	 * @param stDate the new st date
	 */
	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}

	/**
	 * Gets the num social.
	 *
	 * @return the num social
	 */
	public Integer getNumSocial() {
		return numSocial;
	}

	/**
	 * Sets the num social.
	 *
	 * @param numSocial the new num social
	 */
	public void setNumSocial(Integer numSocial) {
		this.numSocial = numSocial;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FindSocialAnalyzeReturn [memberNo=" + memberNo
				+ ", memberName=" + memberName + ", stDate=" + stDate
				+ ", numSocial=" + numSocial + ", merchantNo=" + merchantNo
				+ ", memberNoGm=" + memberNoGm + "]";
	}
    
}
