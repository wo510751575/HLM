package com.lj.business.weixin.emus;
/**
 * 朋友圈信息类型
 * @author ldq
 *
 */
public enum FriendsInfoType {
	PIC("1","图片"),MESSAGE("2","文字"),SHARE("3","分享"),MUSIC("4","歌曲"),SHARE_2("5","MV分享"),VIDEO("15","视频");
	private String type;
	private String value;
	
	/**
	 * 
	 * @param value		类型编码
	 * @param type		类型说明
	 */
	private FriendsInfoType(String value, String type) {
		this.type = type;
		this.value = value;
	}
	
	/**
	 * 
	 *
	 * 方法说明：此方法返回的朋友圈类型说明（中文）
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年3月16日
	 *
	 */
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
		for (FriendsInfoType infoType: FriendsInfoType.values()) {
				if(infoType.getValue().equals(type)){
					return true;
				}
		}
		return false;
	}
	
}
