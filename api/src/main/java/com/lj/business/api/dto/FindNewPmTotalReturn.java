package com.lj.business.api.dto;

import java.io.Serializable;
import java.util.Collection;

import com.lj.business.member.dto.FindNewPmPageReturn;

/**
 * The Class FindNewPmTotalReturn.
 */
public class FindNewPmTotalReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5303040473264626448L;
	
	
	/** 今日需新增客户数. */
	private Integer numNeedAdd;
	
	/** 已完成客户数. */
	private Integer numFinish;
	
	/** 累计欠客户数. */
	private Integer numDis;
	
	/** 第一页数据. */
	private Collection<FindNewPmPageReturn> detail;
	
	/** * 总条数. */
	private long total;
	

	/** * 每页条数. */
	private int limit;
	
	/**
	 * Gets the 今日需新增客户数.
	 *
	 * @return the 今日需新增客户数
	 */
	public Integer getNumNeedAdd() {
		return numNeedAdd;
	}

	/**
	 * Sets the 今日需新增客户数.
	 *
	 * @param numNeedAdd the new 今日需新增客户数
	 */
	public void setNumNeedAdd(Integer numNeedAdd) {
		this.numNeedAdd = numNeedAdd;
	}

	/**
	 * Gets the 已完成客户数.
	 *
	 * @return the 已完成客户数
	 */
	public Integer getNumFinish() {
		return numFinish;
	}

	/**
	 * Sets the 已完成客户数.
	 *
	 * @param numFinish the new 已完成客户数
	 */
	public void setNumFinish(Integer numFinish) {
		this.numFinish = numFinish;
	}

	/**
	 * Gets the 累计欠客户数.
	 *
	 * @return the 累计欠客户数
	 */
	public Integer getNumDis() {
		return numDis;
	}

	/**
	 * Sets the 累计欠客户数.
	 *
	 * @param numDis the new 累计欠客户数
	 */
	public void setNumDis(Integer numDis) {
		this.numDis = numDis;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindNewPmTotalReturn [numNeedAdd=").append(numNeedAdd)
				.append(", numFinish=").append(numFinish).append(", numDis=")
				.append(numDis).append(", detail=").append(detail)
				.append(", total=").append(total).append(", limit=")
				.append(limit).append("]");
		return builder.toString();
	}

	/**
	 * Gets the 第一页数据.
	 *
	 * @return the 第一页数据
	 */
	public Collection<FindNewPmPageReturn> getDetail() {
		return detail;
	}

	/**
	 * Sets the 第一页数据.
	 *
	 * @param detail the new 第一页数据
	 */
	public void setDetail(Collection<FindNewPmPageReturn> detail) {
		this.detail = detail;
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
