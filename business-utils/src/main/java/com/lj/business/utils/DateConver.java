package com.lj.business.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateConver implements Converter {
	 private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	  
	    /** 
	     *  要将字符串的value转换为java.util.Date类型的值 
	     *  targetClass 第一个参数 是你的目标类型 
	     *   value   要转换的值， 
	     *   Object   把要转回的值，返回出去就ok了 
	     */  
	    public Object convert(Class targetClass, Object value) {  
	  
	        if (targetClass != Date.class) {  
	            return null;  
	        }  
	        try {  
	            if (value instanceof String) {  
	                String v = (String) value;  
	  
	                return format.parse(v);  
	            }  
	        } catch (ParseException e) {  
	  
	            e.printStackTrace();  
	        }  
	  
	        return null;  
	    }  
}
