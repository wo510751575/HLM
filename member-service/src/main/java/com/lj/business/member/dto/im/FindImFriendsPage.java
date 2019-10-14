/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.im;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * 
 * 类说明：分页查询IM微信好友请求参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月28日
 */
public class FindImFriendsPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6782111667297272662L;
	
	/**
	 * 是否为老板账号:true-是,false-否
	 */
	private Boolean adminUserId;
	/**
	 * 商户编号，非空
	 */
	private String merchantNo;
	
	/**
	 * 导购编号，导购编号和导购微信号不能同时为空
	 */
	private String memberNoGm;
	
	/**
	 * 导购微信号，导购编号和导购微信号不能同时为空
	 */
	private String noWxGm;
	
	/**
	 * 最小版本号，非空
	 */
	private Long version;
	/**
	 * 搜索
	 */
	private String searchKey;
	
	
	
	public Boolean getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Boolean adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	/**
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * @param memberNoGm the memberNoGm to set
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * @return the noWxGm
	 */
	public String getNoWxGm() {
		return noWxGm;
	}

	/**
	 * @param noWxGm the noWxGm to set
	 */
	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	/**
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "FindImFriendsPage [adminUserId=" + adminUserId + ", merchantNo=" + merchantNo + ", memberNoGm="
				+ memberNoGm + ", noWxGm=" + noWxGm + ", version=" + version + ", searchKey=" + searchKey + "]";
	}

	

}
