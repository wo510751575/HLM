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
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IAddFriendsTaskDao;
import com.lj.business.member.dao.IAddFriendsTaskDetailDao;
import com.lj.business.member.domain.AddFriendsTask;
import com.lj.business.member.dto.AddFriendsTaskCountDto;
import com.lj.business.member.dto.AddFriendsTaskDto;
import com.lj.business.member.dto.FindAddFriendsTaskPage;
import com.lj.business.member.service.IAddFriendsTaskService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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
public class AddFriendsTaskServiceImpl implements IAddFriendsTaskService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(AddFriendsTaskServiceImpl.class);
	

	@Resource
	private IAddFriendsTaskDao addFriendsTaskDao;
	@Resource
	private IAddFriendsTaskDetailDao addFriendsTaskDetailDao;
	
	
	@Override
	public void addAddFriendsTask(
			AddFriendsTaskDto addFriendsTaskDto) throws TsfaServiceException {
		logger.debug("addAddFriendsTask(AddAddFriendsTask addAddFriendsTask={}) - start", addFriendsTaskDto); 

		AssertUtils.notNull(addFriendsTaskDto);
		try {
			AddFriendsTask addFriendsTask = new AddFriendsTask();
			//add数据录入
			if(StringUtils.isNotEmpty(addFriendsTaskDto.getCode())) {
				addFriendsTask.setCode(addFriendsTaskDto.getCode());
			}else{
				addFriendsTask.setCode(GUID.generateCode());
			}
			addFriendsTask.setName(addFriendsTaskDto.getName());
			addFriendsTask.setNoWxArrays(addFriendsTaskDto.getNoWxArrays());
			addFriendsTask.setSendMessage(addFriendsTaskDto.getSendMessage());
			addFriendsTask.setTotalPhonenum(addFriendsTaskDto.getTotalPhonenum());
			addFriendsTask.setCompleteNum(addFriendsTaskDto.getCompleteNum());
			addFriendsTask.setSuccessNum(addFriendsTaskDto.getSuccessNum());
			addFriendsTask.setStatus(addFriendsTaskDto.getStatus());
			addFriendsTask.setCreateDate(new Date());
			addFriendsTask.setStartDate(addFriendsTaskDto.getStartDate());
			addFriendsTask.setEndDate(addFriendsTaskDto.getEndDate());
			addFriendsTask.setMerchantNo(addFriendsTaskDto.getMerchantNo());
			addFriendsTask.setMerchantName(addFriendsTaskDto.getMerchantName());
			addFriendsTaskDao.insertSelective(addFriendsTask);
			if(null!=addFriendsTaskDto.getDetailList() && addFriendsTaskDto.getDetailList().size()>0) {
				addFriendsTaskDetailDao.insertForeach(addFriendsTaskDto.getDetailList());
			}
			logger.debug("addAddFriendsTask(AddFriendsTaskDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增加粉任务详情信息错误！",e);
			if(e.getCause() instanceof MySQLIntegrityConstraintViolationException) {
				String message = "";
				if(e.getMessage().contains("'")) {
					int first =e.getMessage().indexOf("'")+1;
					if(first>0) {
						message = e.getMessage().substring(first, e.getMessage().indexOf("'",first));
					}
				}
				throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_DETAIL_ADD_REPEAT_ERROR,"手机号重复："+message,e);
			}
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_ADD_ERROR,"新增加粉任务详情信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询加粉任务详情信息
	 *
	 * @param findAddFriendsTaskPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<AddFriendsTaskDto>  findAddFriendsTasks(FindAddFriendsTaskPage findAddFriendsTaskPage)throws TsfaServiceException{
		AssertUtils.notNull(findAddFriendsTaskPage);
		List<AddFriendsTaskDto> returnList=null;
		try {
			returnList = addFriendsTaskDao.findAddFriendsTasks(findAddFriendsTaskPage);
		} catch (Exception e) {
			logger.error("查找加粉任务详情信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_NOT_EXIST_ERROR,"加粉任务详情信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateAddFriendsTask(
			AddFriendsTaskDto addFriendsTaskDto)
			throws TsfaServiceException {
		logger.debug("updateAddFriendsTask(AddFriendsTaskDto addFriendsTaskDto={}) - start", addFriendsTaskDto); 

		AssertUtils.notNull(addFriendsTaskDto);
		AssertUtils.notNullAndEmpty(addFriendsTaskDto.getCode(),"Code不能为空");
		try {
			AddFriendsTask addFriendsTask = new AddFriendsTask();
			//update数据录入
			addFriendsTask.setCode(addFriendsTaskDto.getCode());
			addFriendsTask.setName(addFriendsTaskDto.getName());
			addFriendsTask.setNoWxArrays(addFriendsTaskDto.getNoWxArrays());
			addFriendsTask.setSendMessage(addFriendsTaskDto.getSendMessage());
			addFriendsTask.setTotalPhonenum(addFriendsTaskDto.getTotalPhonenum());
			addFriendsTask.setCompleteNum(addFriendsTaskDto.getCompleteNum());
			addFriendsTask.setSuccessNum(addFriendsTaskDto.getSuccessNum());
			addFriendsTask.setStatus(addFriendsTaskDto.getStatus());
			addFriendsTask.setUpdateDate(new Date());
			addFriendsTask.setStartDate(addFriendsTaskDto.getStartDate());
			addFriendsTask.setEndDate(addFriendsTaskDto.getEndDate());
			AssertUtils.notUpdateMoreThanOne(addFriendsTaskDao.updateByPrimaryKeySelective(addFriendsTask));
			logger.debug("updateAddFriendsTask(AddFriendsTaskDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("加粉任务详情信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_UPDATE_ERROR,"加粉任务详情信息更新信息错误！",e);
		}
	}

	

	@Override
	public AddFriendsTaskDto findAddFriendsTask(
			AddFriendsTaskDto addFriendsTaskDto) throws TsfaServiceException {
		logger.debug("findAddFriendsTask(FindAddFriendsTask findAddFriendsTask={}) - start", addFriendsTaskDto); 

		AssertUtils.notNull(addFriendsTaskDto);
		AssertUtils.notAllNull(addFriendsTaskDto.getCode(),"Code不能为空");
		try {
			AddFriendsTask addFriendsTask = addFriendsTaskDao.selectByPrimaryKey(addFriendsTaskDto.getCode());
			if(addFriendsTask == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_NOT_EXIST_ERROR,"加粉任务详情信息不存在");
			}
			AddFriendsTaskDto findAddFriendsTaskReturn = new AddFriendsTaskDto();
			//find数据录入
			findAddFriendsTaskReturn.setCode(addFriendsTask.getCode());
			findAddFriendsTaskReturn.setName(addFriendsTask.getName());
			findAddFriendsTaskReturn.setNoWxArrays(addFriendsTask.getNoWxArrays());
			findAddFriendsTaskReturn.setSendMessage(addFriendsTask.getSendMessage());
			findAddFriendsTaskReturn.setTotalPhonenum(addFriendsTask.getTotalPhonenum());
			findAddFriendsTaskReturn.setCompleteNum(addFriendsTask.getCompleteNum());
			findAddFriendsTaskReturn.setSuccessNum(addFriendsTask.getSuccessNum());
			findAddFriendsTaskReturn.setStatus(addFriendsTask.getStatus());
			findAddFriendsTaskReturn.setCreateDate(addFriendsTask.getCreateDate());
			findAddFriendsTaskReturn.setUpdateDate(addFriendsTask.getUpdateDate());
			findAddFriendsTaskReturn.setStartDate(addFriendsTask.getStartDate());
			findAddFriendsTaskReturn.setEndDate(addFriendsTask.getEndDate());
			findAddFriendsTaskReturn.setMerchantNo(addFriendsTask.getMerchantNo());
			findAddFriendsTaskReturn.setMerchantName(addFriendsTask.getMerchantName());
			
			logger.debug("findAddFriendsTask(AddFriendsTaskDto) - end - return value={}", findAddFriendsTaskReturn); 
			return findAddFriendsTaskReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找加粉任务详情信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_FIND_ERROR,"查找加粉任务详情信息信息错误！",e);
		}


	}

	
	@Override
	public Page<AddFriendsTaskDto> findAddFriendsTaskPage(FindAddFriendsTaskPage findAddFriendsTaskPage)
			throws TsfaServiceException {
		logger.debug("findAddFriendsTaskPage(FindAddFriendsTaskPage findAddFriendsTaskPage={}) - start", findAddFriendsTaskPage); 

		AssertUtils.notNull(findAddFriendsTaskPage);
		List<AddFriendsTaskDto> returnList=null;
		int count = 0;
		try {
			count = addFriendsTaskDao.findAddFriendsTaskPageCount(findAddFriendsTaskPage);
			if(count >0) {
				returnList = addFriendsTaskDao.findAddFriendsTaskPage(findAddFriendsTaskPage);
			}
		}  catch (Exception e) {
			logger.error("加粉任务详情信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_FIND_PAGE_ERROR,"加粉任务详情信息不存在错误.！",e);
		}
		Page<AddFriendsTaskDto> returnPage = new Page<AddFriendsTaskDto>(returnList, count, findAddFriendsTaskPage);

		logger.debug("findAddFriendsTaskPage(FindAddFriendsTaskPage) - end - return value={}", returnPage); 
		return  returnPage;
	}

	@Override
	public AddFriendsTaskCountDto selectAddFriendsTaskDetailCount(AddFriendsTaskDto addFriendsTaskDto) {

		logger.debug("selectAddFriendsTaskDetailCount(AddFriendsTaskDto addFriendsTaskDto={}) - start", addFriendsTaskDto);
		AssertUtils.notNull(addFriendsTaskDto);
	
		AddFriendsTaskCountDto addFriendsTaskCountDto  = new AddFriendsTaskCountDto();
		try {
			addFriendsTaskCountDto =addFriendsTaskDao.selectAddFriendsTaskDetailCount(addFriendsTaskDto);
			//任务微信量
			String noWxGms = this.selectDistinctGroupByNoWxGms(addFriendsTaskDto);
			String num = StringUtils.isEmpty(noWxGms)?"0":String.valueOf(noWxGms.split(",").length);
			addFriendsTaskCountDto.setWxGmCount(num);
		} catch (Exception e) {
			logger.error("加粉任务统计错误", e);
			throw new TsfaServiceException(
					ErrorCode.ADD_FRIENDS_FIND_ERROR, "加粉任务统计错误.！", e);
		}
		return addFriendsTaskCountDto;
	}


	@Override
	public String selectDistinctGroupByNoWxGms(AddFriendsTaskDto addFriendsTaskDto) {
		logger.debug("selectDistinctGroupByNoWxGms(AddFriendsTaskDto addFriendsTaskDto={}) - start", addFriendsTaskDto);
		AssertUtils.notNull(addFriendsTaskDto);
		String noWxGms =null;
		try {
			noWxGms =addFriendsTaskDao.selectDistinctGroupByNoWxGms(addFriendsTaskDto);
			logger.debug("selectDistinctGroupByNoWxGms(FindAddFriendsTaskPage) - end - return value={}", noWxGms); 
		} catch (Exception e) {
			logger.error("加粉任务统计错误", e);
			throw new TsfaServiceException(
					ErrorCode.ADD_FRIENDS_FIND_ERROR, "加粉任务统计错误.！", e);
		}
		return noWxGms;
	}

}
