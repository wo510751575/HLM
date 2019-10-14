package com.lj.business.cp.emus;
/**
 * 
 * 
 * 类说明：是否已制券
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 杨恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2018年1月24日
 */
public enum IsProduce {
	YES("已制券"),
	NO("未制券");
	IsProduce(String name){
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
