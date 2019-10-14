package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.lj.oms.utils.excel.annotation.ExcelField;

public class CountGmAddFriendsDto implements Serializable{
	
   private static final long serialVersionUID = -8475436664919196436L;


	
	/**
	 * 
	 */
	@ExcelField(title="名称", align=2, sort=10)
	private String memberGmName;
	


	/**
	 * 数量
	 */
	@ExcelField(title="数量", align=2, sort=20)
	private String count;



	public String getMemberGmName() {
		return memberGmName;
	}



	public void setMemberGmName(String memberGmName) {
		this.memberGmName = memberGmName;
	}



	public String getCount() {
		return count;
	}



	public void setCount(String count) {
		this.count = count;
	}
}
