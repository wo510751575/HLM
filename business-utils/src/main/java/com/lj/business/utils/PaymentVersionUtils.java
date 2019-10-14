package com.lj.business.utils;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class PaymentVersionUtils {
	/***
	 * 判断comparedVersion是否比oriVersion新
	 * 更新则返回true，否则false
	 * @param oriVersion
	 * @param comparedVersion
	 */
	public static boolean isUpdatedVersion(String oriVersion,String comparedVersion){
		if(comparedVersion !=null && oriVersion!=null && !comparedVersion.equals("") && !oriVersion.equals("")) {
		     return version2long(comparedVersion)>version2long(oriVersion);
		}else {
			return false;
		}
	}
	/***
	 * 支付的正式对外版本格式必须为x.y.z
	 * （x大项目升级才更新）
	 * （y每次常规迭代升级更新）
	 * （z每次迭代后的bug升级）
	 * @param version
	 * @return
	 */
	public static long version2long(String version){
		long versionLong = 0;
		long multiple = 1000000;//第一个版本位乘以百万，后面每次除以100来相乘
		for(String str : version.split("\\.")){
			versionLong += multiple*Long.valueOf(str);
			multiple = multiple/100;
		}
		return versionLong;
	}
	
	public static void main(String args []){
		System.out.println(version2long("182"));
		System.out.println(isUpdatedVersion("182","186"));
	}
}
