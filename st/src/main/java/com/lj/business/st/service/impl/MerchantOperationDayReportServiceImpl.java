package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.ICompanyOperationDayReportDao;
import com.lj.business.st.dao.IMerchantOperationDayReportDao;
import com.lj.business.st.domain.CompanyOperationDayReport;
import com.lj.business.st.domain.MerchantOperationDayReport;
import com.lj.business.st.dto.mec.FindMerchantOperationDayReportPage;
import com.lj.business.st.dto.mec.FindMerchantOperationDayReportPageReturn;
import com.lj.business.st.service.IMerchantOperationDayReportService;

/**
 * 
 * 
 * 类说明：商户运营日报实现类
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
@Service
public class MerchantOperationDayReportServiceImpl implements IMerchantOperationDayReportService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MerchantOperationDayReportServiceImpl.class);
	
	@Resource
	private IMerchantOperationDayReportDao merchantOperationDayReportDao;
	
	@Resource
	private ICompanyOperationDayReportDao companyOperationDayReportDao;
	
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
	@Override
	public void generatorDayReport(String merchantNo, String merchanName, Date reportDate) {
		logger.info("生成商户{}店运营日报{} -------- start", merchantNo, DateUtils.formatDate(reportDate, DateUtils.PATTERN_yyyy_MM_dd));
		
		try {
			CompanyOperationDayReport companyDayReport = companyOperationDayReportDao.stsGroupByMerchant(merchantNo, reportDate);
			
			MerchantOperationDayReport merchantOperationDayReport = new MerchantOperationDayReport();
			merchantOperationDayReport.setCode(GUID.generateByUUID());
			merchantOperationDayReport.setReportDate(reportDate);
			merchantOperationDayReport.setMerchantNo(merchantNo);
			merchantOperationDayReport.setMerchantName(merchanName);
			if(companyDayReport != null) {
				merchantOperationDayReport.setMbrTotalCount(companyDayReport.getMbrTotalCount());
				merchantOperationDayReport.setMbrTodayCount(companyDayReport.getMbrTodayCount());
				merchantOperationDayReport.setOrdGoodCount(companyDayReport.getOrdGoodCount());
				merchantOperationDayReport.setOrdOrderCount(companyDayReport.getOrdOrderCount());
				merchantOperationDayReport.setOrdOrderAmount(companyDayReport.getOrdOrderAmount());
				merchantOperationDayReport.setOrdCustomerPrice(companyDayReport.getOrdCustomerPrice());
				merchantOperationDayReport.setOrdActivitieAmount(companyDayReport.getOrdActivitieAmount());
				merchantOperationDayReport.setOrdPayAmount(companyDayReport.getOrdPayAmount());
				merchantOperationDayReport.setSuccGoodCount(companyDayReport.getSuccGoodCount());
				merchantOperationDayReport.setSuccOrderCount(companyDayReport.getSuccOrderCount());
				merchantOperationDayReport.setSuccOrderAmount(companyDayReport.getSuccOrderAmount());
				merchantOperationDayReport.setSuccCustomerPrice(companyDayReport.getSuccCustomerPrice());
				merchantOperationDayReport.setSuccActivitieAmount(companyDayReport.getSuccActivitieAmount());
				merchantOperationDayReport.setSuccPayAmount(companyDayReport.getSuccPayAmount());
				merchantOperationDayReport.setPvIndexTotalCount(companyDayReport.getPvIndexTotalCount());
				merchantOperationDayReport.setPvIndexMemberCount(companyDayReport.getPvIndexMemberCount());
				merchantOperationDayReport.setPvDetailTotalCount(companyDayReport.getPvDetailTotalCount());
				merchantOperationDayReport.setPvDetailMemberCount(companyDayReport.getPvDetailMemberCount());
				merchantOperationDayReport.setSmsCount(companyDayReport.getSmsCount());
			} else {
				merchantOperationDayReport.setMbrTotalCount(0);
				merchantOperationDayReport.setMbrTodayCount(0);
				merchantOperationDayReport.setOrdGoodCount(0);
				merchantOperationDayReport.setOrdOrderCount(0);
				merchantOperationDayReport.setOrdOrderAmount(0L);
				merchantOperationDayReport.setOrdCustomerPrice(0L);
				merchantOperationDayReport.setOrdActivitieAmount(0L);
				merchantOperationDayReport.setOrdPayAmount(0L);
				merchantOperationDayReport.setSuccGoodCount(0);
				merchantOperationDayReport.setSuccOrderCount(0);
				merchantOperationDayReport.setSuccOrderAmount(0L);
				merchantOperationDayReport.setSuccCustomerPrice(0L);
				merchantOperationDayReport.setSuccActivitieAmount(0L);
				merchantOperationDayReport.setSuccPayAmount(0L);
				merchantOperationDayReport.setPvIndexTotalCount(0);
				merchantOperationDayReport.setPvIndexMemberCount(0);
				merchantOperationDayReport.setPvDetailTotalCount(0);
				merchantOperationDayReport.setPvDetailMemberCount(0);
				merchantOperationDayReport.setSmsCount(0);
			}
//			merchantOperationDayReport.setRemark(companyDayReport.getRemark());
			merchantOperationDayReport.setCreateTime(new Date());
			merchantOperationDayReportDao.insert(merchantOperationDayReport);
		} catch(Exception e) {
			logger.error("生成商户运营日报错误", e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_OPERATION_DAY_REPORT_ADD_ERROR, "生成商户运营日报错误", e);
		}
		
		logger.info("生成商户{}店运营日报{} -------- end", merchantNo, DateUtils.formatDate(reportDate, DateUtils.PATTERN_yyyy_MM_dd));
	}

	@Override
	public Page<FindMerchantOperationDayReportPageReturn> findMerchantOperationDayReportPage(FindMerchantOperationDayReportPage findMerchantOperationDayReportPage) throws TsfaServiceException {
		logger.debug("findMerchantOperationDayReportPage(FindMerchantOperationDayReportPage findMerchantOperationDayReportPage={}) - start", findMerchantOperationDayReportPage); //$NON-NLS-1$

		AssertUtils.notNull(findMerchantOperationDayReportPage);
		List<FindMerchantOperationDayReportPageReturn> returnList = null;
		int count = 0;
		try {
			count = merchantOperationDayReportDao.findMerchantOperationDayReportPageCount(findMerchantOperationDayReportPage);
			if(count > 0) {
				returnList = merchantOperationDayReportDao.findMerchantOperationDayReportPage(findMerchantOperationDayReportPage);
			}
		}  catch (Exception e) {
			logger.error("商户运营日报信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_OPERATION_DAY_REPORT_FIND_PAGE_ERROR,"商户运营日报信息不存在错误.！",e);
		}
		Page<FindMerchantOperationDayReportPageReturn> returnPage = new Page<FindMerchantOperationDayReportPageReturn>(returnList, count, findMerchantOperationDayReportPage);

		logger.debug("findMerchantOperationDayReportPage(FindMerchantOperationDayReportPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 
}
