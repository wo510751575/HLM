package com.lj.business.api.dto;

import java.io.Serializable;

/**
 * The Class FindComTaskChooseIndexAllReturn.
 */
public class FindComTaskChooseIndexAllReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2667980504356936438L;
	
	
	/** 需沟通客户数. */
	private Integer numToday;
	
	/** 累计欠任务数. */
	private Integer numDis;

	/**
	 * Gets the 需沟通客户数.
	 *
	 * @return the 需沟通客户数
	 */
	public Integer getNumToday() {
		return numToday;
	}

	/**
	 * Sets the 需沟通客户数.
	 *
	 * @param numToday the new 需沟通客户数
	 */
	public void setNumToday(Integer numToday) {
		this.numToday = numToday;
	}

	/**
	 * Gets the 累计欠任务数.
	 *
	 * @return the 累计欠任务数
	 */
	public Integer getNumDis() {
		return numDis;
	}

	/**
	 * Sets the 累计欠任务数.
	 *
	 * @param numDis the new 累计欠任务数
	 */
	public void setNumDis(Integer numDis) {
		this.numDis = numDis;
	}


}
