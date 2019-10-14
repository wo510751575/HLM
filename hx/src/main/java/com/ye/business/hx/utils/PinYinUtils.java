/**
 * 
 */
package com.ye.business.hx.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 
 * 类说明：拼音工具类。
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年4月15日
 */
public class PinYinUtils {
	private static HanyuPinyinOutputFormat format;

	static {
		format = new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	}
	// 转换单个字符

	public static String getCharacterPinYin(char c) {
		String[] pinyin = null;
		try {
			pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		// 如果c不是汉字，toHanyuPinyinStringArray会返回null或size=0
		if (pinyin == null ||pinyin.length==0 ) {
			return null;
		}
		return pinyin[0];
	}

	// 转换一个字符串
	public static String getStringPinYin(String str) {
		if(str==null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		String tempPinyin = null;
		for (int i = 0; i < str.length(); ++i) {
			tempPinyin = getCharacterPinYin(str.charAt(i));
			if (tempPinyin == null) {
				// 如果str.charAt(i)非汉字，则保持原样
				sb.append(str.charAt(i));
			} else {
				sb.append(tempPinyin);
			}
		}
		return sb.toString().toLowerCase();
	}

	/**
	 * 返回小写，获取中文的首字母拼音，如果是英文则原样.
	 * <br/>如“你好” 返回nh
	 * <br/>如“Hello” 返回hello
	 * <br/>如“Hello,测试” 返回hello,cs
	 * @param str
	 * @return
	 */
	public static String getStringFirstPinYin(String str) {
		if(str==null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		String tempPinyin = null;
		for (int i = 0; i < str.length(); ++i) {
			tempPinyin = getCharacterPinYin(str.charAt(i));
			if (tempPinyin == null) {
				// 如果str.charAt(i)非汉字，则保持原样
				sb.append(str.charAt(i));
			} else {
				sb.append(tempPinyin.substring(0, 1));
			}
		}
		return sb.toString().toLowerCase();
	}

	
	public static void main(String[] args){
		// 中英文混合的一段文字
		String str = "荆溪白石出，Hello 天寒红叶稀。android 山路元无雨，What's up?   空翠湿人衣。";
		String strPinyin = getStringPinYin(str);
		System.out.println(strPinyin);
		System.out.println(getStringFirstPinYin(str));
	}
}
