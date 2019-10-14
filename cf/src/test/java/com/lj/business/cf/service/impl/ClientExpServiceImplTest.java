package com.lj.business.cf.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.base.core.pagination.Page;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cf.dto.clientExp.ClientExpDto;
import com.lj.business.cf.dto.clientExp.FindClientExpPage;
import com.lj.business.cf.emus.ExpResult;
import com.lj.business.cf.service.IClientExpService;
import com.lj.business.cf.util.TestHelp;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 邹磊
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class ClientExpServiceImplTest extends SpringTestCase{
	
	
	
	@Autowired
	private ClientExpServiceImpl clientExpServiceImpl;


	/**
	 * 
	 *
	 * 方法说明：查找跟进异常情况表(分页)
	 *
	 * @param exGuider
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年7月1日
	 *
	 */
	@Test
	public void findCfErrorInfoPage() {
		FindClientExpPage findClientExpPage = new FindClientExpPage();
		findClientExpPage.setStartTime(null);
		findClientExpPage.setEndTime(null);
	    Page<ClientExpDto> pages=clientExpServiceImpl.findClientExpPage(findClientExpPage);
		System.out.println(pages.getTotal());
	}
	
	/*@Test
	 public void findClientExpOms(){
		FindClientExpPage findClientExpPage=new FindClientExpPage();
	  Page<FindClientExpOmsPage> page=clientExpServiceImpl.findClientExpOms(findClientExpPage);
	  System.out.println(page);
	 }*/
	
	@Test
	public void findClientExps() {
		FindClientExpPage findClientExpPage = new FindClientExpPage();
		findClientExpPage.setMerchantNo(TestHelp.merchantNo_test);
//		findClientExpPage.setStartTime(null);
//		findClientExpPage.setEndTime(null);
		findClientExpPage.setCfCode("LJ_3433ca87f09047048d93baacab6b166c");
	    List<ClientExpDto> list=clientExpServiceImpl.findClientExps(findClientExpPage);
		System.out.println(list.toString());
	}
	@Test
	public void addClientExp() {
		ClientExpDto clientExp = new ClientExpDto();
		clientExp.setExpFb("aaaaaa");
		clientExp.setExpTime("2017-10-23 09:54:00");
		clientExp.setImgs("aaaaaa");
		clientExp.setResourcesUrl("aaaaa");
		clientExp.setMemberName("NINI");
		clientExp.setMemberNameGm("峰火");
		clientExp.setMemberNo("7dd89cc45a474626bdc871c27c3b5b0c");
		clientExp.setMemberNoGm("da08e545a349485aa8faa42a34d43978");
		clientExp.setMerchantNo("1f169ad6143d46f5832535642ce2d331");
		clientExp.setShopName("天津市市区直营店");
		clientExp.setShopNo("LJ_82039188265241e0bd8f87651db6ab3c");
		clientExpServiceImpl.addClientExp(clientExp);
	}
	
	@Test
	public void findExpResults() {
		Map<String,Object> parmMap = new HashMap<String, Object>();
		parmMap.put("merchantNo", TestHelp.merchantNo_test);
		parmMap.put("expResult", ExpResult.Y.toString());
		List<Map<String,Object>> list =clientExpServiceImpl.findExpResults(parmMap);
		System.out.println(list.toString());
	}
	@Test
	public void findClientExpByCfCode() {
		String cfCode = "LJ_3433ca87f09047048d93baacab6b166c";
		ClientExpDto list =clientExpServiceImpl.findClientExpByCfCode(cfCode);
		System.out.println(list.toString());
	}
	
	@Test
	public void findExpCountForMemberType() {
		String nowDate = "2017-09-09";
		String merchantNo = "e79d975846ee41ba8c3c55738fda66a9";
		String shopNo = "";
		String memberNoGm = "c795e08cadac4867bb7fad496216efaf";
		String memberType = "GUID";
		String searchNoGm = "";
		Map<String,Object> map =clientExpServiceImpl.findExpCountForMemberType(merchantNo, memberType, shopNo, memberNoGm, searchNoGm, nowDate);
		System.out.println(map.toString());
	}
	
	@Test
	public void findCountVisitByGm() throws Exception {
		Map<String, Object> map = new HashMap<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		map.put("beginTime", calendar.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		map.put("endTime", calendar.getTime());
		map.put("memberNoGm", "38df1c34ff2b430599fdbbe6aab1aaac");
		map.put("expResult", "Y");
		System.out.println(clientExpServiceImpl.findCountVisitByGm(map));
	}
	@Test
	public void findClientNum(){
		ClientExpDto clientExpDto=new ClientExpDto();
	  System.out.println(clientExpServiceImpl.findClientExpTodayNum(clientExpDto));
		
	}
	
}
