package com.lj.business.api.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 类说明：
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 * 
 * CreateDate: 2017年7月1日
 */
public class FindWorkTodayInfoReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -700108959281222732L;
	
	
	/**
     * 工作完成度 .
     */
    private Double ratioWork;
    
    /**
     * 击败导购数量 .
     */
    private Integer numBeatGm;

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
	 * Gets the 击败导购数量 .
	 *
	 * @return the 击败导购数量 
	 */
	public Integer getNumBeatGm() {
		return numBeatGm;
	}

	/**
	 * Sets the 击败导购数量 .
	 *
	 * @param numBeatGm the new 击败导购数量 
	 */
	public void setNumBeatGm(Integer numBeatGm) {
		this.numBeatGm = numBeatGm;
	}

	/**
	 * Gets the 客户提醒BEAN.
	 *
	 * @return the 客户提醒BEAN
	 */
	public List<FindUnContactReturn> getUnContactTotalInfo() {
		return unContactTotalInfo;
	}

	/**
	 * Sets the 客户提醒BEAN.
	 *
	 * @param unContactTotalInfo the new 客户提醒BEAN
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
