package com.lj.business.api.dto.hc;

import java.io.Serializable;
import java.util.Collection;

import com.lj.business.member.dto.FindNewPmPageReturn;

/**
 * 
 * 
 * 类说明：新增顾客-列表
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年9月23日
 */
public class ServiceNewPmReturn implements Serializable {

	private static final long serialVersionUID = -2981919097810845011L;
	
	/**
	 * 当日新增客户
	 */
	private int todayAddCount;
	
	/**
	 * 当月新增客户
	 */
	private int monthAddCount;

	/** 第一页数据. */
	private Collection<FindNewPmPageReturn> detail;
	

	/**
	 * @return the todayAddCount
	 */
	public int getTodayAddCount() {
		return todayAddCount;
	}


	/**
	 * @param todayAddCount the todayAddCount to set
	 */
	public void setTodayAddCount(int todayAddCount) {
		this.todayAddCount = todayAddCount;
	}


	/**
	 * @return the monthAddCount
	 */
	public int getMonthAddCount() {
		return monthAddCount;
	}


	/**
	 * @param monthAddCount the monthAddCount to set
	 */
	public void setMonthAddCount(int monthAddCount) {
		this.monthAddCount = monthAddCount;
	}


	/**
	 * @return the detail
	 */
	public Collection<FindNewPmPageReturn> getDetail() {
		return detail;
	}


	/**
	 * @param detail the detail to set
	 */
	public void setDetail(Collection<FindNewPmPageReturn> detail) {
		this.detail = detail;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceNewPmReturn [todayAddCount=");
		builder.append(todayAddCount);
		builder.append(", monthAddCount=");
		builder.append(monthAddCount);
		builder.append(", detail=");
		builder.append(detail);
		builder.append("]");
		return builder.toString();
	}


}
