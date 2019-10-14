package com.lj.business.cm.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月25日下午3:38:58
 */
public class FindMaterialLinkPageReturn implements Serializable{

	private static final long serialVersionUID = 614996950679634757L;
	
	private String code;
	private String title;
	private String url;
	private Date updateTime;
	private String merchantNo;
	private String photo;
	private String officeName;
	
	
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
