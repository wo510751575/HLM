package com.lj.business.cf.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cf.dto.callChatInfo.AddCallChatInfo;
import com.lj.business.cf.dto.callChatInfo.DelCallChatInfo;
import com.lj.business.cf.dto.callChatInfo.FindCallChatInfo;
import com.lj.business.cf.dto.callChatInfo.FindCallChatInfoPage;
import com.lj.business.cf.dto.callChatInfo.FindCallChatInfoPageReturn;
import com.lj.business.cf.dto.callChatInfo.UpdateCallChatInfo;
import com.lj.business.cf.service.ICallChatInfoService;
import com.lj.cc.domain.SystemParams;
import com.lj.cc.domain.SystemParamsKey;
import com.lj.cc.service.ISystemParamsService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 冯辉
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class CallChatInfoServiceImplTest extends SpringTestCase{

	@Resource
	ICallChatInfoService callChatInfoService;

	@Resource
	private ISystemParamsService systemParamsService;

	/**
	 * 
	 *
	 * 方法说明：添加电话聊天信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addCallChatInfo() throws TsfaServiceException{
		AddCallChatInfo addCallChatInfo = new AddCallChatInfo();
		//add数据录入
		addCallChatInfo.setCode("Code");
		addCallChatInfo.setMemberNo("MemberNo");
		addCallChatInfo.setMemberName("MemberName");
		addCallChatInfo.setMobile("Mobile");
		addCallChatInfo.setLinkmanRemark("LinkmanRemark");
		addCallChatInfo.setCallTime(2000L);
		addCallChatInfo.setCallDate(2000L);
		addCallChatInfo.setStatus("Status");
		addCallChatInfo.setCreateDate(new Date());
		addCallChatInfo.setRemark("Remark");
		addCallChatInfo.setRemark2("Remark2");
		addCallChatInfo.setRemark3("Remark3");
		addCallChatInfo.setRemark4("Remark4");
		
		callChatInfoService.addCallChatInfo(addCallChatInfo );
		
	}
	@Test
	public void testCC(){
		SystemParamsKey key=new SystemParamsKey();
		key.setGroupName("abandon");
		key.setSysParamName("abandonCount");
		SystemParams params = systemParamsService.selectByPrimaryKey(key);
		System.out.println(params);
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改电话聊天信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void updateCallChatInfo() throws TsfaServiceException{
		UpdateCallChatInfo updateCallChatInfo = new UpdateCallChatInfo();
		//update数据录入
		updateCallChatInfo.setCode("Code");
		updateCallChatInfo.setMemberNo("MemberNo");
		updateCallChatInfo.setMemberName("MemberName");
		updateCallChatInfo.setMobile("Mobile");
		updateCallChatInfo.setLinkmanRemark("LinkmanRemark");
		updateCallChatInfo.setCallTime(2000L);
		updateCallChatInfo.setCallDate(2000L);
		updateCallChatInfo.setStatus("Status");
		updateCallChatInfo.setCreateDate(new Date());
		updateCallChatInfo.setRemark("Remark");
		updateCallChatInfo.setRemark2("Remark2");
		updateCallChatInfo.setRemark3("Remark3");
		updateCallChatInfo.setRemark4("Remark4");

		callChatInfoService.updateCallChatInfo(updateCallChatInfo );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找电话聊天信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findCallChatInfo() throws TsfaServiceException{
		FindCallChatInfo findCallChatInfo = new FindCallChatInfo();
		findCallChatInfo.setCode("111");
		callChatInfoService.findCallChatInfo(findCallChatInfo);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找电话聊天信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findCallChatInfoPage() throws TsfaServiceException{
		FindCallChatInfoPage findCallChatInfoPage = new FindCallChatInfoPage();
		Page<FindCallChatInfoPageReturn> page = callChatInfoService.findCallChatInfoPage(findCallChatInfoPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除电话聊天信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void delCallChatInfo() throws TsfaServiceException{
		DelCallChatInfo delCallChatInfo = new DelCallChatInfo();
		delCallChatInfo.setCode("111");
		callChatInfoService.delCallChatInfo(delCallChatInfo);
		
	}
	
	@Test
	public void uploadCallChatInfo() throws Exception {
		long l = callChatInfoService.uploadCallChatInfo("{'memberNoGuid':'8430ab66cc08459586e827ba7a71aef7','data':[{'mobile':'18576672217','callTime':10,'callDate':1497247426789,'status':2},{'mobile':'18038047379','callTime':211,'callDate':1497324812888,'status':2},{'mobile':'18576672217','callTime':15,'callDate':1498120095414,'status':2},{'mobile':'18576672217','callTime':8,'callDate':1499739654949,'status':2},{'mobile':'13434774588','callTime':10,'callDate':1503047882333,'status':2},{'mobile':'13691883466','callTime':33,'callDate':1503321481551,'status':2},{'mobile':'13532968066','callTime':11,'callDate':1504171214775,'status':2},{'mobile':'13532968066','callTime':0,'callDate':1504604707721,'status':2},{'mobile':'13532968066','callTime':10,'callDate':1504660138566,'status':2},{'mobile':'13532968066','callTime':3,'callDate':1504660142447,'status':2},{'mobile':'13828768430','callTime':12,'callDate':1504681124066,'status':2},{'mobile':'15222629336','callTime':3,'callDate':1505292352941,'status':2},{'mobile':'15502291865','callTime':0,'callDate':1505464703789,'status':2},{'mobile':'13076945626','callTime':0,'callDate':1506595802247,'status':2}]}");
		System.out.println(l);
	}
}
