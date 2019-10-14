package com.lj.business.st.emus;

/**
 * 
 * 
 * 类说明：优秀导购选择表（基础）:项目类型
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
public enum TypeListBestGmChoose {
	
	/**
	 * 最牛销售奖
	 */
	ZUI_NIU_XIAO_SHOU("最牛销售奖"),
    /**
     * 客户最多奖
     */
	KE_HU_ZUI_DUO("客户最多奖"),
    /**
     * 新增客户最多奖
     */
	XIN_ZENG_KE_HU("新增客户最多奖"),
    /**
     * 最勤快奖
     */
	QIN_KUAI("最勤快奖"),
    /**
     * 跟进效果突出奖
     */
	GEN_JIN("跟进效果突出奖"),
	 /**
     * 活动传播奖
     */
	HUO_DONG("活动传播奖"),
	;
	
	TypeListBestGmChoose(String name){
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
