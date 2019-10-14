package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.text.MessageFormat;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.core.util.StringUtils;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;


/**
 * 
 * 
 * 类说明：测试类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年12月14日
 */
public class AyncSendMessageByNewMemberServiceTest extends SpringTestCase{

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	@Test
	public void testGreetings() throws Exception {
		for (int k = 0; k < 100; k++) {
			
			String greeting="";
			//获取一条随机问候话术
			Map<String, String> personalGreetingsMap = localCacheSystemParams.getSystemParamGroup("ms", "personalGreetings");
			String greetingKey="greeting"+((int)(personalGreetingsMap.size()*Math.random())+1);
			greeting=personalGreetingsMap.get(greetingKey);
			//获取随机数量和内容的问候表情
			Object[] arr=new Object[getOccur(greeting, "{")];
			arr[0]=StringUtils.toString("导购姓名");//导购姓名
			Map<String, String> greetingEmojiMap = localCacheSystemParams.getSystemParamGroup("ms", "greetingEmoji");
			int emojiSize=getOccur(greeting, "{")-1;//表情坑位数
			for (int i = 1; i <= emojiSize; i++) {
				arr[i]="";
				for (int j = 0; j < (int)(3*Math.random())+1; j++) {//暂定一个坑随机填1~3个表情
					String emojiKey="emoji"+((int)(greetingEmojiMap.size()*Math.random())+1);
					arr[i]+=greetingEmojiMap.get(emojiKey);
				}
			}
			//整合话术及表情,生成问候语
			greeting = MessageFormat.format(greeting, arr);
			System.out.println(greeting);
			
		}
	}
	
	public static int getOccur(String src,String find){
		  int o = 0;
		  int index=-1;
		  while((index=src.indexOf(find,index))>-1){
		   ++index;
		   ++o;
		  }
		  return o;
	 }
}