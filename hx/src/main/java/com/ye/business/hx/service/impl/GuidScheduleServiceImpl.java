package com.ye.business.hx.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IGuidScheduleDao;
import com.ye.business.hx.domain.GuidSchedule;
import com.ye.business.hx.dto.FindGuidSchedulePage;
import com.ye.business.hx.dto.GuidScheduleCycleDto;
import com.ye.business.hx.dto.GuidScheduleDto;
import com.ye.business.hx.service.IGuidScheduleService;
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
public class GuidScheduleServiceImpl implements IGuidScheduleService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(GuidScheduleServiceImpl.class);
	

	@Resource
	private IGuidScheduleDao guidScheduleDao;
	
	
	@Override
	public GuidScheduleDto addGuidSchedule(
			GuidScheduleDto guidScheduleDto) throws TsfaServiceException {
		logger.debug("addGuidSchedule(AddGuidSchedule addGuidSchedule={}) - start", guidScheduleDto); 

		AssertUtils.notNull(guidScheduleDto);
		try {
			GuidSchedule guidSchedule = new GuidSchedule();
			//add数据录入
			guidSchedule.setCode(GUID.getPreUUID());
			guidSchedule.setMemberNoGuid(guidScheduleDto.getMemberNoGuid());
			guidSchedule.setMemberNameGuid(guidScheduleDto.getMemberNameGuid());
			guidSchedule.setShopNo(guidScheduleDto.getShopNo());
			guidSchedule.setShopName(guidScheduleDto.getShopName());
			guidSchedule.setMerchantNo(guidScheduleDto.getMerchantNo());
			guidSchedule.setMerchantName(guidScheduleDto.getMerchantName());
			guidSchedule.setType(guidScheduleDto.getType());
			guidSchedule.setDayNum(guidScheduleDto.getDayNum());
			guidSchedule.setScheduleCode(guidScheduleDto.getScheduleCode());
			guidSchedule.setCreateId(guidScheduleDto.getCreateId());
			guidSchedule.setCreateDate(new Date());
			guidScheduleDao.insertSelective(guidSchedule);
			logger.debug("addGuidSchedule(GuidScheduleDto) - end - return"); 
			
			GuidScheduleDto rtDto=new GuidScheduleDto();
			rtDto.setCode(guidSchedule.getCode());
			return rtDto;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增员工班次信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_ADD_ERROR,"新增员工班次信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询员工班次信息
	 *
	 * @param findGuidSchedulePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<GuidScheduleDto>  findGuidSchedules(FindGuidSchedulePage findGuidSchedulePage)throws TsfaServiceException{
		AssertUtils.notNull(findGuidSchedulePage);
		List<GuidScheduleDto> returnList=null;
		try {
			returnList = guidScheduleDao.findGuidSchedules(findGuidSchedulePage);
		} catch (Exception e) {
			logger.error("查找员工班次信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_NOT_EXIST_ERROR,"员工班次信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateGuidSchedule(
			GuidScheduleDto guidScheduleDto)
			throws TsfaServiceException {
		logger.debug("updateGuidSchedule(GuidScheduleDto guidScheduleDto={}) - start", guidScheduleDto); //$NON-NLS-1$

		AssertUtils.notNull(guidScheduleDto);
		AssertUtils.notNullAndEmpty(guidScheduleDto.getCode(),"Code不能为空");
		try {
			GuidSchedule guidSchedule = new GuidSchedule();
			//update数据录入
			guidSchedule.setCode(guidScheduleDto.getCode());
			guidSchedule.setMemberNoGuid(guidScheduleDto.getMemberNoGuid());
			guidSchedule.setMemberNameGuid(guidScheduleDto.getMemberNameGuid());
			guidSchedule.setShopNo(guidScheduleDto.getShopNo());
			guidSchedule.setShopName(guidScheduleDto.getShopName());
			guidSchedule.setMerchantNo(guidScheduleDto.getMerchantNo());
			guidSchedule.setMerchantName(guidScheduleDto.getMerchantName());
			guidSchedule.setType(guidScheduleDto.getType());
			guidSchedule.setDayNum(guidScheduleDto.getDayNum());
			guidSchedule.setScheduleCode(guidScheduleDto.getScheduleCode());
			guidSchedule.setCreateId(guidScheduleDto.getCreateId());
			guidSchedule.setCreateDate(guidScheduleDto.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(guidScheduleDao.updateByPrimaryKeySelective(guidSchedule));
			logger.debug("updateGuidSchedule(GuidScheduleDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("员工班次信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_UPDATE_ERROR,"员工班次信息更新信息错误！",e);
		}
	}

	

	@Override
	public GuidScheduleDto findGuidSchedule(
			GuidScheduleDto guidScheduleDto) throws TsfaServiceException {
		logger.debug("findGuidSchedule(FindGuidSchedule findGuidSchedule={}) - start", guidScheduleDto); //$NON-NLS-1$

		AssertUtils.notNull(guidScheduleDto);
		AssertUtils.notAllNull(guidScheduleDto.getCode(),"Code不能为空");
		try {
			GuidSchedule guidSchedule = guidScheduleDao.selectByPrimaryKey(guidScheduleDto.getCode());
			if(guidSchedule == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_NOT_EXIST_ERROR,"员工班次信息不存在");
			}
			GuidScheduleDto findGuidScheduleReturn = new GuidScheduleDto();
			//find数据录入
			findGuidScheduleReturn.setCode(guidSchedule.getCode());
			findGuidScheduleReturn.setMemberNoGuid(guidSchedule.getMemberNoGuid());
			findGuidScheduleReturn.setMemberNameGuid(guidSchedule.getMemberNameGuid());
			findGuidScheduleReturn.setShopNo(guidSchedule.getShopNo());
			findGuidScheduleReturn.setShopName(guidSchedule.getShopName());
			findGuidScheduleReturn.setMerchantNo(guidSchedule.getMerchantNo());
			findGuidScheduleReturn.setMerchantName(guidSchedule.getMerchantName());
			findGuidScheduleReturn.setType(guidSchedule.getType());
			findGuidScheduleReturn.setDayNum(guidSchedule.getDayNum());
			findGuidScheduleReturn.setScheduleCode(guidSchedule.getScheduleCode());
			findGuidScheduleReturn.setCreateId(guidSchedule.getCreateId());
			findGuidScheduleReturn.setCreateDate(guidSchedule.getCreateDate());
			
			logger.debug("findGuidSchedule(GuidScheduleDto) - end - return value={}", findGuidScheduleReturn); //$NON-NLS-1$
			return findGuidScheduleReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找员工班次信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_FIND_ERROR,"查找员工班次信息信息错误！",e);
		}


	}

	
	@Override
	public Page<GuidScheduleDto> findGuidSchedulePage(
			FindGuidSchedulePage findGuidSchedulePage)
			throws TsfaServiceException {
		logger.debug("findGuidSchedulePage(FindGuidSchedulePage findGuidSchedulePage={}) - start", findGuidSchedulePage); //$NON-NLS-1$

		AssertUtils.notNull(findGuidSchedulePage);
		List<GuidScheduleDto> returnList=null;
		int count = 0;
		try {
			returnList = guidScheduleDao.findGuidSchedulePage(findGuidSchedulePage);
			count = guidScheduleDao.findGuidSchedulePageCount(findGuidSchedulePage);
		}  catch (Exception e) {
			logger.error("员工班次信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_FIND_PAGE_ERROR,"员工班次信息不存在错误.！",e);
		}
		Page<GuidScheduleDto> returnPage = new Page<GuidScheduleDto>(returnList, count, findGuidSchedulePage);

		logger.debug("findGuidSchedulePage(FindGuidSchedulePage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public int deleteGuidSchedule(GuidScheduleDto guidScheduleDto) throws TsfaServiceException {
		logger.debug("deleteGuidSchedule(GuidScheduleDto guidScheduleDto={}) - start", guidScheduleDto); //$NON-NLS-1$

		AssertUtils.notNull(guidScheduleDto);
		AssertUtils.notNullAndEmpty(guidScheduleDto.getCode(),"Code不能为空");
		try {
			int rt=guidScheduleDao.deleteByPrimaryKey(guidScheduleDto.getCode());
			AssertUtils.notUpdateMoreThanOne(rt);
			logger.debug("deleteGuidSchedule(GuidScheduleDto) - end - return"); //$NON-NLS-1$
			return rt;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("员工班次信息刪除信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_UPDATE_ERROR,"员工班次信息更新信息错误！",e);
		}
	}


	@Override
	public Page<GuidScheduleDto> findGuidSchedulePageGroupByGuid(FindGuidSchedulePage findGuidSchedulePage)
			throws TsfaServiceException {
		logger.debug("findGuidSchedulePageGroupByGuid(FindGuidSchedulePage findGuidSchedulePage={}) - start", findGuidSchedulePage); //$NON-NLS-1$

		AssertUtils.notNull(findGuidSchedulePage);
		List<GuidScheduleDto> returnList=null;
		int count = 0;
		try {
			returnList = guidScheduleDao.findGuidSchedulePageGroupByGuid(findGuidSchedulePage);
			count = guidScheduleDao.findGuidSchedulePageGroupByGuidCount(findGuidSchedulePage);
		}  catch (Exception e) {
			logger.error("员工班次信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_FIND_PAGE_ERROR,"员工班次信息不存在错误.！",e);
		}
		Page<GuidScheduleDto> returnPage = new Page<GuidScheduleDto>(returnList, count, findGuidSchedulePage);

		logger.debug("findGuidSchedulePageGroupByGuid(FindGuidSchedulePage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public List<GuidScheduleCycleDto> findGuidScheduleByGm(GuidScheduleDto guidScheduleDto)
			throws TsfaServiceException {
		AssertUtils.notNull(guidScheduleDto);
		List<GuidScheduleCycleDto> returnList=null;
		try {
			returnList = guidScheduleDao.findGuidScheduleByGm(guidScheduleDto);
		} catch (Exception e) {
			logger.error("查找员工班次信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_NOT_EXIST_ERROR,"员工班次信息不存在");
		}
		return returnList;
	}


	@Override
	public List<GuidScheduleCycleDto> findEnableGuidSchedule(GuidScheduleDto guidScheduleDto)
			throws TsfaServiceException {
		AssertUtils.notNull(guidScheduleDto);
		List<GuidScheduleCycleDto> returnList=null;
		try {
			returnList = guidScheduleDao.findEnableGuidSchedule(guidScheduleDto);
		} catch (Exception e) {
			logger.error("查找员工班次信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_NOT_EXIST_ERROR,"员工班次信息不存在");
		}
		return returnList;
	}


	@Override
	public Map<String, String> upadteGuidScheduleByTypeAndDayNum(GuidScheduleDto guidScheduleDto) throws TsfaServiceException {
		AssertUtils.notNull(guidScheduleDto);
		AssertUtils.notNull(guidScheduleDto.getMemberNoGuid(), "员工编号不能为空");
		AssertUtils.notNull(guidScheduleDto.getType(), "排班类型不能为空");
		try {
			// 1.删除员工排班
			guidScheduleDao.deleteGuidSchedule(guidScheduleDto);
			// 2.有新增则新增
			// key=班次code,value=新增后的排班code
			Map<String, String> rt = new HashMap<String, String>();
			if (StringUtils.isNotEmpty(guidScheduleDto.getScheduleCodes())) {
				AssertUtils.notNull(guidScheduleDto.getDayNum(), "排班天数不能为空");
				AssertUtils.notNullAndEmpty(guidScheduleDto.getMemberNameGuid(),"员工名称不能为空！");
				AssertUtils.notNullAndEmpty(guidScheduleDto.getMerchantNo(),"商户编号不能为空！");
				AssertUtils.notNullAndEmpty(guidScheduleDto.getMerchantName(),"商户名称不能为空！");
				
				String[] scheduleCodes = guidScheduleDto.getScheduleCodes().split(",");
				for (int i = 0; i < scheduleCodes.length; i++) {
					if (StringUtils.isNotEmpty(scheduleCodes[i])) {
						guidScheduleDto.setScheduleCode(scheduleCodes[i]);
						GuidScheduleDto rtOne = addGuidSchedule(guidScheduleDto);
						rt.put(scheduleCodes[i], rtOne.getCode());
					}
				}
			}
			return rt;
		} catch (Exception e) {
			logger.error("修改员工班次信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_UPDATE_ERROR, "修改员工班次信息信息错误");
		}
		
	}


	@Override
	public void addGuidScheduleBatch(GuidScheduleDto guidScheduleDto) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(guidScheduleDto.getType(),"班次类型不能为空！");
		AssertUtils.notNullAndEmpty(guidScheduleDto.getScheduleJson(),"班次信息不能为空！");
//		AssertUtils.notNullAndEmpty(guidScheduleDto.getDayNum(),"班次日期不能为空！");
		AssertUtils.notNullAndEmpty(guidScheduleDto.getMemberNoGuid(),"员工编号不能为空！");
		AssertUtils.notNullAndEmpty(guidScheduleDto.getMemberNameGuid(),"员工名称不能为空！");
		AssertUtils.notNullAndEmpty(guidScheduleDto.getMerchantNo(),"商户编号不能为空！");
		AssertUtils.notNullAndEmpty(guidScheduleDto.getMerchantName(),"商户名称不能为空！");
		
		try {
			if (StringUtils.isNotEmpty(guidScheduleDto.getScheduleJson())) {
				List<GuidScheduleDto> schedules = JSONArray.parseArray(guidScheduleDto.getScheduleJson(),
						GuidScheduleDto.class);
				
				// 1.删除整周员工排班
				guidScheduleDao.deleteGuidSchedule(guidScheduleDto);
				// 2.新增整周员工排班
				for (Iterator<GuidScheduleDto> iterator = schedules.iterator(); iterator.hasNext();) {
					GuidScheduleDto ele = (GuidScheduleDto) iterator.next();
					AssertUtils.notNull(ele.getDayNum(),"排班天数不能为空");
					AssertUtils.notNull(ele.getScheduleCodes(),"班次不能为空");
					
					String[] scheduleCodes = ele.getScheduleCodes().split(",");
					for (int i = 0; i < scheduleCodes.length; i++) {
						if(StringUtils.isNotEmpty(scheduleCodes[i])) {
							ele.setScheduleCode(scheduleCodes[i]);
							ele.setMemberNoGuid(guidScheduleDto.getMemberNoGuid());
							ele.setType(guidScheduleDto.getType());
							ele.setMemberNameGuid(guidScheduleDto.getMemberNameGuid());
							ele.setMerchantNo(guidScheduleDto.getMerchantNo());
							ele.setMerchantName(guidScheduleDto.getMerchantName());
							
							addGuidSchedule(ele);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("批量新增员工班次信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_SCHEDULE_UPDATE_ERROR, "批量新增员工班次信息信息错误！");
		}
		
	} 

	
}
