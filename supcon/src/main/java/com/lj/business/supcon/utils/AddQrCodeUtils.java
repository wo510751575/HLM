package com.lj.business.supcon.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 类说明：判断号码规则
 * <p>
 * 详细描述：
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2018年1月11日
 */
public class AddQrCodeUtils {
	
	/**
	 * 方法说明：判断是否为手机号,手机号规则可能出现更新,后期动态调整
	 * @param phone
	 * @return
	 * @author 李端强 CreateDate: 2018年1月11日
	 */
	public static boolean isMobile(String phone) {
		boolean ret =  false;
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
		if(phone.length() != 11){
			return ret;
		}else {
			Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
		}
	}
	
}
