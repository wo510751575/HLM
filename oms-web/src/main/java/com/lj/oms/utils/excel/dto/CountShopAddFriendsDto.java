package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.lj.oms.utils.excel.annotation.ExcelField;

public class CountShopAddFriendsDto implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -8475436664919196435L;


	
	/**
	 * 
	 */
	@ExcelField(title="名称", align=2, sort=10)
	private String shopName;
	


	/**
	 * 数量
	 */
	@ExcelField(title="数量", align=2, sort=20)
	private String count;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
