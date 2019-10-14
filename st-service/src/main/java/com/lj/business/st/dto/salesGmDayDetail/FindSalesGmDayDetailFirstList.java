package com.lj.business.st.dto.salesGmDayDetail;

import java.io.Serializable;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The Class FindSalesGmDayDetailFirstList.
 */
public class FindSalesGmDayDetailFirstList implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3739027294309603075L;
	
	/** 统计日期. */
	private Date dateSt;

	/**
	 * Gets the date st.
	 *
	 * @return the date st
	 */
	public Date getDateSt() {
		return dateSt;
	}

	/**
	 * Sets the date st.
	 *
	 * @param dateSt the new date st
	 */
	public void setDateSt(Date dateSt) {
		this.dateSt = dateSt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindSalesGmDayDetailFirstList [dateSt=").append(dateSt)
				.append("]");
		return builder.toString();
	}
	
}
