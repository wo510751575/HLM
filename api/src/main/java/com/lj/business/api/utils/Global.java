package com.lj.business.api.utils;

import java.util.Map;
import java.util.ResourceBundle;

import com.google.common.collect.Maps;

/**
 * 
 * 
 * 类说明：全局配置类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年10月11日
 */
public class Global {
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 属性文件加载对象
	 */
	private static ResourceBundle config = ResourceBundle.getBundle("config");
	
	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = config.getString(key);
			map.put(key, value);
		}
		return value;
	}
}
