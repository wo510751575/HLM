package com.lj.business.member.emus;

/**
 * 
 * 
 * 类说明：添加好友状态
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月25日
 */
public enum AddFriendsStatus {
	
	/**
	 * 通过
	 */
	Y("通过"),
	
	/**
	 * 等待验证
	 */
	N("等待验证"),
	
	/**
	 * 失败
	 */
	F("失败"),
	
	/**
	 * 过期
	 */
	E("过期"),
	;
	
	private String name;

	AddFriendsStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
