package com.lj.business.cf.emus;

/**
 * 
 * 
 * 类说明：维护类型
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 冯辉
 *   
 * CreateDate: 2017年7月14日
 */
public enum KeepType {
	
	PHONE("电话记录"),
	WECHAT("微信记录"),
	SMS("短信记录"),
	SHOP("门店接待"),
	SYSTEM("系统")
	;
	
	KeepType(String name){
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
