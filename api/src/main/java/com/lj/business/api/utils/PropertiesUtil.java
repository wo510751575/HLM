package com.lj.business.api.utils;

import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * 类说明：属性文件读取工具类
 * <p>
 * 详细描述：
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2017年12月7日
 */
public class PropertiesUtil {
	Logger logger = Logger.getLogger(PropertiesUtil.class);
	
	private Properties props;
	
	public PropertiesUtil(String fileName) {
		readProperties(fileName);
	}
	/**
	 * 加载配置文件
	 * @param fileName
	 */
	private void readProperties(String fileName) {
		try {
			props = new Properties();
			InputStreamReader inputStream = new InputStreamReader(
					getClass().getClassLoader().getResourceAsStream(fileName), "UTF-8");
			props.load(inputStream);
			if(inputStream!=null)inputStream.close();
		} catch (Exception e) {
			logger.error("readProperties",e);
		}
	}

	/**
	 * 根据key读取对应的value
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return props.getProperty(key);
	}

	/**
	 * 得到所有的配置信息
	 * @return
	 */
	public Map<String, String> getAll() {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<?> enu = props.propertyNames();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			String value = props.getProperty(key);
			map.put(key, value);
		}
		return map;
	}
}
