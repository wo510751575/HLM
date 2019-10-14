package com.lj.business.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
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
 * @author 邹磊
 *   
 * CreateDate: 2017年7月1日
 */
public class MessageActionTest extends SpringTestCase {

	@Test
	public void testSmsCodeSend() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("mobile", "15889399351");
		ApiHelp.doPost("/sms/codeSend.do", businessParamMap);
	}
	
	@Test
	public void testSmsCodeVerify() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("mobile", "15889399351");
		businessParamMap.put("smsCode", "7651");
		businessParamMap.put("processFlag", Boolean.TRUE);
		ApiHelp.doPost("/sms/codeVerify.do", businessParamMap);
	}
	
	@Test
	public void testMyMessageList() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("createDate", "2015-10-01 10:00:00");
		businessParamMap.put("start", "0");
		businessParamMap.put("limit", "10");
		ApiHelp.doPost("/msg/myMessageList.do", businessParamMap);
	}
}
