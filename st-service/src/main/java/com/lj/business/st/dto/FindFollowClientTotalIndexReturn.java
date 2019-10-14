package com.lj.business.st.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class FindFollowClientTotalIndexReturn.
 */
public class FindFollowClientTotalIndexReturn implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3363704279622504724L;
	
	/** 工作完成度日. */
	private Double ratioWorkDay;
	
	/** 工作完成度周. */
	private Double ratioWorkWeek;
	
	/** 工作完成度月. */
	private Double ratioWorkMonth;

	public Double getRatioWorkDay() {
		return ratioWorkDay;
	}

	public void setRatioWorkDay(Double ratioWorkDay) {
		this.ratioWorkDay = ratioWorkDay;
	}

	public Double getRatioWorkWeek() {
		return ratioWorkWeek;
	}

	public void setRatioWorkWeek(Double ratioWorkWeek) {
		this.ratioWorkWeek = ratioWorkWeek;
	}

	public Double getRatioWorkMonth() {
		return ratioWorkMonth;
	}

	public void setRatioWorkMonth(Double ratioWorkMonth) {
		this.ratioWorkMonth = ratioWorkMonth;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindFollowClientTotalIndexReturn [ratioWorkDay=")
				.append(ratioWorkDay).append(", ratioWorkWeek=")
				.append(ratioWorkWeek).append(", ratioWorkMonth=")
				.append(ratioWorkMonth).append("]");
		return builder.toString();
	}

}
