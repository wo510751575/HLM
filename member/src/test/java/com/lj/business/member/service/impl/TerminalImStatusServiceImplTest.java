package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.terminalimstatus.AddTerminalImStatus;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatus;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatusPage;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatusPageReturn;
import com.lj.business.member.dto.terminalimstatus.TerminalImLoginRequest;
import com.lj.business.member.dto.terminalimstatus.TerminalImLogoutRequest;
import com.lj.business.member.dto.terminalimstatus.UpdateTerminalImStatus;
import com.lj.business.member.service.ITerminalImStatusService;
import com.lj.business.weixin.dto.imchat.FindImChatInfoPage;
import com.lj.business.weixin.dto.imchat.FindImChatInfoPageReturn;


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
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月1日
 */
public class TerminalImStatusServiceImplTest extends SpringTestCase{

	@Resource
	ITerminalImStatusService terminalImStatusService;



	/**
	 * 
	 *
	 * 方法说明：添加终端IM状态信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	@Test
	public void addTerminalImStatus() throws TsfaServiceException{
		AddTerminalImStatus addTerminalImStatus = new AddTerminalImStatus();
		//add数据录入
		addTerminalImStatus.setCode("Code");
		addTerminalImStatus.setMerchantNo("MerchantNo");
		addTerminalImStatus.setMerchantName("MerchantName");
//		addTerminalImStatus.setShopNo("ShopNo");
//		addTerminalImStatus.setShopName("ShopName");
		addTerminalImStatus.setTerminalType("TerminalType");
		addTerminalImStatus.setOnlineFlag(1);
		addTerminalImStatus.setMemberNoGm("MemberNoGm");
		addTerminalImStatus.setMemberName("MemberName");
//		addTerminalImStatus.setMobileName("MobileName");
		addTerminalImStatus.setImei("Imei");
		addTerminalImStatus.setLoginTime(new Date());
		addTerminalImStatus.setCreateId("CreateId");
		addTerminalImStatus.setCreateDate(new Date());
		addTerminalImStatus.setRemark("Remark");
		addTerminalImStatus.setRemark2("Remark2");
		addTerminalImStatus.setRemark3("Remark3");
		addTerminalImStatus.setRemark4("Remark4");
		
		Assert.assertNotNull(terminalImStatusService.addTerminalImStatus(addTerminalImStatus ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改终端IM状态信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	@Test
	public void updateTerminalImStatus() throws TsfaServiceException{
		UpdateTerminalImStatus updateTerminalImStatus = new UpdateTerminalImStatus();
		//update数据录入
		updateTerminalImStatus.setCode("Code");
		updateTerminalImStatus.setMerchantNo("MerchantNo");
		updateTerminalImStatus.setMerchantName("MerchantName");
//		updateTerminalImStatus.setShopNo("ShopNo");
//		updateTerminalImStatus.setShopName("ShopName");
		updateTerminalImStatus.setTerminalType("TerminalType");
		updateTerminalImStatus.setOnlineFlag(1);
		updateTerminalImStatus.setMemberNoGm("MemberNoGm");
		updateTerminalImStatus.setMemberName("MemberName");
		updateTerminalImStatus.setMobileName("MobileName");
		updateTerminalImStatus.setImei("Imei");
		updateTerminalImStatus.setLoginTime(new Date());
		updateTerminalImStatus.setCreateId("CreateId");
		updateTerminalImStatus.setCreateDate(new Date());
		updateTerminalImStatus.setRemark("Remark");
		updateTerminalImStatus.setRemark2("Remark2");
		updateTerminalImStatus.setRemark3("Remark3");
		updateTerminalImStatus.setRemark4("Remark4");

		terminalImStatusService.updateTerminalImStatus(updateTerminalImStatus );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找终端IM状态信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	@Test
	public void findTerminalImStatus() throws TsfaServiceException{
		FindTerminalImStatus findTerminalImStatus = new FindTerminalImStatus();
		findTerminalImStatus.setCode("code");
		terminalImStatusService.findTerminalImStatus(findTerminalImStatus);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找终端IM状态信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	@Test
	public void findTerminalImStatusPage() throws TsfaServiceException{
		FindTerminalImStatusPage findTerminalImStatusPage = new FindTerminalImStatusPage();
		Page<FindTerminalImStatusPageReturn> page = terminalImStatusService.findTerminalImStatusPage(findTerminalImStatusPage);
		Assert.assertNotNull(page);
	}
	
	@Test
	public void testLogin() {
		TerminalImLoginRequest request = new TerminalImLoginRequest();
		request.setMemberNoGm("71deb4191b764764a5927c7f93d5e142");
		request.setType("GM");
//		request.setImei("234234234423");
		terminalImStatusService.login(request);
	}
	
	@Test
	public void testLogout() {
		TerminalImLogoutRequest request = new TerminalImLogoutRequest();
		request.setMemberNoGm("61c4a7f1d59e4d16b4e65078b9e2425f");
		request.setType("ZK");
		request.setTerminalCode("");
		terminalImStatusService.logout(request);
	}
	
	@Test
	public void testupdateTerminalImStatusByType() {
		UpdateTerminalImStatus updateTerminalImStatus = new UpdateTerminalImStatus();
		updateTerminalImStatus.setMemberNoGm("da08e545a349485aa8faa42a34d43978");
		updateTerminalImStatus.setTerminalType("ZK");
		updateTerminalImStatus.setImei("866036039541244");
		updateTerminalImStatus.setBatteryLevel(15);
		terminalImStatusService.updateTerminalImStatusByType(updateTerminalImStatus);
	}
	
	@Test
	public void testSplit() {
		FindImChatInfoPage findImChatInfoPage=new FindImChatInfoPage();
		List<FindImChatInfoPageReturn> returnList = Lists.newArrayList();
		Page<FindImChatInfoPageReturn> chatInfo = new Page<FindImChatInfoPageReturn>(returnList,0,findImChatInfoPage);
		List<FindImChatInfoPageReturn> list=Lists.newArrayList();
		list.addAll(chatInfo.getRows());
		System.out.println(list);
		System.out.println(list.size());
//		System.out.println(list.get(0));
//		System.out.println(list.get(0).getContent());
	}
}