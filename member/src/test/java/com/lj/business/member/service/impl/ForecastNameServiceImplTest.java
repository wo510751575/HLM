package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.forecastName.AddForecastName;
import com.lj.business.member.dto.forecastName.FindForecastName;
import com.lj.business.member.dto.forecastName.FindForecastNamePage;
import com.lj.business.member.dto.forecastName.FindForecastNamePageReturn;
import com.lj.business.member.dto.forecastName.UpdateForecastName;
import com.lj.business.member.service.IForecastNameService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭俊霖
 * 
 * 
 * CreateDate: 2017-11-01
 */
public class ForecastNameServiceImplTest extends SpringTestCase{

	@Resource
	IForecastNameService forecastNameService;



	/**
	 * 
	 *
	 * 方法说明：添加预报名信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void addForecastName() throws TsfaServiceException{
		AddForecastName addForecastName = new AddForecastName();
		//add数据录入
		addForecastName.setCode("Code");
		addForecastName.setMemberCode("MemberCode");
		addForecastName.setMemberNo("MemberNo");
		addForecastName.setMemberName("MemberName");
		addForecastName.setName("Name");
		addForecastName.setMobile("Mobile");
		addForecastName.setSex("Sex");
		addForecastName.setIntentAddress("IntentAddress");
		addForecastName.setPersonCount(1);
		addForecastName.setProductLine("ProductLine");
		addForecastName.setMemberNoGm("MemberNoGm");
		addForecastName.setMemberNameGm("MemberNameGm");
		addForecastName.setMerchantNo("MerchantNo");
		addForecastName.setCreateId("CreateId");
		addForecastName.setCreateDate(new Date());
		addForecastName.setRemark("Remark");
		addForecastName.setRemark2("Remark2");
		addForecastName.setRemark3("Remark3");
		addForecastName.setRemark4("Remark4");
		
		Assert.assertNotNull(forecastNameService.addForecastName(addForecastName ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改预报名信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void updateForecastName() throws TsfaServiceException{
		UpdateForecastName updateForecastName = new UpdateForecastName();
		//update数据录入
		updateForecastName.setCode("Code");
		updateForecastName.setMemberCode("MemberCode");
		updateForecastName.setMemberNo("MemberNo");
		updateForecastName.setMemberName("MemberName");
		updateForecastName.setName("Name");
		updateForecastName.setMobile("Mobile");
		updateForecastName.setSex("Sex");
		updateForecastName.setIntentAddress("IntentAddress");
		updateForecastName.setPersonCount(1);
		updateForecastName.setProductLine("ProductLine");
		updateForecastName.setMemberNoGm("MemberNoGm");
		updateForecastName.setMemberNameGm("MemberNameGm");
		updateForecastName.setMerchantNo("MerchantNo");
		updateForecastName.setCreateId("CreateId");
		updateForecastName.setCreateDate(new Date());
		updateForecastName.setRemark("Remark");
		updateForecastName.setRemark2("Remark2");
		updateForecastName.setRemark3("Remark3");
		updateForecastName.setRemark4("Remark4");

		forecastNameService.updateForecastName(updateForecastName );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找预报名信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void findForecastName() throws TsfaServiceException{
		FindForecastName findForecastName = new FindForecastName();
		findForecastName.setCode("code");
		forecastNameService.findForecastName(findForecastName);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找预报名信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void findForecastNamePage() throws TsfaServiceException{
		FindForecastNamePage findForecastNamePage = new FindForecastNamePage();
		Page<FindForecastNamePageReturn> page = forecastNameService.findForecastNamePage(findForecastNamePage);
		Assert.assertNotNull(page);
		
	}
	
}
