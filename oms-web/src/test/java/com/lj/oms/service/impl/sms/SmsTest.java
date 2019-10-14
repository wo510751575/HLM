/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.oms.service.impl.sms;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lj.messagecenter.dto.sms.SmsSenderRequest;
import com.lj.messagecenter.emus.SendTarget;
import com.lj.messagecenter.emus.SmsAppId;
import com.lj.messagecenter.service.ISmsInfoService;
import com.lj.oms.service.impl.BaseJunitTest;
import com.lj.oms.utils.excel.ImportExcel;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年7月9日
 */
public class SmsTest extends BaseJunitTest {

	private static final Logger logger = LoggerFactory.getLogger(SmsTest.class);

	@Resource
	private ISmsInfoService smsInfoService;
	
	private static final String CONTENT_TEMPLATE = "感谢您购买芝华仕头等舱沙发！为感谢新老客户，根据您的购买记录，特赠送您count积分,赶紧去http://mec.manwahgroup.com/api/iem/index兑换您心仪的小礼品吧！【芝华仕头等舱沙发】";
	
	/**
	 * 
	 *
	 * 方法说明：批量发送特殊短信
	 *
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月6日
	 *
	 */
	@Test
	public void testSmsSmsByExcel() throws InvalidFormatException, IOException, InstantiationException, IllegalAccessException {
		File file = new File("D:\\扬恩\\项目\\积分商城\\老客户激活模板.xlsx");
		ImportExcel ei = new ImportExcel(file, 1, 0);
		List<SmsExcelDto> list = ei.getDataList(SmsExcelDto.class);
		
		SmsSenderRequest sms = new SmsSenderRequest();
		sms.setAppId(SmsAppId.IEM.name());					// 应用标识：积分商城
		sms.setSendTarget(SendTarget.MEMBER.getStatus());	// 发送目标：客户
		int index = 1;
		for(SmsExcelDto smsExcelDto : list) {
			logger.info("正在发送第" + (index++) + "条短信，客户手机号：" + smsExcelDto.getMobile());
			sms.setMobile(smsExcelDto.getMobile());
			sms.setContent(new String(CONTENT_TEMPLATE).replace("count", smsExcelDto.getCount()));
			smsInfoService.sendSms(sms);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：批量发送特殊短信
	 *
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月6日
	 *
	 */
	@Test
	public void testSendSmsBatch() {
		Map<String, String> senderMap = new HashMap<String, String>();
		senderMap.put("15889399351", "10000");
		SmsSenderRequest sms = new SmsSenderRequest();
		Set<Entry<String, String>> set = senderMap.entrySet();
		sms.setAppId(SmsAppId.IEM.name());					// 应用标识：积分商城
		sms.setSendTarget(SendTarget.MEMBER.getStatus());	// 发送目标：客户
		int index = 1;
		for(Entry<String, String> entry : set) {
			logger.info("正在发送第" + (index++) + "条短信，客户手机号：" + entry.getKey());
			sms.setMobile(entry.getKey());
			sms.setContent(new String(CONTENT_TEMPLATE).replace("count", entry.getValue()));
			smsInfoService.sendSms(sms);
		}
	}
}
