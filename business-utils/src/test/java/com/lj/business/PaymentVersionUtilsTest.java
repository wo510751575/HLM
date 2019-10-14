package com.lj.business;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import com.lj.business.utils.PaymentVersionUtils;
import com.lj.base.core.hardware.LocalMachineInfo;
import com.lj.base.core.util.GUID;
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
 * @author 罗书明
 *   
 * CreateDate: 2017年7月14日
 */
public class PaymentVersionUtilsTest {
	public static void main(String []args){
		boolean isUpdated = PaymentVersionUtils.isUpdatedVersion("1.0.0", "1.0.1");
		System.out.println(isUpdated);
		
		System.out.println(LocalMachineInfo.getAddressIPv4()[3]);
		System.out.println(GUID.generateByIP());
	}
}
