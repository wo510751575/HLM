package com.lj.business.cp.emus;

/**
 * 
 * 
 * 类说明：使用范围
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
public enum UseScope {
	ALL("全店铺"),
	ASSIGN("指定店铺");
	
	UseScope(String name){
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
