package com.lj.business.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.api.domain.msg.SmsCodeSenderRequest;
import com.lj.business.api.service.SmsCodeService;
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
 * CreateDate: 2017年8月18日
 */
public class SmsCodeServiceTest extends SpringTestCase {
	
	@Resource
	private SmsCodeService smsCodeService;

	
	/**
	 * 
	 *
	 * 方法说明：发送验证码短信
	 *
	 *
	 * @author 彭阳 CreateDate: 2017年8月18日
	 *
	 */
	@Test
	public void send() {
		SmsCodeSenderRequest request = new SmsCodeSenderRequest();
		request.setMobile("13501556403");
		request.setSenderName("测试");
		smsCodeService.send(request);
	}
	
	
	
}
