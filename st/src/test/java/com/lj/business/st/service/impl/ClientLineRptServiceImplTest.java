package com.lj.business.st.service.impl;

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
import com.lj.business.st.dto.ClientLineRpt.AddClientLineRpt;
import com.lj.business.st.dto.ClientLineRpt.FindClientLineRpt;
import com.lj.business.st.service.IClientLineRptService;


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
public class ClientLineRptServiceImplTest extends SpringTestCase{

	@Resource
	IClientLineRptService clientLineRptService;



	/**
	 * 
	 *
	 * 方法说明：添加客户职业统计表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addClientLineRpt() throws TsfaServiceException{
		AddClientLineRpt addClientLineRpt = new AddClientLineRpt();
		//add数据录入
		addClientLineRpt.setCode("Code");
		addClientLineRpt.setMerchantNo("MerchantNo");
		addClientLineRpt.setShopNo("ShopNo");
		addClientLineRpt.setShopName("ShopName");
		addClientLineRpt.setMemberNoGm("MemberNoGm");
		addClientLineRpt.setMemberNameGm("MemberNameGm");
		addClientLineRpt.setCodeLine("CodeLine");
		addClientLineRpt.setLineName("LineName");
		//addClientLineRpt.setRatioLine("RatioLine");
		addClientLineRpt.setDimensionSt("DimensionSt");
		//addClientLineRpt.setCreateDate("CreateDate");
		
		clientLineRptService.addClientLineRpt(addClientLineRpt );
		
	}
	/**
	 * 
	 *
	 * 方法说明：查找客户职业统计表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findClientLineRpt() throws TsfaServiceException{
		FindClientLineRpt findClientLineRpt = new FindClientLineRpt();
		findClientLineRpt.setCode("111");
		clientLineRptService.findClientLineRpt(findClientLineRpt);
	}
}
