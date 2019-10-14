package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.WorkBrDay.AddWorkBrDay;
import com.lj.business.st.dto.WorkBrDay.FindWorkBrDay;
import com.lj.business.st.service.IWorkBrDayService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 邹磊
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class WorkBrDayServiceImplTest extends SpringTestCase{

	@Resource
	IWorkBrDayService workBrDayService;



	/**
	 * 
	 *
	 * 方法说明：添加日工作简报表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addWorkBrDay() throws TsfaServiceException{
		AddWorkBrDay addWorkBrDay = new AddWorkBrDay();
		//add数据录入
		addWorkBrDay.setCode("Code");
		addWorkBrDay.setMerchantNo("MerchantNo");
		addWorkBrDay.setAreaCode("AreaCode");
		addWorkBrDay.setAreaName("AreaName");
		addWorkBrDay.setShopNo("ShopNo");
		addWorkBrDay.setShopName("ShopName");
		addWorkBrDay.setMemberNoGm("MemberNoGm");
		addWorkBrDay.setMemberNameGm("MemberNameGm");
		addWorkBrDay.setCodeList("CodeList");
		addWorkBrDay.setNameList("NameList");
		addWorkBrDay.setValueList("ValueList");
		addWorkBrDay.setDaySt(new Date());
		addWorkBrDay.setDimensionSt("DimensionSt");
		addWorkBrDay.setCreateDate(new Date());
		
		workBrDayService.addWorkBrDay(addWorkBrDay);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找日工作简报表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findWorkBrDay() throws TsfaServiceException{
		FindWorkBrDay findWorkBrDay = new FindWorkBrDay();
		findWorkBrDay.setCode("111");
		workBrDayService.findWorkBrDay(findWorkBrDay);
	}
	
	
	
}
