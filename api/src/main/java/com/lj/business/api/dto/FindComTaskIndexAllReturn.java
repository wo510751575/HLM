package com.lj.business.api.dto;

import java.io.Serializable;

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
public class FindComTaskIndexAllReturn implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8021288904979366142L;
	

	  /**
     * 需要沟通客户数量 .
     */
    private Integer numClient;
    
    /**
     *累计欠客户数量 .
     */
    private Integer disNumClient;
	
	/**
	 * Gets the 需要沟通客户数量 .
	 *
	 * @return the 需要沟通客户数量 
	 */
	public Integer getNumClient() {
		return numClient;
	}

	/**
	 * Sets the 需要沟通客户数量 .
	 *
	 * @param numClient the new 需要沟通客户数量 
	 */
	public void setNumClient(Integer numClient) {
		this.numClient = numClient;
	}

	/**
	 * Gets the 累计欠客户数量 .
	 *
	 * @return the 累计欠客户数量 
	 */
	public Integer getDisNumClient() {
		return disNumClient;
	}

	/**
	 * Sets the 累计欠客户数量 .
	 *
	 * @param disNumClient the new 累计欠客户数量 
	 */
	public void setDisNumClient(Integer disNumClient) {
		this.disNumClient = disNumClient;
	}

	

}
