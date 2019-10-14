package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.AddClientAnalyze;
import com.lj.business.st.dto.FindClientAnalyze;
import com.lj.business.st.dto.FindClientAnalyzeAndOthers;
import com.lj.business.st.dto.FindClientAnalyzeAndOthersReturn;
import com.lj.business.st.emus.DimensionSt;
import com.lj.business.st.service.IClientAnalyzeService;
import com.lj.business.st.util.TestHelp;


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
public class ClientAnalyzeServiceImplTest extends SpringTestCase{

	@Resource
	private  IClientAnalyzeService clientAnalyzeService;



	/**
	 * 
	 *
	 * 方法说明：添加客户画像信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void addClientAnalyze() throws TsfaServiceException{
		AddClientAnalyze addClientAnalyze = new AddClientAnalyze();
		//add数据录入
		addClientAnalyze.setCode("Code");
		addClientAnalyze.setMerchantNo("MerchantNo");
		addClientAnalyze.setAreaCode("AreaCode");
		addClientAnalyze.setAreaName("AreaName");
		addClientAnalyze.setShopNo("ShopNo");
		addClientAnalyze.setShopName("ShopName");
		addClientAnalyze.setStDate(new Date());
		addClientAnalyze.setRatioMale(2.1);
		addClientAnalyze.setRatioFemale(2.1);
		addClientAnalyze.setNumPm(2l);
		addClientAnalyze.setRatioAgeTen(2.1);
		addClientAnalyze.setNumAgeTen(2);
		addClientAnalyze.setRatioAgeTwenty(2.1);
		addClientAnalyze.setNumAgeTwenty(2);
		addClientAnalyze.setRatioAgeThirty(2.1);
		addClientAnalyze.setNumAgeThirty(2);
		addClientAnalyze.setRatioAgeForty(2.1);
		addClientAnalyze.setNumAgeForty(2);
		addClientAnalyze.setRatioAgeFifty(2.1);
		addClientAnalyze.setNumAgeFifty(2);
		addClientAnalyze.setCreateDate(new Date());
		
		Assert.assertNotNull(clientAnalyzeService.addClientAnalyze(addClientAnalyze ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查询客户画像统计
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年7月31日
	 *
	 */
	@Test
    public void findClientAnalyzeList()throws TsfaServiceException{
	Map<String,Object> map=new HashMap<String,Object>();
	map.put("merchantNo", "");
	List<Map<String,Object>> list=clientAnalyzeService.findClientAnalyzeList(map);
	System.out.println(list);
	}
	
	@Test
    public void findClientAnalyzeAndOthersReturn()throws TsfaServiceException{
		FindClientAnalyzeAndOthers findClientAnalyzeAndOthers=new FindClientAnalyzeAndOthers();
		//findClientAnalyzeAndOthers.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		findClientAnalyzeAndOthers.setAreaCode("2");
		findClientAnalyzeAndOthers.setDimensionSt(DimensionSt.AREA.toString());
		FindClientAnalyzeAndOthersReturn list=clientAnalyzeService.findClientAnalyzeAndOthersReturn(findClientAnalyzeAndOthers);
	System.out.println(list);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查询客户性别
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年7月31日
	 *
	 */
	@Test
	public void findClientAnalyze()throws TsfaServiceException{
		FindClientAnalyze findClientAnalyze=new FindClientAnalyze();
		findClientAnalyze.setMerchantNo(TestHelp.merchantNo_test);
		clientAnalyzeService.findClientAnalyze(findClientAnalyze);
		
	}
}
