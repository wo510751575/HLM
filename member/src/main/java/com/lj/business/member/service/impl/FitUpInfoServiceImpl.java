package com.lj.business.member.service.impl;

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
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IFitUpInfoDao;
import com.lj.business.member.domain.FitUpInfo;
import com.lj.business.member.dto.fitUpInfo.AddFitUpInfo;
import com.lj.business.member.dto.fitUpInfo.DelFitUpInfo;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfo;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfoPage;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfoPageReturn;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfoReturn;
import com.lj.business.member.dto.fitUpInfo.UpdateFitUpInfo;
import com.lj.business.member.service.IFitUpInfoService;

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
public class FitUpInfoServiceImpl implements IFitUpInfoService { 

	
	private static final String NORMAL = "NORMAL";


	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(FitUpInfoServiceImpl.class);
	

	@Resource
	private IFitUpInfoDao fitUpInfoDao;
	
	
	@Override
	public void addFitUpInfo(
			AddFitUpInfo addFitUpInfo) throws TsfaServiceException {
		logger.debug("addFitUpInfo(AddFitUpInfo addFitUpInfo={}) - start", addFitUpInfo); 

		AssertUtils.notNull(addFitUpInfo);
		try {
			FitUpInfo fitUpInfo = new FitUpInfo();
			if(addFitUpInfo.getStatus() == null)
				addFitUpInfo.setStatus(NORMAL);
			//add数据录入
			fitUpInfo.setCode(GUID.generateCode());
			fitUpInfo.setStyle(addFitUpInfo.getStyle());
			fitUpInfo.setFullName(addFitUpInfo.getFullName());
			fitUpInfo.setHouseType(addFitUpInfo.getHouseType());
			fitUpInfo.setMobile(addFitUpInfo.getMobile());
			fitUpInfo.setImgAddr(addFitUpInfo.getImgAddr());
			fitUpInfo.setStatus(addFitUpInfo.getStatus());
			fitUpInfo.setRemark(addFitUpInfo.getRemark());
			fitUpInfo.setCreateId(addFitUpInfo.getCreateId());
			fitUpInfoDao.insert(fitUpInfo);
			logger.debug("addFitUpInfo(AddFitUpInfo) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增装修需求信息表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FIT_UP_INFO_ADD_ERROR,"新增装修需求信息表信息错误！",e);
		}
	}
	
	
	/*@Override
	public void delFitUpInfo(
			DelFitUpInfo delFitUpInfo) throws TsfaServiceException {
		logger.debug("delFitUpInfo(DelFitUpInfo delFitUpInfo={}) - start", delFitUpInfo); 

		AssertUtils.notNull(delFitUpInfo);
		AssertUtils.notNull(delFitUpInfo.getCode(),"Code不能为空！");
		try {
			fitUpInfoDao.deleteByPrimaryKey(delFitUpInfo.getCode());
			logger.debug("delFitUpInfo(DelFitUpInfo) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除装修需求信息表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FIT_UP_INFO_DEL_ERROR,"删除装修需求信息表信息错误！",e);

		}
	}

	@Override
	public void updateFitUpInfo(
			UpdateFitUpInfo updateFitUpInfo)
			throws TsfaServiceException {
		logger.debug("updateFitUpInfo(UpdateFitUpInfo updateFitUpInfo={}) - start", updateFitUpInfo); 

		AssertUtils.notNull(updateFitUpInfo);
		AssertUtils.notNullAndEmpty(updateFitUpInfo.getCode(),"Code不能为空");
		try {
			FitUpInfo fitUpInfo = new FitUpInfo();
			//update数据录入
			fitUpInfo.setCode(updateFitUpInfo.getCode());
			fitUpInfo.setStyle(updateFitUpInfo.getStyle());
			fitUpInfo.setFullName(updateFitUpInfo.getFullName());
			fitUpInfo.setHouseType(updateFitUpInfo.getHouseType());
			fitUpInfo.setMobile(updateFitUpInfo.getMobile());
			fitUpInfo.setImgAddr(updateFitUpInfo.getImgAddr());
			fitUpInfo.setStatus(updateFitUpInfo.getStatus());
			fitUpInfo.setRemark(updateFitUpInfo.getRemark());
			fitUpInfo.setCreateId(updateFitUpInfo.getCreateId());
			fitUpInfo.setCreateDate(updateFitUpInfo.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(fitUpInfoDao.updateByPrimaryKeySelective(fitUpInfo));
			logger.debug("updateFitUpInfo(UpdateFitUpInfo) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("装修需求信息表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FIT_UP_INFO_UPDATE_ERROR,"装修需求信息表信息更新信息错误！",e);
		}
	}
*/
	

	@Override
	public FindFitUpInfoReturn findFitUpInfo(
			FindFitUpInfo findFitUpInfo) throws TsfaServiceException {
		logger.debug("findFitUpInfo(FindFitUpInfo findFitUpInfo={}) - start", findFitUpInfo); 

		AssertUtils.notNull(findFitUpInfo);
		AssertUtils.notNullAndEmpty(findFitUpInfo.getCode(),"Code不能为空");
		try {
			FitUpInfo fitUpInfo = fitUpInfoDao.selectByPrimaryKey(findFitUpInfo.getCode());
			if(fitUpInfo == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.FIT_UP_INFO_NOT_EXIST_ERROR,"装修需求信息表信息不存在");
			}
			FindFitUpInfoReturn findFitUpInfoReturn = new FindFitUpInfoReturn();
			//find数据录入
			findFitUpInfoReturn.setCode(fitUpInfo.getCode());
			findFitUpInfoReturn.setStyle(fitUpInfo.getStyle());
			findFitUpInfoReturn.setFullName(fitUpInfo.getFullName());
			findFitUpInfoReturn.setHouseType(fitUpInfo.getHouseType());
			findFitUpInfoReturn.setMobile(fitUpInfo.getMobile());
			findFitUpInfoReturn.setImgAddr(fitUpInfo.getImgAddr());
			findFitUpInfoReturn.setStatus(fitUpInfo.getStatus());
			findFitUpInfoReturn.setRemark(fitUpInfo.getRemark());
			findFitUpInfoReturn.setCreateId(fitUpInfo.getCreateId());
			findFitUpInfoReturn.setCreateDate(fitUpInfo.getCreateDate());
			
			logger.debug("findFitUpInfo(FindFitUpInfo) - end - return value={}", findFitUpInfoReturn); 
			return findFitUpInfoReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找装修需求信息表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FIT_UP_INFO_FIND_ERROR,"查找装修需求信息表信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindFitUpInfoPageReturn> findFitUpInfoPage(
			FindFitUpInfoPage findFitUpInfoPage)
			throws TsfaServiceException {
		logger.debug("findFitUpInfoPage(FindFitUpInfoPage findFitUpInfoPage={}) - start", findFitUpInfoPage); 

		AssertUtils.notNull(findFitUpInfoPage);
		List<FindFitUpInfoPageReturn> returnList;
		int count = 0;
		try {
			returnList = fitUpInfoDao.findFitUpInfoPage(findFitUpInfoPage);
			count = fitUpInfoDao.findFitUpInfoPageCount(findFitUpInfoPage);
		}  catch (Exception e) {
			logger.error("装修需求信息表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.FIT_UP_INFO_FIND_PAGE_ERROR,"装修需求信息表信息不存在错误.！",e);
		}
		Page<FindFitUpInfoPageReturn> returnPage = new Page<FindFitUpInfoPageReturn>(returnList, count, findFitUpInfoPage);

		logger.debug("findFitUpInfoPage(FindFitUpInfoPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
