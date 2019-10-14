package com.lj.business.st.emus;

/**
 * 
 * 
 * 类说明：统计维度
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
public enum DimensionSt {
	/**
	 * 商户
	 */
    MERCHANT("商户"),
    /**
     * 区域
     */
    AREA("区域"),
    /**
     * 省份
     */
    PROVINCE("省份"),
    /**
     * 门店
     */
    SHOP("门店"),
    /**
     * 导购
     */
    GUID("导购")
	;
	
	DimensionSt(String name){
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
