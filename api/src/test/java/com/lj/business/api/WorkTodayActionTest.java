package com.lj.business.api;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.api.controller.member.WorkTodayAction;
import com.lj.business.api.dto.FindWorkTodayInfoShop;
import com.lj.business.api.util.TestHelp;
/**
 * 
 * 
 * 类说明：问候信息api测试
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 邹磊
 *   
 * CreateDate: 2017年7月22日
 */
public class WorkTodayActionTest extends SpringTestCase{
	
	
	@Autowired
	private WorkTodayAction workTodayAction;
	/**
	 * 
	 *
	 * 方法说明：通过日期查看所有问候客户信息
	 *
	 * @throws ParseException
	 *
	 * @author 邹磊 CreateDate: 2017年7月24日
	 *
	 */
	@Test
	public void findGreetClientForDay() throws ParseException {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("memberNoGm", "d7b963349b8f4bcbbed9a36fe41ae626");
		//Date date = DateUtils.parseDate("2017-07-20", DateUtils.PATTERN_yyyy_MM_dd);
		//String string = DateUtils.formatDate(date,DateUtils.PATTERN_yyyy_MM_dd);
		//System.out.println(date);
		businessParamMap.put("createDate", "2017-07-20");

		ApiHelp.doPost("work/findGreetClientForDay.do", businessParamMap);
	}

	@Test
	public void findActivitys() throws ParseException {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("merchantNo", "LJ_43fc6d29f6fc424b9fbcd52d567f88a5");
		ApiHelp.doPost("work/findActivitys.do", businessParamMap);
	}


	/**
	 * 
	 *
	 * 方法说明：获取导购今日工作首页详情
	 *
	 * @throws ParseException
	 *
	 * @author 彭阳 CreateDate: 2017年7月24日
	 *
	 */
	@Test
	public void todayGm() throws ParseException {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("merchantNo",TestHelp.merchantNo_test);
		businessParamMap.put("memberNoGm",TestHelp.memberNoGm_test);
		ApiHelp.doPost("work/todayGm.do", businessParamMap);
	}
	
//	{
//	    "cfCode": "",
//	    "expResult": "Y",
//	    "cfNo": "f6e74260ec714c7aad94dd1c16d03d4a",
//	    "merchantNo": "e79d975846ee41ba8c3c55738fda66a9",
//	    "memberNo": "167039936db048349d39b8f2413ca578",
//	    "memberName": "魔力乐高",
//	    "memberNoGm": "d7b963349b8f4bcbbed9a36fe41ae626",
//	    "memberNameGm": "深圳安家店-建才",
//	    "shopNo": "LJ_c269478af52646b692fcc48deb10a7ad",
//	    "shopName": "南山分店",
//	    "expFb": "[{'text':'jjjjajajaja','time':'2017年08月23日 16:53','type':0}]",
//	    "expTime": "2017-08-23 16:52:00",
//	    "failReason": ""
//	}
	
	@Test
	public void todayWorkExp() throws ParseException {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("cfCode","");
		businessParamMap.put("expResult","Y");
		businessParamMap.put("cfNo","f6e74260ec714c7aad94dd1c16d03d4a");
		businessParamMap.put("merchantNo","e79d975846ee41ba8c3c55738fda66a9");
		businessParamMap.put("memberNo","167039936db048349d39b8f2413ca578");
		businessParamMap.put("memberName","魔力乐高");
		businessParamMap.put("memberNoGm","d7b963349b8f4bcbbed9a36fe41ae626");
		businessParamMap.put("memberNameGm","深圳安家店-建才");
		businessParamMap.put("shopNo","LJ_c269478af52646b692fcc48deb10a7ad");
		businessParamMap.put("shopName","南山分店");
		businessParamMap.put("expFb","[{'text':'jjjjajajaja','time':'2017年08月23日 16:53','type':0}]");
		businessParamMap.put("expTime","2017-08-23 16:52:00");
		businessParamMap.put("failReason","");
		
		ApiHelp.doPost("clientExp/addClientExp.do", businessParamMap);
	}
	
	@Test
	public void todayShop() throws Exception {
		
//		memberNoGm=d7b963349b8f4bcbbed9a36fe41ae626, memberN
//				oShop=e7t9y3349b8f4bcbbed9as6fe41ae626, merchantNo=e79d975846ee41ba8c3c55738fda66a9
		
		FindWorkTodayInfoShop findWorkTodayInfoShop = new FindWorkTodayInfoShop();
		findWorkTodayInfoShop.setMemberNoGm("3f59ef3cd6514dd7887b93c88b08c2fb");
		findWorkTodayInfoShop.setMemberNoShop("LJ_31bdf059170446808c39467e0b8ef2ff");
		findWorkTodayInfoShop.setMerchantNo("1f2a6d3e3022448382734bc70c9384af");
		workTodayAction.todayShop(findWorkTodayInfoShop);
	}
	
	
}
