package com.ye.business.ad.service.impl;

/**
 * Copyright &copy; 2018-2021  All rights reserved.
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
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.ad.constant.ErrorCodeRwUserBeansChange;
import com.ye.business.ad.dao.IRwUserBeansChangeDao;
import com.ye.business.ad.domain.RwUserBeansChange;
import com.ye.business.ad.dto.FindRwUserBeansChangePage;
import com.ye.business.ad.dto.RwUserBeansChangeDto;
import com.ye.business.ad.service.IRwUserBeansChangeService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author sjiying
 * 
 * 
 * CreateDate: 2019.04.18
 */
@Service
public class RwUserBeansChangeServiceImpl implements IRwUserBeansChangeService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(RwUserBeansChangeServiceImpl.class);
	

	@Resource
	private IRwUserBeansChangeDao rwUserBeansChangeDao;
	
	
	@Override
	public String addRwUserBeansChange(
			RwUserBeansChangeDto rwUserBeansChangeDto) throws TsfaServiceException {
		logger.debug("addRwUserBeansChange(AddRwUserBeansChange addRwUserBeansChange={}) - start", rwUserBeansChangeDto); 

		AssertUtils.notNull(rwUserBeansChangeDto);
		try {
			RwUserBeansChange rwUserBeansChange = new RwUserBeansChange();
			//add数据录入
			rwUserBeansChange.setCode(rwUserBeansChangeDto.getCode());
			rwUserBeansChange.setMemberNo(rwUserBeansChangeDto.getMemberNo());
			rwUserBeansChange.setMemberName(rwUserBeansChangeDto.getMemberName());
			rwUserBeansChange.setShopNo(rwUserBeansChangeDto.getShopNo());
			rwUserBeansChange.setShopName(rwUserBeansChangeDto.getShopName());
			rwUserBeansChange.setMerchantNo(rwUserBeansChangeDto.getMerchantNo());
			rwUserBeansChange.setMerchantName(rwUserBeansChangeDto.getMerchantName());
			rwUserBeansChange.setOldBeansSum(rwUserBeansChangeDto.getOldBeansSum());
			rwUserBeansChange.setOldBeansUse(rwUserBeansChangeDto.getOldBeansUse());
			rwUserBeansChange.setOldBeansFreeze(rwUserBeansChangeDto.getOldBeansFreeze());
			rwUserBeansChange.setOldBeansNormal(rwUserBeansChangeDto.getOldBeansNormal());
			rwUserBeansChange.setNowBeansSum(rwUserBeansChangeDto.getNowBeansSum());
			rwUserBeansChange.setNowBeansUse(rwUserBeansChangeDto.getNowBeansUse());
			rwUserBeansChange.setNowBeansFreeze(rwUserBeansChangeDto.getNowBeansFreeze());
			rwUserBeansChange.setNowBeansNormal(rwUserBeansChangeDto.getNowBeansNormal());
			rwUserBeansChange.setBeansNum(rwUserBeansChangeDto.getBeansNum());
			rwUserBeansChange.setType(rwUserBeansChangeDto.getType());
			rwUserBeansChange.setStatus(rwUserBeansChangeDto.getStatus());
			rwUserBeansChange.setCreateId(rwUserBeansChangeDto.getCreateId());
			rwUserBeansChange.setCreateName(rwUserBeansChangeDto.getCreateName());
			rwUserBeansChange.setCreateDate(rwUserBeansChangeDto.getCreateDate());
			rwUserBeansChange.setUpdateId(rwUserBeansChangeDto.getUpdateId());
			rwUserBeansChange.setUpdateName(rwUserBeansChangeDto.getUpdateName());
			rwUserBeansChange.setUpdateDate(rwUserBeansChangeDto.getUpdateDate());
			rwUserBeansChange.setRemark(rwUserBeansChangeDto.getRemark());
			rwUserBeansChange.setRemark2(rwUserBeansChangeDto.getRemark2());
			rwUserBeansChange.setRemark3(rwUserBeansChangeDto.getRemark3());
			rwUserBeansChange.setRemark4(rwUserBeansChangeDto.getRemark4());
			
			rwUserBeansChange.setCode(GUID.getPreUUID());
			rwUserBeansChangeDao.insertSelective(rwUserBeansChange);
			logger.debug("addRwUserBeansChange(RwUserBeansChangeDto) - end - return"); 
			return rwUserBeansChange.getCode();
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增用户豆子更改记录信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUserBeansChange.RW_USER_BEANS_CHANGE_ADD_ERROR, "新增用户豆子更改记录信息错误！", e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询用户豆子更改记录信息
	 *
	 * @param findRwUserBeansChangePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<RwUserBeansChangeDto>  findRwUserBeansChanges(FindRwUserBeansChangePage findRwUserBeansChangePage)throws TsfaServiceException{
		AssertUtils.notNull(findRwUserBeansChangePage);
		List<RwUserBeansChangeDto> returnList=null;
		try {
			returnList = rwUserBeansChangeDao.findRwUserBeansChanges(findRwUserBeansChangePage);
		} catch (Exception e) {
			logger.error("查找用户豆子更改记录信息信息错误！",  e);
			throw new TsfaServiceException(ErrorCodeRwUserBeansChange.RW_USER_BEANS_CHANGE_NOT_EXIST_ERROR, "用户豆子更改记录信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateRwUserBeansChange(
			RwUserBeansChangeDto rwUserBeansChangeDto)
			throws TsfaServiceException {
		logger.debug("updateRwUserBeansChange(RwUserBeansChangeDto rwUserBeansChangeDto={}) - start", rwUserBeansChangeDto); 

		AssertUtils.notNull(rwUserBeansChangeDto);
		AssertUtils.notNullAndEmpty(rwUserBeansChangeDto.getCode(),"Code不能为空");
		try {
			RwUserBeansChange rwUserBeansChange = new RwUserBeansChange();
			//update数据录入
			rwUserBeansChange.setCode(rwUserBeansChangeDto.getCode());
			rwUserBeansChange.setMemberNo(rwUserBeansChangeDto.getMemberNo());
			rwUserBeansChange.setMemberName(rwUserBeansChangeDto.getMemberName());
			rwUserBeansChange.setShopNo(rwUserBeansChangeDto.getShopNo());
			rwUserBeansChange.setShopName(rwUserBeansChangeDto.getShopName());
			rwUserBeansChange.setMerchantNo(rwUserBeansChangeDto.getMerchantNo());
			rwUserBeansChange.setMerchantName(rwUserBeansChangeDto.getMerchantName());
			rwUserBeansChange.setOldBeansSum(rwUserBeansChangeDto.getOldBeansSum());
			rwUserBeansChange.setOldBeansUse(rwUserBeansChangeDto.getOldBeansUse());
			rwUserBeansChange.setOldBeansFreeze(rwUserBeansChangeDto.getOldBeansFreeze());
			rwUserBeansChange.setOldBeansNormal(rwUserBeansChangeDto.getOldBeansNormal());
			rwUserBeansChange.setNowBeansSum(rwUserBeansChangeDto.getNowBeansSum());
			rwUserBeansChange.setNowBeansUse(rwUserBeansChangeDto.getNowBeansUse());
			rwUserBeansChange.setNowBeansFreeze(rwUserBeansChangeDto.getNowBeansFreeze());
			rwUserBeansChange.setNowBeansNormal(rwUserBeansChangeDto.getNowBeansNormal());
			rwUserBeansChange.setBeansNum(rwUserBeansChangeDto.getBeansNum());
			rwUserBeansChange.setType(rwUserBeansChangeDto.getType());
			rwUserBeansChange.setStatus(rwUserBeansChangeDto.getStatus());
			rwUserBeansChange.setCreateId(rwUserBeansChangeDto.getCreateId());
			rwUserBeansChange.setCreateName(rwUserBeansChangeDto.getCreateName());
			rwUserBeansChange.setCreateDate(rwUserBeansChangeDto.getCreateDate());
			rwUserBeansChange.setUpdateId(rwUserBeansChangeDto.getUpdateId());
			rwUserBeansChange.setUpdateName(rwUserBeansChangeDto.getUpdateName());
			rwUserBeansChange.setUpdateDate(rwUserBeansChangeDto.getUpdateDate());
			rwUserBeansChange.setRemark(rwUserBeansChangeDto.getRemark());
			rwUserBeansChange.setRemark2(rwUserBeansChangeDto.getRemark2());
			rwUserBeansChange.setRemark3(rwUserBeansChangeDto.getRemark3());
			rwUserBeansChange.setRemark4(rwUserBeansChangeDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(rwUserBeansChangeDao.updateByPrimaryKeySelective(rwUserBeansChange));
			logger.debug("updateRwUserBeansChange(RwUserBeansChangeDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("用户豆子更改记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUserBeansChange.RW_USER_BEANS_CHANGE_UPDATE_ERROR, "用户豆子更改记录信息更新信息错误！", e);
		}
	}

	

	@Override
	public RwUserBeansChangeDto findRwUserBeansChange(
			RwUserBeansChangeDto rwUserBeansChangeDto) throws TsfaServiceException {
		logger.debug("findRwUserBeansChange(FindRwUserBeansChange findRwUserBeansChange={}) - start", rwUserBeansChangeDto); 

		AssertUtils.notNull(rwUserBeansChangeDto);
		AssertUtils.notAllNull(rwUserBeansChangeDto.getCode(),"Code不能为空");
		try {
			RwUserBeansChange rwUserBeansChange = rwUserBeansChangeDao.selectByPrimaryKey(rwUserBeansChangeDto.getCode());
			if(rwUserBeansChange == null){
				return null;
				//throw new TsfaServiceException(ErrorCodeRwUserBeansChange.RW_USER_BEANS_CHANGE_NOT_EXIST_ERROR,"用户豆子更改记录信息不存在");
			}
			RwUserBeansChangeDto findRwUserBeansChangeReturn = new RwUserBeansChangeDto();
			//find数据录入
			findRwUserBeansChangeReturn.setCode(rwUserBeansChange.getCode());
			findRwUserBeansChangeReturn.setMemberNo(rwUserBeansChange.getMemberNo());
			findRwUserBeansChangeReturn.setMemberName(rwUserBeansChange.getMemberName());
			findRwUserBeansChangeReturn.setShopNo(rwUserBeansChange.getShopNo());
			findRwUserBeansChangeReturn.setShopName(rwUserBeansChange.getShopName());
			findRwUserBeansChangeReturn.setMerchantNo(rwUserBeansChange.getMerchantNo());
			findRwUserBeansChangeReturn.setMerchantName(rwUserBeansChange.getMerchantName());
			findRwUserBeansChangeReturn.setOldBeansSum(rwUserBeansChange.getOldBeansSum());
			findRwUserBeansChangeReturn.setOldBeansUse(rwUserBeansChange.getOldBeansUse());
			findRwUserBeansChangeReturn.setOldBeansFreeze(rwUserBeansChange.getOldBeansFreeze());
			findRwUserBeansChangeReturn.setOldBeansNormal(rwUserBeansChange.getOldBeansNormal());
			findRwUserBeansChangeReturn.setNowBeansSum(rwUserBeansChange.getNowBeansSum());
			findRwUserBeansChangeReturn.setNowBeansUse(rwUserBeansChange.getNowBeansUse());
			findRwUserBeansChangeReturn.setNowBeansFreeze(rwUserBeansChange.getNowBeansFreeze());
			findRwUserBeansChangeReturn.setNowBeansNormal(rwUserBeansChange.getNowBeansNormal());
			findRwUserBeansChangeReturn.setBeansNum(rwUserBeansChange.getBeansNum());
			findRwUserBeansChangeReturn.setType(rwUserBeansChange.getType());
			findRwUserBeansChangeReturn.setStatus(rwUserBeansChange.getStatus());
			findRwUserBeansChangeReturn.setCreateId(rwUserBeansChange.getCreateId());
			findRwUserBeansChangeReturn.setCreateName(rwUserBeansChange.getCreateName());
			findRwUserBeansChangeReturn.setCreateDate(rwUserBeansChange.getCreateDate());
			findRwUserBeansChangeReturn.setUpdateId(rwUserBeansChange.getUpdateId());
			findRwUserBeansChangeReturn.setUpdateName(rwUserBeansChange.getUpdateName());
			findRwUserBeansChangeReturn.setUpdateDate(rwUserBeansChange.getUpdateDate());
			findRwUserBeansChangeReturn.setRemark(rwUserBeansChange.getRemark());
			findRwUserBeansChangeReturn.setRemark2(rwUserBeansChange.getRemark2());
			findRwUserBeansChangeReturn.setRemark3(rwUserBeansChange.getRemark3());
			findRwUserBeansChangeReturn.setRemark4(rwUserBeansChange.getRemark4());
			
			logger.debug("findRwUserBeansChange(RwUserBeansChangeDto) - end - return value={}", findRwUserBeansChangeReturn); 
			return findRwUserBeansChangeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找用户豆子更改记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUserBeansChange.RW_USER_BEANS_CHANGE_FIND_ERROR, "查找用户豆子更改记录信息信息错误！", e);
		}


	}

	
	@Override
	public Page<RwUserBeansChangeDto> findRwUserBeansChangePage(
			FindRwUserBeansChangePage findRwUserBeansChangePage)
			throws TsfaServiceException {
		logger.debug("findRwUserBeansChangePage(FindRwUserBeansChangePage findRwUserBeansChangePage={}) - start", findRwUserBeansChangePage); 

		AssertUtils.notNull(findRwUserBeansChangePage);
		List<RwUserBeansChangeDto> returnList=null;
		int count = 0;
		try {
			returnList = rwUserBeansChangeDao.findRwUserBeansChangePage(findRwUserBeansChangePage);
			count = rwUserBeansChangeDao.findRwUserBeansChangePageCount(findRwUserBeansChangePage);
		}  catch (Exception e) {
			logger.error("用户豆子更改记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeRwUserBeansChange.RW_USER_BEANS_CHANGE_FIND_PAGE_ERROR, "用户豆子更改记录信息不存在错误.！", e);
		}
		Page<RwUserBeansChangeDto> returnPage = new Page<RwUserBeansChangeDto>(returnList, count, findRwUserBeansChangePage);

		logger.debug("findRwUserBeansChangePage(FindRwUserBeansChangePage) - end - return value={}", returnPage); 
		return  returnPage;
	} 

	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(rwUserBeansChangeDao.deleteByPrimaryKey(code));
			logger.debug("removeByPrimaryKey(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("用户豆子更改记录信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUserBeansChange.RW_USER_BEANS_CHANGE_UPDATE_ERROR, "用户豆子更改记录信息刪除信息错误！", e);
		}
	}


}
