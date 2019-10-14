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
import com.lj.business.st.dto.ClientInterestRpt.AddClientInterestRpt;
import com.lj.business.st.dto.ClientInterestRpt.FindClientInterestRpt;
import com.lj.business.st.service.IClientInterestRptService;


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
public class ClientInterestRptServiceImplTest extends SpringTestCase{

	@Resource
	IClientInterestRptService clientInterestRptService;



	/**
	 * 
	 *
	 * 方法说明：添加客户兴趣统计表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addClientInterestRpt() throws TsfaServiceException{
		AddClientInterestRpt addClientInterestRpt = new AddClientInterestRpt();
		//add数据录入
		addClientInterestRpt.setCode("Code");
		addClientInterestRpt.setMerchantNo("MerchantNo");
		addClientInterestRpt.setShopNo("ShopNo");
		addClientInterestRpt.setShopName("ShopName");
		addClientInterestRpt.setMemberNoGm("MemberNoGm");
		addClientInterestRpt.setMemberNameGm("MemberNameGm");
		addClientInterestRpt.setCodeInterest("CodeInterest");
		addClientInterestRpt.setInterestName("InterestName");
		//addClientInterestRpt.setRatioLine("RatioLine");
		addClientInterestRpt.setDimensionSt("DimensionSt");
		//addClientInterestRpt.setCreateDate("CreateDate");
		
		clientInterestRptService.addClientInterestRpt(addClientInterestRpt );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找客户兴趣统计表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findClientInterestRpt() throws TsfaServiceException{
		FindClientInterestRpt findClientInterestRpt = new FindClientInterestRpt();
		findClientInterestRpt.setCode("111");
		clientInterestRptService.findClientInterestRpt(findClientInterestRpt);
	}
	
	
}
