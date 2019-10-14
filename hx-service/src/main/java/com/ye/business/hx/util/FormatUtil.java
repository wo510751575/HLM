package com.ye.business.hx.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class FormatUtil {

	/**
	 * 姓名格式化
	 * 
	 * @param name
	 * @return
	 */
	public static String formatName(String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}
		return name.substring(0, 1) + "**";
	}

	/**
	 * 格式化手机号码
	 * 
	 * @param phone
	 * @return
	 */
	public static String formatMobile(String phone) {
		if (StringUtils.isEmpty(phone)) {
			return null;
		}
		return phone.substring(0, 3) + "****"
				+ phone.substring(7, phone.length());
	}

	/**
	 * 格式化诊所名称
	 * 
	 * @param phone
	 * @return
	 */
	public static String formatClinic(String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}
		if (name.length() >= 2) {
			return "***" + name.substring(name.length() - 2, name.length());
		} else {
			return "***" + name;
		}
	}
	
	/**
	 * 验证手机号码正确性
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean matchMobile(String mobile) {
		if (ObjectUtil.isEmpty(mobile))
			return false;
		String regex = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mobile);
		return m.matches();
	}
}
