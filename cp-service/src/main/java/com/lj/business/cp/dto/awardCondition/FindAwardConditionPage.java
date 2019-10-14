package com.lj.business.cp.dto.awardCondition;

import java.util.Date;
import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

public class FindAwardConditionPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1062349227392643004L;
	/**
	 * CODE .
	 */
	private String code;
     /**
      * 商户编号
      */
	private String merchantNo;
	/**
	 * 终端编号
	 */
	
	/**
	 * 终端名称
	 */
	
	/**
	 * 开始时间
	 */
    private Date beginDate;
    /**
     * 结束时间
     */
    private String endDate;
    /**
     * 终端编号集合
     */
    private List<String> shopNos;
    
    
	public List<String> getShopNos() {
		return shopNos;
	}
	public void setShopNos(List<String> shopNos) {
		this.shopNos = shopNos;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
    
    

}
