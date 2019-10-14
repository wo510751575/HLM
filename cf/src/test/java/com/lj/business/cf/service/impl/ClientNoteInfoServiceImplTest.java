package com.lj.business.cf.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cf.dto.clientNoteInfo.AddClientNoteInfo;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfo;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfoPage;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfoPageReturn;
import com.lj.business.cf.dto.clientNoteInfo.UpdateClientNoteInfo;
import com.lj.business.cf.service.IClientNoteInfoService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class ClientNoteInfoServiceImplTest extends SpringTestCase{

	@Resource
	IClientNoteInfoService clientNoteInfoService;



/*	*//**
	 * 
	 *
	 * 方法说明：添加短信信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 *//*
	@Test
	public void addClientNoteInfo() throws TsfaServiceException{
		AddClientNoteInfo addClientNoteInfo = new AddClientNoteInfo();
		//add数据录入
		addClientNoteInfo.setMemberNo("MemberNo");
		addClientNoteInfo.setMobile("Mobile");
		addClientNoteInfo.setSendType("SendType");
		addClientNoteInfo.setSendTime(new Date());
		addClientNoteInfo.setCreateDate(new Date());
		addClientNoteInfo.setRemark("Remark");
		addClientNoteInfo.setRemark4("Remark4");
		addClientNoteInfo.setRemark3("Remark3");
		addClientNoteInfo.setRemark2("Remark2");
		
		Assert.assertNotNull(clientNoteInfoService.addClientNoteInfo(addClientNoteInfo ));
		
	}*/
	
	/**
	 * 
	 *
	 * 方法说明：修改短信信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void updateClientNoteInfo() throws TsfaServiceException{
		UpdateClientNoteInfo updateClientNoteInfo = new UpdateClientNoteInfo();
		//update数据录入
		updateClientNoteInfo.setCode("LJ_1fa7c581e3024fa7a19d4bc9aadab980");
		updateClientNoteInfo.setMemberNo("MemberNo");
		updateClientNoteInfo.setMobile("Mobile");
		updateClientNoteInfo.setSendType("SendType");
		updateClientNoteInfo.setSendTime("");
		updateClientNoteInfo.setCreateDate(new Date());
		updateClientNoteInfo.setRemark("Remark");
		updateClientNoteInfo.setRemark4("Remark4");
		updateClientNoteInfo.setRemark3("Remark3");
		updateClientNoteInfo.setRemark2("Remark2");

		clientNoteInfoService.updateClientNoteInfo(updateClientNoteInfo );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找短信信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findClientNoteInfo() throws TsfaServiceException{
		FindClientNoteInfo findClientNoteInfo = new FindClientNoteInfo();
		findClientNoteInfo.setCode("LJ_1fa7c581e3024fa7a19d4bc9aadab980");
		clientNoteInfoService.findClientNoteInfo(findClientNoteInfo);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找短信信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findClientNoteInfoPage() throws TsfaServiceException{
		FindClientNoteInfoPage findClientNoteInfoPage = new FindClientNoteInfoPage();
		Page<FindClientNoteInfoPageReturn> page = clientNoteInfoService.findClientNoteInfoPage(findClientNoteInfoPage);
		Assert.assertNotNull(page);
		
	}
	
	@Test
	public void findClientNoteInfoList()throws TsfaServiceException{
	    Map<String,Object>  map=new HashMap();
	    
		System.out.println("");
	}
	
	
}
