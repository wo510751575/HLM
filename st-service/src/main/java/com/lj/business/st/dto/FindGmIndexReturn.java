package com.lj.business.st.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class FindGmIndexReturn.
 */
public class FindGmIndexReturn implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7057554464139030433L;
	
	/** 排名. */
	private Integer rownum;
	
	/** 排名日. */
	private Integer rownumDay;
	
	/** 排名周. */
	private Integer rownumWeek;
	
	/** 排名月. */
	private Integer rownumMonth;
	
	/** 导购编号. */
	private String memberNoGm;

	/**
	 * Gets the rownum.
	 *
	 * @return the rownum
	 */
	public Integer getRownum() {
		return rownum;
	}

	/**
	 * Sets the rownum.
	 *
	 * @param rownum the new rownum
	 */
	public void setRownum(Integer rownum) {
		this.rownum = rownum;
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
	 * @param memberNoGm the new member no gm
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the rownum day.
	 *
	 * @return the rownum day
	 */
	public Integer getRownumDay() {
		return rownumDay;
	}

	/**
	 * Sets the rownum day.
	 *
	 * @param rownumDay the new rownum day
	 */
	public void setRownumDay(Integer rownumDay) {
		this.rownumDay = rownumDay;
	}

	/**
	 * Gets the rownum week.
	 *
	 * @return the rownum week
	 */
	public Integer getRownumWeek() {
		return rownumWeek;
	}

	/**
	 * Sets the rownum week.
	 *
	 * @param rownumWeek the new rownum week
	 */
	public void setRownumWeek(Integer rownumWeek) {
		this.rownumWeek = rownumWeek;
	}

	/**
	 * Gets the rownum month.
	 *
	 * @return the rownum month
	 */
	public Integer getRownumMonth() {
		return rownumMonth;
	}

	/**
	 * Sets the rownum month.
	 *
	 * @param rownumMonth the new rownum month
	 */
	public void setRownumMonth(Integer rownumMonth) {
		this.rownumMonth = rownumMonth;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindGmIndexReturn [rownum=").append(rownum)
				.append(", rownumDay=").append(rownumDay)
				.append(", rownumWeek=").append(rownumWeek)
				.append(", rownumMonth=").append(rownumMonth)
				.append(", memberNoGm=").append(memberNoGm).append("]");
		return builder.toString();
	}

}
