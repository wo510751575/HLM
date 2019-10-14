package com.lj.business.st.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 类说明：日期工具类
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 * 
 *         CreateDate: 2018年03月05日
 */
public class DateUtil {

	/**
	 * 获取某天的开始和结束时间
	 * 
	 * @return
	 */
	public static Map<String, Object> getDayBeginAndEnd(Date date) {
		Map<String, Object> map = new HashMap<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		map.put("beginTime", calendar.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		map.put("endTime", calendar.getTime());
		return map;
	}
    
}
