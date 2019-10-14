package com.lj.business.cp.emus;

/**
 * 
 * 
 * 类说明：发券条件
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 杨恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年9月16日
 */
public enum ToCoupon {
	NONE("无"),
	INVITE("邀请人数");
	
	ToCoupon(String name){
		this.name=name;
	}
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
