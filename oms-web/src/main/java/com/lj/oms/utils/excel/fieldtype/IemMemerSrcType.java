package com.lj.oms.utils.excel.fieldtype;

import com.lj.business.member.emus.IemMemerSource;



/**
 * 
 * 
 * 类说明：积分商城-会员来源转换
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2018年3月27日
 */
public class IemMemerSrcType {

	/**
	 * 获取对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((IemMemerSource)val) != null){
		    IemMemerSource item = (IemMemerSource)val;
			for (IemMemerSource e : IemMemerSource.values()){
				if (item.equals(e)){
					return e.getValue();
				}
			}
		}
		return "";
	}
	
	
}
