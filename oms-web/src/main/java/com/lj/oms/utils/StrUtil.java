/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.oms.utils;

/**
 * 
 * 类说明：公用字符串处理。
 * 
 * <p>
 *   
 * @Company: 扬恩科技有限公司
 *   
 * CreateDate: 2018年4月4日
 */
public class StrUtil {
	/**
	 * 方法说明：
	 * @param c:拼接字符
	 * @param num：数据源
	 * @param size ：长度
	 * @return
	 *
	 * @author lhy  2017年11月24日
	 *
	 */
	public static String leftJoin(char c,int num,int size){
		String src=(num+1)+"";
		StringBuilder sb=new StringBuilder("");
		int i=size-src.length();
		for (int j = 0; j < i; j++) {
			sb.append(c);
		}
		sb.append(src);
		return sb.toString();
	}
}
