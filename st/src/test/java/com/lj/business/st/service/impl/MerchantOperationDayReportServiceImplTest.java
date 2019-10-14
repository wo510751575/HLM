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
import com.lj.business.st.dto.mec.FindMerchantOperationDayReportPage;
import com.lj.business.st.dto.mec.FindMerchantOperationDayReportPageReturn;
import com.lj.business.st.service.IMerchantOperationDayReportService;


/**
 * 
 * 
 * 类说明：商户运营日报测试类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年03月22日
 */
public class MerchantOperationDayReportServiceImplTest extends SpringTestCase{

	@Resource
	private IMerchantOperationDayReportService merchantOperationDayReportService;
	/**
	 * 
	 *
	 * 方法说明：查找商户运营日报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findMerchantOperationDayReportPage() throws TsfaServiceException{
		FindMerchantOperationDayReportPage findMerchantOperationDayReportPage = new FindMerchantOperationDayReportPage();
		Page<FindMerchantOperationDayReportPageReturn> page = merchantOperationDayReportService.findMerchantOperationDayReportPage(findMerchantOperationDayReportPage);
		Assert.assertNotNull(page);
		
	}
	
}
