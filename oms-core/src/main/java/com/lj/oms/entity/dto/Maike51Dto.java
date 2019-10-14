package com.lj.oms.entity.dto;

import java.io.Serializable;

/**
 * 
 * 
 * 类说明：卖客星球DTO
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2018年1月29日
 */
public class Maike51Dto implements Serializable{

	public static final String ROLE_KEY = "maike51";
	public static final String OFFICE_NAME_PRE = "卖客星球_";
	/**
	 * 
	 */
	private static final long serialVersionUID = 3482168215231758893L;
	private String userNo;
	private String phone;
	private String nickname;
	private String shopId;
	/**
	 * @return the userNo
	 */
	public String getUserNo() {
		return userNo;
	}
	/**
	 * @param userNo the userNo to set
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the shopId
	 */
	public String getShopId() {
		return shopId;
	}
	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Maike51Dto [userNo=" + userNo + ", phone=" + phone
				+ ", nickname=" + nickname + ", shopId=" + shopId + "]";
	}
	
}
