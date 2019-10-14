package com.lj.business.cp.emus;

/**
 * 
 * 
 * 类说明：分享状态
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 杨恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2018年1月27日
 */
public enum ShareStatus {
	
    GUID("导购分享"),
    
    MEMBER("客户分享");

	ShareStatus(String name){
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
