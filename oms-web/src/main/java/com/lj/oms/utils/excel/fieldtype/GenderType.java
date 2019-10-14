package com.lj.oms.utils.excel.fieldtype;

import com.lj.business.member.emus.Gender;



/**
 * 
 * 
 * 类说明：性别类型转换
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月19日
 */
public class GenderType {

	/**
	 * 获取对象值（导入）
	 */
//	public static Object getValue(String val) {
//		for (Gender e : Gender.values()){
//			if (val.equals(e.getName())){
//				return e;
//			}
//		}
//		return null;
//	}

	/**
	 * 获取对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((Gender)val) != null){
			Gender gender =	(Gender)val;
			for (Gender e : Gender.values()){
				if (gender.equals(e)){
					return e.getName();
				}
			}
		}
		return "";
	}
	
	
}
