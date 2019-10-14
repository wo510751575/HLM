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

import java.util.List;

import com.ye.business.hx.dto.ServerDetailDto;
import com.ye.business.hx.emus.ServerDetailShop;
import com.ye.business.hx.dto.FindServerDetailPage;
import com.ye.business.hx.service.IServerDetailService;

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
public class ServerDetailServiceImplTest extends SpringTestCase{

	@Resource
	IServerDetailService serverDetailService;



	/**
	 * 
	 *
	 * 方法说明：添加系统服务项信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addServerDetail() throws TsfaServiceException{
		ServerDetailDto serverDetailDto = new ServerDetailDto();
		//add数据录入
//		serverDetailDto.setCode("Code");
		serverDetailDto.setServerCode("LJ_db4f0ec17b97442fbee855dd50009886");
		serverDetailDto.setUserType("2");
		serverDetailDto.setServerNum(30);
		serverDetailDto.setPrice(0L);
		serverDetailDto.setIsShop(ServerDetailShop.YES.toString());
		serverDetailDto.setServerDesc("1500元/人，精准客户指主诉明确且为正畸，种植和修复类型的客户，计算费用按客户实际到店计算");
		serverDetailService.addServerDetail(serverDetailDto);
		
		serverDetailDto.setUserType("1");
		serverDetailDto.setServerNum(30);
		serverDetailDto.setPrice(0L);
		serverDetailDto.setIsShop(ServerDetailShop.NO.toString());
		serverDetailDto.setServerDesc("800元/人，精准客户指主诉明确且为正畸、种植和修复类型的客户，计算费用按线索有效性（何为有效性？）计算");
		serverDetailService.addServerDetail(serverDetailDto);
		
		
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改系统服务项信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateServerDetail() throws TsfaServiceException{
		ServerDetailDto serverDetailDto = new ServerDetailDto();
		//update数据录入
		serverDetailDto.setCode("Code");
		serverDetailDto.setServerCode("ServerCode");
		serverDetailDto.setUserType("UserType");
		serverDetailDto.setServerNum(2);
		serverDetailDto.setPrice(100L);

		serverDetailService.updateServerDetail(serverDetailDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找系统服务项信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findServerDetail() throws TsfaServiceException{
		ServerDetailDto findServerDetail = new ServerDetailDto();
		findServerDetail.setCode("111");
		serverDetailService.findServerDetail(findServerDetail);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找系统服务项信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findServerDetailPage() throws TsfaServiceException{
		FindServerDetailPage findServerDetailPage = new FindServerDetailPage();
		Page<ServerDetailDto> page = serverDetailService.findServerDetailPage(findServerDetailPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找系统服务项信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findServerDetails() throws TsfaServiceException{
		FindServerDetailPage findServerDetailPage = new FindServerDetailPage();
		List<ServerDetailDto> page = serverDetailService.findServerDetails(findServerDetailPage);
		Assert.assertNotNull(page);
		
	}
	
}
