package com.lj.business.cm.domain;

import java.util.Date;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月25日下午3:49:42
 */
public class MaterialText {

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
