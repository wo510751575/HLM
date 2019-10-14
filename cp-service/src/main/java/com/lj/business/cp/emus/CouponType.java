package com.lj.business.cp.emus;

/**
 * 
 * 
 * 类说明：优惠券类型
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 杨恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年9月16日
 */
public enum CouponType {
	COUPON("优惠券"),
	CASH("现金券"),
	DISCOUNT("折扣券");
	
	CouponType(String name){
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
