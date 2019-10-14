package com.lj.business.st.emus;

public enum TypeList {
	//运营分析
	SALE_FUNNEL("销售漏斗"),
	AREA_ORDER("区域成单分析"),
	CUSTOMER_BEHAVIOR_AS("客户行为分析"),
	CUSTOMER_PICTURE("客户画像"),
	FOLLOW("跟进分析"),
	AREA_CUSTOMER("区域客户分析"),
	//跟进分析
	/**
	 * 客户分析
	 */
	CLIENT_ANALYZE("客户分析"),
    /**
     * 客户行为
     */
	CLIENT_ACTION("客户行为"),
    /**
     * 社交分析
     */
	SOCIAL("社交分析"),
    /**
     * 素材分析
     */
	MATERIAL("素材分析"),
    /**
     * 客户分类
     */
	CLIENT_TYPE("客户分类"),
	//优秀导购
	HARDWORKING_AWARD("最勤快奖"),
	CUSTOMER_MAX("客户最多奖"),
	NEW_CUSTOMER_MAX("新增客户最多奖"),
	BEST_SALES("最牛销售奖"),
	EXTRUDE_FOLLOW("跟进效果突出奖"),
	ACTIVITY_AWARD("活动奖"),
	//日工作简报
	SALE("销售额"),
	INTENTION("意向客户数"),
	SUCCESS("成单客户数"),
	NEW("新增客户数"),
	GIVEUP("暂停跟进数"),
	PM_FOLLOW_NUM("跟进客户数"),
	FOLLOW_TIMES("跟进次数"),
	ORDER_RATIO("成单转换率"),
	//运营日报
	TOTAL_CUSTOMER("总客户量"),
	VISIT_CUSTOMER("到店体验量"),
	ORDER("成单量"),
	NEW_CUSTOMER("新增客户量"),
	INTENTION_CUSTOMER("意向客户量"),
	ABANDON_CUSTOMER("放弃客户")
	;
    TypeList(String name){
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
