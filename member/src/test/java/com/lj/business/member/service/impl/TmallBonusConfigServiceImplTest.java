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
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.FindTmallBonusConfigPage;
import com.lj.business.member.dto.TmallBonusConfigDto;
import com.lj.business.member.service.ITmallBonusConfigService;

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
public class TmallBonusConfigServiceImplTest extends SpringTestCase{

	@Resource
	ITmallBonusConfigService tmallBonusConfigService;



	/**
	 * 
	 *
	 * 方法说明：添加天猫订单红包配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addTmallBonusConfig() throws TsfaServiceException{
		TmallBonusConfigDto tmallBonusConfigDto = new TmallBonusConfigDto();
		//add数据录入
		tmallBonusConfigDto.setCode("Code");
		tmallBonusConfigDto.setMerchantNo("MerchantNo");
		tmallBonusConfigDto.setOrdAmtMin(1L);
		tmallBonusConfigDto.setOrdAmtMax(10L);
		tmallBonusConfigDto.setBonuxMin(1L);
		tmallBonusConfigDto.setBonuxMax(10L);
		tmallBonusConfigDto.setCreateDate(new Date());
		tmallBonusConfigDto.setCreateId("CreateId");
		
		tmallBonusConfigService.addTmallBonusConfig(tmallBonusConfigDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改天猫订单红包配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateTmallBonusConfig() throws TsfaServiceException{
		TmallBonusConfigDto tmallBonusConfigDto = new TmallBonusConfigDto();
		//update数据录入
		tmallBonusConfigDto.setCode("Code");
		tmallBonusConfigDto.setMerchantNo("MerchantNo");
		tmallBonusConfigDto.setOrdAmtMin(1L);
		tmallBonusConfigDto.setOrdAmtMax(10L);
		tmallBonusConfigDto.setBonuxMin(1L);
		tmallBonusConfigDto.setBonuxMax(10L);
		tmallBonusConfigDto.setCreateDate(new Date());
		tmallBonusConfigDto.setCreateId("CreateId");

		tmallBonusConfigService.updateTmallBonusConfig(tmallBonusConfigDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找天猫订单红包配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findTmallBonusConfig() throws TsfaServiceException{
		TmallBonusConfigDto findTmallBonusConfig = new TmallBonusConfigDto();
		findTmallBonusConfig.setCode("111");
		tmallBonusConfigService.findTmallBonusConfig(findTmallBonusConfig);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找天猫订单红包配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findTmallBonusConfigPage() throws TsfaServiceException{
		FindTmallBonusConfigPage findTmallBonusConfigPage = new FindTmallBonusConfigPage();
		Page<TmallBonusConfigDto> page = tmallBonusConfigService.findTmallBonusConfigPage(findTmallBonusConfigPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找天猫订单红包配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findTmallBonusConfigs() throws TsfaServiceException{
		FindTmallBonusConfigPage findTmallBonusConfigPage = new FindTmallBonusConfigPage();
		List<TmallBonusConfigDto> page = tmallBonusConfigService.findTmallBonusConfigs(findTmallBonusConfigPage);
		Assert.assertNotNull(page);
		System.out.println(JsonUtils.jsonFromList(page));
		
	}
	
}
