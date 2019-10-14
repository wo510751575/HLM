package com.lj.business.st.dto;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年5月31日上午11:44:12
 */
public class FindUserCountPage extends PageParamEntity {

	private static final long serialVersionUID = 6739075172964737487L;
	
	private Date  startTime;	//创建时间
	private Date  endTime;	//创建时间
	private String  userId;	//用户id
	private String  name;	//姓名
	private String  company;//所属公司	
	private String  office;	//所属部门
	private String  shopWx;	//所属终端
	private String  merchantNo;	//商户号
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getShopWx() {
		return shopWx;
	}
	public void setShopWx(String shopWx) {
		this.shopWx = shopWx;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
}
