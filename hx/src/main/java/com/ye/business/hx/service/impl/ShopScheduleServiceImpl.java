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
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;

import com.ye.business.hx.dto.ShopScheduleDto;
import com.ye.business.hx.dto.FindShopSchedulePage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IShopScheduleDao;
import com.ye.business.hx.domain.ShopSchedule;
import com.ye.business.hx.service.IShopScheduleService;
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
public class ShopScheduleServiceImpl implements IShopScheduleService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ShopScheduleServiceImpl.class);
	

	@Resource
	private IShopScheduleDao shopScheduleDao;
	
	
	@Override
	public ShopScheduleDto addShopSchedule(
			ShopScheduleDto shopScheduleDto) throws TsfaServiceException {
		logger.debug("addShopSchedule(AddShopSchedule addShopSchedule={}) - start", shopScheduleDto); 

		AssertUtils.notNull(shopScheduleDto);
		try {
			ShopSchedule shopSchedule = new ShopSchedule();
			//add数据录入
			shopSchedule.setCode(GUID.getPreUUID());
			shopSchedule.setShopNo(shopScheduleDto.getShopNo());
			shopSchedule.setShopName(shopScheduleDto.getShopName());
			shopSchedule.setMerchantNo(shopScheduleDto.getMerchantNo());
			shopSchedule.setMerchantName(shopScheduleDto.getMerchantName());
			shopSchedule.setScheduleName(shopScheduleDto.getScheduleName());
			shopSchedule.setWorkTime(shopScheduleDto.getWorkTime());
			shopSchedule.setOffTime(shopScheduleDto.getOffTime());
			shopSchedule.setAptFlag(shopScheduleDto.getAptFlag());
			shopSchedule.setDelFlag(shopScheduleDto.getDelFlag());
			shopSchedule.setUpdateId(shopScheduleDto.getUpdateId());
			shopSchedule.setUpdateDate(new Date());
			shopSchedule.setCreateId(shopScheduleDto.getCreateId());
			shopSchedule.setCreateDate(new Date());
			shopScheduleDao.insertSelective(shopSchedule);

			logger.debug("addShopSchedule(ShopScheduleDto) - end - return"); 
			ShopScheduleDto rtshopScheduleDto=new ShopScheduleDto();
			rtshopScheduleDto.setCode(shopSchedule.getCode());
			return rtshopScheduleDto;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增门店班次信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_SCHEDULE_ADD_ERROR,"新增门店班次信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询门店班次信息
	 *
	 * @param findShopSchedulePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<ShopScheduleDto>  findShopSchedules(FindShopSchedulePage findShopSchedulePage)throws TsfaServiceException{
		AssertUtils.notNull(findShopSchedulePage);
		List<ShopScheduleDto> returnList=null;
		try {
			returnList = shopScheduleDao.findShopSchedules(findShopSchedulePage);
		} catch (Exception e) {
			logger.error("查找门店班次信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_SCHEDULE_NOT_EXIST_ERROR,"门店班次信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateShopSchedule(
			ShopScheduleDto shopScheduleDto)
			throws TsfaServiceException {
		logger.debug("updateShopSchedule(ShopScheduleDto shopScheduleDto={}) - start", shopScheduleDto); //$NON-NLS-1$

		AssertUtils.notNull(shopScheduleDto);
		AssertUtils.notNullAndEmpty(shopScheduleDto.getCode(),"Code不能为空");
		try {
			ShopSchedule shopSchedule = new ShopSchedule();
			//update数据录入
			shopSchedule.setCode(shopScheduleDto.getCode());
			shopSchedule.setShopNo(shopScheduleDto.getShopNo());
			shopSchedule.setShopName(shopScheduleDto.getShopName());
			shopSchedule.setMerchantNo(shopScheduleDto.getMerchantNo());
			shopSchedule.setMerchantName(shopScheduleDto.getMerchantName());
			shopSchedule.setScheduleName(shopScheduleDto.getScheduleName());
			shopSchedule.setWorkTime(shopScheduleDto.getWorkTime());
			shopSchedule.setOffTime(shopScheduleDto.getOffTime());
			shopSchedule.setAptFlag(shopScheduleDto.getAptFlag());
			shopSchedule.setDelFlag(shopScheduleDto.getDelFlag());
			shopSchedule.setUpdateId(shopScheduleDto.getUpdateId());
			shopSchedule.setUpdateDate(new Date());
//			shopSchedule.setCreateId(shopScheduleDto.getCreateId());
//			shopSchedule.setCreateDate(shopScheduleDto.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(shopScheduleDao.updateByPrimaryKeySelective(shopSchedule));
			logger.debug("updateShopSchedule(ShopScheduleDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("门店班次信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_SCHEDULE_UPDATE_ERROR,"门店班次信息更新信息错误！",e);
		}
	}

	

	@Override
	public ShopScheduleDto findShopSchedule(
			ShopScheduleDto shopScheduleDto) throws TsfaServiceException {
		logger.debug("findShopSchedule(FindShopSchedule findShopSchedule={}) - start", shopScheduleDto); //$NON-NLS-1$

		AssertUtils.notNull(shopScheduleDto);
		AssertUtils.notAllNull(shopScheduleDto.getCode(),"Code不能为空");
		try {
			ShopSchedule shopSchedule = shopScheduleDao.selectByPrimaryKey(shopScheduleDto.getCode());
			if(shopSchedule == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.SHOP_SCHEDULE_NOT_EXIST_ERROR,"门店班次信息不存在");
			}
			ShopScheduleDto findShopScheduleReturn = new ShopScheduleDto();
			//find数据录入
			findShopScheduleReturn.setCode(shopSchedule.getCode());
			findShopScheduleReturn.setShopNo(shopSchedule.getShopNo());
			findShopScheduleReturn.setShopName(shopSchedule.getShopName());
			findShopScheduleReturn.setMerchantNo(shopSchedule.getMerchantNo());
			findShopScheduleReturn.setMerchantName(shopSchedule.getMerchantName());
			findShopScheduleReturn.setScheduleName(shopSchedule.getScheduleName());
			findShopScheduleReturn.setWorkTime(shopSchedule.getWorkTime());
			findShopScheduleReturn.setOffTime(shopSchedule.getOffTime());
			findShopScheduleReturn.setAptFlag(shopSchedule.getAptFlag());
			findShopScheduleReturn.setDelFlag(shopSchedule.getDelFlag());
			findShopScheduleReturn.setUpdateId(shopSchedule.getUpdateId());
			findShopScheduleReturn.setUpdateDate(shopSchedule.getUpdateDate());
			findShopScheduleReturn.setCreateId(shopSchedule.getCreateId());
			findShopScheduleReturn.setCreateDate(shopSchedule.getCreateDate());
			
			logger.debug("findShopSchedule(ShopScheduleDto) - end - return value={}", findShopScheduleReturn); //$NON-NLS-1$
			return findShopScheduleReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找门店班次信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_SCHEDULE_FIND_ERROR,"查找门店班次信息信息错误！",e);
		}


	}

	
	@Override
	public Page<ShopScheduleDto> findShopSchedulePage(
			FindShopSchedulePage findShopSchedulePage)
			throws TsfaServiceException {
		logger.debug("findShopSchedulePage(FindShopSchedulePage findShopSchedulePage={}) - start", findShopSchedulePage); //$NON-NLS-1$

		AssertUtils.notNull(findShopSchedulePage);
		List<ShopScheduleDto> returnList=null;
		int count = 0;
		try {
			returnList = shopScheduleDao.findShopSchedulePage(findShopSchedulePage);
			count = shopScheduleDao.findShopSchedulePageCount(findShopSchedulePage);
		}  catch (Exception e) {
			logger.error("门店班次信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SHOP_SCHEDULE_FIND_PAGE_ERROR,"门店班次信息不存在错误.！",e);
		}
		Page<ShopScheduleDto> returnPage = new Page<ShopScheduleDto>(returnList, count, findShopSchedulePage);

		logger.debug("findShopSchedulePage(FindShopSchedulePage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 


}
