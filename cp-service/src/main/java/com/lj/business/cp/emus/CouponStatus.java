package com.lj.business.cp.emus;

/**
 * 
 * 
 * 类说明：优惠券状态
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 杨恩科技有限公司
 * @author 杨杰
 *   
 * CreateDate: 2017年9月18日
 */
public enum CouponStatus {
	UNUSED("未使用"),
	USED("已使用"),
	EXPIRED("已过期"),
	NONE("优惠券不存在"),
	IS_GET("已领取")
	;
	
	CouponStatus(String name){
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
