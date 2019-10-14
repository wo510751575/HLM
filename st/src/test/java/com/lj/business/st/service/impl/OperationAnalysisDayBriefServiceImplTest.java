package com.lj.business.st.service.impl;

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
import com.lj.business.st.dto.OperationAnalysisDayBrief.AddOperationAnalysisDayBrief;
import com.lj.business.st.service.IOperationAnalysisDayBriefService;


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
public class OperationAnalysisDayBriefServiceImplTest extends SpringTestCase{

	@Resource
	private OperationAnalysisDayBriefServiceImpl operationAnalysisDayBriefServiceImpl;



	/**
	 * 
	 *
	 * 方法说明：添加报表项目信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void addOperationAnalysisDayBrief() throws TsfaServiceException{
		AddOperationAnalysisDayBrief addOperationAnalysisDayBrief = new AddOperationAnalysisDayBrief();
		//add数据录入
		addOperationAnalysisDayBrief.setCode("Code");
		addOperationAnalysisDayBrief.setMerchantNo("MerchantNo");
		addOperationAnalysisDayBrief.setAreaCode("AreaCode");
		addOperationAnalysisDayBrief.setAreaName("AreaName");
		addOperationAnalysisDayBrief.setShopNo("ShopNo");
		addOperationAnalysisDayBrief.setShopName("ShopName");
		addOperationAnalysisDayBrief.setMemberNoGm("MemberNoGm");
		addOperationAnalysisDayBrief.setMemberNameGm("MemberNameGm");
		addOperationAnalysisDayBrief.setBriefSale("BriefSale");
		addOperationAnalysisDayBrief.setBriefOrder("BriefOrder");
		addOperationAnalysisDayBrief.setBriefClientAction("BriefClientAction");
		addOperationAnalysisDayBrief.setBriefClientAnalyze("BriefClientAnalyze");
		addOperationAnalysisDayBrief.setBriefCf("BriefCf");
		addOperationAnalysisDayBrief.setBriefCaArea("BriefCaArea");
		addOperationAnalysisDayBrief.setDimensionSt("DimensionSt");
		addOperationAnalysisDayBrief.setCreateDate(new Date());
		operationAnalysisDayBriefServiceImpl.addOperationAnalysisDayBrief(addOperationAnalysisDayBrief);
	}

	
	


	
}
