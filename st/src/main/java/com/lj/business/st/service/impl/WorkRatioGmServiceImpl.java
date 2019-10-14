package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IWorkRatioGmDao;
import com.lj.business.st.domain.OperationAnalysisDayBrief;
import com.lj.business.st.domain.WorkRatioGm;
import com.lj.business.st.dto.AddWorkRatioGm;
import com.lj.business.st.dto.AddWorkRatioGmReturn;
import com.lj.business.st.dto.DelWorkRatioGm;
import com.lj.business.st.dto.DelWorkRatioGmReturn;
import com.lj.business.st.dto.FindFollowClientTotalIndex;
import com.lj.business.st.dto.FindOperateAnalysisReturn;
import com.lj.business.st.dto.FindOperateAnalyzeIndexReturn;
import com.lj.business.st.dto.FindOperateDayReport;
import com.lj.business.st.dto.FindOperateDayReportReturn;
import com.lj.business.st.dto.FindWorkDayGmIndex;
import com.lj.business.st.dto.FindWorkDayGmIndexListReturn;
import com.lj.business.st.dto.FindWorkDayGmIndexReturn;
import com.lj.business.st.dto.FindWorkRatioGm;
import com.lj.business.st.dto.FindWorkRatioGmPage;
import com.lj.business.st.dto.FindWorkRatioGmPageReturn;
import com.lj.business.st.dto.FindWorkRatioGmReturn;
import com.lj.business.st.dto.FindWrgTotal;
import com.lj.business.st.dto.FindWrgTotalReturn;
import com.lj.business.st.dto.UpdateWorkRatioGm;
import com.lj.business.st.dto.UpdateWorkRatioGmReturn;
import com.lj.business.st.emus.DimensionSt;
import com.lj.business.st.emus.WorkDayType;
import com.lj.business.st.service.IOperationAnalysisDayBriefService;
import com.lj.business.st.service.IOperationAnalysisDayChooseService;
import com.lj.business.st.service.IOperationDayChooseService;
import com.lj.business.st.service.IWorkBrDayChooseService;
import com.lj.business.st.service.IWorkRatioGmService;

