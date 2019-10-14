package com.lj.oms.utils.excel.fieldtype;

import com.lj.business.member.emus.Gender;
import com.lj.business.member.emus.MemerSourceType;



/**
 * 
 * 
 * 类说明：客户来源转换
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年8月10日
 */
public class MemerSrcType {

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
		if (val != null && ((MemerSourceType)val) != null){
			MemerSourceType item =	(MemerSourceType)val;
			for (MemerSourceType e : MemerSourceType.values()){
				if (item.equals(e)){
					return e.getName();
				}
			}
		}
		return "";
	}
	
	
}
