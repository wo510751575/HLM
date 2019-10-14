package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.mec.FindCompanyOperationDayReportPage;
import com.lj.business.st.dto.mec.FindCompanyOperationDayReportPageReturn;


/**
 * 
 * 
 * 类说明：经销商运营日报接口类
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
public interface ICompanyOperationDayReportService {
	
	/**
	 * 
	 *
	 * 方法说明：生成商户下所有经销商指定日期的运营日报
	 *
	 * @param merchantNo
	 * @param reportDate
	 *
	 * @author 曾垂瑜 CreateDate: 2018年6月13日
	 *
	 */
	public void generatorDayReport(String merchantNo, Date reportDate);
	
	/**
	 * 
	 *
	 * 方法说明：查找经销商运营日报信息
	 *
	 * @param findCompanyOperationDayReportPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public Page<FindCompanyOperationDayReportPageReturn> findCompanyOperationDayReportPage(FindCompanyOperationDayReportPage findCompanyOperationDayReportPage) throws TsfaServiceException;
}
