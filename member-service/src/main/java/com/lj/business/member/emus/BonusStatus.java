package com.lj.business.member.emus;

/**
 * 
 * 类说明：红包发送状态
 *  
 * 
 * <p>
 *   
 * @Company: 扬恩科技有限公司
 * @author 刘红艳
 *   
 * CreateDate: 2019年2月20日
 */
public enum BonusStatus {
	/** 成功. */
	Y("成功"),

	/** 失败. */
	N("失败");
	
	BonusStatus(String name){
		this.name = name;
	}
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
