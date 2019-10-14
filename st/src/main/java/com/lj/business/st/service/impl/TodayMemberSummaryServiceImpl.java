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
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.ITodayMemberSummaryDao;
import com.lj.business.st.domain.TodayMemberSummary;
import com.lj.business.st.dto.tms.AddTodayMemberSummary;
import com.lj.business.st.dto.tms.AddTodayMemberSummaryReturn;
import com.lj.business.st.dto.tms.DelTodayMemberSummary;
import com.lj.business.st.dto.tms.DelTodayMemberSummaryReturn;
import com.lj.business.st.dto.tms.FindTodayMemberSummary;
import com.lj.business.st.dto.tms.FindTodayMemberSummaryPage;
import com.lj.business.st.dto.tms.FindTodayMemberSummaryPageReturn;
import com.lj.business.st.dto.tms.FindTodayMemberSummaryReturn;
import com.lj.business.st.dto.tms.UpdateTodayMemberSummary;
import com.lj.business.st.dto.tms.UpdateTodayMemberSummaryReturn;
import com.lj.business.st.service.ITodayMemberSummaryService;

/**
 * 
 * 
 * 类说明：今日客户汇总实现类
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
public class TodayMemberSummaryServiceImpl implements ITodayMemberSummaryService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(TodayMemberSummaryServiceImpl.class);
	
	@Resource
	private ITodayMemberSummaryDao todayMemberSummaryDao;
	
	@Resource
	private IGuidMemberService guidMemberService;
	
	/**
	 * 
	 *
	 * 方法说明：添加今日客户汇总信息
	 *
	 * @param addTodayMemberSummary
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Override
	public AddTodayMemberSummaryReturn addTodayMemberSummary(AddTodayMemberSummary addTodayMemberSummary) throws TsfaServiceException {
		logger.debug("addTodayMemberSummary(AddTodayMemberSummary addTodayMemberSummary={}) - start", addTodayMemberSummary); 

		AssertUtils.notNull(addTodayMemberSummary);
		try {
			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(addTodayMemberSummary.getMemberNoGm());
			FindGuidMemberReturn guidMember = guidMemberService.findGuidMember(findGuidMember);
			
			Date now = new Date();
			TodayMemberSummary todayMemberSummary = todayMemberSummaryDao.selectByMemberAndSummaryDate(addTodayMemberSummary.getMemberNoGm(), now);
			
			// 不存在则添加
			if(todayMemberSummary == null) {
				todayMemberSummary = new TodayMemberSummary();
				todayMemberSummary.setCode(GUID.generateByUUID());
				todayMemberSummary.setSummaryDate(now);
				todayMemberSummary.setMerchantNo(guidMember.getMerchantNo());
//				todayMemberSummary.setShopNo(guidMember.getShopNo());
//				todayMemberSummary.setShopName(guidMember.getShopName());
				todayMemberSummary.setMemberNoGm(addTodayMemberSummary.getMemberNoGm());
				todayMemberSummary.setMemberNameGm(guidMember.getMemberName());
				todayMemberSummary.setShopCount(addTodayMemberSummary.getShopCount());
				todayMemberSummary.setIntentionCount(addTodayMemberSummary.getIntentionCount());
				todayMemberSummary.setScanCount(addTodayMemberSummary.getScanCount());
				todayMemberSummary.setUnscanReason(addTodayMemberSummary.getUnscanReason());
				todayMemberSummary.setInfoCount(addTodayMemberSummary.getInfoCount());
				todayMemberSummary.setUninfoReason(addTodayMemberSummary.getUninfoReason());
				todayMemberSummary.setCreateDate(now);
				todayMemberSummaryDao.insert(todayMemberSummary);
				logger.info("已添加今日客户汇总记录:{}", todayMemberSummary.getCode());
			} else {	// 存在则修改
				throw new TsfaServiceException(ErrorCode.TODAY_MEMBER_SUMMARY_HAS_EXIST_ERROR, "今日已添加客户汇总记录");
				/*TodayMemberSummary update = new TodayMemberSummary();
				update.setCode(todayMemberSummary.getCode());
				update.setShopCount(addTodayMemberSummary.getShopCount());
				update.setIntentionCount(addTodayMemberSummary.getIntentionCount());
				update.setScanCount(addTodayMemberSummary.getScanCount());
				update.setUnscanCount(addTodayMemberSummary.getUnscanCount());
				update.setUnscanReason(addTodayMemberSummary.getUnscanReason());
				update.setInfoCount(addTodayMemberSummary.getInfoCount());
				update.setUninfoCount(addTodayMemberSummary.getUninfoCount());
				update.setUninfoReason(addTodayMemberSummary.getUninfoReason());
				todayMemberSummaryDao.updateByPrimaryKeySelective(update);
				logger.info("已修改今日客户汇总记录:{}", todayMemberSummary.getCode());*/
			}
			
			AddTodayMemberSummaryReturn addTodayMemberSummaryReturn = new AddTodayMemberSummaryReturn();
			
			logger.debug("addTodayMemberSummary(AddTodayMemberSummary) - end - return value={}", addTodayMemberSummaryReturn); 
			return addTodayMemberSummaryReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增今日客户汇总信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TODAY_MEMBER_SUMMARY_ADD_ERROR,"新增今日客户汇总信息错误！",e);
		}
	}
	
	@Override
	public DelTodayMemberSummaryReturn delTodayMemberSummary(DelTodayMemberSummary delTodayMemberSummary) throws TsfaServiceException {
		logger.debug("delTodayMemberSummary(DelTodayMemberSummary delTodayMemberSummary={}) - start", delTodayMemberSummary); 

		AssertUtils.notNull(delTodayMemberSummary);
		AssertUtils.notNull(delTodayMemberSummary.getCode(),"Code不能为空！");
		try {
			todayMemberSummaryDao.deleteByPrimaryKey(delTodayMemberSummary.getCode());
			DelTodayMemberSummaryReturn delTodayMemberSummaryReturn  = new DelTodayMemberSummaryReturn();

			logger.debug("delTodayMemberSummary(DelTodayMemberSummary) - end - return value={}", delTodayMemberSummaryReturn); //$NON-NLS-1$
			return delTodayMemberSummaryReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除今日客户汇总信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TODAY_MEMBER_SUMMARY_DEL_ERROR,"删除今日客户汇总信息错误！",e);
		}
	}

	@Override
	public UpdateTodayMemberSummaryReturn updateTodayMemberSummary(UpdateTodayMemberSummary updateTodayMemberSummary) throws TsfaServiceException {
		logger.debug("updateTodayMemberSummary(UpdateTodayMemberSummary updateTodayMemberSummary={}) - start", updateTodayMemberSummary); //$NON-NLS-1$

		AssertUtils.notNull(updateTodayMemberSummary);
		AssertUtils.notNullAndEmpty(updateTodayMemberSummary.getCode(),"Code不能为空");
		try {
			TodayMemberSummary todayMemberSummary = new TodayMemberSummary();
			//update数据录入
			todayMemberSummary.setCode(updateTodayMemberSummary.getCode());
			todayMemberSummary.setSummaryDate(updateTodayMemberSummary.getSummaryDate());
			todayMemberSummary.setMerchantNo(updateTodayMemberSummary.getMerchantNo());
			todayMemberSummary.setShopNo(updateTodayMemberSummary.getShopNo());
			todayMemberSummary.setShopName(updateTodayMemberSummary.getShopName());
			todayMemberSummary.setMemberNoGm(updateTodayMemberSummary.getMemberNoGm());
			todayMemberSummary.setMemberNameGm(updateTodayMemberSummary.getMemberNameGm());
			todayMemberSummary.setShopCount(updateTodayMemberSummary.getShopCount());
			todayMemberSummary.setIntentionCount(updateTodayMemberSummary.getIntentionCount());
			todayMemberSummary.setScanCount(updateTodayMemberSummary.getScanCount());
			todayMemberSummary.setUnscanReason(updateTodayMemberSummary.getUnscanReason());
			todayMemberSummary.setInfoCount(updateTodayMemberSummary.getInfoCount());
			todayMemberSummary.setUninfoReason(updateTodayMemberSummary.getUninfoReason());
			todayMemberSummary.setCreateDate(updateTodayMemberSummary.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(todayMemberSummaryDao.updateByPrimaryKeySelective(todayMemberSummary));
			UpdateTodayMemberSummaryReturn updateTodayMemberSummaryReturn = new UpdateTodayMemberSummaryReturn();

			logger.debug("updateTodayMemberSummary(UpdateTodayMemberSummary) - end - return value={}", updateTodayMemberSummaryReturn); //$NON-NLS-1$
			return updateTodayMemberSummaryReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("今日客户汇总信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TODAY_MEMBER_SUMMARY_UPDATE_ERROR,"今日客户汇总信息更新信息错误！",e);
		}
	}

	@Override
	public FindTodayMemberSummaryReturn findTodayMemberSummary(FindTodayMemberSummary findTodayMemberSummary) throws TsfaServiceException {
		logger.debug("findTodayMemberSummary(FindTodayMemberSummary findTodayMemberSummary={}) - start", findTodayMemberSummary); //$NON-NLS-1$

		AssertUtils.notNull(findTodayMemberSummary);
		AssertUtils.notAllNull(findTodayMemberSummary.getCode(),"Code不能为空");
		try {
			TodayMemberSummary todayMemberSummary = todayMemberSummaryDao.selectByPrimaryKey(findTodayMemberSummary.getCode());
			if(todayMemberSummary == null){
				throw new TsfaServiceException(ErrorCode.TODAY_MEMBER_SUMMARY_NOT_EXIST_ERROR,"今日客户汇总信息不存在");
			}
			FindTodayMemberSummaryReturn findTodayMemberSummaryReturn = new FindTodayMemberSummaryReturn();
			//find数据录入
			findTodayMemberSummaryReturn.setCode(todayMemberSummary.getCode());
			findTodayMemberSummaryReturn.setSummaryDate(todayMemberSummary.getSummaryDate());
			findTodayMemberSummaryReturn.setMerchantNo(todayMemberSummary.getMerchantNo());
			findTodayMemberSummaryReturn.setShopNo(todayMemberSummary.getShopNo());
			findTodayMemberSummaryReturn.setShopName(todayMemberSummary.getShopName());
			findTodayMemberSummaryReturn.setMemberNoGm(todayMemberSummary.getMemberNoGm());
			findTodayMemberSummaryReturn.setMemberNameGm(todayMemberSummary.getMemberNameGm());
			findTodayMemberSummaryReturn.setShopCount(todayMemberSummary.getShopCount());
			findTodayMemberSummaryReturn.setIntentionCount(todayMemberSummary.getIntentionCount());
			findTodayMemberSummaryReturn.setScanCount(todayMemberSummary.getScanCount());
			findTodayMemberSummaryReturn.setUnscanReason(todayMemberSummary.getUnscanReason());
			findTodayMemberSummaryReturn.setInfoCount(todayMemberSummary.getInfoCount());
			findTodayMemberSummaryReturn.setUninfoReason(todayMemberSummary.getUninfoReason());
			findTodayMemberSummaryReturn.setCreateDate(todayMemberSummary.getCreateDate());
			
			logger.debug("findTodayMemberSummary(FindTodayMemberSummary) - end - return value={}", findTodayMemberSummaryReturn); //$NON-NLS-1$
			return findTodayMemberSummaryReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找今日客户汇总信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TODAY_MEMBER_SUMMARY_FIND_ERROR,"查找今日客户汇总信息信息错误！",e);
		}
	}

	@Override
	public Page<FindTodayMemberSummaryPageReturn> findTodayMemberSummaryPage(FindTodayMemberSummaryPage findTodayMemberSummaryPage) throws TsfaServiceException {
		logger.debug("findTodayMemberSummaryPage(FindTodayMemberSummaryPage findTodayMemberSummaryPage={}) - start", findTodayMemberSummaryPage); //$NON-NLS-1$

		AssertUtils.notNull(findTodayMemberSummaryPage);
		List<FindTodayMemberSummaryPageReturn> returnList = null;
		int count = 0;
		try {
			count = todayMemberSummaryDao.findTodayMemberSummaryPageCount(findTodayMemberSummaryPage);
			if(count > 0) {
				returnList = todayMemberSummaryDao.findTodayMemberSummaryPage(findTodayMemberSummaryPage);
			}
		}  catch (Exception e) {
			logger.error("今日客户汇总信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.TODAY_MEMBER_SUMMARY_FIND_PAGE_ERROR,"今日客户汇总信息不存在错误.！",e);
		}
		Page<FindTodayMemberSummaryPageReturn> returnPage = new Page<FindTodayMemberSummaryPageReturn>(returnList, count, findTodayMemberSummaryPage);

		logger.debug("findTodayMemberSummaryPage(FindTodayMemberSummaryPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 
}
