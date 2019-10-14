/**
 * 
 */
package com.ye.business.hx.emus;

/**
 * 
 * 
 * 类说明：账单退款信息的REFUND_TYPE
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
public enum RefundType {
	/** 退款类型（ALL:整单退，ITEM：按项目数量退，PART:退一部分金额）*/
	ALL("整单退"),

	ITEM("按项目数量退"),
	
	PART("退一部分金额");
	
	RefundType(String name) {
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
