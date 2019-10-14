package com.lj.business.weixin.emus;

/**
 * 朋友圈谁可以看类型
 */
public enum WhoType {
	/**
	 * 公开
	 */
	OPEN("1","公开"),
	/**
	 * 私密
	 */
	PRIVATE("2","私密"),
	/**
	 * 部分可见
	 */
	PART("3","部分可见"),
	/**
	 * 不给谁看
	 */
	DO_NOT("4","不给谁看"),
	;
	
	private String value;
	private String name;
	/**
	 * 
	 * @param value		类型编码
	 * @param type		类型说明
	 */
	private WhoType(String value, String name) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	/**
	 * 
	 *
	 * 方法说明：此方法返回的朋友圈类型编码
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年3月16日
	 *
	 */
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public static boolean getType(String type){
		for (WhoType infoType: WhoType.values()) {
				if(infoType.getValue().equals(type)){
					return true;
				}
		}
		return false;
	}
	
}
