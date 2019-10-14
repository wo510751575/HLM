package com.lj.business.api.spring;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lj.base.core.util.DateUtils;

/**
 * 
 * 
 * 类说明：返回客户端参数时，将特殊类型转为指定格式字符串
 * 1、日期类型转为yyyy-MM-dd HH:mm:ss
 * 2、长整型取toString()
 * 
 * <p/>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class CustomObjectMapper extends ObjectMapper {/*

	public CustomObjectMapper(){  
        CustomSerializerFactory factory = new CustomSerializerFactory();
        // 日期类型
        factory.addGenericMapping(Date.class, new JsonSerializer<Date>(){  
            @Override  
            public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {  
            	if(value == null) {
            		jsonGenerator.writeString(null); 
            	} else {
	                jsonGenerator.writeString(DateUtils.formatDate(value, DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
            	}
            }  
        });  
        // 长整型
        factory.addGenericMapping(Long.class, new JsonSerializer<Long>(){
        	 @Override  
             public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {  
        		 if(value == null) {
        			 jsonGenerator.writeString(null); 
             	 } else {
             		 jsonGenerator.writeString(value.toString());
             	 }
             }  
        });
        this.setSerializerFactory(factory);  
    }  
*/}
