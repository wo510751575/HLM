package com.ye.business.hx.service.impl;

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

import com.ye.business.hx.dto.ServerInfoDto;
import com.ye.business.hx.emus.Status;
import com.ye.business.hx.dto.FindServerInfoPage;
import com.ye.business.hx.service.IServerInfoService;

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
public class ServerInfoServiceImplTest extends SpringTestCase{

	@Resource
	IServerInfoService serverInfoService;



	/**
	 * 
	 *
	 * 方法说明：添加系统服务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addServerInfo() throws TsfaServiceException{
		ServerInfoDto serverInfoDto = new ServerInfoDto();
		//add数据录入
		serverInfoDto.setCode("Code");
		serverInfoDto.setMerchantNo("1");
		serverInfoDto.setMerchantName("root");
		serverInfoDto.setServerName("加强版套餐");
		serverInfoDto.setPrice(3980000L);
		serverInfoDto.setOriginalPrice(2980000L);
		serverInfoDto.setStatus(Status.use.toString());
//		serverInfoDto.setUpdateId("UpdateId");
		serverInfoDto.setUpdateDate(new Date());
//		serverInfoDto.setCreateId("CreateId");
		serverInfoDto.setCreateDate(new Date());
		serverInfoDto.setCtx("加强版套餐包含固定数量的客户信息，包括：精准客户到店、精准客户线索、常规客户线索、常规客户到店，并赠送3Z患者全流程跟进，在产品服务周期内根据实际消费服务项扣取费用。");
		
		serverInfoService.addServerInfo(serverInfoDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改系统服务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateServerInfo() throws TsfaServiceException{
		ServerInfoDto serverInfoDto = new ServerInfoDto();
		//update数据录入
		serverInfoDto.setCode("Code");
		serverInfoDto.setMerchantNo("MerchantNo");
		serverInfoDto.setMerchantName("MerchantName");
		serverInfoDto.setServerName("ServerName");
		serverInfoDto.setPrice(400L);
		serverInfoDto.setOriginalPrice(600L);
		serverInfoDto.setStatus("Status");
		serverInfoDto.setUpdateId("UpdateId");
		serverInfoDto.setUpdateDate(new Date());
		serverInfoDto.setCreateId("CreateId");
		serverInfoDto.setCreateDate(new Date());
		serverInfoDto.setCtx("Ctx");

		serverInfoService.updateServerInfo(serverInfoDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找系统服务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findServerInfo() throws TsfaServiceException{
		ServerInfoDto findServerInfo = new ServerInfoDto();
		findServerInfo.setCode("111");
		serverInfoService.findServerInfo(findServerInfo);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找系统服务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findServerInfoPage() throws TsfaServiceException{
		FindServerInfoPage findServerInfoPage = new FindServerInfoPage();
		Page<ServerInfoDto> page = serverInfoService.findServerInfoPage(findServerInfoPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找系统服务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findServerInfos() throws TsfaServiceException{
		FindServerInfoPage findServerInfoPage = new FindServerInfoPage();
		List<ServerInfoDto> page = serverInfoService.findServerInfos(findServerInfoPage);
		Assert.assertNotNull(page);
		
	}
	
}
