package com.lj.business.cm.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月25日下午3:38:58
 */
public class FindMaterialTextPageReturn implements Serializable{

	private static final long serialVersionUID = 614996950679634757L;
	
	private String code;
	private String content;
	private Date updateTime;
	private String merchantNo;
	private String officeName;
	
	
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	
	
	

}
