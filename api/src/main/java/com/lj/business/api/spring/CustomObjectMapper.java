package com.lj.business.api.spring;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * 
 * 类说明：为SpringMVC里默认序列化使用的 com.fasterxml.jackson.databind.ObjectMapper 设置其属性 
 * SerializationFeature.FAIL_ON_EMPTY_BEANS 为false。 
貌似没办法直接在XML配置里设置。只能自定义 com.fasterxml.jackson.databind.ObjectMapper 类进行设置，然后再配置xml
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class CustomObjectMapper extends ObjectMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -24762560654870160L;

	public CustomObjectMapper(){  
		//设置 值为null时 参数不参与json解析，字段不返回
		this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 设置 SerializationFeature.FAIL_ON_EMPTY_BEANS 为 false
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }  
}
