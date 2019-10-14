package com.lj.business.st.emus;

public enum OperationAnalysisEnum {

	SALE_FUNNEL("销售漏斗"),
	AREA_ORDER("区域成单分析"),
	CUSTOMER_BEHAVIOR_AS("客户行为分析"),
	CUSTOMER_PICTURE("客户画像"),
    FOLLOW("跟进分析"),
	AREA_CUSTOMER("区域客户分析"),
	;

    OperationAnalysisEnum(String name){
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
