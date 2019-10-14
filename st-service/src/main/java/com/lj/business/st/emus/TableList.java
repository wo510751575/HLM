package com.lj.business.st.emus;

public enum TableList {
	
	WORK_BR_DAY_LIST("日工作简报"),
	CF_ANALYZE_CHOOSE("跟进分析"),
	OPERATION_ANALYSIS_DAY_CHOOSE("运营分析"),
	BEST_GM_CHOOSE("优秀导购"),
	OPERATION_DAY_CHOOSE("运营日报");
	
	
	TableList(String name){
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
