package com.lj.business.cp.emus;

/**
 * 
 * 
 * 类说明：是否实名
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
public enum RealName {
	YES("实名"),
	NO("不实名");
	
	RealName(String name){
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
