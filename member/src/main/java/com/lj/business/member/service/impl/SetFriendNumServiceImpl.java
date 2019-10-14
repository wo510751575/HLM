package com.lj.business.member.service.impl;

import java.util.Date;
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
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.ISetFriendNumDao;
import com.lj.business.member.domain.SetFriendNum;
import com.lj.business.member.dto.FindSetFriendNumPage;
import com.lj.business.member.dto.SetFriendNumDto;
import com.lj.business.member.service.ISetFriendNumService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.14
 */
@Service
public class SetFriendNumServiceImpl implements ISetFriendNumService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(SetFriendNumServiceImpl.class);
	

	@Resource
	private ISetFriendNumDao setFriendNumDao;
	
	
	@Override
	public void addSetFriendNum(
			SetFriendNumDto setFriendNumDto) throws TsfaServiceException {
		logger.debug("addSetFriendNum(AddSetFriendNum addSetFriendNum={}) - start", setFriendNumDto); 

		AssertUtils.notNull(setFriendNumDto);
		try {
			SetFriendNum setFriendNum = new SetFriendNum();
			//add数据录入
			setFriendNum.setCode(GUID.generateCode());
			setFriendNum.setMerchantNo(setFriendNumDto.getMerchantNo());
			setFriendNum.setMerchantName(setFriendNumDto.getMerchantName());
			setFriendNum.setNoWx(setFriendNumDto.getNoWx());
			setFriendNum.setAlias(setFriendNumDto.getAlias());
			setFriendNum.setStartDate(setFriendNumDto.getStartDate());
			setFriendNum.setEndDate(setFriendNumDto.getEndDate());
			setFriendNum.setNum(setFriendNumDto.getNum());
			setFriendNum.setRemark(setFriendNumDto.getRemark());
			setFriendNum.setRemark2(setFriendNumDto.getRemark2());
			setFriendNum.setRemark3(setFriendNumDto.getRemark3());
			setFriendNum.setRemark4(setFriendNumDto.getRemark4());
			setFriendNum.setCreateId(setFriendNumDto.getCreateId());
			setFriendNum.setCreateDate(new Date());
			setFriendNumDao.insertSelective(setFriendNum);
			logger.debug("addSetFriendNum(SetFriendNumDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增设置添加好友数信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SET_FRIEND_NUM_ADD_ERROR,"新增设置添加好友数信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询设置添加好友数信息
	 *
	 * @param findSetFriendNumPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<SetFriendNumDto>  findSetFriendNums(FindSetFriendNumPage findSetFriendNumPage)throws TsfaServiceException{
		AssertUtils.notNull(findSetFriendNumPage);
		List<SetFriendNumDto> returnList=null;
		try {
			returnList = setFriendNumDao.findSetFriendNums(findSetFriendNumPage);
		} catch (Exception e) {
			logger.error("查找设置添加好友数信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SET_FRIEND_NUM_NOT_EXIST_ERROR,"设置添加好友数信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateSetFriendNum(
			SetFriendNumDto setFriendNumDto)
			throws TsfaServiceException {
		logger.debug("updateSetFriendNum(SetFriendNumDto setFriendNumDto={}) - start", setFriendNumDto); 

		AssertUtils.notNull(setFriendNumDto);
		AssertUtils.notNullAndEmpty(setFriendNumDto.getCode(),"Code不能为空");
		try {
			SetFriendNum setFriendNum = new SetFriendNum();
			//update数据录入
			setFriendNum.setCode(setFriendNumDto.getCode());
			setFriendNum.setMerchantNo(setFriendNumDto.getMerchantNo());
			setFriendNum.setMerchantName(setFriendNumDto.getMerchantName());
			setFriendNum.setNoWx(setFriendNumDto.getNoWx());
			setFriendNum.setAlias(setFriendNumDto.getAlias());
			setFriendNum.setStartDate(setFriendNumDto.getStartDate());
			setFriendNum.setEndDate(setFriendNumDto.getEndDate());
			setFriendNum.setNum(setFriendNumDto.getNum());
			setFriendNum.setRemark(setFriendNumDto.getRemark());
			setFriendNum.setRemark2(setFriendNumDto.getRemark2());
			setFriendNum.setRemark3(setFriendNumDto.getRemark3());
			setFriendNum.setRemark4(setFriendNumDto.getRemark4());
			setFriendNum.setUpdateId(setFriendNumDto.getUpdateId());
			setFriendNum.setUpdateDate(new Date());
			AssertUtils.notUpdateMoreThanOne(setFriendNumDao.updateByPrimaryKeySelective(setFriendNum));
			logger.debug("updateSetFriendNum(SetFriendNumDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("设置添加好友数信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SET_FRIEND_NUM_UPDATE_ERROR,"设置添加好友数信息更新信息错误！",e);
		}
	}

	

	@Override
	public SetFriendNumDto findSetFriendNum(
			SetFriendNumDto setFriendNumDto) throws TsfaServiceException {
		logger.debug("findSetFriendNum(FindSetFriendNum findSetFriendNum={}) - start", setFriendNumDto); 

		AssertUtils.notNull(setFriendNumDto);
		AssertUtils.notAllNull(setFriendNumDto.getCode(),"Code不能为空");
		try {
			SetFriendNum setFriendNum = setFriendNumDao.selectByPrimaryKey(setFriendNumDto.getCode());
			if(setFriendNum == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.SET_FRIEND_NUM_NOT_EXIST_ERROR,"设置添加好友数信息不存在");
			}
			SetFriendNumDto findSetFriendNumReturn = new SetFriendNumDto();
			//find数据录入
			findSetFriendNumReturn.setCode(setFriendNum.getCode());
			findSetFriendNumReturn.setMerchantNo(setFriendNum.getMerchantNo());
			findSetFriendNumReturn.setMerchantName(setFriendNum.getMerchantName());
			findSetFriendNumReturn.setNoWx(setFriendNum.getNoWx());
			findSetFriendNumReturn.setAlias(setFriendNum.getAlias());
			findSetFriendNumReturn.setStartDate(setFriendNum.getStartDate());
			findSetFriendNumReturn.setEndDate(setFriendNum.getEndDate());
			findSetFriendNumReturn.setNum(setFriendNum.getNum());
			findSetFriendNumReturn.setRemark(setFriendNum.getRemark());
			findSetFriendNumReturn.setRemark2(setFriendNum.getRemark2());
			findSetFriendNumReturn.setRemark3(setFriendNum.getRemark3());
			findSetFriendNumReturn.setRemark4(setFriendNum.getRemark4());
			findSetFriendNumReturn.setCreateId(setFriendNum.getCreateId());
			findSetFriendNumReturn.setCreateDate(setFriendNum.getCreateDate());
			findSetFriendNumReturn.setUpdateId(setFriendNum.getUpdateId());
			findSetFriendNumReturn.setUpdateDate(setFriendNum.getUpdateDate());
			
			logger.debug("findSetFriendNum(SetFriendNumDto) - end - return value={}", findSetFriendNumReturn); 
			return findSetFriendNumReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找设置添加好友数信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SET_FRIEND_NUM_FIND_ERROR,"查找设置添加好友数信息信息错误！",e);
		}


	}

	
	@Override
	public Page<SetFriendNumDto> findSetFriendNumPage(
			FindSetFriendNumPage findSetFriendNumPage)
			throws TsfaServiceException {
		logger.debug("findSetFriendNumPage(FindSetFriendNumPage findSetFriendNumPage={}) - start", findSetFriendNumPage); 

		AssertUtils.notNull(findSetFriendNumPage);
		List<SetFriendNumDto> returnList=null;
		int count = 0;
		try {
			count = setFriendNumDao.findSetFriendNumPageCount(findSetFriendNumPage);
			if(count >0) {
				returnList = setFriendNumDao.findSetFriendNumPage(findSetFriendNumPage);
			}
		}  catch (Exception e) {
			logger.error("设置添加好友数信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SET_FRIEND_NUM_FIND_PAGE_ERROR,"设置添加好友数信息不存在错误.！",e);
		}
		Page<SetFriendNumDto> returnPage = new Page<SetFriendNumDto>(returnList, count, findSetFriendNumPage);

		logger.debug("findSetFriendNumPage(FindSetFriendNumPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public int deleteByPrimaryKey(String code) {
		logger.debug("deleteByPrimaryKey(code={}) - start", code); 

		AssertUtils.notNullAndEmpty(code,"Code不能为空");
		int count =0;
		try {
			count =setFriendNumDao.deleteByPrimaryKey(code);
			logger.debug("deleteByPrimaryKey(code) - end - return={}",count); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("设置添加好友数信息删除信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SET_FRIEND_NUM_UPDATE_ERROR,"设置添加好友数信息删除信息错误！",e);
		}
		return count;
	} 


}
