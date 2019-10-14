package com.lj.business.member.emus;

/**
 * 
 * 
 * 类说明：车发发默认分组类型
 *  
 * 	
 * <p>
 * 详细描述：枚举内容顺序请不要随便更改!!!!!
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年10月17日
 */
public enum CffPmTypeType {
	
	/**
	 * 未分组客户
	 */
	UNGROUP("未分组客户"),
	
	/**
	 * 普通客户
	 */
	CFF_NORMAL("普通客户"),
	/**
	 * 银卡客户
	 */
	CFF_SILVER("银卡客户"),
	/**
	 * 金卡客户
	 */
	CFF_GOLD("金卡客户"),
	/**
	 * 保险客户
	 */
	CFF_INSURANCE("保险客户"),
	;
	
	private String name;

	CffPmTypeType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
