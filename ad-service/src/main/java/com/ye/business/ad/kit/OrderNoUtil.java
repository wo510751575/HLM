package com.ye.business.ad.kit;

import java.util.Date;
import java.util.Random;

public class OrderNoUtil {

	
	/**
	 * 生成18位流水号
	 * @return
	 */
	public static String getOrderNo(){
		long time = new Date().getTime();
		Random ra = new Random();
		int nextInt = ra.nextInt(999999)+100000;
		String str = (time+"" + nextInt + "").substring(0,18);
		return str;
		
	}
}
