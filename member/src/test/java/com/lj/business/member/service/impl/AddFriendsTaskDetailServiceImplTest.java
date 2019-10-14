package com.lj.business.member.service.impl;

import java.util.ArrayList;
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
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.AddFriendsTaskDetailDto;
import com.lj.business.member.dto.FindAddFriendsTaskDetailPage;
import com.lj.business.member.service.IAddFriendsTaskDetailService;
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
public class AddFriendsTaskDetailServiceImplTest extends SpringTestCase{

	@Resource
	IAddFriendsTaskDetailService addFriendsTaskDetailService;



	/**
	 * 
	 *
	 * 方法说明：添加加粉任务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void addAddFriendsTaskDetail() throws TsfaServiceException{
		AddFriendsTaskDetailDto addFriendsTaskDetailDto = new AddFriendsTaskDetailDto();
		addFriendsTaskDetailDto.setTaskCode("8aa55c6cb3f34a139e01616bfea23f31");
		
		addFriendsTaskDetailDto.setUsername("Username");
		addFriendsTaskDetailDto.setSendMessage("SendMessage");
		addFriendsTaskDetailDto.setNoWx("NoWx");
		addFriendsTaskDetailDto.setNoWxGm("NoWxGm1");
		addFriendsTaskDetailDto.setStatus("1");
		addFriendsTaskDetailDto.setDetail("Detail");
		addFriendsTaskDetailDto.setMerchantNo("MerchantNo");
		addFriendsTaskDetailDto.setMerchantName("MerchantName");
		
		for (int j = 0; j < 200; j++) {
			addFriendsTaskDetailDto.setNoWxGm("NoWxGm"+j);
			//add数据录入
			for (int i = 0; i < 5000; i++) {
				addFriendsTaskDetailDto.setPhone("Phone"+i);
				addFriendsTaskDetailService.addAddFriendsTaskDetail(addFriendsTaskDetailDto);
			}
		}
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改加粉任务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updateAddFriendsTaskDetail() throws TsfaServiceException{
		AddFriendsTaskDetailDto addFriendsTaskDetailDto = new AddFriendsTaskDetailDto();
		//update数据录入
		addFriendsTaskDetailDto.setCode("Code");
		addFriendsTaskDetailDto.setTaskCode("TaskCode");
		addFriendsTaskDetailDto.setPhone("Phone");
		addFriendsTaskDetailDto.setUsername("Username");
		addFriendsTaskDetailDto.setSendMessage("SendMessage");
		addFriendsTaskDetailDto.setNoWx("NoWx");
		addFriendsTaskDetailDto.setNoWxGm("NoWxGm");
		addFriendsTaskDetailDto.setStatus("1");
		addFriendsTaskDetailDto.setDetail("Detail");
		addFriendsTaskDetailDto.setMerchantNo("MerchantNo");
		addFriendsTaskDetailDto.setMerchantName("MerchantName");

		addFriendsTaskDetailService.updateAddFriendsTaskDetail(addFriendsTaskDetailDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找加粉任务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findAddFriendsTaskDetail() throws TsfaServiceException{
		AddFriendsTaskDetailDto findAddFriendsTaskDetail = new AddFriendsTaskDetailDto();
		findAddFriendsTaskDetail.setCode("111");
		addFriendsTaskDetailService.findAddFriendsTaskDetail(findAddFriendsTaskDetail);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找加粉任务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findAddFriendsTaskDetailPage() throws TsfaServiceException{
		FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage = new FindAddFriendsTaskDetailPage();
		Page<AddFriendsTaskDetailDto> page = addFriendsTaskDetailService.findAddFriendsTaskDetailPage(findAddFriendsTaskDetailPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找加粉任务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findAddFriendsTaskDetails() throws TsfaServiceException{
		FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage = new FindAddFriendsTaskDetailPage();
		List<AddFriendsTaskDetailDto> page = addFriendsTaskDetailService.findAddFriendsTaskDetails(findAddFriendsTaskDetailPage);
		Assert.assertNotNull(page);
		
	}
	
	@Test
	public void insertForeach() throws TsfaServiceException{
		List<AddFriendsTaskDetailDto> record = new ArrayList<AddFriendsTaskDetailDto>();
		AddFriendsTaskDetailDto addFriendsTaskDetailDto = new AddFriendsTaskDetailDto();
		
		int id =0;
		for (int j = 0; j < 200; j++) {
			//add数据录入
			for (int i = 0; i < 5000; i++) {
				addFriendsTaskDetailDto = new AddFriendsTaskDetailDto();
				addFriendsTaskDetailDto.setNoWxGm("NoWxGm"+j);
				addFriendsTaskDetailDto.setNoWx("NoWx");
				addFriendsTaskDetailDto.setStatus("1");
				++id;
				addFriendsTaskDetailDto.setCode(id+"");
				addFriendsTaskDetailDto.setPhone("Phone"+i);
				record.add(addFriendsTaskDetailDto);
			}
			int count = addFriendsTaskDetailService.insertForeach(record);
			record.clear();
			System.out.println(count+"=======================================");
			logger.debug(count+"=======================================");
		}
		
		
	}
	
	@Test
	public void findByCond() throws TsfaServiceException{
		AddFriendsTaskDetailDto addFriendsTaskDetailDto = new AddFriendsTaskDetailDto();
		addFriendsTaskDetailDto.setNoWxGm("noWxGm");
		addFriendsTaskDetailDto.setPhone("Phone");
		AddFriendsTaskDetailDto page = addFriendsTaskDetailService.findByCond(addFriendsTaskDetailDto);
		Assert.assertNotNull(page);
	}
	
	@Test
	public void updateByCond() throws TsfaServiceException{
		AddFriendsTaskDetailDto addFriendsTaskDetailDto = new AddFriendsTaskDetailDto();
//		addFriendsTaskDetailDto.addCodes("Code");
		addFriendsTaskDetailDto.setStatus("2");
		addFriendsTaskDetailDto.setTaskCode("df231bd1c61743e2831d5729f33824b4");
		addFriendsTaskDetailService.updateByCond(addFriendsTaskDetailDto);
	}
	
	@Test
	public void findJobResult() throws TsfaServiceException{
		Assert.assertNotNull(addFriendsTaskDetailService.findJobResult(20));
	}
	
}
