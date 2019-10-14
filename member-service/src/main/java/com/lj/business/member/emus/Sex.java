package com.lj.business.member.emus;

/**
 * 
 * 
 * 类说明：性别
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年4月12日
 */
public enum Sex {
	
	/** 男 */
	MALE("男"),
	
	/** 女 */
	FEMALE("女"),
	
	/** 未知 */
	UNKNOWN("未知");
	
	private String name;
	
	Sex(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
