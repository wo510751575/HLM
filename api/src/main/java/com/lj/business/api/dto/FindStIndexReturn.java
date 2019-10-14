package com.lj.business.api.dto;

import java.io.Serializable;

/**
 * The Class FindStIndexReturn.
 */
public class FindStIndexReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5974696578627458736L;

	

	/** 累计未完成任务. */
	private Integer numDis;
	

	/** 当前任务数量. */
	private Integer num;
	
	
	/** * 总条数. */
	private long total;


	/** * 每页条数. */
	private int limit;
	
	/**
	 * Gets the 累计未完成任务.
	 *
	 * @return the 累计未完成任务
	 */
	public Integer getNumDis() {
		return numDis;
	}

	/**
	 * Sets the 累计未完成任务.
	 *
	 * @param numDis the new 累计未完成任务
	 */
	public void setNumDis(Integer numDis) {
		this.numDis = numDis;
	}

	/**
	 * Gets the 当前任务.
	 *
	 * @return the 当前任务
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * Sets the 当前任务.
	 *
	 * @param num the new 当前任务
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * Gets the * 总条数.
	 *
	 * @return the * 总条数
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * Sets the * 总条数.
	 *
	 * @param total the new * 总条数
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * Gets the * 每页条数.
	 *
	 * @return the * 每页条数
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * Sets the * 每页条数.
	 *
	 * @param limit the new * 每页条数
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
