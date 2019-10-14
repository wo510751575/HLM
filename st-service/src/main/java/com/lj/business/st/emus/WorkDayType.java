package com.lj.business.st.emus;

public enum WorkDayType {
	
    SALE("销售额"),
    INTENTION_CUSTOMER("意向客户数"),
    ORDER("成单客户数"),
    NEW_CUSTOMER("新增客户数"),
    ABANDON_CUSTOMER("暂停跟进数"),
    TOTAL_CUSTOMER("总客户数"),
	VISIT_CUSTOMER("到店体验数"),
	PM_FOLLOW_NUM("跟进客户数"),
	FOLLOW_TIMES("跟进次数"),
	ORDER_RATIO("成单转换率"),
	;
	
    WorkDayType(String name){
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
