/**
 * 
 */
package com.ye.business.hx.emus;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 *   
 * CreateDate:  2019年4月17日
 */
public enum OptType {
	SVAE("保存"),
	
	PAY("收费"),

	DEBT("收欠费"),
	
//	REFUNDAPLLY("退款申请"),
	
	REFUND("退款");
 

	OptType(String name) {
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
