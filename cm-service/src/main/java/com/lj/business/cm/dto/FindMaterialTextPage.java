package com.lj.business.cm.dto;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindMaterialTextPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -681631034704680027L; 

	/**
	 * 商户编号
	 */
	private String merchantNo;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
     * 类型CODE .
     */
    private String code;
    
    /**
     * 内容 .
     */
    private String content;
    /**
     * 开始时间
     */
    private Date begainTime;
    /**
     * 结束时间
     */
    private Date endTime;

    
    
	public Date getBegainTime() {
		return begainTime;
	}

	public void setBegainTime(Date begainTime) {
		this.begainTime = begainTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    
	
    
}