/**
 * 类说明：实现类
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
@Service
public class WorkRatioGmServiceImpl implements IWorkRatioGmService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WorkRatioGmServiceImpl.class);
	

	/** The work ratio gm dao. */
	@Resource
	private IWorkRatioGmDao workRatioGmDao;

	/** The guid member service. */
	@Resource
	private IGuidMemberService guidMemberService;

	/** The person member service. */
	@Resource
	private IPersonMemberService personMemberService;
	
	@Resource
	private IWorkBrDayChooseService workBrDayChooseService;

	@Resource
	private IOperationDayChooseService operationDayChooseService;

	@Resource
	private IOperationAnalysisDayChooseService operationAnalysisDayChooseService;

	@Resource
	private IOperationAnalysisDayBriefService operationAnalysisDayBriefService;
	
	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioGmService#addWorkRatioGm(com.lj.business.st.dto.AddWorkRatioGm)
	 */
	@Override
	public AddWorkRatioGmReturn addWorkRatioGm(
			AddWorkRatioGm addWorkRatioGm) throws TsfaServiceException {
		logger.debug("addWorkRatioGm(AddWorkRatioGm addWorkRatioGm={}) - start", addWorkRatioGm); 

		AssertUtils.notNull(addWorkRatioGm);
		try {
			WorkRatioGm workRatioGm = new WorkRatioGm();
			//add数据录入
			workRatioGm.setCode(GUID.getPreUUID());
			
			workRatioGm.setMerchantNo(addWorkRatioGm.getMerchantNo());
			workRatioGm.setAreaCode(addWorkRatioGm.getAreaCode());
			workRatioGm.setAreaName(addWorkRatioGm.getAreaName());
			workRatioGm.setShopNo(addWorkRatioGm.getShopNo());
			workRatioGm.setShopName(addWorkRatioGm.getShopName());
			workRatioGm.setMemberNoGm(addWorkRatioGm.getMemberNoGm());
			workRatioGm.setMemberNameGm(addWorkRatioGm.getMemberNameGm());
			workRatioGm.setHeadAddress(addWorkRatioGm.getHeadAddress());
			workRatioGm.setRatioWork(addWorkRatioGm.getRatioWork());
			
			workRatioGm.setNumBeatGm(addWorkRatioGm.getNumBeatGm());
			workRatioGm.setNumPm(addWorkRatioGm.getNumPm());
			workRatioGm.setNumPmAdd(addWorkRatioGm.getNumPmAdd());
			workRatioGm.setNumPmIntention(addWorkRatioGm.getNumPmIntention());
			workRatioGm.setNumPmAbandon(addWorkRatioGm.getNumPmAbandon());
			workRatioGm.setNumSale(addWorkRatioGm.getNumSale());
			workRatioGm.setNumOrder(addWorkRatioGm.getNumOrder());
			workRatioGm.setNumPmOrder(addWorkRatioGm.getNumPmOrder());
			workRatioGm.setNumRead(addWorkRatioGm.getNumRead());
			workRatioGm.setNumSocial(addWorkRatioGm.getNumSocial());
			workRatioGm.setNumVisit(addWorkRatioGm.getNumVisit());
			workRatioGm.setNumPmCf(addWorkRatioGm.getNumPmCf());
			workRatioGm.setNumPmCfDay(addWorkRatioGm.getNumPmCfDay());
			workRatioGm.setNumCfDay(addWorkRatioGm.getNumCfDay());
			workRatioGm.setNumPmKeep(addWorkRatioGm.getNumPmKeep());
			
			workRatioGm.setRatioWorkRank(addWorkRatioGm.getRatioWorkRank());
			workRatioGm.setNumPmRank(addWorkRatioGm.getNumPmRank());
			workRatioGm.setNumPmAddRank(addWorkRatioGm.getNumPmAddRank());
			workRatioGm.setNumSaleRank(addWorkRatioGm.getNumSaleRank());
			workRatioGm.setNumOrderRank(addWorkRatioGm.getNumOrderRank());
			workRatioGm.setNumReadRank(addWorkRatioGm.getNumReadRank());
			workRatioGm.setNumVisitRank(addWorkRatioGm.getNumVisitRank());
			workRatioGm.setRatioWorkRankYtd(addWorkRatioGm.getRatioWorkRankYtd());
			workRatioGm.setNumPmRankYtd(addWorkRatioGm.getNumPmRankYtd());
			workRatioGm.setNumPmAddRankYtd(addWorkRatioGm.getNumPmAddRankYtd());
			workRatioGm.setNumSaleRankYtd(addWorkRatioGm.getNumSaleRankYtd());
			workRatioGm.setNumOrderRankYtd(addWorkRatioGm.getNumOrderRankYtd());
			workRatioGm.setNumReadRankYtd(addWorkRatioGm.getNumReadRankYtd());
			workRatioGm.setNumVisitRankYtd(addWorkRatioGm.getNumVisitRankYtd());
			
			workRatioGm.setDaySt(addWorkRatioGm.getDaySt());
			workRatioGm.setDimensionSt(addWorkRatioGm.getDimensionSt());
			workRatioGm.setCreateDate(addWorkRatioGm.getCreateDate());
			
			workRatioGmDao.insert(workRatioGm);
			AddWorkRatioGmReturn addWorkRatioGmReturn = new AddWorkRatioGmReturn();
			
			logger.debug("addWorkRatioGm(AddWorkRatioGm) - end - return value={}", addWorkRatioGmReturn); 
			return addWorkRatioGmReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增导购工作完成度统计信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_GM_ADD_ERROR,"新增导购工作完成度统计信息错误！",e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioGmService#delWorkRatioGm(com.lj.business.st.dto.DelWorkRatioGm)
	 * 
	 *
	 * 方法说明：删除导购工作完成度统计信息
	 *
	 * @param delWorkRatioGm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	@Override
	public DelWorkRatioGmReturn delWorkRatioGm(
			DelWorkRatioGm delWorkRatioGm) throws TsfaServiceException {
		logger.debug("delWorkRatioGm(DelWorkRatioGm delWorkRatioGm={}) - start", delWorkRatioGm); 

		AssertUtils.notNull(delWorkRatioGm);
		AssertUtils.notNull(delWorkRatioGm.getCode(),"ID不能为空！");
		try {
			workRatioGmDao.deleteByPrimaryKey(delWorkRatioGm.getCode());
			DelWorkRatioGmReturn delWorkRatioGmReturn  = new DelWorkRatioGmReturn();

			logger.debug("delWorkRatioGm(DelWorkRatioGm) - end - return value={}", delWorkRatioGmReturn); //$NON-NLS-1$
			return delWorkRatioGmReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除导购工作完成度统计信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_GM_DEL_ERROR,"删除导购工作完成度统计信息错误！",e);

		}
	}

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioGmService#updateWorkRatioGm(com.lj.business.st.dto.UpdateWorkRatioGm)
	 * 
	 *
	 * 方法说明：修改导购工作完成度统计信息
	 *
	 * @param updateWorkRatioGm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	@Override
	public UpdateWorkRatioGmReturn updateWorkRatioGm(
			UpdateWorkRatioGm updateWorkRatioGm)
			throws TsfaServiceException {
		logger.debug("updateWorkRatioGm(UpdateWorkRatioGm updateWorkRatioGm={}) - start", updateWorkRatioGm); //$NON-NLS-1$

		AssertUtils.notNull(updateWorkRatioGm);
		AssertUtils.notNullAndEmpty(updateWorkRatioGm.getCode(),"ID不能为空");
		try {
			WorkRatioGm workRatioGm = new WorkRatioGm();
			//update数据录入
			workRatioGm.setCode(updateWorkRatioGm.getCode());
			workRatioGm.setMerchantNo(updateWorkRatioGm.getMerchantNo());
			workRatioGm.setAreaCode(updateWorkRatioGm.getAreaCode());
			workRatioGm.setAreaName(updateWorkRatioGm.getAreaName());
			workRatioGm.setShopNo(updateWorkRatioGm.getShopNo());
			workRatioGm.setShopName(updateWorkRatioGm.getShopName());
			workRatioGm.setMemberNoGm(updateWorkRatioGm.getMemberNoGm());
			workRatioGm.setMemberNameGm(updateWorkRatioGm.getMemberNameGm());
			workRatioGm.setHeadAddress(updateWorkRatioGm.getHeadAddress());
			workRatioGm.setRatioWork(updateWorkRatioGm.getRatioWork());
			workRatioGm.setNumBeatGm(updateWorkRatioGm.getNumBeatGm());
			workRatioGm.setNumPm(updateWorkRatioGm.getNumPm());
			workRatioGm.setNumPmAdd(updateWorkRatioGm.getNumPmAdd());
			workRatioGm.setNumPmIntention(updateWorkRatioGm.getNumPmIntention());
			workRatioGm.setNumPmAbandon(updateWorkRatioGm.getNumPmAbandon());
			workRatioGm.setNumSale(updateWorkRatioGm.getNumSale());
			workRatioGm.setNumOrder(updateWorkRatioGm.getNumOrder());
			workRatioGm.setNumRead(updateWorkRatioGm.getNumRead());
			workRatioGm.setNumSocial(updateWorkRatioGm.getNumSocial());
			workRatioGm.setNumPmCf(updateWorkRatioGm.getNumPmCf());
			workRatioGm.setNumPmKeep(updateWorkRatioGm.getNumPmKeep());
			workRatioGm.setRatioWorkRank(updateWorkRatioGm.getRatioWorkRank());
			workRatioGm.setNumPmRank(updateWorkRatioGm.getNumPmRank());
			workRatioGm.setNumPmAddRank(updateWorkRatioGm.getNumPmAddRank());
			workRatioGm.setNumSaleRank(updateWorkRatioGm.getNumSaleRank());
			workRatioGm.setNumOrderRank(updateWorkRatioGm.getNumOrderRank());
			workRatioGm.setNumReadRank(updateWorkRatioGm.getNumReadRank());
			workRatioGm.setRatioWorkRankYtd(updateWorkRatioGm.getRatioWorkRankYtd());
			workRatioGm.setNumPmRankYtd(updateWorkRatioGm.getNumPmRankYtd());
			workRatioGm.setNumPmAddRankYtd(updateWorkRatioGm.getNumPmAddRankYtd());
			workRatioGm.setNumSaleRankYtd(updateWorkRatioGm.getNumSaleRankYtd());
			workRatioGm.setNumOrderRankYtd(updateWorkRatioGm.getNumOrderRankYtd());
			workRatioGm.setNumReadRankYtd(updateWorkRatioGm.getNumReadRankYtd());
			workRatioGm.setDaySt(updateWorkRatioGm.getDaySt());
			workRatioGm.setDimensionSt(updateWorkRatioGm.getDimensionSt());
			workRatioGm.setCreateDate(updateWorkRatioGm.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(workRatioGmDao.updateByPrimaryKeySelective(workRatioGm));
			UpdateWorkRatioGmReturn updateWorkRatioGmReturn = new UpdateWorkRatioGmReturn();

			logger.debug("updateWorkRatioGm(UpdateWorkRatioGm) - end - return value={}", updateWorkRatioGmReturn); //$NON-NLS-1$
			return updateWorkRatioGmReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("导购工作完成度统计信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_GM_UPDATE_ERROR,"导购工作完成度统计信息更新信息错误！",e);
		}
	}

	

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioGmService#findWorkRatioGm(com.lj.business.st.dto.FindWorkRatioGm)
	 * 
	 *
	 * 方法说明：查找导购工作完成度统计信息
	 *
	 * @param findWorkRatioGm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	@Override
	public FindWorkRatioGmReturn findWorkRatioGm(
			FindWorkRatioGm findWorkRatioGm) throws TsfaServiceException {
		logger.debug("findWorkRatioGm(FindWorkRatioGm findWorkRatioGm={}) - start", findWorkRatioGm); //$NON-NLS-1$

		AssertUtils.notNull(findWorkRatioGm);
		if(StringUtils.isEmpty(findWorkRatioGm.getCode()) && (StringUtils.isEmpty(findWorkRatioGm.getMemberNoGm()) 
				|| findWorkRatioGm.getDaySt() == null) ){
			throw new IllegalArgumentException("参数不能全部为空");
		}
		try {
			WorkRatioGm workRatioGmQuery = new WorkRatioGm();
			workRatioGmQuery.setCode(findWorkRatioGm.getCode());
			workRatioGmQuery.setMemberNoGm(findWorkRatioGm.getMemberNoGm());
			workRatioGmQuery.setDaySt(findWorkRatioGm.getDaySt());
			WorkRatioGm workRatioGm = workRatioGmDao.selectByParams(workRatioGmQuery);
			if(workRatioGm != null){
				FindWorkRatioGmReturn findWorkRatioGmReturn = new FindWorkRatioGmReturn();
				//find数据录入
				findWorkRatioGmReturn.setCode(workRatioGm.getCode());
				findWorkRatioGmReturn.setMerchantNo(workRatioGm.getMerchantNo());
				findWorkRatioGmReturn.setAreaCode(workRatioGm.getAreaCode());
				findWorkRatioGmReturn.setAreaName(workRatioGm.getAreaName());
				findWorkRatioGmReturn.setShopNo(workRatioGm.getShopNo());
				findWorkRatioGmReturn.setShopName(workRatioGm.getShopName());
				findWorkRatioGmReturn.setMemberNoGm(workRatioGm.getMemberNoGm());
				findWorkRatioGmReturn.setMemberNameGm(workRatioGm.getMemberNameGm());
				findWorkRatioGmReturn.setHeadAddress(workRatioGm.getHeadAddress());
				findWorkRatioGmReturn.setRatioWork(workRatioGm.getRatioWork());
				findWorkRatioGmReturn.setNumBeatGm(workRatioGm.getNumBeatGm());
				findWorkRatioGmReturn.setNumPm(workRatioGm.getNumPm());
				findWorkRatioGmReturn.setNumPmAdd(workRatioGm.getNumPmAdd());
				findWorkRatioGmReturn.setNumPmIntention(workRatioGm.getNumPmIntention());
				findWorkRatioGmReturn.setNumPmAbandon(workRatioGm.getNumPmAbandon());
				findWorkRatioGmReturn.setNumSale(workRatioGm.getNumSale());
				findWorkRatioGmReturn.setNumOrder(workRatioGm.getNumOrder());
				findWorkRatioGmReturn.setNumRead(workRatioGm.getNumRead());
				findWorkRatioGmReturn.setNumSocial(workRatioGm.getNumSocial());
				findWorkRatioGmReturn.setNumPmCf(workRatioGm.getNumPmCf());
				findWorkRatioGmReturn.setNumPmKeep(workRatioGm.getNumPmKeep());
				findWorkRatioGmReturn.setRatioWorkRank(workRatioGm.getRatioWorkRank());
				findWorkRatioGmReturn.setNumPmRank(workRatioGm.getNumPmRank());
				findWorkRatioGmReturn.setNumPmAddRank(workRatioGm.getNumPmAddRank());
				findWorkRatioGmReturn.setNumSaleRank(workRatioGm.getNumSaleRank());
				findWorkRatioGmReturn.setNumOrderRank(workRatioGm.getNumOrderRank());
				findWorkRatioGmReturn.setNumReadRank(workRatioGm.getNumReadRank());
				findWorkRatioGmReturn.setRatioWorkRankYtd(workRatioGm.getRatioWorkRankYtd());
				findWorkRatioGmReturn.setNumPmRankYtd(workRatioGm.getNumPmRankYtd());
				findWorkRatioGmReturn.setNumPmAddRankYtd(workRatioGm.getNumPmAddRankYtd());
				findWorkRatioGmReturn.setNumSaleRankYtd(workRatioGm.getNumSaleRankYtd());
				findWorkRatioGmReturn.setNumOrderRankYtd(workRatioGm.getNumOrderRankYtd());
				findWorkRatioGmReturn.setNumReadRankYtd(workRatioGm.getNumReadRankYtd());
				findWorkRatioGmReturn.setDaySt(workRatioGm.getDaySt());
				findWorkRatioGmReturn.setDimensionSt(workRatioGm.getDimensionSt());
				findWorkRatioGmReturn.setCreateDate(workRatioGm.getCreateDate());
				
				logger.debug("findWorkRatioGm(FindWorkRatioGm) - end - return value={}", findWorkRatioGmReturn); //$NON-NLS-1$
				return findWorkRatioGmReturn;
			}
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找导购工作完成度统计信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_GM_FIND_ERROR,"查找导购工作完成度统计信息信息错误！",e);
		}
		return null;
	}

	
	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioGmService#findWorkRatioGmPage(com.lj.business.st.dto.FindWorkRatioGmPage)
	 * 
	 *
	 * 方法说明：查找导购工作完成度统计信息
	 *
	 * @param findWorkRatioGmPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	@Override
	public Page<FindWorkRatioGmPageReturn> findWorkRatioGmPage(
			FindWorkRatioGmPage findWorkRatioGmPage)
			throws TsfaServiceException {
		logger.debug("findWorkRatioGmPage(FindWorkRatioGmPage findWorkRatioGmPage={}) - start", findWorkRatioGmPage); //$NON-NLS-1$

		AssertUtils.notNull(findWorkRatioGmPage);
		List<FindWorkRatioGmPageReturn> returnList;
		int count = 0;
		try {
			returnList = workRatioGmDao.findWorkRatioGmPage(findWorkRatioGmPage);
			count = workRatioGmDao.findWorkRatioGmPageCount(findWorkRatioGmPage);
		}  catch (Exception e) {
			logger.error("导购工作完成度统计信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_GM_FIND_PAGE_ERROR,"导购工作完成度统计信息不存在错误.！",e);
		}
		Page<FindWorkRatioGmPageReturn> returnPage = new Page<FindWorkRatioGmPageReturn>(returnList, count, findWorkRatioGmPage);

		logger.debug("findWorkRatioGmPage(FindWorkRatioGmPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioGmService#addWorkRatioGm()
	 *
	 * 方法说明：每天定时添加导购工作完成度统计信息
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月24日
	 *
	 */
	@Override
	@Deprecated //模式修改，放置到报表接口出统一生成 update by LeoPeng 2017-08-18
	public AddWorkRatioGmReturn addWorkRatioGm() throws TsfaServiceException {
		logger.info("开始增加导购工作统计");
		// 查询第一页的数据并插入统计数据
		try {
			Page<FindGuidMemberPageReturn> guidMemberPage = guidMemberService.findGuidMemberPage(1, 100);
			doAddWorkRatioGm(guidMemberPage.getRows());

			// 计算总页数并从第二页插入统计数据
			long totalPage = guidMemberPage.getTotal() / guidMemberPage.getLimit();
			totalPage = (guidMemberPage.getTotal() % guidMemberPage.getLimit() == 0) ? totalPage : totalPage + 1;
			for (int i = 2; i < totalPage; i++) {
				guidMemberPage = guidMemberService.findGuidMemberPage(i, 100);
				doAddWorkRatioGm(guidMemberPage.getRows());
			}

			// 计算每个分店每个导购的客户数量排名
			return new AddWorkRatioGmReturn();
		}  catch (Exception e) {
			logger.error("每天定时添加导购工作完成度统计信息时异常",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_GM_FIND_PAGE_ERROR,"每天定时添加导购工作完成度统计信息时异常.！",e);
		}
	}

	
	/**
	 * Do add work ratio gm.
	 *
	 * @param members the members
	 */
	@Deprecated //模式修改，放置到报表接口出统一生成 update by LeoPeng 2017-08-18
	private void doAddWorkRatioGm(Collection<FindGuidMemberPageReturn> members) {
		if (!CollectionUtils.isEmpty(members)) {
			Date now = new Date();
			for (FindGuidMemberPageReturn each : members) {
				// 查询导购下面的客户数量
				int customerCount = personMemberService.findCountByMemberNoGm(each.getMerchantNo(), each.getMemberNo());

				AddWorkRatioGm addWorkRatioGm = new AddWorkRatioGm();
				addWorkRatioGm.setMerchantNo(each.getMerchantNo());
				addWorkRatioGm.setAreaCode(each.getAreaCode());
				addWorkRatioGm.setAreaName("");// TODO wupengfei 区域名称
//				addWorkRatioGm.setShopNo(each.getShopNo());
//				addWorkRatioGm.setShopName(each.getShopName());
				addWorkRatioGm.setMemberNoGm(each.getMemberNo());
				addWorkRatioGm.setMemberNameGm(each.getMemberName());
				addWorkRatioGm.setHeadAddress(each.getHeadAddress());
				addWorkRatioGm.setRatioWork(0.0);
				addWorkRatioGm.setNumPm((long) customerCount);
				addWorkRatioGm.setNumPmAdd(0L);
				addWorkRatioGm.setNumSale(0L);
				addWorkRatioGm.setNumOrder(0L);
				addWorkRatioGm.setNumRead(0L);
				addWorkRatioGm.setRatioWorkRank(0);
				addWorkRatioGm.setNumPmRank(0);// TODO wupengfei 客户数量排名
				addWorkRatioGm.setNumPmAddRank(0);
				addWorkRatioGm.setNumSaleRank(0);
				addWorkRatioGm.setNumOrderRank(0);
				addWorkRatioGm.setNumReadRank(0);
				addWorkRatioGm.setDaySt(now);
				addWorkRatioGm.setDimensionSt("GUID");
				addWorkRatioGm(addWorkRatioGm);
			}
		}
	}



	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioGmService#findWrgTotal(com.lj.business.st.dto.FindWrgTotal)
	 * 
	 *
	 * 方法说明：获取导购工作统计信息_H5用
	 *
	 * @param findWrgTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年7月31日
	 *
	 */
	public List<FindWrgTotalReturn> findWrgTotal(FindWrgTotal findWrgTotal)
			throws TsfaServiceException {
		AssertUtils.notNull(findWrgTotal);
		AssertUtils.notNull(findWrgTotal.getMerchantNo(),"商户编号不能为空！");
		AssertUtils.notNull(findWrgTotal.getBeginDate(),"开始日期不能为空！");
		AssertUtils.notNull(findWrgTotal.getEndDate(),"结束日期不能为空！");
		try {
			if("HARDWORKING_AWARD".equals(findWrgTotal.getFlag())){
				findWrgTotal.setOrderBy("RATIO_WORK");
			}else if("CUSTOMER_MAX".equals(findWrgTotal.getFlag())){
				findWrgTotal.setOrderBy("NUM_PM");
			}else if("NEW_CUSTOMER_MAX".equals(findWrgTotal.getFlag())){
				findWrgTotal.setOrderBy("NUM_PM_ADD");
			}else if("BEST_SALES".equals(findWrgTotal.getFlag())){
				findWrgTotal.setOrderBy("NUM_SALE");
			}else if("EXTRUDE_FOLLOW".equals(findWrgTotal.getFlag())){
				findWrgTotal.setOrderBy("NUM_ORDER");
			}else if("ACTIVITY_AWARD".equals(findWrgTotal.getFlag())){
				findWrgTotal.setOrderBy("NUM_READ");
			}else
				findWrgTotal.setOrderBy("NUM_PM");
			findWrgTotal.setDimensionSt(DimensionSt.GUID.toString());
			List<FindWrgTotalReturn> list = workRatioGmDao.findWrgTotal(findWrgTotal);
			
			//去除所有的数据，累加每个导购的数据
			Map<String,FindWrgTotalReturn> dataMap = new HashMap<>();
			Map<String,Integer> numMap = new HashMap<>();
			List<FindWrgTotalReturn> returnList = new ArrayList<>();
			
			for (FindWrgTotalReturn findWrgTotalReturn : list) {
				if (dataMap.containsKey(findWrgTotalReturn.getMemberNoGm())) {
					FindWrgTotalReturn totalReturn = dataMap.get(findWrgTotalReturn.getMemberNoGm());
					totalReturn.setRatioWork(totalReturn.getRatioWork() + findWrgTotalReturn.getRatioWork());
					totalReturn.setNumPm(totalReturn.getNumPm() + findWrgTotalReturn.getNumPm());
					totalReturn.setNumPmAdd(totalReturn.getNumPmAdd() + findWrgTotalReturn.getNumPmAdd());
					totalReturn.setNumSale(totalReturn.getNumSale() + findWrgTotalReturn.getNumSale());
					totalReturn.setNumOrder(totalReturn.getNumOrder() + findWrgTotalReturn.getNumOrder());
					totalReturn.setNumRead(totalReturn.getNumRead() + findWrgTotalReturn.getNumRead());
					
					numMap.put(findWrgTotalReturn.getMemberNoGm(), numMap.get(findWrgTotalReturn.getMemberNoGm()) + 1);
				} else {
					dataMap.put(findWrgTotalReturn.getMemberNoGm(), findWrgTotalReturn);
					numMap.put(findWrgTotalReturn.getMemberNoGm(), 1);
					
					returnList.add(findWrgTotalReturn);
				}
			}
			
			//算工作完成度的平均数
			for (FindWrgTotalReturn findWrgTotalReturn : returnList) {
				findWrgTotalReturn.setRatioWork(findWrgTotalReturn.getRatioWork() / numMap.get(findWrgTotalReturn.getMemberNoGm()));
			}
			return returnList;
		}  catch (Exception e) {
			logger.error("查找导购工作完成度统计信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_GM_FIND_ERROR,"查找导购工作完成度统计信息信息错误！",e);
		}
	
	}


	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioGmService#findFcTotalIndex(com.lj.business.st.dto.FindFollowClientTotalIndex)
	 * 
	 *
	 * 方法说明：跟进统计首页
	 *
	 * @param findFollowClientTotalIndex
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年7月31日
	 *
	 */
	@Override
	public Double findFcTotalIndex(FindFollowClientTotalIndex findFollowClientTotalIndex) throws TsfaServiceException {
		AssertUtils.notNull(findFollowClientTotalIndex);
		AssertUtils.notNullAndEmpty(findFollowClientTotalIndex.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findFollowClientTotalIndex.getBeginDate(),"统计开始时间不能为空");
		AssertUtils.notNullAndEmpty(findFollowClientTotalIndex.getEndDate(),"统计结束时间不能为空");
		try{
			Double retDouble = 0.0;
			//查询平均完成度
			List<WorkRatioGm> list = findWorkRatioGmList(findFollowClientTotalIndex);
			if(list != null && list.size() > 0){
				int count = 0;
				BigDecimal wr = new BigDecimal(0);
				for(WorkRatioGm workRatioGm : list){
					wr = wr.add(new BigDecimal(workRatioGm.getRatioWork()));
					count++ ;
				}
				retDouble = wr.divide(new BigDecimal(count),4, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			return retDouble;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("跟进统计首页信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_GM_FIND_ERROR,"跟进统计首页信息错误！",e);
		}
		
	}


	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioGmService#findWorkRatioGmList(com.lj.business.st.dto.FindFollowClientTotalIndex)
	 * 
	 *
	 * 方法说明：查询导购工作统计
	 *
	 * @param findFollowClientTotalIndex
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月31日
	 *
	 */
	@Override
	public List<WorkRatioGm> findWorkRatioGmList(FindFollowClientTotalIndex findFollowClientTotalIndex) throws TsfaServiceException {
		logger.debug("findWorkRatioGmList(FindFollowClientTotalIndex findFollowClientTotalIndex={}) - start", findFollowClientTotalIndex); //$NON-NLS-1$

		AssertUtils.notNull(findFollowClientTotalIndex);
		AssertUtils.notNullAndEmpty(findFollowClientTotalIndex.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findFollowClientTotalIndex.getShopNo(),"分店号不能为空");
		AssertUtils.notNullAndEmpty(findFollowClientTotalIndex.getMemberNoGm(),"导购号不能为空");
		AssertUtils.notNullAndEmpty(findFollowClientTotalIndex.getBeginDate(),"统计开始时间不能为空");
		AssertUtils.notNullAndEmpty(findFollowClientTotalIndex.getEndDate(),"统计结束时间不能为空");
		try{
			//查询平均完成度
			List<WorkRatioGm> list = new ArrayList<WorkRatioGm>();
			list = workRatioGmDao.findWorkRatioGmList(findFollowClientTotalIndex);

			logger.debug("findWorkRatioGmList() - end - return value={}", list); //$NON-NLS-1$
			return list;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("跟进统计首页信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_GM_FIND_ERROR,"查询导购工作统计信息错误！",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioGmService#findGmIndex(com.lj.business.st.dto.FindFollowClientTotalIndex)
	 * 
	 *
	 * 方法说明：根据导购号按照完成度查排名
	 *
	 * @param findFollowClientTotalIndex
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年7月31日
	 *
	 */
	@Override
	public Integer findGmIndex(FindFollowClientTotalIndex findFollowClientTotalIndex) throws TsfaServiceException {
		logger.debug("findGmIndex(FindFollowClientTotalIndex findFollowClientTotalIndex={}) - start", findFollowClientTotalIndex); //$NON-NLS-1$

		AssertUtils.notNull(findFollowClientTotalIndex);
		AssertUtils.notNullAndEmpty(findFollowClientTotalIndex.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findFollowClientTotalIndex.getShopNo(),"分店号不能为空");
		AssertUtils.notNullAndEmpty(findFollowClientTotalIndex.getMemberNoGm(),"导购号不能为空");
		AssertUtils.notNullAndEmpty(findFollowClientTotalIndex.getBeginDate(),"统计开始时间不能为空");
		AssertUtils.notNullAndEmpty(findFollowClientTotalIndex.getEndDate(),"统计结束时间不能为空");
		try{
			//查询排名
			Integer findGmIndexReturn = 0;
			findGmIndexReturn = workRatioGmDao.findGmIndex(findFollowClientTotalIndex);
			if(findGmIndexReturn == null){
				findGmIndexReturn = 0;
			}
			
			FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
			findGuidMemberPage.setMerchantNo(findFollowClientTotalIndex.getMerchantNo());
			int count = guidMemberService.findGuidMemberByMerchantNo(findGuidMemberPage);
			
			if(findGmIndexReturn != 0){
				findGmIndexReturn = count - findGmIndexReturn;
			}
			
			if(findGmIndexReturn <= 0){
				findGmIndexReturn = 0;
			}
			
			logger.debug("findGmIndex() - end - return value={}", findGmIndexReturn); //$NON-NLS-1$
			return findGmIndexReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查询导购排名信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_GM_FIND_ERROR,"查询导购排名信息错误！",e);
		}
	}


	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioGmService#findWorkDayGmIndex(com.lj.business.st.dto.FindWorkDayGmIndex)
	 * 
	 *
	 * 方法说明：日工作简报
	 *
	 * @param findWorkDayGmIndex
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年7月31日
	 *
	 */
	@Override
	public List<FindWorkDayGmIndexReturn> findWorkDayGmIndex(FindWorkDayGmIndex findWorkDayGmIndex) throws TsfaServiceException {
		AssertUtils.notNull(findWorkDayGmIndex);
		AssertUtils.notNullAndEmpty(findWorkDayGmIndex.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findWorkDayGmIndex.getShopNo(),"分店号不能为空");
		AssertUtils.notNullAndEmpty(findWorkDayGmIndex.getMemberNoGm(),"导购号不能为空");
		AssertUtils.notNullAndEmpty(findWorkDayGmIndex.getStDate(),"统计时间不能为空");
		try{
			List<FindWorkDayGmIndexReturn> retList = new ArrayList<>();
			// 查询导购工作统计
			WorkRatioGm workRatioGm = workRatioGmDao.findWorkRatioGmByGmStDate(findWorkDayGmIndex);
			if(workRatioGm != null){
				//查询日工作简报
				List<FindWorkDayGmIndexListReturn> list = workBrDayChooseService.findWorkDayGmIndexByMNo(findWorkDayGmIndex);
				if(list != null && list.size() > 0){
					for(FindWorkDayGmIndexListReturn findWorkDayGmIndexListReturn : list){
						FindWorkDayGmIndexReturn findWorkDayGmIndexReturn = new FindWorkDayGmIndexReturn();
						findWorkDayGmIndexReturn.setCodeList(findWorkDayGmIndexListReturn.getCodeList());
						findWorkDayGmIndexReturn.setCodeName(findWorkDayGmIndexListReturn.getNameList());
						findWorkDayGmIndexReturn.setImgAddr(findWorkDayGmIndexListReturn.getImgAddr());
						findWorkDayGmIndexReturn.setTypeList(findWorkDayGmIndexListReturn.getTypeList());
						findWorkDayGmIndexReturn.setUnitType(findWorkDayGmIndexListReturn.getUnitList());
						if(WorkDayType.SALE.toString().equals(findWorkDayGmIndexListReturn.getTypeList())){
							if(workRatioGm.getNumSale() == null){
								workRatioGm.setNumSale(0L);
							}
							findWorkDayGmIndexReturn.setNum(workRatioGm.getNumSale());
						}else if(WorkDayType.ORDER.toString().equals(findWorkDayGmIndexListReturn.getTypeList())){
							/*if(workRatioGm.getNumPmOrder() ==null){
								workRatioGm.setNumPmOrder(0L);
							}
							findWorkDayGmIndexReturn.setNum(workRatioGm.getNumPmOrder());*/
							if(workRatioGm.getNumOrder() ==null){	// 返回成单数
								workRatioGm.setNumOrder(0L);
							}
							findWorkDayGmIndexReturn.setNum(workRatioGm.getNumOrder());
						}
						else if(WorkDayType.PM_FOLLOW_NUM.toString().equals(findWorkDayGmIndexListReturn.getTypeList())){
							if(workRatioGm.getNumPmCfDay() == null){
								workRatioGm.setNumPmAbandon(0L);
							}
							findWorkDayGmIndexReturn.setNum(workRatioGm.getNumPmCfDay());
						}else if(WorkDayType.FOLLOW_TIMES.toString().equals(findWorkDayGmIndexListReturn.getTypeList())){
							if(workRatioGm.getNumCfDay() == null){
								workRatioGm.setNumPmIntention(0L);
							}
							findWorkDayGmIndexReturn.setNum(workRatioGm.getNumCfDay());
						}else if(WorkDayType.NEW_CUSTOMER.toString().equals(findWorkDayGmIndexListReturn.getTypeList())){
							if(workRatioGm.getNumPmAdd() == null){
								workRatioGm.setNumPmAdd(0L);
							}
							findWorkDayGmIndexReturn.setNum(workRatioGm.getNumPmAdd());
						}else if(WorkDayType.ORDER_RATIO.toString().equals(findWorkDayGmIndexListReturn.getTypeList())){
							if(workRatioGm.getNumOrder() == null){
								workRatioGm.setNumOrder(0L);
							}
							if (workRatioGm.getNumPmCfDay() == null || workRatioGm.getNumPmCfDay() == 0 || workRatioGm.getNumOrder() == null) {
								findWorkDayGmIndexReturn.setNum(0L);
							} else {
								findWorkDayGmIndexReturn.setNum((long)(((double)workRatioGm.getNumOrder() / workRatioGm.getNumPmCfDay()) * 100));
							}
						}
						retList.add(findWorkDayGmIndexReturn);
					}
				}
			}
			return retList;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查询日工作简报信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_BR_DAY_CHOOSE_FIND_ERROR,"查询日工作简报信息错误！",e);
		}
	}

	/**
	 * 查询运营日报
	 * @param findOperateDayReport
	 * @return
	 * @throws TsfaServiceException
	 */
	@Override
	public FindOperateAnalyzeIndexReturn findOperateAnalyzeIndex(FindOperateDayReport findOperateDayReport) throws TsfaServiceException {
		AssertUtils.notNull(findOperateDayReport);
		AssertUtils.notNullAndEmpty(findOperateDayReport.getBeginDate(),"开始日期不能为空");
		AssertUtils.notNullAndEmpty(findOperateDayReport.getEndDate(),"结束日期不能为空");

		try{
			FindOperateAnalyzeIndexReturn result = new FindOperateAnalyzeIndexReturn();
			List<FindOperateDayReportReturn> operateDayList = new ArrayList<>();

			// 查询导购工作统计并计算总量
			Long totalSale = 0L;
			List<WorkRatioGm> workRatioGmParams = workRatioGmDao.findWorkRatioGmParams(findOperateDayReport);
			if (!CollectionUtils.isEmpty(workRatioGmParams)) {
				for (WorkRatioGm each : workRatioGmParams) {
					totalSale += toZeroIfNull(each.getNumSale());
				}
			}
			result.setTotalSale(totalSale);
			result.setTenThousandSale(fenToTenThousand(totalSale));

			// 查询运营日报的选择
			List<FindOperateDayReportReturn> operationDayChooseList = operationDayChooseService.findOperationDayChooseList(findOperateDayReport);
			if (!CollectionUtils.isEmpty(operationDayChooseList)) {
				// 查找昨天的运营日报
				Long totalNumPm = 0L;
				Long totalNumPmAdd = 0L;
				Long totalNumIntention = 0L;
				Long totalNumOrder = 0L;
				Long totalNumAbandon = 0L;
				Long totalNumVisit = 0L;
				String yesterday = getDate(1);
				FindOperateDayReport yesterdayFindOperateDayReport = new FindOperateDayReport();
				BeanUtils.copyProperties(findOperateDayReport, yesterdayFindOperateDayReport);
				yesterdayFindOperateDayReport.setBeginDate(yesterday + " 00:00:00");
				yesterdayFindOperateDayReport.setEndDate(yesterday + " 23:59:59");
				List<WorkRatioGm> yesterdayWorkRatioGmList = workRatioGmDao.findWorkRatioGmParams(yesterdayFindOperateDayReport);
				if (!CollectionUtils.isEmpty(yesterdayWorkRatioGmList)) {
					for (WorkRatioGm each : yesterdayWorkRatioGmList) {
						totalNumPm += toZeroIfNull(each.getNumPm());
						totalNumPmAdd += toZeroIfNull(each.getNumPmAdd());
						totalNumIntention += toZeroIfNull(each.getNumPmIntention());
						totalNumOrder += toZeroIfNull(each.getNumOrder());
						totalNumAbandon += toZeroIfNull(each.getNumPmAbandon());
						totalNumVisit += toZeroIfNull(each.getNumVisit());
					}
				}

				// 设置数量
				for (FindOperateDayReportReturn each : operationDayChooseList) {
					if (WorkDayType.SALE.toString().equals(each.getTypeList())) {
						each.setNum(totalSale);
					} else if (WorkDayType.ABANDON_CUSTOMER.toString().equals(each.getTypeList())) {
						each.setNum(totalNumAbandon);
					} else if (WorkDayType.INTENTION_CUSTOMER.toString().equals(each.getTypeList())) {
						each.setNum(totalNumIntention);
					} else if (WorkDayType.NEW_CUSTOMER.toString().equals(each.getTypeList())) {
						each.setNum(totalNumPmAdd);
					} else if (WorkDayType.ORDER.toString().equals(each.getTypeList())) {
						each.setNum(totalNumOrder);
					} else if (WorkDayType.TOTAL_CUSTOMER.toString().equals(each.getTypeList())) {
						each.setNum(totalNumPm);
					} else if (WorkDayType.VISIT_CUSTOMER.toString().equals(each.getTypeList())) {
						each.setNum(totalNumVisit);
					}
					operateDayList.add(each);
				}
			}
			result.setOperateDayList(operateDayList);

			// 查询昨天的报表摘要
			OperationAnalysisDayBrief yesterdayData = null;
			List<OperationAnalysisDayBrief> operationAnalysisDayBriefs = operationAnalysisDayBriefService.selectYesterdayList(findOperateDayReport);
			if (!CollectionUtils.isEmpty(operationAnalysisDayBriefs)) {
				yesterdayData = operationAnalysisDayBriefs.get(0);
			}

			// 查询运营分析的选择
			List<FindOperateAnalysisReturn> operateAnalysisList = new ArrayList<>();
			List<FindOperateAnalysisReturn> operationAnalysisChooseList = operationAnalysisDayChooseService.findOperationAnalysisChooseList(findOperateDayReport);
			if (!CollectionUtils.isEmpty(operationAnalysisChooseList)) {
				for (FindOperateAnalysisReturn each : operationAnalysisChooseList) {
					if (yesterdayData != null) {//TODO 暂时去掉标语
						/*if (OperationAnalysisEnum.AREA_CUSTOMER.toString().equals(each.getTypeList())) {
							each.setBrief(yesterdayData.getBriefCaArea());
						}
						else if (OperationAnalysisEnum.FOLLOW.toString().equals(each.getTypeList())) {
							each.setBrief(yesterdayData.getBriefCf());
						}
						else if (OperationAnalysisEnum.CUSTOMER_PICTURE.toString().equals(each.getTypeList())) {
							each.setBrief(yesterdayData.getBriefClientAnalyze());
						}
						else if (OperationAnalysisEnum.CUSTOMER_BEHAVIOR_AS.toString().equals(each.getTypeList())) {
							each.setBrief(yesterdayData.getBriefClientAction());
						}
						else if (OperationAnalysisEnum.AREA_ORDER.toString().equals(each.getTypeList())) {
							each.setBrief(yesterdayData.getBriefOrder());
						}
						else if (OperationAnalysisEnum.SALE_FUNNEL.toString().equals(each.getTypeList())) {
							each.setBrief(yesterdayData.getBriefSale());
						}*/
					}
					operateAnalysisList.add(each);
				}
			}
			result.setOperateAnalysisList(operateAnalysisList);
			return result;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查询运营分析信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_BR_DAY_CHOOSE_FIND_ERROR,"查询运营分析信息错误！",e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：根据时间从导购工作统计中查询门店工作统计
	 *
	 * @param preday
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年8月21日
	 *
	 */
	@Override
	public List<WorkRatioGm> findWorkRatioShopByDay(Date preday) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(preday, "查询日期不能为空");
		return workRatioGmDao.findWorkRatioShopByDay(preday);
	}
	
	@Override
	public List<WorkRatioGm> findWorkRatioByDimDay(Map<String, Object> map) {
		AssertUtils.notNull(map, "查询日期不能为空");
		AssertUtils.notNullAndEmpty(map.get("dimensionSt"), "查询日期不能为空");
		AssertUtils.notNullAndEmpty(map.get("date"), "查询日期不能为空");
		return workRatioGmDao.findWorkRatioByDimDay(map);
	}


	private Long toZeroIfNull(Long num) {
		return num == null ? 0 : num;
	}

	private String fenToTenThousand(Long money) {
		if (money == 0) {
			return "0";
		}
		BigDecimal bd = new BigDecimal(money / 100 * 10000);
		bd = bd.setScale(10, RoundingMode.HALF_UP);
		return bd.toString();
	}

	private static String getDate(Integer minusDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -minusDay);
		return DateUtils.formatDate(cal.getTime(), DateUtils.PATTERN_yyyy_MM_dd);
	}


}
