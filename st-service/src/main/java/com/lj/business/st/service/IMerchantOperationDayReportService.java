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
import com.lj.business.st.dto.mec.FindMerchantOperationDayReportPage;
import com.lj.business.st.dto.mec.FindMerchantOperationDayReportPageReturn;


/**
 * 
 * 
 * 类说明：商户运营日报接口类
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
public interface IMerchantOperationDayReportService {
	
	/**
	 * 
	 *
	 * 方法说明：生成商户指定日期的运营日报
	 *
	 * @param merchantNo
	 * @param merchanName
	 * @param reportDate
	 * @author 曾垂瑜 CreateDate: 2018年6月13日
	 *
	 */
	public void generatorDayReport(String merchantNo, String merchanName, Date reportDate);
	
	/**
	 * 
	 *
	 * 方法说明：查找商户运营日报信息
	 *
	 * @param findMerchantOperationDayReportPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public Page<FindMerchantOperationDayReportPageReturn> findMerchantOperationDayReportPage(FindMerchantOperationDayReportPage findMerchantOperationDayReportPage) throws TsfaServiceException;
}
