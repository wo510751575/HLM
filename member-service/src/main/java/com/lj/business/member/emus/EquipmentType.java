package com.lj.business.member.emus;
/**
 * 
 * 
 * 类说明：移动设备类型
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年4月17日
 */
public enum EquipmentType {
	
	/** IPHONE. */
	IPHONE("iPhone"),

	/** ANDROID_PHONE. */
	ANDROID_PHONE("安卓手机");
	
	EquipmentType(String name){
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
