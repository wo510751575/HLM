package com.ye.business.hx.test;

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

import java.util.Date;
import java.util.List;

import com.ye.business.hx.dto.HxClueDto;
import com.ye.business.hx.dto.FindHxCluePage;
import com.ye.business.hx.service.IHxClueService;

/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
public class HxClueServiceImplTest extends SpringTestCase{

	@Resource
	IHxClueService hxClueService;



	/**
	 * 
	 *
	 * 方法说明：添加线索信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addHxClue() throws TsfaServiceException{
		HxClueDto hxClueDto = new HxClueDto();
		//add数据录入
		hxClueDto.setCode("Code");
		hxClueDto.setName("Name");
		hxClueDto.setPhone("Phone");
		hxClueDto.setSex("Sex");
		hxClueDto.setAge(1);
		hxClueDto.setProvince("Province");
		hxClueDto.setCity("City");
		hxClueDto.setArea("Area");
		hxClueDto.setAddrInfo("AddrInfo");
		hxClueDto.setSource("Source");
		hxClueDto.setWechatNo("WechatNo");
		hxClueDto.setWechatName("WechatName");
		hxClueDto.setUserType("UserType");
		hxClueDto.setUserPrice(100L);
		hxClueDto.setStatus("Status");
		hxClueDto.setValidStatus("ValidStatus");
		hxClueDto.setCreateTime(new Date());
		hxClueDto.setOrderNo("OrderNo");
		hxClueDto.setProject("Project");
		hxClueDto.setReserveDate(new Date());
		hxClueDto.setReserveTime("ReserveTime");
		hxClueDto.setFollowName("FollowName");
		hxClueDto.setWishLevel("WishLevel");
		hxClueDto.setComplaint("Complaint");
		hxClueDto.setRemark("Remark");
		
		hxClueService.addHxClue(hxClueDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改线索信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateHxClue() throws TsfaServiceException{
		HxClueDto hxClueDto = new HxClueDto();
		//update数据录入
		hxClueDto.setCode("Code");
		hxClueDto.setName("Name");
		hxClueDto.setPhone("Phone");
		hxClueDto.setSex("Sex");
		hxClueDto.setAge(2);
		hxClueDto.setProvince("Province");
		hxClueDto.setCity("City");
		hxClueDto.setArea("Area");
		hxClueDto.setAddrInfo("AddrInfo");
		hxClueDto.setSource("Source");
		hxClueDto.setWechatNo("WechatNo");
		hxClueDto.setWechatName("WechatName");
		hxClueDto.setUserType("UserType");
		hxClueDto.setUserPrice(3L);
		hxClueDto.setStatus("Status");
		hxClueDto.setValidStatus("ValidStatus");
		hxClueDto.setCreateTime(new Date());
		hxClueDto.setOrderNo("OrderNo");
		hxClueDto.setProject("Project");
		hxClueDto.setReserveDate(new Date());
		hxClueDto.setReserveTime("ReserveTime");
		hxClueDto.setFollowName("FollowName");
		hxClueDto.setWishLevel("WishLevel");
		hxClueDto.setComplaint("Complaint");
		hxClueDto.setRemark("Remark");

		hxClueService.updateHxClue(hxClueDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找线索信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findHxClue() throws TsfaServiceException{
		HxClueDto findHxClue = new HxClueDto();
		findHxClue.setCode("111");
		hxClueService.findHxClue(findHxClue);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找线索信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findHxCluePage() throws TsfaServiceException{
		FindHxCluePage findHxCluePage = new FindHxCluePage();
		Page<HxClueDto> page = hxClueService.findHxCluePage(findHxCluePage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找线索信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findHxClues() throws TsfaServiceException{
		FindHxCluePage findHxCluePage = new FindHxCluePage();
		List<HxClueDto> page = hxClueService.findHxClues(findHxCluePage);
		Assert.assertNotNull(page);
		
	}
	
}
