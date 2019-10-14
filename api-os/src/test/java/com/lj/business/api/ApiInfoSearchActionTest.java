package com.lj.business.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;

/**
 * 
 * 类说明：对接测试对外接口
 * <p>
 * 详细描述：测试用例
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2017年12月29日
 */
public class ApiInfoSearchActionTest extends SpringTestCase{
	
	
	/**
	 * 方法说明1：测试提供外部接口,根据手机号查询客户的微信号
	 * @author 李端强 CreateDate: 2017年12月29日
	 */
	@Test
	public void getBaseInfoByMobile() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("mobile", "18566799796");
		ApiHelp.doPost("apiInfoSearch/getBaseInfoByMobile.do", businessParamMap);
	};
	
	/**
	 * 方法说明2：测试LOHO根据条件筛选之后发送微信消息 produces="application/json;charset=utf-8"
	 * @author 李端强 CreateDate: 2017年12月29日
	 */
	@Test
	public void sendWxByCondition() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("mobileGM", "18566799796");//导购手机号
		businessParamMap.put("mobile", "18566799796");//客户手机号
		businessParamMap.put("msg", "test_msg");
		ApiHelp.doPost("apiInfoSearch/sendWxByCondition.do", businessParamMap);
	};
	
	/**
	 * 方法说明3：测试通过手机号添加微信好友
	 * @author 李端强 CreateDate: 2017年12月29日
	 */
	@Test
	public void addFriendByMobile() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("mobileGM", "18922895374");//导购手机号
		businessParamMap.put("mobile", "15274949965");//客户手机号
		businessParamMap.put("currentMerchentNo", "fcecbfa097944565a58134d170f474af");//商户编号
		ApiHelp.doPost("apiInfoSearch/addFriendByMobile.do", businessParamMap);
	};
	
}
