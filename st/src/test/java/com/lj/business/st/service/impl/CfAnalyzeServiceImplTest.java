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
import com.lj.business.st.dto.CfAnalyze.AddCfAnalyze;
import com.lj.business.st.dto.CfAnalyze.FindCfAnalyze;
import com.lj.business.st.service.ICfAnalyzeService;
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
public class CfAnalyzeServiceImplTest extends SpringTestCase{

	@Resource
	ICfAnalyzeService cfAnalyzeService;



	/**
	 * 
	 *
	 * 方法说明：添加跟进分析摘要表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addCfAnalyze() throws TsfaServiceException{
		AddCfAnalyze addCfAnalyze = new AddCfAnalyze();
		//add数据录入
		addCfAnalyze.setMerchantNo(TestHelp.merchantNo_test);
		addCfAnalyze.setAreaCode("AreaCode");
		addCfAnalyze.setAreaName("AreaName");
		addCfAnalyze.setShopNo(TestHelp.shopNo_test);
		addCfAnalyze.setShopName("ShopName");
		addCfAnalyze.setMemberNoGm(TestHelp.memberNoGm_test);
		addCfAnalyze.setMemberNameGm("MemberNameGm");
		addCfAnalyze.setBriefClientAnalyze("客户分析摘要");
		addCfAnalyze.setBriefClientAction("客户行为摘要");
		addCfAnalyze.setBriefSocial("社交分析摘要");
		addCfAnalyze.setBriefMaterial("素材分析摘要");
		addCfAnalyze.setBriefClientType("客户分类摘要");
		addCfAnalyze.setDimensionSt("GUID ");
		addCfAnalyze.setDaySt(new Date());
		
		cfAnalyzeService.addCfAnalyze(addCfAnalyze );
		
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：查找跟进分析摘要表信息_APP用
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findCfAnalyze() throws TsfaServiceException{
		FindCfAnalyze findCfAnalyze = new FindCfAnalyze();
		findCfAnalyze.setMemberNoGm(TestHelp.memberNoGm_test);
		findCfAnalyze.setMerchantNo(TestHelp.merchantNo_test);
		cfAnalyzeService.findCfAnalyze(findCfAnalyze);
	}
}
