package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FindWorkRatioGm.
 */
public class FindWorkRatioGm implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2957759046466976371L; 

	/**
     * CODE .
     */
    private String code;

    /**
     * 导购编号  .
     */
    private String memberNoGm;

    /**
     * 统计日期 .
     */
    private Date daySt;
    
    
	/**
	 * Gets the cODE .
	 *
	 * @return the cODE 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the cODE .
	 *
	 * @param code the new cODE 
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWorkRatioGm [code=").append(code)
				.append(", memberNoGm=").append(memberNoGm).append(", daySt=")
				.append(daySt).append("]");
		return builder.toString();
	}

	/**
	 * Gets the 导购编号  .
	 *
	 * @return the 导购编号  
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the 导购编号  .
	 *
	 * @param memberNoGm the new 导购编号  
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the 统计日期 .
	 *
	 * @return the 统计日期 
	 */
	public Date getDaySt() {
		return daySt;
	}

	/**
	 * Sets the 统计日期 .
	 *
	 * @param daySt the new 统计日期 
	 */
	public void setDaySt(Date daySt) {
		this.daySt = daySt;
	}
}
