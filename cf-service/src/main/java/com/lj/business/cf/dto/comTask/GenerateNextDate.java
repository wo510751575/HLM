package com.lj.business.cf.dto.comTask;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class GenerateNextDate.
 */
public class GenerateNextDate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2025066182629886785L;
	
	/** 客户编号. */
	private String memberNo;
	
	/** 导购编号. */
	private String memberNoGm;

	/** 下次任务时间. */
	private Date nextDate;
	
	/** 是否获取下周时间. */
	private Boolean nextWeek = false;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the 下次任务时间.
	 *
	 * @return the 下次任务时间
	 */
	public Date getNextDate() {
		return nextDate;
	}

	/**
	 * Sets the 下次任务时间.
	 *
	 * @param nextDate the new 下次任务时间
	 */
	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

	/**
	 * Gets the 是否获取下周时间.
	 *
	 * @return the 是否获取下周时间
	 */
	public Boolean getNextWeek() {
		return nextWeek;
	}

	/**
	 * Sets the 是否获取下周时间.
	 *
	 * @param nextWeek the new 是否获取下周时间
	 */
	public void setNextWeek(Boolean nextWeek) {
		this.nextWeek = nextWeek;
	}
	
	@Override
	public String toString() {
		return "GenerateNextDate [memberNo=" + memberNo + ", memberNoGm="
				+ memberNoGm + ", nextDate=" + nextDate + ", nextWeek="
				+ nextWeek + "]";
	}
}
