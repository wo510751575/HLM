package com.lj.business.st.emus;
/**
 * 
 * 
 * 类说明：跟进分析统计 
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年8月3日
 */
public enum TextType {
  /**
   * 客户分析
   */
  CLIENT_ANALYZE("客户分析"),
  /**
   * 客户行为
   */
  CLIENT_BEHAVIOR("客户行为"),
  /**
   * 客户分类
   */
  CLIENT_TYPE("客户分类"),
  /**
   * 素材分析
   */
  MATERIAL_ANALYZE("素材分析"),
  /**
   * 社交分析
   */
  SOCIAL_ANALYZE("社交分析"),
  /**
   * 导购任务完成度分析
   */
  GM_WORK_ANALYZE("导购完成度分析"),
  /**
   * 店长任务完成度分析
   * @param name
   */
  SHOP_WORK_ANALYZE("店长完成度分析");
  
  TextType(String name){
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
