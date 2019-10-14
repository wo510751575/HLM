/**
 * 
 */
package com.ye.business.hx.emus;

/**
 * 
 * 
 * 类说明：账单退款信息的Refund_Status
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
public enum RefundStatus {
	/** 状态（UNCHECK待审批，UNPAY待退费，CHKREFUS:已拒绝，FINISH已退费）*/
	UNCHECK("待审批"),

	UNPAY("待退费"),
	
	CHKREFUS("已拒绝"),
	
	FINISH("已退费");
	
	RefundStatus(String name) {
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
