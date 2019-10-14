package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.company.FindBranchCompanyReturn;
import com.lj.business.member.service.IBranchCompanyService;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.ICompanyOperationDayReportDao;
import com.lj.business.st.dao.IShopOperationDayReportDao;
import com.lj.business.st.domain.CompanyOperationDayReport;
import com.lj.business.st.domain.ShopOperationDayReport;
import com.lj.business.st.dto.mec.FindCompanyOperationDayReportPage;
import com.lj.business.st.dto.mec.FindCompanyOperationDayReportPageReturn;
import com.lj.business.st.service.ICompanyOperationDayReportService;

/**
 * 
 * 
 * 类说明：经销商运营日报实现类
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
public class CompanyOperationDayReportServiceImpl implements ICompanyOperationDayReportService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CompanyOperationDayReportServiceImpl.class);
	
	@Resource
	private ICompanyOperationDayReportDao companyOperationDayReportDao;
	
	@Resource
	private IShopOperationDayReportDao shopOperationDayReportDao;
	
	@Resource
	private IBranchCompanyService branchCompanyService;

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
	@Override
	public void generatorDayReport(String merchantNo, Date reportDate) {
		String reportDateStr = DateUtils.formatDate(reportDate, DateUtils.PATTERN_yyyy_MM_dd);
		logger.info("生成商户{}下经销商运营日报{} -------- start", merchantNo, reportDateStr);
		
		// 查询商户下所有经销商
		List<FindBranchCompanyReturn> companyList = branchCompanyService.findBranchCompanyByMerchantNo(merchantNo);
		if(companyList != null && !companyList.isEmpty()) {
			logger.info("商户下有{}个经销商待生成运营日报", companyList.size());
			
			List<ShopOperationDayReport> shopGroupByCompanyDayReportList = null;
			try {
				shopGroupByCompanyDayReportList = shopOperationDayReportDao.stsGroupByCompany(merchantNo, reportDate);
			} catch(Exception e) {
				logger.error("按经销商统计门店运营日报数据错误", e);
				throw new TsfaServiceException(ErrorCode.COMPANY_OPERATION_DAY_REPORT_ADD_ERROR, "按经销商统计门店运营日报数据错误", e);
			}
			if(shopGroupByCompanyDayReportList == null || shopGroupByCompanyDayReportList.isEmpty()) {
				logger.info("所有经销商没有产生运营数据，不生成运营日报");
				logger.info("生成商户{}下经销商运营日报{} -------- end", merchantNo, reportDateStr);
				return;
			}
			Map<String, ShopOperationDayReport> shopGroupByCompanyDayReportMap = new HashMap<String, ShopOperationDayReport>();
			for(ShopOperationDayReport shopGroupByCompanyDayReport : shopGroupByCompanyDayReportList) {
				shopGroupByCompanyDayReportMap.put(shopGroupByCompanyDayReport.getCompanyNo(), shopGroupByCompanyDayReport);
			}
			
			// 初始化所有经销商数据
			CompanyOperationDayReport companyOperationDayReport = null;
			Date now = new Date();
			for(FindBranchCompanyReturn company : companyList) {
				try {
					if(company.getCreateDate().after(reportDate)) {
						continue;	// 当天新增的经销商不参与日报统计
					}
//					if(Status.CANCEL.name().equals(company.getStatus()) && company.getUpdateDate().after(reportDate) && company.getUpdateDate().before(DateUtils.getNextday(reportDate))) {
//						continue;	// 当天注销的经销商不参与日报统计
//					}
					
					// 产生了运营数据，才生成经销商日报
					ShopOperationDayReport shopGroupByCompanyDayReport = shopGroupByCompanyDayReportMap.get(company.getCompanyNo());
					if(shopGroupByCompanyDayReport != null && (shopGroupByCompanyDayReport.getMbrTotalCount() > 0 || shopGroupByCompanyDayReport.getOrdOrderCount() > 0 || shopGroupByCompanyDayReport.getSuccOrderCount() > 0 || shopGroupByCompanyDayReport.getPvIndexTotalCount() > 0)) {
						companyOperationDayReport = new CompanyOperationDayReport();
						companyOperationDayReport.setCode(GUID.generateByUUID());
						companyOperationDayReport.setReportDate(reportDate);
						companyOperationDayReport.setDealerCode(company.getDealerCode());
						companyOperationDayReport.setCompanyNo(company.getCompanyNo());
						companyOperationDayReport.setCompanyName(company.getCompanyName());
						companyOperationDayReport.setMerchantNo(merchantNo);
						companyOperationDayReport.setMbrTotalCount(shopGroupByCompanyDayReport.getMbrTotalCount());
						companyOperationDayReport.setMbrTodayCount(shopGroupByCompanyDayReport.getMbrTodayCount());
						companyOperationDayReport.setOrdGoodCount(shopGroupByCompanyDayReport.getOrdGoodCount());
						companyOperationDayReport.setOrdOrderCount(shopGroupByCompanyDayReport.getOrdOrderCount());
						companyOperationDayReport.setOrdOrderAmount(shopGroupByCompanyDayReport.getOrdOrderAmount());
						companyOperationDayReport.setOrdCustomerPrice(shopGroupByCompanyDayReport.getOrdCustomerPrice());
						companyOperationDayReport.setOrdActivitieAmount(shopGroupByCompanyDayReport.getOrdActivitieAmount());
						companyOperationDayReport.setOrdPayAmount(shopGroupByCompanyDayReport.getOrdPayAmount());
						companyOperationDayReport.setSuccGoodCount(shopGroupByCompanyDayReport.getSuccGoodCount());
						companyOperationDayReport.setSuccOrderCount(shopGroupByCompanyDayReport.getSuccOrderCount());
						companyOperationDayReport.setSuccOrderAmount(shopGroupByCompanyDayReport.getSuccOrderAmount());
						companyOperationDayReport.setSuccCustomerPrice(shopGroupByCompanyDayReport.getSuccCustomerPrice());
						companyOperationDayReport.setSuccActivitieAmount(shopGroupByCompanyDayReport.getSuccActivitieAmount());
						companyOperationDayReport.setSuccPayAmount(shopGroupByCompanyDayReport.getSuccPayAmount());
						companyOperationDayReport.setPvIndexTotalCount(shopGroupByCompanyDayReport.getPvIndexTotalCount());
						companyOperationDayReport.setPvIndexMemberCount(shopGroupByCompanyDayReport.getPvIndexMemberCount());
						companyOperationDayReport.setPvDetailTotalCount(shopGroupByCompanyDayReport.getPvDetailTotalCount());
						companyOperationDayReport.setPvDetailMemberCount(shopGroupByCompanyDayReport.getPvDetailMemberCount());
						companyOperationDayReport.setSmsCount(shopGroupByCompanyDayReport.getSmsCount());
		//				companyOperationDayReport.setRemark(company.getRemark());
						companyOperationDayReport.setCreateTime(now);
						companyOperationDayReportDao.insert(companyOperationDayReport);
					} else {
						logger.info("{}经销商没有运营数据不生成运营日报： {} - {}", reportDateStr, company.getCompanyNo(), company.getCompanyName());
					}
				} catch (Exception e) {
					logger.error("创建经销商运营日报记录失败：{} - {}", company.getCompanyNo(), company.getCompanyName());
					logger.error("错误日志：", e);
					throw new TsfaServiceException(ErrorCode.COMPANY_OPERATION_DAY_REPORT_ADD_ERROR, "创建经销商运营日报记录失败", e);
				}
			}
			
			// 统计门店运营日报，更新经销商运营日报数据
			/*try {
				companyOperationDayReportDao.updateByShopDayReport(merchantNo, reportDate);
			} catch(Exception e) {
				logger.error("统计门店运营日报，更新经销商运营日报数据错误", e);
				throw new TsfaServiceException(ErrorCode.COMPANY_OPERATION_DAY_REPORT_ADD_ERROR, "更新经销商运营日报失败", e);
			}*/
		}
		
		logger.info("生成商户{}下经销商运营日报{} -------- end", merchantNo, reportDateStr);
	}



	@Override
	public Page<FindCompanyOperationDayReportPageReturn> findCompanyOperationDayReportPage(FindCompanyOperationDayReportPage findCompanyOperationDayReportPage) throws TsfaServiceException {
		logger.debug("findCompanyOperationDayReportPage(FindCompanyOperationDayReportPage findCompanyOperationDayReportPage={}) - start", findCompanyOperationDayReportPage); //$NON-NLS-1$

		AssertUtils.notNull(findCompanyOperationDayReportPage);
		List<FindCompanyOperationDayReportPageReturn> returnList = null;
		int count = 0;
		try {
			count = companyOperationDayReportDao.findCompanyOperationDayReportPageCount(findCompanyOperationDayReportPage);
			if(count > 0) {
				returnList = companyOperationDayReportDao.findCompanyOperationDayReportPage(findCompanyOperationDayReportPage);
			}
		}  catch (Exception e) {
			logger.error("经销商运营日报信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.COMPANY_OPERATION_DAY_REPORT_FIND_PAGE_ERROR,"经销商运营日报信息不存在错误.！",e);
		}
		Page<FindCompanyOperationDayReportPageReturn> returnPage = new Page<FindCompanyOperationDayReportPageReturn>(returnList, count, findCompanyOperationDayReportPage);

		logger.debug("findCompanyOperationDayReportPage(FindCompanyOperationDayReportPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 
}
