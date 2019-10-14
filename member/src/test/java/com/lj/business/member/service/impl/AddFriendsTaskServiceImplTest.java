package com.lj.business.member.service.impl;

import java.util.List;

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
import com.lj.business.member.dto.AddFriendsTaskDto;
import com.lj.business.member.dto.FindAddFriendsTaskPage;
import com.lj.business.member.service.IAddFriendsTaskService;
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
public class AddFriendsTaskServiceImplTest extends SpringTestCase{

	@Resource
	IAddFriendsTaskService addFriendsTaskService;



	/**
	 * 
	 *
	 * 方法说明：添加加粉任务详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void addAddFriendsTask() throws TsfaServiceException{
		AddFriendsTaskDto addFriendsTaskDto = new AddFriendsTaskDto();
		
		for (int i = 0; i < 10; i++) {
			//add数据录入
			addFriendsTaskDto.setName("Name");
			addFriendsTaskDto.setNoWxArrays("NoWxArrays"+i+",NoWxArrays"+i+1);
			addFriendsTaskDto.setSendMessage("SendMessage");
			addFriendsTaskDto.setTotalPhonenum(0);
			addFriendsTaskDto.setCompleteNum(0);
			addFriendsTaskDto.setSuccessNum(0);
			addFriendsTaskDto.setStatus("1");
			addFriendsTaskDto.setMerchantNo("MerchantNo");
			addFriendsTaskDto.setMerchantName("MerchantName");
			addFriendsTaskService.addAddFriendsTask(addFriendsTaskDto);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改加粉任务详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updateAddFriendsTask() throws TsfaServiceException{
		AddFriendsTaskDto addFriendsTaskDto = new AddFriendsTaskDto();
		//update数据录入
		addFriendsTaskDto.setCode("Code");
		addFriendsTaskDto.setName("Name");
		addFriendsTaskDto.setNoWxArrays("NoWxArrays");
		addFriendsTaskDto.setSendMessage("SendMessage");
		addFriendsTaskDto.setTotalPhonenum(0);
		addFriendsTaskDto.setCompleteNum(0);
		addFriendsTaskDto.setSuccessNum(0);
		addFriendsTaskDto.setStatus("1");
		addFriendsTaskDto.setMerchantNo("MerchantNo");
		addFriendsTaskDto.setMerchantName("MerchantName");

		addFriendsTaskService.updateAddFriendsTask(addFriendsTaskDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找加粉任务详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findAddFriendsTask() throws TsfaServiceException{
		AddFriendsTaskDto findAddFriendsTask = new AddFriendsTaskDto();
		findAddFriendsTask.setCode("111");
		addFriendsTaskService.findAddFriendsTask(findAddFriendsTask);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找加粉任务详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findAddFriendsTaskPage() throws TsfaServiceException{
		FindAddFriendsTaskPage findAddFriendsTaskPage = new FindAddFriendsTaskPage();
		Page<AddFriendsTaskDto> page = addFriendsTaskService.findAddFriendsTaskPage(findAddFriendsTaskPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找加粉任务详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findAddFriendsTasks() throws TsfaServiceException{
		FindAddFriendsTaskPage findAddFriendsTaskPage = new FindAddFriendsTaskPage();
		List<AddFriendsTaskDto> page = addFriendsTaskService.findAddFriendsTasks(findAddFriendsTaskPage);
		Assert.assertNotNull(page);
		
	}
	
	
	@Test
	public void selectAddFriendsTaskDetailCount() throws TsfaServiceException{
		AddFriendsTaskDto addFriendsTaskDto = new AddFriendsTaskDto();
		Assert.assertNotNull(addFriendsTaskService.selectAddFriendsTaskDetailCount(addFriendsTaskDto));
		
	}
}
