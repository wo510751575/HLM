package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.lj.business.st.dto.CfCountAnalyze.FindCfCountAnalyzeReturn;
import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.CfCountAnalyze.AddCfCountAnalyze;
import com.lj.business.st.dto.CfCountAnalyze.FindCfCountAnalyze;
import com.lj.business.st.service.ICfCountAnalyzeService;


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
public class CfCountAnalyzeServiceImplTest extends SpringTestCase{

	@Resource
	ICfCountAnalyzeService cfCountAnalyzeService;



	/**
	 * 
	 *
	 * 方法说明：添加跟进次数分析表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addCfCountAnalyze() throws TsfaServiceException{
		AddCfCountAnalyze addCfCountAnalyze = new AddCfCountAnalyze();
		//add数据录入
		addCfCountAnalyze.setCode("Code");
		addCfCountAnalyze.setMerchantNo("MerchantNo");
		addCfCountAnalyze.setAreaCode("AreaCode");
		addCfCountAnalyze.setAreaName("AreaName");
		addCfCountAnalyze.setShopNo("ShopNo");
		addCfCountAnalyze.setShopName("ShopName");
		addCfCountAnalyze.setStDate(new Date());
		addCfCountAnalyze.setNumCf(1L);
		//addCfCountAnalyze.setRatioCfFive("RatioCfFive");
		//addCfCountAnalyze.setNumCfFive("NumCfFive");
		//addCfCountAnalyze.setRatioCfTen("RatioCfTen");
		//addCfCountAnalyze.setNumCfTen("NumCfTen");
		//addCfCountAnalyze.setRatioCfFifteen("RatioCfFifteen");
		//addCfCountAnalyze.setNumCfFifteen("NumCfFifteen");
		//addCfCountAnalyze.setRatioCfSixteen("RatioCfSixteen");
		addCfCountAnalyze.setNumCfSixteen(1);
		addCfCountAnalyze.setCreateDate(new Date());
		
		cfCountAnalyzeService.addCfCountAnalyze(addCfCountAnalyze );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找跟进次数分析表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findCfCountAnalyze() throws TsfaServiceException{
		FindCfCountAnalyze findCfCountAnalyze = new FindCfCountAnalyze();
		findCfCountAnalyze.setBeginDate("2017-07-27");
		findCfCountAnalyze.setEndDate("2017-07-29");
		List<FindCfCountAnalyzeReturn> cfCountAnalyzeList = cfCountAnalyzeService.findCfCountAnalyzeList(findCfCountAnalyze);
		Assert.assertNotNull(cfCountAnalyzeList);
	}
	
	
	
}
