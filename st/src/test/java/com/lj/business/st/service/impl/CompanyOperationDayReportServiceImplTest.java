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
import com.lj.business.st.dto.mec.FindCompanyOperationDayReportPage;
import com.lj.business.st.dto.mec.FindCompanyOperationDayReportPageReturn;
import com.lj.business.st.service.ICompanyOperationDayReportService;


/**
 * 
 * 
 * 类说明：经销商运营日报测试类
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
public class CompanyOperationDayReportServiceImplTest extends SpringTestCase{

	@Resource
	private ICompanyOperationDayReportService companyOperationDayReportService;
	/**
	 * 
	 *
	 * 方法说明：查找经销商运营日报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findCompanyOperationDayReportPage() throws TsfaServiceException{
		FindCompanyOperationDayReportPage findCompanyOperationDayReportPage = new FindCompanyOperationDayReportPage();
		Page<FindCompanyOperationDayReportPageReturn> page = companyOperationDayReportService.findCompanyOperationDayReportPage(findCompanyOperationDayReportPage);
		Assert.assertNotNull(page);
		
	}
}
