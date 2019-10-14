package com.ye.business.hx.service.impl;

import java.util.Date;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IGuidScheduleDao;
import com.ye.business.hx.dao.IGuidScheduleLogDao;
import com.ye.business.hx.domain.GuidScheduleLog;
import com.ye.business.hx.dto.FindGuidScheduleLogPage;
import com.ye.business.hx.dto.FindGuidSchedulePage;
import com.ye.business.hx.dto.GuidScheduleCycleDto;
import com.ye.business.hx.dto.GuidScheduleDto;
import com.ye.business.hx.dto.GuidScheduleLogDto;
import com.ye.business.hx.service.IGuidScheduleLogService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
@Service
public class GuidScheduleLogServiceImpl implements IGuidScheduleLogService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(GuidScheduleLogServiceImpl.class);
	

	@Resource
	private IGuidScheduleLogDao guidScheduleLogDao;
	

	@Resource
	private IGuidScheduleDao guidScheduleDao;
	
	@Override
	public void addGuidScheduleLog(
			GuidScheduleLogDto guidScheduleLogDto) throws TsfaServiceException {
		logger.debug("addGuidScheduleLog(AddGuidScheduleLog addGuidScheduleLog={}) - start", guidScheduleLogDto); 

		AssertUtils.notNull(guidScheduleLogDto);
		try {
			GuidScheduleLog guidScheduleLog = new GuidScheduleLog();
			//add数据录入
			guidScheduleLog.setCode(GUID.getPreUUID());
			guidScheduleLog.setMemberNoGuid(guidScheduleLogDto.getMemberNoGuid());
			guidScheduleLog.setMemberNameGuid(guidScheduleLogDto.getMemberNameGuid());
			guidScheduleLog.setShopNo(guidScheduleLogDto.getShopNo());
			guidScheduleLog.setShopName(guidScheduleLogDto.getShopName());
			guidScheduleLog.setMerchantNo(guidScheduleLogDto.getMerchantNo());
			guidScheduleLog.setMerchantName(guidScheduleLogDto.getMerchantName());
			guidScheduleLog.setWorkDate(guidScheduleLogDto.getWorkDate());
			guidScheduleLog.setDayNum(guidScheduleLogDto.getDayNum());
			guidScheduleLog.setScheduleCode(guidScheduleLogDto.getScheduleCode());
			guidScheduleLog.setScheduleName(guidScheduleLogDto.getScheduleName());
			guidScheduleLog.setWorkTime(guidScheduleLogDto.getWorkTime());
			guidScheduleLog.setOffTime(guidScheduleLogDto.getOffTime());
			guidScheduleLog.setAptFlag(guidScheduleLogDto.getAptFlag());
			guidScheduleLog.setCreateId(guidScheduleLogDto.getCreateId());
			guidScheduleLog.setCreateDate(new Date());
			guidScheduleLogDao.insertSelective(guidScheduleLog);
			logger.debug("addGuidScheduleLog(GuidScheduleLogDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增员工历史排班信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_LOG_ADD_ERROR,"新增员工历史排班信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询员工历史排班信息
	 *
	 * @param findGuidScheduleLogPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<GuidScheduleLogDto>  findGuidScheduleLogs(FindGuidScheduleLogPage findGuidScheduleLogPage)throws TsfaServiceException{
		AssertUtils.notNull(findGuidScheduleLogPage);
		List<GuidScheduleLogDto> returnList=null;
		try {
			returnList = guidScheduleLogDao.findGuidScheduleLogs(findGuidScheduleLogPage);
		} catch (Exception e) {
			logger.error("查找员工历史排班信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_LOG_NOT_EXIST_ERROR,"员工历史排班信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateGuidScheduleLog(
			GuidScheduleLogDto guidScheduleLogDto)
			throws TsfaServiceException {
		logger.debug("updateGuidScheduleLog(GuidScheduleLogDto guidScheduleLogDto={}) - start", guidScheduleLogDto); //$NON-NLS-1$

		AssertUtils.notNull(guidScheduleLogDto);
		AssertUtils.notNullAndEmpty(guidScheduleLogDto.getCode(),"Code不能为空");
		try {
			GuidScheduleLog guidScheduleLog = new GuidScheduleLog();
			//update数据录入
			guidScheduleLog.setCode(guidScheduleLogDto.getCode());
			guidScheduleLog.setMemberNoGuid(guidScheduleLogDto.getMemberNoGuid());
			guidScheduleLog.setMemberNameGuid(guidScheduleLogDto.getMemberNameGuid());
			guidScheduleLog.setShopNo(guidScheduleLogDto.getShopNo());
			guidScheduleLog.setShopName(guidScheduleLogDto.getShopName());
			guidScheduleLog.setMerchantNo(guidScheduleLogDto.getMerchantNo());
			guidScheduleLog.setMerchantName(guidScheduleLogDto.getMerchantName());
			guidScheduleLog.setWorkDate(guidScheduleLogDto.getWorkDate());
			guidScheduleLog.setDayNum(guidScheduleLogDto.getDayNum());
			guidScheduleLog.setScheduleCode(guidScheduleLogDto.getScheduleCode());
			guidScheduleLog.setScheduleName(guidScheduleLogDto.getScheduleName());
			guidScheduleLog.setWorkTime(guidScheduleLogDto.getWorkTime());
			guidScheduleLog.setOffTime(guidScheduleLogDto.getOffTime());
			guidScheduleLog.setAptFlag(guidScheduleLogDto.getAptFlag());
			guidScheduleLog.setCreateId(guidScheduleLogDto.getCreateId());
			guidScheduleLog.setCreateDate(guidScheduleLogDto.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(guidScheduleLogDao.updateByPrimaryKeySelective(guidScheduleLog));
			logger.debug("updateGuidScheduleLog(GuidScheduleLogDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("员工历史排班信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_LOG_UPDATE_ERROR,"员工历史排班信息更新信息错误！",e);
		}
	}

	

	@Override
	public GuidScheduleLogDto findGuidScheduleLog(
			GuidScheduleLogDto guidScheduleLogDto) throws TsfaServiceException {
		logger.debug("findGuidScheduleLog(FindGuidScheduleLog findGuidScheduleLog={}) - start", guidScheduleLogDto); //$NON-NLS-1$

		AssertUtils.notNull(guidScheduleLogDto);
		AssertUtils.notAllNull(guidScheduleLogDto.getCode(),"Code不能为空");
		try {
			GuidScheduleLog guidScheduleLog = guidScheduleLogDao.selectByPrimaryKey(guidScheduleLogDto.getCode());
			if(guidScheduleLog == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_LOG_NOT_EXIST_ERROR,"员工历史排班信息不存在");
			}
			GuidScheduleLogDto findGuidScheduleLogReturn = new GuidScheduleLogDto();
			//find数据录入
			findGuidScheduleLogReturn.setCode(guidScheduleLog.getCode());
			findGuidScheduleLogReturn.setMemberNoGuid(guidScheduleLog.getMemberNoGuid());
			findGuidScheduleLogReturn.setMemberNameGuid(guidScheduleLog.getMemberNameGuid());
			findGuidScheduleLogReturn.setShopNo(guidScheduleLog.getShopNo());
			findGuidScheduleLogReturn.setShopName(guidScheduleLog.getShopName());
			findGuidScheduleLogReturn.setMerchantNo(guidScheduleLog.getMerchantNo());
			findGuidScheduleLogReturn.setMerchantName(guidScheduleLog.getMerchantName());
			findGuidScheduleLogReturn.setWorkDate(guidScheduleLog.getWorkDate());
			findGuidScheduleLogReturn.setDayNum(guidScheduleLog.getDayNum());
			findGuidScheduleLogReturn.setScheduleCode(guidScheduleLog.getScheduleCode());
			findGuidScheduleLogReturn.setScheduleName(guidScheduleLog.getScheduleName());
			findGuidScheduleLogReturn.setWorkTime(guidScheduleLog.getWorkTime());
			findGuidScheduleLogReturn.setOffTime(guidScheduleLog.getOffTime());
			findGuidScheduleLogReturn.setAptFlag(guidScheduleLog.getAptFlag());
			findGuidScheduleLogReturn.setCreateId(guidScheduleLog.getCreateId());
			findGuidScheduleLogReturn.setCreateDate(guidScheduleLog.getCreateDate());
			
			logger.debug("findGuidScheduleLog(GuidScheduleLogDto) - end - return value={}", findGuidScheduleLogReturn); //$NON-NLS-1$
			return findGuidScheduleLogReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找员工历史排班信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_LOG_FIND_ERROR,"查找员工历史排班信息信息错误！",e);
		}


	}

	
	@Override
	public Page<GuidScheduleLogDto> findGuidScheduleLogPage(
			FindGuidScheduleLogPage findGuidScheduleLogPage)
			throws TsfaServiceException {
		logger.debug("findGuidScheduleLogPage(FindGuidScheduleLogPage findGuidScheduleLogPage={}) - start", findGuidScheduleLogPage); //$NON-NLS-1$

		AssertUtils.notNull(findGuidScheduleLogPage);
		List<GuidScheduleLogDto> returnList=null;
		int count = 0;
		try {
			returnList = guidScheduleLogDao.findGuidScheduleLogPage(findGuidScheduleLogPage);
			count = guidScheduleLogDao.findGuidScheduleLogPageCount(findGuidScheduleLogPage);
		}  catch (Exception e) {
			logger.error("员工历史排班信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_LOG_FIND_PAGE_ERROR,"员工历史排班信息不存在错误.！",e);
		}
		Page<GuidScheduleLogDto> returnPage = new Page<GuidScheduleLogDto>(returnList, count, findGuidScheduleLogPage);

		logger.debug("findGuidScheduleLogPage(FindGuidScheduleLogPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 

	@Override
	public Page<GuidScheduleLogDto> findGuidScheduleLogPageGroupByGuid(FindGuidSchedulePage findGuidSchedulePage)
			throws TsfaServiceException {
		logger.debug("findGuidSchedulePageGroupByGuid(FindGuidSchedulePage findGuidSchedulePage={}) - start", findGuidSchedulePage); //$NON-NLS-1$

		AssertUtils.notNull(findGuidSchedulePage);
		List<GuidScheduleLogDto> returnList=null;
		int count = 0;
		try {
			returnList = guidScheduleLogDao.findGuidScheduleLogPageGroupByGuid(findGuidSchedulePage);
			count = guidScheduleLogDao.findGuidScheduleLogPageGroupByGuidCount(findGuidSchedulePage);
		}  catch (Exception e) {
			logger.error("员工班次信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_FIND_PAGE_ERROR,"员工班次信息不存在错误.！",e);
		}
		Page<GuidScheduleLogDto> returnPage = new Page<GuidScheduleLogDto>(returnList, count, findGuidSchedulePage);

		logger.debug("findGuidSchedulePageGroupByGuid(FindGuidSchedulePage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public List<GuidScheduleCycleDto> findGuidScheduleLogByGm(GuidScheduleDto guidScheduleDto)
			throws TsfaServiceException {
		AssertUtils.notNull(guidScheduleDto);
		List<GuidScheduleCycleDto> returnList=null;
		try {
			returnList = guidScheduleLogDao.findGuidScheduleLogByGm(guidScheduleDto);
		} catch (Exception e) {
			logger.error("查找员工班次信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_NOT_EXIST_ERROR,"员工班次信息不存在");
		}
		return returnList;
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void batchAddGuidScheduleLog(String batchNum) throws TsfaServiceException {
		logger.info("批次{},1.开始记录历史排班及更新当周排班信息....",batchNum);
		// 1.将当周排班转移到历史排班
		logger.info("批次{},2.开始记录历史排班信息....",batchNum);
		int logNum=guidScheduleLogDao.batchAddGuidScheduleLog();
		logger.info("批次{},3.记录历史排班信息已经完成，开始清除旧的当周排班信息....",batchNum);
		// 2.完成1后，将当周排班删除
		int deleteWeekNum=guidScheduleDao.deleteWeekGuidSchedule();
		logger.info("批次{},4.清除旧的当周排班信息已完成，开始将固定排班更新到当周排班....",batchNum);
		// 3.将固定排班转移到当周排班
		int insertWeekNum=guidScheduleDao.batchAddGuidScheduleWeek();
		logger.info("批次{},5.更新到当周排班已完成！",batchNum);
		logger.info("批次{},6.所有操作已完成！新增历史排班数:{},清除当周排班数:{},更新当周排班数{}",batchNum,logNum,deleteWeekNum,insertWeekNum);
	}


	
}
