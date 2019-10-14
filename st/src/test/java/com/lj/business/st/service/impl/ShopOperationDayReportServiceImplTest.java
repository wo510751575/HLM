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
import com.lj.business.st.dto.mec.FindShopOperationDayReportPage;
import com.lj.business.st.dto.mec.FindShopOperationDayReportPageReturn;
import com.lj.business.st.service.IShopOperationDayReportService;


/**
 * 
 * 
 * 类说明：门店运营日报测试类
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
public class ShopOperationDayReportServiceImplTest extends SpringTestCase{

	@Resource
	private IShopOperationDayReportService shopOperationDayReportService;
	
	/**
	 * 
	 *
	 * 方法说明：查找门店运营日报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findShopOperationDayReportPage() throws TsfaServiceException{
		FindShopOperationDayReportPage findShopOperationDayReportPage = new FindShopOperationDayReportPage();
		Page<FindShopOperationDayReportPageReturn> page = shopOperationDayReportService.findShopOperationDayReportPage(findShopOperationDayReportPage);
		Assert.assertNotNull(page);
		
	}
}
