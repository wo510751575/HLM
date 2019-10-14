package com.lj.business.member.dto.addfriends;

import java.io.Serializable;

public class FindAllotGuidMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1368637891232355934L; 

	/**
	 * 终端编号
	 */
	
	
	/**
	 * 导购微信
	 */
	private String noWxGm;
	
	/**
	 * 搜索字段
	 */
	private String searchVal;
	
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

	public String getSearchVal() {
		return searchVal;
	}

	public void setSearchVal(String searchVal) {
		this.searchVal = searchVal;
	}

	@Override
	public String toString() {
		return "FindAllotGuidMember [noWxGm=" + noWxGm + ", searchVal=" + searchVal + "]";
	}

}
