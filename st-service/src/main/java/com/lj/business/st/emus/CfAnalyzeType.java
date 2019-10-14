package com.lj.business.st.emus;

/**
 * 
 * 
 * 类说明：项目类型
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
public enum CfAnalyzeType {
	
	
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
	CLIENT_TYPE("客户分类")
	;
	
	CfAnalyzeType(String name){
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
