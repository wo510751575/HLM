package com.lj.business.member.emus;

/**
 * 
 * 
 * 类说明：美容美发默认分组类型
 *  
 * 	
 * <p>
 * 详细描述：枚举内容顺序请不要随便更改!!!!!
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年9月22日
 */
public enum HcPmTypeType {
	
	/**
	 * 未分组客户
	 */
	UNGROUP("未分组客户"),
	
	/**
	 * 男性顾客
	 */
	HC_MALE("男性顾客"),
	/**
	 * 女性顾客
	 */
	HC_FEMALE("女性顾客"),
	/**
	 * 高频顾客
	 */
	HC_HFRE("高频顾客"),
	/**
	 * 高消费顾客
	 */
	HC_HCON("高消费顾客"),
	;
	
	private String name;

	HcPmTypeType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
