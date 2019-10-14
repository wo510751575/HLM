package com.lj.business.api.dto;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * 类说明：
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * CreateDate: 2017年7月1日
 * @Company: 扬恩科技有限公司
 */
public class FindWorkTodayInfoShopReturn implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1509002369272875673L;


	/**
     * 工作完成度 .
     */
    private Double ratioWork;

    /**
     * 首页评语 .
     */
    private String remarkIndex;
	
	
	/** 客户提醒LIST. */
	private List<FindUnContactReturn> unContactTotalInfo;
	
	
	/**
	 * Gets the 工作完成度 .
	 *
	 * @return the 工作完成度 
	 */
	public Double getRatioWork() {
		return ratioWork;
	}

	/**
	 * Sets the 工作完成度 .
	 *
	 * @param ratioWork the new 工作完成度 
	 */
	public void setRatioWork(Double ratioWork) {
		this.ratioWork = ratioWork;
	}


	/**
	 * Gets the un contact total info.
	 *
	 * @return the un contact total info
	 */
	public List<FindUnContactReturn> getUnContactTotalInfo() {
		return unContactTotalInfo;
	}

	/**
	 * Sets the un contact total info.
	 *
	 * @param unContactTotalInfo the new un contact total info
	 */
	public void setUnContactTotalInfo(List<FindUnContactReturn> unContactTotalInfo) {
		this.unContactTotalInfo = unContactTotalInfo;
	}


	/**
	 * Gets the 首页评语 .
	 *
	 * @return the 首页评语 
	 */
	public String getRemarkIndex() {
		return remarkIndex;
	}

	/**
	 * Sets the 首页评语 .
	 *
	 * @param remarkIndex the new 首页评语 
	 */
	public void setRemarkIndex(String remarkIndex) {
		this.remarkIndex = remarkIndex;
	}

}
