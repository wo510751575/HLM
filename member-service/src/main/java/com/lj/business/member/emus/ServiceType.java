package com.lj.business.member.emus;

/**
 * 
 * 
 * 类说明：服务类型
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年9月23日
 */
public enum ServiceType {
	
	/** 服务人员 */
	SERVICE_PERSON("服务人员"),
	
	/** 人员作品 */
	PERSON_PRODUCT("人员作品"),

	/** 服务产品 */
	SERVICE_PRODUCT("服务产品");
	
	private String name;
	
	ServiceType(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
