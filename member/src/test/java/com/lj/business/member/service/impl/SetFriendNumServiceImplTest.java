package com.lj.business.member.service.impl;

import java.util.Date;
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
import com.lj.business.member.dto.FindSetFriendNumPage;
import com.lj.business.member.dto.SetFriendNumDto;
import com.lj.business.member.service.ISetFriendNumService;
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
public class SetFriendNumServiceImplTest extends SpringTestCase{

	@Resource
	ISetFriendNumService setFriendNumService;



	/**
	 * 
	 *
	 * 方法说明：添加设置添加好友数信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void addSetFriendNum() throws TsfaServiceException{
		SetFriendNumDto setFriendNumDto = new SetFriendNumDto();
		//add数据录入
		setFriendNumDto.setCode("Code");
		setFriendNumDto.setMerchantNo("MerchantNo");
		setFriendNumDto.setMerchantName("MerchantName");
		setFriendNumDto.setNoWx("NoWx");
		setFriendNumDto.setAlias("Alias");
		setFriendNumDto.setStartDate(new Date());
		setFriendNumDto.setEndDate(new Date());
		setFriendNumDto.setNum(1);
		setFriendNumDto.setRemark("Remark");
		setFriendNumDto.setRemark2("Remark2");
		setFriendNumDto.setRemark3("Remark3");
		setFriendNumDto.setRemark4("Remark4");
		
		setFriendNumService.addSetFriendNum(setFriendNumDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改设置添加好友数信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updateSetFriendNum() throws TsfaServiceException{
		SetFriendNumDto setFriendNumDto = new SetFriendNumDto();
		//update数据录入
		setFriendNumDto.setCode("Code");
		setFriendNumDto.setMerchantNo("MerchantNo");
		setFriendNumDto.setMerchantName("MerchantName");
		setFriendNumDto.setNoWx("NoWx");
		setFriendNumDto.setAlias("Alias");
		setFriendNumDto.setStartDate(new Date());
		setFriendNumDto.setEndDate(new Date());
		setFriendNumDto.setNum(1);
		setFriendNumDto.setRemark("Remark");
		setFriendNumDto.setRemark2("Remark2");
		setFriendNumDto.setRemark3("Remark3");
		setFriendNumDto.setRemark4("Remark4");

		setFriendNumService.updateSetFriendNum(setFriendNumDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找设置添加好友数信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findSetFriendNum() throws TsfaServiceException{
		SetFriendNumDto findSetFriendNum = new SetFriendNumDto();
		findSetFriendNum.setCode("111");
		setFriendNumService.findSetFriendNum(findSetFriendNum);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找设置添加好友数信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findSetFriendNumPage() throws TsfaServiceException{
		FindSetFriendNumPage findSetFriendNumPage = new FindSetFriendNumPage();
		Page<SetFriendNumDto> page = setFriendNumService.findSetFriendNumPage(findSetFriendNumPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找设置添加好友数信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findSetFriendNums() throws TsfaServiceException{
		FindSetFriendNumPage findSetFriendNumPage = new FindSetFriendNumPage();
		List<SetFriendNumDto> page = setFriendNumService.findSetFriendNums(findSetFriendNumPage);
		Assert.assertNotNull(page);
		
	}
	
}
