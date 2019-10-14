package com.lj.business.weixin.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;

import java.util.List;

import com.lj.business.weixin.dto.ImGroupChatInfoDto;
import com.lj.business.weixin.dto.FindImGroupChatInfoPage;
import com.lj.business.weixin.service.IImGroupChatInfoService;
/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.11
 */
public class ImGroupChatInfoServiceImplTest extends SpringTestCase{

	@Resource
	IImGroupChatInfoService imGroupChatInfoService;



	/**
	 * 
	 *
	 * 方法说明：添加群发聊天设置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void addImGroupChatInfo() throws TsfaServiceException{
		ImGroupChatInfoDto imGroupChatInfoDto = new ImGroupChatInfoDto();
		//add数据录入
		imGroupChatInfoDto.setCode("Code");
		imGroupChatInfoDto.setMerchantNo("MerchantNo");
		imGroupChatInfoDto.setMerchantName("MerchantName");
		imGroupChatInfoDto.setMemberNos("MemberNos");
		imGroupChatInfoDto.setMemberNames("MemberNames");
		imGroupChatInfoDto.setMemberNoWxs("MemberNoWxs");
//		imGroupChatInfoDto.setShopNo("ShopNo");
//		imGroupChatInfoDto.setShopName("ShopName");
		imGroupChatInfoDto.setMemberNoGm("MemberNoGm");
		imGroupChatInfoDto.setMemberNameGm("MemberNameGm");
		imGroupChatInfoDto.setNoWxGm("NoWxGm");
		imGroupChatInfoDto.setType("Type");
		imGroupChatInfoDto.setStatus("1");
		imGroupChatInfoDto.setResourcesPath("ResourcesPath");
		imGroupChatInfoDto.setChatroomType(1);
		imGroupChatInfoDto.setChatroomNoWx("ChatroomNoWx");
		imGroupChatInfoDto.setChatAssistantCode("ChatAssistantCode");
		imGroupChatInfoDto.setRemark("Remark");
		imGroupChatInfoDto.setRemark2("Remark2");
		imGroupChatInfoDto.setRemark3("Remark3");
		imGroupChatInfoDto.setRemark4("Remark4");
		imGroupChatInfoDto.setContent("Content");
		
		imGroupChatInfoService.addImGroupChatInfo(imGroupChatInfoDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改群发聊天设置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updateImGroupChatInfo() throws TsfaServiceException{
		ImGroupChatInfoDto imGroupChatInfoDto = new ImGroupChatInfoDto();
		//update数据录入
		imGroupChatInfoDto.setCode("Code");
		imGroupChatInfoDto.setMerchantNo("MerchantNo");
		imGroupChatInfoDto.setMerchantName("MerchantName");
		imGroupChatInfoDto.setMemberNos("MemberNos");
		imGroupChatInfoDto.setMemberNames("MemberNames");
		imGroupChatInfoDto.setMemberNoWxs("MemberNoWxs");
//		imGroupChatInfoDto.setShopNo("ShopNo");
//		imGroupChatInfoDto.setShopName("ShopName");
		imGroupChatInfoDto.setMemberNoGm("MemberNoGm");
		imGroupChatInfoDto.setMemberNameGm("MemberNameGm");
		imGroupChatInfoDto.setNoWxGm("NoWxGm");
		imGroupChatInfoDto.setType("Type");
		imGroupChatInfoDto.setStatus("Status");
		imGroupChatInfoDto.setResourcesPath("ResourcesPath");
		imGroupChatInfoDto.setChatroomType(1);
		imGroupChatInfoDto.setChatroomNoWx("ChatroomNoWx");
		imGroupChatInfoDto.setChatAssistantCode("ChatAssistantCode");
		imGroupChatInfoDto.setRemark("Remark");
		imGroupChatInfoDto.setRemark2("Remark2");
		imGroupChatInfoDto.setRemark3("Remark3");
		imGroupChatInfoDto.setRemark4("Remark4");
		imGroupChatInfoDto.setContent("Content");

		imGroupChatInfoService.updateImGroupChatInfo(imGroupChatInfoDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找群发聊天设置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findImGroupChatInfo() throws TsfaServiceException{
		ImGroupChatInfoDto findImGroupChatInfo = new ImGroupChatInfoDto();
		findImGroupChatInfo.setCode("111");
		imGroupChatInfoService.findImGroupChatInfo(findImGroupChatInfo);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找群发聊天设置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findImGroupChatInfoPage() throws TsfaServiceException{
		FindImGroupChatInfoPage findImGroupChatInfoPage = new FindImGroupChatInfoPage();
		Page<ImGroupChatInfoDto> page = imGroupChatInfoService.findImGroupChatInfoPage(findImGroupChatInfoPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找群发聊天设置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findImGroupChatInfos() throws TsfaServiceException{
		FindImGroupChatInfoPage findImGroupChatInfoPage = new FindImGroupChatInfoPage();
		List<ImGroupChatInfoDto> page = imGroupChatInfoService.findImGroupChatInfos(findImGroupChatInfoPage);
		Assert.assertNotNull(page);
		
	}
	
}
