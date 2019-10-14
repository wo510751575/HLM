package com.lj.oms.utils;

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
	
	/**
	 * 
	 *
	 * 方法说明：将时间 startTimes 至0时0分0秒 ，endTime 至 23时59分 59秒
	 *
	 * @param startTimes
	 * @param endTimes
	 * @return
	 */
	public static Map<String,Date> initDate(Date startTimes,Date  endTimes) {
		Map<String,Date> map = new HashMap<>(2);
		if(startTimes !=null){
			Calendar calendar =  com.ape.common.utils.DateUtils.toCalendar(startTimes);
			//将小时至0  
			calendar.set(Calendar.HOUR_OF_DAY, 0);  
			//将分钟至0  
			calendar.set(Calendar.MINUTE, 0);  
			//将秒至0  
			calendar.set(Calendar.SECOND,0);  
			Date startTime= calendar.getTime(); 
			map.put("startTime", startTime);
		}
		if( endTimes !=null){
			Calendar calendar =  com.ape.common.utils.DateUtils.toCalendar(endTimes);
			//将小时至23  
			calendar.set(Calendar.HOUR_OF_DAY, 23);  
			//将分钟至59  
			calendar.set(Calendar.MINUTE, 59);  
			//将秒至59  
			calendar.set(Calendar.SECOND,59);  
			
			Date endTime= calendar.getTime();
			
			map.put("endTime", endTime);
		}
		return map;
	}
    
}
